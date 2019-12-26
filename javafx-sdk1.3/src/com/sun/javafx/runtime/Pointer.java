/*
 * Copyright 2008-2009 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

package com.sun.javafx.runtime;

import javafx.animation.KeyValueTarget;


/**
 * Pointers
 *
 * @author Brian Goetz
 * @author A. Sundararajan
 */
public class Pointer implements KeyValueTarget {
    private final Type type;
    private final FXObject obj;
    private final int varnum;

    @com.sun.javafx.runtime.annotation.JavafxSignature("(Ljava/lang/Object;)Lcom/sun/javafx/runtime/Pointer;")
    public static Pointer make(Type type, FXObject obj, int varnum) {
        return new Pointer(type, obj, varnum);
    }
    
    public static boolean equals(Pointer p1, Pointer p2) {
        return (p1 == null) ? (p2 == null) : p1.equals(p2);
    }

    private Pointer(Type type, FXObject obj, int varnum) {
        this.type = type;
        this.obj = obj;
        this.varnum = varnum;
    }

    public Object getDefaultValue() {
        switch (type) {
            case BYTE: return (byte)0;
            case SHORT: return (short)0;
            case INTEGER: return 0;
            case LONG: return 0L;
            case FLOAT: return 0.0F;
            case DOUBLE: return 0.0D;
            case BOOLEAN: return false;
            case SEQUENCE: return TypeInfo.Object.emptySequence;
            case OBJECT: return null;
        }
        // unknown type, so return null
        return null;
    }

    public FXObject getFXObject() {
        return obj;
    }

    public int getVarNum() {
        return varnum;
    }

    public Type getType() {
        return type;
    }

    public Pointer unwrap() {
        return this;
    }
    
    public Object get() {
        return obj != null? obj.get$(varnum) : getDefaultValue();
    }

    public Object get(int pos) {
        assert type == Type.SEQUENCE : "expecting a sequence type";
        return obj != null? obj.elem$(varnum, pos) : null;
    }

    public void set(Object value) {
        if (obj != null) {
            obj.set$(varnum, value);
        }
    }

    public void set(int pos, Object value) {
        assert type == Type.SEQUENCE : "expecting a sequence type";
        if (obj != null) {
            obj.set$(varnum, value);
        }
    }

    public int size() {
        assert type == Type.SEQUENCE : "expecting a sequence type";
        return obj != null? obj.size$(varnum)  : 0;
    }

    public Object getValue() {
        return get();
    }

    public void setValue(Object o) {
        set(o);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pointer) {
            Pointer other = (Pointer)o;
            return obj == other.obj && varnum == other.varnum;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(obj) ^ varnum;
    }

    public void addDependency(FXObject dep) {
        if (obj != null) {
            obj.addDependent$(varnum, dep, 0);
        }
    }

    public void removeDependency(FXObject dep) {
        if (obj != null) {
            obj.removeDependent$(varnum, dep);
        }
    }

    public static void switchDependence(Pointer oldPtr, Pointer newPtr, FXObject dep, int depNum) {
        if (oldPtr != newPtr && dep != null) {
            FXObject oldSrc = (oldPtr != null)? oldPtr.getFXObject() : null;
            FXObject newSrc = (newPtr != null)? newPtr.getFXObject() : null;
            int oldVarNum = (oldPtr != null)? oldPtr.getVarNum() : 0;
            int newVarNum = (newPtr != null)? newPtr.getVarNum() : 0;
            dep.switchDependence$(oldSrc, oldVarNum, newSrc, newVarNum, depNum);
        }
    }

    /**
     * A BoundPointer is returned from the Pointer.bind(Pointer) method.
     * BoundPointer instance has to be kept alive till you want bind to be
     * effective. You can explicitly unbind the pointer using the "unbind"
     * method is this class.
     */
    public static class BoundPointer extends Pointer {
        private Pointer srcPtr;
        private FXObject listener;

        private BoundPointer(Pointer destPtr, Pointer srcPtr, FXObject listener) {
            super(destPtr.getType(), destPtr.getFXObject(), destPtr.getVarNum());
            this.srcPtr = srcPtr;
            this.listener = listener;
        }

        /**
         * Uubind the current Pointer from the srcPtr. Repeated calls are fine
         * but subsequent calls are just no-ops. After unbind call, the BoundPointer
         * becomes effectively a regular, unbound Pointer. There is no need to
         * explicitly call 'unbind'. If this BoundPointer instance is unreachable,
         * GC will collect it and srcPtr will eventually see that the listener
         * object is not alive and so remove it from it's dependencies.
         */
        public void unbind() {
            srcPtr.removeDependency(listener);
            // clear everything related to Pointer bind.
            srcPtr = null;
            listener = null;
        }
    }

    /**
     * Implements identity bind expression between "srcPtr" and the current Pointer.
     * Whenever the value pointed by srcPtr changes, the current Pointer's value
     * is set from that.
     *
     * @param srcPtr The source Pointer object to which the current Pointer is bound to
     */
    public BoundPointer bind(Pointer srcPtr) {
        final FXObject thisObj = getFXObject();
        final int thisVarNum = getVarNum();
        final int srcVarNum = srcPtr.getVarNum();
        FXObject listener = new FXBase() {
            @Override
            public boolean update$(FXObject src, final int depNum,
                    int startPos, int endPos, int newLength, final int phase) {
                if ((phase & PHASE_TRANS$PHASE) == PHASE$TRIGGER) {
                    // update value from "src"
                    if (thisObj != null) {
                        thisObj.set$(thisVarNum, src.get$(srcVarNum));
                    }
                }
                return true;
            }
        };
        // initial update from "srcPtr"
        this.set(thisVarNum, srcPtr.get());

        // add dependency so that we will get notified with update$ calls
        srcPtr.addDependency(listener);
        // return a BoundPointer so that use can call call "unbind()" later, if needed
        return new BoundPointer(this, srcPtr, listener);
    }
}

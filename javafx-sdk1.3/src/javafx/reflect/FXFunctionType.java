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

package javafx.reflect;

/** A run-time representation of a JavaFX function type.
 *
 * @author Per Bothner
 * @profile desktop
 */

public class FXFunctionType extends FXType {
    protected int minArgs;
    protected FXType[] argTypes;
    protected boolean varArgs;
    protected FXType returnType;

    FXFunctionType() {
    }

    FXFunctionType(FXType[] argTypes, FXType returnType) {
        this.argTypes = argTypes;
        minArgs = argTypes.length;
        this.returnType = returnType;
    }

    /** The fixed (minimum) number of arguments needed.
     * Does not count varargs, and (possible future) optional args.
     */
    public int minArgs() { return minArgs; }

    /** Was this method declared to take a variable number of arguments?
     * This is a place-holder for future functionality.  (JavaFX doesn't yet
     * support var-args, and we don't set it properly for Java methods either.)
     */
    public boolean isVarArgs() { return varArgs; }

    public FXType getArgumentType(int i) {
        return argTypes[varArgs && i >= minArgs ? minArgs : i]; }

    public FXType getReturnType() { return returnType; }

    public boolean equals(FXFunctionType ftype) {
        if (minArgs != ftype.minArgs || varArgs != ftype.varArgs
              || ! returnType.equals(ftype.returnType))
            return false;
        for (int i = minArgs; --i >= 0; ) {
            if (! argTypes[i].equals(ftype.argTypes[i]))
                return false;
        }
        return true;
    }
    
    public void toStringRaw(StringBuilder sb) {
        sb.append('(');
        int n = minArgs();
        for (int i = 0; i < n; i++) {
            if (i > 0)
                sb.append(',');
            getArgumentType(i).toStringTerse(sb);
        }
        sb.append(')');
        sb.append(':');
        returnType.toStringTerse(sb);
    }
    protected void toStringTerse(StringBuilder sb) {
        sb.append("function");
        toStringRaw(sb);
    }
}


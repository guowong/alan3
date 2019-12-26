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

package com.sun.javafx.functions;

import com.sun.javafx.runtime.FXObject;

public class Function5<R, A1, A2, A3, A4, A5> extends Function<R> {
    public Function5() {}
    
    public Function5(final FXObject implementor, final int number) {
        super(implementor, number);
    }
    
    // Get the implementor to invoke the function.
    // Don't override this.
    public Object invoke$(Object arg1, Object arg2, Object[] rargs) {
        if (implementor != null) {
            return implementor.invoke$(number, arg1, arg2, rargs);
        } else {
            return invoke((A1)arg1, (A2)arg2, (A3)rargs[0], (A4)rargs[1], (A5)rargs[2]);
        }
    }
    
    // Override this
    public R invoke(A1 x1, A2 x2, A3 x3, A4 x4, A5 x5) {
        if (implementor != null) {
            return (R) implementor.invoke$(number, x1, x2, new Object[] { x3,  x4, x5 });
        } else {
            throw new RuntimeException("invoke function missing");
        }
    }
}

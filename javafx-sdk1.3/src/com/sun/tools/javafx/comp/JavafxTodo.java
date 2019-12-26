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

package com.sun.tools.javafx.comp;

import com.sun.tools.mjavac.util.*;

/** A queue of all as yet unattributed classes.
 *
 *  <p><b>This is NOT part of any API supported by Sun Microsystems.  If
 *  you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class JavafxTodo extends ListBuffer<JavafxEnv<JavafxAttrContext>> {
    /** The context key for the todo list. */
    protected static final Context.Key<JavafxTodo> javafxTodoKey =
	new Context.Key<JavafxTodo>();

    /** Get the Todo instance for this context. */
    public static JavafxTodo instance(Context context) {
	JavafxTodo instance = context.get(javafxTodoKey);
	if (instance == null)
	    instance = new JavafxTodo(context);
	return instance;
    }

    /** Create a new todo list. */
    protected JavafxTodo(Context context) {
	context.put(javafxTodoKey, this);
    }
}

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

package com.sun.javafx.api.tree;

import javax.lang.model.element.Name;
import com.sun.javafx.api.JavafxBindStatus;
import com.sun.tools.javafx.tree.JFXType;

/**
 * A tree node for a variable declaration.
 *
 * For example:
 * <pre>
 *   <em>modifiers</em> <em>type</em> <em>name</em> <em>initializer</em> ;
 * </pre>
 *
 * @see "The Java Language Specification, 3rd ed, sections 8.3 and 14.4"
 *
 * @author Peter von der Ah&eacute;
 * @author Jonathan Gibbons
 * @since 1.6
 */
public interface VariableTree extends ExpressionTree {
    ModifiersTree getModifiers();
    Name getName();
    Tree getType();
    ExpressionTree getInitializer();
    JFXType getJFXType();
    OnReplaceTree getOnReplaceTree();
    OnReplaceTree getOnInvalidateTree();
    JavafxBindStatus getBindStatus();
}

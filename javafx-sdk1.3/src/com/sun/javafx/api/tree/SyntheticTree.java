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

/**
 * Common interface for JFX Tree nodes that allows tree walkers to discover
 * whether a node was synthesized by the compiler (synthetic main() for instance)
 * or was created as a consequence of parsing the input source. This interface
 * is mainly used by IDE plugins, but is available to any tree walker.
 *
 * @author Jim Idle
 */
public interface SyntheticTree {

    /**
     * Enumerates all the sources of AST nodes. Currently there are only
     * nodes that reflect real source code, and nodes that were created by the
     * compiler to support execution or code generation etc.
     */
    public enum SynthType {

        /**
         * Used to flag a node as being the result of compiled source code.
         */
        COMPILED,

        /**
         * Used to flag a node as generated by the compiler to support code generation,
         * or execution. This may be expanded later to distinguish other nodes such as
         * entries created to assist IDE plugins and so on.
         */
        SYNTHETIC
    }

    /**
     * Sets the generated type of the node, overriding the default of SynthType.COMPILED
     */
    void setGenType(SynthType getType);

    /**
     * Returns the generated type of the node
     */
    SynthType getGenType();

}
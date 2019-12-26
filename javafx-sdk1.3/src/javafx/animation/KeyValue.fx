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

package javafx.animation;

import java.lang.Object;

/**
 * Defines a key value to be interpolated for a particular interval along
 * the animation.
 * <p>
 * By default, {@code Interpolator.LINEAR} is used in the interval.
 *
 * <p>
 * Users will not normally use this class directly. It is a runtime
 * support class for the animation feature of the JavaFX compiler.
 *
 * @profile common
 * @see Interpolator
 * @see KeyValueTarget
 */
public class KeyValue {
    
    /**
     * Target variable holds the key value.
     *
     * @profile common
     * @defaultvalue null
     */      
    public-init var target: KeyValueTarget;
    
    /**
     * Target value
     *
     * @profile common
     * @defaultvalue null
     */      
    public-init var value: function(): Object;
    
    /**
     * {@link Interpolator} to be used for calculating the key value
     * along the particular interval. By default, {@link Interpolator.LINEAR}
     * is used.
     *
     * @profile common
     * @defaultvalue Interpolator.LINEAR
     */      
    public-init var interpolate: Interpolator = Interpolator.LINEAR;

    /**
     * Evaluated value for this KeyValue
     *
     * @treatasprivate Implementation detail
     */
    package var evaluation:Object = null;
}

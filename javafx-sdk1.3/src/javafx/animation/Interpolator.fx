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
import  com.sun.javafx.animation.InterpolatorFactory;

/**
 * Built-in interpolator that provides discrete time interpolation. 
 * The return value of {@code interpolate()} is {@code endValue}
 * only when the input {@code faction} is 1.0, and {@code startValue} otherwise.
 *
 * @profile common
 */
public def DISCRETE:Interpolator =
         CoreInterpolator {
             i: Timeline.getInterpolatorFactory().getDiscreteInstance();
         };

/**
 * Built-in interpolator that provides linear time interpolation.
 * The return value of {@code interpolate()} is 
 * {@code startValue} + ({@code endValue} - {@code startValue}) * {@code faction}. 
 *
 * @profile common
 */
public def LINEAR:Interpolator =
        CoreInterpolator { 
            i: Timeline.getInterpolatorFactory().getLinearInstance();
        };

/**
 * Built-in interpolator instance that provides ease in/out behavior. It uses
 * default values of 0.2 and 0.2 for the acceleration and deceleration
 * factors, respectively.
 *
 * @profile common
 */
public def EASEBOTH:Interpolator =
        CoreInterpolator { 
            i: Timeline.getInterpolatorFactory().getEasingInstance();
        };

/**
 * Built-in interpolator instance that provides ease in behavior. It uses value of 0.2
 * for the acceleration factor.
 *
 * @profile common
 */
public def EASEIN:Interpolator =
        CoreInterpolator {
            i: Timeline.getInterpolatorFactory().getEasingInstance(0.2 as Float,
                0.0 as Float);
        };

/**
 * Built-in interpolator instance that provides ease out behavior. It uses value of 0.2
 * for the deceleration factor.
 *
 * @profile common
 */
public def EASEOUT:Interpolator =
        CoreInterpolator {
            i: Timeline.getInterpolatorFactory().getEasingInstance(0.0 as Float,
                0.2 as Float);
        };

/**
 * Built-in interpolator instance that is shaped using the spline control points defined 
 * by ({@code x1}, {@code y1}) and ({@code x2}, {@code y2}).  The anchor points of the 
 * spline are implicitly defined as ({@code 0.0}, {@code 0.0}) and ({@code 1.0}, {@code 1.0}).
 *
 * @param x1    x coordinate of the first control point
 * @param y1    y coordinate of the first control point
 * @param x2    x coordinate of the second control point
 * @param y2    y coordinate of the second control point
 * @return  A spline interpolator
 *
 * @profile common
 */
public function SPLINE(x1: Number, y1: Number, x2: Number, y2: Number):Interpolator {
        CoreInterpolator { 
            i: Timeline.getInterpolatorFactory().getSplineInstance(x1.floatValue(),
                y1.floatValue(), x2.floatValue(), y2.floatValue());
        }
    }


/**
 * The abstract class defines the single {@link #interpolate()} method,
 * which is used to calculate interpolated values.  Various built-in
 * implementations of this class are offered. Applications may choose to implement 
 * their own Interpolator to get custom interpolation behavior.
 *
 * @profile common
 */
public abstract class Interpolator {
    
    /**
     * This function takes {@code startValue} and {@code endValue} along with {@code faction} 
     * between 0.0 and 1.0 and returns another value, between {@code startValue} and 
     * {@code1 endValue}. The purpose of the function is to define how time 
     * (represented as a (0.0 - 1.0) fraction of the duration of an animation) is altered 
     * to derive different value calculations during an animation.
     *
     * @param startValue start value
     * @param endValue   end value
     * @param fraction   a value between 0.0 and 1.0
     * @return interpolated value
     *
     * @profile common
     */    
    public abstract function interpolate(startValue:Object, endValue:Object, fraction:Number):Object;
}

class CoreInterpolator extends SimpleInterpolator {
    var i:com.sun.javafx.animation.Interpolator;

    override function curve(t: Number) : Number {
        i.interpolate(t)
    }
}

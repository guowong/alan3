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

import java.lang.Comparable;
import java.lang.Object;
import javafx.lang.Duration;

/**
 * Defines target values at a specified point in time for a set of variables
 * that are interpolated along a {@link Timeline}.
 *
 * <p>
 * The developer controls the interpolation of a set of variables for the
 * interval between successive key frames by providing a target value and
 * an {@link Interpolator} associated with each variable. The variables are
 * interpolated such that they will reach their target value at the specified time.
 * An {@link #action} function is invoked on each KeyFrame if one is provided.
 *
 * @profile common
 * @see Timeline
 * @see Interpolator
 */
public class KeyFrame extends Comparable {
    /**
     * Defines the reference elapsed time offset within a single cycle
     * of a {@link Timeline} at which the associated values will be set and at
     * which the {@link #action} function variable will be called.
     * {@code KeyFrame}s having {@code time < 0s} will be ignored by the
     * {@code Timeline}.
     *
     * @see Timeline#keyValues
     * @profile common
     * @defaultvalue 0s
     */
    public var time: Duration on replace {
        if(owner != null) {
            owner.sortAndComputeTL(false);
            owner.invalidate();
        }
    };

    /**
     * The list of target variables and the desired values they should
     * interpolate at the specified time of this {@code KeyFrame}.
     *
     * Commonly, KeyValues use a constant value, for example:
     *   {@code values: x => 10}
     *
     * but can also use a non-constant value, such as:
     *   {@code values: x => someVal + 5}
     *
     * KeyValue.value expressions are evaluated once before a Timeline is
     * played.  The results are saved and used for each play of the Timeline.
     *
     * For some Timelines, it is desirable for KeyValues to be re-evaluated and
     * take on a different value for each run (or even during a run).  The
     * {@code Timeline.evaluateKeyValues()} function allows this.
     *
     * @see Timeline#evaluateKeyValues
     * @profile common
     * @defaultvalue null
     */
    public var values: KeyValue[];

    /**
     * A function that is called when the elapsed time on a cycle passes
     * the specified time of this {@code KeyFrame}.
     * The {@code action} function variable will be called if the elapsed
     * time passes the indicated value, even if it never equaled the
     * time value exactly.
     * 
     * @profile common
     * @defaultvalue null
     */
    public var action: function();

    /**
     * Defines whether or not the {@link #action()} function
     * can be skipped if the master timer gets behind and
     * more than one {@link Timeline} cycles are skipped
     * between time pulses.
     * If {@code true}, only one call to the {@link #action}
     * function variable will occur for each time pulse, regardless of
     * how many cycles have occurred since the last time pulse
     * was processed.
     * 
     * @profile common
     * @defaultvalue false
     */
    public var canSkip: Boolean = false;

    /**
     * The timeline in which this key frame to be executed. It 
     * provides feedback to timeline if there is any var
     * change that timeline needs to be invalidated.
     */
    package var owner: Timeline;
    
    /**
     * A comparison function used to sort KeyFrames by their
     * specified reference time.
     * 
     * @param o the {@code KeyFrame} to compare to
     * @return  an Integer value<br>
                > 0 if specified {@code KeyFrame} timing is ahead of this <br>
                = 0 if they have the same timing<br>
                < 0 if specified {@code KeyFrame} timing is behind this<br>
     * @profile common
     */
    public override function compareTo(o:Object):Integer {
        var kf = o as KeyFrame;
        return time.compareTo(kf.time);
    }

    package function visit() {
        for (kv in values) {
            if (kv.target != null and kv.value != null) {
                kv.target.set(kv.evaluation);
            }
        }
        if (action != null) {
            action();
        }
    }
}

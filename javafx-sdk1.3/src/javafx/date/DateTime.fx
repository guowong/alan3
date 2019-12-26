/*
 * Copyright 2009 Sun Microsystems, Inc.  All Rights Reserved.
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

package javafx.date;

import com.sun.javafx.runtime.date.DateTimeEngine;
import com.sun.javafx.runtime.date.DateTimeConverter;
import java.lang.System;
import java.util.TimeZone;

/**
 * <code>DateTime</code> represents a specific instant in time, with
 * millisecond precision. Unlike <code>java.util.Date</code>,
 * <code>DateTime</code> supports the "proleptic Gregorian calendar"
 * (the pure Gregorian calendar with no Julian-Gregorian transition).
 *
 * @profile common
 */
public class DateTime {
    /**
     * The instant value represented by the number of milliseconds
     * since the Epoch (1970-01-01T00:00:00Z).
     *
     * @defaultvalue the instant at which this DateTime was instantiated
     */
    public-init var instant: Long;

    var tz: TimeZone;
    var engine: DateTimeEngine;
    var tzid: String;

    init {
        if (not isInitialized(tzid)) {
            tz = TimeZone.getDefault();
            tzid = tz.getID();
        }
        if (isInitialized(engine)) {
            if (tz != engine.getZone()) {
                engine.setZone(tz);
            }
            normalize();
        } else if (not isInitialized(instant)) {
            instant = System.currentTimeMillis();
        }
    }

    /**
     * Converts this <code>DateTime</code> object to a
     * <code>String</code> of the form:
     * <pre>
     *   ['-']yyyy '-' MM '-' dd 'T' hh ':' mm ':' ss['.' f] [zzzzzz]
     * </pre>
     * where
     * <ul>
     *   <li><em>[ '-' ]</em> is an optional negative sign indicating the
     *   year is before 1. (The year before 1 is represented as
     *   "-0001".)</li>
     *
     *   <li><em>yyyy</em> is a four-or-more digit number representing
     *   the year.</li>
     *
     *   <li><em>'-'</em> is a date field separater.</li>
     *
     *   <li><em>MM</em> is a two-digit number representing the month
     *   (01 - 12).</li>
     *
     *   <li><em>dd</em> is a two-digit number representing the day of
     *   month (01 - 31).</li>
     *
     *   <li><em>'T'</em> is a separater between the date fields and
     *   time fields.</li>
     *
     *   <li><em>hh</em> is a two-digit number representing the hour
     *   in the 24-hour clock (00 - 23).</li>
     *
     *   <li><em>':'</em> is a time field separater.</li>
     *
     *   <li><em>mm</em> is a two-digit number representing the minute
     *   [00-59].</li>
     *
     *   <li><em>ss</em> is a two-digit number representing the second
     *   (00 - 59).</li>
     *
     *   <li><em>['.' f]</em> is an optional number representing the
     *   fractional seconds. For example, if the fractional seconds
     *   value is 120 milliseconds, it is represented as ".12".  If
     *   the value is 0, this part is empty.</li>
     *
     *   <li><em>[zzzzzz]</em> represents optional time zone
     *   information as <em>'Z' | sign hh ':' mm</em>, where:
     *       <ul>
     *       <li><em>'Z'</em> represents UTC.</li>
     *       <li><em>sign</em> is character <code>'+'</code>
     *       representing a positive time zone offset, or character
     *       <code>'-'</code> representing a negative time zone
     *       offset.</li>
     *       <li><em>hh</em> is a two-digit number representing hours
     *       (00 - 14).</li>
     *       <li><em>':'</em> is a separater between hours and minutes.
     *       <li><em>mm</em> is a two-digit number representing
     *       minutes (00 - 59).</li>
     *       </ul>
     *     If this <code>DateTime</code> object has no time zone, this
     *     part is empty.</li>
     * </ul>
     *
     * @treatasprivate
     */
    public /*override*/ function impl_toString():String {
        normalize();
        engine.toString();
    }

    /**
     * Converts this <code>DateTime</code> object to a
     * <code>Long</code> instant value represented by the number of milliseconds
     * since the Epoch (1970-01-01T00:00:00Z).     
     *
     * @treatasprivate
     */
    public function impl_toInstant():Long {
        normalize();
        instant;
    }

    /**
     * Converts this <code>DateTime</code> object to a
     * <code>String</code> of the form defined by RFC 822:
     * <pre><code>
     *  EEE ',&nbsp;' dd '&nbsp;' MMM '&nbsp;' yyyy '&nbsp;' HH ':' mm ':' ss '&nbsp;' Z
     * </code></pre>
     * where
     * <ul>
     *   <li><em>EEE</em> is a day of week abbreviation, such as "Sun".</li>
     *
     *   <li><em>dd</em> is a two-digit number representing day of
     *   month (01 - 31).</em>
     *
     *   <li><em>MMM</em> is a month abbreviation, such as "Jan".</li>
     *
     *   <li><em>yyyy</em> is a number representing the year.</li>
     *
     *   <li><em>HH</em> is a two-digit number representing the hour
     *   in the 24-hour clock (00 - 23).</li>
     *
     *   <li><em>mm</em> is a two-digit number representing the
     *   minute (00 - 59).</li>
     *
     *   <li><em>ss</em> is a two-digit number representing the second
     *   (00 - 59).</li>
     *
     *   <li><em>Z</em> is either a zone name defined by RFC 822 or
     *   sign followed by 4-digit time zone offset, such as
     *   "-0800". No military single letter zone representation is
     *   supported for formatting.</li>
     *
     *   <li><em>',&nbsp;', '&nbsp;', ':'</em> are separaters between
     *   fields.</li>
     * </ul>
     *
     * @treatasprivate
     */
    public function impl_toRFC822String():String {
        return DateTimeConverter.toRFC822String(getDateTimeEngine());
    }

    function getDateTimeEngine():DateTimeEngine {
        if (engine == null) {
            engine = DateTimeEngine.getInstance(instant, tz);
        }
        engine;
    }

    function normalize():Void {
        if (engine == null) {
            getDateTimeEngine();
        } else {
            if (not engine.isNormalized()) {
                instant = engine.getInstant();
            }
        }
    }
}

/**
 * Parses the given input in an ISO 8601 format and returns a
 * <code>DateTime</code> object.
 *
 * @treatasprivate
 */
public function impl_parseXMLDateTime(input:String):DateTime {
    var date:DateTimeEngine = DateTimeConverter.parseXMLDateTime(input);
    var zone:TimeZone = date.getZone();
    var id:String = if (zone != null) then zone.getID() else "";
    DateTime {
        instant: date.getInstant();
        engine: date
        tz: zone
        tzid: id
    };
}

/**
 * Parses the given <code>input</code> in the RFC 822 date-time format
 * and returns a <code>DateTime</code> object.
 *
 * <p>Any military single letter zone is converted to its
 * corresponding custom time zone name, such as "A" converted to
 * "GMT-01:00".
 *
 * @param input the string in the RFC 822 date-time format to be parsed
 * @exception IllegalArgumentException if any field has an invalid
 *            value, such as "24" for the hour field, or any unknown
 *            name as the day of week, the month, or the time zone
 *            field.
 * @see #impl_toRFC822String()
 * @treatasprivate
 */
public function impl_parseRFC822DateTime(input:String):DateTime {
    var date:DateTimeEngine = DateTimeConverter.parseRFC822DateTime(input);
    var zone:TimeZone = date.getZone();
    var id:String = if (zone != null) then zone.getID() else "";
    DateTime {
        instant: date.getInstant();
        engine: date
        tz: zone
        tzid: id
    };
}

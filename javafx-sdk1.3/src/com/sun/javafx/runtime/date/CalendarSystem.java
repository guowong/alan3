/*
 * Copyright 2000-2009 Sun Microsystems, Inc.  All Rights Reserved.
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

package com.sun.javafx.runtime.date;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <code>CalendarSystem</code> is an abstract class that defines the
 * programming interface to deal with calendar date and time.
 *
 * <p><code>CalendarSystem</code> instances are singletons. For
 * example, there exists only one Gregorian calendar instance in the
 * Java runtime environment. A singleton instance can be obtained
 * calling one of the static factory methods.
 *
 * <h4>CalendarDate</h4>
 *
 * <p>For the methods in a <code>CalendarSystem</code> that manipulate
 * a <code>CalendarDate</code>, <code>CalendarDate</code>s that have
 * been created by the <code>CalendarSystem</code> must be
 * specified. Otherwise, the methods throw an exception. This is
 * because, for example, a Chinese calendar date can't be understood
 * by the Hebrew calendar system.
 *
 * @see CalendarDate
 * @author Masayoshi Okutsu
 */

/*
 * Derived from JDK7 sun.util.calendar.
 */
abstract class CalendarSystem {

    public abstract CalendarDate getCalendarDate();

    /**
     * Calculates calendar fields from the specified number of
     * milliseconds since the Epoch, January 1, 1970 00:00:00 UTC
     * (Gregorian). This method doesn't check overflow or underflow
     * when adjusting the millisecond value (representing UTC) with
     * the time zone offsets (i.e., the GMT offset and amount of
     * daylight saving).
     *
     * @param millis the offset value in milliseconds from January 1,
     * 1970 00:00:00 UTC (Gregorian).
     * @return a <code>CalendarDate</code> instance that contains the
     * calculated calendar field values.
     */
    public abstract CalendarDate getCalendarDate(long millis);

    public abstract CalendarDate getCalendarDate(long millis, CalendarDate date);

    public abstract CalendarDate getCalendarDate(long millis, TimeZone zone);

    /**
     * Constructs a <code>CalendarDate</code> that is specific to this
     * calendar system. All calendar fields have their initial
     * values. The {@link TimeZone#getDefault() default time zone} is
     * set to the instance.
     *
     * @return a <code>CalendarDate</code> instance that contains the initial
     * calendar field values.
     */
    public abstract CalendarDate newCalendarDate();

    public abstract CalendarDate newCalendarDate(TimeZone zone);

    /**
     * Returns the number of milliseconds since the Epoch, January 1,
     * 1970 00:00:00 UTC (Gregorian), represented by the specified
     * <code>CalendarDate</code>.
     *
     * @param date the <code>CalendarDate</code> from which the time
     * value is calculated
     * @return the number of milliseconds since the Epoch.
     */
    public abstract long getTime(CalendarDate date);

    /**
     * Returns the length in days of the specified year by
     * <code>date</code>. This method does not perform the
     * normalization with the specified <code>CalendarDate</code>. The
     * <code>CalendarDate</code> must be normalized to get a correct
     * value.
     */
    public abstract int getYearLength(CalendarDate date);

    /**
     * Returns the number of months of the specified year. This method
     * does not perform the normalization with the specified
     * <code>CalendarDate</code>. The <code>CalendarDate</code> must
     * be normalized to get a correct value.
     */
    public abstract int getYearLengthInMonths(CalendarDate date);

    /**
     * Returns the length in days of the month specified by the calendar
     * date. This method does not perform the normalization with the
     * specified calendar date. The <code>CalendarDate</code> must
     * be normalized to get a correct value.
     *
     * @param date the date from which the month value is obtained
     * @return the number of days in the month
     * @exception IllegalArgumentException if the specified calendar date
     * doesn't have a valid month value in this calendar system.
     */
    public abstract int getMonthLength(CalendarDate date); // no setter

    /**
     * Returns the length in days of a week in this calendar
     * system. If this calendar system has multiple radix weeks, this
     * method returns only one of them.
     */
    public abstract int getWeekLength();

    /**
     * Returns a <code>CalendarDate</code> of the n-th day of week
     * which is on, after or before the specified date. For example, the
     * first Sunday in April 2002 (Gregorian) can be obtained as
     * below:
     *
     * <pre><code>
     * Gregorian cal = CalendarSystem.getGregorianCalendar();
     * CalendarDate date = cal.newCalendarDate();
     * date.setDate(2004, cal.APRIL, 1);
     * CalendarDate firstSun = cal.getNthDayOfWeek(1, cal.SUNDAY, date);
     * // firstSun represents April 4, 2004.
     * </code></pre>
     *
     * This method returns a new <code>CalendarDate</code> instance
     * and doesn't modify the original date.
     *
     * @param nth specifies the n-th one. A positive number specifies
     * <em>on or after</em> the <code>date</code>. A non-positive number
     * specifies <em>on or before</em> the <code>date</code>.
     * @param dayOfWeek the day of week
     * @param date the date
     * @return the date of the nth <code>dayOfWeek</code> after
     * or before the specified <code>CalendarDate</code>
     */
    public abstract CalendarDate getNthDayOfWeek(int nth, int dayOfWeek,
                                                 CalendarDate date);

    public abstract CalendarDate setTimeOfDay(CalendarDate date, int timeOfDay);

    /**
     * Checks whether the calendar fields specified by <code>date</code>
     * represents a valid date and time in this calendar system. If the
     * given date is valid, <code>date</code> is marked as <em>normalized</em>.
     *
     * @param date the <code>CalendarDate</code> to be validated
     * @return <code>true</code> if all the calendar fields are consistent,
     * otherwise, <code>false</code> is returned.
     * @exception NullPointerException if the specified
     * <code>date</code> is <code>null</code>
     */
    public abstract boolean validate(CalendarDate date);

    /**
     * Normalizes calendar fields in the specified
     * <code>date</code>. Also all {@link CalendarDate#FIELD_UNDEFINED
     * undefined} fields are set to correct values. The actual
     * normalization process is calendar system dependent.
     *
     * @param date the calendar date to be validated
     * @return <code>true</code> if all fields have been normalized;
     * <code>false</code> otherwise.
     * @exception NullPointerException if the specified
     * <code>date</code> is <code>null</code>
     */
    public abstract boolean normalize(CalendarDate date);
}

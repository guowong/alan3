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

package com.sun.javafx.runtime.sequence;

import java.util.BitSet;
import java.util.Iterator;

import com.sun.javafx.runtime.TypeInfo;

/**
 * Sequences are immutable, homogeneous, ordered collections.  A sequence has an element type,
 * a length, and a list of elements.  New sequences can be derived by calling the factory methods
 * (insert, delete, subsequence, etc) or can be constructed with the static factories in Sequences.
 * Sequence types are reified; the sequence references the Class object for the element type.
 *
 * Sequences are stored as trees.  The "leaf" nodes are array-based sequences; intermediate nodes are "views"
 * onto underlying sequences, performing transformations such as adding elements, filtering elements, changing the
 * order of elements, etc.  Do not use the constructors for the various Sequence implementation classes to produce
 * sequences; use the factory methods in the Sequence interface or the static factories in the Sequences class.
 *
 * Sequences with elements of type Integer, Boolean, and Double are special; in these cases, when the
 * get() operation might return a null (because the index is out of range), it will instead return the
 * default value for that type (zero or false).
 *
 * @author Brian Goetz
 */
public interface Sequence<T> extends Iterable<T> {
    /** How large is this sequence?  */
    public int size();

    /** Is this sequence empty? */
    public boolean isEmpty();

    /** What is the element type? */
    public TypeInfo<T> getElementType();

    /** Copy the contents of this sequence to an array, at a specified offset within the destination array */
    public void toArray(Object[] array, int destOffset);

    /** Copy a portion of the contents of this sequence to an array, at a specified offset within the destination array */
    public void toArray(int sourceOffset, int length, Object[] array, int destOffset);

    public void toArray(int sourceOffset, int length, char[] dest, int destOffset);
    public void toArray(int sourceOffset, int length, boolean[] dest, int destOffset);
    public void toArray(int sourceOffset, int length, byte[] dest, int destOffset);
    public void toArray(int sourceOffset, int length, short[] dest, int destOffset);
    public void toArray(int sourceOffset, int length, int[] dest, int destOffset);
    public void toArray(int sourceOffset, int length, long[] dest, int destOffset);
    public void toArray(int sourceOffset, int length, float[] dest, int destOffset);
    public void toArray(int sourceOffset, int length, double[] dest, int destOffset);

    /** Extract the element at the specified position.  If the position is out of range, the default value for
     * the element type is returned; either null, zero for Integer or Double sequences, or false for Boolean
     * sequences.  */
    public T get(int position);

    public boolean getAsBoolean(int position);

    public char getAsChar(int position);

    public byte getAsByte(int position);

    public short getAsShort(int position);

    public int getAsInt(int position);

    public long getAsLong(int position);

    public float getAsFloat(int position);

    public double getAsDouble(int position);
    
    /** Extract a slice of the sequence */
    public Sequence<? extends T> getSlice(int startPos, int lastPos);

    /** Select elements from the sequence matching the specified predicate. */
    public Sequence<? extends T> get(SequencePredicate<? super T> predicate);

    /**
     * Return a BitSet indicating which elements of the sequence match the given predicate.  AbstractSequence
     * provides a default implementation in terms of get(i); implementations may want to provide an optimized
     * version.
     */
    public BitSet getBits(SequencePredicate<? super T> predicate);

    public Iterator<T> iterator();
    public Iterator<T> iterator(int startPos, int endPos);

    T getDefaultValue();

    Sequence<T> getEmptySequence();

    public void incrementSharing();

    public void decrementSharing();
}

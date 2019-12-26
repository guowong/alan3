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

package javafx.util;

import java.lang.Object;
import java.lang.Comparable;
import java.lang.System;
import java.util.Comparator;

import java.lang.UnsupportedOperationException;


/**
 * This class contains various functions for manipulating sequences (such as 
 * sorting and searching). All of these functions are nonmutative, they do not
 * change the input-parameters, but create new instances for output.
 * 
 * @author Michael Heinrichs
 * @profile common
 */
public class Sequences {
}

// script-level "static" functions below
    
    /**
     * Returns {@code true} if the two specified sequences are equal to one another. 
     * The two sequences are considered equal if both sequences contain the same
     * number of elements, and all corresponding pairs of elements in the two 
     * sequences are identical. In other words, the two sequences are equal if
     * they contain the same elements in the same order.
     * 
     * @param seq1 One sequence to be tested for equality
     * @param seq2 The other array to be tested for equality 
     *
     * @profile common
     */    
    public function isEqualByContentIdentity(seq1: Object[], seq2: Object[]): Boolean {
        return com.sun.javafx.runtime.sequence.Sequences.isEqualByContentIdentity(seq1, seq2);
    }
    
    /**
     * Searches the specified sequence for the specified object using the 
     * binary search algorithm. The sequence must be sorted into ascending 
     * order according to the natural ordering of its elements (as by 
     * the {@code sort(Sequence<T>)} method) prior to making this call. 
     * <p />
     * If it is not sorted, the results are undefined. If the array contains 
     * multiple elements equal to the specified object, there is no guarantee 
     * which one will be found.
     * 
     * @param seq The sequence to be searched.
     * @param key The value to be searched for.
     * @return Index of the search key, if it is contained in the array; 
     *         otherwise, (-(insertion point) - 1). The insertion point is 
     *         defined as the point at which the key would be inserted into the 
     *         array: the index of the first element greater than the key, or
     *         {@code a.length} if all elements in the array are less than the
     *         specified key. Note that this guarantees that the return value
     *         will be >= 0 if and only if the key is found.
     *
     * @profile common 
     */
    public function binarySearch(seq: Comparable[], key: Comparable): Integer {
        return com.sun.javafx.runtime.sequence.Sequences.binarySearch(seq, key);
    }
    
    /**
     * Searches the specified array for the specified object using the 
     * binary search algorithm. The array must be sorted into ascending 
     * order according to the specified comparator (as by the 
     * {@code sort(Sequence<T>, Comparator<? super T>)}  method) prior to
     * making this call. 
     * <p />
     * If it is not sorted, the results are undefined. If the array contains 
     * multiple elements equal to the specified object, there is no guarantee 
     * which one will be found.
     * 
     * @param seq The sequence to be searched.
     * @param key The value to be searched for.
     * @param c The {@code Comparator} by which the array is ordered. 
     *          A {@code null} value indicates that the elements' natural
     *          ordering should be used.
     * @return Index of the search key, if it is contained in the array; 
     *         otherwise, (-(insertion point) - 1). The insertion point is 
     *         defined as the point at which the key would be inserted into the 
     *         array: the index of the first element greater than the key, or
     *         {@code a.length} if all elements in the array are less than the
     *         specified key. Note that this guarantees that the return value 
     *         will be >= 0 if and only if the key is found.
     *
     * @profile common 
     */
    public function binarySearch(seq: Object[], key: Object, c: Comparator): Integer {
        return com.sun.javafx.runtime.sequence.Sequences.binarySearch(seq, key, c);
    }
    
    /**
     * Searches the specified sequence for the specified object.
     * <p />
     * If the sequence contains multiple elements equal to the specified object, 
     * the first occurence in the sequence will be returned.
     * <p />
     * The method {@code nextIndexByIdentity()} can be used in consecutive  
     * calls to iterate through all occurences of a specified object.
     * 
     * @param seq The sequence to be searched.
     * @param key The value to be searched for.
     * @return Index of the search key, if it is contained in the array; 
     *         otherwise -1.
     *
     * @profile common 
     */
   public function indexByIdentity(seq: Object[], key: Object): Integer {
        return com.sun.javafx.runtime.sequence.Sequences.indexByIdentity(seq, key);
    }

    /**
     * Searches the specified sequence for an object with the same value. The
     * objects are compared using the method {@code equals()}. If the sequence 
     * is sorted, binarySearch should be used instead.
     * <p />
     * If the sequence contains multiple elements equal to the specified object, 
     * the first occurence in the sequence will be returned.
     * <p />
     * The method {@code nextIndexOf()} can be used in consecutive calls to 
     * iterate through all occurences of a specified object.
     * 
     * @param seq The sequence to be searched.
     * @param key The value to be searched for.
     * @return Index of the search key, if it is contained in the array; 
     *         otherwise -1.
     *
     * @profile common 
     */
    public function indexOf(seq: Object[], key: Object): Integer {
        return com.sun.javafx.runtime.sequence.Sequences.indexOf(seq, key);
    }
    
    /**
     * Returns the element with the maximum value in the specified sequence, 
     * according to the natural ordering  of its elements. All elements in the 
     * sequence must implement the {@code Comparable} interface. Furthermore, 
     * all elements in the sequence must be mutually comparable (that is, 
     * {@code e1.compareTo(e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the sequence).
     * <p />
     * If the sequence contains multiple elements with the maximum value, 
     * there is no guarantee which one will be found.
     * 
     * @param seq The sequence to be searched.
     * @return The element with the maximum value.
     *
     * @profile common 
     */
    public function max(seq: Comparable[]): Comparable {
        return com.sun.javafx.runtime.sequence.Sequences.max(seq);
    }
    
    /**
     * Returns the element with the maximum value in the specified sequence, 
     * according to the specified {@code Comparator}. All elements in the 
     * sequence must be mutually comparable by the specified {@code Comparator} 
     * (that is, {@code c.compare(e1, e2)} must not throw a 
     * {@code ClassCastException} for any elements {@code e1} and {@code e2}
     * in the sequence).
     * <p />
     * If the sequence contains multiple elements with the maximum value, 
     * there is no guarantee which one will be found.
     * 
     * @param seq The sequence to be searched.
     * @param c The {@code Comparator} to determine the order of the sequence. 
     *          A {@code null} value indicates that the elements' natural 
     *          ordering should be used.
     * @return The element with the maximum value.
     *
     * @profile common 
     */
    public function max(seq: Object[], c: Comparator): Object {
        return com.sun.javafx.runtime.sequence.Sequences.max(seq, c);
    }
    
    /**
     * Returns the element with the minimum value in the specified sequence, 
     * according to the natural ordering  of its elements. All elements in the 
     * sequence must implement the {@code Comparable} interface. Furthermore, 
     * all elements in the sequence must be mutually comparable (that is, 
     * {@code e1.compareTo(e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the sequence).
     * <p />
     * If the sequence contains multiple elements with the minimum value, 
     * there is no guarantee which one will be found.
     * 
     * @param seq The sequence to be searched.
     * @return The element with the maximum value.
     *
     * @profile common 
     */
    public function min(seq: Comparable[]): Comparable {
        return com.sun.javafx.runtime.sequence.Sequences.min(seq);
    }
    
     /**
     * Returns the element with the minimum value in the specified sequence, 
     * according to the specified {@code Comparator}. All elements in the 
     * sequence must be mutually comparable by the specified {@code Comparator}
     * (that is, {@code c.compare(e1, e2)} must not throw a 
     * {@code ClassCastException} for any elements {@code e1} and {@code e2}
     * in the sequence).
     * <p />
     * If the sequence contains multiple elements with the minimum value, 
     * there is no guarantee which one will be found.
     * 
     * @param seq The sequence to be searched.
     * @param c The {@code Comparator} to determine the order of the sequence. 
     *          A {@code null} value indicates that the elements' natural 
     *          ordering should be used.
     * @return The element with the minimum value.
     *
     * @profile common 
     */
   public function min(seq: Object[], c: Comparator): Object {
        return com.sun.javafx.runtime.sequence.Sequences.min(seq, c);
    }
    
    /**
     * Searches the specified sequence for the specified object, starting the
     * search at the specified position. 
     * <p />
     * If the sequence contains multiple elements equal to the specified object, 
     * the first occurence in the subsequence will be returned.
     * 
     * @param seq The sequence to be searched.
     * @param key The value to be searched for.
     * @param pos The position in the sequence to start the search.
     *            If {@code pos} is negative or 0 the whole sequence will be
     *            searched.
     * @return Index of the search key, if it is contained in the array; 
     *         otherwise -1.
     *
     * @profile common 
     */
    public function nextIndexByIdentity(seq: Object[], key: Object, pos: Integer): Integer {
        return com.sun.javafx.runtime.sequence.Sequences.nextIndexByIdentity(seq, key, pos);
    }
    
    /**
     * Searches the specified sequence for an object with the same value,
     * starting the search at the specified position. The objects are compared 
     * using the method {@code equals()}.
     * <p />
     * If the sequence contains multiple elements equal to the specified object, 
     * the first occurence in the subsequence will be returned.
     * 
     * @param seq The sequence to be searched.
     * @param key The value to be searched for.
     * @param pos The position in the sequence to start the search.
     *            If {@code pos} is negative or 0 the whole sequence will be
     *            searched.
     * @return Index of the search key, if it is contained in the array; 
     *         otherwise -1.
     *
     * @profile common 
     */
    public function nextIndexOf(seq: Object[], key: Object, pos: Integer): Integer {
        return com.sun.javafx.runtime.sequence.Sequences.nextIndexOf(seq, key, pos);
    }
    
    /**
     * Reverses the order of the elements in the specified sequence.
     * <p />
     * This method is immutative, the result is returned in a new sequence,
     * while the original sequence is left untouched.
     *
     * @param seq The sequence which elements are to be reversed.
     * @return The reversed sequence.
     *
     * @profile common
     */
    public function <<reverse>> (seq:Object[]): Object[] {
        return com.sun.javafx.runtime.sequence.Sequences.<<reverse>>(seq);
    }
    
    /**
     * Sorts the specified sequence of objects into ascending order, according 
     * to the natural ordering  of its elements. All elements in the sequence
     * must implement the {@code Comparable} interface. Furthermore, all 
     * elements in the sequence must be mutually comparable (that is, 
     * {@code e1.compareTo(e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the sequence).
     * <p />
     * This method is immutative, the result is returned in a new sequence,
     * while the original sequence is left untouched.
     * <p />
     * This sort is guaranteed to be stable: equal elements will not be 
     * reordered as a result of the sort.
     * <p />
     * The sorting algorithm is a modified mergesort (in which the merge is 
     * omitted if the highest element in the low sublist is less than the 
     * lowest element in the high sublist). This algorithm offers guaranteed 
     * n*log(n) performance. 
     * 
     * @param seq The sequence to be sorted.
     * @return The sorted sequence.
     *
     * @profile common 
     */
    public function sort(seq: Comparable[]): Comparable[] {
        return com.sun.javafx.runtime.sequence.Sequences.sort(seq);
    }
    
    /**
     * Sorts the specified sequence of objects according to the order induced 
     * by the specified {@code Comparator}. All elements in the sequence must be 
     * mutually comparable by the specified {@code Comparator} (that is, 
     * {@code c.compare(e1, e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the sequence).
     * <p />
     * This method is immutative, the result is returned in a new sequence,
     * while the original sequence is left untouched.
     * <p />
     * This sort is guaranteed to be stable: equal elements will not be 
     * reordered as a result of the sort.
     * <p />
     * The sorting algorithm is a modified mergesort (in which the merge is 
     * omitted if the highest element in the low sublist is less than the 
     * lowest element in the high sublist). This algorithm offers guaranteed 
     * n*log(n) performance. 
     * 
     * @param seq The sequence to be sorted.
     * @param c The {@code Comparator} to determine the order of the sequence. 
     *          A {@code null} value indicates that the elements' natural
     *          ordering should be used.
     * @return The sorted sequence.
     *
     * @profile common 
     */
    public function sort(seq: Object[], c: Comparator): Object[] {
        return com.sun.javafx.runtime.sequence.Sequences.sort(seq, c);
    }

    /**
     * Randomly permutes the specified list using a default source of randomness. 
     * All permutations occur with approximately equal likelihood.
     * <p />
     * The hedge "approximately" is used in the foregoing description because 
     * default source of randomness is only approximately an unbiased source of 
     * independently chosen bits. If it were a perfect source of randomly chosen 
     * bits, then the algorithm would choose permutations with perfect uniformity.
     * <p />
     * This method is immutative, the result is returned in a new sequence,
     * while the original sequence is left untouched.
     *
     * @param seq The sequence to be shuffled.
     * @return The shuffled sequence.
     *
     * @profile common
     */
    public function shuffle(seq: Object[]):Object[] {
        return com.sun.javafx.runtime.sequence.Sequences.shuffle(seq);
    }


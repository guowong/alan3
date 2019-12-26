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

package javafx.fxunit;
import java.lang.Object;
import java.lang.NullPointerException;
import junit.framework.TestCase;
import com.sun.javafx.runtime.SystemProperties;

public class FXTestCase extends TestCase {

    init {
       try {
           var codebase = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
           SystemProperties.setFXProperty(SystemProperties.codebase, codebase);
	} catch (ignored : NullPointerException) {
	   // just in case the code source is null
	}
    }

    public function assertEquals(a:Object[], b:Object[]):Void {
        assertEquals(null, a, b);
    }

    public function assertEquals(message:String, a:Object[], b:Object[]):Void {
        assertEquals(message, sizeof a, sizeof b);
        for (i in [0..sizeof a -1]) {
            var ax:Object = a[i];
            var bx:Object = b[i];
            assertEquals(message, ax, bx);
        };
    }
    
    public function assertEquals(expected:Number, actual:Number):Void {
        assertEquals(null, expected, actual);
    }

    public function assertEquals(message:String, expected:Number, actual:Number):Void {
        var exp:Object = expected;
        var act:Object = actual;
        assertEquals(message, exp, act);
    }
}

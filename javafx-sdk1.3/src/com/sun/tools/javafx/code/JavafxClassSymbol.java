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

package com.sun.tools.javafx.code;

import com.sun.tools.mjavac.code.Symbol;
import com.sun.tools.mjavac.code.Symbol.ClassSymbol;
import com.sun.tools.mjavac.code.Type;
import com.sun.tools.mjavac.code.Types;
import com.sun.tools.mjavac.util.List;
import com.sun.tools.mjavac.util.Name;

import static com.sun.tools.mjavac.code.TypeTags.*;
import static com.sun.tools.mjavac.code.Flags.*;

/**
 * Marker wrapper on class: this is a JavaFX class
 * 
 * @author llitchev
 */
public class JavafxClassSymbol extends ClassSymbol {
    public ClassSymbol jsymbol;
    public JavafxClassSymbol scriptSymbol;
    public JavafxVarSymbol thisSymbol;
    public JavafxVarSymbol superSymbol;
    public JavafxVarSymbol scriptAccessSymbol;
    private boolean isScriptingModeScript;
    private int memberVarCount = 0;
    private int scriptVarCount = 0;
    
    /** Creates a new instance of JavafxClassSymbol */
    public JavafxClassSymbol(long flags, Name name, Symbol owner) {
        super(flags, name, owner);
    }

    @Override
    public boolean isSubClass(Symbol base, Types types) {
        /** we need to override this because of the MIXIN flag **/
        if (this == base) {
            return true;
        } else if ((base.flags() & (INTERFACE | JavafxFlags.MIXIN)) != 0) {
            for (Type t = type; t.tag == CLASS; t = types.supertype(t))
                for (List<Type> is = types.interfaces(t);
                     is.nonEmpty();
                     is = is.tail)
                    if (is.head.tsym.isSubClass(base, types)) return true;
        } else {
            for (Type t = type; t.tag == CLASS; t = types.supertype(t))
                if (t.tsym == base) return true;
        }
        return false;
    }

    public void addVar(JavafxVarSymbol vsym, boolean isScriptVar) {
        vsym.setVarIndex(isScriptVar ?
            scriptVarCount++ :
            memberVarCount++);
    }

    public int getMemberVarCount() {
        return memberVarCount;
    }

    public int getScriptVarCount() {
        return scriptVarCount;
    }
    
    public boolean isScriptingModeScript() {
        return isScriptingModeScript;
    }

    public void setScriptingModeScript() {
        isScriptingModeScript = true;
    }
}

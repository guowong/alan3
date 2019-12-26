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

package com.sun.tools.javafx.comp;

import com.sun.javafx.api.JavafxBindStatus;
import com.sun.tools.mjavac.code.Flags;
import com.sun.tools.mjavac.code.Kinds;
import com.sun.tools.mjavac.code.Scope.Entry;
import com.sun.tools.mjavac.code.Symbol;
import com.sun.tools.mjavac.code.Symbol.ClassSymbol;
import com.sun.tools.mjavac.code.Symbol.MethodSymbol;
import com.sun.tools.mjavac.code.Type;
import com.sun.tools.mjavac.code.Type.*;
import com.sun.tools.mjavac.tree.JCTree;
import com.sun.tools.mjavac.tree.JCTree.JCExpression;
import com.sun.tools.mjavac.tree.JCTree.JCStatement;
import com.sun.tools.mjavac.util.JCDiagnostic.DiagnosticPosition;
import com.sun.tools.mjavac.util.List;
import com.sun.tools.mjavac.util.ListBuffer;
import com.sun.tools.mjavac.util.Name;

import com.sun.tools.javafx.code.JavafxFlags;
import com.sun.tools.javafx.code.JavafxTypes;
import com.sun.tools.javafx.code.JavafxSymtab;
import com.sun.tools.javafx.code.JavafxVarSymbol;
import com.sun.tools.javafx.comp.JavafxAbstractTranslation.*;
import com.sun.tools.javafx.tree.*;

import com.sun.tools.mjavac.code.Symbol.VarSymbol;
import static com.sun.tools.mjavac.code.Flags.*;

import java.util.*;

/**
 * This class is used by JavafxInitializationBuilder to determine which inherited
 * attributes and methods have effect in the current javafx class.
 * @author Robert Field
 * @author Jim Laskey
 */
class JavafxAnalyzeClass {
    // Invoking translator.
    private final JavafxInitializationBuilder initBuilder;

    // Position in the current javafx class source.
    private final DiagnosticPosition diagPos;

    // Current class decl.
    private final JFXClassDeclaration currentClassDecl;

    // Current class symbol.
    private final ClassSymbol currentClassSym;

    // Null or symbol for the immediate super class (if a javafx class.)
    private ClassSymbol superClassSym;

    // Resulting list of all superclasses in top down order.
    private ListBuffer<ClassSymbol> superClasses = ListBuffer.lb();

    // Resulting list of immediate mixin classes in left to right order.
    private ListBuffer<ClassSymbol> immediateMixins = ListBuffer.lb();

    // Resulting list of all mixin classes in top down order.
    private ListBuffer<ClassSymbol> allMixins = ListBuffer.lb();

    // Number of vars in the current class (includes mixins.)
    private int classVarCount;

    // Number of vars in the current class (includes mixins.)
    private int scriptVarCount;
    
    // Resulting list of class vars.
    private final ListBuffer<VarInfo> classVarInfos = ListBuffer.lb();

    // Resulting list of script vars.
    private final ListBuffer<VarInfo> scriptVarInfos = ListBuffer.lb();

    // Resulting list of class vars.
    private final ListBuffer<FuncInfo> classFuncInfos = ListBuffer.lb();

    // Resulting list of script vars.
    private final ListBuffer<FuncInfo> scriptFuncInfos = ListBuffer.lb();

    // List of all attributes.  Used to track overridden and mixin attributes.
    private final Map<Name, VarInfo> visitedAttributes = new HashMap<Name, VarInfo>();
    
    // Map of all bind selects used to construct the class update$ method.
    private final HashMap<JavafxVarSymbol, HashMap<JavafxVarSymbol, HashSet<VarInfo>>> classUpdateMap = new LinkedHashMap<JavafxVarSymbol, HashMap<JavafxVarSymbol, HashSet<VarInfo>>>();

    // Map of all bind selects used to construct the script update$ method.
    private final HashMap<JavafxVarSymbol, HashMap<JavafxVarSymbol, HashSet<VarInfo>>> scriptUpdateMap = new LinkedHashMap<JavafxVarSymbol, HashMap<JavafxVarSymbol, HashSet<VarInfo>>>();

    // Resulting list of relevant methods.  A map is used to so that only the last occurrence is kept.
    private final Map<String, FuncInfo> needDispatchMethods = new LinkedHashMap<String, FuncInfo>();

    // List of all methods.  Used to track whether a mixin method should be included.
    private final Map<String, FuncInfo> visitedMethods = new HashMap<String, FuncInfo>();

    // List of classes encountered.  Used to prevent duplication.
    private final Set<Symbol> addedBaseClasses = new HashSet<Symbol>();

    // List of vars (attributes) found in the current javafx class (supplied by JavafxInitializationBuilder.)
    private final List<TranslatedVarInfo> translatedAttrInfo;

    // List of overriding vars (attributes) found in the current javafx class (supplied by JavafxInitializationBuilder.)
    private final List<TranslatedOverrideClassVarInfo> translatedOverrideAttrInfo;

    // List of functions (methods) found in the current javafx class (supplied by JavafxInitializationBuilder.)
    private final List<TranslatedFuncInfo> translatedFuncInfo;

    // Global names table (supplied by JavafxInitializationBuilder.)
    private final Name.Table names;

    // Global types table (supplied by JavafxInitializationBuilder.)
    private final JavafxTypes types;

    // Global defs table (supplied by JavafxInitializationBuilder.)
    private final JavafxDefs defs;

    // Global syms table (supplied by JavafxInitializationBuilder.)
    private final JavafxSymtab syms;
     
    // Class reader used to fetch superclass .class files (supplied by JavafxInitializationBuilder.)
    private final JavafxClassReader reader;


    //
    // This class supers all classes used to hold var information. Consumed by
    // JavafxInitializationBuilder.
    //
    static abstract class VarInfo {
        // Position of the var declaration or current javafx class if read from superclass.
        private final DiagnosticPosition diagPos;

        // Var symbol (unique to symbol.)
        protected final JavafxVarSymbol sym;

        // Name of the var (is it the same as sym.name?)
        private final Name name;

        // Null or code for initializing the var.
        protected final JCExpression initExpr;

        // The class local enumeration value for this var.
        private int enumeration = -1;

        // Inversion of boundBindees.
        private HashSet<VarInfo> binders = new LinkedHashSet<VarInfo>();
        
        // Inversion of invalidators.
        private ListBuffer<BindeeInvalidator> boundInvalidatees = ListBuffer.lb();;
        
        // True if the var needs to generate mixin interfaces (getMixin$, setMixin$ and getVOFF$)
        private boolean needsMixinInterface = false;

        private VarInfo(DiagnosticPosition diagPos, Name name, JavafxVarSymbol attrSym, JCExpression initExpr) {
            this.diagPos = diagPos;
            this.name = name;
            this.sym = attrSym;
            this.initExpr = initExpr;
        }

        // Return the var symbol.
        public JavafxVarSymbol getSymbol() { return sym; }

        // Return the var position.
        public DiagnosticPosition pos() { return diagPos; }

        // Return type information from type translation.
        public Type getRealType()     { return sym.type; }
        public Type getElementType()  { return sym.getElementType(); }
        public boolean useAccessors() { return sym.useAccessors(); }
        public boolean useGetters()   { return sym.useGetters(); }
        public boolean useSetters()   { return sym.useSetters() || needsMixinInterface(); }

        // Return var name.
        public Name getName() { return name; }

        // Return var name as string.
        public String getNameString() { return name.toString(); }

        // Return modifier flags from the symbol.
        public long getFlags() { return sym.flags(); }

        // Return true if the var is a bare bones synthesize var (bind temp.)
        public boolean isBareSynth() {
            return (getFlags() & JavafxFlags.VARMARK_BARE_SYNTH) != 0;
        }
        
        // Returns true if the var is read only.
        public boolean isReadOnly() {
            return isDef() || (hasBoundDefinition() && !hasBiDiBoundDefinition());
        }
        
        // Returns true if the var needs to generate mixin interfaces (getMixin$, setMixin$ and getVOFF$)
        public boolean needsMixinInterface() {
            return needsMixinInterface;
        }
        
        // Indicate if the var needs a mixin interface.
        public void setNeedsMixinInterface(boolean needs) {
            needsMixinInterface = needs;
        }
        
        // Return true if the var/override has an initializing expression
        public boolean hasInitializer() { return false; }

        // true if this variable is initialized with a "safe" expression -
        // so that we don't need a try..catch error handler wrapper in it's
        // getter method.
        public boolean hasSafeInitializer() { return false; }

        // Return true if the var has a VarInit
        public boolean hasVarInit() { return false; }

        // is this initialzed with a bound function result var?
        public boolean isInitWithBoundFuncResult() { return false; }

        // Is this variable is initialized by another synthetic variable
        // of Pointer type and that var stores result from a bound function call?
        // If so, return the symbol of the synthetic variable.
        public Symbol boundFuncResultInitSym() { return null; }

        // Return true if the var has a bound definition.
        public boolean hasBoundDefinition() { return false; }
        
        // Return true if the var has a bidirectional bind.
        public boolean hasBiDiBoundDefinition() { return false; }
        
        // Return true if the var is an inline bind.
        public boolean isInlinedBind() { return hasBoundDefinition(); }

        // Generally means that the var needs to be included in the current method.
        public boolean needsCloning() { return false; }
        
        // Return true if the var is an override.
        public boolean isOverride() { return false; }

        // A proxy var serves several roles, but generally means that the proxy's
        // qualified name should be used in place of the current var's qualified name.
        public VarInfo proxyVar() { return null; }
        public boolean hasProxyVar() { return proxyVar() != null; }

        // An override is non-null when a mixin var is overridden in the mixee.
        public VarInfo overrideVar() { return null; }
        public boolean hasOverrideVar() { return overrideVar() != null; }

        // Convenience method to return the current symbol to be used for qualified name.
        public JavafxVarSymbol proxyVarSym() { return hasProxyVar() ? proxyVar().sym : sym; }
        
        // Predicate for static var test.
        public boolean isStatic() { return sym.isStatic(); }

        // Predicate for synthetic var test.
        public boolean isSynthetic() { return (getFlags() & Flags.SYNTHETIC) != 0L; }
        
        // Predicate for protexted var test.
        public boolean isProtectedAccess() { return (getFlags() & Flags.PROTECTED) != 0L; }
        
        // Predicate for public var test.
        public boolean isPublicAccess() { return (getFlags() & Flags.PUBLIC) != 0L; }
        
        // Predicate for public read var test.
        public boolean isPublicReadAccess() { return (getFlags() & JavafxFlags.PUBLIC_READ) != 0L; }
        
        // Predicate for public init var test.
        public boolean isPublicInitAccess() { return (getFlags() & JavafxFlags.PUBLIC_INIT) != 0L; }
        
        // Predicate for is externally seen test.
        public boolean isExternallySeen() { return sym.isExternallySeen(); }
        
        // Predicate for script private var test.
        public boolean hasScriptOnlyAccess() { return sym.hasScriptOnlyAccess(); }

        // Predicate for def (constant) var.
        public boolean isDef() { return sym.isDef(); }

        // Predicate for parameter var.
        public boolean isParameter() { return sym.isParameter(); }

        // Predicate for self-reference in init.
        public boolean hasSelfReference() { return sym.hasSelfReference(); }

        // Predicate whether the var came from a mixin.
        public boolean isMixinVar() { return isMixinClass(sym.owner); }

        // Predicate whether the var came from the current javafx class.
        public boolean isDirectOwner() { return false; }

        // Predicate to test for sequence.
        public boolean isSequence() {
            return sym.isSequence();
        }
        // Returns null or the code for var initialization.
        public JCExpression getDefaultInitExpression() { return initExpr; }

        // Class local enumeration accessors.
        public int  getEnumeration()                { return enumeration; }
        public void setEnumeration(int enumeration) { this.enumeration = enumeration; }
        public boolean hasEnumeration()             { return enumeration != -1; }
        public boolean needsEnumeration() {
            return !isOverride() &&
                   needsCloning() &&
                   sym.needsEnumeration();
        }

        // null or javafx tree for the var's 'on replace'.
        public JFXOnReplace onReplace() { return null; }

        // null or Java tree for var's on-replace for use in a setter.
        public JCStatement onReplaceAsInline() { return null; }

        // null or Java tree for var's on-replace for use in change listeber.
        public JCStatement onReplaceAsListenerInstanciation() { return null; }

        // null or javafx tree for the var's 'on invalidate'.
        public JFXOnReplace onInvalidate() { return null; }

        // null or Java tree for var's on-invalidate for use in var$invalidate method.
        public JCStatement onInvalidateAsInline() { return null; }

        // Is there a getter expression of bound variable
        public boolean hasBoundInit() { return false; }

        // Null or Java code for getter expression of bound variable
        public JCExpression boundInit() { return null; }

        // Empty or Java preface code for getter of bound variable
        public List<JCStatement> boundPreface() { return List.<JCStatement>nil(); }

        // Empty or Java preface code for setting of bound with inverse variable
        public List<JCStatement> boundInvSetterPreface() { return List.<JCStatement>nil(); }

        // Empty or variable symbols on which this variable depends
        public List<JavafxVarSymbol> boundBindees() { return List.<JavafxVarSymbol>nil(); }
        
        // Bound variable symbols on which this variable is used.
        public HashSet<VarInfo> boundBinders() { return binders; }
        
        // Bound sequences that need to be invalidated when invalidated.
        public List<BindeeInvalidator> boundInvalidatees() { return boundInvalidatees.toList(); }

        // Empty or bound select pairs.
        public List<DependentPair> boundBoundSelects() { return List.<DependentPair>nil(); }

        // Predicate for generating sequence style accessors -- if bound, must be virtual
        public boolean generateSequenceAccessors() { return isSequence() &&
                                    (hasBoundDefinition() == isBoundVirtualSequence()); }

        // Predicate for bound sequence represented as virtual sequence
        public boolean isBoundVirtualSequence() { return false; }

        // Null or Java code for getting the element of a bound sequence
        public JCStatement boundElementGetter() { return null; }

        // Null or Java code for getting the size of a bound sequence
        public JCStatement boundSizeGetter() { return null; }
        
        // Null or Java code for invalidation of a bound sequence
        public List<BindeeInvalidator> boundInvalidators() { return List.<BindeeInvalidator>nil(); }
        
        // Return true if the var has dependents.
        public boolean hasDependents() {
            return (getFlags() & (JavafxFlags.VARUSE_BIND_ACCESS | JavafxFlags.VARUSE_VARREF)) != 0 || !boundBinders().isEmpty();
        }
        
        // Return true if the var is dependent.
        public boolean isDependent() {
            return (getFlags() & (JavafxFlags.VARUSE_BOUND_INIT | JavafxFlags.VARUSE_VARREF)) != 0 || hasBiDiBoundDefinition() ||
                   !boundBindees().isEmpty() || !boundBoundSelects().isEmpty();
        }

        @Override
        public String toString() { return getNameString(); }

        // Useful diagnostic tool.
        public void printInfo() {
            printInfo(true);
        }
        public void printInfo(boolean detail) {
            System.err.println("    " + getEnumeration() + ". " +
                               getSymbol() +
                               ", type=" + getSymbol().type +
                               ", owner=" + getSymbol().owner +
                               (isStatic() ? ", static" : "") +
                               (isSynthetic() ? ", synthetic" : "") +
                               (useAccessors() ? ", useAccessors" : "") +
                               (needsCloning() ? ", clone" : "") +
                               (isDef() ? ", isDef" : "") +
                               (!boundBindees().isEmpty() ? ", intra binds" : "") + 
                               (!boundBoundSelects().isEmpty() ? ", inter binds" : "") + 
                               (binders != null ?  ", binders" : "") + 
                               (!boundInvalidatees.isEmpty() ?  ", invalidators" : "") + 
                               (getDefaultInitExpression() != null ? ", init" : "") +
                               (isBareSynth() ? ", bare" : "") +
                               (needsMixinInterface() ? ", needsMixinInterface" : "") +
                               ", class=" + getClass().getSimpleName());
            if (detail) {
                long flags = getFlags();
                System.err.println("    " +
                                   (((flags & JavafxFlags.VARUSE_HAS_TRIGGER) != 0)       ? ", VARUSE_HAS_TRIGGER" : "") +
                                   (((flags & JavafxFlags.VARUSE_BOUND_INIT) != 0)        ? ", VARUSE_BOUND_INIT" : "") +
                                   (((flags & JavafxFlags.VARUSE_ASSIGNED_TO) != 0)       ? ", VARUSE_ASSIGNED_TO" : "") +
                                   (((flags & JavafxFlags.VARUSE_OBJ_LIT_INIT) != 0)      ? ", VARUSE_OBJ_LIT_INIT" : "") +
                                   (((flags & JavafxFlags.VARUSE_FORWARD_REFERENCE) != 0) ? ", VARUSE_FORWARD_REFERENCE" : "") +
                                   (((flags & JavafxFlags.VARUSE_SELF_REFERENCE) != 0)    ? ", VARUSE_SELF_REFERENCE" : "") +
                                   (((flags & JavafxFlags.VARUSE_OPT_TRIGGER) != 0)       ? ", VARUSE_OPT_TRIGGER" : "") +
                                   (((flags & JavafxFlags.VARUSE_TMP_IN_INIT_EXPR) != 0)  ? ", VARUSE_TMP_IN_INIT_EXPR" : "") +
                                   (((flags & JavafxFlags.VARUSE_NEED_ACCESSOR) != 0)     ? ", VARUSE_NEED_ACCESSOR" : "") +
                                   (((flags & JavafxFlags.VARUSE_NON_LITERAL) != 0)       ? ", VARUSE_NON_LITERAL" : "") +
                                   (((flags & JavafxFlags.VARUSE_BIND_ACCESS) != 0)       ? ", VARUSE_BIND_ACCESS" : "") +
                                   (((flags & JavafxFlags.VARUSE_VARREF) != 0)            ? ", VARUSE_VARREF" : "") +
                                   (((flags & JavafxFlags.VARUSE_SPECIAL) != 0)           ? ", VARUSE_SPECIAL" : "") +
                                   (getSymbol().isExternallySeen()                         ? ", EXTERNALLY SEEN" : ""));
                if (!boundBoundSelects().isEmpty()) {
                    for (DependentPair pair : boundBoundSelects()) {
                        System.err.println("        select=" + pair.instanceSym + " " + pair.referencedSym);
                    }
                }
                if (!boundBindees().isEmpty()) {
                    for (JavafxVarSymbol bindeeSym : boundBindees()) {
                        System.err.println("        bindee=" + bindeeSym);
                    }
                }
                if (hasProxyVar()) {
                    System.err.print("        proxy=");
                    proxyVar().printInfo(false);
                }
                
                if (hasOverrideVar()) {
                    System.err.print("        override=");
                    overrideVar().printInfo(false);
                }
                
                if (boundElementGetter() != null) {
                    System.err.print("        element getter=");
                    System.err.println(boundElementGetter());
                }
                
                if (boundSizeGetter() != null) {
                    System.err.print("        size getter=");
                    System.err.println(boundSizeGetter());
                }
                
                if (boundInvalidators().size() != 0) {
                    System.err.println("        invalidators=");
                    for (BindeeInvalidator bi : boundInvalidators()) {
                        System.err.println("          " + bi.bindee + " " + bi.invalidator);
                    }
                }
            }
        }
    }

    //
    // This base class is used for vars declared in the current javafx class..
    //
    static abstract class TranslatedVarInfoBase extends VarInfo {

        // Null or javafx code for the var's on replace.
        private final JFXOnReplace onReplace;

        // Null or javafx code for the var's on invalidate.
        private final JFXOnReplace onInvalidate;

        // The bind status for the var/override
        private final JavafxBindStatus bindStatus;

        // The does this var have an initializing expression
        private final boolean hasInitializer;

        // Null or java code for the var's on replace inlined in setter.
        private final JCStatement onReplaceAsInline;

        // Null or java code for the var's on invalidate inlined in var$invalidate method.
        private final JCStatement onInvalidateAsInline;

        // Result of bind translation
        private final ExpressionResult bindOrNull;

        TranslatedVarInfoBase(DiagnosticPosition diagPos, Name name, JavafxVarSymbol attrSym, JavafxBindStatus bindStatus, boolean hasInitializer, 
                JCExpression initExpr, ExpressionResult bindOrNull,
                JFXOnReplace onReplace, JCStatement onReplaceAsInline,
                JFXOnReplace onInvalidate, JCStatement onInvalidateAsInline) {
            super(diagPos, name, attrSym, initExpr);
            this.hasInitializer = hasInitializer;
            this.bindStatus = bindStatus;
            this.bindOrNull = bindOrNull;
            this.onReplace = onReplace;
            this.onReplaceAsInline = onReplaceAsInline;
            this.onInvalidate = onInvalidate;
            this.onInvalidateAsInline = onInvalidateAsInline;
        }

        // Return true if the var/override has an initializing expression
        @Override
        public boolean hasInitializer() { return hasInitializer; }

        // Return true if the var has a bound definition.
        @Override
        public boolean hasBoundDefinition() { return bindStatus.isBound(); }

        // Return true if the var has a bidirectional bind.
        @Override
        public boolean hasBiDiBoundDefinition() { return bindStatus.isBidiBind(); }

        // Is there a getter expression of bound variable
        @Override
        public boolean hasBoundInit() { return bindOrNull==null? false : bindOrNull.hasExpr(); }

        // Null or Java code for getter expression of bound variable
        @Override
        public JCExpression boundInit() { return bindOrNull==null? null : bindOrNull.expr(); }

        // Null or Java preface code for getter of bound variable
        @Override
        public List<JCStatement> boundPreface() { return bindOrNull==null? List.<JCStatement>nil() : bindOrNull.statements(); }

        // Empty or Java preface code for setting of bound with inverse variable
        @Override
        public List<JCStatement> boundInvSetterPreface() { return bindOrNull==null? List.<JCStatement>nil() : bindOrNull.setterPreface(); }

        // Variable symbols on which this variable depends
        @Override
        public List<JavafxVarSymbol> boundBindees() { return bindOrNull==null? List.<JavafxVarSymbol>nil() : bindOrNull.bindees(); }

        // Empty or bound select pairs.
        @Override
        public List<DependentPair> boundBoundSelects() { return bindOrNull == null? List.<DependentPair>nil() : bindOrNull.interClass(); }

        // Return true if this is a bound sequence represented as virtual sequence
        @Override
        public boolean isBoundVirtualSequence() { return bindOrNull==null? false : bindOrNull.isBoundVirtualSequence(); }

        // Null or Java code for getting the element of a bound sequence
        @Override
        public JCStatement boundElementGetter() { return !generateSequenceAccessors() || bindOrNull == null ? null : bindOrNull.getElementMethodBody(); }

        // Null or Java code for getting the size of a bound sequence
        @Override
        public JCStatement boundSizeGetter() { return !generateSequenceAccessors() || bindOrNull == null ? null : bindOrNull.getSizeMethodBody(); }
        
        // Null or Java code for invalidation of a bound sequence
        @Override
        public List<BindeeInvalidator> boundInvalidators() { return bindOrNull == null ? List.<BindeeInvalidator>nil() : bindOrNull.invalidators(); }

        // Possible javafx code for the var's 'on replace'.
        @Override
        public JFXOnReplace onReplace() { return onReplace; }

        // Possible java code for the var's 'on replace' in setter.
        @Override
        public JCStatement onReplaceAsInline() { return onReplaceAsInline; }

        // Possible javafx code for the var's 'on invalidate'.
        @Override
        public JFXOnReplace onInvalidate() { return onInvalidate; }

        // Possible java code for the var's 'on invalidate' in var$invalidate method.
        @Override
        public JCStatement onInvalidateAsInline() { return onInvalidateAsInline; }

        // This var is in the current javafx class so it has to be cloned into the java class.
        @Override
        public boolean needsCloning() { return true; }

        // Has to be the current javafx class.
        @Override
        public boolean isDirectOwner() { return true; }
    }

    //
    // This class is used for basic vars declared in the current javafx class.
    //
    static class TranslatedVarInfo extends TranslatedVarInfoBase {
        // Tree for the javafx var.
        private final JFXVar var;
        private final Symbol boundFuncResultInitSym;

        TranslatedVarInfo(JFXVar var, 
                JCExpression initExpr, Symbol boundFuncResultInitSym,
                ExpressionResult bindOrNull, 
                JFXOnReplace onReplace, JCStatement onReplaceAsInline,
                JFXOnReplace onInvalidate, JCStatement onInvalidateAsInline) {
            super(var.pos(), var.sym.name, var.sym, var.getBindStatus(), var.getInitializer()!=null,
                  initExpr, bindOrNull,
                  onReplace, onReplaceAsInline, onInvalidate, onInvalidateAsInline);
            this.var = var;
            this.boundFuncResultInitSym = boundFuncResultInitSym;
        }

        // Returns the tree for the javafx var.
        public JFXVar jfxVar() { return var; }

        @Override
        public boolean hasSafeInitializer() {
            // If unexaminable code can be executed (function/method call or init block) or
            // a division can happen then it is not exception safe.
            if (hasInitializer()) {
                class ExceptionThrowingScanner extends JavafxTreeScanner {

                    boolean safe = true;

                    private void markCanThrowException() {
                        safe = false;
                    }

                    @Override
                    public void visitBinary(JFXBinary tree) {
                        switch (tree.getFXTag()) {
                            case DIV:
                            case MOD:
                                markCanThrowException();
                                break;
                            default:
                                super.visitBinary(tree);
                                break;
                        }
                    }

                    @Override
                    public void visitAssignop(JFXAssignOp tree) {
                        switch (tree.getFXTag()) {
                            case DIV_ASG:
                                markCanThrowException();
                                break;
                            default:
                                super.visitAssignop(tree);
                                break;
                        }
                    }

                    @Override
                    public void visitInstanciate(JFXInstanciate tree) {
                        markCanThrowException();
                    }

                    @Override
                    public void visitFunctionInvocation(JFXFunctionInvocation tree) {
                        markCanThrowException();
                    }
                }
                ExceptionThrowingScanner scanner = new ExceptionThrowingScanner();
                scanner.scan(var.getInitializer());
                return scanner.safe;
            } else {
                return false;
            }
        }
        
        // Return true if the var has a VarInit
        @Override
        public boolean hasVarInit() { return var.getVarInit() != null; }

        @Override
        public boolean isInitWithBoundFuncResult() {
            return boundFuncResultInitSym != null;
        }

        @Override
        public Symbol boundFuncResultInitSym() {
            return boundFuncResultInitSym;
        }
    }

    //
    // This class represents a var override declared in the current javafx class.
    //
    static class TranslatedOverrideClassVarInfo extends TranslatedVarInfoBase {
        // Reference to the var information the override overshadows.
        private VarInfo proxyVar;
        private final Symbol boundFuncResultInitSym;


        TranslatedOverrideClassVarInfo(JFXOverrideClassVar override,
                JCExpression initExpr, Symbol boundFuncResultInitSym,
                ExpressionResult bindOrNull,
                JFXOnReplace onReplace, JCStatement onReplaceAsInline,
                JFXOnReplace onInvalidate, JCStatement onInvalidateAsInline) {
            super(override.pos(), override.sym.name, override.sym, override.getBindStatus(), override.getInitializer() != null, 
                    initExpr, bindOrNull,
                  onReplace, onReplaceAsInline, onInvalidate, onInvalidateAsInline);
            this.boundFuncResultInitSym = boundFuncResultInitSym;
        }
        
        // Return true if the var is an override.
        @Override
        public boolean isOverride() { return true; }

        // Returns the var information the override overshadows.
        @Override
        public VarInfo proxyVar() { return proxyVar; }

        // Setter for the proxy var information.
        public void setProxyVar(VarInfo proxyVar) { this.proxyVar = proxyVar; }
        
        // Returns true is this var overrides a mixin.
        public boolean overridesMixin() {
            return proxyVar != null && proxyVar instanceof MixinClassVarInfo;
        }

        @Override
        public boolean isInitWithBoundFuncResult() {
            return boundFuncResultInitSym != null;
        }

        @Override
        public Symbol boundFuncResultInitSym() {
            return boundFuncResultInitSym;
        }
    }

    //
    // This class represents a var that is declared in a superclass.  This var may be
    // declared in the same compile unit or read in from a .class file.
    //
    static class SuperClassVarInfo extends VarInfo {
        SuperClassVarInfo(DiagnosticPosition diagPos, JavafxVarSymbol var) {
            super(diagPos, var.name, var, null);
        }

        // Superclass vars are never cloned.
        @Override
        public boolean needsCloning() { return false; }
    }

    //
    // This class represents a var that is declared in a mixin superclass.  This var may be
    // declared in the same compile unit or read in from a .class file.
    //
    static class MixinClassVarInfo extends VarInfo {
        // Accessors for mixin var.
        ListBuffer<FuncInfo> accessors;
        
        // Override from mixee.
        private VarInfo overrideVar;
        
        MixinClassVarInfo(DiagnosticPosition diagPos, JavafxVarSymbol var) {
            super(diagPos, var.name, var, null);
            this.accessors = ListBuffer.lb();
            this.overrideVar = null;
        }

        // Returns true if the var needs to generate mixin interfaces (getMixin$, setMixin$ and getVOFF$)
        @Override
        public boolean needsMixinInterface() {
            return true;
        }

        // Return true if the var/override has an initializing expression
        @Override
        public boolean hasInitializer() {
            return hasOverrideVar() && overrideVar().hasInitializer();
        }

        // true if this variable is initialized with a "safe" expression -
        // so that we don't need a try..catch error handler wrapper in it's
        // getter method.
        @Override
        public boolean hasSafeInitializer() {
             return hasOverrideVar() && overrideVar().hasSafeInitializer();
        }

        // Return true if the var has a VarInit
        @Override
        public boolean hasVarInit() {
             return hasOverrideVar() && overrideVar().hasVarInit();
        }

        // is this initialzed with a bound function result var?
        @Override
        public boolean isInitWithBoundFuncResult() {
             return hasOverrideVar() && overrideVar().isInitWithBoundFuncResult();
        }

        // Is this variable is initialized by another synthetic variable
        // of Pointer type and that var stores result from a bound function call?
        // If so, return the symbol of the synthetic variable.
        @Override
        public Symbol boundFuncResultInitSym() {
            return hasOverrideVar() ? overrideVar().boundFuncResultInitSym() : super.boundFuncResultInitSym();
        }

        // Returns null or the code for var initialization.
        @Override
        public JCExpression getDefaultInitExpression() {
            if (hasOverrideVar() && overrideVar().initExpr != null) {
                return overrideVar().initExpr;
            }
            
            return initExpr;
        }

        // Return true if the var has a bound definition.
        @Override
        public boolean hasBoundDefinition() {
            return hasOverrideVar() && overrideVar().hasBoundDefinition();
        }

        // Return true if the var has a bidirectional bind.
        @Override
        public boolean hasBiDiBoundDefinition()  {
            return hasOverrideVar() && overrideVar().hasBiDiBoundDefinition();
        }
        
        // Is there a getter expression of bound variable
        @Override
        public boolean hasBoundInit() {
            return hasOverrideVar() ? overrideVar().hasBoundInit() : super.hasBoundInit();
        }

        // Null or Java code for getter expression of bound variable
        @Override
        public JCExpression boundInit() {
            return hasOverrideVar() ? overrideVar().boundInit() : super.boundInit();
        }

        // Empty or Java preface code for getter of bound variable
        @Override
        public List<JCStatement> boundPreface() {
            return hasOverrideVar() ? overrideVar().boundPreface() : super.boundPreface();
        }

        // Empty or Java preface code for setting of bound with inverse variable
        @Override
        public List<JCStatement> boundInvSetterPreface() {
            return hasOverrideVar() ? overrideVar().boundInvSetterPreface() : super.boundInvSetterPreface();
        }

        // Variable symbols on which this variable depends
        @Override
        public List<JavafxVarSymbol> boundBindees() {
            ListBuffer<JavafxVarSymbol> bindees = ListBuffer.lb();
            bindees.appendList(super.boundBindees());
            
            if (hasOverrideVar()) {
                bindees.appendList(overrideVar().boundBindees());
            }

            return bindees.toList();
        }

        // Bound variable symbols on which this variable is used.
        @Override
        public HashSet<VarInfo> boundBinders() {
            return hasOverrideVar() ? overrideVar().boundBinders() : super.boundBinders();
        }

        // Bound sequences that need to be invalidated when invalidated.
        @Override
        public List<BindeeInvalidator> boundInvalidatees() {
            return hasOverrideVar() ? overrideVar().boundInvalidatees() : super.boundInvalidatees();
        }

        // Empty or bound select pairs.
        @Override
        public List<DependentPair> boundBoundSelects() {
            return hasOverrideVar() ? overrideVar().boundBoundSelects() : super.boundBoundSelects();
        }

        // Return true if this is a bound sequence represented as virtual sequence
        @Override
        public boolean isBoundVirtualSequence() {
            return hasOverrideVar() ? overrideVar().isBoundVirtualSequence() : super.isBoundVirtualSequence();
        }

        // Null or Java code for getting the element of a bound sequence
        @Override
        public JCStatement boundElementGetter() {
            return hasOverrideVar() ? overrideVar().boundElementGetter() : super.boundElementGetter();
        }

        // Null or Java code for getting the size of a bound sequence
        @Override
        public JCStatement boundSizeGetter() {
            return hasOverrideVar() ? overrideVar().boundSizeGetter() : super.boundSizeGetter();
        }
        
        // Null or Java code for invalidation of a bound sequence
        @Override
        public List<BindeeInvalidator> boundInvalidators() {
            return hasOverrideVar() ? overrideVar().boundInvalidators() : super.boundInvalidators();
        }

        // Possible javafx code for the var's 'on replace'.
        @Override
        public JFXOnReplace onReplace() {
            return hasOverrideVar() ? overrideVar().onReplace() : super.onReplace();
        }

        // Possible java code for the var's 'on replace' in setter.
        @Override
        public JCStatement onReplaceAsInline() {
            return hasOverrideVar() ? overrideVar().onReplaceAsInline() : super.onReplaceAsInline();
        }

        // Possible javafx code for the var's 'on invalidate'.
        @Override
        public JFXOnReplace onInvalidate() {
            return hasOverrideVar() ? overrideVar().onInvalidate() : super.onInvalidate();
        }

        // Possible java code for the var's 'on invalidate' in var$invalidate method.
        @Override
        public JCStatement onInvalidateAsInline() {
            return hasOverrideVar() ? overrideVar().onInvalidateAsInline() : super.onInvalidateAsInline();
        }

        // Mixin vars are always cloned.
        @Override
        public boolean needsCloning() { return true; }
        
        // Fetch the override.
        @Override
        public VarInfo overrideVar() { return overrideVar; }
        
        // Fetch the override.
        public void setOverride(VarInfo override) { overrideVar = override; }
        
        // Add an accessor function.
        public void addAccessor(FuncInfo accessor) {
            accessors.append(accessor);
        }
        
        // Add an accessor function.
        public List<FuncInfo> getAccessors() {
            return accessors.toList();
        }
    }

    //
    // Set up the analysis.
    //
    JavafxAnalyzeClass(
            JavafxInitializationBuilder initBuilder,
            DiagnosticPosition diagPos,
            ClassSymbol currentClassSym,
            List<TranslatedVarInfo> translatedAttrInfo,
            List<TranslatedOverrideClassVarInfo> translatedOverrideAttrInfo,
            List<TranslatedFuncInfo> translatedFuncInfo,
            Name.Table names,
            JavafxTypes types,
            JavafxDefs defs,
            JavafxSymtab syms,
            JavafxClassReader reader) {
        this.initBuilder = initBuilder;
        this.names = names;
        this.types = types;
        this.defs = defs;
        this.syms = syms;
        this.reader = reader;
        this.diagPos = diagPos;
        this.currentClassDecl = types.getFxClass(currentClassSym);
        this.currentClassSym = currentClassSym;
        this.translatedAttrInfo = translatedAttrInfo;
        this.translatedOverrideAttrInfo = translatedOverrideAttrInfo;
        this.translatedFuncInfo = translatedFuncInfo;
        this.classVarCount = 0;
        this.scriptVarCount = 0;
        
        // Start by analyzing the current class.
        analyzeCurrentClass();

        // Assign var enumeration and binders.
        for (VarInfo ai : classVarInfos) {
            if (ai.needsEnumeration()) {
                ai.setEnumeration(classVarCount++);
            }
           
            addBinders(ai);
        }
        for (VarInfo ai : scriptVarInfos) {
           if (ai.needsEnumeration()) {
               ai.setEnumeration(scriptVarCount++);
           }
           
           addBinders(ai);
        }

        if (initBuilder.options.get("dumpvarinfo") != null) {
            printAnalysis(false);
        }
    }
    
    private void addInterClassBinder(VarInfo varInfo, JavafxVarSymbol instanceSymbol, JavafxVarSymbol referenceSymbol) {
        // Get the correct update map.
        HashMap<JavafxVarSymbol, HashMap<JavafxVarSymbol, HashSet<VarInfo>>> updateMap =
            varInfo.isStatic() ? scriptUpdateMap : classUpdateMap;
        
        // Get instance level map.
        HashMap<JavafxVarSymbol, HashSet<VarInfo>> instanceMap = updateMap.get(instanceSymbol);
        
        // Add new entry if not found.
        if (instanceMap == null) {
            instanceMap = new LinkedHashMap<JavafxVarSymbol, HashSet<VarInfo>>();
            updateMap.put(instanceSymbol, instanceMap);
        }
        
        // Get reference level map.
        HashSet<VarInfo> referenceSet = instanceMap.get(referenceSymbol);
        
        // Add new entry if not found.
        if (referenceSet == null) {
            referenceSet = new LinkedHashSet<VarInfo>();
            instanceMap.put(referenceSymbol, referenceSet);
        }
        
        // Add symbol to set.
        referenceSet.add(varInfo);
    }
    
    private void addBinders(VarInfo ai) {
        // Add any bindees to binders.
        for (JavafxVarSymbol bindeeSym : ai.boundBindees()) {
            // Find the varInfo
            VarInfo bindee = visitedAttributes.get(initBuilder.attributeValueName(bindeeSym));
            
            if (bindee != null) {
                bindee.binders.add((VarInfo)ai);
            }
        }
            
        // Add any bind select pairs to update map.
        for (DependentPair pair : ai.boundBoundSelects()) {
            addInterClassBinder(ai, pair.instanceSym, (JavafxVarSymbol)pair.referencedSym);
        }
        
        // If the ai has invalidators.
        for (BindeeInvalidator invalidator: ai.boundInvalidators()) {
            // Find the varInfo
            VarInfo bindee = visitedAttributes.get(initBuilder.attributeValueName(invalidator.bindee));
            
            if (bindee != null && invalidator.invalidator != null) {
               bindee.boundInvalidatees.append(invalidator);
            }
        }
    }
    
    //
    // This class supers all classes used to hold function information. Consumed by
    // JavafxInitializationBuilder.
    //
    static abstract class FuncInfo {
        // Position of the function declaration or current javafx class if read from superclass.
        private final DiagnosticPosition diagPos;

        // Function symbol (unique to symbol.)
        private final MethodSymbol funcSym;
        
        FuncInfo(DiagnosticPosition diagPos, MethodSymbol funcSym) {
            this.diagPos = diagPos;
            this.funcSym = funcSym;
        }

        // Return the function position.
        public DiagnosticPosition pos() { return diagPos; }

        // Return the function symbol.
        public MethodSymbol getSymbol() { return funcSym; }

        // Return modifier flags from the symbol.
        public long getFlags() { return funcSym.flags(); }

        // Predicate for static func test.
        public boolean isStatic() { return (getFlags() & Flags.STATIC) != 0; }
        
        // Useful diagnostic tool.
        public void printInfo() {
            System.err.println("    " + getSymbol() +
                               (isStatic() ? ", static" : ""));
        }
    }
    
    //
    // This class is used for basic functions declared in the current javafx class.
    //
    static class TranslatedFuncInfo extends FuncInfo {
        // Javafx definition of the function.
        private final JFXFunctionDefinition jfxFuncDef;
        
        // Java translation of the function.
        private final List<JCTree> jcFuncDef;
        
        TranslatedFuncInfo(JFXFunctionDefinition jfxFuncDef, List<JCTree> jcFuncDef) {
            super(jfxFuncDef, jfxFuncDef.sym);
            this.jfxFuncDef = jfxFuncDef;
            this.jcFuncDef = jcFuncDef;
        }

        // Return the javafx definition of the function.
        public JFXFunctionDefinition jfxFunction() { return jfxFuncDef; }

        // Return the java translation of the function.
        public List<JCTree> jcFunction() { return jcFuncDef; }

    }
    
    //
    // This class represents a function that is declared in a superclass. 
    // This function may be declared in the same compile unit or read in from a .class file.
    //
    static class SuperClassFuncInfo extends FuncInfo {
        SuperClassFuncInfo(MethodSymbol funcSym) {
            super(null, funcSym);
        }
    }
    
    //
    // This class represents a function that is declared in a mixin superclass.  This function may be
    // This function may be declared in the same compile unit or read in from a .class file.
    //
    static class MixinFuncInfo extends FuncInfo {
        MixinFuncInfo(MethodSymbol funcSym) {
            super(null, funcSym);
        }
    }

    //
    // Returns the current class position.
    //
    public DiagnosticPosition getCurrentClassPos() { return diagPos; }

    //
    // Returns the current class decl.
    //
    public JFXClassDeclaration getCurrentClassDecl() { return currentClassDecl; }

    //
    // Returns the current class symbol.
    //
    public ClassSymbol getCurrentClassSymbol() { return currentClassSym; }

    //
    // Returns true if specified symbol is the current class symbol.
    //
    public boolean isCurrentClassSymbol(Symbol sym) { return sym == currentClassSym; }

    //
    // Returns true if specified class is the FXBase class.
    //
    public boolean isFXBase(Symbol sym) { return sym == syms.javafx_FXBaseType.tsym; }

    //
    // Returns true if specified class is the FXObject class.
    //
    public boolean isFXObject(Symbol sym) { return sym == syms.javafx_FXObjectType.tsym; }

    //
    // Returns true if specified class is either the FXBase or the FXObject class.
    //
    public boolean isRootClass(Symbol sym) { return isFXBase(sym) || isFXObject(sym); }

    //
    // Returns true if the current class inherits directly from FXBase.
    //
    public boolean isFirstTier() { return superClassSym !=  null && isFXBase(superClassSym); }

    //
    // Returns true if the current class inherits directly from FXBase and has no mixins.
    //
    public boolean isFirstTierNoMixins() { return isFirstTier() && allMixins.isEmpty(); }

    //
    // Returns the var count for the current class.
    //
    public int getClassVarCount() { return classVarCount; }

    //
    // Returns the var count for the current script.
    //
    public int getScriptVarCount() { return scriptVarCount; }

    //
    // Returns the translatedAttrInfo for the current class.
    //
    public List<TranslatedVarInfo> getTranslatedAttrInfo() { return translatedAttrInfo; }

    //
    // Returns the translatedOverrideAttrInfo for the current class.
    //
    public List<TranslatedOverrideClassVarInfo> getTranslatedOverrideAttrInfo() {
        return translatedOverrideAttrInfo;
    }

    //
    // Returns the translatedFuncInfo for the current class.
    //
    public List<TranslatedFuncInfo> getTranslatedFuncVarInfo() {
        return translatedFuncInfo;
    }

    //
    // Returns the resulting list of class vars.
    //
    public List<VarInfo> classVarInfos() {
        return classVarInfos.toList();
    }

    //
    // Returns the resulting list of script vars.
    //
    public List<VarInfo> scriptVarInfos() {
        return scriptVarInfos.toList();
    }

    //
    // Returns the resulting list of class funcs.
    //
    public List<FuncInfo> classFuncInfos() {
        return classFuncInfos.toList();
    }

    //
    // Returns the resulting list of script func.
    //
    public List<FuncInfo> scriptFuncInfos() {
        return scriptFuncInfos.toList();
    }
    
    //
    // Returns the map used to construct the class update$ method.
    //
    public final HashMap<JavafxVarSymbol, HashMap<JavafxVarSymbol, HashSet<VarInfo>>> getClassUpdateMap() {
        return classUpdateMap;
    }
    
    //
    // Returns the map used to construct the script update$ method.
    //
    public final HashMap<JavafxVarSymbol, HashMap<JavafxVarSymbol, HashSet<VarInfo>>> getScriptUpdateMap() {
        return scriptUpdateMap;
    }

    //
    // Returns the resulting list of methods needing $impl dispatching.
    //
    public List<MethodSymbol> needDispatch() {
        ListBuffer<MethodSymbol> meths = ListBuffer.lb();
        
        // Never add dispatch methods to mixins.
        if (!isMixinClass()) {
            for (FuncInfo fi : needDispatchMethods.values()) {
                meths.append(fi.getSymbol());
            }
        }

        return meths.toList();
    }

    //
    // Returns true if the type is a valid class worthy of analysis.
    //
    private boolean isValidClass(Type type) {
        return type != null && type.tsym != null && type.tsym.kind == Kinds.TYP;
    }

    //
    // Returns true if the class (or current class) is a mixin.
    //
    public boolean isMixinClass() {
        return isMixinClass(currentClassSym);
    }
    public static boolean isMixinClass(Symbol cSym) {
        return (cSym.flags() & JavafxFlags.MIXIN) != 0;
    }

    //
    // Returns true if the class (or current class) is FINAL.
    //
    public boolean isFinal() {
        return isFinal(currentClassSym);
    }
    public static boolean isFinal(Symbol cSym) {
        return (cSym.flags() & Flags.FINAL) != 0;
    }

    //
    // Returns true if the class is a Interface.
    //
    public boolean isInterface(Symbol cSym) {
        return (cSym.flags() & Flags.INTERFACE) != 0;
    }

    //
    // Returns null or the superclass symbol if it is a javafx class.
    //
    public ClassSymbol getFXSuperClassSym() { return superClassSym; }

    //
    // Returns resulting list of all superclasses in top down order.
    //
    public List<ClassSymbol> getSuperClasses() { return superClasses.toList(); }

    //
    // Returns resulting list of immediate mixin classes in left to right order.
    //
    public List<ClassSymbol> getImmediateMixins() { return immediateMixins.toList(); }

    //
    // Returns resulting list of all mixin classes in top down order.
    //
    public List<ClassSymbol> getAllMixins() { return allMixins.toList(); }
    
    //
    // Add a var to the proper vars list.
    //
    public void addVarToList(VarInfo varInfo) {
        if (varInfo.isStatic()) {
            scriptVarInfos.append(varInfo);
        } else {
            classVarInfos.append(varInfo);
        }
    }
    
    //
    // Check to see if a mixin has an override.
    //
    public void checkMixinOverride(MixinClassVarInfo varInfo) {
        for (TranslatedOverrideClassVarInfo tai : translatedOverrideAttrInfo) {
            if (tai.getSymbol() == varInfo.getSymbol()) {
                varInfo.setOverride(tai);
                tai.setProxyVar(varInfo);
                break;
            }
        }
    }

    //
    // This method analyzes the current javafx class.
    //
    private void analyzeCurrentClass() {
        // Make sure we don't recursively redo this class.
        addedBaseClasses.add(currentClassSym);

        // Process up the super class chain first.
        Type superType = currentClassSym.getSuperclass();

        // Make sure the super is a valid java class (is this always true?)
        if (isValidClass(superType)) {
            // Analyze the super class, but note that we don't want to clone
            // anything in the super chain.
            analyzeClass(superType.tsym, true, false);
        }

        // Track the current vars to the instance attribute results.
        for (TranslatedVarInfo tai : translatedAttrInfo) {
            // Track the var for overrides and mixin duplication.
            visitedAttributes.put(initBuilder.attributeValueName(tai.getSymbol()), tai);
        }

        // Map the current methods so they are filtered out of the results.
        for (TranslatedFuncInfo func : translatedFuncInfo) {
            visitedMethods.put(methodSignature(func.getSymbol()), func);
        }

        // Lastly, insert any mixin vars and methods from the interfaces.
        for (JFXExpression supertype : currentClassDecl.getSupertypes()) {
            // This will technically only analyze mixin classes.
            // We also want to clone all mixin vars amnd methods.
            analyzeClass(supertype.type.tsym, true, true);
        }

        // Track the override vars to the instance attribute results.
        for (TranslatedOverrideClassVarInfo tai : translatedOverrideAttrInfo) {
            // Find the overridden var.
            VarInfo oldVarInfo = visitedAttributes.get(initBuilder.attributeValueName(tai.getSymbol()));

            // Test because it's possible to find the override before the var.
            if (oldVarInfo != null && !(oldVarInfo instanceof MixinClassVarInfo)) {
                // Proxy to the overridden var.
                tai.setProxyVar(oldVarInfo);
                tai.setNeedsMixinInterface(tai.needsMixinInterface() || oldVarInfo.needsMixinInterface());
                oldVarInfo.setNeedsMixinInterface(false);
            }
            
            // Track the var for overrides and mixin duplication.
            visitedAttributes.put(initBuilder.attributeValueName(tai.getSymbol()), tai);
        }

        // Add the current vars to the var results.
        // JFXC-3043 - This needs to be done after mixins.
        for (TranslatedVarInfo tai : translatedAttrInfo) {
            addVarToList(tai);
        }

        // Add the override vars to the var results.
        // JFXC-3043 - This needs to be done after mixins.
        for (TranslatedOverrideClassVarInfo tai : translatedOverrideAttrInfo) {
            if (!tai.overridesMixin()) {
                addVarToList(tai);
            }
        }

        // Add the functions to the function results.
        // JFXC-3043 - This needs to be done after mixins.
        for (TranslatedFuncInfo tfi : translatedFuncInfo) {
            if (tfi.isStatic()) {
                scriptFuncInfos.append(tfi);
            } else {
                classFuncInfos.append(tfi);
            }
        }
    }
    
    private void analyzeClass(Symbol sym, boolean isImmediateSuper, boolean needsCloning) {
        // Ignore pure java interfaces, classes we've visited before and non-javafx classes.
        if (!isInterface(sym) && !addedBaseClasses.contains(sym) && types.isJFXClass(sym)) {
            // Get the current class symbol and add it to the visited map.
            ClassSymbol cSym = (ClassSymbol)sym;
            addedBaseClasses.add(cSym);

            // Only continue cloning up the hierarchy if this is a mixin class.
            boolean isMixinClass = isMixinClass(cSym);
            needsCloning = needsCloning && isMixinClass;
            
            // Process up the super class chain first.
            Type superType = cSym.getSuperclass();
            if (isValidClass(superType)) {
                // Analyze the super class, but note that we don't want to clone
                // anything in the super chain.
                analyzeClass(superType.tsym, false, false);
            }
            // Class loaded from .class file.
            if (cSym.members() != null) {
                // Scope information is held in reverse order of declaration.
                ListBuffer<Symbol> reversed = ListBuffer.lb();
                for (Entry e = cSym.members().elems; e != null && e.sym != null; e = e.sibling) {
                    reversed.prepend(e.sym);
                }
                
                // Track mixin var accessors.
                HashMap<Name, MixinClassVarInfo> mixinVarMap = new LinkedHashMap<Name, MixinClassVarInfo>();

                // Scan attribute members.
                for (Symbol varMem : reversed) {
                    if (varMem instanceof JavafxVarSymbol) {
                        // Attribute member.
                        JavafxVarSymbol var = (JavafxVarSymbol)varMem;
                        
                        // Filter out methods generated by the compiler.
                        if (isRootClass(cSym) || !filterVars(var)) {
                            processAttribute(var, cSym, needsCloning, mixinVarMap);
                        }
                    }
                }

                // Scan attribute/method members.
                for (Symbol methMem : reversed) {
                    if (methMem.kind == Kinds.MTH) {
                        // Method member.
                        MethodSymbol meth = (MethodSymbol)methMem;

                        // Workaround for JFXC-3040 - Compile failure building
                        // runtime/javafx-ui-controls/javafx/scene/control/Button.fx
                        //if (!needsCloning) continue;

                        // Filter out methods generated by the compiler.
                        if (isRootClass(cSym) || !filterMethods(meth)) {
                            processMethod(meth, needsCloning, cSym, mixinVarMap);
                        }
                    }
                }
            }
            
            // Now analyze interfaces.
            for (Type supertype : cSym.getInterfaces()) {
                ClassSymbol iSym = (ClassSymbol) supertype.tsym;
                analyzeClass(iSym, isImmediateSuper && isMixinClass, needsCloning);
            }

            // Record the superclass in top down order.
            recordClass(cSym, isImmediateSuper);
        }
    }

    //
    // Predicate method indicates if the method should be include in processing.
    // Should filter out unrelated methods generated by the compiler.
    //
    private boolean filterMethods(MethodSymbol meth) {
        Name name = meth.name;
        
        // if this is a main method in an FX class then it is synthetic, ignore it
        if (name == defs.main_MethodName) {
            if (meth.type instanceof MethodType) {
                MethodType mt = (MethodType)meth.type;
                List<Type> paramTypes = mt.getParameterTypes();
                if (paramTypes.size() == 1 && paramTypes.head instanceof ArrayType) {
                    Type elemType = ((ArrayType) paramTypes.head).getComponentType();
                    if (elemType.tsym.name == syms.stringType.tsym.name) {
                        return true;
                    }
                }
            }
        }

        // ignore GETMAPxxx methods
        if (name.toString().startsWith(defs.varGetMapString)) {
            return true;
        }
        
        return name == names.init || name == names.clinit ||
               name == defs.internalRunFunctionName || 
               name == defs.applyDefaults_FXObjectMethodName ||
               name == defs.count_FXObjectMethodName ||
               name == defs.get_FXObjectMethodName ||
               name == defs.set_FXObjectMethodName ||
               name == defs.invalidate_FXObjectMethodName ||
               name == defs.notifyDependents_FXObjectMethodName ||
               name == defs.getElement_FXObjectMethodName ||
               name == defs.size_FXObjectMethodName ||
               name == defs.update_FXObjectMethodName ||
               name == defs.complete_FXObjectMethodName ||
               name == defs.initialize_FXObjectMethodName ||
               name == defs.userInit_FXObjectMethodName ||
               name == defs.postInit_FXObjectMethodName ||
               name == defs.initVars_FXObjectMethodName ||
               name == defs.invoke_FXObjectMethodName;
    }

    //
    // Predicate method indicates if the var should be include in processing.
    // Should filter out unrelated methods generated by the compiler.
    //
    private boolean filterVars(JavafxVarSymbol var) {
        Name name = var.name;
        String nameString = name.toString();
        
        return nameString.startsWith(JavafxDefs.varMap_FXObjectFieldPrefix) ||
               nameString.startsWith(JavafxDefs.count_FXObjectFieldString) ||
               nameString.startsWith(JavafxDefs.offset_AttributeFieldPrefix) ||
               nameString.startsWith(JavafxDefs.flags_AttributeFieldPrefix) ||
               nameString.startsWith(JavafxDefs.varFlags_FXObjectFieldPrefix);
    }

    //
    // Record the superclasses and mixins in top down order.
    //
    private void recordClass(ClassSymbol cSym, boolean isImmediateSuper) {
        // Make a distinction between superclasses and mixins.
        if (isMixinClass(cSym)) {
            // Record immediate mixin classes in left to right order.
            if (isImmediateSuper) {
                immediateMixins.append(cSym);
            }

            // Record all mixin classes in top down order.
            allMixins.append(cSym);
        } else {
            // Record the immediate superclass.
            if (isImmediateSuper) {
                superClassSym = cSym;
            }

            // Record all superclasses in top down order.
            superClasses.append(cSym);
        }
    }
    
    //
    // This method strips the var name out of an accessor method.
    //
    private Name getAccessorVarName(Name name) {
        for (Name prefix : defs.accessorPrefixes) {
            if (name.startsWith(prefix)) {
                return name.subName(prefix.length() - 1, name.length());
            }
        }

        return null;
    }

    //
    // This method determines if a method should be added to the list of methods
    // needing dispatch.  This method is only called for inherited methods.
    //
    private void processMethod(MethodSymbol sym, boolean needsCloning, ClassSymbol cSym, HashMap<Name, MixinClassVarInfo> mixinVarMap) {
        long flags = sym.flags();
        
        // Only look at real instance methods.
        if ((flags & (Flags.ABSTRACT | Flags.SYNTHETIC)) == 0) {
            // Generate a name/signature string for uniqueness.
            String nameSig = methodSignature(sym);
            // Look to see if we've seen a method like this before.
            FuncInfo oldMethod = visitedMethods.get(nameSig);
            // See if the current method is a mixin.
            boolean newIsMixin = isMixinClass(sym.owner);
            // See if the previous methods is a mixin.
            boolean oldIsMixin = oldMethod != null && isMixinClass(oldMethod.getSymbol().owner);
            // Create new info.
            FuncInfo newMethod = newIsMixin ? new MixinFuncInfo(sym) :  new SuperClassFuncInfo(sym);

            // Are we are still cloning this far up the hierarchy?
            if (needsCloning && (sym.flags() & Flags.PRIVATE) == 0) {
                // If the method didn't occur before or is a real method overshadowing a prior mixin.
                if ((oldMethod == null || (oldIsMixin && !newIsMixin)) && sym.owner == cSym) {
                      
                      Name varName = getAccessorVarName(sym.name);
                      if (varName != null) {
                          // Associate the accessor with the var.
                          MixinClassVarInfo varInfo = mixinVarMap.get(varName);
                          if (varInfo != null) {
                              varInfo.addAccessor(newMethod);
                          }
                      } else {
                          // Add to the methods needing $impl dispatch.
                          needDispatchMethods.put(nameSig, newMethod);
                      }
                  }
            }

            // Map the fact we've seen this name/signature.
            visitedMethods.put(nameSig, newMethod);
        }
    }

    //
    // This method determines if the var needs to be handled in the current class.
    // This method is only called for inherited attributes.
    //
    private void processAttribute(JavafxVarSymbol var, ClassSymbol cSym, boolean needsCloning, HashMap<Name, MixinClassVarInfo> mixinVarMap) {
        boolean isStatic = (var.flags() & Flags.STATIC) != 0;

        // If the var is in a class and not a static (ie., an instance attribute.)
        if (var.isMember() && !isStatic) {
            // See if we've seen this var before.
            VarInfo oldVarInfo = visitedAttributes.get(initBuilder.attributeValueName(var));

            // If we've seen this class before, it must be the same symbol and type,
            // otherwise in doesn't conflict.
            if (oldVarInfo != null &&
                (!oldVarInfo.getSymbol().name.equals(var.name) ||
                 !types.isSameType(oldVarInfo.getSymbol().type, var.type))) {
                oldVarInfo = null;
            }

            // Is the var in a mixin class and needs cloning.
            boolean newIsMixin = isMixinClass(var.owner);
            if (newIsMixin && needsCloning) {
                // Only process the mixin var if we've not seen it before.
                if ((oldVarInfo == null || oldVarInfo instanceof TranslatedOverrideClassVarInfo) && (var.flags() & Flags.PRIVATE) == 0) {
                    // Construct a new mixin VarInfo.
                    MixinClassVarInfo newVarInfo = new MixinClassVarInfo(diagPos, var);
                    // Check for overriding var.
                    checkMixinOverride(newVarInfo);
                    
                    // Add var to map.
                    Name varName = initBuilder.attributeValueName(var);
                    mixinVarMap.put(varName, newVarInfo);
                    
                    // Don't add mixin vars to mixin classes.
                    if (!isMixinClass()) {
                        // Add the new mixin VarInfo to the result list.
                        addVarToList(newVarInfo);
                    }
                    
                    // Map the fact we've seen this var.
                    visitedAttributes.put(initBuilder.attributeValueName(var), newVarInfo);
                } else if (oldVarInfo != null) {
                    // Still needs the interface.
                    oldVarInfo.setNeedsMixinInterface(true);
                }
            } else {
                // Construct a new superclass VarInfo.
                SuperClassVarInfo newVarInfo = new SuperClassVarInfo(diagPos, var);
                // Add the new superclass VarInfo to the result list.
                classVarInfos.append(newVarInfo);
                // Map the fact we've seen this var.
                visitedAttributes.put(initBuilder.attributeValueName(var), newVarInfo);
            }
        }
    }

    //
    // This method constructs a name/signature string for the supplied method
    // symbol.
    //
    private String methodSignature(MethodSymbol meth) {
        StringBuilder nameSigBld = new StringBuilder();
        nameSigBld.append(meth.name.toString());
        nameSigBld.append(":");
        nameSigBld.append(meth.getReturnType().tsym.flatName());
        nameSigBld.append(":");
        for (VarSymbol param : meth.getParameters()) {
            nameSigBld.append(param.type.tsym.flatName());
            nameSigBld.append(":");
        }
        return nameSigBld.toString();
    }

    //
    // This method can be used to dump the state of a VarInfo or subclass.
    // The before flag cam be used to dump the VarInfo supplied by the
    // JavafxInitializationBuilder.
    //
    private void printAnalysis(boolean before) {
        System.err.println("Analyzed : " + currentClassSym);

        if (before) {
            System.err.println("  translatedAttrInfo");
            for (TranslatedVarInfo ai : translatedAttrInfo) ai.printInfo();
            System.err.println("  translatedOverrideAttrInfo");
            for (TranslatedOverrideClassVarInfo ai : translatedOverrideAttrInfo) ai.printInfo();
        }

        System.err.println("  superClass");
        System.err.println("    " + superClassSym);
        System.err.println("  superClasses");
        for (ClassSymbol cs : superClasses) System.err.println("    " + cs);
        System.err.println("  immediate mixins");
        for (ClassSymbol cs : immediateMixins) System.err.println("    " + cs);
         System.err.println("  all mixins");
        for (ClassSymbol cs : allMixins) System.err.println("    " + cs);

        System.err.println("  classVarInfos");
        for (VarInfo ai : classVarInfos) ai.printInfo();
        System.err.println("  scriptVarInfos");
        for (VarInfo ai : scriptVarInfos) ai.printInfo();
        System.err.println("  classFuncInfos");
        for (FuncInfo fi : classFuncInfos) fi.printInfo();
        System.err.println("  scriptFuncInfos");
        for (FuncInfo fi : scriptFuncInfos) fi.printInfo();
        System.err.println("  needDispatchMethods");
        for (MethodSymbol m : needDispatch()) {
            System.err.println("    " + m + ", owner=" + m.owner);
        }
        System.err.println();
        System.err.println();
    }

}

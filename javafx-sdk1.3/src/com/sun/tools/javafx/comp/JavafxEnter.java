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

import static com.sun.tools.mjavac.code.Flags.*;
import static com.sun.tools.mjavac.code.Kinds.*;
import com.sun.tools.mjavac.code.*;
import com.sun.tools.mjavac.code.Type.*;
import com.sun.tools.mjavac.code.Symbol.*;
import com.sun.tools.mjavac.code.Type.ClassType;
import com.sun.tools.mjavac.code.Type.ErrorType;
import com.sun.tools.mjavac.jvm.*;
import com.sun.tools.mjavac.util.*;
import com.sun.tools.mjavac.util.JCDiagnostic.DiagnosticPosition;

import com.sun.tools.javafx.tree.*;
import com.sun.tools.javafx.code.*;
import com.sun.tools.javafx.util.MsgSym;

import java.util.Map;
import java.util.HashMap;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileManager;

public class JavafxEnter extends JavafxTreeScanner {
    protected static final Context.Key<JavafxEnter> javafxEnterKey =
	new Context.Key<JavafxEnter>();

    private final Log log;
    private final JavafxSymtab syms;
    private final JavafxCheck chk;
    private final JavafxTreeMaker fxmake;
    private final ClassReader reader;
    private final JavafxAnnotate annotate;
    private final JavafxMemberEnter memberEnter;
    private final Lint lint;
    private final JavaFileManager fileManager;
    private final JavafxTypes types;
    private JavafxScriptClassBuilder javafxModuleBuilder;
    
    public static JavafxEnter instance(Context context) {
        JavafxEnter instance = context.get(javafxEnterKey);
        if (instance == null) {
            instance = new JavafxEnter(context);
        }
        return instance;
    }

    protected JavafxEnter(Context context) {
        context.put(javafxEnterKey, this);

        log = Log.instance(context);
        reader = ClassReader.instance(context);
        fxmake = (JavafxTreeMaker) JavafxTreeMaker.instance(context);
        syms = (JavafxSymtab) JavafxSymtab.instance(context);
        chk = JavafxCheck.instance(context);
        memberEnter = JavafxMemberEnter.instance(context);
        annotate = JavafxAnnotate.instance(context);
        lint = Lint.instance(context);
        javafxModuleBuilder = JavafxScriptClassBuilder.instance(context);

        predefClassDef = fxmake.ClassDeclaration(
                fxmake.Modifiers(PUBLIC),
                syms.predefClass.name, List.<JFXExpression>nil(), null);
        predefClassDef.sym = syms.predefClass;
        fileManager = context.get(JavaFileManager.class);
        types = JavafxTypes.instance(context);
    }

    /** A hashtable mapping classes and packages to the environments current
     *  at the points of their definitions.
     */
    Map<TypeSymbol,JavafxEnv<JavafxAttrContext>> typeEnvs =
	    new HashMap<TypeSymbol,JavafxEnv<JavafxAttrContext>>();

    /** Visitor method: Scan a single node.
     */
    @Override
    public void scan(JFXTree tree) {
        if (tree != null) {
            tree.accept(this);
        }
    }

    /** Visitor method: scan a list of nodes.
     */
    @Override
    public void scan(List<? extends JFXTree> trees) {
        if (trees != null) {
            for (List<? extends JFXTree> l = trees; l.nonEmpty(); l = l.tail) {
                scan(l.head);
            }
        }
    }

    /** Accessor for typeEnvs
     */
    public JavafxEnv<JavafxAttrContext> getEnv(TypeSymbol sym) {
        return typeEnvs.get(sym);
    }

    public JavafxEnv<JavafxAttrContext> getClassEnv(TypeSymbol sym) {
        JavafxEnv<JavafxAttrContext> localEnv = getEnv(sym);
        JavafxEnv<JavafxAttrContext> lintEnv = localEnv;
        while (lintEnv.info.lint == null)
            lintEnv = lintEnv.next;
        localEnv.info.lint = lintEnv.info.lint.augment(sym.attributes_field, sym.flags());
        return localEnv;
    }
    
    /** The queue of all classes that might still need to be completed;
     *	saved and initialized by main().
     */
// JavaFX change
    protected
// JavaFX change
    ListBuffer<ClassSymbol> uncompleted;

    /** A dummy class to serve as enclClass for toplevel environments.
     */
    private JFXClassDeclaration predefClassDef;

/* ************************************************************************
 * environment construction
 *************************************************************************/


    /** Create a fresh environment for class bodies.
     *	This will create a fresh scope for local symbols of a class, referred
     *	to by the environments info.scope field.
     *	This scope will contain
     *	  - symbols for this and super
     *	  - symbols for any type parameters
     *	In addition, it serves as an anchor for scopes of methods and initializers
     *	which are nested in this scope via Scope.dup().
     *	This scope should not be confused with the members scope of a class.
     *
     *	@param tree	The class definition.
     *	@param env	The environment current outside of the class definition.
     */
    public static JavafxEnv<JavafxAttrContext> classEnv(JFXClassDeclaration tree, JavafxEnv<JavafxAttrContext> env) {
        JavafxEnv<JavafxAttrContext> localEnv =
                env.dup(tree, env.info.dup(new Scope(tree.sym)));
        localEnv.enclClass = tree;
        localEnv.outer = env;
        localEnv.info.isSelfCall = false;
        localEnv.info.lint = null; // leave this to be filled in by Attr,
        // when annotations have been processed
        return localEnv;
    }

    /** Create a fresh environment for toplevels.
     *	@param tree	The toplevel tree.
     */
    JavafxEnv<JavafxAttrContext> topLevelEnv(JFXScript tree) {
        JavafxEnv<JavafxAttrContext> localEnv = new JavafxEnv<JavafxAttrContext>(tree, new JavafxAttrContext());
        localEnv.toplevel = tree;
        localEnv.enclClass = predefClassDef;
        if (tree.namedImportScope == null) {
            tree.namedImportScope = new Scope.ImportScope(tree.packge);
            JavafxMemberEnter.importPredefs(syms, tree.namedImportScope);
        }
        if (tree.starImportScope == null) {
            tree.starImportScope = new Scope.ImportScope(tree.packge);
        }
        localEnv.info.scope = tree.namedImportScope;
        localEnv.info.lint = lint;
        return localEnv;
    }

    public JavafxEnv<JavafxAttrContext> getTopLevelEnv(JFXScript tree) {
        JavafxEnv<JavafxAttrContext> localEnv = new JavafxEnv<JavafxAttrContext>(tree, new JavafxAttrContext());
        localEnv.toplevel = tree;
        localEnv.enclClass = predefClassDef;
        localEnv.info.scope = tree.namedImportScope;
        localEnv.info.lint = lint;
        return localEnv;
    }

    /** The scope in which a member definition in environment env is to be entered
     *	This is usually the environment's scope, except for class environments,
     *	where the local scope is for type variables, and the this and super symbol
     *	only, and members go into the class member scope.
     */
    public static Scope enterScope(JavafxEnv<JavafxAttrContext> env) {
        return (env.tree.getFXTag() == JavafxTag.CLASS_DEF)
                ? ((JFXClassDeclaration) env.tree).sym.members_field
                : env.info.scope;
    }

/* ************************************************************************
 * Visitor methods for phase 1: class enter
 *************************************************************************/

    /** Visitor argument: the current environment.
     */
    protected JavafxEnv<JavafxAttrContext> env;

    /** Visitor result: the computed type.
     */
// JavaFX change
    protected
// JavaFX change
    Type result;

    /** Visitor method: enter all classes in given tree, catching any
     *	completion failure exceptions. Return the tree's type.
     *
     *	@param tree    The tree to be visited.
     *	@param env     The environment visitor argument.
     */
    Type classEnter(JFXTree tree, JavafxEnv<JavafxAttrContext> env) {
        JavafxEnv<JavafxAttrContext> prevEnv = this.env;
        try {
            this.env = env;
            if (tree != null) {
                tree.accept(this);
            }
            return result;
        } catch (CompletionFailure ex) {
            return chk.completionError(tree.pos(), ex);
        } finally {
            this.env = prevEnv;
        }
    }

    /** Visitor method: enter classes of a list of trees, returning a list of types.
     */
// JavaFX change
    protected
// JavaFX change
            <T extends JFXTree> List<Type> classEnter(List<T> trees, JavafxEnv<JavafxAttrContext> env) {
        ListBuffer<Type> ts = new ListBuffer<Type>();
        for (List<T> l = trees; l.nonEmpty(); l = l.tail) {
            ts.append(classEnter(l.head, env));
        }
        return ts.toList();
    }

    @Override
    public void visitScript(JFXScript tree) {
        JavaFileObject prev = log.useSource(tree.sourcefile);
        boolean isPkgInfo = tree.sourcefile.isNameCompatible("package-info",
                JavaFileObject.Kind.SOURCE);

        // It is possible to hava a packahe identifier that was in error
        // as in package x.; So we need to check ans see if we can produce a
        // name (we will get null if it was an erroneous declaration).
        //
        Name packageName = JavafxTreeInfo.fullName(tree.pid);

        tree.packge = (packageName == null)?
              syms.unnamedPackage
            : reader.enterPackage(packageName);
        tree.packge.complete(); // Find all classes in package.
        JavafxEnv<JavafxAttrContext> localEnv = topLevelEnv(tree);

        JFXClassDeclaration moduleClass =
                javafxModuleBuilder.preProcessJfxTopLevel(tree);

        // Save environment of package-info.java file.
        if (isPkgInfo) {
            JavafxEnv<JavafxAttrContext> env0 = typeEnvs.get(tree.packge);
            if (env0 == null) {
                typeEnvs.put(tree.packge, localEnv);
            } else {
                JFXScript tree0 = env0.toplevel;
                if (!fileManager.isSameFile(tree.sourcefile, tree0.sourcefile)) {
                    log.warning(tree.pid != null ? tree.pid.pos()
                            : null,
                            MsgSym.MESSAGE_PKG_INFO_ALREADY_SEEN,
                            tree.packge);
                }
            }
        }
        classEnter(tree.defs, localEnv);
        log.useSource(prev);
        tree.scriptScope = moduleClass.sym.members_field;
        scriptScopes.append(moduleClass.sym.members_field);
        if (moduleClass.isScriptingModeScript())
            ((JavafxClassSymbol) moduleClass.sym).setScriptingModeScript();
        result = null;
    }

    public ListBuffer<Scope> scriptScopes = new ListBuffer<Scope>();

    @Override
    public void visitClassDeclaration(JFXClassDeclaration tree) {
        Symbol owner = env.info.scope.owner;
        Scope enclScope = enterScope(env);
        ClassSymbol c;
        if (owner.kind == PCK) {
            // We are seeing a toplevel class.
            PackageSymbol packge = (PackageSymbol) owner;
            for (Symbol q = packge; q != null && q.kind == PCK; q = q.owner)
                q.flags_field |= EXISTS;
            c = reader.enterClass(tree.getName(), packge);
            packge.members().enterIfAbsent(c);
        } else {
            if (tree.getName().len != 0 &&
                    !chk.checkUniqueClassName(tree.pos(), tree.getName(), enclScope)) {
                result = null;
                return;
            }
            if (owner.kind == TYP) {
                // We are seeing a member class.
                c = reader.enterClass(tree.getName(), (TypeSymbol) owner);
                if ((owner.flags_field & INTERFACE) != 0) {
                    tree.mods.flags |= PUBLIC | STATIC;
                }
            } else {
                // We are seeing a local class.
                c = reader.defineClass(tree.getName(), owner);
                c.flatname = chk.localClassName(c);
                if (c.name.len != 0)
                    chk.checkTransparentClass(tree.pos(), c, env.info.scope);
            }
        }
        tree.sym = c;

        // Enter class into `compiled' table and enclosing scope.
        if (chk.compiled.get(c.flatname) != null) {
            duplicateClass(tree.pos(), c);
            result = new ErrorType(tree.getName(), (TypeSymbol) owner);
            tree.sym = (ClassSymbol) result.tsym;
            return;
        }
        chk.compiled.put(c.flatname, c);
        enclScope.enter(c);

        // Set up an environment for class block and store in `typeEnvs'
        // table, to be retrieved later in memberEnter and attribution.
        JavafxEnv<JavafxAttrContext> localEnv = classEnv(tree, env);
        typeEnvs.put(c, localEnv);

        // Fill out class fields.
        c.completer = memberEnter;
        c.flags_field = chk.checkFlags(tree.pos(), tree.mods.flags, c, tree);
        c.sourcefile = env.toplevel.sourcefile;
        c.members_field = new Scope(c);

        ClassType ct = (ClassType) c.type;
        if (owner.kind != PCK && (c.flags_field & STATIC) == 0) {
            // We are seeing a local or inner class.
            // Set outer_field of this class to closest enclosing class
            // which contains this class in a non-static context
            // (its "enclosing instance class"), provided such a class exists.
            Symbol owner1 = owner;
            while ((owner1.kind & (VAR | MTH)) != 0 &&
                    (owner1.flags_field & STATIC) == 0) {
                owner1 = owner1.owner;
            }
            if (owner1.kind == TYP) {
                ct.setEnclosingType(owner1.type);
            }
        }

        // Add non-local class to uncompleted, to make sure it will be
        // completed later.
        if (!c.isLocal() && uncompleted != null) uncompleted.append(c);
        // Recursively enter all member classes.
        classEnter(tree.getMembers(), localEnv);

        types.addFxClass(c, tree);
        result = c.type;
    }

    /** Complain about a duplicate class. */
    protected void duplicateClass(DiagnosticPosition pos, ClassSymbol c) {
        log.error(pos, MsgSym.MESSAGE_DUPLICATE_CLASS, c.fullname);
    }

    /** Main method: enter all classes in a list of toplevel trees.
     *	@param trees	  The list of trees to be processed.
     */
    public void main(List<JFXScript> trees) {
        complete(trees, null);
    }

    /** Main method: enter one class from a list of toplevel trees and
     *  place the rest on uncompleted for later processing.
     *  @param trees      The list of trees to be processed.
     *  @param c          The class symbol to be processed.
     */
    public void complete(List<JFXScript> trees, ClassSymbol c) {
        annotate.enterStart();
        ListBuffer<ClassSymbol> prevUncompleted = uncompleted;
        if (memberEnter.completionEnabled) {
            uncompleted = new ListBuffer<ClassSymbol>();
        }

        try {
            // enter all classes, and construct uncompleted list
            classEnter(trees, null);

            // complete all uncompleted classes in memberEnter
            if (memberEnter.completionEnabled) {
                while (uncompleted.nonEmpty()) {
                    ClassSymbol clazz = uncompleted.next();
                    if (c == null || c == clazz || prevUncompleted == null) {
                        clazz.complete();
                    } else {
                        // defer
                        prevUncompleted.append(clazz);
                    }
                }

                // if there remain any unimported toplevels (these must have
                // no classes at all), process their import statements as well.
                for (JFXScript tree : trees) {
                    if (!tree.isEntered) {
                        JavaFileObject prev = log.useSource(tree.sourcefile);
                        JavafxEnv<JavafxAttrContext> localEnv = typeEnvs.get(tree.packge);
                        if (localEnv == null) {
                            localEnv = topLevelEnv(tree);
                        }
                        memberEnter.memberEnter(tree, localEnv);
                        log.useSource(prev);
                    }
                }
            }
        } finally {
            uncompleted = prevUncompleted;
            annotate.enterDone();
        }
    }
}

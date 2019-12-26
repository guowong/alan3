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

package com.sun.tools.javafxdoc;

import java.io.*;

import java.util.Collection;

import com.sun.tools.mjavac.code.Symbol.*;
import com.sun.tools.mjavac.parser.DocCommentScanner;
import com.sun.tools.mjavac.util.Paths;
import com.sun.tools.javafx.tree.*;
import com.sun.tools.mjavac.util.*;
import com.sun.tools.javafx.code.JavafxSymtab;
import com.sun.tools.javafx.code.JavafxTypes;
import com.sun.tools.javafx.util.JavafxFileManager;
import com.sun.tools.javafx.comp.JavafxClassReader;

/**
 *  This class could be the main entry point for Javafxdoc when Javafxdoc is 
 *  used as a component in a larger software system. It provides operations to
 *  construct a new javadoc processor, and to run it on a set of source
 *  files.
 *  @author Neal Gafter, Javadoc source
 *  @author Tom Ball, Javafxdoc port
 */
public class JavafxdocTool extends com.sun.tools.javafx.main.JavafxCompiler {
    DocEnv docenv;

    final Context ctx;
    final Messager messager;
    final JavafxClassReader clsreader;
    final JavafxdocEnter jdenter;
    private final Paths paths;

    /**
     * Construct a new JavaCompiler processor, using appropriately
     * extended phases of the underlying compiler.
     */
    protected JavafxdocTool(Context context) {
        super(context);
        this.ctx = context;
        messager = Messager.instance0(context);
        clsreader = JavafxClassReader.instance(context);
        jdenter = JavafxdocEnter.instance0(context);
        paths = Paths.instance(context);
    }

    @Override
    protected void registerServices(final Context context) {
        Messager.instance0(context);
    }

    /**
     * For javadoc, the parser needs to keep comments. Overrides method from JavaCompiler.
     */
    @Override
    protected boolean keepComments() {
        return true;
    }

    /**
     *  Construct a new javadoc tool.
     */
    public static JavafxdocTool make0(Context context) {
        try {
            // Because of circularities we need to register these services
            // before we allocate JavafxClassReader, which needs to be done
            // before we allocate JavafxdocClassReader, which needs to be
            // done before we allocate JavafxdocTool.  Hence we do all this
            // stuff here rather than in registerServices.  Sigh.
            JavafxFileManager.preRegister(context);
            JavafxdocEnter.preRegister(context);
            JavafxdocMemberEnter.preRegister(context);
            JavafxSymtab.preRegister(context);
            JavadocTodo.preRegister(context);
            JavafxTypes.preRegister(context);
            DocCommentScanner.Factory.preRegister(context);

            JavafxClassReader reader = JavafxClassReader.instance(context);
            JavafxdocClassReader jclsreader = new JavafxdocClassReader(context);
            reader.jreader = jclsreader;

            return new JavafxdocTool(context);
        } catch (CompletionFailure ex) {
            Messager messager = Messager.instance0(context);
            if (messager != null)
            	messager.error(Position.NOPOS, ex.getMessage());
            return null;
        }
    }

    public RootDocImpl getRootDocImpl(String doclocale,
                                      String encoding,
                                      ModifierFilter filter,
                                      List<String> javaNames,
                                      List<String[]> options,
                                      boolean breakiterator,
                                      List<String> subPackages,
                                      List<String> excludedPackages,
                                      boolean docClasses,
                                      boolean legacyDoclet,
                      boolean quiet) throws IOException {
        docenv = DocEnv.instance(ctx);
        docenv.showAccess = filter;
        docenv.quiet = quiet;
        docenv.breakiterator = breakiterator;
        docenv.setLocale(doclocale);
        docenv.setEncoding(encoding);
        docenv.docClasses = docClasses;
        docenv.legacyDoclet = legacyDoclet;
        clsreader.sourceCompleter = docClasses ? null : this;

        ListBuffer<String> filenames = new ListBuffer<String>();
        ListBuffer<JFXScript> classTrees = new ListBuffer<JFXScript>();
        ListBuffer<JFXScript> packTrees = new ListBuffer<JFXScript>();

        try {
            for (List<String> it = javaNames; it.nonEmpty(); it = it.tail) {
                String name = it.head;
                if (!docClasses && name.endsWith(".fx") && new File(name).exists()) {
                    docenv.notice("main.Loading_source_file", name);
                        JFXScript tree = parse(name);
                        classTrees.append(tree);
                } else if (isValidPackageName(name)) {
                    filenames = filenames.append(name);
                } else if (name.endsWith(".fx")) {
                    docenv.error(null, "main.file_not_found", name);
                } else {
                    docenv.error(null, "main.illegal_package_name", name);
                }
            }

            if (!docClasses) {
                // Recursively search given subpackages.  If any packages
                //are found, add them to the list.
                searchSubPackages(subPackages, filenames, excludedPackages);

                // Parse the packages
                for (List<String> packs = filenames.toList(); packs.nonEmpty(); packs = packs.tail) {
                    // Parse sources ostensibly belonging to package.
                    parsePackageClasses(packs.head, packTrees, excludedPackages);
                }

                if (messager.nerrors() != 0) return null;

                // Enter symbols for all files
                docenv.notice("main.Building_tree");
                enterTrees(classTrees.toList().appendList(packTrees.toList()));
            }
        } catch (Abort ex) {}

        if (messager.nerrors() != 0) return null;

        if (docClasses)
            return new RootDocImpl(docenv, javaNames, options);
        else
            return new RootDocImpl(docenv, listClasses(classTrees.toList()), filenames.toList(), options);
    }

    /** Is the given string a valid package name? */
    boolean isValidPackageName(String s) {
        int index;
        while ((index = s.indexOf('.')) != -1) {
            if (!isValidClassName(s.substring(0, index))) return false;
            s = s.substring(index+1);
        }
        return isValidClassName(s);
    }

    /**
     * search all directories in path for subdirectory name. Add all
     * .java files found in such a directory to args.
     */
    private void parsePackageClasses(String name,
                                     ListBuffer<JFXScript> trees,
                                     List<String> excludedPackages)
        throws IOException {
        if (excludedPackages.contains(name)) {
            return;
        }
        boolean hasFiles = false;
        docenv.notice("main.Loading_source_files_for_package", name);
        name = name.replace('.', File.separatorChar);
        for (File pathname : paths.sourceSearchPath()) {
            File f = new File(pathname, name);
            String filenames[] = f.list();
            // if names not null, then found directory with source files
            if (filenames != null) {
                String dir = f.getAbsolutePath();
                if (!dir.endsWith(File.separator))
                    dir = dir + File.separator;
                for (int j = 0; j < filenames.length; j++) {
                    if (isValidJavaFXSourceFile(filenames[j])) {
                        String fn = dir + filenames[j];
                        // messager.notice("main.Loading_source_file", fn);
                            trees.append(parse(fn));
                        hasFiles = true;
                    }
                }
            }
        }
        if (!hasFiles)
            messager.warning("main.no_source_files_for_package",
                             name.replace(File.separatorChar, '.'));
    }

    /**
     * Recursively search all directories in path for subdirectory name.
     * Add all packages found in such a directory to packages list.
     */
    private void searchSubPackages(List<String> subPackages,
                                   ListBuffer<String> packages,
                                   List<String> excludedPackages) {
        // FIXME: This search path is bogus.
        // Only the effective source path should be searched for sources.
        // Only the effective class path should be searched for classes.
        // Should the bootclasspath/extdirs also be searched for classes?
        java.util.List<File> pathnames = new java.util.ArrayList<File>();
        if (paths.sourcePath() != null)
            for (File elt : paths.sourcePath())
                pathnames.add(elt);
        for (File elt : paths.userClassPath())
            pathnames.add(elt);

        for (String subPackage : subPackages)
            searchSubPackage(subPackage, packages, excludedPackages, pathnames);
    }

    /**
     * Recursively search all directories in path for subdirectory name.
     * Add all packages found in such a directory to packages list.
     */
    private void searchSubPackage(String packageName,
                                  ListBuffer<String> packages,
                                  List<String> excludedPackages,
                                  Collection<File> pathnames) {
        if (excludedPackages.contains(packageName))
            return;

        String packageFilename = packageName.replace('.', File.separatorChar);
        boolean addedPackage = false;
        for (File pathname : pathnames) {
            File f = new File(pathname, packageFilename);
            String filenames[] = f.list();
            // if filenames not null, then found directory
            if (filenames != null) {
                for (String filename : filenames) {
                    if (!addedPackage
                            && (isValidJavaFXSourceFile(filename) ||
                                isValidClassFile(filename))
                            && !packages.contains(packageName)) {
                        packages.append(packageName);
                        addedPackage = true;
                    } else if (isValidClassName(filename) &&
                               (new File(f, filename)).isDirectory()) {
                        searchSubPackage(packageName + "." + filename,
                                         packages, excludedPackages, pathnames);
                    }
                }
            }
        }
    }

    /**
     * Return true if given file name is a valid class file name.
     * @param file the name of the file to check.
     * @return true if given file name is a valid class file name
     * and false otherwise.
     */
    private static boolean isValidClassFile(String file) {
        if (!file.endsWith(".class")) return false;
        String clazzName = file.substring(0, file.length() - ".class".length());
        return isValidClassName(clazzName);
    }

    /**
     * Return true if given file name is a valid Java source file name.
     * @param file the name of the file to check.
     * @return true if given file name is a valid Java source file name
     * and false otherwise.
     */
    private static boolean isValidJavaFXSourceFile(String file) {
        if (!file.endsWith(".fx")) return false;
        String clazzName = file.substring(0, file.length() - ".fx".length());
        return isValidClassName(clazzName);
    }

    /** Are surrogates supported?
     */
    final static boolean surrogatesSupported = surrogatesSupported();
    private static boolean surrogatesSupported() {
        try {
            Character.isHighSurrogate('a'); 
            return true;
        } catch (NoSuchMethodError ex) {
            return false;
        }
    }

    /**
     * Return true if given file name is a valid class name
     * (including "package-info").
     * @param clazzname the name of the class to check.
     * @return true if given class name is a valid class name
     * and false otherwise.
     */
    public static boolean isValidClassName(String s) {
        if (s.length() < 1) return false;
        if (s.equals("package-info")) return true;
        if (surrogatesSupported) {
            int cp = s.codePointAt(0);
            if (!Character.isJavaIdentifierStart(cp))
                return false;
            for (int j=Character.charCount(cp); j<s.length(); j+=Character.charCount(cp)) {
                cp = s.codePointAt(j);
                if (!Character.isJavaIdentifierPart(cp))
                    return false;
            }
        } else {
            if (!Character.isJavaIdentifierStart(s.charAt(0)))
                return false;
            for (int j=1; j<s.length(); j++)
                if (!Character.isJavaIdentifierPart(s.charAt(j)))
                    return false;
        }
        return true;
    }

    /**
     * From a list of top level trees, return the list of contained class definitions
     */
    List<JFXClassDeclaration> listClasses(List<JFXScript> trees) {
        ListBuffer<JFXClassDeclaration> result = new ListBuffer<JFXClassDeclaration>();
        for (JFXScript t : trees) {
            for (JFXTree def : t.defs) {
                if (def instanceof JFXClassDeclaration)
                    result.append((JFXClassDeclaration)def);
            }
        }
        return result.toList();
    }
    
    private JFXScript parse(String filename) throws IOException {
        JavacFileManager fm = (JavacFileManager)fileManager;
        return parse(fm.getJavaFileObjectsFromStrings(List.of(filename)).iterator().next());
    }
}

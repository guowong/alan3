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

import com.sun.javadoc.*;

import com.sun.tools.mjavac.main.CommandLine;
import com.sun.tools.mjavac.util.Context;
import com.sun.tools.mjavac.util.List;
import com.sun.tools.mjavac.util.ListBuffer;
import com.sun.tools.mjavac.util.Options;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import static com.sun.tools.mjavac.code.Flags.*;
import static com.sun.tools.javafx.code.JavafxFlags.*;

/**
 * Main program of Javadoc.
 * Previously named "Main".
 *
 * @since 1.2
 * @author Robert Field
 * @author Neal Gafter (rewrite)
 */
class Start {
    /** Context for this invocation. */
    private final Context context;

    /**
     * Name of the program
     */
    private final String defaultDocletClassName;

    private static final String javafxdocName = "javafxdoc";

    private static final String standardDocletClassName =
        "com.sun.tools.xmldoclet.XMLDoclet";

    private ListBuffer<String[]> options = new ListBuffer<String[]>();

    private ModifierFilter showAccess = null;

    private long defaultFilter = PUBLIC | PROTECTED | PUBLIC_READ | PUBLIC_INIT;

    private Messager messager;

    String docLocale = "";

    boolean breakiterator = false;
    boolean quiet = false;
    String encoding = null;

    private DocletInvoker docletInvoker;

    /* Treat warnings as errors. */
    private boolean rejectWarnings = false;

    Start(String programName,
          PrintWriter errWriter,
          PrintWriter warnWriter,
          PrintWriter noticeWriter,
          String defaultDocletClassName) {
        context = new Context();
        messager = new Messager(context, programName, errWriter, warnWriter, noticeWriter);
        this.defaultDocletClassName = defaultDocletClassName;
    }

    Start(String programName, String defaultDocletClassName) {
        context = new Context();
        messager = new Messager(context, programName);
        this.defaultDocletClassName = defaultDocletClassName;
    }

    Start(String programName) {
        this(programName, standardDocletClassName);
    }

    Start() {
        this(javafxdocName);
    }

    /**
     * Usage
     */
    private void usage() {
        messager.notice("main.usage");

        // let doclet print usage information (does nothing on error)
        if (docletInvoker != null) {
            docletInvoker.optionLength("-help");
        }
    }

    /**
     * Exit
     */
    private void exit() {
        messager.exit();
    }

    private static final String versionRBName =
            "com.sun.tools.javafxdoc.resources.version";
    private static ResourceBundle versionRB;

    private static String version(String key) {
        if (versionRB == null) {
            try {
                versionRB = ResourceBundle.getBundle(versionRBName);
            } catch (MissingResourceException e) {
                return  "Error:" + e.getMessage() + " " +
                        System.getProperty("java.version");
            }
        }
        try {
            return versionRB.getString(key);
        } catch (MissingResourceException e) {
            return "Error:" + e.getMessage() + " " +
                    System.getProperty("java.version");
        }
    }
    
    private void version(boolean fullversion) {
        // TODO: for now send it to err.
        System.err.print(javafxdocName + " ");
        if (fullversion) {
            System.err.println("full version \"" + version("full") + "\"");
        } else {
            System.err.println(version("release"));
        }
    }

    /**
     * Main program - external wrapper
     */
    int begin(String argv[]) {
        boolean failed = false;

        try {
            failed = !parseAndExecute(argv);
        } catch(Messager.ExitJavadoc exc) {
            // ignore, we just exit this way
        } catch (OutOfMemoryError ee) {
            messager.error("main.out.of.memory");
            failed = true;
        } catch (Error ee) {
            ee.printStackTrace();
            messager.error("main.fatal.error");
            failed = true;
        } catch (Exception ee) {
            ee.printStackTrace();
            messager.error("main.fatal.exception");
            failed = true;
        } finally {
            messager.exitNotice();
            messager.flush();
        }
        failed |= messager.nerrors() > 0;
        failed |= rejectWarnings && messager.nwarnings() > 0;
        return failed ? 1 : 0;
    }

    private void addToList(ListBuffer<String> list, String str){
        StringTokenizer st = new StringTokenizer(str, ":");
        String current;
        while(st.hasMoreTokens()){
            current = st.nextToken();
            list.append(current);
        }
    }

    /**
     * Main program - internal
     */
    private boolean parseAndExecute(String argv[]) throws IOException {
        long tm = System.currentTimeMillis();

        ListBuffer<String> javaNames = new ListBuffer<String>();

        // Preprocess @file arguments
        try {
            argv = CommandLine.parse(argv);
        } catch (FileNotFoundException e) {
            messager.error("main.cant.read", e.getMessage());
            exit();
        } catch (IOException e) {
            e.printStackTrace();
            exit();
        }

        setDocletInvoker(argv);
        ListBuffer<String> subPackages = new ListBuffer<String>();
        ListBuffer<String> excludedPackages = new ListBuffer<String>();
        Options compOpts = Options.instance(context);
        boolean docClasses = false;
        boolean versionOption = false;

        // Parse arguments
        for (int i = 0 ; i < argv.length ; i++) {
            String arg = argv[i];
            if (arg.equals("-subpackages")) {
                oneArg(argv, i++);
                addToList(subPackages, argv[i]);
            } else if (arg.equals("-exclude")){
                oneArg(argv, i++);
                addToList(excludedPackages, argv[i]);
            } else if (arg.equals("-verbose")) {
                setOption(arg);
                compOpts.put("-verbose", "");
            } else if (arg.equals("-encoding")) {
                oneArg(argv, i++);
                encoding = argv[i];
                compOpts.put("-encoding", argv[i]);
            } else if (arg.equals("-breakiterator")) {
                breakiterator = true;
                setOption("-breakiterator");
            } else if (arg.equals("-quiet")) {
                quiet = true;
                setOption("-quiet");
            } else if (arg.equals("-help")) {
                usage();
                exit();
            } else if (arg.equals("-Xclasses")) {
                setOption(arg);
                docClasses = true;
            } else if (arg.equals("-Xwerror")) {
                setOption(arg);
                rejectWarnings = true;
            } else if (arg.equals("-private")) {
                setOption(arg);
                setFilter(ModifierFilter.ALL_ACCESS);
            } else if (arg.equals("-package")) {
                setOption(arg);
                setFilter(PUBLIC | PROTECTED | PUBLIC_READ | PUBLIC_INIT | PACKAGE_ACCESS );
            } else if (arg.equals("-protected")) {
                setOption(arg);
                setFilter(PUBLIC | PROTECTED | PUBLIC_READ | PUBLIC_INIT );
            } else if (arg.equals("-public")) {
                setOption(arg);
                setFilter(PUBLIC | PUBLIC_READ | PUBLIC_INIT);
            } else if (arg.equals("-source")) {
                oneArg(argv, i++);
                if (compOpts.get("-source") != null) {
                    usageError("main.option.already.seen", arg);
                }
                compOpts.put("-source", argv[i]);
            } else if (arg.equals("-prompt")) {
                compOpts.put("-prompt", "-prompt");
                messager.promptOnError = true;
            } else if (arg.equals("-sourcepath")) {
                oneArg(argv, i++);
                if (compOpts.get("-sourcepath") != null) {
                    usageError("main.option.already.seen", arg);
                }
                compOpts.put("-sourcepath", argv[i]);
            } else if (arg.equals("-classpath")) {
                oneArg(argv, i++);
                if (compOpts.get("-classpath") != null) {
                    usageError("main.option.already.seen", arg);
                }
                compOpts.put("-classpath", argv[i]);
            } else if (arg.equals("-sysclasspath")) {
                oneArg(argv, i++);
                if (compOpts.get("-bootclasspath") != null) {
                    usageError("main.option.already.seen", arg);
                }
                compOpts.put("-bootclasspath", argv[i]);
            } else if (arg.equals("-bootclasspath")) {
                oneArg(argv, i++);
                if (compOpts.get("-bootclasspath") != null) {
                    usageError("main.option.already.seen", arg);
                }
                compOpts.put("-bootclasspath", argv[i]);
            } else if (arg.equals("-extdirs")) {
                oneArg(argv, i++);
                if (compOpts.get("-extdirs") != null) {
                    usageError("main.option.already.seen", arg);
                }
                compOpts.put("-extdirs", argv[i]);
            } else if (arg.equals("-overview")) {
                oneArg(argv, i++);
            } else if (arg.equals("-doclet")) {
                i++;  // handled in setDocletInvoker
            } else if (arg.equals("-docletpath")) {
                i++;  // handled in setDocletInvoker
            } else if (arg.equals("-locale")) {
                if (i != 0)
                    usageError("main.locale_first");
                oneArg(argv, i++);
                docLocale = argv[i];
            } else if (arg.startsWith("-XD")) {
                String s = arg.substring("-XD".length());
                int eq = s.indexOf('=');
                String key = (eq < 0) ? s : s.substring(0, eq);
                String value = (eq < 0) ? s : s.substring(eq+1);
                compOpts.put(key, value);
            } else if (arg.startsWith("-version")) {
                versionOption = true;
                version(false);
            } else if (arg.startsWith("-fullversion")) {
                versionOption = true;
                version(true);
            }
            // call doclet for its options
            // other arg starts with - is invalid
            else if ( arg.startsWith("-") ) {
                int optionLength;
                optionLength = docletInvoker.optionLength(arg);
                if (optionLength < 0) {
                    // error already displayed
                    exit();
                } else if (optionLength == 0) {
                    // option not found
                    usageError("main.invalid_flag", arg);
                } else {
                    // doclet added option
                    if ((i + optionLength) > argv.length) {
                        usageError("main.requires_argument", arg);
                    }
                    ListBuffer<String> args = new ListBuffer<String>();
                    for (int j = 0; j < optionLength-1; ++j) {
                        args.append(argv[++i]);
                    }
                    setOption(arg, args.toList());
                }
            } else {
                javaNames.append(arg);
            }
        }

        if (javaNames.isEmpty() && subPackages.isEmpty()) {
            if (versionOption) {
                exit();
            } else {
                usageError("main.No_packages_or_classes_specified");
            }
        }

        if (!docletInvoker.validOptions(options.toList())) {
            // error message already displayed
            exit();
        }

        JavafxdocTool comp = JavafxdocTool.make0(context);
        if (comp == null) return false;

        if (showAccess == null) {
            setFilter(defaultFilter);
        }

        LanguageVersion languageVersion = docletInvoker.languageVersion();
        RootDocImpl root = comp.getRootDocImpl(
                docLocale, encoding, showAccess,
                javaNames.toList(), options.toList(), breakiterator,
                subPackages.toList(), excludedPackages.toList(),
                docClasses,
                // legacy?
                languageVersion == null || languageVersion == LanguageVersion.JAVA_1_1, quiet);

        // pass off control to the doclet
        boolean ok = root != null;
        if (ok) ok = docletInvoker.start(root);

        // We're done.
        if (compOpts.get("-verbose") != null) {
            tm = System.currentTimeMillis() - tm;
            messager.notice("main.done_in", Long.toString(tm));
        }

        return ok;
    }

    private void setDocletInvoker(String[] argv) {
        String docletClassName = null;
        String docletPath = null;

        // Parse doclet specifying arguments
        for (int i = 0 ; i < argv.length ; i++) {
            String arg = argv[i];
            if (arg.equals("-doclet")) {
                oneArg(argv, i++);
                if (docletClassName != null) {
                    usageError("main.more_than_one_doclet_specified_0_and_1",
                               docletClassName, argv[i]);
                }
                docletClassName = argv[i];
            } else if (arg.equals("-docletpath")) {
                oneArg(argv, i++);
                if (docletPath == null) {
                    docletPath = argv[i];
                } else {
                    docletPath += File.pathSeparator + argv[i];
                }
            }
        }

        if (docletClassName == null) {
            docletClassName = defaultDocletClassName;
        }

        // attempt to find doclet
        docletInvoker = new DocletInvoker(messager,
                                          docletClassName, docletPath);
    }

    private void setFilter(long filterBits) {
        if (showAccess != null) {
            messager.error("main.incompatible.access.flags");
            usage();
            exit();
        }
        showAccess = new ModifierFilter(filterBits);
    }

    /**
     * Set one arg option.
     * Error and exit if one argument is not provided.
     */
    private void oneArg(String[] args, int index) {
        if ((index + 1) < args.length) {
            setOption(args[index], args[index+1]);
        } else {
            usageError("main.requires_argument", args[index]);
        }
    }

    private void usageError(String key, Object... args) {
        messager.error(key, args);
        usage();
        exit();
    }

    /**
     * indicate an option with no arguments was given.
     */
    private void setOption(String opt) {
        String[] option = { opt };
        options.append(option);
    }

    /**
     * indicate an option with one argument was given.
     */
    private void setOption(String opt, String argument) {
        String[] option = { opt, argument };
        options.append(option);
    }

    /**
     * indicate an option with the specified list of arguments was given.
     */
    private void setOption(String opt, List<String> arguments) {
        String[] args = new String[arguments.length() + 1];
        int k = 0;
        args[k++] = opt;
        for (List<String> i = arguments; i.nonEmpty(); i=i.tail) {
            args[k++] = i.head;
        }
        options = options.append(args);
    }

}

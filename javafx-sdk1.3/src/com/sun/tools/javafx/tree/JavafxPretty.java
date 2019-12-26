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

package com.sun.tools.javafx.tree;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import com.sun.javafx.api.JavafxBindStatus;
import com.sun.javafx.api.tree.ForExpressionInClauseTree;
import com.sun.tools.mjavac.code.Symbol;
import com.sun.tools.mjavac.code.TypeTags;
import com.sun.tools.mjavac.tree.JCTree;
import com.sun.tools.mjavac.util.Convert;
import com.sun.tools.mjavac.util.List;
import com.sun.tools.mjavac.util.Name;
import com.sun.tools.mjavac.util.Position;
import com.sun.tools.javafx.code.JavafxFlags;

import static com.sun.tools.mjavac.code.Flags.*;

/** Prints out a tree as an indented Java source program.
 *
 *  <p><b>This is NOT part of any API supported by Sun Microsystems.  If
 *  you write code tree depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 *
 * @author Robert Field
 */
public class JavafxPretty implements JavafxVisitor {
    public static final int SCOPE_OUTER = 0;
    public static final int SCOPE_CLASS = 1;
    public static final int SCOPE_METHOD = 2;
    public static final int SCOPE_PARAMS = 3;
    protected int variableScope = SCOPE_OUTER;

   /** Set when we are producing source output.  If we're not
     *  producing source output, we can sometimes give more detail in
     *  the output even though that detail would not be valid java
     *  soruce.
     */
    protected final boolean sourceOutput;

    /** The output stream on which trees are printed.
     */
    Writer out;

    /** Indentation width (can be reassigned from outside).
     */
    public int width = 4;

    /** The current left margin.
     */
    int lmargin = 0;

    /** A hashtable mapping trees to their documentation comments
     *  (can be null)
     */
    Map<JCTree, String> docComments = null;
    
    CharSequence sourceContent;

    public JavafxPretty(Writer out, boolean sourceOutput, CharSequence content) {
        this.out = out;
        this.sourceOutput = sourceOutput;
        sourceContent = content;
    }

    public JavafxPretty(Writer out, boolean sourceOutput) {
        this.out = out;
        this.sourceOutput = sourceOutput;
        sourceContent = null;
    }

    /** Align code to be indented to left margin.
     */
    public void align() throws IOException {
        for (int i = 0; i < lmargin; i++) out.write(" ");
    }

    /** Increase left margin by indentation width.
     */
    public void indent() {
        lmargin = lmargin + width;
    }

    /** Decrease left margin by indentation width.
     */
    public void undent() {
        lmargin = lmargin - width;
    }

    /** Enter a new precedence level. Emit a `(' if new precedence level
     *  is less than precedence level so far.
     *  @param contextPrec    The precedence level in force so far.
     *  @param ownPrec        The new precedence level.
     */
    void open(int contextPrec, int ownPrec) throws IOException {
        if (ownPrec < contextPrec) out.write("(");
    }

    /** Leave precedence level. Emit a `(' if inner precedence level
     *  is less than precedence level we revert to.
     *  @param contextPrec    The precedence level we revert to.
     *  @param ownPrec        The inner precedence level.
     */
    void close(int contextPrec, int ownPrec) throws IOException {
        if (ownPrec < contextPrec) out.write(")");
    }

    /** Print string, replacing all non-ascii character with unicode escapes.
     */
    public void print(Object s) throws IOException {
        // s may be null for CATCH in "try {} catch() {}"
        out.write(Convert.escapeUnicode((s != null) ? s.toString() : ""));
    }

    /** Print new line.
     */
    public void println() throws IOException {
        out.write(lineSep);
    }

    String lineSep = System.getProperty("line.separator");

    /**************************************************************************
     * Traversal methods
     *************************************************************************/

    /** Exception to propogate IOException through visitXXX methods */
    protected static class UncheckedIOException extends RuntimeException {
        static final long serialVersionUID = -4032692679158424751L;
        public UncheckedIOException(IOException e) {
            super(e.getMessage(), e);
        }
    }

    /** Visitor argument: the current precedence level.
     */
    protected int prec;

    /** Visitor method: print expression tree.
     *  @param prec  The current precedence level.
     */
    public void printExpr(JFXTree tree, int prec) throws IOException {
        int prevPrec = this.prec;
        try {
//          uncomment to debug position information
//            println();
//            print(posAsString(tree.getStartPosition()));
            this.prec = prec;
            if (tree == null || tree instanceof JFXErroneous) {
                print("/*missing*/");
            } else {
                tree.accept(this);
            }
        } catch (UncheckedIOException ex) {
            IOException e = new IOException(ex.getMessage());
            e.initCause(ex);
            throw e;
        } finally {
            this.prec = prevPrec;
        }
    }

    /** Derived visitor method: print expression tree at minimum precedence level
     *  for expression.
     */
    public void printExpr(JFXTree tree) throws IOException {
        printExpr(tree, JavafxTreeInfo.noPrec);
    }

    /** Derived visitor method: print statement tree.
     */
    public void printStat(JFXTree tree) throws IOException {
        printExpr(tree, JavafxTreeInfo.notExpression);
    }

    /** Derived visitor method: print list of expression trees, separated by given string.
     *  @param sep the separator string
     */
    public <T extends JFXTree> void printExprs(List<T> trees, String sep) throws IOException {
        if (trees.nonEmpty()) {
            printExpr(trees.head);
            for (List<T> l = trees.tail; l.nonEmpty(); l = l.tail) {
                print(sep);
                printExpr(l.head);
            }
        }
    }

    /** Derived visitor method: print list of expression trees, separated by commas.
     */
    public <T extends JFXTree> void printExprs(List<T> trees) throws IOException {
        printExprs(trees, ", ");
    }

    /** Derived visitor method: print list of statements, each on a separate line.
     */
    public void printStats(List<? extends JFXTree> trees) throws IOException {
        for (List<? extends JFXTree> l = trees; l.nonEmpty(); l = l.tail) {
            align();
            printStat(l.head);
            println();
        }
    }

    /** Print a set of modifiers.
     */
    public void printFlags(long flags) throws IOException {
        String sf = JavafxTreeInfo.flagNames(flags, true);
        print(sf);
        if (sf.length() > 0) print(" ");
        if ((flags & ANNOTATION) != 0) print("@");
    }

    /** Print documentation comment, if it exists
     *  @param tree    The tree for which a documentation comment should be printed.
     */
    public void printDocComment(JFXTree tree) throws IOException {
        if (docComments != null) {
            String dc = docComments.get(tree);
            if (dc != null) {
                int pos = 0;
                int endpos = lineEndPos(dc, pos);
                while (pos < dc.length()) {
                    align();
                    print(dc.substring(pos, endpos)); 
                    pos = endpos + 1;
                    if (pos < dc.length()) println();
                    endpos = lineEndPos(dc, pos);
                }
                align();
            }
        }
    }
//where
    static int lineEndPos(String s, int start) {
        int pos = s.indexOf('\n', start);
        if (pos < 0) pos = s.length();
        return pos;
    }

    /** Print a block.
     */
    public void printBlock(List<? extends JFXTree> stats) throws IOException {
        print("{");
        println();
        indent();
        printStats(stats);
        undent();
        align();
        print("}");
    }

    /** Print unit consisting of package clause and import statements in toplevel,
     *  followed by class definition. if class definition == null,
     *  print all definitions in toplevel.
     *  @param tree     The toplevel tree
     *  @param cdef     The class definition, which is assumed to be part of the
     *                  toplevel tree.
     */
    public void printUnit(JFXScript tree) throws IOException {
        docComments = tree.docComments;
        printDocComment(tree);
        if (tree.pid != null) {
            print("package ");
            printExpr(tree.pid);
            print(";");
            println();
        }
        boolean firstImport = true;
        for (List<JFXTree> l = tree.defs; l.nonEmpty(); l = l.tail) {
            if (l.head.getFXTag() == JavafxTag.IMPORT) {
                JFXImport imp = (JFXImport)l.head;
                    if (firstImport) {
                        firstImport = false;
                        println();
                    }
                    printStat(imp);
            } else {
                printStat(l.head);
            }
        }
    }
    // where
    boolean isUsed(final Symbol t, JFXTree cdef) {
        class UsedVisitor extends JavafxTreeScanner {
            @Override
            public void scan(JFXTree tree) {
                if (tree!=null && !result) tree.accept(this);
            }
            boolean result = false;
            @Override
            public void visitIdent(JFXIdent tree) {
                if (tree.sym == t) result = true;
            }
        }
        UsedVisitor v = new UsedVisitor();
        v.scan(cdef);
        return v.result;
    }

    /**************************************************************************
     * Visitor methods
     *************************************************************************/

    public void visitScript(JFXScript tree) {
        try {
            printUnit(tree);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitImport(JFXImport tree) {
        try {
            print("import ");
            printExpr(tree.qualid);
            print(";");
            println();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitSkip(JFXSkip tree) {
        try {
            print(";");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitWhileLoop(JFXWhileLoop tree) {
        try {
            print("while ");
            if (tree.cond.getFXTag() == JavafxTag.PARENS) {
                printExpr(tree.cond);
            } else {
                print("(");
                printExpr(tree.cond);
                print(")");
            }
            print(" ");
            printStat(tree.body);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitTry(JFXTry tree) {
        try {
            print("try ");
            printStat(tree.body);
            for (List<JFXCatch> l = tree.catchers; l.nonEmpty(); l = l.tail) {
                printStat(l.head);
            }
            if (tree.finalizer != null) {
                print(" finally ");
                printStat(tree.finalizer);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitCatch(JFXCatch tree) {
        try {
            print(" catch (");
            printExpr(tree.param);
            print(") ");
            printStat(tree.body);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitIfExpression(JFXIfExpression tree) {
        try {
            print(" if (");
            printExpr(tree.cond);
            print(") ");
            printExpr(tree.truepart);
            if (tree.falsepart != null) {
                print(" else ");
                printExpr(tree.falsepart);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitBreak(JFXBreak tree) {
        try {
            print("break");
            if (tree.label != null) print(" " + tree.label);
            print(";");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitContinue(JFXContinue tree) {
        try {
            print("continue");
            if (tree.label != null) print(" " + tree.label);
            print(";");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitReturn(JFXReturn tree) {
        try {
            print("return");
            if (tree.expr != null) {
                print(" ");
                printExpr(tree.expr);
            }
            print(";");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitThrow(JFXThrow tree) {
        try {
            print("throw ");
            printExpr(tree.expr);
            print(";");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitFunctionInvocation(JFXFunctionInvocation tree) {
        try {
            if (!tree.typeargs.isEmpty()) {
                if (tree.meth.getFXTag() == JavafxTag.SELECT) {
                    JFXSelect left = (JFXSelect)tree.meth;
                    printExpr(left.selected);
                    print(".<");
                    printExprs(tree.typeargs);
                    print(">" + left.name);
                } else {
                    print("<");
                    printExprs(tree.typeargs);
                    print(">");
                    printExpr(tree.meth);
                }
            } else {
                printExpr(tree.meth);
            }
            print("(");
            printExprs(tree.args);
            print(")");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitParens(JFXParens tree) {
        try {
            print("(");
            printExpr(tree.expr);
            print(")");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitAssign(JFXAssign tree) {
        try {
            open(prec, JavafxTreeInfo.assignPrec);
            printExpr(tree.lhs, JavafxTreeInfo.assignPrec + 1);
            print(" = ");
            printExpr(tree.rhs, JavafxTreeInfo.assignPrec);
            close(prec, JavafxTreeInfo.assignPrec);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String operatorName(JavafxTag tag) {
        switch(tag) {
            case NEG:     return "-";
            case NOT:     return "not";
            case PREINC:  return "++";
            case PREDEC:  return "--";
            case POSTINC: return "++";
            case POSTDEC: return "--";
            case NULLCHK: return "<*nullchk*>";
            case OR:      return "or";
            case AND:     return "and";
            case EQ:      return "==";
            case NE:      return "!=";
            case LT:      return "<";
            case GT:      return ">";
            case LE:      return "<=";
            case GE:      return ">=";
            case PLUS:    return "+";
            case MINUS:   return "-";
            case MUL:     return "*";
            case DIV:     return "/";
            case MOD:     return "%";
            case PLUS_ASG:   return "+=";
            case MINUS_ASG:  return "-=";
            case MUL_ASG:    return "*=";
            case DIV_ASG:    return "/=";
            case REVERSE:    return "reverse";
            case INDEXOF:    return "indexof";
            case SIZEOF:     return "sizeof";
            default: return "[unexpected operator tag "+tag+"]";
        }
    }

    public void visitAssignop(JFXAssignOp tree) {
        try {
            open(prec, JavafxTreeInfo.assignopPrec);
            printExpr(tree.lhs, JavafxTreeInfo.assignopPrec + 1);
            print(" " + operatorName(tree.getFXTag()));
            printExpr(tree.rhs, JavafxTreeInfo.assignopPrec);
            close(prec, JavafxTreeInfo.assignopPrec);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    //@Override
    public void visitUnary(JFXUnary tree) {
        try {
           if (tree.getFXTag() == JavafxTag.SIZEOF) {
               print("(sizeof ");
               printExpr(tree.arg);
               print(")");
            } else {
                int ownprec = JavafxTreeInfo.opPrec(tree.getFXTag());
                String opname = operatorName(tree.getFXTag());
                open(prec, ownprec);
                if (tree.getFXTag().ordinal() <= JavafxTag.PREDEC.ordinal()) {
                    print(opname);
                    printExpr(tree.arg, ownprec);
                } else {
                    printExpr(tree.arg, ownprec);
                    print(opname);
                }
                close(prec, ownprec);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitBinary(JFXBinary tree) {
        try {
            int ownprec = JavafxTreeInfo.opPrec(tree.getFXTag());
            String opname = operatorName(tree.getFXTag());
            open(prec, ownprec);
            printExpr(tree.lhs, ownprec);
            print(" " + opname + " ");
            printExpr(tree.rhs, ownprec + 1);
            close(prec, ownprec);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitTypeCast(JFXTypeCast tree) {
        try {
            printExpr(tree.expr, JavafxTreeInfo.prefixPrec);
            print(" as ");
            printExpr(tree.clazz);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitInstanceOf(JFXInstanceOf tree) {
        try {
            open(prec, JavafxTreeInfo.ordPrec);
            printExpr(tree.expr, JavafxTreeInfo.ordPrec);
            print(" instanceof ");
            printExpr(tree.clazz, JavafxTreeInfo.ordPrec + 1);
            close(prec, JavafxTreeInfo.ordPrec);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitSelect(JFXSelect tree) {
        try {
            printExpr(tree.selected, JavafxTreeInfo.postfixPrec);
            print("." + tree.name);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitIdent(JFXIdent tree) {
        try {
            print(tree.getName());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitLiteral(JFXLiteral tree) {
        try {
            switch (tree.typetag) {
                case TypeTags.BYTE:
                case TypeTags.SHORT:
                case TypeTags.INT:
                    print(tree.value.toString());
                    break;
                case TypeTags.LONG:
                    print(tree.value + "L");
                    break;
                case TypeTags.FLOAT:
                    print(tree.value + "F");
                    break;
                case TypeTags.DOUBLE:
                    print(tree.value.toString());
                    break;
                case TypeTags.CHAR:
                    print("\'" +
                            Convert.quote(
                            String.valueOf((char)((Number)tree.value).intValue())) +
                            "\'");
                    break;
                case TypeTags.BOOLEAN:
                    print(((Number)tree.value).intValue() == 1 ? "true" : "false");
                    break;
                case TypeTags.BOT:
                    print("null");
                    break;
                default:
                    print("\"" + Convert.quote(tree.value.toString()) + "\"");
                    break;
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }


    public void visitErroneous(JFXErroneous tree) {
        try {
            print("(ERROR)");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitModifiers(JFXModifiers mods) {
        try {
            printFlags(mods.flags);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitTree(JFXTree tree) {
        try {
            print("(UNKNOWN: " + tree + ")");
            println();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    private String posAsString(int pos) {
        if (pos == Position.NOPOS || sourceContent == null) {
            return "%?%";
        }
        int line = 1;
        int bp = 0;
        while (bp < sourceContent.length() && bp < pos) {
            switch (sourceContent.charAt(bp++)) {
                case 0xD: //CR
                    if (bp < sourceContent.length() && sourceContent.charAt(bp) == 0xA) {
                        bp++;
                    }
                    line++;
                    break;
                case 0xA: //LF
                    line++;
                    break;
                }
        }
        return " %(" + pos + ")" + line + "% ";
    }

    private void printInterpolateValue(JFXInterpolateValue tree) {
        try {
            if (tree.getAttribute() != null) {
                print(tree.getAttribute());
                print("=>");
            }
            print(tree.getValue());
            printTween(tree);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitClassDeclaration(JFXClassDeclaration tree) {
        try {
            int oldScope = variableScope;
            variableScope = SCOPE_CLASS;
            println();
            align();
            printDocComment(tree);
            printFlags(tree.getModifiers().flags);
            print("class ");
            Name n = tree.getName();
            print(n == null ? "<anonymous>" : n);
            if (tree.getSupertypes().nonEmpty()) {
                print(" extends");
                for (JFXExpression sup : tree.getSupertypes()) {
                    print(" ");
                    printExpr(sup);
                }
            }
            print(" {");
            println();
            indent();
            for (JFXTree mem : tree.getMembers()) {
                align();
                printExpr(mem);
            }
            undent();
            println();
            print("}");
            println();
            align();
            variableScope = oldScope;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

     public static void visitFunctionValue(JavafxPretty pretty, JFXFunctionValue tree) {
        try {
            pretty.println();
            pretty.align();
            pretty.printDocComment(tree);
            pretty.print("function ");
            pretty.print("(");
            pretty.printExprs(tree.getParams());
            pretty.print(")");
            if (tree.getType() != null) {
                pretty.printExpr(tree.getType());
            }
            JFXBlock body = tree.getBodyExpression();
            if (body != null) {

                if  (body instanceof JFXErroneousBlock) {
                    pretty.print("<erroroneous>");
                } else {
                    pretty.printExpr(body);
                }
            }
            pretty.println();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

  public void visitFunctionValue(JFXFunctionValue tree) {
          visitFunctionValue(this, tree);
    }

    public static void visitFunctionDefinition(JavafxPretty pretty, JFXFunctionDefinition tree) {
        try {
            JavafxPretty fxpretty = (JavafxPretty)pretty;
            int oldScope = fxpretty.variableScope;
            pretty.println();
            pretty.align();
            pretty.printDocComment(tree);
            pretty.printExpr(tree.mods);
            pretty.print("function ");
            pretty.print(tree.name);
            pretty.print("(");
            fxpretty.variableScope = SCOPE_PARAMS;
            pretty.printExprs(tree.getParams());
            fxpretty.variableScope = SCOPE_METHOD;
            pretty.print(")");
            if (tree.operation.rettype != null && tree.operation.rettype.getFXTag() != JavafxTag.TYPEUNKNOWN) {
                pretty.print(" : ");
                pretty.printExpr(tree.operation.rettype);
            }
            JFXBlock body = tree.getBodyExpression();
            if (body != null) {
                pretty.print(" ");
                pretty.printExpr(body);
            }
            pretty.println();
            fxpretty.variableScope = oldScope;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public void visitFunctionDefinition(JFXFunctionDefinition tree) {
        visitFunctionDefinition(this, tree);
    }

    public void visitInitDefinition(JFXInitDefinition tree) {
        try {
            println();
            align();
            printDocComment(tree);
            print("init ");
            print(tree.getBody());
            println();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitPostInitDefinition(JFXPostInitDefinition tree) {
        try {
            println();
            align();
            printDocComment(tree);
            print("postinit ");
            print(tree.getBody());
            println();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitBlockExpression(JFXBlock tree) {
        visitBlockExpression(this, tree);
    }

    public static void visitBlockExpression(JavafxPretty pretty, JFXBlock tree) {
        try {
            pretty.printFlags(tree.flags);
            pretty.print("{");
            pretty.println();
            pretty.indent();
            pretty.printStats(tree.stats);
            if (tree.value != null) {
                pretty.align();
                pretty.printExpr(tree.value);
                pretty.println();
            }
            pretty.undent();
            pretty.align();
            pretty.print("}");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    void printBind(JavafxBindStatus bindStatus) {
        try {
            if (bindStatus.isUnidiBind()) {
                print(" bind ");
            }
            if (bindStatus.isBidiBind()) {
                print(" bind /*bi-directional*/ ");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitSequenceEmpty(JFXSequenceEmpty that) {
        try {
            print("[]");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public void visitSequenceRange(JFXSequenceRange that) {
         try {
            print("[");
            printExpr(that.getLower());
            print("..");
            printExpr(that.getUpper());
            if (that.getStepOrNull() != null) {
                print("step ");
                printExpr(that.getStepOrNull());
            }
            if (that.isExclusive()) {
                print(" exclusive");
            }
            print("]");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public void visitSequenceExplicit(JFXSequenceExplicit that) {
        try {
            boolean first = true;
            print("[");
            for (JFXExpression expr : that.getItems()) {
                if (!first) {
                    print(", ");
                }
                first = false;
                printExpr(expr);
            }
            print("]");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitSequenceIndexed(JFXSequenceIndexed that) {
        try {
            printExpr(that.getSequence());
            print("[ ");
            printExpr(that.getIndex());
            print("]");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

     public void visitSequenceSlice(JFXSequenceSlice that) {
        try {
            printExpr(that.getSequence());
            print("[ ");
            printExpr(that.getFirstIndex());
            print(" .. ");
            printExpr(that.getLastIndex());
            print("]");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    //@Override
    public void visitSequenceInsert(JFXSequenceInsert that) {
        try {
            print("insert ");
            printExpr(that.getElement());
            print(" into ");
            printExpr(that.getSequence());
            print("; ");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    //@Override
    public void visitSequenceDelete(JFXSequenceDelete that) {
        try {
            print("delete ");
            printExpr(that.getSequence());
            if (that.getElement() != null) {
                print(" (");
                printExpr(that.getElement());
                print(")");
            }
            print("; ");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    //@Override
    public void visitInvalidate(JFXInvalidate that) {
        try {
            print("invalidate ");
            printExpr(that.getVariable());
            print("; ");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    //@Override
    public void visitStringExpression(JFXStringExpression tree) {
        try {
            int i;
            List<JFXExpression> parts = tree.getParts();
            for (i = 0; i < parts.length() - 1; i += 3) {
                printExpr(parts.get(i));
                print("{");
                JFXExpression format = parts.get(i + 1);
                if (format != null) {
                    printExpr(format);
                }
                printExpr(parts.get(i + 2));
                print("}");
            }
            printExpr(parts.get(i));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitInstanciate(JFXInstanciate tree) {
        try {
            JFXExpression id = tree.getIdentifier();
            if (tree.getArgs().nonEmpty())
                print("new ");
            if (id != null) {
                printExpr(id);
            }
            if (tree.getArgs().nonEmpty()) {
                // Java constructor call
                print("(");
                printExprs(tree.getArgs());
                print(")");
            }
            {
                // JFX instantiation
                print(" {");
                if (tree.getParts().nonEmpty()) {
                    indent();
                    for (JFXObjectLiteralPart mem : tree.getParts()) {
                        println();
                        align();
                        printExpr(mem);
                    }
                    //TODO: add defs
                    undent();
                    println();
                    align();
                }
                if (tree.getClassBody() != null) {
                    printExpr(tree.getClassBody());
                }
                print("}");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitObjectLiteralPart(JFXObjectLiteralPart tree) {
        try {
            print(tree.getName());
            print(": ");
            printBind(tree.getExplicitBindStatus());
            printExpr(tree.getExpression());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitTypeAny(JFXTypeAny tree) {
        try {
            print("* ");
            print(ary(tree));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void printTypeSpecifier(JFXType type) {
        try {
            if (type instanceof JFXTypeUnknown)
                return;
            print(": ");
            printExpr(type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public void visitTypeClass(JFXTypeClass tree) {
        try {
            print(tree.getClassName());
            print(ary(tree));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitTypeFunctional(JFXTypeFunctional tree) {
        try {
            print("(");
            List<JFXType> params = tree.getParams();
            if (params.nonEmpty()) {
                printTypeSpecifier(params.head);
                for (List<JFXType> l = params.tail; l.nonEmpty(); l = l.tail) {
                    print(", ");
                    printTypeSpecifier(l.head);
                }
            }
            print(")");
            printTypeSpecifier((JFXType)tree.getReturnType());
            print(ary(tree));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    //@Override
    public void visitTypeArray(JFXTypeArray tree) {
        try {
            print("nativearray of ");
            printTypeSpecifier(tree.getElementType());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitTypeUnknown(JFXTypeUnknown tree) {
        try {
            print(ary(tree));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    String ary(JFXType tree) {
        switch (tree.getCardinality()) {
            case ANY:
                return "[]";
            case SINGLETON:
                return "";
        }
        return "";
    }

    public void visitVarInit(JFXVarInit tree) {
        try {
            print("var-init: ");
            print(tree.getVar());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    //@Override
    public void visitVarRef(JFXVarRef tree) {
        try {
            print(tree.getVarRefKind() + "(" + tree.getExpression() + ")");
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitVar(JFXVar tree) {
        try {
            if (docComments != null && docComments.get(tree) != null) {
                println(); align();
            }
            printDocComment(tree);
            printExpr(tree.mods);
            if (variableScope != SCOPE_PARAMS) {
                if ((tree.getModifiers().flags & JavafxFlags.IS_DEF) != 0) {
                    print("def ");
                } else {
                    print("var ");
                }
            }
            print(tree.getName());
            if (tree.getJFXType() != null && tree.getJFXType().getFXTag() != JavafxTag.TYPEANY) {
                printTypeSpecifier(tree.getJFXType());
            }
            if (variableScope != SCOPE_PARAMS) {
                if (tree.getInitializer() != null) {
                    print(" = ");
                    if (tree.isBound())
                        print("bind ");
                    printExpr(tree.getInitializer());
                    if (tree.isBidiBind())
                        print(" with inverse");
                }
            }
            if (tree.getOnReplace() != null) {
                printExpr(tree.getOnReplace());
            }
            if (tree.getOnInvalidate() != null) {
                printExpr(tree.getOnInvalidate());
            }
            print(";");
            if (variableScope == SCOPE_OUTER || variableScope == SCOPE_CLASS) {
                println();
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitOverrideClassVar(JFXOverrideClassVar tree) {
        try {
            print("override var ");
            printExpr(tree.getId());
            if (tree.getInitializer() != null) {
                print(" = ");
                if (tree.isBound()) {
                    print("bind ");
                }
                printExpr(tree.getInitializer());
                if (tree.isBidiBind()) {
                    print(" with inverse");
                }
            }
            print(" ");
            align();
            if (tree.getOnReplace() != null) {
                printExpr(tree.getOnReplace());
            }
            if (tree.getOnInvalidate() != null) {
                printExpr(tree.getOnInvalidate());
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
  
    
    //@Override
    public void visitOnReplace(JFXOnReplace tree) {
        try {
            String triggerKind = tree.getTriggerKind() == JFXOnReplace.Kind.ONREPLACE ?
                "replace" : "invalidate";
            print(" on " + triggerKind);
            if (tree.getOldValue() != null) {
                print(" ");
                printExpr(tree.getOldValue());
            }
            if (tree.getFirstIndex() != null) {
                print("[");
                printExpr(tree.getFirstIndex());
                if (tree.getLastIndex() != null) {
                    print(" .. ");
                    printExpr(tree.getLastIndex());
                }
                print(" ]");
            }
            if (tree.getNewElements() != null) {
                print("= ");
                printExpr(tree.getNewElements());
            }
            print(" ");
            if (tree.getBody() != null) {
                printExpr(tree.getBody());
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }      
    }
    
    
    //@Override
    public void visitForExpression(JFXForExpression tree) {
        try {
            boolean first = true;
            print("for (");
            for (ForExpressionInClauseTree cl : tree.getInClauses()) {

                // Don't try to examine erroneous in clauses. We don't wish to
                // place the entire for expression into error nodes, just because
                // one or more in clauses was in error, so we jsut skip any
                // erroneous ones.
                //
                if  (cl == null || cl instanceof JFXErroneousForExpressionInClause) {
                    continue;
                }

                JFXForExpressionInClause clause = (JFXForExpressionInClause)cl;
                if (first) {
                    first = false;
                } else {
                    print(", ");
                }
                
                JFXVar var = clause.getVar();

                // Don't try to examine erroneous loop controls, such as
                // when a variable was missing. Again, this is because the IDE may
                // try to attribute a node that is mostly correct, but contains
                // one or more components that are in error.
                //
                if  (var == null || var instanceof JFXErroneousVar) 
                {
                    print("<missing>)");
                } else {
                    print(var.getName());
                }
                print(" in ");

                JFXExpression e1 = clause.getSequenceExpression();

                if  (e1 == null || e1 instanceof JFXErroneous) {
                   print("<error>");
                } else {
                    printExpr(e1);
                }
                if (clause.getWhereExpression() != null) {
                    print(" where ");
                    printExpr(clause.getWhereExpression());
                }
            }
            print(") ");

            JFXExpression body = tree.getBodyExpression();

            if  (body == null || body instanceof JFXErroneous) {
                print(" {}\n");
            } else {
                printExpr(body);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitIndexof(JFXIndexof that) {
        try {
            print("indexof ");
            print(that.fname);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }      
    }

    //@Override
    public void visitForExpressionInClause(JFXForExpressionInClause that) {
        try {

            if (that.var == null || that.var instanceof JFXErroneousVar) {
                print("<missing var>");
            } else {
                print(that.var);
            }
            print(" in ");

            if (that.seqExpr == null || that.seqExpr instanceof JFXErroneous) {
                print("<missing expr>");
            } else {
                print(that.seqExpr);
            }
            if (that.getWhereExpression() != null) {
                print(" where ");
                if (that.getWhereExpression() instanceof JFXErroneous) {
                    print("<erroreous where>");
                } else {
                    print(that.getWhereExpression());
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    /** Convert a tree to a pretty-printed string. */
    public static String toString(JFXTree tree) {
        StringWriter s = new StringWriter();
        try {
            new JavafxPretty(s, false).printExpr(tree);
        }
        catch (IOException e) {
            // should never happen, because StringWriter is defined             
            // never to throw any IOExceptions                                  
            throw new AssertionError(e);
        }
        return s.toString();
    }

    public void visitTimeLiteral(JFXTimeLiteral tree) {
        try {
            Double d = ((Number)tree.value.value).doubleValue();
            d /= tree.duration.getMultiplier();
            print(d + tree.duration.getSuffix());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void visitInterpolateValue(JFXInterpolateValue tree) {
        printInterpolateValue(tree);
    }
    
    private void printTween(JFXInterpolateValue tree) throws IOException {
        JFXExpression tween = tree.getInterpolation();
        if (tween != null) {
            print(" tween ");
            print(tween);
        }
    }

    public void visitKeyFrameLiteral(JFXKeyFrameLiteral tree) {
        try {
            print("at (");
            print(tree.getStartDuration());
            print(") {");
            println();
            
            indent();
            printStats(List.convert(JFXTree.class, tree.getInterpolationValues()));
            
            if (tree.getTrigger() != null) {
                align();
                print("trigger ");
                visitBlockExpression(this, (JFXBlock)tree.getTrigger());
            }
            
            undent();
            
            println();
            align();
            print("}");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

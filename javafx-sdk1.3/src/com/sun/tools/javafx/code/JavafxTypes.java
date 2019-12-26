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

import com.sun.tools.javafx.comp.JavafxDefs;
import static com.sun.tools.javafx.code.JavafxTypeRepresentation.*;
import com.sun.tools.mjavac.code.*;
import com.sun.tools.mjavac.util.*;
import com.sun.tools.mjavac.code.Type.*;
import java.util.HashMap;
import com.sun.tools.javafx.tree.*;
import com.sun.tools.mjavac.code.Symbol.*;
import com.sun.tools.mjavac.jvm.ClassWriter;
import static com.sun.tools.mjavac.code.Kinds.*;
import static com.sun.tools.mjavac.code.Flags.*;
import static com.sun.tools.mjavac.code.TypeTags.*;

/**
 *
 * @author bothner
 */
public class JavafxTypes extends Types {
    JavafxSymtab syms;
    ClassWriter writer;

    private HashMap<ClassSymbol, JFXClassDeclaration> fxClasses;

    public static void preRegister(final Context context) {
        if (context.get(typesKey) == null)
            context.put(typesKey, new Context.Factory<Types>() {
                public Types make() {
                    return new JavafxTypes(context);
                }
            });
    }

    public static void preRegister(final Context context, JavafxTypes types) {
        context.put(typesKey, types);
    }

    public static JavafxTypes instance(Context context) {
        JavafxTypes instance = (JavafxTypes) context.get(typesKey);
        if (instance == null)
            instance = new JavafxTypes(context);
        return instance;
    }

    protected JavafxTypes(Context context) {
        super(context);
        syms = (JavafxSymtab) JavafxSymtab.instance(context);
        writer = ClassWriter.instance(context);
    }

    public boolean isNullable(Type type) {
            return !type.isPrimitive() &&
                    type != syms.javafx_StringType &&
                    type != syms.javafx_DurationType;
    }

    public boolean isSequence(Type type) {
        return type != Type.noType && type != null
            && type.tag != TypeTags.ERROR
            && type.tag != TypeTags.METHOD && type.tag != TypeTags.FORALL
            && erasure(type) == syms.javafx_SequenceTypeErasure;
    }

    public boolean isSyntheticBuiltinsFunction(Symbol sym) {
        return  sym != null && sym.kind == Kinds.MTH &&
                (sym.flags_field & JavafxFlags.FUNC_IS_BUILTINS_SYNTH) != 0;
    }

    public boolean isSyntheticPointerFunction(Symbol sym) {
        return  sym != null && sym.kind == Kinds.MTH &&
                (sym.flags_field & JavafxFlags.FUNC_POINTER_MAKE) != 0;
    }

    public boolean isArrayOrSequenceType(Type type) {
        return isArray(type) || isSequence(type);
    }

    public Type arrayOrSequenceElementType(Type type) {
        return isArray(type) ?
            elemtype(type) :
            elementType(type);
    }

    public Type sequenceType(Type elemType) {
        return sequenceType(elemType, true);
    }
     public Type sequenceType(Type elemType, boolean withExtends) {
        elemType = boxedTypeOrType(elemType);
        if (withExtends)
            elemType = new WildcardType(elemType, BoundKind.EXTENDS, syms.boundClass);
        return applySimpleGenericType(syms.javafx_SequenceType, elemType);
    }

    public Type applySimpleGenericType(Type base, Type... parameter) {
        List<Type> actuals = List.from(parameter);
        Type clazzOuter = base.getEnclosingType();
        return new ClassType(clazzOuter, actuals, base.tsym);
    }

    public JavafxTypeRepresentation typeRep(Type type) {
        TypeSymbol tsym = type.tsym;

        if (tsym == syms.booleanType.tsym) return TYPE_REPRESENTATION_BOOLEAN;
        if (tsym == syms.charType.tsym) return TYPE_REPRESENTATION_CHAR;
        if (tsym == syms.byteType.tsym) return TYPE_REPRESENTATION_BYTE;
        if (tsym == syms.shortType.tsym) return TYPE_REPRESENTATION_SHORT;
        if (tsym == syms.intType.tsym) return TYPE_REPRESENTATION_INT;
        if (tsym == syms.longType.tsym) return TYPE_REPRESENTATION_LONG;
        if (tsym == syms.floatType.tsym) return TYPE_REPRESENTATION_FLOAT;
        if (tsym == syms.doubleType.tsym) return TYPE_REPRESENTATION_DOUBLE;
        if (isSequence(type)) {
            return TYPE_REPRESENTATION_SEQUENCE;
        } else {
            return TYPE_REPRESENTATION_OBJECT;
        }
    }

    public Type arraySequenceType(Type elemType) {
        if (elemType.isPrimitive()) {
            String tname = typeRep(elemType).prefix();
            return syms.enterClass(JavafxDefs.sequence_PackageString + "." + tname + "ArraySequence");
        }
        Type seqtype = syms.enterClass("com.sun.javafx.runtime.sequence.ObjectArraySequence");
        return applySimpleGenericType(seqtype, elemType);
    }

    public Type boxedElementType(Type seqType) {
        Type elemType = seqType.getTypeArguments().head;
        if (elemType instanceof CapturedType)
            elemType = ((CapturedType) elemType).wildcard;
        if (elemType instanceof WildcardType)
            elemType = ((WildcardType) elemType).type;
        if (elemType == null)
            return syms.javafx_AnyType;
        return elemType;
    }

    public Type elementType(Type seqType) {
        Type elemType = boxedElementType(seqType);
        Type unboxed = unboxedType(elemType);
        if (unboxed.tag != TypeTags.NONE)
            elemType = unboxed;
        return elemType;
    }

    public Type unboxedTypeOrType(Type t) {
        Type ubt = unboxedType(t);
        return ubt==Type.noType? t : ubt;
    }

    public Type boxedTypeOrType(Type t) {
        return (t.isPrimitive() || t == syms.voidType)?
                      boxedClass(t).type
                    : t;
    }

    public Type elementTypeOrType(Type t) {
        return isSequence(t) ? elementType(t) : t;
    }

    public Type makeUnionType(Type s, Type t) {
        Type lub = lub(s.baseType(), t.baseType());
        if (lub.isCompound()) {
            //members of the compound type could not be ordered properly
            //due to the fact that JavaFX allows MI through mixins
            //the compound supertype should always be a JavaFX class
            //while the superinterfaces should be mixins
            Type clazz = null;
            ListBuffer<Type> interfaces = new ListBuffer<Type>();
            ListBuffer<Type> mixins = new ListBuffer<Type>();
            for (Type st : interfaces(lub).prepend(supertype(lub))) {
                if (isMixin(st.tsym))
                    mixins.append(st);
                else if (st.isInterface())
                    interfaces.append(st);
                else
                    clazz = st;
            }
            List<Type> supertypes = interfaces.toList().prependList(mixins.toList());
            if (clazz != null)
                supertypes = supertypes.prepend(clazz);
            lub = makeCompoundType(supertypes);
        }
        return lub;
    }
    
    @Override
    public boolean isSubtype(Type t, Type s, boolean capture) {
        boolean b = super.isSubtype(t, s, capture);
        if (!b && s.tag == CLASS && s.isCompound()) {
            for (Type s2 : interfaces(s).prepend(supertype(s))) {
                if (!isSubtype(t, s2, capture))
                    return false;
            }
            return true;
        }
        else
            return b;
    }

    @Override
    public Type asSuper(Type t, Symbol sym) {
         return asSuper.visit(t, sym);
    }
    // where
    private SimpleVisitor<Type,Symbol> asSuper = new SimpleVisitor<Type,Symbol>() {

        public Type visitType(Type t, Symbol sym) {
            return null;
        }

        @Override
        public Type visitClassType(ClassType t, Symbol sym) {
            if (t.tsym == sym)
                return t;

            for (Type st : supertypes(t)) {
                if (st.tag == CLASS || st.tag == TYPEVAR || st.tag == ERROR) {
                    Type x = asSuper(st, sym);
                    if (x != null)
                        return x;
                }
             }
             return null;
        }

        @Override
        public Type visitArrayType(ArrayType t, Symbol sym) {
            return isSubtype(t, sym.type) ? sym.type : null;
        }

        @Override
        public Type visitTypeVar(TypeVar t, Symbol sym) {
            if (t.tsym == sym)
                return t;
            else
                return asSuper(t.bound, sym);
        }

        @Override
        public Type visitErrorType(ErrorType t, Symbol sym) {
            return t;
        }
    };

    @Override
    public boolean isConvertible (Type t, Type s, Warner warn) {
        if (super.isConvertible(t, s, warn))
            return true;
        if (isSequence(t) && isArray(s))
            return isConvertible(elementType(t), elemtype(s), warn);
        if (isArray(t) && isSequence(s))
            return isConvertible(elemtype(t), elementType(s), warn);
        if (isSequence(t) && isSequence(s))
            return isConvertible(elementType(t), elementType(s), warn);
        //sequence promotion conversion
        if (isSequence(s) && !isSequence(t)) {
            return isConvertible(sequenceType(t), s, warn);
        }
        // Allow all numeric conversion, for now (some should warn)
        if (isNumeric(t) && isNumeric(s)) {
            return true;
        }
        if (t == syms.intType && s == syms.charType)
            return true;
        return false;
    }

    @Override
    public boolean isCastable(Type t, Type s, Warner warn) {
        //if source is a sequence and target is neither a sequence nor Object return false
        if (isSequence(t) &&
                !(isSequence(s) || s.tag == TypeTags.ARRAY) &&
                s != syms.objectType &&
                s != syms.botType) {
            return false;
        }

        //cannot cast from null to a value type (non-null by default) and vice-versa
        if ((s == syms.botType && t.isPrimitive()) ||
                (t == syms.botType && s.isPrimitive())) {
            return false;
        }

        Type target = isSequence(s) ? elementType(s) : s.tag == TypeTags.ARRAY ? ((ArrayType) s).elemtype : s;
        Type source = isSequence(t) ? elementType(t) : t.tag == TypeTags.ARRAY ? ((ArrayType) t).elemtype : t;
        if (!source.isPrimitive())
            target = boxedTypeOrType(target);
        if (!target.isPrimitive())
            source = boxedTypeOrType(source);

        if (source == syms.botType ||
            target == syms.botType)
            return true;

        return isCastableNoConversion(source, target, warn);
    }

    public boolean isCastableNoConversion(Type source, Type target, Warner warn) {
        if (isSequence(source) != isSequence(target) &&
                !isSameType(source, syms.objectType) &&
                !isSameType(target, syms.objectType))
            return false;

        if (source.isPrimitive() &&
                !target.isPrimitive() &&
                isSubtype(boxedClass(source).type, target))
            return true;

        if (target.isPrimitive() &&
                !source.isPrimitive() &&
                isSubtype(boxedClass(target).type, source))
            return true;

        boolean isSourceFinal = (source.tsym.flags() & FINAL) != 0;
        boolean isTargetFinal = (target.tsym.flags() & FINAL) != 0;
        if (isMixin(source.tsym) && isMixin(target.tsym))
            return true;
        else if (isMixin(source.tsym) &&
            !isTargetFinal ||
            (target.isInterface() && !isSequence(target)))
            return true;
        else if (isMixin(target.tsym) &&
            !isSourceFinal ||
            (target.isInterface() && !isSequence(target)))
            return true;
        else //conversion between two primitives/Java classes
            return super.isCastable(source, target, warn);
    }
    
    public boolean isMixin(Symbol sym) {
        if (! (sym instanceof JavafxClassSymbol))
            return false;
        sym.complete();
        return (sym.flags_field & JavafxFlags.MIXIN) != 0;
    }

    public boolean isJFXClass(Symbol sym) {
        if (! (sym instanceof JavafxClassSymbol))
            return false;
        sym.complete();
        return (sym.flags_field & JavafxFlags.FX_CLASS) != 0;
    }

    public boolean isJFXFunction(Type t) {
        return (t instanceof FunctionType);
    }
    
    public void addFxClass(ClassSymbol csym, JFXClassDeclaration cdecl) {
        if (fxClasses == null) {
            fxClasses = new HashMap<ClassSymbol, JFXClassDeclaration>();
        }
        csym.flags_field |= JavafxFlags.FX_CLASS;
        fxClasses.put(csym, cdecl);
    }
    
    public JFXClassDeclaration getFxClass (ClassSymbol csym) {
       return fxClasses.get(csym);
    }
    
    /** The implementation of this (abstract) symbol in class origin;
     *  null if none exists. Synthetic methods are not considered
     *  as possible implementations.
     *  Based on the Javac implementation method in MethodSymbol,
     *  but modified to handle multiple inheritance.
     */
    public MethodSymbol implementation(MethodSymbol msym, TypeSymbol origin, boolean checkResult) {
        msym.complete();
        if (origin instanceof JavafxClassSymbol) {
            JavafxClassSymbol c = (JavafxClassSymbol) origin;
            for (Scope.Entry e = c.members().lookup(msym.name);
                     e.scope != null;
                     e = e.next()) {
                if (e.sym.kind == MTH) {
                        MethodSymbol m = (MethodSymbol) e.sym;
                        m.complete();
                        if (m.overrides(msym, origin, this, checkResult) &&
                            (m.flags() & SYNTHETIC) == 0)
                            return m;
                }
            }
            List<Type> supers = supertypes(c.type);
            for (List<Type> l = supers; l.nonEmpty(); l = l.tail) {
                MethodSymbol m = implementation(msym, l.head.tsym, checkResult);
                if (m != null)
                    return m;
            }
            return null;
        }
        else
            return msym.implementation(origin, this, checkResult);
    }

    /** A replacement for MethodSymbol.overrides. */
    public boolean overrides(Symbol sym, Symbol _other, TypeSymbol origin, boolean checkResult) {
        if (sym.isConstructor() || _other.kind != MTH) return false;

        if (sym == _other) return true;
        MethodSymbol other = (MethodSymbol)_other;

        // assert types.asSuper(origin.type, other.owner) != null;
        Type mt = this.memberType(origin.type, sym);
        Type ot = this.memberType(origin.type, other);
        return
            this.isSubSignature(mt, ot) &&
            (!checkResult || this.resultSubtype(mt, ot, Warner.noWarnings));
    }

    /**
     * Returns a list of all supertypes of t, without duplicates, where supertypes
     * are listed according to the order in which they appear in t's extends clause.
     * This method is used in order to implicitly resolve mixin conflicts.
     *
     * @param t the type for which the supertypes list is to be retrieved
     * @return list of ordered supertypes
     */
    public List<Type> supertypesClosure(Type t) {
        return supertypesClosure(t, false, false);
    }

    public List<Type> supertypesClosure(Type t, boolean includeThis) {
        return supertypesClosure(t, includeThis, false);
    }

    public List<Type> supertypesClosure(Type t, boolean includeThis, boolean ascending) {
        List<Type> closure = supertypesClosure(t, ListBuffer.<Type>lb(), ascending);
        return includeThis ? closure :
            ascending ? 
                closure.reverse().tail.reverse() :
                closure.tail;
    }
    //where
    private List<Type> supertypesClosure(Type t, ListBuffer<Type> seenTypes, boolean ascending) {
        if (t == null || t.tag == NONE || seenTypes.contains(t)) {
            return List.nil();
        }
        else {
            seenTypes.append(t);
            List<Type> closure = supertypesClosure(supertype(t), seenTypes, ascending);
            for (Type i : interfaces(t)) {
                closure = closure.appendList(supertypesClosure(i, seenTypes, ascending));
            }
            closure = ascending ?
                closure.append(t) :
                closure.prepend(t);
            return closure;
        }
    }

    public List<Type> supertypes(Type t) {
        Type sup = supertype(t);
        return (sup == null || sup.tag == NONE) ?
            interfaces(t) :
            interfaces(t).prepend(sup);
    }

    public void clearCaches() {
        fxClasses = null;
    }

    public boolean isNumeric(Type type) {
        return (isSameType(type, syms.javafx_ByteType) ||
                isSameType(type, syms.javafx_ShortType) ||
                isSameType(type, syms.javafx_IntegerType) ||
                isSameType(type, syms.javafx_LongType) ||
                isSameType(type, syms.javafx_FloatType) ||
                isSameType(type, syms.javafx_DoubleType));
    }

    public List<String> toJavaFXString(List<Type> ts) {
        List<String> buf = List.nil();
        for (Type t : ts) {
            buf = buf.prepend(toJavaFXString(t));
        }
        return buf.reverse();
    }

    public String toJavaFXString(Type type) {
        StringBuilder buffer = new StringBuilder();
        typePrinter.visit(type, buffer);
        return buffer.toString();
    }

    SimpleVisitor typePrinter = new SimpleVisitor<Void, StringBuilder>() {

        public Void visitType(Type t, StringBuilder buffer) {
            String s = null;
            switch (t.tag) {
                case NONE: s = "<unknown>"; break;
                case UNKNOWN: s = "Object"; break;
                case BYTE: s = "Byte"; break;
                case SHORT: s = "Short"; break;
                case INT: s = "Integer"; break;
                case LONG: s = "Long"; break;
                case FLOAT: s = "Number"; break;
                case DOUBLE: s = "Double"; break;
                case CHAR: s = "Character"; break;
                case BOOLEAN: s = "Boolean"; break;
                default: s = t.toString(); break;
            }
            buffer.append(s);
            return null;
        }

        @Override
        public Void visitMethodType(MethodType t, StringBuilder buffer) {
            if (t.getReturnType() == null) {
                buffer.append("function(?):?");
                return null;
            }
            buffer.append("function(");
            List<Type> args = t.getParameterTypes();
            for (List<Type> l = args; l.nonEmpty(); l = l.tail) {
                if (l != args) {
                    buffer.append(",");
                }
                buffer.append(":");
                visit(l.head, buffer);
            }
            buffer.append("):");
            visit(t.getReturnType(), buffer);
            return null;
        }

        @Override
        public Void visitArrayType(ArrayType t, StringBuilder buffer) {
            buffer.append("nativearray of ");
            visit(elemtype(t), buffer);
            return null;
        }

        @Override
        public Void visitClassType(ClassType t, StringBuilder buffer) {
            if (isSameType(t, syms.stringType))
                buffer.append("String");
            else if (isSameType(t, syms.objectType))
                buffer.append("Object");
            else if (isSequence(t)) {
                if (t != syms.javafx_EmptySequenceType) {
                    visit(elementType(t), buffer);
                }
                buffer.append("[]");
            }
            else if (t instanceof FunctionType) {
                visitMethodType(t.asMethodType(), buffer);
            }
            else if (t.isCompound()) {
                visit(supertype(t), buffer);
            }
            else
                buffer.append(t.toString());
            return null;
        }
    };

    public String toJavaFXString(MethodSymbol sym, List<VarSymbol> params) {
        StringBuilder buffer = new StringBuilder();
        if ((sym.flags() & BLOCK) != 0)
            buffer.append(sym.owner.name);
        else {
            buffer.append(sym.name == sym.name.table.init ? sym.owner.name : sym.name);
            if (sym.type != null) {
                buffer.append('(');
                // FUTURE: check (flags() & VARARGS) != 0
                List<Type> args = sym.type.getParameterTypes();
                for (List<Type> l = args; l.nonEmpty(); l = l.tail) {
                    if (l != args)
                        buffer.append(",");
                    if (params != null && params.nonEmpty()) {
                        VarSymbol param = params.head;
                        if (param != null)
                            buffer.append(param.name);
                        params = params.tail;
                    }
                    buffer.append(":");
                    buffer.append(toJavaFXString(l.head));
                }
                buffer.append(')');
            }
        }
        return buffer.toString();
    }

    public String location (Symbol sym, Type site) {
        while ((sym.owner.flags() & BLOCK) != 0 ||
                syms.isRunMethod(sym.owner))
            sym = sym.owner;
        return sym.location(site, this);
    }

    public String location (Symbol sym) {
        while ((sym.owner.flags() & BLOCK) != 0 ||
                syms.isRunMethod(sym.owner))
            sym = sym.owner;
        return sym.location();
    }

    /**
     * Computes a type which is suitable as a variable inferred type.
     * This step is needed because the inferred type can contain captured
     * types which makes the inferred type too specific.
     *
     * @param t the type to be normalized
     * @return the normalized type
     */
    public Type normalize(Type t) {
        class TypeNormalizer extends SimpleVisitor<Type, Boolean> {

            @Override
            public Type visitTypeVar(TypeVar t, Boolean preserveWildcards) {
                return visit(t.getUpperBound(), preserveWildcards);
            }

            @Override
            public Type visitCapturedType(CapturedType t, Boolean preserveWildcards) {
                return visit(t.wildcard, preserveWildcards);
            }

            @Override
            public Type visitWildcardType(WildcardType t, Boolean preserveWildcards) {
                Type bound2 = visit(upperBound(t), preserveWildcards);
                if (!preserveWildcards) {
                    return bound2;
                }
                else if (t.kind != BoundKind.SUPER && !isSameType(bound2, upperBound(t))) {
                    t = new WildcardType(bound2, BoundKind.EXTENDS, syms.boundClass);
                }
                return t;
            }

            @Override
            public Type visitClassType(ClassType t, Boolean preserveWildcards) {
                List<Type> args2 = visit(t.getTypeArguments(), true);
                Type encl2 = visit(t.getEnclosingType(), false);
                if (!isJFXFunction(t) &&
                        (!isSameTypes(args2, t.getTypeArguments()) ||
                        !isSameType(encl2, t.getEnclosingType()))) {
                    t = new ClassType(encl2, args2, t.tsym);
                }
                return t;
            }

            public Type visitType(Type t, Boolean preserveWildcards) {
                if (t == syms.botType) {
                    return syms.objectType;
                }
                else if (isSameType(t, syms.javafx_EmptySequenceType)) {
                    return sequenceType(syms.objectType);
                }
                else if (t == syms.unreachableType) {
                    return syms.objectType;
                }
                else {
                    return t;
                }
            }

            public List<Type> visit(List<Type> ts, Boolean preserveWildcards) {
                ListBuffer<Type> buf = ListBuffer.lb();
                for (Type t : ts) {
                    buf.append(visit(t, preserveWildcards));
                }
                return buf.toList();
            }
        }
        return new TypeNormalizer().visit(t, false);
    }

    public String toSignature(Type t) {
        return writer.typeSig(t).toString();
    }
}

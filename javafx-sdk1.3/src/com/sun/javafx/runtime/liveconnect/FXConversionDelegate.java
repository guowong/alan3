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

package com.sun.javafx.runtime.liveconnect;

import java.util.*;

import netscape.javascript.*;
import com.sun.java.browser.plugin2.liveconnect.v1.*;
import javafx.reflect.*;

public class FXConversionDelegate implements ConversionDelegate {
    private FXLocal.Context context = FXLocal.getContext();
    private Bridge bridge;

    // Support for conversion of arbitrary Objects to Strings
    // This has a higher conversion cost than normal conversions
    // Note that this basically assumes the maximum number of incoming
    // arguments is this value; could be smarter, but little benefit
    private static final int TOSTRING_CONVERSION_PENALTY = 50;
    // Support for conversion of JSObjects into Strings and arrays
    // We prefer to do this as a last resort
    private static final int JSOBJECT_CONVERSION_PENALTY =
        TOSTRING_CONVERSION_PENALTY * TOSTRING_CONVERSION_PENALTY;

    // Need to know about certain primitive types
    private FXType booleanType;
    private FXType charType;
    private FXType byteType;
    private FXType shortType;
    private FXType integerType;
    private FXType longType;
    private FXType floatType;
    private FXType doubleType;

    public FXConversionDelegate(Bridge bridge) {
        this.bridge = bridge;
        booleanType = context.getBooleanType();
        charType = context.getCharacterType();
        byteType = context.getByteType();
        shortType = context.getShortType();
        integerType = context.getIntegerType();
        longType = context.getLongType();
        floatType = context.getFloatType();
        doubleType = context.getDoubleType();
    }

    public int conversionCost(Object arg, Object toType) {
        if (!(toType instanceof FXType)) {
            return -1;
        }

        FXType targetType = (FXType) toType;
        if (arg == null) {
            if (targetType instanceof FXPrimitiveType) {
                // null arguments can not undergo unboxing conversions
                return -1;
            }
            return 0;
        }

        // Primitive value conversions
        if (targetType instanceof FXPrimitiveType) {
            if (targetType.equals(floatType)) {
                if (arg instanceof Float)
                    return 0;
            } else if (targetType.equals(doubleType)) {
                if (arg instanceof Double)
                    return 0;
            } else if (targetType.equals(booleanType)) {
                if (arg instanceof Boolean)
                    return 0;
            } else if (targetType.equals(integerType)) {
                if (arg instanceof Integer)
                    return 0;
            } else if (targetType.equals(longType)) {
                if (arg instanceof Long)
                    return 0;
            } else if (targetType.equals(shortType)) {
                if (arg instanceof Short)
                    return 0;
            } else if (targetType.equals(byteType)) {
                if (arg instanceof Byte)
                    return 0;
            } else if (targetType.equals(charType)) {
                if (arg instanceof Character)
                    return 0;
            }
            if (arg instanceof String ||
                arg instanceof Number ||
                arg instanceof Character ||
                arg instanceof Boolean) {
                return 1;
            }
            // Not convertible
            return -1;
        }

        if (arg instanceof JSObject) {
            if (canConvert((JSObject) arg, targetType)) {
                return JSOBJECT_CONVERSION_PENALTY;
            } else {
                return -1;
            }
        }

        // FIXME: add any-to-String conversion

        if (!(targetType instanceof FXClassType)) {
            // Don't know what's going on
            return -1;
        }

        FXType argType;
        if (arg instanceof FXValue) {
            if (arg instanceof FXObjectValue) {
                argType = ((FXObjectValue) arg).getClassType();
            } else {
                // Shouldn't happen if we know what is going on
                return -1;
            }
        } else {
            argType = context.makeClassRef(arg.getClass());
        }

        // Object type conversions
        if (argType.equals(targetType)) {
            return 0;
        }

        return conversionDistance((FXClassType) argType, (FXClassType) targetType);
    }

    private int conversionDistance(FXClassType fromType, FXClassType toType) {
        int res = conversionDistance(fromType, toType, 0);
        if (res == Integer.MAX_VALUE) {
            // Not convertible
            return -1;
        }
        return res;
    }

    private int conversionDistance(FXClassType fromType, FXClassType toType, int depth) {
        if (fromType.equals(toType)) {
            return depth;
        }

        // Walk up superclass hierarchy
        List<FXClassType> supers = fromType.getSuperClasses(false);
        int minDepth = Integer.MAX_VALUE;
        for (FXClassType type : supers) {
            minDepth = Math.min(minDepth, conversionDistance(type, toType, 1 + depth));
        }
        return minDepth;
    }

    public boolean convert(Object obj, Object toType, Object[] result) throws Exception {
        if (!(toType instanceof FXType)) {
            return false;
        }

        FXType targetType = (FXType) toType;
        if (obj == null) {
            return true;
        }

        // Primitive value conversions
        if (targetType instanceof FXPrimitiveType) {
            boolean isNumber = obj instanceof Number;

            if (targetType.equals(floatType)) {
                if (isNumber) {
                    result[0] = context.mirrorOf(((Number) obj).floatValue());
                    return true;
                } else if (obj instanceof String) {
                    result[0] = context.mirrorOf(Float.valueOf((String) obj));
                    return true;
                } else if (obj instanceof Boolean) {
                  result[0] = context.mirrorOf(((Boolean) obj).booleanValue() ? (float) 1.0 : (float) 0.0);
                    return true;
                } else if (obj instanceof Character) {
                  result[0] = context.mirrorOf((float) ((Character) obj).charValue());
                    return true;
                }
            } else if (targetType.equals(doubleType)) {
                if (isNumber) {
                    result[0] = context.mirrorOf(((Number) obj).doubleValue());
                    return true;
                } else if (obj instanceof String) {
                    result[0] = context.mirrorOf(Double.valueOf((String) obj));
                    return true;
                } else if (obj instanceof Boolean) {
                    result[0] = context.mirrorOf(((Boolean) obj).booleanValue() ? 1.0 : 0.0);
                    return true;
                } else if (obj instanceof Character) {
                  result[0] = context.mirrorOf((double) ((Character) obj).charValue());
                    return true;
                }
            } else if (targetType.equals(integerType)) {
                if (isNumber) {
                    result[0] = context.mirrorOf(((Number) obj).intValue());
                    return true;
                } else if (obj instanceof String) {
                    result[0] = context.mirrorOf(Integer.valueOf((String) obj));
                    return true;
                } else if (obj instanceof Boolean) {
                    result[0] = context.mirrorOf(((Boolean) obj).booleanValue() ? 1 : 0);
                    return true;
                } else if (obj instanceof Character) {
                  result[0] = context.mirrorOf((int) ((Character) obj).charValue());
                    return true;
                }
            } else if (targetType.equals(longType)) {
                if (isNumber) {
                    result[0] = context.mirrorOf(((Number) obj).longValue());
                    return true;
                } else if (obj instanceof String) {
                    result[0] = context.mirrorOf(Long.valueOf((String) obj));
                    return true;
                } else if (obj instanceof Boolean) {
                    result[0] = context.mirrorOf(((Boolean) obj).booleanValue() ? 1L : 0L);
                    return true;
                } else if (obj instanceof Character) {
                  result[0] = context.mirrorOf((long) ((Character) obj).charValue());
                    return true;
                }
            } else if (targetType.equals(shortType)) {
                if (isNumber) {
                    result[0] = context.mirrorOf(((Number) obj).shortValue());
                    return true;
                } else if (obj instanceof String) {
                    result[0] = context.mirrorOf(Short.valueOf((String) obj));
                    return true;
                } else if (obj instanceof Boolean) {
                  result[0] = context.mirrorOf(((Boolean) obj).booleanValue() ? (short) 1 : (short) 0);
                    return true;
                } else if (obj instanceof Character) {
                  result[0] = context.mirrorOf((short) ((Character) obj).charValue());
                    return true;
                }
            } else if (targetType.equals(byteType)) {
                if (isNumber) {
                    result[0] = context.mirrorOf(((Number) obj).byteValue());
                    return true;
                } else if (obj instanceof String) {
                    result[0] = context.mirrorOf(Byte.valueOf((String) obj));
                    return true;
                } else if (obj instanceof Boolean) {
                  result[0] = context.mirrorOf(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
                    return true;
                } else if (obj instanceof Character) {
                  result[0] = context.mirrorOf((byte) ((Character) obj).charValue());
                    return true;
                }
            } else if (targetType.equals(booleanType)) {
                if (obj instanceof Boolean) {
                    result[0] = context.mirrorOf(((Boolean) obj).booleanValue());
                    return true;
                } else if (isNumber) {
                    // Conversion as per Core JavaScript Guide 1.5
                    // FIXME: intermediate conversion to double may be a mistake
                    double d = ((Number) obj).doubleValue();
                    result[0] = context.mirrorOf(! (Double.isNaN(d) || d == 0));
                    return true;
                } else if (obj instanceof String) {
                    result[0] = context.mirrorOf(((String) obj).length() != 0);
                    return true;
                } else if (obj instanceof Character) {
                    result[0] = context.mirrorOf(((Character) obj).charValue() != 0);
                    return true;
                }
            }

            // Inconvertible
            throw inconvertible(obj, targetType);
        }

        if (obj instanceof JSObject) {
            if (isStringType(targetType)) {
                result[0] = context.mirrorOf(obj.toString());
            } else if (targetType instanceof FXSequenceType) {
                try {
                    JSObject jsObj = (JSObject) obj;
                    FXType componentType = ((FXSequenceType) targetType).getComponentType();
                    int length = ((Number) jsObj.getMember("length")).intValue();
                    FXValue[] values = new FXValue[length];
                    Object[] tmp = new Object[1];
                    for (int i = 0; i < length; i++) {
                        Object element = null;
                        try {
                            element = jsObj.getSlot(i);
                        } catch (JSException e) {
                            // Support sparse JavaScript arrays
                        }
                        if (element != null) {
                            convert(element, componentType, tmp);
                            values[i] = (FXValue) tmp[0];
                        }
                    }
                    result[0] = context.makeSequence(componentType, values);
                    return true;
                } catch (Exception e) {
                    throw inconvertible(obj, targetType, e);
                }
            } else {
                throw inconvertible(obj, targetType);
            }
        }

        // FIXME: add any-to-String conversion
        
        if (!(targetType instanceof FXClassType)) {
            // Don't know what's going on
            throw inconvertible(obj, targetType);
        }

        FXType argType;
        if (obj instanceof FXValue) {
            if (obj instanceof FXObjectValue) {
                argType = ((FXObjectValue) obj).getClassType();
            } else {
                // Shouldn't happen if we know what is going on
                throw inconvertible(obj, targetType);
            }
        } else {
            argType = context.makeClassRef(obj.getClass());
        }

        if (!targetType.isAssignableFrom(argType)) {
            throw inconvertible(argType, targetType);
        }

        if (obj instanceof FXValue) {
            result[0] = (FXValue) obj;
        } else {
            result[0] = context.mirrorOf(obj);
        }
        return true;
    }

    private boolean isStringType(FXType targetType) {
        return ((targetType instanceof FXLocal.ClassType) &&
                (((FXLocal.ClassType) targetType).getJavaImplementationClass() == String.class));
    }
            
    // Indicates whether a JSObject can be converted to the target type
    private boolean canConvert(JSObject obj, FXType targetType) {
        if (isStringType(targetType)) {
            return true;
        }

        if (targetType instanceof FXSequenceType) {
            // See whether we have a chance of converting it; note
            // that this is a risky conversion because it implies a
            // lot of work that might go wrong (and we don't want to
            // do it twice)
            try {
                obj.getMember("length");
                return true;
            } catch (JSException e) {
                // Fall through
            }
        }

        return false;
    }

    private static IllegalArgumentException inconvertible(Object object, FXType toType) {
        return inconvertible(object, toType, null);
    }

    private static IllegalArgumentException inconvertible(Object object, FXType toType, Exception cause) {
        IllegalArgumentException exc = 
            new IllegalArgumentException("Object " + object +
                                         " can not be converted to " + toType);
        if (cause != null) {
            exc.initCause(cause);
        }
        return exc;
    }

    private static IllegalArgumentException inconvertible(FXType objectType, FXType targetType) {
        return inconvertible(objectType, targetType, null);
    }

    private static IllegalArgumentException inconvertible(FXType objectType, FXType targetType, Exception cause) {
        IllegalArgumentException exc = 
            new IllegalArgumentException("FXType " + objectType.getName() +
                                         " can not be converted to " + targetType.getName());
        if (cause != null) {
            exc.initCause(cause);
        }
        return exc;
    }
}

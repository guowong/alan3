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

package javafx.reflect;


/** A handle/proxy for an {@code Object} reference.
 *
 * @author Per Bothner
 * @profile desktop
 */

public abstract class FXObjectValue implements FXValue {
    protected FXObjectValue() {
    }

    public abstract FXClassType getType();

    /** Get the class of this instance.
     * (This is different from getType, which gives something more like
     * the compile-time type of the value - which may have limited usefulness.)
     * @return the reflection of the run-time class
     */
    public abstract FXClassType getClassType();

    public FXContext getReflectionContext() {
        return getType().getReflectionContext();
    }

  /** Initialize an attribute of an object to a given value.
   * Should only be called between {@code cls.allocate()} and {@code obj.initialize()}.
   */
  public void initVar(String name, FXValue value) {
    FXVarMember attr = getType().getVariable(name);
    initVar(attr, value);
  }
  /** Initialize an attribute of an object to a given value.
   * Should only be called between {@code cls.allocate()} and {@code obj.initialize()}.
   */
  public void initVar(FXVarMember attr, FXValue value) {
      attr.initVar(this, value);
  }
  /** Bind an attribute of an object to a given location.
   * Should only be called between {@code cls.allocate()} and {@code obj.initialize()}.
   */
  public void bindVar(String name, FXLocation location) {
    FXVarMember attr = getType().getVariable(name);
    bindVar(attr, location);
  }
  /** Bind an attribute of an object to a given location.
   * Should only be called between {@code cls.allocate()} and {@code obj.initialize()}.
   */
  public void bindVar(FXVarMember attr, FXLocation location) {
      throw new UnsupportedOperationException("unimplemented: bindVar");
  }

  /** Finish constructing an object.
   * Run init hooks, triggers etc.
   * @return the constructed object - normally the same as this.
   */
  public FXObjectValue initialize() {
      throw new UnsupportedOperationException("unimplemented: initialize");
  }

  /** Convenience method to invoke a member function. */
  public FXValue invoke(String name, FXValue... args) {
    FXType[] types = new FXType[args.length];
    for (int i = args.length;  --i >= 0; ) types[i] = args[i].getType();
    return getType().getFunction(name, types).invoke(this, args);
  }
  /** Convenience method to invoke a member function. */
  public FXValue invoke(FXFunctionMember method, FXValue... args) {
    return method.invoke(this, args);
  }

  public FXValue getItem(int index) { return this; }
  public int getItemCount() { return isNull() ? 0 : 1; }
}

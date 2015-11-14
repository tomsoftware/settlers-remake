/*******************************************************************************
 * Copyright (c) 2015
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package jsettlers.graphics.test;

import jsettlers.common.images.DrawableObjectFrame;
import jsettlers.common.mapobject.EMapObjectType;
import jsettlers.common.mapobject.IMapObject;
import jsettlers.common.material.EMaterialType;

public class TestStack implements IMapObject {

	private final EMaterialType material;
	private final int count;

	public TestStack(EMaterialType material, int count) {
		this.material = material;
		this.count = count;
	}

	public EMaterialType getMaterial() {
		return this.material;
	}

	public IMapObject getNextStack() {
		return null;
	}

	@Override
	public EMapObjectType getObjectType() {
		return EMapObjectType.STACK_OBJECT;
	}

	@Override
	public float getStateProgress() {
		return (byte) this.count;
	}
	
	@Override
	public DrawableObjectFrame getObjectStyle() {
		return null;
	}
	
	@Override
	public IMapObject getNextObject() {
		return null;
	}

	@Override
	public IMapObject getMapObject(EMapObjectType type) {
		return type == getObjectType() ? this : null;
	}
	

}

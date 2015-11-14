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
package jsettlers.logic.objects;

import jsettlers.common.images.DrawableObjectFrame;
import jsettlers.common.images.EDrawableObject;
import jsettlers.common.map.object.MapObject;
import jsettlers.common.mapobject.EMapObjectType;
import jsettlers.logic.map.grid.objects.AbstractHexMapObject;



/**
 * A decoratio object
 * 
 * @thomas Zeugner
 */
public class DecorationObject extends AbstractHexMapObject implements MapObject{
	private static final long serialVersionUID = -3554691277157393770L;


	public EDecorationType styleType;
	
	public DecorationObject(EDecorationType style) {
		this.styleType = style;
	}


	@Override
	public EMapObjectType getObjectType() {
		return EMapObjectType.DECORATION;
	}

	@Override
	public float getStateProgress() {
		return 0;
	}
	
	@Override
	public DrawableObjectFrame getObjectStyle() {
		return new DrawableObjectFrame(styleType.style);
	}
	
	@Override
	public boolean cutOff() {
		return false;
	}

	@Override
	public boolean canBeCut() {
		return false;
	}

	
}



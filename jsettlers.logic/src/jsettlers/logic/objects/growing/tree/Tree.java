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
package jsettlers.logic.objects.growing.tree;

import jsettlers.common.mapobject.EMapObjectType;
import jsettlers.common.position.ShortPoint2D;
import jsettlers.common.position.RelativePoint;
import jsettlers.common.sound.ISoundable;
import jsettlers.graphics.map.draw.MapObjectDrawer;
import jsettlers.logic.map.original.OriginalMapFileDataStructs.EMapObjectTypeType;
import jsettlers.logic.objects.growing.GrowingObject;

/**
 * This is a tree on the map.
 * 
 * @author Andreas Eberle
 * 
 */
public class Tree extends GrowingObject implements ISoundable {
	private static final long serialVersionUID = 8241068714975746824L;

	public static final float GROWTH_DURATION = 7 * 60;
	public static final float DECOMPOSE_DURATION = 2 * 60;

	private static final RelativePoint[] BLOCKED = new RelativePoint[] { new RelativePoint(0, 0) };

	private boolean soundPlayed;

	public enum ETreeTypes {
		ELM_1(0, true),
		ELM_2(1, true),
		OAK_1(2, true),
		BIRCH_1(3, true),
		BIRCH_2(4, true),
		ARECACEAE_1(5, true),
		ARECACEAE_2(6, true),
		 
		SMALL(0, false),
		 
		NOT_DEFINED(0, true);
		
		public boolean isAdult;
		public int style;
		
		ETreeTypes(int style, boolean isAdult) {
			this.isAdult = isAdult;
			this.style = style;
		}
		
		public EMapObjectType getEMapObjectType() {
			if (isAdult) return EMapObjectType.TREE_GROWING;
			return EMapObjectType.TREE_ADULT;
		}
		
		static public ETreeTypes fromInt(int treeStyle) {
			if ((treeStyle < 0) || (treeStyle >= ETreeTypes.values().length))
				return ETreeTypes.NOT_DEFINED;
			
			return ETreeTypes.values()[treeStyle];
		}
	}
	
	//- save some memory and only use byte and not int
	private byte treeTypeStyle; //- this value is ETreeTypes.ordinal();
	
	/**
	 * Creates a new Tree.
	 * 
	 * @param grid
	 */
	public Tree(ShortPoint2D pos, ETreeTypes treeStyle) {
		super(pos, treeStyle.getEMapObjectType()); // TODO : Sound is played?!
		
		if (treeStyle != ETreeTypes.NOT_DEFINED) {
			treeTypeStyle = (byte)treeStyle.style;
		}
		else {
			short x = pos.x;
			short y = pos.y;
			
			treeTypeStyle = (byte)((x + x / 5 + y / 3 + y + y / 7) % MapObjectDrawer.TREE_TYPES);
		}
	}
	
	
	@Override
	public RelativePoint[] getBlockedTiles() {
		return BLOCKED;
	}

	@Override
	protected float getGrowthDuration() {
		return GROWTH_DURATION;
	}

	@Override
	protected float getDecomposeDuration() {
		return DECOMPOSE_DURATION;
	}

	@Override
	public void setSoundPlayed() {
		soundPlayed = true;
	}

	@Override
	public boolean isSoundPlayed() {
		return soundPlayed;
	}

	@Override
	protected EMapObjectType getDeadState() {
		return EMapObjectType.TREE_DEAD;
	}

	@Override
	protected EMapObjectType getAdultState() {
		return EMapObjectType.TREE_ADULT;
	}
	
	@Override
	public int getObjectStyle() {
		return treeTypeStyle;
	}
}

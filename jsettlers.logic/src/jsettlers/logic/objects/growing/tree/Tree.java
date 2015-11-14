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

import jsettlers.common.images.DrawableObjectFrame;
import jsettlers.common.images.EDrawableObject;
import jsettlers.common.mapobject.EMapObjectType;
import jsettlers.common.mapobject.IMapObject;
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
		ELM_1(EDrawableObject.TREE_ELM_1, EDrawableObject.TREE_ELM_FALL, true),
		ELM_2(EDrawableObject.TREE_ELM_2, EDrawableObject.TREE_ELM_FALL, true),
		OAK_1(EDrawableObject.TREE_OAK_1, EDrawableObject.TREE_OAK_FALL, true),
		BIRCH_1(EDrawableObject.TREE_BIRCH_1, EDrawableObject.TREE_BIRCH_FALL, true),
		BIRCH_2(EDrawableObject.TREE_BIRCH_2, EDrawableObject.TREE_BIRCH_FALL, true),
		ARECACEAE_1(EDrawableObject.TREE_ARECACEAE_1, EDrawableObject.TREE_ARECACEAE_FALL, true),
		ARECACEAE_2(EDrawableObject.TREE_ARECACEAE_2, EDrawableObject.TREE_ARECACEAE_FALL, true),
		 
		NEW_TREE(EDrawableObject.TREE_GROWING_NEW, null, false),
		
		NOT_DEFINED(null, null, true);
		
		public final boolean isAdult;
		public final EDrawableObject style;
		public final EDrawableObject style_dead;
		
		ETreeTypes(EDrawableObject style, EDrawableObject style_dead, boolean isAdult) {
			this.isAdult = isAdult;
			this.style = style;
			this.style_dead = style_dead;
		}
		
		public EMapObjectType getEMapObjectType() {
			//if (isAdult) return EMapObjectType.TREE_GROWING; //- <- TODO
			return EMapObjectType.TREE_GROWING; //- <- ???
		}
		
		static public ETreeTypes fromInt(int treeStyle) {
			if ((treeStyle < 0) || (treeStyle >= ETreeTypes.values().length))
				return ETreeTypes.NOT_DEFINED;
			
			return ETreeTypes.values()[treeStyle];
		}
		
		static final ETreeTypes[] allAdultTrees = {ELM_1, ELM_2, OAK_1, BIRCH_1, BIRCH_2, ARECACEAE_1, ARECACEAE_2};
	}
	 
	
	//- save some memory and only use byte and not int
	private ETreeTypes treeType; //- this value is ETreeTypes.ordinal();
	
	/**
	 * Creates a new Tree.
	 * 
	 * @param grid
	 */
	public Tree(ShortPoint2D pos, ETreeTypes treeStyle) {
		super(pos, treeStyle.getEMapObjectType()); // TODO : Sound is played?!; Tree is not growing?!
		
		if (treeStyle != ETreeTypes.NOT_DEFINED) {
			this.treeType = treeStyle;
		}
		else {
			short x = pos.x;
			short y = pos.y;
			
			int n = (byte)((x + x / 5 + y / 3 + y + y / 7) % ETreeTypes.allAdultTrees.length);
			
			this.treeType = ETreeTypes.allAdultTrees[n];
		}
	}
	
	@Override
	public DrawableObjectFrame getObjectStyle() {
	
		/**
		 * First images in tree cutting sequence
		 */
		final int TREE_FALL_IMAGES = 4;

		/**
		 * Tree falling speed. bigger => faster.
		 */
		final float TREE_FALLING_SPEED = 1 / 0.001f;
		/**
		 *
		 */
		final int TREE_ROT_IMAGES = 4;
		
		
		if (super.getObjectType() == EMapObjectType.TREE_ADULT)
		{
			return new DrawableObjectFrame(this.treeType.style);
		}
		else if (super.getObjectType() == EMapObjectType.TREE_DEAD)
		{
			float progress = super.getStateProgress();
			
			int imageStep = 0;

			if (progress < IMapObject.TREE_CUT_1) {
				imageStep = (int) (progress * TREE_FALLING_SPEED);
				if (imageStep >= TREE_FALL_IMAGES) {
					imageStep = TREE_FALL_IMAGES - 1;
				}
			} else if (progress < IMapObject.TREE_CUT_2) {
				// cut image 1
				imageStep = TREE_FALL_IMAGES;
			} else if (progress < IMapObject.TREE_CUT_3) {
				// cut image 2
				imageStep = TREE_FALL_IMAGES + 1;
			} else if (progress < IMapObject.TREE_TAKEN) {
				// cut image 3
				imageStep = TREE_FALL_IMAGES + 2;
			} else {
				int relativeStep =
						(int) ((progress - IMapObject.TREE_TAKEN)
								/ (1 - IMapObject.TREE_TAKEN) * TREE_ROT_IMAGES);

				imageStep = relativeStep + TREE_FALL_IMAGES + 3;
			}
			
			
			return new DrawableObjectFrame(this.treeType.style_dead, imageStep, 4);
		}
		else //- TREE_GROWING
		{
			float progress = super.getStateProgress();
			
			if (progress < 0.33) {
				return new DrawableObjectFrame(EDrawableObject.TREE_GROWING_NEW);
			} else if (progress < 0.66) {
				return new DrawableObjectFrame(EDrawableObject.TREE_GROWING_SMALL);
			} else {
				return new DrawableObjectFrame(EDrawableObject.TREE_GROWING_MEDIUM);
			}
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
	
}

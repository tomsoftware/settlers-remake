package jsettlers.common.map.object;

import jsettlers.common.images.EDrawableObject;
import jsettlers.common.mapobject.EMapObjectType;


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
	public static final ETreeTypes[] values = ETreeTypes.values();
	public static final ETreeTypes[] allAdultTrees = {ELM_1, ELM_2, OAK_1, BIRCH_1, BIRCH_2, ARECACEAE_1, ARECACEAE_2};
	
	
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
		if ((treeStyle < 0) || (treeStyle >= values.length))
			return ETreeTypes.NOT_DEFINED;
		
		return values[treeStyle];
	}
	
	
}

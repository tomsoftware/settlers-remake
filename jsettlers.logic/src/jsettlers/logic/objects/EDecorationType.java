package jsettlers.logic.objects;

import jsettlers.common.images.EDrawableObject;

public enum EDecorationType {
	NOT_DEFINED(null),
	
	BIG_STONE_1(EDrawableObject.DECO_BIG_STONE_1),
	BIG_STONE_2(EDrawableObject.DECO_BIG_STONE_2),
	BIG_STONE_3(EDrawableObject.DECO_BIG_STONE_3),
	BIG_STONE_4(EDrawableObject.DECO_BIG_STONE_4),
	BIG_STONE_5(EDrawableObject.DECO_BIG_STONE_5),
	BIG_STONE_6(EDrawableObject.DECO_BIG_STONE_6),
	BIG_STONE_7(EDrawableObject.DECO_BIG_STONE_7),
	BIG_STONE_8(EDrawableObject.DECO_BIG_STONE_8),
	STONE_1(EDrawableObject.DECO_STONE_1),
	STONE_2(EDrawableObject.DECO_STONE_2),
	STONE_3(EDrawableObject.DECO_STONE_3),
	STONE_4(EDrawableObject.DECO_STONE_4),
	BOUNDERY_STONE_1(EDrawableObject.DECO_BOUNDERY_STONE_1),
	BOUNDERY_STONE_2(EDrawableObject.DECO_BOUNDERY_STONE_2),
	BOUNDERY_STONE_3(EDrawableObject.DECO_BOUNDERY_STONE_3),
	BOUNDERY_STONE_4(EDrawableObject.DECO_BOUNDERY_STONE_4),
	BOUNDERY_STONE_5(EDrawableObject.DECO_BOUNDERY_STONE_5),
	BOUNDERY_STONE_6(EDrawableObject.DECO_BOUNDERY_STONE_6),
	BOUNDERY_STONE_7(EDrawableObject.DECO_BOUNDERY_STONE_7),
	BOUNDERY_STONE_8(EDrawableObject.DECO_BOUNDERY_STONE_8),
	SMALL_STONE_1(EDrawableObject.DECO_SMALL_STONE_1),
	SMALL_STONE_2(EDrawableObject.DECO_SMALL_STONE_2),
	SMALL_STONE_3(EDrawableObject.DECO_SMALL_STONE_3),
	SMALL_STONE_4(EDrawableObject.DECO_SMALL_STONE_4),
	SMALL_STONE_5(EDrawableObject.DECO_SMALL_STONE_5),
	SMALL_STONE_6(EDrawableObject.DECO_SMALL_STONE_6),
	SMALL_STONE_7(EDrawableObject.DECO_SMALL_STONE_7),
	SMALL_STONE_8(EDrawableObject.DECO_SMALL_STONE_8),
	WRECK_1(EDrawableObject.DECO_WRECK_1),
	WRECK_2(EDrawableObject.DECO_WRECK_2),
	WRECK_3(EDrawableObject.DECO_WRECK_3),
	WRECK_4(EDrawableObject.DECO_WRECK_4),
	WRECK_5(EDrawableObject.DECO_WRECK_5),
	GRAVE(EDrawableObject.DECO_GRAVE),
	PLANT_SMALL_1(EDrawableObject.DECO_PLANT_SMALL_1),
	PLANT_SMALL_2(EDrawableObject.DECO_PLANT_SMALL_2),
	PLANT_SMALL_3(EDrawableObject.DECO_PLANT_SMALL_3),
	MUSHROOM_1(EDrawableObject.DECO_MUSHROOM_1),
	MUSHROOM_2(EDrawableObject.DECO_MUSHROOM_2),
	MUSHROOM_3(EDrawableObject.DECO_MUSHROOM_3),
	TREE_STUMP_1(EDrawableObject.DECO_TREE_STUMP_1),
	TREE_STUMP_2(EDrawableObject.DECO_TREE_STUMP_2),
	TREE_DEAD_1(EDrawableObject.DECO_TREE_DEAD_1),
	TREE_DEAD_2(EDrawableObject.DECO_TREE_DEAD_2),
	CACTUS_1(EDrawableObject.DECO_CACTUS_1),
	CACTUS_2(EDrawableObject.DECO_CACTUS_2),
	CACTUS_3(EDrawableObject.DECO_CACTUS_3),
	CACTUS_4(EDrawableObject.DECO_CACTUS_4),
	BONES(EDrawableObject.DECO_BONES),
	FLOWER_1(EDrawableObject.DECO_FLOWER_1),
	FLOWER_2(EDrawableObject.DECO_FLOWER_2),
	FLOWER_3(EDrawableObject.DECO_FLOWER_3),
	SHRUB_SMALL_1(EDrawableObject.DECO_SHRUB_SMALL_1),
	SHRUB_SMALL_2(EDrawableObject.DECO_SHRUB_SMALL_2),
	SHRUB_SMALL_3(EDrawableObject.DECO_SHRUB_SMALL_3),
	SHRUB_SMALL_4(EDrawableObject.DECO_SHRUB_SMALL_4),
	SHRUB_1(EDrawableObject.DECO_SHRUB_1),
	SHRUB_2(EDrawableObject.DECO_SHRUB_2),
	SHRUB_3(EDrawableObject.DECO_SHRUB_3),
	SHRUB_4(EDrawableObject.DECO_SHRUB_4),
	SHRUB_5(EDrawableObject.DECO_SHRUB_5),
	REED_BEDS_1(EDrawableObject.DECO_REED_BEDS_1),
	REED_BEDS_2(EDrawableObject.DECO_REED_BEDS_2),
	REED_BEDS_3(EDrawableObject.DECO_REED_BEDS_3),
	REED_BEDS_4(EDrawableObject.DECO_REED_BEDS_4),
	REED_BEDS_5(EDrawableObject.DECO_REED_BEDS_5),
	REED_BEDS_6(EDrawableObject.DECO_REED_BEDS_6),
	REEF_SMALL(EDrawableObject.DECO_REEF_SMALL),
	REEF_MEDIUM(EDrawableObject.DECO_REEF_MEDIUM),
	REEF_LARGE(EDrawableObject.DECO_REEF_LARGE),
	REEF_XLARGE(EDrawableObject.DECO_REEF_XLARGE);

	public final EDrawableObject style;
	
	EDecorationType(EDrawableObject style) {
		this.style = style;
	}
	
	static public EDecorationType fromInt(int treeStyle) {
		if ((treeStyle < 0) || (treeStyle >= EDecorationType.values().length))
			return EDecorationType.NOT_DEFINED;
		
		return EDecorationType.values()[treeStyle];
	}
}

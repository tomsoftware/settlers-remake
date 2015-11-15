package jsettlers.common.images;



public enum EDrawableObject {
	
	//- Trees
	TREE_ELM_1(1, 1, 0, true),
	TREE_ELM_2(1, 2, 0, true),
	TREE_ELM_FALL(1, 3, 0, true),
	//TREE_ELM_CUT1(1, 3, 4, false),
	//TREE_ELM_CUT2(1, 3, 5, false),
	//TREE_ELM_TAKE(1, 3, 6, false),
	//TREE_ELM_ROT(1, 3, 7, false),
	
	TREE_OAK_1(1, 4, 0, true),
	TREE_OAK_FALL(1, 6, 0, true),
	
	TREE_BIRCH_1(1, 7, 0, true),
	TREE_BIRCH_2(1, 8, 0, true),
	TREE_BIRCH_FALL(1, 9, 0, true),
	
	TREE_BIRCH_GROWING_SMALL(1, 21, 12),
	TREE_BIRCH_GROWING_MEDIUM(1, 21, 11),
	
	TREE_ARECACEAE_1(1, 16, 0, true),
	TREE_ARECACEAE_2(1, 17, 0, true),
	TREE_ARECACEAE_FALL(1, 9, 0, true),
	
	TREE_ARECACEAE_GROWING_SMALL(1, 18, 12), //- not used
	TREE_ARECACEAE_GROWING_MEDIUM(1, 18, 11),
	
	TREE_GROWING_NEW(1, 22, 0),

	//- Corn
	CORN_ADULT(1, 23, 7),
	CORN_DEAD(1, 23, 8),
	CORN_GROWING(1, 23, 0),
	
	//- Wine
	WINE_ADULT(1, 25, 3),
	WINE_DEAD(1, 25, 0),
	WINE_GROWING(1, 25, 1),
	
	
	//- decoration
	DECO_BIG_STONE_1(1, 29, 0),
	DECO_BIG_STONE_2(1, 29, 1),
	DECO_BIG_STONE_3(1, 29, 2),
	DECO_BIG_STONE_4(1, 29, 3),
	DECO_BIG_STONE_5(1, 29, 4),
	DECO_BIG_STONE_6(1, 29, 5),
	DECO_BIG_STONE_7(1, 29, 6),
	DECO_BIG_STONE_8(1, 29, 7),

	DECO_STONE_1(1, 29, 8),
	DECO_STONE_2(1, 29, 9),
	DECO_STONE_3(1, 29, 10),
	DECO_STONE_4(1, 29, 11),

	DECO_BOUNDERY_STONE_1(1, 29, 12),
	DECO_BOUNDERY_STONE_2(1, 29, 13),
	DECO_BOUNDERY_STONE_3(1, 29, 14),
	DECO_BOUNDERY_STONE_4(1, 29, 15),
	DECO_BOUNDERY_STONE_5(1, 29, 16),
	DECO_BOUNDERY_STONE_6(1, 29, 17),
	DECO_BOUNDERY_STONE_7(1, 29, 18),
	DECO_BOUNDERY_STONE_8(1, 29, 19),
	
	DECO_REEF_XLARGE(1, 29, 20),
	DECO_REEF_LARGE(1, 29, 21),
	DECO_REEF_MEDIUM(1, 29, 22),
	DECO_REEF_SMALL(1, 29, 23),
	
	DECO_SMALL_STONE_1(1, 30, 0),
	DECO_SMALL_STONE_2(1, 30, 1),
	DECO_SMALL_STONE_3(1, 30, 2),
	DECO_SMALL_STONE_4(1, 30, 3),
	DECO_SMALL_STONE_5(1, 30, 4),
	DECO_SMALL_STONE_6(1, 30, 5),
	DECO_SMALL_STONE_7(1, 30, 6),
	DECO_SMALL_STONE_8(1, 30, 7),

	DECO_WRECK_1(1, 28, 0),
	DECO_WRECK_2(1, 28, 1),
	DECO_WRECK_3(1, 28, 2),
	DECO_WRECK_4(1, 28, 3),
	DECO_WRECK_5(1, 28, 4),
	DECO_GRAVE(1, 28, 5),

	DECO_PLANT_SMALL_1(1, 27, 0),
	DECO_PLANT_SMALL_2(1, 27, 1),
	DECO_PLANT_SMALL_3(1, 27, 2),

	DECO_MUSHROOM_1(1, 27, 3),
	DECO_MUSHROOM_2(1, 27, 4),
	DECO_MUSHROOM_3(1, 27, 5),

	DECO_TREE_STUMP_1(1, 27, 6),
	DECO_TREE_STUMP_2(1, 27, 7),
	DECO_TREE_DEAD_1(1, 27, 8),
	DECO_TREE_DEAD_2(1, 27, 9),

	DECO_CACTUS_1(1, 27, 10),
	DECO_CACTUS_2(1, 27, 11),
	DECO_CACTUS_3(1, 27, 12),
	DECO_CACTUS_4(1, 27, 13),

	DECO_BONES(1, 27, 14),

	DECO_FLOWER_1(1, 27, 15),
	DECO_FLOWER_2(1, 27, 16),
	DECO_FLOWER_3(1, 27, 17),


	DECO_SHRUB_SMALL_1(1, 27, 18),
	DECO_SHRUB_SMALL_2(1, 27, 19),
	DECO_SHRUB_SMALL_3(1, 27, 20),
	DECO_SHRUB_SMALL_4(1, 27, 21),

	DECO_SHRUB_1(1, 27, 22),
	DECO_SHRUB_2(1, 27, 23),
	DECO_SHRUB_3(1, 27, 24),
	DECO_SHRUB_4(1, 27, 25),
	DECO_SHRUB_5(1, 27, 26),
	
	DECO_REED_BEDS_1(1, 27, 27),
	DECO_REED_BEDS_2(1, 27, 28),
	DECO_REED_BEDS_3(1, 27, 29),
	DECO_REED_BEDS_4(1, 27, 30),
	DECO_REED_BEDS_5(1, 27, 31),
	DECO_REED_BEDS_6(1, 27, 32),
	
	
	DECONSTRUCTION_SMOKE(13, 38, 0, true),
	
	
	//- resources signs
	FOUND_COAL(1, 94, 0),
	FOUND_GEMSTONE(1, 95, 0),
	FOUND_GOLD(1, 96, 0),
	FOUND_IRON(1, 97, 0),
	FOUND_BRIMSTONE(1, 98, 0),
	FOUND_NOTHING(1, 99, 0),
	
	
	//- construction of Buildings
	BUILDINGSITE_SIGN(1, 93, 0),
	BUILDINGSITE_POST(1, 92, 0),
	WORKAREA_MARK(1, 91, 0),
	CONSTRUCTION_MARK(4, 6, 0),
	SMOKE(13, 42, 0),
	
	//- Building Objects
	WINE_BOWL(13, 46, 8),

	//- Buildings
	BUILDING_MILL_RUNNING(13, 15, 0),
	
	WINE(1, 25, 0),
	
	BORDERPOST(13, 65, 0),
	
	//- X-Cross
	MARKER(3, 0 ,0),
	
	//- Stone that can 
	RES_STONE(1, 31, 0);

	
	
	
	public final byte file;
	public final int sequences;
	public final byte firstFrame;
	public final boolean isPlayableSequences;
	
	EDrawableObject(int file, int sequences, int firstFrame, boolean playSequences) {
		this.file = (byte)file;
		this.sequences	= sequences;
		this.firstFrame = (byte)firstFrame;
		this.isPlayableSequences = playSequences;
		
	}
	
	/*
	EDrawableObject(int file, int sequences, int firstFrame, boolean playSequences, int soundId) {
		this.file = (byte)file;
		this.sequences	= sequences;
		this.firstFrame = (byte)firstFrame;
		this.isPlayableSequences = playSequences;
		this.soundId = soundId;
	}
	*/
	EDrawableObject(int file, int sequences, int firstFrame) {
		this.file = (byte)file;
		this.sequences	= sequences;
		this. firstFrame = (byte)firstFrame;
		this.isPlayableSequences = false;
	}
}


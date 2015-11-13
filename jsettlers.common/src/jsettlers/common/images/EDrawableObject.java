package jsettlers.common.images;



public enum EDrawableObject {
	TREE_ELM_1(1, 1, 0, true),
	TREE_ELM_2(1, 2, 0, true),
	TREE_ELM_FALL(1, 3, 0, true),
	TREE_OAK_1(1, 4, 0, true),
	TREE_OAK_FALL(1, 6, 0, true),
	TREE_BIRCH_1(1, 7, 0, true),
	TREE_BIRCH_2(1, 8, 0, true),
	TREE_BIRCH_FALL(1, 9, 0, true),
	TREE_ARECACEAE_1(1, 16, 0, true),
	TREE_ARECACEAE_2(1, 17, 0, true),
	TREE_ARECACEAE_FALL(1, 9, 0, true),
	
	TREE_GROWING_NEW(1, 22, 0),
	TREE_GROWING_SMALL(1, 21, 12),
	TREE_GROWING_MEDIUM(1, 21, 11);
	
	public final byte file;
	public final int sequences;
	public final byte firstFrame;
	public final boolean playSequences;

	
	EDrawableObject(int file, int sequences, int firstFrame, boolean playSequences) {
		this.file = (byte)file;
		this.sequences	= sequences;
		this.firstFrame = (byte)firstFrame;
		this.playSequences = playSequences;
		
	}
	
	EDrawableObject(int file, int sequences, int firstFrame) {
		this.file = (byte)file;
		this.sequences	= sequences;
		this. firstFrame = (byte)firstFrame;
		this.playSequences = false;
	}
}

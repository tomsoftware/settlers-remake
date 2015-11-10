package jsettlers.graphics.image;

import java.io.IOException;

import jsettlers.graphics.reader.bytereader.ByteReader;

public class AnimationSequence {
	
	public class AnimationFrame {
		short posX;
		short posY;
		//- Image File ID (e.g. 12 for "siedler3_12.7c003e01f.dat" -> settlers soldiers)
		short imageFileId;
		//- Image ID (e.g. 16 for "Pike-Settler")
		short imageId;
		//- Image Frame (e.g. 0 for first "Pike-Settler" image) / also for shadow
		short imageFrame;
			       
		//- Settler Torso File
		short torsoFileId;
		short torsoId;
		short torsoFrame;
		
		//- Settler Torso File
		short shadowFileId;
		short shadowId;
	
		
		short soundFlag1;
		short soundFlag2;
	}
	
	public AnimationFrame [] frames = null;
	
	public AnimationSequence(int capacity) {
		frames = new AnimationFrame[capacity];
	}

	public void set(int FrameIndex, AnimationFrame newFrame) {
		if ((FrameIndex < 0) || (FrameIndex >= frames.length)) return;
		
		frames[FrameIndex] = newFrame;
	}
	
	public void set(int FrameIndex, ByteReader reader) throws IOException {
		if ((FrameIndex < 0) || (FrameIndex >= frames.length)) return;
		
		frames[FrameIndex].posX = (short)reader.read16signed();
		frames[FrameIndex].posY = (short)reader.read16signed();
		
		frames[FrameIndex].imageId = (short)reader.read16signed();
		frames[FrameIndex].imageFileId = (short)reader.read16signed();
		
		frames[FrameIndex].torsoId = (short)reader.read16signed();
		frames[FrameIndex].torsoFileId = (short)reader.read16signed();
		
		frames[FrameIndex].shadowId = (short)reader.read16signed();
		frames[FrameIndex].shadowFileId = (short)reader.read16signed();
		
		frames[FrameIndex].imageFrame = (short)reader.read16signed();
		frames[FrameIndex].torsoFrame = (short)reader.read16signed();
		
		frames[FrameIndex].soundFlag1 = (short)reader.read16signed();
		frames[FrameIndex].soundFlag2 = (short)reader.read16signed();

	}
	
	
	public AnimationFrame get(int FrameIndex) {
		if ((FrameIndex < 0) || (FrameIndex >= frames.length)) return null;
		
		return frames[FrameIndex];
	}
	

	
}

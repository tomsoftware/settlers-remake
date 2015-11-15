package jsettlers.common.images;

public class DrawableObjectFrame {
	
	public EDrawableObject style;
	public int frame;
	public boolean doPlayAnimation;
	public int soundId;
	
	public DrawableObjectFrame(EDrawableObject style, int frame, int soundId) {
		this.style = style;
		this.frame = frame;
		this.doPlayAnimation = false;
		this.soundId = soundId;
	}
	
	public DrawableObjectFrame(EDrawableObject style, int frame) {
		this.style = style;
		this.frame = frame;
		this.doPlayAnimation = false;
		this.soundId = -1;
	}
	
	public DrawableObjectFrame(EDrawableObject style) {
		this.style = style;
		this.frame = style.firstFrame;
		this.doPlayAnimation = false;
		this.soundId = -1;
	}
	
	public DrawableObjectFrame(EDrawableObject style, boolean doPlayAnimation) {
		this.style = style;
		this.frame = style.firstFrame;
		this.doPlayAnimation = doPlayAnimation;
		this.soundId = -1;
	}
}

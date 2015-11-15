package jsettlers.logic.objects;

import jsettlers.common.images.DrawableObjectFrame;
import jsettlers.common.images.EDrawableObject;
import jsettlers.common.mapobject.EMapObjectType;
import jsettlers.logic.constants.Constants;
import jsettlers.logic.map.grid.objects.AbstractHexMapObject;
import jsettlers.logic.stack.IStackSizeSupplier;

public class WineBowlMapObject extends AbstractHexMapObject {
	private static final long serialVersionUID = -174985264395107962L;

	private final IStackSizeSupplier wineStack;

	public WineBowlMapObject(IStackSizeSupplier wineStack) {
		this.wineStack = wineStack;
	}

	@Override
	public EMapObjectType getObjectType() {
		return EMapObjectType.WINE_BOWL;
	}

	@Override
	public float getStateProgress() {
		return ((float) wineStack.getStackSize()) / Constants.STACK_SIZE;
	}
	
	@Override
	public DrawableObjectFrame getObjectStyle() {
		int step = (int) (getStateProgress() * (EDrawableObject.WINE_BOWL.firstFrame));
		
		return new DrawableObjectFrame(EDrawableObject.WINE_BOWL, step);
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

package uk.tomhomewood.smart.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

public class SmartViewFlipper extends ViewFlipper {
	private Animation positiveOutAnimation, positiveInAnimation, negativeOutAnimation, negativeInAnimation;

	public SmartViewFlipper(Context context) {
		super(context);
	}
	
	public SmartViewFlipper(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setSetPositiveAnimations(Animation outAnimation, Animation inAnimation){
		positiveOutAnimation = outAnimation;
		positiveInAnimation = inAnimation;
	}
	
	public void setSetNegativeAnimations(Animation outAnimation, Animation inAnimation){
		negativeOutAnimation = outAnimation;
		negativeInAnimation = inAnimation;
	}
	
	@Override
	public void setDisplayedChild(int whichChild){
		int currentChild = getDisplayedChild();
		if(whichChild!=currentChild){
			if(whichChild<currentChild){
				setInAnimation(negativeInAnimation);
				setOutAnimation(negativeOutAnimation);
			}
			else{
				setInAnimation(positiveInAnimation);
				setOutAnimation(positiveOutAnimation);
			}
			super.setDisplayedChild(whichChild);
		}
	}
}
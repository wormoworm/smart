package uk.tomhomewood.smart.activities;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.TextView;

public class SmartPreferenceActivity extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		if(!isLargeTablet()){				//True if the device is not a tablet (meaning it has a screen smaller than a 7" tablet)
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);			//If this is a small device, lock the orientation to portrait
		}
	}
/*
	@Override
	protected boolean isValidFragment(String fragmentName){
		
	}
*/
	/**
	 * Helper method to determine if the device has an large screen. For example, 7" tablets are large.
	 */
	protected boolean isLargeTablet() {
		return (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}
	
	@Override
	protected boolean isValidFragment(String fragmentName){
		return true;
	}
	
	/**
	 * Returns a reference to the title {@link TextView} in the action bar.
	 * @return		The title TextView, or null if it could not be found.
	 */
	protected TextView getActionBarTitle(){
		final int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
		return (TextView) getWindow().findViewById(titleId); 
	}
}
package uk.tomhomewood.smart.preferences;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SmartPreferenceFragment extends PreferenceFragment{
	
	public static final String ARG_SHOULD_SET_ACTION_BAR_ICON = "shouldSetActionBarIcon";
	
	boolean shouldSetActionBarIcon;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Bundle arguments = getArguments();
		if(arguments!=null){
			shouldSetActionBarIcon = arguments.getBoolean(ARG_SHOULD_SET_ACTION_BAR_ICON);
		}
	}
	
	protected void setActionBarIcon(int iconResId){
		if(shouldSetActionBarIcon){
			ActionBar actionBar = getActivity().getActionBar();
			if(actionBar!=null){
				actionBar.setIcon(iconResId);
			}
		}
	}
}
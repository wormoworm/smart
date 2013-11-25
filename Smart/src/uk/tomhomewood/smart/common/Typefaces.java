package uk.tomhomewood.smart.common;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

public class Typefaces {
	private final static String TAG = "SmartTextView";

	public static final String FONT_DEFAULT_APP = "district_pro";
	public static final String FONT_DEFAULT_ANDROID = "roboto";

	public static final int TEXT_SIZE_TITLE_DEFAULT = 30;
	public static final int TEXT_SIZE_SUMMARY_DEFAULT = 20;
	
	//private static long nanoTime = 0;
	
	//Fonts are stored in this HashMap, indexed by the font name. This caching means no unneccessary reload of the same font file, which is expensive.
	private static HashMap<String, Typeface> typefaces;

	public static Typeface getFont(Context context, String fontName) {
		//Log.d(TAG, "Getting typeface, context: "+context.getClass().getName());
		//long start = System.nanoTime();
		if(typefaces==null){
			typefaces = new HashMap<String, Typeface>();
		}
		Typeface font = typefaces.get(fontName);
		if(font==null){
			try{
				font = Typeface.createFromAsset(context.getAssets(), "fonts/"+fontName+".ttf");
			}
			catch(RuntimeException e){				//Loading the font from name.ttf failed, try name.otf in case it is an opentype font
				try{
					font = Typeface.createFromAsset(context.getAssets(), "fonts/"+fontName+".otf");
				}
				catch(RuntimeException e2){
					Log.d(TAG, "Could not load font \""+fontName+"\": "+e2.toString());
				}
			}
			if(font!=null){
				typefaces.put(fontName, font);
			}
		}
		return font;
	}

	public static Typeface getDefaultAppFont(Context context) {
		return getFont(context, FONT_DEFAULT_APP);
	}
}
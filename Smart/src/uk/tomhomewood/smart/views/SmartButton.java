package uk.tomhomewood.smart.views;

import uk.tomhomewood.smart.R;
import uk.tomhomewood.smart.common.Typefaces;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class SmartButton extends Button{
	//private final static String TAG = "SmartEditText";

	public SmartButton(Context context, AttributeSet attributes) {
		super(context, attributes);
		TypedArray properties = context.obtainStyledAttributes(attributes, R.styleable.SmartButton);
		String fontName = properties.getString(R.styleable.SmartButton_font);
		properties.recycle();
		if(fontName==null){
			fontName = Typefaces.FONT_DEFAULT_APP;
		}
		if(!fontName.equals(Typefaces.FONT_DEFAULT_ANDROID)){			//Font not the default android one, so load it
			if(!isInEditMode()){
				Typeface font = Typefaces.getFont(context, fontName);
				if(font!=null){									//Font loaded successfully, so use it
					setTypeface(font);
				}
			}
		}
	}
}
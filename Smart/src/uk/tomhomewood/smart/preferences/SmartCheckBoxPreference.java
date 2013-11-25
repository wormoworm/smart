package uk.tomhomewood.smart.preferences;

import uk.tomhomewood.smart.R;

import uk.tomhomewood.smart.common.Typefaces;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class SmartCheckBoxPreference extends CheckBoxPreference{
	//private final static String TAG = "SmartCheckBoxPreference";

	private Typeface font;
	private int textSizeTitle, textSizeSummary;  
	
	public SmartCheckBoxPreference(Context context, AttributeSet attributes) {
		super(context, attributes);
		TypedArray properties = context.obtainStyledAttributes(attributes, R.styleable.SmartCheckBoxPreference);
		String fontName = properties.getString(R.styleable.SmartCheckBoxPreference_font);
		textSizeTitle = properties.getDimensionPixelSize(R.styleable.SmartCheckBoxPreference_textSizeTitle, Typefaces.TEXT_SIZE_TITLE_DEFAULT);
		textSizeSummary = properties.getDimensionPixelSize(R.styleable.SmartCheckBoxPreference_textSizeSummary, Typefaces.TEXT_SIZE_SUMMARY_DEFAULT);
		properties.recycle();
		if(fontName==null){
			fontName = Typefaces.FONT_DEFAULT_APP;
		}
		if(!fontName.equals(Typefaces.FONT_DEFAULT_ANDROID)){			//Font not the default android one, so load it
			font = Typefaces.getFont(context, fontName);
		}
	}
	
	@Override
    protected void onBindView(View view) {
        super.onBindView(view);
        TextView title = (TextView) view.findViewById(android.R.id.title);
		TextView summary = (TextView) view.findViewById(android.R.id.summary);
		title.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeTitle);
		summary.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeSummary);
		if(font!=null){									//Font loaded successfully, so use it
			title.setTypeface(font);
			summary.setTypeface(font);
		}
    }
}
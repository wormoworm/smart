package uk.tomhomewood.smart.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class SmartProgressBar extends View {
	//private final String TAG = "SmartProgressBar";
	
	private int colourNormal;
	private int colourLow;
	private int colourCritical;
	private int colourWhite;
	
	boolean initialised = false;
	
	private int value;
	private float pixelsPerPoint;
	
	private float valueMin, valueMax;
	private float valueCritical, valueLow;
	
	private int barColour;
	
	private float containerWidth, containerHeight;
	
	private Paint paintBar;
	private Bitmap bitmap;
	
	private RectF rectF;
	private Canvas imageBuffer;
	
	public SmartProgressBar(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    
	    colourNormal = 0xFF92C500;
	    colourLow = 0xFFFF8A00;
	    colourCritical = 0xCC0000;
	    colourWhite = 0xFFFFFFFF;
	    
	    value = 40;
	    
	    valueMin = 0;
	    valueMax = 100;
	    valueCritical = 5;
	    valueLow = 25;

	    paintBar = new Paint();
	    paintBar.setAntiAlias(true);
	    
	    rectF = new RectF();
	}
	
	public SmartProgressBar setColours(int colourNormal, int colourLow, int colourCritical){
		this.colourNormal = colourNormal;
		this.colourLow = colourLow;
		this.colourCritical = colourCritical;
		return this;
	}
	
	private void initialise(Canvas canvas){
		containerWidth = this.getWidth();
	    containerHeight = this.getHeight();
	    
	    bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);

	    imageBuffer = new Canvas(bitmap);
	    
	    initialised = true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
	    super.onDraw(canvas);
	    
	    if(!initialised){
	    	initialise(canvas);
	    }
	    
	    //Decide what colour the bar will be based on the value
	    if(value<valueCritical){
	    	barColour = colourCritical;
	    }
	    else if(value<valueLow){
	    	barColour = colourLow;
	    }
	    else{
	    	barColour = colourNormal;
	    }
	    paintBar.setColor(barColour);

		pixelsPerPoint = containerWidth / (valueMax - valueMin);
	    float barWidth = value * pixelsPerPoint;
	    
	    rectF.set(0, 0, barWidth, containerHeight);

	    canvas.drawColor(colourWhite);
	    canvas.drawRect(rectF, paintBar);
	    imageBuffer.drawRect(rectF, paintBar);
	    canvas.save();
	}
	
	public void setLimits(int min, int max, int criticalThreshold, int lowThreshold){
		valueMin = min;
		valueMax = max;
		valueCritical = criticalThreshold;
		valueLow = lowThreshold;
	}
	
	public void setValue(int value){
		this.value = value;
		invalidate();		//Redraw
	}
}
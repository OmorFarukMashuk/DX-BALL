package hr.game.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DxBallBar implements Runnable {
	
	
	private float barTop, barLeft, barWidth, barHeight;
	Canvas canvas;
	Paint paint=new Paint();
	
	public DxBallBar(Canvas canvas,float barTop,float barLeft,float barWidth,float barHeight) {
		// TODO Auto-generated constructor stub
		
		this.canvas=canvas;
		this.barTop=barTop;
		this.barLeft=barLeft;
		this.barWidth=barWidth;
		this.barHeight=barHeight;
	}
	public float getBarHeight() {
		return barHeight;
	}
	public float getBarTop() {
		return barTop;
	}
	public float getBarDown() {
		return barTop+barHeight;
	}
	public float getBarLeft() {
		return barLeft;
	}
	public float getBarRight() {
		return barLeft+barWidth;
	}
	public float getBarWidth() {
		return barWidth;
	}
	public void setBarHeight(float barHeight) {
		this.barHeight = barHeight;
	}
	public void setBarLeft(float barLeft) {
		this.barLeft = barLeft;
	}
	
	public void setBarTop(float barTop) {
		this.barTop = barTop;
	}
	public void setBarWidth(float barWidth) {
		this.barWidth = barWidth;
	}
	
	public void onDraw()
	{



		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.FILL);

		canvas.drawRect(barLeft,barTop,barLeft+barWidth,barTop+barHeight, paint);

	}

	public void run() {

	}
}

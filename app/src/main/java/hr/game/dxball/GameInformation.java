package hr.game.dxball;

import android.graphics.Canvas;
import android.graphics.Paint;

public class GameInformation {
	
	Canvas canvas;
	
	Paint paint=new Paint();
	private int life,level,score;
	public GameInformation(Canvas canvas,int life,int level,int score) {
		// TODO Auto-generated constructor stub
		
		this.canvas=canvas;
		
		this.life=life;
		this.level=level;
		this.score=score;
	}
	
	public int getLife() {
		return life;
	}
	public int getLevel() {
		return level;
	}
	public int getScore() {
		return score;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setScore(int score) {
		this.score = score;
	}
	

	public void onDraw()
	{
		canvas.drawText("Life:  "+this.life, canvas.getWidth()-canvas.getWidth()+20, 40, paint);
		
		canvas.drawText("Score:  "+this.score, canvas.getWidth()/2-100, 40, paint);

		canvas.drawText("Level:  "+this.level, canvas.getWidth()-200, 40, paint);
		paint.setTextSize(40f);
	}
}

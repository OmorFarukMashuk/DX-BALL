package hr.game.dxball;

import static java.lang.Math.abs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.media.MediaPlayer;
import android.util.Log;

public class DxBallBall extends Thread {
	
	Paint paint=new Paint();
	Canvas canvas;
	//float xx=1;
	
	
	float x=0,y=0,dx=+6,dy=-6;
	float startx,starty;

	private float ballRadius;
	
	private float brickx;
	private float bricky;
	
	private int num_of_brick;
	
	float h,w;
	//final Handler handler = new Handler();
	
	GameInformation gameInformation;
	//private Thread thisThread;
	DxBallBrickInfo[] brick=new DxBallBrickInfo[200];
	
	Context context;
	public DxBallBall(Context context, Canvas temp,float ballRadius, float x, float y, float h, float w, GameInformation gameInformation, DxBallBrickInfo[] temp_brick, float brickx, float bricky) {
		// TODO Auto-generated constructor stub


		this.context=context;
		this.canvas=temp;
		this.ballRadius=ballRadius;
		
		this.x=x;
		this.y=y;
		this.h=h;
		this.w=w;
		this.startx=x;
		this.starty=y;
		this.gameInformation=gameInformation;
		this.num_of_brick=temp_brick.length;
		this.brickx=brickx;
		this.bricky=bricky;

		System.arraycopy(temp_brick, 0, this.brick, 0, temp_brick.length);



		//brick_initialize(temp_brick);
	}



	

	public void draw_rect()
	{
		//canvas.drawRect(100, 100, 200, 200, paint); // x1=100 , y1=100 , x2=200 , y2=200


		int remain=1;
		int tempRemain=0;
		for(int i=0;i<num_of_brick;i++)
		{

			if(i%10==0)
			{
				int temp=remain;
				remain=tempRemain;
				tempRemain=temp;

			}


			if(i%2==remain)
			{
				paint.setColor(Color.LTGRAY);  // what will be the color of painting

				//paint.setColor(Color.BLUE);
				paint.setStyle(Style.FILL); //
			}
			else
			{
				paint.setColor(Color.LTGRAY);  // what will be the color of painting
				//paint.setColor(Color.GREEN);
				paint.setStyle(Style.FILL); //
			}



			if(brick[i].getFlag())
			{
				canvas.drawRect(brick[i].getx(),brick[i].gety(),brick[i].getxx(),brick[i].getyy(), paint); // x1=100 , y1=100 , x2=200 , y2=200
			}

		}
		//canvas.drawRect(100, 100, 200, 200, paint); // x1=100 , y1=100 , x2=200 , y2=200
	}
	
	Boolean intersects(float circlex,float circley,float rectx,float recty)
	{
		Log.d("circle info :  ",Float.toString(circlex)+"   "+Float.toString(circley));
		Log.d("rectangle info :  ",Float.toString(rectx)+"   "+Float.toString(recty));
		
		float circleDistancex=abs(circlex - rectx);
		float circleDistancey=abs(circley - recty);
		
		
		
	    if(circleDistancex>(brickx/2+ballRadius)){return false;}

	    if(circleDistancey>(bricky/2+ballRadius)){return false;}
	    
	    if(circleDistancex<=(brickx/2)){return true;}
	    
	    if(circleDistancey<=(bricky/2)){return true;}
	    
	    float cornerDistance=((circleDistancex-brickx/2)* (circleDistancex-brickx/2))+
				((circleDistancey-bricky/2)*(circleDistancey-bricky/2));

	    return (cornerDistance<=(ballRadius*ballRadius));
	}
	
	
	public void collision_detection()
	{
		for(int i=0;i<num_of_brick;i++)
			{


				if(intersects(x,y,brick[i].getx()+(brickx/2), brick[i].gety()+(bricky/2)))
				{
					//Log.d("collision found ",Float.toString(x)+"   "+Float.toString(y)+"   "+Float.toString(brick[i].getx()+(brickx/2))+"   "+Float.toString(brick[i].gety()+(bricky/2)));
					
					if(brick[i].getFlag())
					{
						brick[i].setFlagFalse();
						ball_new_track(i);
						gameInformation.setScore(gameInformation.getScore()+5);
					}
					
				}
				
			}
		
	}
	
	

	
	protected void calculateNextPos(Canvas canvas,DxBallBar mainBar){

		if(x<=ballRadius)
		{
			dx=-dx;
		}
		else if((y+ballRadius>=mainBar.getBarTop()))
		{
			if((x>=mainBar.getBarLeft()&&x<=mainBar.getBarRight()))
			{
				dy=-dy;
			}
			else
			{

				gameInformation.setLife(gameInformation.getLife()-1);
				if(gameInformation.getLife()<0){
					StartGameOverActivity();
				}
				this.x=(mainBar.getBarLeft()+mainBar.getBarRight())/2;
				this.y=mainBar.getBarTop()-ballRadius/2-ballRadius;
				dx=+6;
				dy=-6;
				return ;
				//dx=+4;
				//dy=-4;
			}

		}
		else if(x+ballRadius>=w)
		{
			dx=-dx;

		}

		else if(y<=ballRadius)
		{
			dy=-dy;

		}


		y+=dy;
		x+=dx;

	}

	public void StartGameOverActivity(){

		Log.d("score:",String.valueOf(gameInformation.getScore()));
		GameOverActivity go = new GameOverActivity(gameInformation.getScore());

		Intent i = new Intent(context, go.getClass());
		context.startActivity(i);

		//startActivity(i);

	}

	public void ball_new_track(int index)
	{
		if (((y - ballRadius) <=brick[index].getyy()) && ((y +ballRadius) >= brick[index].gety()) && ((x) >= brick[index].getx()) && ((x) <= brick[index].getxx())) {

			dy=-dy;
		}
		else if(((y) <= brick[index].getyy()) && ((y) >= brick[index].gety()) && ((x + ballRadius) >= brick[index].getx()) && ((x -ballRadius) <= brick[index].getxx())) {

			dx=-dx;
		}


	}



	@Override
	public void run() {

		if(GameCanvas.stgOverflg){
			paint.setColor(Color.WHITE);
			GameCanvas.stgOverflg=false;

		}

		else {
			paint.setColor(Color.DKGRAY);
			paint.setStyle(Style.FILL);
			canvas.drawCircle(x,y, ballRadius, paint); // tell to draw circle in the canvas using paint object property


		}


	}
	public void onDraw(DxBallBar mainBar)
	{

		calculateNextPos(canvas,mainBar);

		//draw ball
		run();

		new Thread(new Runnable() {
			public void run() {
				collision_detection();
			}
		}).start();


		draw_rect();

	}

}

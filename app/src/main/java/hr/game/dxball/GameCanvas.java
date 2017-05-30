package hr.game.dxball;

import android.content.Context;
import static java.lang.Math.abs;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameCanvas extends View {

	//public boolean BGMusic;
	Paint paint;

	float x,y;

	float h,w;
	boolean firstTime=true;

	Canvas canvas;
	
	float mainBarWidth=280;

	public static boolean stgOverflg=false;



	//mashuk
	public static int stageNo;
	public static int num_of_brick;
	//mashuk

	private DxBallBrickInfo[] brick=new DxBallBrickInfo[num_of_brick];

	private float brickx;
	private float bricky=120;   //hight of brick

	DxBallBall ball;
	DxBallBar Bar;
	
	GameInformation gameInformation;

	Context context;


	
	protected void onDraw(Canvas canvas) {// here canvas is the place (mobile screen ) where object should be painted

		final float ballRadius=18;

		if(firstTime)
		{
			firstTime=false;
			w=canvas.getWidth();
			h=canvas.getHeight();
			this.canvas=canvas;

			x=w/2;
			//y=h-25-ballRadius/2-ballRadius;
			y=(h-ballRadius*2)-ballRadius/2-ballRadius;
			

			Log.d("w :  ",Float.toString(w));
			Log.d("h :  ",Float.toString(h)); 
			
			
			Log.d("x :  ",Float.toString(x));
			Log.d("y :  ",Float.toString(y));

			Log.d("w/num_of_brick :  ",Float.toString(w/num_of_brick));

			brickx=w/10;     										//width of brick,10 brick per row

			initialize_brick();
			
			
			gameInformation=new GameInformation(canvas,2,stageNo,0);


			// ball initialization
			ball=new DxBallBall(getContext(),canvas,ballRadius,x,y,h,w,gameInformation,brick,brickx,bricky);


			// this code is for bar initialization
			Bar=new DxBallBar(canvas,h-36,w/2-mainBarWidth/2,mainBarWidth,ballRadius*2);


		}

		//mash
		if(stageNo==1 && isStageOver()) {
			StartUnlockAcivity();



		}
		else if(stageNo==2 && isStageOver()){

			ball.StartGameOverActivity();

		}
		//mash

		canvas.drawRGB(255, 255, 255); // tells to set canvas color

		ball.onDraw(Bar);
		Bar.onDraw();
		gameInformation.onDraw();

		invalidate();
	}



	//mash
	public boolean isStageOver(){



		for(int i=0; i<num_of_brick;i++){
			if(brick[i].flag){
				return false;
			}
		}
		stgOverflg=true;
		return true;
	}

	public void StartUnlockAcivity(){

		Intent i = new Intent(getContext(),UnlockActivity.class);
		super.getContext().startActivity(i);
		//startActivity(i);



	}
	//mash
	
    @Override
    public boolean onTouchEvent(MotionEvent event) {

		float touchleft = event.getX();


					if(touchleft>=(Bar.getBarLeft()+Bar.getBarHeight()/2)){
						new Thread(new Runnable() {
							public void run(){
								if(Bar.getBarLeft()+Bar.getBarWidth()+15<w)
								{
									Bar.setBarLeft(Bar.getBarLeft()+15);
								}

							}
						}).start();





					}
					if(touchleft<(Bar.getBarLeft()+Bar.getBarHeight()/2)){

						new Thread(new Runnable() {
							public void run(){
								if(Bar.getBarLeft()-15>0)
								{
									Bar.setBarLeft(Bar.getBarLeft()-15);
								}

							}
						}).start();


					}

					return true;

    	
    	//Log.d(tag, msg)
		
		//canvas.drawText(String.valueOf(touchx),canvas.getWidth()/2-60,40,paint);


    }
    
	
	public GameCanvas(Context context) {
		super(context);
		paint = new Paint();
		
	}

	
	public void initialize_brick()
	{
		

		float[] x1=new float[num_of_brick]; 			//brick left
		float[] y1=new float[num_of_brick];				//brick top
		float[] x2=new float[num_of_brick];				//brick right
		float[] y2=new float[num_of_brick];				//brick bottom


		x1[0]=0+4;
		y1[0]=h/4;
		
		
		Log.d("x1 :  ",Float.toString(x1[0]));
		Log.d("y1 :  ",Float.toString(y1[0]));

		for(int i=1;i<num_of_brick;i++)
		{
			if(i%10==0){
				x1[i]=0+4;
				y1[i]=y1[i-1]+bricky;

				continue;
			}
			x1[i]=x1[i-1]+brickx;
			y1[i]=y1[i-1];
		}




		for(int i=0;i<num_of_brick;i++)
		{
			x2[i]=x1[i]+brickx-4;
			y2[i]=y1[i]+bricky-4;
		}

		
		for(int i=0;i<num_of_brick;i++)
		{
			Log.d(Integer.toString(i)+"    brick info:  ",Float.toString(x1[i])+"   "+Float.toString(y1[i])+"    "+Float.toString(x2[i])+"    "+Float.toString(y2[i]));
			
			brick[i]=new DxBallBrickInfo(x1[i],y1[i],x2[i],y2[i]);
		}


	}
	

}

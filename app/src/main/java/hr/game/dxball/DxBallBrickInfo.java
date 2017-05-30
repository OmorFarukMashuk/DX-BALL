package hr.game.dxball;

public class DxBallBrickInfo {
	private float x,y,xx,yy;
	public Boolean flag;
	
	public DxBallBrickInfo(float x, float y, float xx, float yy) {
		// TODO Auto-generated constructor stub
		
		this.x=x;
		this.y=y;
		this.xx=xx;
		this.yy=yy;
		this.flag=true;
	}
	public float getx()
	{
		return x;
	}
	public float gety()
	{
		return y;
	}
	public float getxx()
	{
		return xx;
	}
	public float getyy()
	{
		return yy;
	}
	public void setFlagFalse()
	{
		this.flag=false;
	}
	public Boolean getFlag()
	{
		return this.flag;
	}
}

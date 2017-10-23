package local.maps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import local.maps.model.IFLocation;

public class ChartMap extends LocalMap{

	public ChartMap(int wCount, int hCount) {
		super(wCount, hCount);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void paint(Graphics g)
	{
		super.paint(g);

		g.drawString(String.format("%3.6f", mouseLocationX)+",  "+String.format("%3.6f", mouseLocationY), this.getWidth()-200, this.getHeight()-50);
	}

	protected void drawGird(Graphics g)
	{

		g.setColor(Color.lightGray);


		for(int i=0;i<=wGapCount;i++)
		{
			g.drawLine((int) (startX+i*getWRate()), endY, (int) (startX+i*getWRate()), startY);
			g.drawString(i+"", (int) (startX+i*getWRate()), startY);
		}

		//가로 줄


		for(int i=hGapCount,count=0;i>=0;i--,count++)
		{
			g.drawLine(startX, (int) (i*getHRate()+endY), endX, (int) (i*getHRate()+endY));
			g.drawString(count+"", startX, (int) (getHRate()*i+endY));
		}

		//센터 그리드	
	}
	public void update()
	{
		
		centerX = this.getWidth()/2;	
		
		centerY = this.getHeight()/2;
		
		wGap = (this.getWidth()-insect.x*2)/wGapCount;
		
		hGap = (this.getHeight()-insect.y*2)/hGapCount;
		
		startX = (int) (centerX-wGap*wGapCount/2*getRate());
		
		endX = (int) (centerX+wGap*wGapCount/2*getRate());

		endY = (int) (centerY-hGap*wGapCount/2*getRate());

		startY = (int) (centerY+hGap*wGapCount/2*getRate());
		
		Iterator<IFLocation> iter = locationList.iterator();

		while(iter.hasNext())
		{
			iter.next().update(this);
		}
	}

	@Override
	public int getXOnMap(double locationX) {

		return (int) (startX+locationX*this.getWRate());
	}

	@Override
	public int getYOnMap(double locationY) {
		// TODO Auto-generated method stub
		return (int) (startY-locationY*this.getHRate());
	}

	@Override
	public void downScale() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upScale() {
		// TODO Auto-generated method stub
		
	}

}

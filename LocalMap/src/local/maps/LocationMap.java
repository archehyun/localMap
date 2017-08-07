package local.maps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

import local.maps.model.IFLocation;

public class LocationMap extends LocalMap{

	public LocationMap() {
		super(180, 90);
		// TODO Auto-generated constructor stub
	}
	private static final Color gridColor = Color.lightGray;
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
		super.drawGird(g);
		
		int w = this.getSize().width;

		int h = this.getSize().height;
		
		
		g.setColor(gridColor);
		
		//
		for(int i=1;i<=wGapCount;i++)
		{
			g.drawLine((int) (startX+i*getWRate()), upEndY, (int) (startX+i*getWRate()), startY);
			g.drawLine((int) (startX+i*getWRate()), startY, (int) (startX+i*getWRate()), downEndY);
			g.drawString(i+"",(int) (startX+i*getWRate()), this.getHeight()-25);
		}
		// 세로 west
		for(int i=1;i<=wGapCount;i++)
		{
			g.drawLine((int) (startX-i*getWRate()), upEndY, (int) (startX-i*getWRate()), startY);
			g.drawLine((int) (startX-i*getWRate()), startY, (int) (startX-i*getWRate()), downEndY);
			g.drawString(i+"",(int) (startX-i*getWRate()),  this.getHeight()-25);
		}
		
		//가로 north
		for(int i=1;i<=hGapCount;i++)
		{
			g.drawLine(startX, (int) (startY+i*getHRate()),rightEndX , (int) (startY+i*getHRate()));
			g.drawLine(startX, (int) (startY+i*getHRate()),leftEndX , (int) (startY+i*getHRate()));
			g.drawString(i+"", 25, (int) (startY-i*getHRate()));
		}
		
		// 가로 south
		for(int i=1;i<=hGapCount;i++)
		{
			g.drawLine(startX, (int) (startY-i*getHRate()),rightEndX , (int) (startY-i*getHRate()));
			g.drawLine(startX, (int) (startY-i*getHRate()),leftEndX , (int) (startY-i*getHRate()));
			g.drawString(i+"", 25, (int) (startY+i*getHRate()));
		}
		
		g.setColor(Color.black);

		g.drawLine(startX, insect.y, startX, h-insect.y);

		g.drawLine(insect.x, startY, w-insect.x, startY);
		
		g.setColor(Color.red);

		g.drawLine(centerX, insect.y, centerX, h-insect.y);

		g.drawLine(insect.x, centerY, w-insect.x, centerY);
		
	}
	
	private int leftEndX;
	
	private int rightEndX;
	
	private int upEndY;
	
	private int downEndY;
	
	public void update()
	{
		centerX = this.getWidth()/2;	
		
		centerY = this.getHeight()/2;
		
		wGap = (centerX-insect.x)/wGapCount;
		
		hGap = (centerY-insect.y)/hGapCount;
		
		startX = (int) (centerX-centerLocation.getLocationX()*getWRate());
		
		startY = (int) (centerY+centerLocation.getLocationY()*getHRate());	
		
		rightEndX = (int) (startX+wGap*wGapCount*getRate());
		
		leftEndX = (int) (startX-wGap*wGapCount*getRate());

		upEndY = (int) (startY-hGap*hGapCount*getRate());

		downEndY = (int) (startY+hGap*hGapCount*getRate());
		
		mouseLocationX  = (mousePoint.x-startX)/this.getWRate();
		
		mouseLocationY  = (startY-mousePoint.y)/this.getHRate();
		
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
		return (int) (startY-locationY*this.getHRate());
		
	}
	public void setMousePoint(Point mousePoint) {
		super.setMousePoint(mousePoint);
		Iterator<IFLocation> iter = locationList.iterator();

		while(iter.hasNext())
		{
			IFLocation item= iter.next();
			item.setLabelView(item.isEnter(mousePoint));
					
		}
	}

}

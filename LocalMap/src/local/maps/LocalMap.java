package local.maps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import local.maps.model.IFLocation;
import local.maps.model.Location;
import local.maps.model.Marker;

public class LocalMap extends Component implements Runnable{
	
	private double MAX_RATE=2.0;
	
	private double MIN_RATE=0.1;
	
	private double scaleUnit=0.01; 
	
	public LocalMap() {
		
		centerLocation = new Location(this.getWidth()/2, this.getHeight()/2);
		new Thread(this).start();
	}
	
	private double rate=2.0;


	public double getRate() {
		return rate;
	}

	Point mousePoint = new Point();

	public void setMousePoint(Point mousePoint) {
		this.mousePoint = mousePoint;
	}

	Point insect = new Point(10, 10);

	private int centerX; //화면 가운데 x

	public int getCenterX() {
		return centerX;
	}
	public int getCenterY() {
		return centerY;
	}


	private int centerY; //화면 가운데 Y
	
	private double mouseLocationX;
	
	private double mouseLocationY;

	private	int wGap;

	private	int hGap;
	
	private	int wGapCount=180;

	private	int hGapCount=90;

	private static final Color gridColor = Color.lightGray;

	/**
	 * 
	 */

	List<Marker> markerList = new LinkedList<Marker>();
	
	List<IFLocation> locationList = new LinkedList<IFLocation>();

	
	private Location centerLocation;
	
	private Location mouseLocation = new Location(0, 0);

	private static final long serialVersionUID = 1L;

	public void addMarker(Marker marker)
	{
		locationList.add(marker);
		
		markerList.add(marker);
	}
	public void setCenterLocation(Location centerLocation)
	{
		this.centerLocation = centerLocation;
	}

	public void paint(Graphics g)
	{
		this.drawGird(g);

		drawLocation(g);
		
		drawMarker(g);		

		g.drawString(String.format("%3.6f", mouseLocationX)+",  "+String.format("%3.6f", mouseLocationY), this.getWidth()-200, this.getHeight()-50);
	}
	private void drawLocation(Graphics g) {
		
		Iterator<IFLocation> iter = locationList.iterator();
		
		while(iter.hasNext())
		{
			IFLocation marker = iter.next();
			marker.draw(g);
		}
	}
	private void drawMarker(Graphics g) {

		Graphics2D gd = (Graphics2D) g;
		
		Iterator<Marker> iter = markerList.iterator();
		
		while(iter.hasNext())
		{
			Marker marker = iter.next();
			marker.draw(g);
			
		}		
	}
	
	public double getWRate() {
		return wGap*rate;
	}
	public double getHRate() {
		return hGap*rate;
	}
	private void drawGird(Graphics g)
	{
		int w = this.getSize().width;

		int h = this.getSize().height;
		
		g.setColor(Color.black);

		g.drawRect(insect.x, insect.y, w-insect.x*2, h-insect.y*2);
		
		g.setColor(gridColor);


		for(int i=1;i<=wGapCount;i++)
		{
			g.drawLine(wGap*i+centerX, insect.y, wGap*i+centerX, h-insect.y);
			g.drawString(i+"", wGap*i+centerX, h-insect.y);
		}
		for(int i=1;i<=wGapCount;i++)
		{
			g.drawLine(centerX-(wGap*i), insect.y, centerX-wGap*i, h-insect.y);
		}
		for(int i=1;i<=hGapCount;i++)
		{
			g.drawLine(insect.x, i*hGap+centerY, w-insect.x, i*hGap+centerY);
		}
		for(int i=1;i<=hGapCount;i++)
		{
			g.drawLine(insect.x, centerY-i*hGap, w-insect.x, centerY-i*hGap);
		}	
		
		g.setColor(Color.red);

		g.drawLine(centerX, insect.y, centerX, h-insect.y);

		g.drawLine(insect.x, centerY, w-insect.x, centerY);
		
		//센터 그리드	
		
		
		int centerW = (int) (centerX+centerLocation.getX()*this.getWRate());

		int centerH = (int) (centerY-centerLocation.getY()*this.getHRate());		
		
		g.setColor(Color.black);

		g.drawLine(centerW, insect.y, centerW, h-insect.y);

		g.drawLine(insect.x, centerH, w-insect.x, centerH);

	}
	private void update()
	{
		int w = this.getSize().width;

		int h = this.getSize().height;
		
		centerX = this.getWidth()/2;
		
		centerY = this.getHeight()/2;
		
		wGap = (int) (((w/2-insect.x)/(wGapCount))/this.rate);
		
		hGap = (int) ((h/2-insect.y)/(hGapCount)/this.rate);
		
		mouseLocationX = (mousePoint.x-centerX)/this.getWRate()-centerLocation.getLocationX() ;
		
		mouseLocationY = -(mousePoint.y-centerY)/this.getHRate()-centerLocation.getLocationY() ;
		
		centerLocation.update(this);
		
		Iterator<IFLocation> iter = locationList.iterator();
		
		while(iter.hasNext())
		{
			iter.next().update(this);
		}
	}
	@Override
	public void run() {
		while(true)
		{
			try {

				update();
				
				repaint();

				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public void addLocation(IFLocation location) {
		
		this.locationList.add(location);
		
	}
	public void upScale() {
		if(rate<MAX_RATE)
		{
			rate+=scaleUnit;
		}
	}
	public void downScale() {
		if(rate>MIN_RATE)
		{
			rate-=scaleUnit;
		}
	}
	public Location getcenterLocation() {
		// TODO Auto-generated method stub
		return centerLocation;
	}
	public List<IFLocation> getLocatonList() {
		// TODO Auto-generated method stub
		return locationList;
	}



}


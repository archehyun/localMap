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
import local.maps.model.LatLng;
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
	
	private double rate=1.0;


	Point mousePoint = new Point();

	public void setMousePoint(Point mousePoint) {
		this.mousePoint = mousePoint;
	}

	Point insect = new Point(10, 10);

	private int centerX;

	public int getCenterX() {
		return centerX;
	}
	public int getCenterY() {
		return centerY;
	}


	private int centerY;
	
	private int locationX;
	
	private int locationY;

	private	int wGap;

	private	int hGap;
	
	private	int wGapCount=20;

	private	int hGapCount=20;

	private static final Color gridColor = Color.lightGray;

	/**
	 * 
	 */

	List<Marker> markerList = new LinkedList<Marker>();
	
	List<IFLocation> locationList = new LinkedList<IFLocation>();

	private LatLng centerLatLng;
	
	private Location centerLocation;
	
	private Location mouseLocation = new Location(0, 0);

	private static final long serialVersionUID = 1L;

	public void setCenterLatLng(LatLng centerLatLng)
	{
		this.centerLatLng = centerLatLng;
	}
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

		g.drawString(locationX+","+locationY, this.getWidth()-50, this.getHeight()-50);
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
		
		wGap = (w/2-insect.x)/(wGapCount);
		
		hGap = (h/2-insect.y)/(hGapCount);	


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
		

		int centerW = (int) (centerX+centerLocation.getX()*this.getWRate());

		int centerH = (int) (centerY+centerLocation.getY()*this.getHRate());
		
		g.setColor(Color.black);

		g.drawLine(centerW, insect.y, centerW, h-insect.y);

		g.drawLine(insect.x, centerH, w-insect.x, centerH);


	}
	private void update()
	{
		centerX = this.getWidth()/2;
		
		centerY = this.getHeight()/2;
		
		centerLocation.update(this);
		
		System.out.println("center:"+centerLocation.getLocationX()+","+ centerLocation.getLocationY());
		
		locationX = mousePoint.x-centerX ;
		
		locationY = -mousePoint.y+centerY ;
		
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



}


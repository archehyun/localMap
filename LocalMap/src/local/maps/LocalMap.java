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

/**
 * LocalMap 최상위 맵
 * @author archehyun
 *
 */
public abstract class LocalMap extends Component {
	
	
	protected int step=1;
	
	protected int startX;
	
	protected int endX;
	
	protected int startY;
	
	protected int endY;
	
	protected int MAX_STEP=28;
	
	protected int MIN_STEP=1;
	
	private double majorScaleUnit=0.1;
	
	private double minorScaleUnit=0.01;
	
	public abstract void downScale();
	public abstract void upScale();
	
	protected double rate=1;


	public double getRate() {
		return rate;
	}

	Point mousePoint = new Point();

	public void setMousePoint(Point mousePoint) {
		this.mousePoint = mousePoint;
	}
	
	public void setStep(int step)
	{
		this.step =step;
	}

	protected Point insect = new Point(10, 10);

	protected int centerX; //화면 가운데 x

	public int getCenterX() {
		return centerX;
	}
	public int getCenterY() {
		return centerY;
	}


	protected int centerY; //화면 가운데 Y
	
	protected double mouseLocationX;
	
	protected double mouseLocationY;

	protected	int wGap;

	protected	int hGap;
	
	protected int wGapCount;

	protected int hGapCount;
	protected void updateDrawList()
	{
		centerLocation.update(this);
		
		Iterator<IFLocation> iter = locationList.iterator();
		
		while(iter.hasNext())
		{
			iter.next().update(this);
		}
		Iterator<IFLocation> overayiter = overayList.iterator();
		
		while(overayiter.hasNext())
		{
			overayiter.next().update(this);
		}
	}
	

	/**
	 * 
	 */

	List<Marker> markerList = new LinkedList<Marker>();
	
	List<IFLocation> locationList = new LinkedList<IFLocation>();
	
	List<IFLocation> overayList = new LinkedList<IFLocation>();
	
	protected Location centerLocation;
	
	private Location mouseLocation = new Location(0, 0);	

	private static final long serialVersionUID = 1L;
	

	public LocalMap(int wCount, int hCount) {
		this.wGapCount = wCount;
		this.hGapCount = hCount;
		centerLocation = new Location(0, 0);
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
		g.setColor(new Color(163,204,255));
		
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		this.drawGird(g);

		drawOveray(g);
		
		drawLocation(g);
		
		drawMarker(g);
		
	}
	
	private void drawOveray(Graphics g) {
		
		Iterator<IFLocation> iter = overayList.iterator();
		
		while(iter.hasNext())
		{
			IFLocation marker = iter.next();
			marker.draw(g);
		}
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
	protected void drawGird(Graphics g)
	{
		int w = this.getSize().width;

		int h = this.getSize().height;
		
		g.setColor(Color.black);

		g.drawRect(insect.x, insect.y, w-insect.x*2, h-insect.y*2);
		

		
		//센터 그리드	
	}
	public void update()
	{
		int w = this.getSize().width;

		int h = this.getSize().height;
		
		centerX = this.getWidth()/2;
		
		centerY = this.getHeight()/2;
		
		wGap = (int) (((w-insect.x*2)/(wGapCount))/this.rate);
		
		hGap = (int) ((h-insect.y*2)/(hGapCount)/this.rate);
		
		mouseLocationX = -(centerX-mousePoint.x)/this.getWRate()-centerLocation.getLocationX();
		
		mouseLocationY = (centerY-mousePoint.y)/this.getHRate()-centerLocation.getLocationY() ;
		
		updateDrawList();
	}
	public void addOveray(IFLocation overay)
	{
		this.overayList.add(overay);
	}
	public void addLocation(IFLocation location) {
		
		this.locationList.add(location);		
	}
	
	public void clear()
	{
		locationList.clear();
		
		markerList.clear();
	}

	public Location getcenterLocation() {
		// TODO Auto-generated method stub
		return centerLocation;
	}
	public List<IFLocation> getLocatonList() {
		// TODO Auto-generated method stub
		return locationList;
	}
	
	public abstract int getXOnMap(double locationX); 
	
	public abstract int getYOnMap(double locationY);
	public int getStartX() {
		// TODO Auto-generated method stub
		return startX;
	}
	public int getStartY() {
		// TODO Auto-generated method stub
		return startY;
	}
	public void setRate(double dScale) {
		rate = dScale;
		
	}

}


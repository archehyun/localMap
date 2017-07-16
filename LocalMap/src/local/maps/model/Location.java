package local.maps.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import local.maps.LocalMap;

public class Location extends Point implements IFLocation{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	boolean isLabelView=false;
	
	public boolean isLabelView() {
		return isLabelView;
	}

	public void setLabelView(boolean isLabelView) {
		this.isLabelView = isLabelView;
	}

	private double locationX;
	
	private double locationY;
	
	private String locationName;
	
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public double getLocationX() {
		return locationX;
	}

	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}

	public double getLocationY() {
		return locationY;
	}

	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}

	private int XOnMap;
	
	private int YOnMap;
	
	public int getLocationXOnMap() {
		return XOnMap;
	}

	public void setLocationYOnMap(int locationYOnMap) {
		this.YOnMap = locationYOnMap;
	}
	
	private int width =5;
	
	private int heidth =5;
	
	public Location(int x, int y) {
		
		this.x=x;		
		this.y=y;
		this.locationX =x;
		this.locationY=y;		
		
	}
	
	public Location(double locationX, double locationY) {
		this((int)locationX, (int)locationY);
		this.locationX =locationX;		
		this.locationY =locationY;
		
	}

	@Override
	public void update(LocalMap map) {
		
		XOnMap = map.getXOnMap(this.getLocationX());
		
		YOnMap = map.getYOnMap(this.getLocationY());
		
		//System.out.println(XOnMap+","+YOnMap);
		
	}

	public int getLocationYOnMap() {
		return YOnMap;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		
		g.drawRect(XOnMap-width/2, YOnMap-heidth/2, width, heidth);
		
		if(isLabelView())
		{
			g.drawString("("+locationX+","+locationY+")", XOnMap-width/2, YOnMap-heidth/2);
		}
	}
	
	public boolean isEnter(Point point)
	{
		if(point.x>=XOnMap-width
		 &&point.x<=XOnMap+width
		 &&point.y>=YOnMap-heidth
		 &&point.y<=YOnMap+heidth)
		{
			return true;
		}
		else
		{
			return false;	
		}
		
	}

}

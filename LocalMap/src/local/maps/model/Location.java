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
	private double locationX;
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

	private double locationY;

	private int locationXOnMap;
	
	private int locationYOnMap;
	
	public int getLocationXOnMap() {
		return locationXOnMap;
	}

	public void setLocationYOnMap(int locationYOnMap) {
		this.locationYOnMap = locationYOnMap;
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
		
		Location centerLocation =map.getcenterLocation();
		
		locationXOnMap = (int) (locationX*map.getWRate()+centerLocation.getLocationX()+map.getCenterX()) ;
		
		locationYOnMap = (int) (-locationY*map.getWRate()+centerLocation.getLocationX()+map.getCenterY()) ;
		
	}

	public int getLocationYOnMap() {
		return locationYOnMap;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(locationXOnMap-width/2, locationYOnMap-heidth/2, width, heidth);
	}
	
	public boolean isEnter()
	{
		return false;
	}

}

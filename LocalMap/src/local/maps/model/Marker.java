package local.maps.model;

import java.awt.Graphics;
import java.awt.Point;

import local.maps.LocalMap;

public class Marker implements IFLocation{	
	
	LatLng latLng;
	
	protected double latPointRate;
	
	public double getLatPointRate() {
		return latPointRate;
	}

	public void setLatPointRate(double latPointRate) {
		this.latPointRate = latPointRate;
	}

	public double getLngPointRate() {
		return lngPointRate;
	}

	public void setLngPointRate(double lngPointRate) {
		this.lngPointRate = lngPointRate;
	}

	protected double lngPointRate;
	
	
	public Marker(LatLng latLng) {
		
		this.latLng = latLng;
	}
	
	public int getX()
	{
		return  this.convertLngtoX(latLng.getLongitude()); 
	}
	public int getY()
	{
		return this.convertLattoY(latLng.getLatitude());
	}
	private double convertYtoLat(int y) {
		return (double)y/(double)latPointRate;
	}
	private double convertXtoLng(int x) {

		return (double)x/(double)lngPointRate;
	}
	

	private int convertLngtoX(double lng) {

		return (int)(lng*lngPointRate);
	}

	private int convertLattoY(double lat) {
		return (int)(lat*latPointRate)*-1;
	}

	public void draw(Graphics g) {
		
		g.drawRect(0, 0, 100, 50);
	}

	@Override
	public void update(LocalMap map) {
		
		
	}

	@Override
	public void setLabelView(boolean isLabelView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnter(Point mousePoint) {
		// TODO Auto-generated method stub
		return false;
	}

}

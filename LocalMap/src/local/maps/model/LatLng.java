package local.maps.model;

import java.awt.Graphics;
import java.awt.Point;

import local.maps.LocalMap;

public class LatLng implements IFLocation{
	
	private double latitude; // 위도
	
	private double longitude; // 경도
	
	private int XOnMap;
	
	private int YOnMap;
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public LatLng(double latitude, double longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;		
	}

	@Override
	public void update(LocalMap map) {
		XOnMap = map.getXOnMap(this.getLatitude());
		
		YOnMap = map.getYOnMap(this.getLongitude());
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
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

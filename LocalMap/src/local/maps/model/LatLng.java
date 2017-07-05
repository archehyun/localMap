package local.maps.model;

import java.awt.Graphics;

import local.maps.LocalMap;

public class LatLng implements IFLocation{
	
	private double latitude; // 위도
	
	private double longitude; // 경도
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}

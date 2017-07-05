package local.maps.model;

import java.awt.Graphics;

import local.maps.LocalMap;

public class MBRLocation implements IFLocation{
	
	Location leftUp,leftDown,rightUp,rightDown;
	public MBRLocation(Location leftUp,Location leftDown, Location rightUp, Location rightDown) {
		this.leftUp = leftUp;
		this.leftDown = leftDown;
		this.rightUp = rightUp;
		this.rightDown = rightDown;
		
	}
	@Override
	public void update(LocalMap map) {
		leftUp.update(map);
		leftDown.update(map);
		rightDown.update(map);
		rightUp.update(map);
		
	}
	@Override
	public void draw(Graphics g) {
		leftUp.draw(g);
		leftDown.draw(g);
		rightDown.draw(g);
		rightUp.draw(g);
		
		g.drawLine(leftUp.getLocationXOnMap(), leftUp.getLocationYOnMap(), rightUp.getLocationXOnMap(), rightUp.getLocationYOnMap());
		g.drawLine(rightUp.getLocationXOnMap(), rightUp.getLocationYOnMap(), rightDown.getLocationXOnMap(), rightDown.getLocationYOnMap());
		g.drawLine(rightDown.getLocationXOnMap(), rightDown.getLocationYOnMap(), leftDown.getLocationXOnMap(), leftDown.getLocationYOnMap());
		g.drawLine(leftDown.getLocationXOnMap(), leftDown.getLocationYOnMap(), leftUp.getLocationXOnMap(), leftUp.getLocationYOnMap());
	}
	

}


package local.maps.model;

import java.awt.Graphics;
import java.awt.Point;

import local.maps.LocalMap;

public interface IFLocation {
	public void update(LocalMap map);
	public void draw(Graphics g);
	public void setLabelView(boolean isLabelView);
	public boolean isEnter(Point mousePoint);

}

package local.maps.model;

import java.awt.Graphics;

import local.maps.LocalMap;

public interface IFLocation {
	public void update(LocalMap map);
	public void draw(Graphics g);

}

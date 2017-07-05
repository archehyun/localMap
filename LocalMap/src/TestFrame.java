import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

import local.maps.LocalMap;
import local.maps.model.LatLng;
import local.maps.model.Location;
import local.maps.model.MBRLocation;
import local.maps.model.Marker;

public class TestFrame extends JFrame implements MouseListener, MouseMotionListener, MouseWheelListener{
	private LocalMap localMap;
	public TestFrame() {
		
		localMap = new LocalMap();
		localMap.setCenterLocation(new Location(0,0));
		localMap.addMarker(new Marker(new LatLng(0, 0)));
		localMap.addLocation(new Location(15.8,15));
		localMap.addLocation(new Location(15,-10));
		localMap.addLocation(new Location(-10,-10));
		localMap.addLocation(new Location(-8,10));
		localMap.addLocation(new MBRLocation(new Location(12, 15),new Location(-12, 5),new Location(12, -15),new Location(-12, -15)));
		localMap.addMouseListener(this);
		localMap.addMouseMotionListener(this);
		
		this.addMouseWheelListener(this);
		this.getContentPane().add(localMap);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		TestFrame frame = new TestFrame();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("click");
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("endter");
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		System.out.println("exit");
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("press");
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("mouse drag");
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
		localMap.setMousePoint(e.getPoint());
		
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation()==-1)
		{
			localMap.upScale();
		}
		else
		{
			localMap.downScale();
		}
		
	}

}




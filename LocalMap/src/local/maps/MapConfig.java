package local.maps;

/**
 * 맵 기본 정보를 설정
 * @author archehyun
 *
 */
public class MapConfig implements Runnable{
	Thread thread;
	LocalMap map;
	public MapConfig(LocalMap map) {
		this.map = map;
		thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		while(true)
		{
			try {

				map.update();				
				map.repaint();

				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

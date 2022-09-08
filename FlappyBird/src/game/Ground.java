package game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 地板类
 * @author Administrator
 *
 */
public class Ground {
	public int x;//地板的横坐标
	public int y;//地板的纵坐标
	public BufferedImage img;//地板图片
	//构造方法，初始化地板属性
	public Ground(){ 
		//加载地板图片
		try {
			img = ImageIO.read(
					this.getClass().getResource(
							"/img/ground.png"));
			x = 0;
			y = 500;
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
	}
	//让地板移动
	public void setup() {
		x--;
		if(x<=-108){
			x = 0;
		}
	}
}

package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
/*
 * 柱子类
 * 
 */

public class Colum {
	public int x;
	public int y;
	public BufferedImage img;
	public int width;
	public int height;
	public int distance;//柱子间的间距
	public int gap;//柱子的缝隙
	//随机数生成器
	public Random rd=new Random();
	//index表示屏幕上第几根柱子
	//增加游戏难度可以增加柱子数，同时缩小柱子之间的间距
	public Colum(int index){
		//加载图片
		try {
			img=ImageIO.read(this.getClass().getResource("/img/column.png"));
			width=img.getWidth();
			height=img.getHeight();
			distance=245;
			gap=144;
			//让主子的初始位置处在屏幕的最右端以外
			//index=1huo2;
			x=(index-1)*distance+(432+width/2);
			//随机柱子的纵坐标
			y= rd.nextInt(250)+132;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//让主子移动
	public void setup() {
		// TODO Auto-generated method stub
		//一次移动4，柱子的移动速度和地板的不一样
		//x-=4;
		//和地板一样的移动速度
		x--;
		if(x<=-width){
			x=2*distance-width/2;
			y=rd.nextInt(250)+132;
		}
		
		
	}
	//第二根柱子上下移动的方法
	public void setupY() {
		// TODO Auto-generated method stub
		x--;
		y--;
		if(x<=-width){
			x=2*distance-width/2;
			y=rd.nextInt(250)+132;
		}
	
	

	}
}

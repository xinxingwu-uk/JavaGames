package game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Bird {
	public int x;
	public int y;
	//图片数组，用来存放组成鸟飞行动画的图片
	public BufferedImage[] imgs =
				new BufferedImage[8];
	public BufferedImage img;//鸟的图片
	public int width;
	public int height;
	public int size;
	public int g;//重力加速度
	public double s;//竖直移动位移
	public double speed;//初始速度
	public double t;//时间间隔
	public double alpha;//鸟倾斜的角度
	
	
	public Bird(){
		//加载鸟的图片
		try {
			for(int i=0;i<imgs.length;i++){
				//将图片存入数组中
				imgs[i] = ImageIO.read(
						this.getClass().getResource(
							"/img/"+i+".png"));
			}
			//设置默认显示图片（初始显示的图片）
			img = imgs[0];
			//获取图片的宽度
			width = img.getWidth();
			//获取图片的高度
			height = img.getHeight();
			//设置鸟的横纵坐标
			x = 152;
			y = 280;
			size = 40;
			g=4;//如果想让鸟下落的快，增加
			t=0.25;
			speed=20;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	int index;//表示显示第几张图片
	public void setup() {
		index ++;
		img = imgs[(index/10)%8];
	}
	//竖直上抛运动
	//s=V0*t-1/2*g*t*t
	//v=V0-g*t;
	public void fly() {
		// TODO Auto-generated method stub
		double v0=speed;
		//计算t秒后的速度
		double v=v0-g*t;
		//作为下次移动初始速度
		speed =v;
		//计算垂直位移
		s=v0*t-0.5*g*t*t;
		//让鸟移动位移
		y=y-(int)s;
		//鸟飞行的时候给他一个角度
		alpha=Math.atan(s/20);
		//x显示鸟飞行时的动画
		index ++;
		img = imgs[(index/10)%8];
		
	}
	public void flappy() {
		// TODO Auto-generated method stub
		speed=20;
		
	}
	//是否撞击了竹子
	public boolean hit(Colum c1) {
		// TODO Auto-generated method stub
		int x1=c1.x-c1.width/2-size/2;
		int x2=c1.x+c1.width/2+size/2;
		int y1=c1.y-c1.gap/2+size/2;
		int y2=c1.y+c1.gap/2-size/2;
		if(x>x1&&x<x2){
			if(y>y1&&y<y2){
				return false;
			}
			return true;
			
		}
		return false;
	}
	//是否撞击了地面
	public boolean hit(Ground ground) {
		// TODO Auto-generated method stub
		
		return (y+height/2)>ground.y;
	}
	
}

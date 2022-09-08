package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 面板类：游戏界面
 * @author Administrator
 *
 */
public class BirdPanel extends JPanel{
	//定义第一根柱子
	Colum c1;
	Colum c2;
	//定义背景图片
	BufferedImage background;
	//定义开始图片
	BufferedImage startImage;
	//定义结束图片
	BufferedImage overImage;
	//定义地板
	Ground ground ;
	//定义鸟
	Bird bird ;
	//定义分数
	int score;
	//判断游戏是否结束
	boolean gameover;
	//判断游戏是否开始
	boolean start;
	//构造函数，初始化界面属性
	public BirdPanel(){
		try {
			//创建第一根主子对象
			c1=new Colum(1);
			//第二根柱子
			c2=new Colum(2);
			//创建地板对象
			ground = new Ground();
			//创建鸟对象
			bird = new Bird();
			//加载图片
			background = ImageIO.read(
					this.getClass().getResource(
							"/img/bg.png"));
			startImage = ImageIO.read(
					this.getClass().getResource(
							"/img/start.png"));
			overImage=ImageIO.read(this.getClass().getResource("/img/gameover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void paint(Graphics g) {
		super.paint(g);
		//g.drawString("sss",0,0);
		//画图片(图片，横坐标，纵坐标，null)
		g.drawImage(background, 0, 0, null);
		if(!start){
			//游戏没开始的话就画开始图片
			g.drawImage(startImage,0,0,null);
		}
		
		//先画哪个哪个就在下面
		//画柱子
		g.drawImage(c1.img,c1.x-c1.width/2,c1.y-c1.height/2,null);
		g.drawImage(c2.img,c2.x-c2.width/2,c2.y-c2.height/2,null);
		//获得2D画笔
		Graphics2D g2=(Graphics2D)g;
		//旋转鸟的坐标
		g2.rotate(-bird.alpha,bird.x,bird.y);
		//画鸟
		g.drawImage(bird.img,bird.x-bird.width/2
				,bird.y-bird.height/2, null);
		//换回来
		g2.rotate(bird.alpha,bird.x,bird.y);
		//画地板
		g.drawImage(ground.img, ground.x,
				ground.y, null);
		//设置大小
		Font font=new Font("楷体",Font.BOLD,30);
		g.setFont(font);
		g.setColor(Color.white);
		
		//划分数表
		g.drawString("分数:"+score,10 , 30);
		if(gameover){
			//游戏结束,画结束图片
			g.drawImage(overImage,0,0,null);
		}
		
	}
	//启动，控制游戏
	public void action(){
		//创建鼠标监听器
		MouseListener listener = 
			new MouseAdapter() {
			//鼠标按下去会调用的方法
		
			public void mousePressed(MouseEvent e) {
				if(gameover){
					//如果游戏结束
					//重新启动游戏
					reStart();
				}else{
					//游戏没结束
					//游戏开始
					start = true;
					bird.flappy();
				}
			}
		};
		//将监听器添加到面板中
		addMouseListener(listener);
		//一直刷新界面
		while(true){
			if(!gameover){
				if(start){
					//如果游戏没有结束，并且游戏开始了
					//让地板移动
					ground.setup();
					//让鸟飞
					bird.setup();
					bird.fly();
					//让主子移动
					c1.setup();
					c2.setup();
					//增加难度，让第二根柱子上下移动
					//c2.setupY();
				}
				//穿过一个柱子得一分
				if(bird.x==c1.x||bird.x==c2.x){
					score++;
				}
				
			}
			//如果鸟撞击柱子或者地面，游戏结束
			if(bird.hit(c1)||bird.hit(c2)||bird.hit(ground)){
				gameover=true;//游戏结束
			}
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	//重新启动游戏
	protected void reStart() {
		// TODO Auto-generated method stub
		//创建第一根主子对象
		c1=new Colum(1);
		//第二根柱子
		c2=new Colum(2);
		//创建地板对象
		ground = new Ground();
		//创建鸟对象
		bird = new Bird();
		//将start重新设置为false
		start=false;
		//将gameover设置为false
		gameover=false;
		//将分数清零
		score=0;
	}

}

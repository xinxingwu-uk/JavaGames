package game;

import javax.swing.JFrame;

/**
 * 窗体
 * java中的窗体类：JFrame
 * @author Administrator
 *
 */
public class BirdFrame extends JFrame{
	//定义游戏面板
	BirdPanel panel ;
	//构造方法，初始化窗体属性
	public BirdFrame(){
		//创建面板对象
		panel = new BirdPanel();
		//添加面板
		add(panel);
		//设置标题
		setTitle("飞扬的小鸟");
		//设置大小
		setSize(432,674);
		//设置位置(据中)
		setLocationRelativeTo(null);
		//设置不允许改变窗体大小
		setResizable(false);
		//设置关闭窗口时退出程序
		setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		//设置显示界面
		setVisible(true);
	}
	//程序入口
	public static void main(String[] args) {
		//创建窗体对象
		BirdFrame frame = new BirdFrame();
		frame.panel.action();
	}
}

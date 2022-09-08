import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Crop extends JLabel {
	Icon icon = null;// 图标对象

	public Crop() {
		super();
	}

	public void setIcon(String picture) {
		icon = new ImageIcon(picture);// 获取图片
		setIcon(icon);// 设置组件要显示的图标，用于显示作物的状态
	}

}

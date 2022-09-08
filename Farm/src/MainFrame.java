import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame implements MouseListener {
	int fruitNumber = 0; // 果实
	Farm farm = new Farm(); // 实例化Farm类的对象
	final Crop crop = new Crop();

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public MainFrame() {
		super();
		setTitle("打造自己的开心农场");
		setBounds(500, 200, 466, 276);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		final BackgroundPanel backgroundPanel = new BackgroundPanel();
		Image bk = Toolkit.getDefaultToolkit().getImage("images/plowland.jpg");
		backgroundPanel.setImage(bk);
		backgroundPanel.setBounds(0, 0, 458, 242);
		getContentPane().add(backgroundPanel);

		// final Crop crop = new Crop();
		crop.setBounds(180, 55, 106, 96);
		backgroundPanel.add(crop);

		final JButton button = new JButton();
		// 设置当光标移动到按钮上方时显示的图片
		button.setRolloverIcon(new ImageIcon("images/播种1.png"));
		// 设置为不绘制按钮的边框，当设为false时表示不绘制，默认为绘制。
		button.setBorderPainted(false);
		// 设置按钮的背景为透明，当设为false时表示不绘制，默认为绘制；
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon("images/播种.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String message = farm.seed(crop, "images/seed.png"); // 播种
				if (!message.equals("")) { // 当提示信息不为空时
					JOptionPane.showMessageDialog(null, message); // 弹出提示对话框
				}
			}
		});
		button.setBounds(29, 185, 56, 56);
		backgroundPanel.add(button);

		final JButton button_1 = new JButton();
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setRolloverIcon(new ImageIcon("images/生长1.png"));
		button_1.setIcon(new ImageIcon("images/生长.png"));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String message = farm.grow(crop, "images/grow.png"); // 生长
				if (!message.equals("")) {
					JOptionPane.showMessageDialog(null, message); // 弹出提示对话框
				}
			}
		});
		backgroundPanel.add(button_1);
		button_1.setBounds(114, 185, 56, 56);

		final JButton button_2 = new JButton();
		button_2.setBorderPainted(false);
		button_2.setContentAreaFilled(false);
		button_2.setRolloverIcon(new ImageIcon("images/开花1.png"));
		button_2.setIcon(new ImageIcon("images/开花.png"));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String message = farm.bloom(crop, "images/bloom.png"); // 开花
				if (!message.equals("")) {
					JOptionPane.showMessageDialog(null, message); // 弹出提示对话框
				}
			}
		});
		button_2.setBounds(199, 185, 56, 56);
		backgroundPanel.add(button_2);

		final JButton button_3 = new JButton();
		button_3.setBorderPainted(false);
		button_3.setContentAreaFilled(false);
		button_3.setRolloverIcon(new ImageIcon("images/结果1.png"));
		button_3.setIcon(new ImageIcon("images/结果.png"));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String message = farm.fruit(crop, "images/fruit.png"); // 结果
				if (!message.equals("")) {
					JOptionPane.showMessageDialog(null, message); // 弹出提示对话框
				}
			}
		});
		button_3.setBounds(284, 185, 56, 56);
		backgroundPanel.add(button_3);

		final JLabel storage = new JLabel();
		storage.setHorizontalAlignment(SwingConstants.CENTER);
		storage.setText("您的仓库没有任何果实，快快播放吧！");
		storage.setBounds(80, 15, 253, 28);
		backgroundPanel.add(storage);

		final JButton button_4 = new JButton();
		button_4.setRolloverIcon(new ImageIcon("images/收获1.png"));
		button_4.setIcon(new ImageIcon("images/收获.png"));
		button_4.setContentAreaFilled(false);
		button_4.setBorderPainted(false);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String message = farm.harvest(crop, ""); // 收获
				if (!message.equals("")) {
					JOptionPane.showMessageDialog(null, message); // 弹出提示对话框
				} else {
					fruitNumber++;
					storage.setText("您的仓库现在有" + fruitNumber + "个果实！");
				}
			}
		});
		button_4.setBounds(369, 185, 56, 56);
		backgroundPanel.add(button_4);
		//
	}

	// 单击选择某块地
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();// 获取x坐标
		int y = e.getY();// 获取y坐标
		// JOptionPane.showMessageDialog(null, x+" "+y); // 弹出提示对话框
		// 选择某块地
		crop.setBounds(x - 55, y - 85, 106, 96);
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

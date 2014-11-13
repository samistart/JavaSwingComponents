package Exercise5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseListen {

	private JFrame frame;
	
	private JLabel label;
	
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JRadioButton rdbtnNewRadioButton;
	
	private JCheckBox chckbxNewCheckBox;
	
	private String message = "";
	
	private int x1 = -10;
	private int x2 = -10;
	private int y1, y2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MouseListen window = new MouseListen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MouseListen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
		label = new JLabel(message);
		label.setBounds(51, 225, 400, 27);
		frame.getContentPane().add(label);
		
		btnNewButton = new JButton("Button 1");
		btnNewButton.setBounds(29, 25, 200, 50);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (x1==-10){
					x1 = (int) e.getX();
					y1 = (int) e.getY();
				}
				x2 = (int) frame.getMousePosition().getX();
				y2 = (int) frame.getMousePosition().getY();
				System.out.println(x2 + ", " + y2);
				btnNewButton.setBounds(x2-x1, y2-y1, 200, 50);
				frame.getContentPane().add(btnNewButton);
				message = "Moved button 1 from (" + x1 + ", " + y1 + ") to ("+ x2 + ", " + y2 + ")";
				label.setText(message);
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				x1=-10;
			}
		});
		
		btnNewButton_1 = new JButton("Button 2");
		btnNewButton_1.setBounds(221, 137, 200, 50);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (x1==-10){
					x1 = (int) e.getX();
					y1 = (int) e.getY();
				}
				x2 = (int) frame.getMousePosition().getX();
				y2 = (int) frame.getMousePosition().getY();
				System.out.println(x2 + ", " + y2);
				btnNewButton_1.setBounds(x2-x1, y2-y1, 200, 50);
				frame.getContentPane().add(btnNewButton_1);
				message = "Moved button 2 from (" + x1 + ", " + y1 + ") to ("+ x2 + ", " + y2 + ")";
				label.setText(message);
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				x1=-10;
			}
		});
		
		rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(40, 101, 200, 50);
		frame.getContentPane().add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (x1==-10){
					x1 = (int) e.getX();
					y1 = (int) e.getY();
				}
				x2 = (int) frame.getMousePosition().getX();
				y2 = (int) frame.getMousePosition().getY();
				System.out.println(x2 + ", " + y2);
				rdbtnNewRadioButton.setBounds(x2-x1, y2-y1, 200, 50);
				frame.getContentPane().add(rdbtnNewRadioButton);
				message = "Moved radio button from (" + x1 + ", " + y1 + ") to ("+ x2 + ", " + y2 + ")";
				label.setText(message);
			}
		});
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				x1=-10;
			}
		});
		
		
		chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(252, 84, 128, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		chckbxNewCheckBox.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (x1==-10){
					x1 = (int) e.getX();
					y1 = (int) e.getY();
				}
				x2 = (int) frame.getMousePosition().getX();
				y2 = (int) frame.getMousePosition().getY();
				System.out.println(x2 + ", " + y2);
				chckbxNewCheckBox.setBounds(x2-x1, y2-y1, 200, 50);
				frame.getContentPane().add(chckbxNewCheckBox);
				message = "Moved checkbox from (" + x1 + ", " + y1 + ") to ("+ x2 + ", " + y2 + ")";
				label.setText(message);
			}
		});
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				x1=-10;
			}
		});
		
	}
}

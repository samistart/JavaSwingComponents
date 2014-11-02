package Exercise5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class TypeAndUpdate extends JFrame {
	// Components

	private JPanel contentPane;
	private JTextField textField = new JTextField();
	JLabel lblWriteSomething = new JLabel("Write something");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TypeAndUpdate frame = new TypeAndUpdate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TypeAndUpdate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.setFocusable(true);
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				lblWriteSomething.setText(textField.getText());

			}

			@Override
			public void keyReleased(KeyEvent e) {
				lblWriteSomething.setText(textField.getText());

			}

			@Override
			public void keyPressed(KeyEvent e) {
				lblWriteSomething.setText(textField.getText());

			}
		});

		contentPane.add(textField, BorderLayout.WEST);
		textField.setColumns(10);

		contentPane.add(lblWriteSomething, BorderLayout.CENTER);
	}

}

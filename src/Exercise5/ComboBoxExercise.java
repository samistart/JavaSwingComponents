package Exercise5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class ComboBoxExercise extends JFrame {
	
	String selectedComboItem;
	String[] comboStrings = {"bananas","apples","mangos","oranges","lemons"};
	JComboBox<String> comboBox = new JComboBox<String>(comboStrings);
	JLabel lblChooseAnOption = new JLabel((String) comboBox.getSelectedItem());

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboBoxExercise frame = new ComboBoxExercise();
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
	public ComboBoxExercise() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				lblChooseAnOption.setText((String) comboBox.getSelectedItem());
				
			}
		});
		contentPane.add(comboBox, BorderLayout.CENTER);

		
		contentPane.add(lblChooseAnOption, BorderLayout.SOUTH);
	}

}

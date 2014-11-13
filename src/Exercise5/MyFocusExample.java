package Exercise5;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;

import java.awt.Insets;
import java.util.Vector;

public class MyFocusExample extends JFrame implements FocusListener {

	final static String newline = "\n";

	private JPanel contentPane;
	JTextArea display;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFocusExample frame = new MyFocusExample();
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
	public MyFocusExample() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, 5);
		c.gridx = 0;
		c.gridy = 1;
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { Double.MIN_VALUE, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton button = new JButton("A Button");
		button.addFocusListener(this);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 0;
		contentPane.add(button, gbc_button);

		String comboPrefix = "ComboBox Item #";
        final int numItems = 10;
        Vector<String> vector = new Vector<String>(numItems);
        for (int i = 0; i < numItems; i++) {
            vector.addElement(comboPrefix + i);
        }
		comboBox = new JComboBox(vector);
		comboBox.addFocusListener(this);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		gbc_comboBox.weightx=1.0;
		contentPane.add(comboBox, gbc_comboBox);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.BOTH;
		display = new JTextArea("Focus messages here:" +newline);
		display.setRequestFocusEnabled(false);
		display.addFocusListener(this);
		display.setEditable(false);
		contentPane.add(display,c);
	}

	public void focusGained(FocusEvent e) {
		displayMessage("Focus gained", e);
		System.out.println("Focus Gained"+newline);
	}

	public void focusLost(FocusEvent e) {
		displayMessage("Focus lost", e);
		System.out.println("Focus Lost"+newline);
	}

	void displayMessage(String prefix, FocusEvent e) {
		display.append(prefix
				+ (e.isTemporary() ? " (temporary):" : ":")
				+ e.getComponent().getClass().getName()
				+ "; Opposite component: "
				+ (e.getOppositeComponent() != null ? e.getOppositeComponent()
						.getClass().getName() : "null") + newline);
		display.setCaretPosition(display.getDocument().getLength());
	}

}

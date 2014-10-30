package BlogEntry;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JEditorPane;

public class BlogEntry extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String blogEntry;

	private JPanel contentPane;
	private JEditorPane editorPane;
	private JTextField textField_1;
	private String blogFilePath = "/Users/SamiStart/Github/Tutorial 5/src/BlogEntry/blog.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlogEntry frame = new BlogEntry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String getTime() {
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	private String getDate() {
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	private String returnLastEntry() throws IOException {

		FileReader fR = new FileReader(blogFilePath);
		BufferedReader textReader = new BufferedReader(fR);

		String lastEntry = "";

		try {
			String testString = textReader.readLine();

			while (testString != null) {
				if (testString.contains("Date: ")) {
					lastEntry = "";
				}
				lastEntry += testString + "\n";
				testString = textReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		textReader.close();
		fR.close();

		return lastEntry;
	}

	private void updateTextField() {
		try {
			String lastEntry = returnLastEntry();
			String textFieldString;
			if (lastEntry != "") {
				textFieldString = "Last entry at " + lastEntry;
			} else {
				textFieldString = "This will be your first blog entry";
			}
			textField_1.setText(textFieldString);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public BlogEntry() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		editorPane = new JEditorPane();
		contentPane.add(editorPane, BorderLayout.CENTER);

		JButton btnAddToBlog = new JButton("Add to blog");

		btnAddToBlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blogEntry = editorPane.getText();
				System.out.println("Date: " + getDate() + ", Time:" + getTime()
						+ "\n" + blogEntry + "\n");

				try (PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter(blogFilePath, true)))) {
					out.println("Date: " + getDate() + ", Time: " + getTime()
							+ "\n" + blogEntry + "\n");
					out.flush();
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// update the text field with the latest blog entry
				updateTextField();
			}
		});
		contentPane.add(btnAddToBlog, BorderLayout.EAST);

		textField_1 = new JTextField();
		contentPane.add(textField_1, BorderLayout.NORTH);
		textField_1.setColumns(10);
		updateTextField();

	}

}

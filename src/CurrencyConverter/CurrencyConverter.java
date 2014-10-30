package CurrencyConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class CurrencyConverter extends JFrame {

	double inputAmount;
	String[] currArray = { "GBP", "USD", "JPY", "EUR" }; // indexes for combo
															// buttons: gbp 0,
															// usd 1, jpy 2, eur
															// 3
	double[] exRates = { 1, 1.5, 40, 1.3 };

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboCurrIn;
	private JComboBox comboCurrOut;
	private JLabel lblTo;
	private JLabel lblResult;

	private float getRate(int inputIndex, int outputIndex) throws IOException {

		// Set up a URL
		URL urlExchangeRate = new URL(
				"http://rate-exchange.appspot.com/currency?from="
						+ currArray[inputIndex] + "&to="
						+ currArray[outputIndex]);
		// Set up a URL connection to said URL
		URLConnection u = urlExchangeRate.openConnection();
		// Set up an io reader inside a buffered reader so that it can handle
		// the page not loading immediately. Get input stream from connection
		BufferedReader r = new BufferedReader(new InputStreamReader(
				u.getInputStream()));
		// Read from website and save to string
		String stringFromWebsite = r.readLine();

		// Use a regular expression to match within the string
		/*
		 * Pattern p = Pattern.compile("\"rate\": .*,"); Matcher m =
		 * p.matcher(stringFromWebsite); System.out.println(m.find());
		 * System.out.println(m.group());
		 */

		// get a substring with just the exchange rate in it
		int indexOfStartMinusFour = stringFromWebsite.indexOf("e\": ");
		int indexOfEndPlusOne = stringFromWebsite.indexOf(", \"fr");
		String rate = stringFromWebsite.substring(indexOfStartMinusFour + 4,
				indexOfEndPlusOne - 1);

		// parse to double and then cast to float to get less sig figs
		double dRate = Double.parseDouble(rate);
		float fRate = (float) dRate;
		return fRate;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrencyConverter frame = new CurrencyConverter();
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
	public CurrencyConverter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		comboCurrIn = new JComboBox(currArray);
		panel.add(comboCurrIn);

		lblTo = new JLabel("to");
		panel.add(lblTo);

		comboCurrOut = new JComboBox(currArray);
		panel.add(comboCurrOut);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputAmount = Double.parseDouble(textField.getText());
				System.out.println();
				double result = 0;
				try {
					result = inputAmount
							* getRate(comboCurrIn.getSelectedIndex(),
									comboCurrOut.getSelectedIndex());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblResult.setText(Double.toString(result));
			}
		});
		panel.add(btnConvert);

		lblResult = new JLabel("Result");
		panel.add(lblResult);
	}

}

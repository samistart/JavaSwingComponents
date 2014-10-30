package WhackAMole;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class WhackAMole extends JFrame {

	private Boolean gameRunning = false;
	private int gridSize = 4;
	private Mole[][] moles = new Mole[gridSize][gridSize];
	private int moleRow, moleCol;
	private int score = 0;
	private int intervalMillisecs = 1000;
	private String scoresFilePath = "/Users/SamiStart/Github/Tutorial 5/src/WhackAMole/scores.txt";
	private JPanel gameJPanel = new JPanel();
	Timer myTimer = new Timer();
	TimerTask myTimerTask;
	// The top panel with the score and controls
	JPanel panel = new JPanel();
	private JPanel contentPane;
	JLabel lblScore = new JLabel("Score: " + score);
	String[] difficulties = { "Easy", "Medium", "Difficult" };
	private final JComboBox comboBox = new JComboBox(difficulties);
	private final JButton btnEndGame = new JButton("End Game");
	private final JButton btnStartGame = new JButton("Start Game");

	private void initialiseGameplay() {
		Random r = new Random();
		moleRow = r.nextInt(gridSize);
		moleCol = r.nextInt(gridSize);

		for (int row = 0; row < moles.length; row++) {
			for (int col = 0; col < moles.length; col++) {
				moles[row][col] = new Mole(false);
				Mole currentMole = moles[row][col];
				moles[row][col].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (gameRunning) {
							if (currentMole.getActive()) {
								score += 1;
							} else {
								score -= 1;
							}
							lblScore.setText("Score: " + score);
						}
					
					}
				});
			}
		}

		moles[moleRow][moleCol].setActive(true);

		setButtons();

		intervalMillisecs = 1000 / (comboBox.getSelectedIndex() + 1);

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WhackAMole frame = new WhackAMole();
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
	public WhackAMole() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Centre panel has a 4 by 4 grid for whackamole
		gameJPanel.setLayout(new GridLayout(4, 4));
		contentPane.add(gameJPanel, BorderLayout.CENTER);

		contentPane.add(panel, BorderLayout.NORTH);
		panel.add(lblScore);

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initialiseGameplay();
				timedShuffle();
			}
		});
		panel.add(comboBox);
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try (PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter(scoresFilePath, true)))) {
					out.println("Date: " + getDate() + ", Time: " + getTime()
							+ "\n" +"Score: "+ score + "\n");
					out.flush();
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				gameRunning=false;
				score=0;
				lblScore.setText("Score: " + score);
			}
		});
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameRunning=true;
			}
		});
		
		panel.add(btnStartGame);
		
		panel.add(btnEndGame);

		initialiseGameplay();
		timedShuffle();

	}

	private void shuffle() {
		moles[moleRow][moleCol].setActive(false);

		Random r = new Random();
		moleRow = r.nextInt(gridSize);
		moleCol = r.nextInt(gridSize);

		moles[moleRow][moleCol].setActive(true);

	}

	private void setButtons() {
		gameJPanel.removeAll();
		for (int row = 0; row < moles.length; row++) {
			for (int col = 0; col < moles[row].length; col++) {
				String address = "cell " + row + " " + col;
				gameJPanel.add(moles[row][col], address);
			}
		}
	}

	private void timedShuffle() {

		initialiseGameplay();

		myTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (gameRunning) {
					shuffle();
				}
			}
		}, 0, intervalMillisecs);

	}

}

package WhackAMole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Mole extends JButton {

	private Boolean active = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Mole(Boolean active) {
		if (active) {
			setActiveImage();
		} else {
			setInactiveImage();
		}

	}

	private void setActiveImage() {
		String trollFileLocation = "/Users/SamiStart/Github/Tutorial 5/src/WhackAMole/troll.png";
		ImageIcon trollIcon = new ImageIcon(trollFileLocation);

		this.setIcon(trollIcon);
	}

	private void setInactiveImage() {
		String trollFileLocation = "/Users/SamiStart/Github/Tutorial 5/src/WhackAMole/trollHole.png";
		ImageIcon trollIcon = new ImageIcon(trollFileLocation);

		this.setIcon(trollIcon);
	}

	public void setActive(Boolean b) {
		active = b;
		if (b) {
			setActiveImage();
		} else {
			setInactiveImage();
		}
	}

	public Boolean getActive() {
		return active;
	}

}

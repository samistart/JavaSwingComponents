package DragableComponents;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class MouseListen {
    JFrame frame = new JFrame("DragButton window");
    JButton button = new JButton("Button");
    JCheckBox checkbox = new JCheckBox("Checkbox");
    JRadioButton radioButton = new JRadioButton("Radio Button");
    JLabel label = new JLabel("");

    int x1 = -1, y1;
    
    JComponent[] components = {button, checkbox, radioButton};
    
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
    
    public MouseListen(){
    	frame.setBounds(100, 100, 450, 300);
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		frame.getContentPane().setLayout(null);
 		 
 		label.setBounds(51, 225, 400, 27);
		frame.getContentPane().add(label);
	
 		button.setBounds(150, 25, 200, 50);
 		checkbox.setBounds(29, 150, 200, 50);
		radioButton.setBounds(150, 150, 200, 50);
		 
		for (final JComponent component:components){
			frame.getContentPane().add(component);
			component.addMouseMotionListener(new MouseMotionAdapter() {
				 
				@Override
				public void mouseDragged(MouseEvent E) {
					Point p = SwingUtilities.convertPoint(component, E.getPoint(), frame.getContentPane());
					component.setBounds(p.x, p.y, 200, 50);
					if (x1 == -1){
						x1 = p.x;
						y1 = p.y;
					}
					label.setText("Moved " + ((AbstractButton) component).getText() + " from (" + x1 + ", " + y1 + ") to ("+ p.x + ", " + p.y + ")");
				}
			});
			 
			component.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					x1 = -1;
				}
			});
		}
    }
}
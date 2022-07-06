import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graph extends JPanel {
	
	@Override
	public void paintComponent(Graphics g) {
		int x;
		int y;
		
		x = 0;
		y = 0; 
		
		for (x = -1000; x < 1000; x++) {
			
			y = 3*x*x; 
			
			g.fillRect(x+500, -y+500, 2, 2);
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(new Graph());

	}

}

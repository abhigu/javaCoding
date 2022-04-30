package gui;

import javax.swing.JFrame;

import viruses.Population;
import viruses.Simulation;
import viruses.Variant;

public class WindowGui extends JFrame {
	
	WindowGui(){
		super.setContentPane(new Panel());
		super.setVisible(true);
		super.setSize(600,600);
		
	}

}

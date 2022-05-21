package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import viruses.Population;
import viruses.Simulation;
import viruses.Variant;

public class WindowGui extends JFrame implements MouseListener{
	Panel panel = new Panel(); 
	
	WindowGui(){
		super.setContentPane(panel);
		JMenuBar menu = new JMenuBar();
		super.setJMenuBar(menu);
		super.setVisible(true);
		super.setSize(600,600);
		
		JMenu tools = new JMenu("Tools");
		JMenuItem restart = new JMenuItem("Restart");
		
		restart.addMouseListener(this);
		
		tools.add(restart);
		menu.add(tools);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		panel.run();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

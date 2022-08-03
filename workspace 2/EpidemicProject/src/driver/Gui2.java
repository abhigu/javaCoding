package driver;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataStructures.SimParam;
import viruses.Simulation;

 

//Using what we already know about swing/javafx, as well as recycling old code from the simpler gui package, this class will create a window that will ask for user input for the various different methods in the driver 
//Essentially front end of driver package.

//Things to do after we finish first version of GUI:

//Fix multigraph in Matrix
//Add ability for Matrix to graph Mutation data, like graphing RKSI values
//Make Gui work with multigraph
//Add ability to change what piece of data you are graphing in Gui, including Mutation data, like RKSI values
//Fix bug that causes program to crash after around 4 runs

public class Gui2 {
	int xLimit;
	Simulation sim = new Simulation(new SimParam(0, 0.0, 0.0, 0.0, 0.0), xLimit);
	Matrix matrix = new Matrix(1000, 1500, sim.getData(), 1);
	
	public Gui2(int xLimit) {
		this.xLimit = xLimit;
	}
	
	public void pageSetUp(JPanel panel, JLabel title) {
		panel.setBackground(new Color(106, 150, 163));
		
		title.setBounds(1475, 15, 400, 50);
		title.setFont(new Font("Times New Roman", Font.BOLD, 40));
		panel.add(title);
	}
	
	public void textSetUp(JPanel panel, JTextField[] inputs, String[] names) {
		for(int i = 0; i < inputs.length; i++) {
			inputs[i] = new JTextField();
			inputs[i].setBounds(1600, 125 + (i*150), 100, 25);
			JLabel label = new JLabel(names[i]);
			label.setBounds(1600, 150 + (i*150), 100, 25);
			panel.add(inputs[i]);
			panel.add(label);
		}
		inputs[0].setText("1000");
		inputs[1].setText("5");
		inputs[2].setText("0.1");
		inputs[3].setText("0.9");
		inputs[4].setText("5");
	}
	
	public void submitSetUp(JPanel panel, JTextField[] inputs, JButton submit) {
		submit.setBounds(1612, 850, 75, 30);
		panel.add(submit);
		
		submit.addActionListener((e) -> {
			panel.remove(matrix);
			sim = new Simulation(new SimParam(
				Integer.valueOf(inputs[0].getText()),
				Double.valueOf(inputs[1].getText()),
				Double.valueOf(inputs[2].getText()),
				Double.valueOf(inputs[3].getText()),
				Double.valueOf(inputs[4].getText())
			), xLimit);
			
			sim.epidemic();
			matrix = new Matrix(xLimit + 1, 1500, sim.getData(), 1);
			panel.add(matrix);
			
			matrix.setBounds(0, 0, xLimit + 1, 1500);
			matrix.addData();
			
			panel.revalidate();
			panel.repaint();
		});
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel(null);
		
		JTextField[] inputs = new JTextField[5];
		String[] names = {"Population", "Rate", "Killability", "Stability", "Incubation"};
		
		JButton submit = new JButton("Submit");
		JLabel title = new JLabel("Epidemic Simulation");
		
		int x = 1399;
		Gui2 gui2 = new Gui2(x);
				
		gui2.pageSetUp(panel, title);
		gui2.textSetUp(panel, inputs, names);
		gui2.submitSetUp(panel, inputs, submit);
		
		frame.setVisible(true);
		frame.setSize(2000, 8000);
		frame.setContentPane(panel);
	}
}

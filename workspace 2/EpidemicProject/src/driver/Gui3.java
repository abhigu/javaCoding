package driver;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
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

public class Gui3 {
	int xLimit;
	Simulation sim;
	//Simulation sim = new Simulation(new SimParam(0, 0.0, 0.0, 0.0, 0.0));
	//Matrix matrix = new Matrix(1000, 1500, sim.getData(), 1);
	PlotData plotData;
	SimulationGenerator simGen;
	
	private int frameWidth = 1470;
	private int frameHeight = 900;
	
	public Gui3(int xLimit) {
		this.xLimit = xLimit + 100;
		
		sim = new Simulation(new SimParam(0, 0.0, 0.0, 0.0, 0.0), xLimit); 
		plotData = new PlotData(0, 0, sim.getData(), 1);
		simGen = new SimulationGenerator(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");
	}
	
	public void pageSetUp(JPanel panel, JLabel title) {
		panel.setBackground(new Color(106, 150, 163));
		
		title.setBounds(frameWidth/2, 15, 400, 50);
		title.setFont(new Font("Times New Roman", Font.BOLD, 40));
		panel.add(title);
	}
	
	public void textSetUp(JPanel panel, JTextField[] inputs, String[] names) {
		for(int i = 0; i < inputs.length; i++) {
			inputs[i] = new JTextField();
			inputs[i].setBounds(frameWidth-100, 0 + (i + 1)*((frameHeight-200)/12), 100, 25);
			JLabel label = new JLabel(names[i]);
			label.setBounds(frameWidth-100, 0 + (i + 1)*((frameHeight-200)/12)+25, 100, 25);
			panel.add(inputs[i]);
			panel.add(label);
		}
		inputs[0].setText("1000");
		inputs[1].setText("2000");
		inputs[2].setText("5");
		inputs[3].setText("7");
		inputs[4].setText("0.1");
		inputs[5].setText("0.2");
		inputs[6].setText("0.7");
		inputs[7].setText("0.9");
		inputs[8].setText("5");
		inputs[9].setText("10");
		inputs[10].setText("10");
		inputs[11].setText("Multi-Graph Test");
	}
	
	public void submitSetUp(JPanel panel, JTextField[] inputs, JButton submit) {
		submit.setBounds(frameWidth-100, frameHeight-100, 75, 30);
		panel.add(submit);
		
		submit.addActionListener((e) -> {
			panel.remove(plotData);
			simGen = new SimulationGenerator(
					Integer.valueOf(inputs[0].getText()),
					Integer.valueOf(inputs[1].getText()),
					Double.valueOf(inputs[2].getText()),
					Double.valueOf(inputs[3].getText()),
					Double.valueOf(inputs[4].getText()),
					Double.valueOf(inputs[5].getText()),
					Double.valueOf(inputs[6].getText()),
					Double.valueOf(inputs[7].getText()),
					Double.valueOf(inputs[8].getText()),
					Double.valueOf(inputs[9].getText()),
					Integer.valueOf(inputs[10].getText()),
					inputs[10].getText()
			);
			/*
			sim = new Simulation(new SimParam(
				Integer.valueOf(inputs[0].getText()),
				Double.valueOf(inputs[1].getText()),
				Double.valueOf(inputs[2].getText()),
				Double.valueOf(inputs[3].getText()),
				Double.valueOf(inputs[4].getText())
			), xLimit);
			*/
			/*
			sim = new Simulation(new SimParam(
					Integer.valueOf(inputs[0].getText()),
					Double.valueOf(inputs[1].getText()),
					Double.valueOf(inputs[2].getText()),
					Double.valueOf(inputs[3].getText()),
					Double.valueOf(inputs[4].getText())
			));
			*/
			simGen.series();
			//sim.epidemic();
			//matrix = new Matrix(xLimit + 1, 1500, sim.getData(), 1);
			//matrix = new Matrix(1400, 1500, sim.getData(), 1);
			plotData = new PlotData(frameWidth/4*3, frameHeight, sim.getData(), 1, simGen.getDataList());
			
			panel.add(plotData);
			
			plotData.setBounds(0, 0, frameWidth/4*3, frameHeight);
			//matrix.setBounds(0, 0, 1400, 1500);
			//plotData.updateData(sim.getData());
			plotData.updateData(simGen.getDataList().get(0));
			
			panel.revalidate();
			panel.repaint();
		});
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel(null);
		
		JTextField[] inputs = new JTextField[12];
		String[] names = {"PopulationMin", "PopulationMax", "RateMin", "RateMax", "KillabilityMin", "KillabilityMax", "StabilityMin", "StabilityMax", "IncubationMin", "IncubationMax", "NumberOfTrials", "Name"};
		
		JButton submit = new JButton("Submit");
		JLabel title = new JLabel("Epidemic Simulation");
		
		int x = 750;
		Gui3 gui3 = new Gui3(x);
		
		
		//Gui2 gui2 = new Gui2();
		
		gui3.pageSetUp(panel, title);
		gui3.textSetUp(panel, inputs, names);
		gui3.submitSetUp(panel, inputs, submit);
		
		frame.setVisible(true);
		frame.setSize(gui3.frameWidth, gui3.frameWidth);
		frame.setContentPane(panel);
	}
}

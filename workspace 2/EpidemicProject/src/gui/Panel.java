package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import viruses.Population;
import viruses.Simulation;
import viruses.Variant;



public class Panel extends JPanel{
	private JScrollPane scroll;
	private JLabel label;
	private JLabel label2;
	private JTextField field;
	private JButton button;
	private JTextArea text;
	private JPanel northPanel;
	
	double[] PRKSI = new double[5]; 
	
	private int state;
	
	ActionListener action;
	
	Panel() {
		label = new JLabel("Enter Population: ");
		label.setFont(new Font("Ariel", Font.ITALIC, 50));
		label.setForeground(Color.BLUE);
		
		label2 = new JLabel("           Epidemic Simulation Data");
		label2.setHorizontalAlignment(0);
		label2.setFont(new Font("Ariel", Font.ITALIC, 50));
		label2.setForeground(Color.RED);
		
		field = new JTextField(22);
		field.setFont(new Font("Ariel", Font.PLAIN, 50));
		
		button = new JButton("Submit!");
		button.setFont(new Font("Ariel", Font.BOLD, 50));
		
		text = new JTextArea(10,100);
		
		scroll = new JScrollPane(text);
		
		northPanel = new JPanel(new BorderLayout());
		northPanel.add(label, BorderLayout.WEST);
		northPanel.add(label2, BorderLayout.CENTER);
		
		super.setLayout(new BorderLayout());
		super.add(northPanel, BorderLayout.NORTH);
		super.add(field, BorderLayout.WEST);
		super.add(button, BorderLayout.SOUTH);
		super.add(scroll, BorderLayout.EAST);

		state = 0;
		run();
	} 
	
	public void run() {
		text.setText("");
		label.setText("Enter Population: ");
		state = 0;
			
		action = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					if(state == 0) {
						iterate(); 
						label.setText("Enter infection rate: ");
					} else if(state == 1) {
						iterate();
						label.setText("Enter death rate: "); 
					} else if(state == 2) {
						iterate();
						label.setText("Enter mutation stability: ");
					} else if (state == 3) {
						iterate();
						label.setText("Enter incubation time: "); 
					} else {
						iterate();
						label.setText("Simulation Done"); 
					
						Population population = new Population((int)PRKSI[0]);
						Variant variant = new Variant(PRKSI[1], PRKSI[2], PRKSI[3], PRKSI[4]);
						Simulation sim = new Simulation(population, variant);
						
						for(int i = 0; i < 100; i++) {
							text.append(sim.Print());
							sim.newDay();
						}
						text.append(sim.endData());  
						button.removeActionListener(action);
					}
				} catch(Exception ex) {
					System.out.println("No value inputed, please enter a value.");
				}	
			}
		};
		button.addActionListener(action);
	}
	
	public void iterate() {
		PRKSI[state] = Double.valueOf(field.getText());
		state++;
	}
	
	/*
	public static void main(String[] args) {
		
	}
	
		Panel() {
		label = new JLabel();
		field = new JTextField(10);
		button = new JButton();
		
		super.add(label);
		super.add(field);
		super.add(button);
		
		button.setText("Submit!");
	
		state = 0;
		button.addActionListener((r) -> {
			if(state == 0) {
				getPopulation();
				result = field.getText();
				state++;
			} else if(state == 1) {
				getRate();
				result = field.getText();
				state++;
			} else if(state == 2) {
				getDeath();
				result = field.getText();
				state++;
			} else if (state == 3) {
				getStability();
				result = field.getText();
				state++;
			} else {
				getIncubation();
				result = field.getText();
				state = 0;
			}		
		});
	}
	
	public String getPopulation() {
		label.setText("Enter Population ");
		return result;
	}
	
	public String getRate() {
		label.setText("Enter infection rate ");
		return result;
	}
	
	public String getDeath() {
		label.setText("Enter death rate ");
		return result;
	}
	
	public String getStability() {
		label.setText("Enter mutation stability ");
		return result;
	}
	
	public String getIncubation() {
		label.setText("Enter incubation time");
		return result;
	}
	
	public static void main(String[] args) {
		String test = "50";
		//int i = String.(test);  
	}
	*/
	
	
	/*
	public String getStat(String condition) {	
		label.setText(condition);
		button.setText("Submit!");
		button.addActionListener((r) -> {result = field.getText();
		getRate();});
		return result;
	}
	
	public String getPopulation() {
		return getStat("Enter population ");
	}
	
	public String getRate() {
		return getStat("Enter infection rate ");
	}
	
	public String getDeath() {
		return getStat("Enter death rate ");
	}
	
	public String getStability() {
		return getStat("Enter mutation stability ");
	}
	
	public String getIncubation() {
		return getStat("Enter incubation time");
	}
	
	public void getPRKSI() {
		getPopulation();
		getRate();
		getDeath();
		getStability();
		getIncubation();
	}
	
	
	
	
	
	
	
	
			button.addActionListener((r) -> {
				if(state == 0) {
					iterate(); 
					label.setText("Enter infection rate ");
				} else if(state == 1) {
					iterate();
					label.setText("Enter death rate "); 
				} else if(state == 2) {
					iterate();
					label.setText("Enter mutation stability ");
				} else if (state == 3) {
					iterate();
					label.setText("Enter incubation time");
				} else {
					iterate();
					label.setText("Simulation Done");
				
					Population population = new Population((int)PRKSI[0]);
					Variant variant = new Variant(PRKSI[1], PRKSI[2], PRKSI[3], PRKSI[4]);
					Simulation sim = new Simulation(population, variant);
				
					sim.epidemic();
				
					//System.out.println(sim.first.stringMutations(sim.first)); //simulation does not print mutations at end 
					button.removeActionListener(); //figure out how to remove action listener to stop user from inputting something after simulation ends
				}
			});	
			
	//overriding epidemic() and toString() methods of Simulation here
	@Override
	public void epidemic() {
		while(population.sizeDeadandHealthy() < population.size() && population.infected.size() > 0) {
			newDay();
			if(day > 30) {
				break;
			}
		}
		newDay();
		
		System.out.println(firstVariant.stringMutations(firstVariant));
	}
	
	@Override
	public String Print() {
		String output = "";
		
		output += "\nDay: " + day;
		output += "\nHealthy: " + population.notInfected.size();  
		output += "\nInfected: " + population.infected.size();
		output += "\nDead: " + population.dead.size();
		output += "\nTotal Mutations:" + firstVariant.countMutations(firstVariant);
		
		return output;
	}

	*/
}

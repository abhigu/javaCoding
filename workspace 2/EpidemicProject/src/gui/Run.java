package gui;

import viruses.Simulation;

public class Run {
	public void consoleRun() {
		ConsoleGui gui = new ConsoleGui();
		
		gui.population();
		gui.infectionRate();
		gui.deathRate();
		gui.stabilityRate();
		gui.incubationRate();
		gui.createVariant();
	
		Simulation sim = new Simulation(gui.pop, gui.variant);
	
		gui.typeOfSimulation(sim);
	}
	
	public void windowRun() {
		WindowGui frame = new WindowGui();
	}

	
	public static void main(String args[]) {
		Run run = new Run();
		
		//run.consoleRun();
		run.windowRun();
	}
}

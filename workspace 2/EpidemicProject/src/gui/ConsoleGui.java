package gui;

import java.util.Scanner;

import viruses.Population;
import viruses.Simulation;
import viruses.Variant;

public class ConsoleGui {
	Scanner scn = new Scanner(System.in);
	int populationSize;
	double R;
	double K; 
	double S;
	double I;
	
	Population pop;
	Variant variant;
	
	public void population() {
		System.out.print("Enter the size of the population: ");
		populationSize = scn.nextInt();
		pop = new Population(populationSize);
	}
	
	public void infectionRate() {
		System.out.print("Enter the infection rate (in people infected per person): ");
		R = scn.nextDouble();
	}
	
	public void deathRate() {
		System.out.print("Enter the death rate (in frequency): ");
		K = scn.nextDouble();
	}
	
	public void stabilityRate() {
		System.out.print("Enter the stability (in frequency): ");
		S = scn.nextDouble();
	}
	
	public void incubationRate() {
		System.out.print("Enter the incubation time (in days): ");
		I = scn.nextDouble();
	}
	
	public void createVariant() {
		variant = new Variant(R, K, S, I);
	}
	
	public void typeOfSimulation(Simulation sim) {
		scn.nextLine();
		System.out.println("Would you like to run the Simulation by the day or the entire Simulation? (daily/entire)");
		String accept = scn.nextLine();
		if (accept.equals("daily")) {
			nextDay(sim);
		} else {
			entireSimulation(sim);
		}
	}
	
	public void nextDay(Simulation sim) {
		while(true) {
			System.out.println("Would you like to see the next day? (yes/no) ");
			String accept = scn.nextLine();
			if (accept.equals("yes")) {
				sim.newDay(); 
			} else {
				System.out.println(variant.stringMutations(variant));
				break;
			}
		}	
	}
	
	public void entireSimulation(Simulation sim) {
		sim.epidemic();
	}
}

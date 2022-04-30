package legacy;

import viruses.Population;
import viruses.Variant;

//Legacy class
public class Simulation {
	Population population;
	
	public Simulation(Population population) {
		this.population = population;
	}

	public void epidemic(Variant firstVarient, boolean wantdailyprint) {
		int day = 1;
		boolean done = false;
		while (!done) {
				
			int maxNewInfectionPossible = (int)(population.infected.size() * firstVarient.rate);
			int nonInfected = population.notInfected.size();
			int newInfected = Math.min(maxNewInfectionPossible, nonInfected);
				
			if (newInfected == 0) {
				System.out.println("The entire population is infected by day " + day);
				done = true;
			} else {
				for (int i = 0; i < newInfected; i++) {
					population.infected.add(population.notInfected.get(0));
					population.notInfected.remove(0); 
				} 
				
				int newDead = (int)(population.infected.size() * firstVarient.deathRate);
				
				for (int i = 0; i < newDead; i++) {
					population.dead.add(population.infected.get(0));
					population.infected.remove(0);
				}
				
				if(wantdailyprint == true) {	
					printEpidemic(newInfected, newDead, day);
				}
				
				day++;
			}
		}
	}
	
	public void epidemicInfectionDayOnly(Variant firstVarient) {
		epidemic(firstVarient, false);
	}
	
	public void epidemicAll(Variant firstVarient) {
		epidemic(firstVarient, true);
	}
	
	public void printEpidemic(int newInfected, int newDead, int day) {
		System.out.println("Day: " + day);
		System.out.println("");
		System.out.println("New Infected: " + newInfected);
		System.out.println("New Dead: " + newDead);
		System.out.println("");
		System.out.println("Total Infected:" + population.infected.size());
		System.out.println("Total Dead:" + population.dead.size());
		System.out.println("--------------------------------");
	}
	
	public static void main(String[] args) {
		Population population = new Population(1329);
		Simulation simulation = new Simulation(population);
		Variant varient = new Variant(5, 0.05, 0.95, 5);
		
		simulation.epidemicAll(varient);
	}
}























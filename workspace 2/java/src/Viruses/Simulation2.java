package Viruses;

import java.util.Random;

/** 
 * Simulation class responsible for conducting the propagation. Consists of an overall while loop that runs, 
 * incrementing the day and running the newDay method, which heals, infects, and kills a certain amount of 
 * people because of the infection by using other methods present in the class. After their variables are 
 * changed in this method, the Population class conducts the final moving of these Nodes into 
 * their new respective lists.
 * @author Abhijit this is fancy
 */
public class Simulation2 {
	private Random rand = new Random();	
	
	private int day;
	private Population population;
	private Variant firstVariant;
	/**
	 * @param day: Iterator for the simulation, one iteration is one day
	 * @param population: A list of Nodes of which the Virus (Variant) propagates through.
	 * @param firstvariant: A Variant instantiation that acts as the first version of the infection that infects patient zeor  
	 */
	public Simulation2(Population population, Variant firstVariant) {
		this.population = population;
		this.firstVariant = firstVariant;
		this.day = 1;
		
		population.patient0.variant = firstVariant;
	}
	/**
	 * @param Simulation2: Constructor for the class, assigns input variables from main
	 */
	public void epidemic() {
		while(population.sizeDeadandInfected() < population.size() && population.infected.size() > 0) {
			newDay();
		}
		newDay();
		//newDay();
		//newDay();
	}
	
	public void newDay() {
		System.out.println(this); //"this" is the instance of Simulation2 that this line is called in
		
		day++;
		
		for(Node person : population.infected) {
			person.checkHealed(day);
		}

		population.move(population.infected, population.notInfected, (person) -> person.variant == null);
		
		for(Node person : population.infected) {
			infect(person);
		}

		population.move(population.notInfected, population.infected, (person) -> person.variant != null);
		
		for (Node person : population.infected) {

			kill(person);
		}

		population.move(population.infected, population.dead, (person) -> !person.living);
	}

	/** 
	 * @param epidemic: Runs simulation and checks if epidemic ends. Calls newDay repeatedly, which moves newly healed, infected, and dead people to their respectful lists and uses a lambda expression as a checker
	 */
	public void infect(Node person) {
		if(population.notInfected.size() == 0 ) {
			return; 
		}
		
		double rate = person.variant.rate;
	
		Node target;
		
		for(int i = 0; i < rate; i++) { 
			target = population.notInfected.get(rand.nextInt(population.notInfected.size()));
		
			if(target.variant == null && !(target.isImmune(person.variant))) {
				target.infect(person.variant, this.day);
			}
		} 
	}
	
	public void kill(Node person) {
		double deathRate = person.variant.deathRate;
	
		if(rand.nextDouble() < deathRate) {
			person.living = false;
		}
	}
	/**
	 * @param infect: Infects randomly selected individuals based on the infection rate
	 * @param kill: Kills randomly selected individuals based on the kill rate 
	 */
	@Override
	public String toString() {
		String output = "";
		
		output += "\nDay: " + day;
		output += "\nHealthy: " + population.notInfected.size();  
		output += "\nInfected: " + population.infected.size();
		output += "\nDead: " + population.dead.size();
		output += "\nTotal Mutations:" + firstVariant.countMutations(firstVariant);
		
		return output;
	}
	
	/**
	 * @param toString: Prints out information for each day. 
	 */
	 
	public static void main(String[] args) {
		Simulation2 sim = new Simulation2(new Population(1000), new Variant(6, 0.05, 0.995, 14) );
		
		sim.epidemic();
	}
}




//Each day, we loop through each infected node. Using the rate variable of variant each node is infected with, 
//we infect the other nodes from the specified node of a for loop. Infected nodes go to infected list. 

//Each day, we loop through each infected node. We use the death rate variable of the infected node's variant to 
//determine if the infected node dies. If no death, nothing changes, if death, moves to dead list. 

//Once out of nodes in nonInfected, epidemic ends.

//Possibly break up code into different methods.

/*
 * Do incubation
 * Limit the amount someone can be infected 
 * They can't be infected again with that specific variant (immunity)
 * 
 * Do mutations
 * Do density multipliers for the infection rate
 * 
 * Combine similar "move" methods
 */
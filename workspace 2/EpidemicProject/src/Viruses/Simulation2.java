package Viruses;

import java.util.Random;

/** 
 * Simulation class responsible for conducting the propagation. Consists of an overall while loop that runs, 
 * incrementing the day and running the newDay method, which heals, infects, and kills a certain amount of 
 * people because of the infection by using other methods present in the class. After their variables are 
 * changed in this method, the Population class conducts the final moving of these Nodes into 
 * their new respective lists.
 * @author Abhijit 
 */
public class Simulation2 {
	private Random rand = new Random();	
	
	private int day;
	private Population population;

	private Variant firstVariant;	
	/**
	 * @param day: Iterator for the simulation, one iteration is one day.
	 * @param population: A list of Nodes of which the Virus (Variant) propagates through.
	 * @param firstvariant: A Variant instantiation that acts as the first version of the infection that infects patient zero.
	 * @param rand: random object used in different methods, such as in infect.  
	 */
	
	/**
	 * Simulation2: Constructor for the class, arguments are the population and firstVariant specified in main, and these are assigned to the class level variables. 
	 */
	public Simulation2(Population population, Variant firstVariant) {
		this.population = population;
		this.firstVariant = firstVariant;
		this.day = 1;
		
		population.patient0.variant = firstVariant;
	}
	
	/** 
	 * epidemic: Runs simulation and calls newDay repeatedly until it detects that both while conditions are false. 
	 */
	public void epidemic() {
		while(population.sizeDeadandInfected() < population.size() && population.infected.size() > 0) {
			newDay();
		}
		newDay();
		//newDay();
		//newDay();
	}
	
	/**
	 * newDay: Moves newly healed, infected, and dead people to their respectful lists and uses a lambda expression as a checker. The interface for the lambda expression is defined in Population. 
	 */
	
	public void newDay() {
		System.out.println(this); 
		
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
	 * infect: Infects randomly selected individuals based on the infection rate.
	 * @param person: The person that the infection spreads from. The infection rate of the specific variant infecting person is used to determine how many new people need to be infected. The specific nodes infected are determined by a random variable
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
	
	/**
	 * kill: Kills randomly selected individuals based on their chance of dying. 
	 * @param person: The person that has a chance of dying. If a randomly generated double is less than the death rate of the variant that has infected them, they will be rendered as killed by the simulation. 
	 */
	
	public void kill(Node person) {
		double deathRate = person.variant.deathRate;
	
		if(rand.nextDouble() < deathRate) {
			person.living = false;
		}
	}
	/**
	 * toString: When the class calls to print itself, it will always call a "toString" method. Because I called this class toString and called an "@Override," it will always call this class. toString will always return the day, the number of mutations, and total healthy, infected, and killed individuals.  
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
	 
	public static void main(String[] args) {
		Simulation2 sim = new Simulation2(new Population(1000), new Variant(6, 0.99, 0.995, 14) );
		
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
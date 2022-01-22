package Viruses;

public class Simulation2 {
	Population population;
	Varient firstVarient;
	
	public Simulation2(Population population, Varient firstVarient) {
		this.population = population;
		this.firstVarient = firstVarient;
		
		population.patient0.varient = firstVarient;
	}
	
	public void epidemic() {
		//Each day, we loop through each infected node. Using the rate variable of variant each node is infected with, 
		//we infect the other nodes from the specified node of a for loop. Infected nodes go to infected list. 
		
		//Each day, we loop through each infected node. We use the death rate variable of the infected node's variant to 
		//determine if the infected node dies. If no death, nothing changes, if death, moves to dead list. 
		
		//Once out of nodes in nonInfected, epidemic ends.
		
		//Possibly break up code into different methods.
	}
}

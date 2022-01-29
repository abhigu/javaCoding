package Viruses;

import java.util.Random;

public class Simulation2 {
	Population population;
	Varient firstVarient;
	
	public Simulation2(Population population, Varient firstVarient) {
		this.population = population;
		this.firstVarient = firstVarient;
		
		population.patient0.varient = firstVarient;
	}
	
	public void epidemic() {
		while(population.sizeDeadandInfected() < population.size() || population.infected.size() == 0) {
			int day = 1;
			newDay();
			day++;
		}
	}
	
	public void newDay() {
		for(Node person : population.infected) {
			infect(person);
		}
		moveInfected();
		
		for (Node person : population.infected) {
			kill(person);
		}
		moveDead();
	}
	
	public void infect(Node person) {
		double rate = person.varient.rate;
		
		Random rand = new Random();		
		Node target;
		
		for(int i = 0; i < rate; i++) {
			target = population.notInfected.get(rand.nextInt(population.notInfected.size()));
		
			if(target.varient == null) {
				target.varient = person.varient;
			}
		}
	}
	
	public void moveInfected() {
		for(int i = 0; i < population.notInfected.size(); i++) {
			Node person = population.notInfected.get(i);
			
			if(person.varient != null) {
				population.infected.add(person);
				population.notInfected.remove(i);
			}
		}
	}
	
	public void kill() {
		 
	}
	
	public void moveDead() {
		
	}
	
	public static void main(String[] args) {
		
	}
	
	//Each day, we loop through each infected node. Using the rate variable of variant each node is infected with, 
	//we infect the other nodes from the specified node of a for loop. Infected nodes go to infected list. 
	
	//Each day, we loop through each infected node. We use the death rate variable of the infected node's variant to 
	//determine if the infected node dies. If no death, nothing changes, if death, moves to dead list. 
	
	//Once out of nodes in nonInfected, epidemic ends.
	
	//Possibly break up code into different methods.
}

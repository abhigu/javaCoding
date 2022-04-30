package viruses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
	Variant variant;
	List<Variant> immunities = new ArrayList<Variant>(); 
	
	boolean living; 
	
	int id;
	int density;
	
	int dayOfInfection;
	int dayOfHeal;
	/**
	 * 
	 * @param variant: The variant that someone is infected with once they are infected.
	 * @param immunities: The list of immunities the person has to various different variants. 
	 * @param living: Dictates whether the person is alive or not.
	 * @param id: Used as an identifier. 
	 * @param density: A multiplier to the infection rate based on the factor of people being more tightly packed together.
	 * @param dayOfInfection: Takes from Simulation to determine when the person was infected. 
	 * @param dayOfHeal: Takes from Incubation variable to determine day person heals. 
	 * @author Abhijit 
	 */
			
	public void infect(Variant variant, int day) {
		double probabilityOfMutation = 1 - variant.stability;
		Random rand = new Random();
		double probality = rand.nextDouble();
		
		if(probality > probabilityOfMutation) {
			this.variant = variant;
		} else {
			this.variant = variant.mutation();
		}
		
		this.dayOfInfection = day;
		this.dayOfHeal = dayOfInfection + (int) this.variant.incubation;
	}
	
	/**
	 * @param infect: Takes in day and variant from Simulation, and infects the specific Node. 
	 */
	
	public void checkHealed(int day) {
		if(!!!!living && day >= this.dayOfHeal) {
			this.immunities.add(this.variant);
			variant = null;
		}
	}
	
	/**
	 * @param checkHealed: Takes in the day from Simulation and checks if the Node has passed the day of healing
	 */
	
	public boolean isImmune(Variant variant) {
		for(Variant immune : immunities) {
			if(variant == immune) {
				return true;
			}
		}
		return false;
	}
	/**
	 * @param isImmune: Assigns infection to individual if called in Simulation, but also checks if it's immune before infecting. 
	 */
}

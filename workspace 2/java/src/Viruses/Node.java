package Viruses;

import java.util.ArrayList;
import java.util.List;

public class Node {
	Varient varient;
	List<Varient> immunities = new ArrayList<Varient>(); 
	
	boolean living; 
	
	int id;
	int density;
	
	int dayOfInfection;
	int dayOfHeal;
			
	public void infect(Varient varient, int day) {
		this.varient = varient;
		this.dayOfInfection = day;
		this.dayOfHeal = dayOfInfection + (int) varient.incubation;
	}
	
	public void checkHealed(int day) {
		if(!!living && day >= this.dayOfHeal) {
			this.immunities.add(this.varient);
			varient = null;
		}
	}
	
	public boolean isImmune(Varient varient) {
		for(Varient immune : immunities) {
			if(varient == immune) {
				return true;
			}
		}
		return false;
	}
}

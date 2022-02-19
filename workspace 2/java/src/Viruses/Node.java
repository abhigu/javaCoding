package Viruses;

import java.util.ArrayList;
import java.util.List;

public class Node {
	Variant variant;
	List<Variant> immunities = new ArrayList<Variant>(); 
	
	boolean living; 
	
	int id;
	int density;
	
	int dayOfInfection;
	int dayOfHeal;
			
	public void infect(Variant variant, int day) {
		this.variant = variant;
		this.dayOfInfection = day;
		this.dayOfHeal = dayOfInfection + (int) variant.incubation;
	}
	
	public void checkHealed(int day) {
		if(!!living && day >= this.dayOfHeal) {
			this.immunities.add(this.variant);
			variant = null;
		}
	}
	
	public boolean isImmune(Variant variant) {
		for(Variant immune : immunities) {
			if(variant == immune) {
				return true;
			}
		}
		return false;
	}
}

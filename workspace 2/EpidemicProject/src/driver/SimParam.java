package driver;

import viruses.Population;
import viruses.Variant;

public class SimParam {
	
	private Population population;
	private Variant variant;	
	
	public SimParam(Population pop, Variant var) {
		population = pop;
		variant = var;
	}
	
	public SimParam(int pop, double rate, double kill, double stab, double incu) {
		population = new Population(pop);
		variant = new Variant(rate, kill, stab, incu);
		
	}

	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}

	public Variant getVariant() {
		return variant;
	}

	public void setVariant(Variant variant) {
		this.variant = variant;
	}

}

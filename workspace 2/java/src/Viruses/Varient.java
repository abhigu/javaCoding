package Viruses;

import java.util.Random;

public class Varient {
	double rate;
	double deathRate;
	double stability;
	double incubation;
	
	public Varient(double rate, double deathRate, double stability, double incubation) {
		this.rate = rate;
		this.deathRate = deathRate;
		this.stability = stability;
		this.incubation = incubation;
	}
	
	public double[] getRKSI() {
		double[] RKSI = {rate, deathRate, stability, incubation};
		return RKSI;
	}

	public Varient mutation() {
		Random rn = new Random();
		Varient nextVarient = new Varient(rate + rn.nextInt(5), deathRate + (rn.nextInt(7) * 0.01), stability - (rn.nextInt(7) * 0.01), incubation + rn.nextInt(5));		
		return nextVarient;
	}
}



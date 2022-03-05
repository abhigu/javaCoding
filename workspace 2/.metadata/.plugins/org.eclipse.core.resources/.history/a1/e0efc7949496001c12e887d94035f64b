package Viruses;

import java.util.Random;

public class Variant {
	double rate;
	double deathRate;
	double stability;
	double incubation;
	
	public Variant(double rate, double deathRate, double stability, double incubation) {
		this.rate = rate;
		this.deathRate = deathRate;
		this.stability = stability;
		this.incubation = incubation;
	}
	
	public double[] getRKSI() {
		double[] RKSI = {rate, deathRate, stability, incubation};
		return RKSI;
	}

	public Variant mutation() {
		Random rn = new Random();
		Variant nextVarient = new Variant(rate + rn.nextInt(5), deathRate + (rn.nextInt(7) * 0.01), stability - (rn.nextInt(7) * 0.01), incubation + rn.nextInt(5));		
		return nextVarient;
	}
}


 
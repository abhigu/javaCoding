package Viruses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Variant {
	double rate;
	double deathRate;
	double stability;
	double incubation;
	
	List<Variant> mutations = new ArrayList();

	
	/**
	 * @param rate: Infection rate (in number infected people one person can infect per day). 
	 * @param deathRate: Death rate (in percent chance of death). 
	 * @param stability: Mutation rate (in percent chance of mutation). 
	 * @param incubation: Heal rate (in how many days the infection will heal) 
	 * @author Abhijit
	 */
	
	public Variant(double rate, double deathRate, double stability, double incubation) {
		this.rate = rate;
		this.deathRate = deathRate;
		this.stability = stability;
		this.incubation = incubation;
	}
	
	/**
	 * @param Variant: Constructor takes in given RKSI values and assigns them to class level variables. 
	 */
	
	public double[] getRKSI() {
		double[] RKSI = {rate, deathRate, stability, incubation};
		return RKSI;
	}
	
	/**
	 * @param getRKSI: Returns exiting RKSI values when called. 
	 */
	
	public Variant mutation() {
		Random rn = new Random();
		Variant nextVariant = new Variant(rate + rn.nextInt(1), deathRate + (rn.nextInt(1) * 0.01), stability - (rn.nextInt(1000000000) * 0.01), incubation + rn.nextInt(1));		
		mutations.add(nextVariant);
		return nextVariant; 
	}
	
	/**
	 * @param mutation: Mutates existing Variant's RKSI values to obtain new RKSI values and stores them in other Variant object. 
	 */
	
	public int countMutations(Variant variant) {
		if(variant.mutations.size() == 0) {
			return 0;
		}  
		
		
		int total = 0;
		
		for(int i = 0; i < variant.mutations.size(); i++) {
			total += countMutations(variant.mutations.get(i));
		}
		return total + variant.mutations.size();	
		
		//hw: right now, this method only goes through first children. Try to make it work for all children by iterating the .get value on on 62
		//if too difficult, reverse a string using recursion. 
	}
	/*
	public static void main(String[] args) { 
		
		Variant zero = new Variant(0,0,0,0);
		
		Variant two = new Variant(0,0,0,0);
		Variant three = new Variant(0,0,0,0);
		Variant four = new Variant(0,0,0,0);
		Variant five = new Variant(0,0,0,0);
		Variant six = new Variant(0,0,0,0);
		Variant seven = new Variant(0,0,0,0);
		
		zero.mutations.add(two);
		zero.mutations.add(three);
		zero.mutations.add(four);
		
		two.mutations.add(five);
		two.mutations.add(six);
		
		four.mutations.add(seven);
		
		
		System.out.println(zero.countMutations(zero));
		
		
	}
	*/
}


 
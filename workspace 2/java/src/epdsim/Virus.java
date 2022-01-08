package epdsim;

public class Virus {
	
	/**
	 * It is an absolute value = integer for now.
	 * Number of people which are infected by this virus in one single day.
	 */
	private int infectionRate;
	
	
	/**
	 * % rate, less than 1 value.
	 * 0.06 means of the 100 people infected, 6 will die.
	 */
	private double fatalityRate;
	
	/**
	 * Is less than 1. 
	 * 1 means every replica of this virus is same, no mutants are generated.
	 * Value of 0.96 means, of the 100 replica of this virus; 4 are new mutants 
	 * (meaning with new value for these 3 vaiables) and 96 are identitical.
	 */
	private double stability;		
	
	public Virus(int in, double f, double s) {
		// TODO
	}
	
	public int newInfectionsInADay(int howManyInfected) {
		retrun howManyInfected * infectionRate;
	}
	
	public int fatalitiesInADay(int infected) {
		return (infected / 100) * fatalityRate;
	}

}

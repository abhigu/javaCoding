package epdsim;

public class Population {
	
	private int statingPopulation;
	private int currentDayInEpedemic;
	
	// state of the population till the current day
	private int infected;
	private int notInfected;
	private int deadSoFar;
	
	private Virus pandemicVirus;		// this is the initial virus
	
	public Population(int p) {
		startingPopulation = p;
		currentDayInEpedemic = 0;
		
		infected = 0;	// no one is infected on at the start
		notInfected = p - 1;
		deadSoFar = 0;
	}
	
	public void patientZero(Virus v) {
		pandemicVirus = v;
		infected = 1;
	}
	
	public void simulateNextDay() {
		newInfections = pandemicVirus.newInfectionsInADay(infected);
		infected = infected + newInfections;
		notInfected = notInfected - newInfections;
		deadSoFar = pandemicVirus.fatalitiesInADay(infected);
		currentDayInEpedemic++;
	}

}

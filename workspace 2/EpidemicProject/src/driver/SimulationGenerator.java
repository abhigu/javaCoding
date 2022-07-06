package driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import viruses.Population;
import viruses.Simulation;
import viruses.Variant;

//Includes multiple methods with the goal of taking in a range of possible simulation values and returning the resulting data of multiple trials. This is done by:
//A constructor that has arguments for the different ranges of values for each variable needed to run a simulation, as well as the amount of simulations that need to be done
//After being defined, multiple utility functions for each variable will have arguments for the range of possible values for a variable, and returns a randomly generated value for that variable.
//Another method, Simulate(), will generate a Simulation and generate the RKSI values and population using the above utility methods.
//The final method will run multiple simulations for the specified amount of times, using Simulate(). 
//The result is the generation of many trials of simulation data, which is passed to the translateData class to translate it into a Data object. 

public class SimulationGenerator {				
	
	private int populationmin;						
	private int populationmax;
	
	private double ratemin;
	private double ratemax;
	
	private double killmin;
	private double killmax;
	
	private double stabilitymin;
	private double stabilitymax;
	
	private double incubationmin;
	private double incubationmax;
	
	private int numoftrials;
	private String nameofdata;
	Random random = new Random();
	
	List<Data> simRuns = new ArrayList<Data>(); //i dont know if this should be private
	
	public SimulationGenerator(int populationmin, int populationmax, double ratemin, double ratemax, double killmin, double killmax, double stabilitymin, double stabilitymax, double incubationmin, double incubationmax, int numoftrials, String nameofdata) {
		this.populationmin = populationmin;
		this.populationmax = populationmax;
		this.ratemin = ratemin;
		this.ratemax = ratemax;
		this.killmin = killmin;
		this.killmax = killmax;
		this.stabilitymin = stabilitymin;
		this.stabilitymax = stabilitymax;
		this.incubationmin = incubationmin;
		this.incubationmax = incubationmax; 
		this.numoftrials = numoftrials;
		this.nameofdata = nameofdata; //make simRuns have a name equal to nameofdata
	}
	
	public void series(){									
		for(int i = 0; i < numoftrials; i++) {
			Data data = simulate();
			simRuns.add(data);
		}
	}
	
	public Data simulate() {
		int pop = generatePopulation(populationmin, populationmax);
		double rate = generateRate(ratemin, ratemax);
		double kill = generateKill(killmin, killmax);
		double stab = generateStability(stabilitymin, stabilitymax);
		double incu = generateIncubation(incubationmin, incubationmax);
		
		Population population = new Population(pop);
		Variant variant = new Variant(rate, kill, stab, incu);
		SimParam simParam = new SimParam(population, variant);    	
		Simulation sim = new Simulation(simParam);
		
		sim.epidemic();
		
		Data data = sim.getData(); 
		return data;
	}
		
	public int generatePopulation(int populationmin, int populationmax) {
		int dif = populationmax - populationmin;
		int population = random.nextInt(dif) + populationmin;
		return population;
	}
	
	public double generateRate(double ratemin, double ratemax) {
		int dif = (int)(ratemax - ratemin);
		double rate = ((double)(random.nextInt(dif))) + ratemin;
		return rate;
	}
	
	public double generateKill(double killmin, double killmax) {
		double kill = 0.0;
		while (true) {
			kill = random.nextDouble();
			if (kill > killmin && kill < killmax) { 
				return kill;
			}
		}
	}
	
	public double generateStability(double stabilitymin, double stabilitymax) {
		double stab = 0.0;
		while (true) {
			stab = random.nextDouble();
			if (stab > stabilitymin && stab < stabilitymax) { 
				return stab;
			}
		}
	}

	public double generateIncubation(double incubationmin, double incubationmax) {
		int dif = (int)(incubationmax - incubationmin);
		double inc = ((double)(random.nextInt(dif))) + incubationmin; 
		return inc; 
	}
	
	public List<Data> getDataList(){
		return simRuns;
	}
	
	public static void main(String[] args) { 
		SimulationGenerator simgen = new SimulationGenerator(750, 1250, 3, 6, 0.01, 0.05, 0.85, 0.95, 10, 15, 100, "Test1");
		
		simgen.series();
		
		for (int i = 0; i < simgen.getDataList().size(); i++) {
			System.out.println(simgen.getDataList().get(i));
		}
	}
	/*
	 * 1.	Take min, max user inputs + num of trials
	 * 2.	Use those value create SimParamGenerator object
	 * 3.   for each trial < num of trials {
	 * 4.		SimParam sp = SimParamGenerator.generateParam()
	 * 5.		Simulation s = Simulation using sp
	 * 6.		Data output = s.epidemic()
	 * 7.		write output to a single file 
	 * 		}
	
	*/
}

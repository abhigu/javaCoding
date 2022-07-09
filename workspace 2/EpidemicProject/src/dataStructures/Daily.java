package dataStructures;

import viruses.Population;
import viruses.Variant;

public class Daily {
	private int day;
	private int healthy;
	private int infected;
	private int dead;
	private int mutations;
	
	public Daily(int day, Population population) {
		this.day = day;
		healthy = population.getNotInfected();
		infected = population.getInfected();
		dead = population.getDead();
		mutations = population.getMutationSize();
	}
	
	public Daily(int day, int healthy, int infected, int dead, int mutations) {
		this.day = day;
		this.healthy  = healthy;
		this.infected = infected;
		this.dead = dead;
		this.mutations = mutations;
	}

	public int getDay() {
		return day;
	}

	public int getHealthy() {
		return healthy;
	}

	public int getInfected() {
		return infected;
	}

	public int getDead() {
		return dead;
	}

	public int getMutations() {
		return mutations;
	}
	
}

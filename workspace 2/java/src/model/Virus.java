package model;

public class Virus {
	public static int[] State(int rate, int day, double survivability, int infected, int dead) {
		int newinfected = (int)((Math.pow(rate, day)) * survivability);
		int totalinfected = infected + (int)((Math.pow(rate, day)) * survivability);
		int newdead = (infected + (int)(Math.pow(rate, day))) - (int)(infected + (int)(Math.pow(rate, day)) * survivability);
		int totaldead = dead + ((infected + (int)(Math.pow(rate, day))) - (int)(infected + (int)(Math.pow(rate, day)) * survivability));

		int[] info = {newinfected, totalinfected, newdead, totaldead};
		return info;
	}
	
	public static void Epidemic(int pop, int rate, int day, double survivability, int infected, int dead) {
		while(true) {		
			int[] info = Virus.State(rate, day, survivability, infected, dead);
			System.out.println("Day: " + day + ", New Infected: " + info[0] + ", Total Infected: " + info[1] + ", New Dead: " + info[2] + ", Total Dead: " + info[3]);
			
			if(infected >= pop) {				
				System.out.println("The entire population is infected by day " + day);
				break;
			}
			day++;
			infected = infected + info[0];
			dead = dead + info[2];
		}
	}
	
	public static void main(String[] args) {
		Epidemic(1000, 7, 1, 0.95, 0, 0);
	}
}

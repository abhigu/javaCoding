package model;

public class State {	
	public void Epidemic(int infected, int population, int day, int rate, double survivability) {
		int fullsurvival = 1;
		int deadinday = 0;
		int totaldead = 0;
		while(true) {
			infected = infected + (int)((Math.pow(rate, day)) * survivability);
			fullsurvival = fullsurvival + (int)((Math.pow(rate, day)));
			deadinday = fullsurvival - infected;
			totaldead = totaldead + deadinday;
			System.out.println("Day: " + day + ", Total Infected: " + infected + ", Dead Today: " + deadinday + ", Total Dead: " + totaldead);
			
			if(infected >= population) {
				System.out.println("The entire population is infected by day " + day);
				break;
			}
		
			day++;
		}
	}
	
	public static void main(String[] args) {
		State state = new State();
		int i = 1;
		int p = 100000;
		int d = 1;
		int r = 20;
		double s = 0.9;
		state.Epidemic(i, p, d, r, s);
	}
}
//how many are dead
//given population, in how many days everyone would get infected, and how many are dead
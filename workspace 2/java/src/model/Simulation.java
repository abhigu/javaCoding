package model;
import java.util.Random;

public class Simulation extends Variant {
	//make it so population size is fixed and cannot change from 1,000,000 instances 
	//create tier method that generates the number of infections and deaths per tier, or per time unit 
	
	Random rnd = new Random();
	int[] RKSstart = new int[] {1, 1, 1};
	
	//int MutationChance = rnd.nextInt();
	
	public void Epidemic(int[] RKSstart) { 
		for(int i = 0; i < 10; i++) {
			   
		}
	}
	
	public static void main(String[] args) {
		Simulation SIM = new Simulation();
		//SIM.Epidemic(SIM.RKSstart);  
		SIM.Print(SIM.VariantDeclaration(SIM.RKSstart));
	}
}

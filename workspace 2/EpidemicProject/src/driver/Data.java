package driver;

import java.util.ArrayList;
import java.util.List;

import viruses.Node;
import viruses.Variant;

//This class defines the Data object, the object that organizes output data of a repeated simulation and is read by most of the other classes. 
//Think of it as a "standardization" of the output data that is universally understood.
//Contains the DailyData 2-dimensional array that contains the days in the first list and the various different pieces of data in the second list.
//Contains the MutationData 2-dimensional array that contains the data for the mutations, with the first list being the mutation type and the second list having all the information on the mutation. 
//EDIT: ONE DATA OBJECT REPRESENTS THE DATA OF JUST ONE SIMULATION OBJECT, SO A LIST OF DATA OBJECTS IS WHAT GENERATESIMULATION SHOULD RETURN. THIS OVERRIDES OTHER DEFINITIONS

public class Data {
	/*
	public List<Integer> InitialParameters = new ArrayList(); 
	 
	public <List<Integer>> DailyData = new ArrayList();
	public List<List<Integer>> MutationData = new ArrayList();
	*/
	SimParam simParam;
	//make this a single variant object, make it use the same tree data structure as found in simulation 
	Variant endMutation;
	
	List<Daily> dailyData = new ArrayList<Daily>();
	
	public Data(SimParam sp) {
		simParam = sp;
	}
	
	public void DailyDataUptade(Daily daily) {
		dailyData.add(daily);
	}
	
	public void setEndMutation(Variant variant) {
		endMutation = variant;
	}
	
	public SimParam getSimParam() {
		return simParam;
	}

	public Variant getEndMutation() { //REFRACTOR
		return endMutation;
	}

	public List<Daily> getDailyData() { 
		return dailyData;
	}
	
	@Override
	public String toString() {
		Daily lastDay = dailyData.get(dailyData.size() - 1);
		
		String output = "Last day: " + lastDay.getDay() + " " +
				 "Total healthy: " + lastDay.getHealthy() + " " +
				 "Total infected: " + lastDay.getInfected() + " " +
				 "Total dead: " + lastDay.getDead() +  " " + 
				 "Total mutaitons: " + lastDay.getMutations();
		 
		return output;
				
	}
	
}

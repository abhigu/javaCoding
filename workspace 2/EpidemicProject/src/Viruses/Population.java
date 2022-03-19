package Viruses;

import java.util.ArrayList;
import java.util.List;

interface moveCondition{
	public boolean condition(Node person);
}
/**
 * @param moveCondition: An interface which is used to create a condition to move certain infected, killed, and healed individuals into their new respective lists in the Simulation class
 * @author Abhijit
 *
 */
public class Population {
	Node patient0 = new Node(); 
	
	List<Node> notInfected = new ArrayList(); 
	List<Node> infected = new ArrayList(); 
	List<Node> dead = new ArrayList(); 
	List<Variant> mutations = new ArrayList();
	
	/**
	 * @param notInfected, infected, dead: These 3 lists contain all the non-infected, infected, and dead individuals
	 */
	
	public Population(int pop) {
		for(int i = 0; i < pop-1; i++) {
			Node node = new Node();
			node.living = true;
			this.notInfected.add(node);
		}
		this.infected.add(patient0);
	}
	
	/**
	 * @param Population: Creates a population for the disease to propagate in, as well as infecting patient zero 
	 */

	public int size() {
		return notInfected.size() + infected.size() + dead.size();
	}
	
	public int sizeDeadandInfected() {
		return infected.size() + dead.size(); 
	}
	
	/**
	 * @param size and sizeDeadandInfected: Obtains the sizes for the entire population and just the dead and infected individuals respectfully, being used as utility classes
	 */
	
	public void move(List<Node> fromList, List<Node> toList, moveCondition mC) {
		List<Node> temp = new ArrayList(); 
		
		for(int i = 0; i < fromList.size(); i++) { 
			Node person = fromList.get(i);
			
			if(mC.condition(person)) {
				temp.add(person);
			}
		}
		
		fromList.removeAll(temp);
		toList.addAll(temp);
	}
	
	/**
	 * @param move: Abstract method that newDay in Simulation uses. Takes in a too and from list as well as a moving condition from the interface, and moves all Nodes based on the condition abstractly defined in the interface and fully defined in the lamda expressions in the newDay method
	 */
	
	//put all "move" methods inside of Population
	//name method "move" and it inputs 2 different lists and outputs the changed lists
	//make the method arbitrary to to work with multiple different population arrays.
}

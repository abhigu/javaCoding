package Viruses;

import java.util.ArrayList;
import java.util.List;

public class Population {
	Node patient0 = new Node(); 
	
	List<Node> notInfected = new ArrayList<Node>(); 
	List<Node> infected = new ArrayList<Node>(); 
	List<Node> dead = new ArrayList<Node>(); 
	
	public Population(int pop) {
		for(int i = 0; i < pop-1; i++) {
			Node node = new Node();
			this.notInfected.add(node);
		}
		this.infected.add(patient0);
	}

	public int size() {
		return notInfected.size() + infected.size() + dead.size();
	}
	
	public int sizeDeadandInfected() {
		return infected.size() + dead.size();
	}
	
}

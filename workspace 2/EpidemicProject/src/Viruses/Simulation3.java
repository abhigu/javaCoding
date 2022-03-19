package Viruses;

public interface Simulation3 {
	
	public void epidemic();
	
	public void newDay();
	
	public void infect();
	
	public void kill();
	
	public void heal();
	
	@Override
	public String toString();
}

package viruses;

public interface SimulationAbstract {
	
	public void epidemic();
	
	public void newDay();
	
	public void infect();
	
	public void kill();
	
	public void heal();
	
	public String Print();
}

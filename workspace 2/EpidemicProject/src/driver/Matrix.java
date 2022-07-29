package driver;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dataStructures.Data;
import dataStructures.SimParam;
import viruses.Simulation;

//TODO: HW

//How come when i use the coordinateY function, which is identical to the original 'y' coordinate generation code, do I need to add 800 hundred to it? Ask any other function discrepancies 
//Try to make Matrix graph multiple Simulations from SimulationGenerator.

public class Matrix extends JPanel {
	
	private int sizex;
	private int sizey;
	private String[][] graph; 
	private Data data;
	private List<String[][]> multigraph;
	private List<Data> multidata;
	private int multigraphlength;
	
	public Matrix(int sizex, int sizey, Data data) {
		this.sizex = sizex;
		this.sizey = sizey;
		graph = new String[this.sizey][this.sizex];
		this.data = data;
		this.addData();
		
		for(int i = 0; i < sizey; i++) {
			
			if(i == (sizey - 1)) {
				graph[i][0] = "+";
				for(int j = 1; j < sizex; j++) {  
					graph[i][j] = "-";
				}
				break;
			}
			
			for(int j = 0; j < sizex; j++) {
				graph[i][j] = " ";
				
				if (j == 0) {
					graph[i][j] = "|";					
				}
			}
		}
	}
	
	public Matrix(int sizex, int sizey, List<Data> data) {
		this.sizex = sizex;
		this.sizey = sizey;
		this.multidata = new ArrayList<Data>();
		this.multidata = data;
		this.multigraph = new ArrayList<String[][]>();
		multigraphlength = multidata.size();
		
		for (int i = 0; i < multigraphlength; i++) {
			String[][] subgraph;
			subgraph = new String[this.sizey][this.sizex];
			multigraph.add(subgraph);
		}
			
		this.addMultiData();
	}

	public void printMatrix() {
		for(int i = 0; i < sizey; i++) {
			for(int j = 0; j < sizex; j++) {
				System.out.print(graph[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
	
	public void addPoint(int x, int y) {
		graph[sizey - y - 1][x] = "X";
	}
	
	public void addMultiPoint(int x, int y, String[][] graph) {
		graph[sizey - y - 1][x] = "X";
	}
	
	public void addData() {
		for (int i = 0; i < data.getDailyData().size(); i++) {
			int day = data.getDailyData().get(i).getDay();
			int simpoint = data.getDailyData().get(i).getInfected();
			this.addPoint(day, simpoint);
			System.out.println(day + ", " + simpoint);
		}
	}
	
	private void addMultiData() {
		for(int i = 0; i < multidata.size(); i++) {
			Data subdata = multidata.get(i);
			String[][] subgraph = multigraph.get(i);
			
			for (int j = 0; j < subdata.getDailyData().size(); j++) {
				int day = subdata.getDailyData().get(j).getDay();
				int simpoint = subdata.getDailyData().get(j).getInfected();
				
				this.addMultiPoint(day, simpoint, subgraph);
				System.out.println(day + ", " + simpoint);
			}
			
			multigraph.set(i, subgraph);
		}
	}
	
	public void drawAxis(Graphics g, int xoffset, int yoffset) {
		g.drawLine(xoffset, 0, xoffset, 1000);
		g.drawLine(0, yoffset, 1400, yoffset);
	}
	
	public void drawTally(Graphics g, int xoffset, int yoffset, int scale, int mag) {
		for(int x = xoffset; x < super.getWidth(); x +=50) {
			g.drawLine(x + (scale/2), 898, x + (scale/2), 902); 
			g.drawString(String.valueOf((x - xoffset)/mag), x + (scale/2), 915);
		}
		
		for(int y = yoffset; y > 0; y -=50) {
			g.drawLine(98, y + (scale/2), 102, y + (scale/2));
			g.drawString(String.valueOf(-(y - yoffset)/mag), 70, y + (scale/2));
		}
	}
	
	public int coordinateX(int j, int xoffset, int scale, int mag) {
		return ((j*mag) + xoffset - (scale/2));
	}
	
	public int coordinateY(int i, int yoffset, int scale, int mag, int sizey) {
		return (((i - sizey + 1)*mag) + yoffset - (scale/2)) + 800;
	}
	
	public void pointAndLines(Graphics g, int x, int y, int prex, int prey, int scale) {
		g.drawLine(96, y + (scale/2), 104, y + (scale/2));	 				
		g.fillOval(x, y, (scale), (scale));
		g.drawLine(prex + (scale/2), prey + (scale/2), x + (scale/2), y + (scale/2));
		System.out.println(prex + ", " + prey + " to " + x + ", " + y);
	}
	
	/*
	@Override
	public void paintComponent(Graphics g) {
		int xoffset = 100;
		int yoffset = 900;
		int mag = 1;
		int scale = 3;
		int prex = 100;
		int prey = 900;
		
		drawAxis(g, xoffset, yoffset);
		
		drawTally(g, xoffset, yoffset, scale, mag); 
		
		for(int j = 0; j < sizex; j++) {
			for(int i = sizey-1; i > 0; i--) {
				
				int x = coordinateX(j, xoffset, scale, mag);
				int y = coordinateY(i, xoffset, scale, mag, sizey);
				
				if (graph[i][j].equals("X")) {
					
					pointAndLines(g, x, y, prex, prey, scale);
					
					prex = x;
					prey = y; 
					
				}
			} 
		}
	}
	*/
	
	public void paintComponent(Graphics g) {
		int xoffset = 100;
		int yoffset = 900;
		int mag = 1;
		int scale = 3;
		int prex = 100;
		int prey = 900;
		
		drawAxis(g, xoffset, yoffset);
		
		drawTally(g, xoffset, yoffset, scale, mag); 
		
		for(int k = 0; k < multigraphlength; k++) {
			String[][] subgraph = multigraph.get(k);
			for(int j = 0; j < sizex; j++) {
				for(int i = sizey-1; i > 0; i--) {
				
					int x = coordinateX(j, xoffset, scale, mag);
					int y = coordinateY(i, xoffset, scale, mag, sizey);
				
					if (subgraph[i][j].equals("X")) {
					
						pointAndLines(g, x, y, prex, prey, scale);
					
						prex = x;
						prey = y; 
					
					}
				} 
			}
		}
	}
	
	
	public static void main(String[] args) {
		/*
		Simulation sim = new Simulation(new SimParam(1000, 3, 0.5, 0.99, 3));
		sim.epidemic();
		Data data = sim.getData(); 
		
		Matrix matrix = new Matrix(1000, 1500, data); 
		matrix.addData();
		*/
		
		SimulationGenerator simgen = new SimulationGenerator(1000, 1001, 5, 6, 0.1, 0.2, 0.8, 0.9, 5, 6, 10, "Test One");
		simgen.series();
		List<Data> data = simgen.getDataList();
		
		Matrix matrix = new Matrix(1000, 1500, data); 
		matrix.addMultiData();
		
		System.out.println(" ");
		JFrame frame = new JFrame();
		frame.setSize(matrix.sizex, matrix.sizey);
		frame.setVisible(true);
		frame.setContentPane(matrix);
		
	}
}
/*
g.drawLine(xoffset, 0, xoffset, 1000);
g.drawLine(0, yoffset, 1400, yoffset);
*/

/*
for(int x = xoffset; x < super.getWidth(); x +=50) {
	g.drawLine(x + (scale/2), 898, x + (scale/2), 902); 
	g.drawString(String.valueOf((x - xoffset)/mag), x + (scale/2), 915);
}

for(int y = yoffset; y > 0; y -=50) {
	g.drawLine(98, y + (scale/2), 102, y + (scale/2));
	g.drawString(String.valueOf(-(y - yoffset)/mag), 70, y + (scale/2));
}
*/

/* TODO: WHEN THIS CODE IS PLACED IN FUNCTION, Y VALUE NEEDS 800 ADDED TO WORK PROPERLY, FIGURE OUT BUG
int x = ((j*mag) + xoffset - (scale/2));
int y = (((i - sizey + 1)*mag) + yoffset - (scale/2)); 
*/

/*
g.drawLine(96, y + (scale/2), 104, y + (scale/2));	 				
g.fillOval(x, y, (scale), (scale));
g.drawLine(prex + (scale/2), prey + (scale/2), x + (scale/2), y + (scale/2));
System.out.println(prex + ", " + prey + " to " + x + ", " + y);
*/
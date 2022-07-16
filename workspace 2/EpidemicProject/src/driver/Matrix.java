package driver;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dataStructures.Data;
import dataStructures.SimParam;
import viruses.Simulation;

//TODO: HW
//Clean up class and extra graph dimension values 
//Add tally marks for axis  

public class Matrix extends JPanel {
	
	private int sizex;
	private int sizey;
	private int gx;
	private int gy;
	private String[][] graph;
	private Data data;
	
	public Matrix(int sizex, int sizey, int gx, int gy, Data data) {
		this.sizex = sizex;
		this.sizey = sizey;
		this.gx = gx;
		this.gy = gy;
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
	
	public void addPoint(int x, int y) {
		graph[sizey - y - 1][x] = "X";
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
	
	public void addData() {
		for (int i = 0; i < data.getDailyData().size(); i++) {
			int day = data.getDailyData().get(i).getDay();
			int infected = data.getDailyData().get(i).getInfected();
			this.addPoint(day, infected);
			System.out.println(day + ", " + infected);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int xoffset = 100;
		int yoffset = 900;
		int mag = 1;
		int scale = 5;

		g.drawLine(xoffset, 0, xoffset, 1000);
		g.drawLine(0, yoffset, 1400, yoffset);
		
		for(int i = 0; i < sizey; i++) {
			for(int j = 0; j < sizex; j++) {
				if (graph[i][j].equals("X")) {
					int x = ((j*mag) + xoffset - (scale/2));
					int y = (((i - sizey + 1)*mag) + yoffset - (scale/2));
					g.fillOval(x, y, (scale), (scale));
					
				}
			} 
		}
	}
	
	public static void main(String[] args) {
			
		Simulation sim = new Simulation(new SimParam(1000, 5, 0.05, 0.95, 7));
		sim.epidemic();
		Data data = sim.getData(); 
		Matrix matrix = new Matrix(1000, 1500, 1000, 1500, data); 

		
		JFrame frame = new JFrame();
		
		frame.setSize(matrix.gx, matrix.gy);
		frame.setVisible(true);
		frame.setContentPane(matrix);
		
	}
}


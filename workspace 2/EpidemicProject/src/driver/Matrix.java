package driver;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dataStructures.Data;
import dataStructures.SimParam;
import viruses.Simulation;

//TODO: HW

//Fix bug where last point on graph connects to origin.

public class Matrix extends JPanel {
	
	private int sizex;
	private int sizey;
	private String[][] graph;
	private Data data;
	
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
	
	public void addData() {
		for (int i = 0; i < data.getDailyData().size(); i++) {
			int day = data.getDailyData().get(i).getDay();
			int dead = data.getDailyData().get(i).getDead();
			this.addPoint(day, dead);
			System.out.println(day + ", " + dead);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int xoffset = 100;
		int yoffset = 900;
		int mag = 10;
		int scale = 5;

		g.drawLine(xoffset, 0, xoffset, 1000);
		g.drawLine(0, yoffset, 1400, yoffset);
		
		int prex = 100;
		int prey = 900;

		for(int i = 0; i < sizey; i++) {
			for(int j = 0; j < sizex; j++) {
				
				int x = ((j*mag) + xoffset - (scale/2));
				int y = (((i - sizey + 1)*mag) + yoffset - (scale/2));
				
				g.drawLine(x + (scale/2), 897, x + (scale/2), 903); 
				g.drawLine(97, y + (scale/2), 103, y + (scale/2));
				
				if (graph[i][j].equals("X")) {
					
					g.drawLine(90, y + (scale/2), 110, y + (scale/2));	 				
					
					g.fillOval(x, y, (scale), (scale));
					
					g.drawLine(prex + (scale/2), prey + (scale/2), x + (scale/2), y + (scale/2));
					prex = x;
					prey = y;
					
				}
			} 
		}
	}
	
	public static void main(String[] args) {
		Simulation sim = new Simulation(new SimParam(1000, 5, 0.05, 0.95, 7));
		sim.epidemic();
		Data data = sim.getData(); 
		
		Matrix matrix = new Matrix(1000, 1500, data); 
		matrix.addData();
		JFrame frame = new JFrame();
		frame.setSize(matrix.sizex, matrix.sizey);
		frame.setVisible(true);
		frame.setContentPane(matrix);
		
	}
}

//int d = (int)Math.sqrt(((x-prex)*(x-prex)) + (y-prey)*(y-prey));
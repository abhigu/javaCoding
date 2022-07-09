package driver;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dataStructures.Data;
import dataStructures.SimParam;
import viruses.Simulation;

public class Matrix extends JPanel {
	
	private int sizex;
	private int sizey;
	private int gx;
	private int gy;
	private String[][] graph;
	
	public Matrix(int sizex, int sizey, int gx, int gy) {
		this.sizex = sizex;
		this.sizey = sizey;
		this.gx = gx;
		this.gy = gy;
		graph = new String[this.sizey][this.sizex];
		
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
	
	
	@Override
	public void paintComponent(Graphics g) {
		int xoffset = 100;
		int yoffset = 900;
		
		g.drawLine(xoffset, 0, xoffset, 1000);
		g.drawLine(0, yoffset, 1400, yoffset);
		
		for(int i = 0; i < sizey; i++) {
			for(int j = 0; j < sizex; j++) {
				if (graph[i][j].equals("X")) {
					g.fillOval(((j) + xoffset - 5), ((i) + yoffset - 95), 10, 10);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Matrix matrix = new Matrix(1000, 1500, 1000, 1500); 
		
		/*
		matrix.addPoint(4, 7);
		matrix.addPoint(2, 1);
		matrix.addPoint(8, 5);
		matrix.addPoint(0, 5);
		matrix.addPoint(3, 0);
		
		matrix.addPoint(5, 5);
		matrix.addPoint(0, 0);
		
		matrix.printMatrix();
		*/
		
		
		
		Simulation sim = new Simulation(new SimParam(1000, 5, 0.05, 0.95, 7));
		sim.epidemic();
		Data data = sim.getData();
		
		for (int i = 0; i < data.getDailyData().size(); i++) {
			matrix.addPoint(data.getDailyData().get(i).getDay(), data.getDailyData().get(i).getDead());
		}
		
		JFrame frame = new JFrame();
		
		frame.setSize(matrix.gx, matrix.gy);
		frame.setVisible(true);
		frame.setContentPane(matrix);
		
	}
}

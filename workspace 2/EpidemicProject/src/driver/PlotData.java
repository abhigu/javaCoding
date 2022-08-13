package driver;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dataStructures.Data;
import dataStructures.SimParam;
import viruses.Simulation;

//Takes in a Data object and uses it to plot the data on a graph through the Gui.

//HW
//Make x and y axis dynamic. For x, if the x values have been unchanging for some time, stop the simulation. The last x value should be at the far right. For y, calculate the maximum y value, and put the max y value at the top of the window. 
//There is some very fascinating errors with the axis code, try to fix it.
//Get PlotData to work with Gui3
//Get multidata work in PlotData.
//Add ability for PlotData to graph Mutation data, like graphing RKSI values
//Add ability to change what piece of data you are graphing in Gui3, including Mutation data, like RKSI values

public class PlotData extends JPanel{
	private int sizex;
	private int sizey;
	private Data data;
	private List<Data> multidata;
	private int mag;
	
	private int xoffset; 
	private int yoffset;
	private int pointSize;
	private int prex;
	private int prey;
	private int maxX;
	private int maxY;
	private int scaleX;
	private int scaleY;
	private int iterator;
	
	public PlotData(int sizex, int sizey, Data data, int magnification) {
		mag = 1;
		xoffset = 100;
		yoffset = sizey - 100;
		pointSize = 2;
		prex = xoffset;
		prey = yoffset;
		iterator = 50;
		
		this.sizex = sizex;
		this.sizey = sizey;
		this.data = data;
		this.mag = magnification;
	}
	
	public void drawAxis(Graphics g) {
		g.drawLine(xoffset, 0, xoffset, 1000);
		g.drawLine(0, yoffset, 1400, yoffset);
	}
	
	public void drawTally(Graphics g) {
		calcX();
		calcY();
		int i = 0;
		for(int x = xoffset; x < super.getWidth(); x += iterator) {
			g.drawLine(x + (pointSize/2), yoffset-2, x + (pointSize/2), yoffset+1); 
			g.drawString(String.valueOf(scaleX*i), x + (pointSize/2), yoffset+15);
			i++;
		}
		i = 0;
		for(int y = yoffset; y > 0; y -= iterator) {
			
			g.drawLine(xoffset-2, y + (pointSize/2), xoffset+2, y + (pointSize/2));
			g.drawString(String.valueOf(scaleY*i), xoffset-30, y + (pointSize/2));
			i++;
		}
	}
	
	
	
	public int coordinateX(double j) {
		//return ((j*mag) + xoffset - (scale/2));
		return (int) (xoffset + (j/scaleX)*iterator);
	}
	
	public int coordinateY(double i) {
		//return (((i - sizey + 1)*mag) + yoffset - (scale/2));
		return (int) (yoffset - (i/scaleY)*iterator);
	}
	
	
	private int calcX() {
		scaleX = maxX/25;
		return scaleX;
	}
	
	private int calcY() {
		scaleY = maxY/25;
		return scaleY;
	}
	
	
	public void pointAndLines(Graphics g, int x, int y) {
		g.drawLine(xoffset-4, y + (pointSize/2), xoffset+4, y + (pointSize/2));	 				
		g.fillOval(x, y, (pointSize), (pointSize));
		g.drawLine(prex + (pointSize/2), prey + (pointSize), x + (pointSize/2), y + (pointSize/2));
	}
	
	

	public void plot (Graphics g) {
		prex = xoffset;
		prey = yoffset;
		for (int i = 0; i < data.getDailyData().size(); i++) {
			int x = coordinateX(data.getDailyData().get(i).getDay());
			int y = coordinateY(data.getDailyData().get(i).getInfected());
			pointAndLines(g, x, y);
			System.out.println(x + ", " + y + ", " + data.getDailyData().get(i).getDay() + ", " + data.getDailyData().get(i).getInfected());
			prex = x;
			prey = y;
		}
		System.out.println(maxX + ", " + maxY);
	}
	
	public void updateData(Data data) {
		this.data = data;
		prex = 0;
		prey = 0;
		for (int i = 0; i < data.getDailyData().size(); i++) {
			int size = data.getDailyData().get(i).getInfected();
			if (size > maxY) {
				maxY = size*2;
			}
			
			if(i > 100) {	
				if (data.getDailyData().get(i).getInfected() == data.getDailyData().get(i - 1).getInfected() && 
				data.getDailyData().get(i).getInfected() == data.getDailyData().get(i - 2).getInfected() &&
				data.getDailyData().get(i).getInfected() == data.getDailyData().get(i - 3).getInfected() &&
				data.getDailyData().get(i).getInfected() == data.getDailyData().get(i - 4).getInfected() &&
				data.getDailyData().get(i).getInfected() == data.getDailyData().get(i - 5).getInfected()
				) {
					maxX = i;
				}
			}
		}
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, sizex, sizey);
		g.setColor(Color.BLACK);
		
		drawAxis(g);
		drawTally(g); 
		
		plot(g);
		
	}
	
	
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		//PlotData plot = new PlotData(1400, 1500, sim);
		
		Simulation sim = new Simulation(new SimParam(500, 5, 0.01, 0.99, 15));
		sim.epidemic();
		Data data = sim.getData();
		
		//plot.updateData(data);
		
		//frame.setContentPane(plot);
		frame.setVisible(true);
		frame.setSize(1400, 1500);
		frame.setTitle("test");
	}
}

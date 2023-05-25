package iceberg;

import javax.swing.JOptionPane;

import iceberg.algorithms.MiddlePoint;
import iceberg.algorithms.Simmetry4;
import iceberg.algorithms.Simmetry8;
import iceberg.algorithms.SimpleCircle;

public class StartCircleDrawing {
	public static void main(String[] args) {
		boolean exit = false;
		int option = 5;
		final String MESSAGE = "Radius (Integer)";

		int radius = Integer.parseInt( JOptionPane.showInputDialog(MESSAGE) );
		
		while(exit != true) {
			option = Integer.parseInt( 
				JOptionPane.showInputDialog(
					"1 - Simple Circle\n" +
					"2 - Midpoint\n" +
					"3 - Symmetry 4\n" +
					"4 - Symmetry 8\n" +
					"5 - New Radius\n" +
					"6 - Credits\n" +
					"0 - Exit"
				)
			);
			
			if(option == 0) {
				exit = true;
			} else if(option == 1) {
				new SimpleCircle(0,0,radius);
			} else if(option == 2) {
				new MiddlePoint(0,0,radius);
			} else if(option == 3) {
				new Simmetry4(0,0,radius);
			} else if(option == 4) {
				new Simmetry8(0,0,radius);
			} else if(option == 5) {
				radius = Integer.parseInt( JOptionPane.showInputDialog(MESSAGE) );
			} else if(option == 6) {
				StringBuilder credits = new StringBuilder();
				credits.append("Credits:\n");
				credits.append("\n");
				credits.append("This project was created in the Graphic Computing discipline\n");
				credits.append("and aims to demonstrate some types of circle drawing based\n");
				credits.append("on mathematical calcs. In this project, its only shown the\n");
				credits.append("points of the circle that could be drawnd in a chart.\n");
				credits.append("\n");
				credits.append("This project also was updated to english language, to become\n");
				credits.append("more accessible to other programmers\n");
				credits.append("\n");
				credits.append("AUTHOR: Eduardo Eismann\n");
				credits.append("CREATE DATE: OCTOBER, 2008\n");
				credits.append("UPDATE DATE: MAY, 2023\n");
				credits.append("\n");

				JOptionPane.showMessageDialog(null, credits.toString());
			}
		}
	}
}

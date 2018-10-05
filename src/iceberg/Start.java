package iceberg;

import javax.swing.JOptionPane;

import iceberg.algorithms.MiddlePoint;
import iceberg.algorithms.Simmetry4;
import iceberg.algorithms.Simmetry8;
import iceberg.algorithms.SimpleCircle;

public class Start {
	public static void main(String[] args) {
		boolean exit = false;
		int option = 5;
		int radius = Integer.parseInt(
			JOptionPane.showInputDialog("Raio: ")
		);
		
		while(exit != true) {
			option = Integer.parseInt( 
				JOptionPane.showInputDialog("1 - Circulo Simples\n2 - Ponto Médio\n3 - Simetria 4\n4 - Simetria 8\n5 - Novo Raio\n0 - Sair")
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
				radius = Integer.parseInt(
					JOptionPane.showInputDialog("Raio: ")
				);
			}
		}
	}
}

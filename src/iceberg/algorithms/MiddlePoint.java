package iceberg.algorithms;

public class MiddlePoint {
	private Integer x;
	private Integer y;
	private Integer pi;
	
	public MiddlePoint(Integer xCentral, Integer yCentral, Integer radius) {
		x = 0;
		y = radius;
		pi = (5 - radius*4) / 4;
		circlePoints(xCentral, yCentral, x, y);
		
		while(x < y) {
			x++;
			
			if(pi < 0) {
				pi += 2*x+1;
			} else {
				y--;
				pi += 2*(x-y)+1;
			}
			
			circlePoints(xCentral, yCentral, x, y);
		}
	}
	
	private void circlePoints(Integer xCentral, Integer yCentral, Integer x, Integer y) {
		if(x == 0) {
			System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + y) + " , " + yCentral + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - y) + " , " + yCentral + "]");
		} else if(x == y) {
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral - y) + "]");
		} else if(x < y) {
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + y) + " , " + (yCentral + x) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - y) + " , " + (yCentral + x) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + y) + " , " + (yCentral - x) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - y) + " , " + (yCentral - x) + "]");
		}
	}
}

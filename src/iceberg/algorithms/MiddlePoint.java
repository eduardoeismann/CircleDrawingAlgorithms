package iceberg.algorithms;

public class MiddlePoint {
	private Integer x;
	private Integer y;
	private Integer pi;
	private Long startProcess = 0L;
	private Long endProcess = 0L;
	private Long quantity = 0L;
	
	public MiddlePoint(Integer xCentral, Integer yCentral, Integer radius) {
		startProcess = System.currentTimeMillis();
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
		
		endProcess = System.currentTimeMillis();
		
		System.out.println("Tempo de processamento: " + ( endProcess - startProcess ) );
		System.out.println("quantidade de pontos: " + quantity);
	}
	
	private void circlePoints(Integer xCentral, Integer yCentral, Integer x, Integer y) {
		if(x == 0) {
			System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + y) + " , " + yCentral + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - y) + " , " + yCentral + "]");
			quantity += 4;
		} else if(x == y) {
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral - y) + "]");
			quantity += 4;
		} else if(x < y) {
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + y) + " , " + (yCentral + x) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - y) + " , " + (yCentral + x) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + y) + " , " + (yCentral - x) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - y) + " , " + (yCentral - x) + "]");
			quantity += 8;
		}
	}
}

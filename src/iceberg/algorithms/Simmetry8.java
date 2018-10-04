package iceberg.algorithms;

public class Simmetry8 {
	private Integer x;
	private Integer y;
	private Integer rSqrt;
	
	public Simmetry8(Integer xCentral, Integer yCentral, Integer radius) {
		rSqrt = radius * radius;
		
		System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral + radius) + "]");
		System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral - radius) + "]");
		System.out.println("Ponto X,Y: [" + (xCentral + radius) + " , " + yCentral + "]");
		System.out.println("Ponto X,Y: [" + (xCentral - radius) + " , " + yCentral + "]");
		
		y = radius;
		x = 1;
		y = (int) (Math.sqrt(rSqrt - 1) + 0.5);
		
		while(x < y) {
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + y) + " , " + (yCentral + x) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + y) + " , " + (yCentral - x) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - y) + " , " + (yCentral + x) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - y) + " , " + (yCentral - x) + "]");
			
			x += 1;
			y = (int) (Math.sqrt(rSqrt - x*x) + 0.5);
		}
		
		if(x == y) {
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral - y) + "]");
		}
	}
}

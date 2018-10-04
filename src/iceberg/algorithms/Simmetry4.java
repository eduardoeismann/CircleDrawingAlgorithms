package iceberg.algorithms;

public class Simmetry4 {
	private Integer x;
	private Integer y;
	private Integer rSqrt;
	
	public Simmetry4(Integer xCentral, Integer yCentral, Integer radius) {
		rSqrt = radius * radius;
		
		System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral + radius) + "]");
		System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral - radius) + "]");
		
		for(x = 0; x <= radius; x++) {
			y = (int) (Math.sqrt(rSqrt - x*x) + 0.5);
			System.out.println(x);
			System.out.println(y);
			System.out.println();
			
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral - y) + "]");
		}
	}
}

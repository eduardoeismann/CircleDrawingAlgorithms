package iceberg.algorithms;

public class SimpleCircle {
	private Integer x;
	private Integer y;
	private Integer rSqrt;
	
	public SimpleCircle(Integer xCentral, Integer yCentral, Integer radius) {
		rSqrt = radius * radius;
		
		for(x = -radius; x <= radius; x++) {
			y = (int) (Math.sqrt(rSqrt - x*x) + 0.5);
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral - y) + "]");
		}
	}
	
}

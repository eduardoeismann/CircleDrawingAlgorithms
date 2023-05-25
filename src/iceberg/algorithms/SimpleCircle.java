package iceberg.algorithms;

public class SimpleCircle {
	private Integer x;
	private Integer y;
	private Integer rSqrt;
	private Long startProcess = 0L;
	private Long endProcess = 0L;
	private Long quantity = 0L;
	
	public SimpleCircle(Integer xCentral, Integer yCentral, Integer radius) {
		startProcess = System.currentTimeMillis();
		rSqrt = radius * radius;
		
		for(x = -radius; x <= radius; x++) {
			y = (int) (Math.sqrt(rSqrt - x*x) + 0.5);
			System.out.println("Point X,Y: [" + (xCentral + x) + " , " + (yCentral + y) + "]");
			System.out.println("Point X,Y: [" + (xCentral + x) + " , " + (yCentral - y) + "]");
			quantity += 2;
		}
		
		endProcess = System.currentTimeMillis();
		
		System.out.println("Time processing: " + ( endProcess - startProcess ) + " milliseconds.");
		System.out.println("Number of points: " + quantity);
	}
	
}

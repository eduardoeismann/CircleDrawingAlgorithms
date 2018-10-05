package iceberg.algorithms;

public class Simmetry4 {
	private Integer x;
	private Integer y;
	private Integer rSqrt;
	private Long startProcess = 0L;
	private Long endProcess = 0L;
	private Long quantity = 0L;
	
	public Simmetry4(Integer xCentral, Integer yCentral, Integer radius) {
		startProcess = System.currentTimeMillis();
		rSqrt = radius * radius;
		
		System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral + radius) + "]");
		System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral - radius) + "]");
		quantity += 2;
		
		for(x = 0; x <= radius; x++) {
			y = (int) (Math.sqrt(rSqrt - x*x) + 0.5);
			
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral + x) + " , " + (yCentral - y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral + y) + "]");
			System.out.println("Ponto X,Y: [" + (xCentral - x) + " , " + (yCentral - y) + "]");
			quantity += 4;
		}
		
		endProcess = System.currentTimeMillis();
		
		System.out.println("Tempo de processamento: " + ( endProcess - startProcess ) );
		System.out.println("quantidade de pontos: " + quantity);
	}
}

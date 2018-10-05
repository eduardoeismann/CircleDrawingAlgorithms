package iceberg.algorithms;

public class Simmetry8 {
	private Integer x;
	private Integer y;
	private Integer rSqrt;
	private Long startProcess = 0L;
	private Long endProcess = 0L;
	private Long quantity = 0L;
	
	public Simmetry8(Integer xCentral, Integer yCentral, Integer radius) {
		startProcess = System.currentTimeMillis();
		rSqrt = radius * radius;
		
		System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral + radius) + "]");
		System.out.println("Ponto X,Y: [" + xCentral + " , " + (yCentral - radius) + "]");
		System.out.println("Ponto X,Y: [" + (xCentral + radius) + " , " + yCentral + "]");
		System.out.println("Ponto X,Y: [" + (xCentral - radius) + " , " + yCentral + "]");
		
		quantity += 4;
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
			quantity += 8;
			
			x += 1;
			y = (int) (Math.sqrt(rSqrt - x*x) + 0.5);
		}
		
		if(x == y) {
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

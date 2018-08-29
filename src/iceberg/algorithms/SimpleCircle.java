package iceberg.algorithms;

import java.awt.Color;

import iceberg.Start;

public class SimpleCircle {
	public SimpleCircle(int xCenter, int yCenter, int radius, Color c) {
		int pix = c.getRGB();
		int x, y, r2;
		
		r2 = radius * radius;
		
		Start start = new Start();
		
		for (x = -radius; x <= radius; x++) {
			
			y = (int) (Math.sqrt(r2 - x*x) + 0.5);
			start.setPixel(pix, xCenter + x, yCenter + y);
			start.setPixel(pix, xCenter + x, yCenter - y);
		}
		
	}
	
}

package iceberg.algorithms;

import java.awt.Color;

import iceberg.Start;

public class Simmetry4 {
	public Simmetry4( int xCenter, int yCenter, int radius, Color c ) {
		int pix = c.getRGB();
		int x, y, r2;
		
		Start start = new Start();
		
		r2 = radius * radius;
		
		start.setPixel(pix, xCenter, yCenter + radius);
		start.setPixel(pix, xCenter, yCenter - radius);

		for (x = 1; x <= radius; x++) {
			y = (int) (Math.sqrt(r2 - x*x) + 0.5);
			start.setPixel(pix, xCenter + x, yCenter + y);
			start.setPixel(pix, xCenter + x, yCenter - y);
			start.setPixel(pix, xCenter - x, yCenter + y);
			start.setPixel(pix, xCenter - x, yCenter - y);
		}
	}
	
}

package iceberg.algorithms;

import java.awt.Color;

import iceberg.Start;

public class Simmetry8 {
	public Simmetry8(int xCenter, int yCenter, int radius, Color c){
		int pix = c.getRGB();
		int x, y, r2;
		
		Start start = new Start();
		
		r2 = radius * radius;
		
		start.setPixel(pix, xCenter, yCenter + radius);
		start.setPixel(pix, xCenter, yCenter - radius);
		start.setPixel(pix, xCenter + radius, yCenter);
		start.setPixel(pix, xCenter - radius, yCenter);
		
		y = radius;
		x = 1;
		y = (int) (Math.sqrt(r2 - 1) + 0.5);

		while (x < y) {
			start.setPixel(pix, xCenter + x, yCenter + y);
			start.setPixel(pix, xCenter + x, yCenter - y);
			start.setPixel(pix, xCenter - x, yCenter + y);
			start.setPixel(pix, xCenter - x, yCenter - y);
			start.setPixel(pix, xCenter + y, yCenter + x);
			start.setPixel(pix, xCenter + y, yCenter - x);
			start.setPixel(pix, xCenter - y, yCenter + x);
			start.setPixel(pix, xCenter - y, yCenter - x);
			x += 1;
			y = (int) (Math.sqrt(r2 - x*x) + 0.5);
		}

		if (x == y) {
			start.setPixel(pix, xCenter + x, yCenter + y);
			start.setPixel(pix, xCenter + x, yCenter - y);
			start.setPixel(pix, xCenter - x, yCenter + y);
			start.setPixel(pix, xCenter - x, yCenter - y);
		}
	}
	
}

package iceberg.algorithms;

import java.awt.Color;

public class Simmetry8 {
	public Simmetry8() {
		// TODO Auto-generated constructor stub
	}
	
	public void CircleSimmetry8(int xCenter, int yCenter, int radius, Color c){
		int pix = c.getRGB();
		int x, y, r2;
		r2 = radius * radius;
		raster.setPixel(pix, xCenter, yCenter + radius);
		raster.setPixel(pix, xCenter, yCenter - radius);
		raster.setPixel(pix, xCenter + radius, yCenter);
		raster.setPixel(pix, xCenter - radius, yCenter);
		y = radius;
		x = 1;
		y = (int) (Math.sqrt(r2 - 1) + 0.5);

		while (x < y) {
			raster.setPixel(pix, xCenter + x, yCenter + y);
			raster.setPixel(pix, xCenter + x, yCenter - y);
			raster.setPixel(pix, xCenter - x, yCenter + y);
			raster.setPixel(pix, xCenter - x, yCenter - y);
			raster.setPixel(pix, xCenter + y, yCenter + x);
			raster.setPixel(pix, xCenter + y, yCenter - x);
			raster.setPixel(pix, xCenter - y, yCenter + x);
			raster.setPixel(pix, xCenter - y, yCenter - x);
			x += 1;
			y = (int) (Math.sqrt(r2 - x*x) + 0.5);
		}

		if (x == y) {
			raster.setPixel(pix, xCenter + x, yCenter + y);
			raster.setPixel(pix, xCenter + x, yCenter - y);
			raster.setPixel(pix, xCenter - x, yCenter + y);
			raster.setPixel(pix, xCenter - x, yCenter - y);
		}
	}
	
}

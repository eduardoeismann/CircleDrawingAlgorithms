package iceberg.algorithms;

import java.awt.Color;

public class SimpleCircle {
	public SimpleCircle() {
		// TODO Auto-generated constructor stub
	}
	
	public void SimpleCircle(int xCenter, int yCenter, int radius, Color c) {
		int pix = c.getRGB();
		int x, y, r2;
		r2 = radius * radius;

		for (x = -radius; x <= radius; x++) {
			y = (int) (Math.sqrt(r2 - x*x) + 0.5);
			raster.setPixel(pix, xCenter + x, yCenter + y);
			raster.setPixel(pix, xCenter + x, yCenter - y);
		}
	}
	
}

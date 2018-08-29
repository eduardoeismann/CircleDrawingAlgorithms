package iceberg.algorithms;

import java.awt.Color;

public class Simmetry4 {
	public Simmetry4() {
		// TODO Auto-generated constructor stub
	}
	
	public void simmetry4( int xCenter, int yCenter, int radius, Color c ) {
		int pix = c.getRGB();
		int x, y, r2;
		r2 = radius * radius;
		raster.setPixel(pix, xCenter, yCenter + radius);
		raster.setPixel(pix, xCenter, yCenter - radius);

		for (x = 1; x <= radius; x++) {
			y = (int) (Math.sqrt(r2 - x*x) + 0.5);
			raster.setPixel(pix, xCenter + x, yCenter + y);
			raster.setPixel(pix, xCenter + x, yCenter - y);
			raster.setPixel(pix, xCenter - x, yCenter + y);
			raster.setPixel(pix, xCenter - x, yCenter - y);
		}
	}
	
}

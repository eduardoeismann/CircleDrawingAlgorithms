package iceberg.algorithms;

import java.awt.Color;

public class MiddlePoint {
	
	public MiddlePoint() {
		// TODO Auto-generated constructor stub
	}
	
	public void CircleMiddlepoint(int xCenter, int yCenter, int radius, Color c){
		int pix = c.getRGB();
		int x = 0;
		int y = radius;
		int p = (5 - radius*4)/4;
		circlePoints(xCenter, yCenter, x, y, pix);
		
		while (x < y) {
			x++;
			
			if (p < 0) {
				p += 2*x+1;
			} else {
				y--;
				p += 2*(x-y)+1;
			}
			
			circlePoints(xCenter, yCenter, x, y, pix);
		}
	}
	
	private final void circlePoints(int cx, int cy, int x, int y, int pix){
		int act = Color.red.getRGB();
		
		if (x == 0) {
			raster.setPixel(act, cx, cy + y);
			raster.setPixel(pix, cx, cy - y);
			raster.setPixel(pix, cx + y, cy);
			raster.setPixel(pix, cx - y, cy);
		} else if (x == y) {
			raster.setPixel(act, cx + x, cy + y);
			raster.setPixel(pix, cx - x, cy + y);
			raster.setPixel(pix, cx + x, cy - y);
			raster.setPixel(pix, cx - x, cy - y);
		} else if (x < y) {
			raster.setPixel(act, cx + x, cy + y);
			raster.setPixel(pix, cx - x, cy + y);
			raster.setPixel(pix, cx + x, cy - y);
			raster.setPixel(pix, cx - x, cy - y);
			raster.setPixel(pix, cx + y, cy + x);
			raster.setPixel(pix, cx - y, cy + x);
			raster.setPixel(pix, cx + y, cy - x);
			raster.setPixel(pix, cx - y, cy - x);
		}
	}

}

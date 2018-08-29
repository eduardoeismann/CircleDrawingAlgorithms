package iceberg.algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import iceberg.Start;

public class MiddlePoint {
	public MiddlePoint(int xCenter, int yCenter, int radius, Color c){
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
	
	private void circlePoints(int cx, int cy, int x, int y, int pix){
		int act = Color.red.getRGB();
		
		Start start = new Start();
		
		if (x == 0) {
			start.setPixel(act, cx, cy + y);
			start.setPixel(pix, cx, cy - y);
			start.setPixel(pix, cx + y, cy);
			start.setPixel(pix, cx - y, cy);
		} else if (x == y) {
			start.setPixel(act, cx + x, cy + y);
			start.setPixel(pix, cx - x, cy + y);
			start.setPixel(pix, cx + x, cy - y);
			start.setPixel(pix, cx - x, cy - y);
		} else if (x < y) {
			start.setPixel(act, cx + x, cy + y);
			start.setPixel(pix, cx - x, cy + y);
			start.setPixel(pix, cx + x, cy - y);
			start.setPixel(pix, cx - x, cy - y);
			start.setPixel(pix, cx + y, cy + x);
			start.setPixel(pix, cx - y, cy + x);
			start.setPixel(pix, cx + y, cy - x);
			start.setPixel(pix, cx - y, cy - x);
		}
	}

}

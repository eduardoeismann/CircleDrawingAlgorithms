package iceberg;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

import iceberg.algorithms.SimpleCircle;

public class Start {
	private int width, height;
	private int pixel[];
	
	public Start() {}
	
    public Start( int w, int h ) {
        width = w;
        height = h;
        pixel = new int[ w * h ];
    }
    
    public Start( Image img ) throws InterruptedException {
        PixelGrabber grabber = new PixelGrabber( img, 0, 0, -1, -1, true );
        if( grabber.grabPixels() ) {
            width = grabber.getWidth();
            height = grabber.getHeight();
            pixel = ( int [] ) grabber.getPixels();
        }
    }
    
    public final int size() {
		return pixel.length;
	}

	public final void fill( Color c ) {
		int s = size();
		int rgb = c.getRGB();
		for( int i = 0; i < s; i++ ) {
			pixel[ i ] = rgb;
		}
	}
	
	public final Image toImage( Component root ) {
		return root.createImage( new MemoryImageSource( width, height, pixel, 0, width ) );
	}

	public final int getPixel( int x, int y ) {
		if( x < 0 ) {
			x = 0;
		}
		
		if( x >= width ) {
			x = width - 1;
		}
		
		if( y < 0 ) {
			y = 0;
		}
		
		if( y >= height ) {
			y = height - 1;
		}

		return pixel[ y * width + x ];
	}
	
	public final Color getColor( int x, int y ) {
		if( x < 0 ) {
			x = 0;
		}
		
		if( x >= width ) {
			x = width - 1;
		}
		
		if( y < 0 ) {
			y = 0;
		}
		
		if( y >= height ) {
			y = height - 1;
		}

		return new Color( pixel[ y * width + x ] );
	}

	public boolean setPixel( int pix, int x, int y ) {
		if( ( x < 0 ) || ( x >= width ) || ( y < 0 ) || ( y >= height ) ) {
			return false;
		}
		
		pixel[ y * width + x ] = pix;
		return true;
	}
	
	public boolean setColor( Color c, int x, int y ) {
		if( ( x < 0 ) || ( x >= width ) || ( y < 0 ) || ( y >= height ) ) {
			return false;
		}
		
		pixel[ y * width + x ] = c.getRGB();
		return true;
	}

	public void lineSimple( int x0, int y0, int x1, int y1, Color color ) {
		int pix = color.getRGB();
		int dx = x1 - x0;
		int dy = y1 - y0;
		setPixel( pix, x0, y0 );
		
		if( dx != 0 ) {
			float m = ( float ) dy / ( float ) dx;
			float b = y0 - m * x0;
			dx = ( x1 > x0 ) ? 1 : -1;
			while( x0 != x1 ) {
				x0 += dx;
				y0 = Math.round( m * x0 + b );
				setPixel( pix, x0, y0 );
			}
		}
	}
	
	public void lineSimple( Point P0, Point P1, Color color ) {
		lineSimple( P0.x, P0.y, P1.x, P1.y, color );
	}

	public void lineImproved( int x0, int y0, int x1, int y1, Color color ) {
		int pix = color.getRGB();
		int dx = x1 - x0;
		int dy = y1 - y0;
		setPixel( pix, x0, y0 );
		
		if( Math.abs( dx ) > Math.abs( dy ) ) {
			float m = ( float ) dy / ( float ) dx;
			float b = y0 - m * x0;
			dx = ( dx < 0 ) ? -1 : 1;

			while( x0 != x1 ) {
				x0 += dx;
				setPixel( pix, x0, Math.round( m * x0 + b ) );
			}
		} else if( dy != 0 ) {
			float m = ( float ) dx / ( float ) dy;
			float b = x0 - m * y0;
			dy = ( dy < 0 ) ? -1 : 1;
			while( y0 != y1 ) {
				y0 += dy;
				setPixel( pix, Math.round( m * y0 + b ), y0 );
			}
		}
	}
	
	public void lineImproved( Point P0, Point P1, Color color ) {
		lineImproved( P0.x, P0.y, P1.x, P1.y, color );
	}

	public void lineDDA( int x0, int y0, int x1, int y1, Color color ) {
		int pix = color.getRGB();
		int dy = y1 - y0;
		int dx = x1 - x0;
		float t = ( float ) 0.5;
		setPixel( pix, x0, y0 );

		if( Math.abs( dx ) > Math.abs( dy ) ) {
			float m = ( float ) dy / ( float ) dx;
			t += y0;
			dx = ( dx < 0 ) ? -1 : 1;
			m *= dx;
			while( x0 != x1 ) {
				x0 += dx;
				t += m;
				setPixel( pix, x0, ( int ) t );
			}
		} else {
			float m = ( float ) dx / ( float ) dy;
			t += x0;
			dy = ( dy < 0 ) ? -1 : 1;
			m *= dy;
			while (y0 != y1) {
				y0 += dy;
				t += m;
				setPixel( pix, ( int ) t, y0 );
			}
		}
	}

	public void lineDDA( Point P0, Point P1, Color color ) {
		lineDDA( P0.x, P0.y, P1.x, P1.y, color );
	}

	public void lineBresenham( int x0, int y0, int x1, int y1, Color color ) {
		int pix = color.getRGB();
		int dy = y1 - y0;
		int dx = x1 - x0;
		int stepx, stepy;
		if( dy < 0 ) {
			dy = -dy;
			stepy = -1;
		} else {
			stepy = 1;
		}
		if( dx < 0 ) {
			dx = -dx;
			stepx = -1;
		} else {
			stepx = 1;
		}
		
		dy <<= 1;
		dx <<= 1;

		setPixel( pix, x0, y0 );

		if( dx > dy ) {
			int fraction = dy - ( dx >> 1 );
			
			while( x0 != x1 ) {
				if( fraction >= 0 ) {
					y0 += stepy;
					fraction -= dx;
				}
				x0 += stepx;
				fraction += dy;
				setPixel( pix, x0, y0 );
			}
		} else {
			int fraction = dx - ( dy >> 1 );
			while( y0 != y1 ) {
				if( fraction >= 0 ) {
					x0 += stepx;
					fraction -= dy;
				}
				y0 += stepy;
				fraction += dx;
				setPixel( pix, x0, y0 );
			}
		}
	}

	public void lineBresenham( Point P0, Point P1, Color color ) {
		lineBresenham( P0.x, P0.y, P1.x, P1.y, color );
	}
    
	public void lineTwoStep( int x0, int y0, int x1, int y1, Color color ) {
		int pix = color.getRGB();
		int dy = y1 - y0;
		int dx = x1 - x0;
		int stepx, stepy;

		if( dy < 0 ) {
			dy = -dy;
			stepy = -1;
		} else {
			stepy = 1;
		}
		if( dx < 0 ) {
			dx = -dx;
			stepx = -1;
		} else {
			stepx = 1;
		}

		setPixel( pix, x0, y0 );
		setPixel( pix, x1, y1 );
		if( dx > dy ) { // refatorado ate aqui
			int length = (dx - 1) >> 2;
			int extras = (dx - 1) & 3;
			int incr2 = (dy << 2) - (dx << 1);
			if (incr2 < 0) {
				int c = dy << 1;
				int incr1 = c << 1;
				int d = incr1 - dx;
				for (int i = 0; i < length; i++) {
					x0 += stepx;
					x1 -= stepx;
					if (d < 0) { // Pattern:
						setPixel(pix, x0, y0); //
						setPixel(pix, x0 += stepx, y0); // x o o
						setPixel(pix, x1, y1); //
						setPixel(pix, x1 -= stepx, y1);
						d += incr1;
					} else {
						if (d < c) { // Pattern:
							setPixel(pix, x0, y0); // o
							setPixel(pix, x0 += stepx, y0 += stepy); // x o
							setPixel(pix, x1, y1); //
							setPixel(pix, x1 -= stepx, y1 -= stepy);
						} else {
							setPixel(pix, x0, y0 += stepy); // Pattern:
							setPixel(pix, x0 += stepx, y0); // o o
							setPixel(pix, x1, y1 -= stepy); // x
							setPixel(pix, x1 -= stepx, y1); //
						}
						d += incr2;
					}
				}
				if (extras > 0) {
					if (d < 0) {
						setPixel(pix, x0 += stepx, y0);
						if (extras > 1)
							setPixel(pix, x0 += stepx, y0);
						if (extras > 2)
							setPixel(pix, x1 -= stepx, y1);
					} else if (d < c) {
						setPixel(pix, x0 += stepx, y0);
						if (extras > 1)
							setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 2)
							setPixel(pix, x1 -= stepx, y1);
					} else {
						setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 1)
							setPixel(pix, x0 += stepx, y0);
						if (extras > 2)
							setPixel(pix, x1 -= stepx, y1 -= stepy);
					}
				}
			} else {
				int c = (dy - dx) << 1;
				int incr1 = c << 1;
				int d = incr1 + dx;
				for (int i = 0; i < length; i++) {
					x0 += stepx;
					x1 -= stepx;
					if (d > 0) {
						setPixel(pix, x0, y0 += stepy); // Pattern:
						setPixel(pix, x0 += stepx, y0 += stepy); // o
						setPixel(pix, x1, y1 -= stepy); // o
						setPixel(pix, x1 -= stepx, y1 -= stepy); // x
						d += incr1;
					} else {
						if (d < c) {
							setPixel(pix, x0, y0); // Pattern:
							setPixel(pix, x0 += stepx, y0 += stepy); // o
							setPixel(pix, x1, y1); // x o
							setPixel(pix, x1 -= stepx, y1 -= stepy); //
						} else {
							setPixel(pix, x0, y0 += stepy); // Pattern:
							setPixel(pix, x0 += stepx, y0); // o o
							setPixel(pix, x1, y1 -= stepy); // x
							setPixel(pix, x1 -= stepx, y1); //
						}
						d += incr2;
					}
				}
				if (extras > 0) {
					if (d > 0) {
						setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 1)
							setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 2)
							setPixel(pix, x1 -= stepx, y1 -= stepy);
					} else if (d < c) {
						setPixel(pix, x0 += stepx, y0);
						if (extras > 1)
							setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 2)
							setPixel(pix, x1 -= stepx, y1);
					} else {
						setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 1)
							setPixel(pix, x0 += stepx, y0);
						if (extras > 2) {
							if (d > c)
								setPixel(pix, x1 -= stepx, y1 -= stepy);
							else
								setPixel(pix, x1 -= stepx, y1);
						}
					}
				}
			}
		} else {
			int length = (dy - 1) >> 2;
			int extras = (dy - 1) & 3;
			int incr2 = (dx << 2) - (dy << 1);
			if (incr2 < 0) {
				int c = dx << 1;
				int incr1 = c << 1;
				int d = incr1 - dy;
				for (int i = 0; i < length; i++) {
					y0 += stepy;
					y1 -= stepy;
					if (d < 0) {
						setPixel(pix, x0, y0);
						setPixel(pix, x0, y0 += stepy);
						setPixel(pix, x1, y1);
						setPixel(pix, x1, y1 -= stepy);
						d += incr1;
					} else {
						if (d < c) {
							setPixel(pix, x0, y0);
							setPixel(pix, x0 += stepx, y0 += stepy);
							setPixel(pix, x1, y1);
							setPixel(pix, x1 -= stepx, y1 -= stepy);
						} else {
							setPixel(pix, x0 += stepx, y0);
							setPixel(pix, x0, y0 += stepy);
							setPixel(pix, x1 -= stepx, y1);
							setPixel(pix, x1, y1 -= stepy);
						}
						d += incr2;
					}
				}
				if (extras > 0) {
					if (d < 0) {
						setPixel(pix, x0, y0 += stepy);
						if (extras > 1)
							setPixel(pix, x0, y0 += stepy);
						if (extras > 2)
							setPixel(pix, x1, y1 -= stepy);
					} else if (d < c) {
						setPixel(pix, stepx, y0 += stepy);
						if (extras > 1)
							setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 2)
							setPixel(pix, x1, y1 -= stepy);
					} else {
						setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 1)
							setPixel(pix, x0, y0 += stepy);
						if (extras > 2)
							setPixel(pix, x1 -= stepx, y1 -= stepy);
					}
				}
			} else {
				int c = (dx - dy) << 1;
				int incr1 = c << 1;
				int d = incr1 + dy;
				for (int i = 0; i < length; i++) {
					y0 += stepy;
					y1 -= stepy;
					if (d > 0) {
						setPixel(pix, x0 += stepx, y0);
						setPixel(pix, x0 += stepx, y0 += stepy);
						setPixel(pix, x1 -= stepy, y1);
						setPixel(pix, x1 -= stepx, y1 -= stepy);
						d += incr1;
					} else {
						if (d < c) {
							setPixel(pix, x0, y0);
							setPixel(pix, x0 += stepx, y0 += stepy);
							setPixel(pix, x1, y1);
							setPixel(pix, x1 -= stepx, y1 -= stepy);
						} else {
							setPixel(pix, x0 += stepx, y0);
							setPixel(pix, x0, y0 += stepy);
							setPixel(pix, x1 -= stepx, y1);
							setPixel(pix, x1, y1 -= stepy);
						}
						d += incr2;
					}
				}
				if (extras > 0) {
					if (d > 0) {
						setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 1)
							setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 2)
							setPixel(pix, x1 -= stepx, y1 -= stepy);
					} else if (d < c) {
						setPixel(pix, x0, y0 += stepy);
						if (extras > 1)
							setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 2)
							setPixel(pix, x1, y1 -= stepy);
					} else {
						setPixel(pix, x0 += stepx, y0 += stepy);
						if (extras > 1)
							setPixel(pix, x0, y0 += stepy);
						if (extras > 2) {
							if (d > c)
								setPixel(pix, x1 -= stepx, y1 -= stepy);
							else
								setPixel(pix, x1, y1 -= stepy);
						}
					}
				}
			}
		}
	}
	
	public void lineTwoStep( Point P0, Point P1, Color color ) {
		lineTwoStep( P0.x, P0.y, P1.x, P1.y, color );
	}

	public void circleSimple( int xCenter, int yCenter, int radius, Color c ) {
		int pix = c.getRGB();
		int x, y, r2;

		r2 = radius * radius;
		for( x = -radius; x <= radius; x++ ) {
			y = ( int )( Math.sqrt( r2 - x * x ) + 0.5 );
			setPixel( pix, xCenter + x, yCenter + y );
			setPixel( pix, xCenter + x, yCenter - y );
		}
	}
	
	public void circleSimple(int x0, int y0, int x1, int y1, Color color) {
		int radius = (int) (Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0)) + 0.5);
		circleSimple(x0, y0, radius, color);
	}

	public void circleSimple(Point P0, Point P1, Color color) {
		circleSimple(P0.x, P0.y, P1.x, P1.y, color);
	}
	
    public void circleSym4(int xCenter, int yCenter, int radius, Color c) {
		int pix = c.getRGB();
		int x, y, r2;

		r2 = radius * radius;
		setPixel(pix, xCenter, yCenter + radius);
		setPixel(pix, xCenter, yCenter - radius);
		for (x = 1; x <= radius; x++) {
			y = (int) (Math.sqrt(r2 - x * x) + 0.5);
			setPixel(pix, xCenter + x, yCenter + y);
			setPixel(pix, xCenter + x, yCenter - y);
			setPixel(pix, xCenter - x, yCenter + y);
			setPixel(pix, xCenter - x, yCenter - y);
		}
	}

	public void circleSym4(int x0, int y0, int x1, int y1, Color color) {
		int radius = (int) (Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0)) + 0.5);
		circleSym4(x0, y0, radius, color);
	}

	public void circleSym4(Point P0, Point P1, Color color) {
		circleSym4(P0.x, P0.y, P1.x, P1.y, color);
	}

	public void circleSym8(int xCenter, int yCenter, int radius, Color c) {
		int pix = c.getRGB();
		int x, y, r2;

		r2 = radius * radius;
		setPixel(pix, xCenter, yCenter + radius);
		setPixel(pix, xCenter, yCenter - radius);
		setPixel(pix, xCenter + radius, yCenter);
		setPixel(pix, xCenter - radius, yCenter);

		y = radius;
		x = 1;
		y = (int) (Math.sqrt(r2 - 1) + 0.5);
		while (x < y) {
			setPixel(pix, xCenter + x, yCenter + y);
			setPixel(pix, xCenter + x, yCenter - y);
			setPixel(pix, xCenter - x, yCenter + y);
			setPixel(pix, xCenter - x, yCenter - y);
			setPixel(pix, xCenter + y, yCenter + x);
			setPixel(pix, xCenter + y, yCenter - x);
			setPixel(pix, xCenter - y, yCenter + x);
			setPixel(pix, xCenter - y, yCenter - x);
			x += 1;
			y = (int) (Math.sqrt(r2 - x * x) + 0.5);
		}
		if (x == y) {
			setPixel(pix, xCenter + x, yCenter + y);
			setPixel(pix, xCenter + x, yCenter - y);
			setPixel(pix, xCenter - x, yCenter + y);
			setPixel(pix, xCenter - x, yCenter - y);
		}
	}

	public void circleSym8(int x0, int y0, int x1, int y1, Color color) {
		int radius = (int) (Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0)) + 0.5);
		circleSym8(x0, y0, radius, color);
	}
    
	public void circleSym8(Point P0, Point P1, Color color) {
		circleSym8(P0.x, P0.y, P1.x, P1.y, color);
	}

	private final void circlePoints(int cx, int cy, int x, int y, int pix) {
		int act = Color.red.getRGB();

		if (x == 0) {
			setPixel(act, cx, cy + y);
			setPixel(pix, cx, cy - y);
			setPixel(pix, cx + y, cy);
			setPixel(pix, cx - y, cy);
		} else if (x == y) {
			setPixel(act, cx + x, cy + y);
			setPixel(pix, cx - x, cy + y);
			setPixel(pix, cx + x, cy - y);
			setPixel(pix, cx - x, cy - y);
		} else if (x < y) {
			setPixel(act, cx + x, cy + y);
			setPixel(pix, cx - x, cy + y);
			setPixel(pix, cx + x, cy - y);
			setPixel(pix, cx - x, cy - y);
			setPixel(pix, cx + y, cy + x);
			setPixel(pix, cx - y, cy + x);
			setPixel(pix, cx + y, cy - x);
			setPixel(pix, cx - y, cy - x);
		}
	}

	public void circleMidpoint(int xCenter, int yCenter, int radius, Color c) {
		int pix = c.getRGB();
		int x = 0;
		int y = radius;
		// int p = (5 - radius*4)/4;
		int p = 1 - radius;

		System.out.println("r : " + radius + "\t p : " + p + "\t x : " + x + "\t y : " + y);
		circlePoints(xCenter, yCenter, x, y, pix);
		while (x < y) {
			x++;
			if (p < 0) {
				p += 2 * x + 1;
			} else {
				y--;
				p += 2 * (x - y + 1);
			}
			System.out.println("r : " + radius + "\t p : " + p + "\t x : " + x + "\t y : " + y);
			circlePoints(xCenter, yCenter, x, y, pix);
		}
	}

	public void circleMidpoint(int x0, int y0, int x1, int y1, Color color) {
		int radius = (int) (Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0)) + 0.5);
		circleMidpoint(x0, y0, radius, color);
	}

	public void circleMidpoint(Point P0, Point P1, Color color) {
		circleMidpoint(P0.x, P0.y, P1.x, P1.y, color);
	}
	
	public static void main(String args[]) {
		System.out.println("--start--");
		SimpleCircle simpleCircle = new SimpleCircle(10, 10, 150, Color.GREEN);
		System.out.println("--finished--");
	}
}

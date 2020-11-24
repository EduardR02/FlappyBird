import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Obstacle {
	
	Random r = new Random();
	
	int x,gapsize,y,width;
	static int dx = 2;
	boolean passed = false;
	Rectangle2D square1;
	Rectangle2D square2;
	Color c = new Color(255, 77, 77);
	
	public Obstacle() {
		width = 50;
		x = Main.width;
		gapsize = 150;
		y = r.nextInt(Main.height - 75*2 - gapsize) + 75 + gapsize;	
		square1 = new Rectangle2D.Double(x + width/2, 0, width, y - gapsize);
		square2 = new Rectangle2D.Double(x + width/2, y, width, Main.height);
	}
	

	
	public void show(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(c);
		g2.fill(square1);
		g2.fill(square2);
	}
	
	public void update() {
		x -= dx;
		square1.setRect(x + width/2, 0, width, y - gapsize);
		square2.setRect(x + width/2, y, width, Main.height);
	}
	
	public void setPassed(boolean p) {
		this.passed = p;
	}
	
	public int getX() {
		return x;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setC(Color kk) {
		this.c = kk;
	}
	
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

public class Bird {

	int y,x;
	public static double dy;
	Rectangle2D d;
	Image img = Toolkit.getDefaultToolkit().getImage("FlappyBird2.png");
	
	public Bird() {
		y = Main.height/2;
		x = Main.width/2;
		dy = -12;
		d = new Rectangle2D.Double(x, y, img.getWidth(null), img.getHeight(null));
	}
	
	public void show(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(244,22,44));
		g2.drawImage(img, x, y, null);
	}
	
	public void update() {
		dy += 0.6;
		y += dy;
		if(dy < -15)
			dy = -15;
		if(y <= 0)
			y = 0;
		if(y >= Main.height) {
			Main.running = false;
			Main.gameover = true;
		}
		d.setRect(x, y, img.getWidth(null), img.getHeight(null));
	}
	
	public int getX() {
		return x;
	}
	
}
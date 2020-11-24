import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JPanel implements ActionListener,KeyListener{
	
	private static final long serialVersionUID = 1L;
	static int speed = 10;
	static int width = 800, height = 600;
	int counter = 0;
	Timer ti = new Timer(speed,this);
	Bird p;
	LinkedList<Obstacle> obs;
	static boolean gameover = true;   
	static boolean running = false;
	static int highscore = 0;
	static int score = 0;
	Color col = new Color(0,250,154);
	
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("FlappyBird");
		f.setContentPane(new Main());
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		
	}
	
	public Main() {
		setPreferredSize(new Dimension(width,height));
		setFocusable(true);								//for KeyListener		
		requestFocus();	
		addKeyListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g2);
		setBackground(new Color(23,23,23));
		g2.setColor(new Color(255,70,70));
		g2.setFont(new Font("Courier New", Font.BOLD, 24));
		
		if (!gameover) {
			for(Obstacle k : obs) {
				k.show(g2);
			}
			p.show(g2);
			if(score >= highscore)
			g2.setColor(col);
			g2.drawString("Score:" + score, width - 150, 25);
		}
		
		else {
			g2.drawString("Press SPACE to start", width / 2 - 150, height / 2);
			g2.setColor(col);
			g2.drawString("Highscore:" + highscore, width / 2 - 150, height / 2 + 25);
			ti.stop();
		}
		
	}
	
	public static boolean testIntersection(Shape shapeA, Shape shapeB) {
		   Area areaA = new Area(shapeA);
		   areaA.intersect(new Area(shapeB));
		   return !areaA.isEmpty();
		}
	
	public void setupLevel() {
		if(!gameover) {
			running = true;
			obs = new LinkedList<Obstacle>();
			p = new Bird();
			counter = 50;
			score = 0;
			ti.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		p.update();
		if(counter >= 250 / Obstacle.dx) {
			Obstacle o = new Obstacle();
			obs.push(o);
			counter = 0;
		}
		
		for(int i = obs.size() - 1; i >= 0; i--) {
			obs.get(i).update();
			
			if(!obs.get(i).passed && (obs.get(i).getX() + obs.get(i).getWidth()) < p.getX()-p.img.getWidth(null)) {
				obs.get(i).setPassed(true);
				score++;
				obs.get(i).setC(col);
				if(highscore < score) {
						highscore = score;
				}
			}
			
			if(testIntersection(p.d,obs.get(i).square1) || testIntersection(p.d,obs.get(i).square2)) {
				gameover = true;
				running = false;
			}
			
			if(obs.get(i).x < (0 - obs.get(i).width*1.5)) {
				obs.remove(i);
			}
		}
		counter ++;
		repaint();
	}
	
	public void keyPressed(KeyEvent t) {
		int k = t.getKeyCode();
		
		if (k == KeyEvent.VK_SPACE) {
			if(!gameover && running)
			Bird.dy = -12;
		}
		if (gameover && !running) {
			gameover = false;
			setupLevel();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

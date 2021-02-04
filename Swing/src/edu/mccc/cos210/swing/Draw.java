package edu.mccc.cos210.swing;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Draw
{
	public Draw()
	{
		JFrame jf = new JFrame("Draw");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jf.setSize(new Dimension(100, 100));
		JPanel jp = new MyJPanel();
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	public static void main(String[] args)
	{	
		EventQueue.invokeLater(Draw::new);
	}
	class MyJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int FPS = 30;
		private long lastTime = 0;
		private double rotation1 = 0.0;
		private double rotDelta1 = 1.0;
		private double rotation2 = 0.0;
		private double rotDelta2 = -0.5;
		private double translation3 = 0.0;
		private double transDelta3 = 2.0;
		
		public MyJPanel() {
			setBackground(Color.cyan.darker());
			setPreferredSize(new Dimension(1024, 768));
			new Timer(1000 / FPS, ae -> repaint()).start();
			lastTime = System.currentTimeMillis();
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			long now = System.currentTimeMillis();
			System.out.println(now - lastTime);
			lastTime = now;
			Graphics2D g2d = (Graphics2D) g.create();
			AffineTransform gat = new AffineTransform();
			gat.translate(getWidth() / 2.0, getHeight() / 2.0);
			gat.scale(1.0, -1.0);
			g2d.transform(gat);
			g2d.setStroke(new BasicStroke(5.0f));
			
			Rectangle2D r2d= new Rectangle2D.Double(
				-200.0, -200.0, 400.0, 400.0
			);
			
			AffineTransform at = new AffineTransform();
			at.rotate(Math.toRadians(rotation1));
			rotation1 += rotDelta1;
			//at.translate(100.0, -100.0);
			//at.rotate(Math.toRadians(22.5));
			Shape s = at.createTransformedShape(r2d);
			g2d.setPaint(new Color(128, 128, 0));
			g2d.fill(s);
			g2d.setPaint(Color.BLACK);
			g2d.draw(s);
			
			Ellipse2D e2d = new Ellipse2D.Double(
				-100.0, -150.0, 200.0, 300.0
			);
			at = new AffineTransform();
			at.rotate(Math.toRadians(rotation2));
			rotation2 += rotDelta2;
			s = at.createTransformedShape(e2d);
			g2d.draw(s);
			
			Path2D p2d = new Path2D.Double();
			p2d.moveTo(0.0,  50.0);
			p2d.lineTo(50.0, -50.0);
			p2d.lineTo(-50.0, -50.0);
			p2d.closePath();
			at = new AffineTransform();
			at.translate(0.0,  translation3);
			translation3 += transDelta3;
			if (translation3 > 250.0 || translation3 < -250.0) {
				transDelta3 = -transDelta3;
			}
			s = at.createTransformedShape(p2d);
			g2d.setPaint(new Color(255, 0, 255, 100));
			g2d.fill(p2d);
			g2d.setPaint(Color.BLACK);
			g2d.draw(p2d);
			
			g2d.dispose();
			Toolkit.getDefaultToolkit().sync();
		}
	}
}
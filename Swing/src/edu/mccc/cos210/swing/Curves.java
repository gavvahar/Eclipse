package edu.mccc.cos210.swing;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class Curves {

	public Curves() {
		JFrame jf = new JFrame("Curves");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new MyJPanel();
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(Curves::new);
	}

	class MyJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int FPS = 30;
		private long lastTime = 0;

		public MyJPanel() {
			setBackground(new Color(200, 200, 255));
			setPreferredSize(new Dimension(800, 600));
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
			
			Ellipse2D e2d = new Ellipse2D.Double(-10.0, -10.0, 20.0, 20.0);
			g2d.setPaint(Color.RED);
			g2d.fill(e2d);
			//QuadCurve2D qc2d = new QuadCurve2D.Double(0.0, 0.0, 100.0, 100.0, 200.0, 100.0);
			//Arc2D a2d = new Arc2D.Double(-100.0, 100.0, 200.0, 200.0, 0.0, -90.0, Arc2D.CHORD);
			//CubicCurve2D cc2d = new CubicCurve2D.Double(0.0, 0.0, 100.0, 100.0, -200.0, 200.0, 100.0, 100.0);
			Path2D p2d = new Path2D.Double();
			p2d.moveTo(0.0, 0.0);
			p2d.quadTo(100.0, 100.0, 200.0, 200.0);
			p2d.curveTo(200.0, 200.0, -100.0, 100.0, -100.0, 0.0);
			p2d.lineTo(300.0, 200.0);
			p2d.closePath();
			g2d.setPaint(Color.PINK);
			g2d.fill(p2d);
			g2d.setPaint(Color.BLACK);
			g2d.setStroke(new BasicStroke(3.0f));
			//g2d.draw(qc2d);
			//g2d.draw(cc2d);
			//g2d.draw(a2d);

			g2d.dispose();
			Toolkit.getDefaultToolkit().sync();
		}
	}

}

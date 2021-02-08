package edu.mccc.cos210.swing;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class PM {

	public PM() {
		JFrame jf = new JFrame("PM");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new MyJPanel();
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(PM::new);
	}

	class MyJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int FPS = 30;
		private long lastTime = 0;

		public MyJPanel() {
			setBackground(Color.WHITE);
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
			
			
			Arc2D a2d = new Arc2D.Double(
				-1.0, -1.0, 2.0, 2.0,
				90.0, 90.0,
				Arc2D.PIE
			);
			AffineTransform at = new AffineTransform();
			at.scale(200.0, 200.0);
			Shape s = at.createTransformedShape(a2d);
			g2d.setPaint(Color.BLUE);
			g2d.fill(s);
			g2d.setPaint(Color.BLACK);
			g2d.setStroke(new BasicStroke(5.0f));
			g2d.draw(s);
			g2d.dispose();
			Toolkit.getDefaultToolkit().sync();
		}
	}

}

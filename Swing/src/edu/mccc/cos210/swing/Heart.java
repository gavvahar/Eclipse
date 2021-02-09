package edu.mccc.cos210.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Heart {

	public Heart() {
		JFrame jf = new JFrame("Heart");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new MyJPanel();
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(Heart::new);
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

			g2d.dispose();
			Toolkit.getDefaultToolkit().sync();
		}
	}

}

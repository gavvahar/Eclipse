package edu.mccc.cos210.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import edu.mccc.cos210.ds.IDoublyLinkedList;
import edu.mccc.cos210.ds.impl.DoublyLinkedList;

public class DrunkardsWalk {

	public DrunkardsWalk() {
		IDoublyLinkedList<Drunkard> theDrunks = initDrunks();
		JFrame jf = new JFrame("DrunkardsWalk");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new MyJPanel(theDrunks);
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(DrunkardsWalk::new);
	}
	
	private IDoublyLinkedList<Drunkard> initDrunks()
	{
		IDoublyLinkedList<Drunkard> dlld = new DoublyLinkedList<>();
		for (int i = 0; i < 200; i++)
		{
		Drunkard d = new Drunkard(
				Math.random() * 1024.0 / 2.0 * (Math.random() < 0.5 ? -1.0 : 1.0),
				Math.random() * 768.0 / 2.0 * (Math.random() < 0.5 ? -1.0 : 1.0),
				Math.random() * 8.0 * (Math.random() < 0.5 ? -1.0 : 1.0),
				Math.random() * 8.0 * (Math.random() < 0.5 ? -1.0 : 1.0),
				Math.random() * 8.0 + 8.0,
				new Color((float) Math.random(), (float) Math.random(), (float) Math.random())
		);
		dlld.addLast(d);
		}
		return dlld;
	}

	class MyJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int FPS = 10;
		private long lastTime = 0;
		private IDoublyLinkedList<Drunkard> dlld;

		public MyJPanel(IDoublyLinkedList<Drunkard> dlld) {
			this.dlld = dlld;
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

			ListIterator<Drunkard> lit = dlld.listIterator();
			while(lit.hasNext())
			{
				Drunkard d = lit.next();
				Ellipse2D e2d = new Ellipse2D.Double(
					d.x - d.radius,
					d.y - d.radius,
					d.radius * 2.0,
					d.radius *2.0
				);
				g2d.setPaint(d.color);
				g2d.fill(e2d);
				Drunkard nd = new Drunkard(
					d.x,
					d.y,
					d.dx,
					d.dy,
					d.radius,
					d.color
				);
				nd.x = d.x + d.dx * (Math.random() < 0.5 ? -1.0 : 1.0);
				nd.y = d.y + d.dy * (Math.random() < 0.5 ? -1.0 : 1.0);
				lit.set(nd);
			}
			
			g2d.dispose();
			Toolkit.getDefaultToolkit().sync();
		}
	}

	static class Drunkard
	{
		double x;
		double y;
		double dx;
		double dy;
		double radius;
		Color color = Color.BLACK;
		public Drunkard(double x, double y, double dx, double dy, double radius, Color color)
		{
			this.x = x;
			this.y = y;
			this.dx = dx;
			this.dy = dy;
			this.radius = radius;
			this.color = color;
		}
	}
}

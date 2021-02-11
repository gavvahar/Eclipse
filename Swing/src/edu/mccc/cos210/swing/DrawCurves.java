package edu.mccc.cos210.swing;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputAdapter;

public class DrawCurves {
	private static final String S1 = "QuadCurve2D";
	private static final String S2 = "CubicCurve2D";
	private java.util.List<Point2D> al = new ArrayList<>();
	private boolean cubic = false;
	private JCheckBox jcb1 = new JCheckBox("Edit");
	private JCheckBox jcb2 = new JCheckBox("Show");
	private JPanel jp = new MyJPanel();
	private BufferedImage bg;
	public static void main(String[] args) throws FileNotFoundException {
		System.setOut(new PrintStream(new FileOutputStream("./DrawCurves.txt")));
		DrawCurves dc = new DrawCurves();
		EventQueue.invokeLater(() -> dc.initSwing("./images/1.jpg"));
	}
	private void initSwing(String s) {
		JFrame jf = new JFrame("DrawCurves");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(createToolBar(), BorderLayout.NORTH);
		jf.add(jp, BorderLayout.CENTER);
		MouseInputAdapter mia = new MyMouseInputListener();
		jp.addMouseListener(mia);
		jp.addMouseMotionListener(mia);
		jp.addKeyListener(new MyKeyListener());
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		this.bg = loadImage(s);
		jf.setVisible(true);
		jp.requestFocus();
	}
	private BufferedImage loadImage(String s) {
		BufferedImage bi = new BufferedImage(1024, 768, BufferedImage.TYPE_3BYTE_BGR);
		if (s != null) {
			try {
				BufferedImage bix = ImageIO.read(new FileInputStream(s));
				Graphics2D g2d = bi.createGraphics();
				g2d.drawImage(bix, 0, 0, bi.getWidth(), bi.getHeight(), null);
				g2d.dispose();
				bix = null;
			} catch (Exception ex) {
				bi = null;
			}
		}
		return bi;
	}
	private JToolBar createToolBar() {
		JToolBar jtb = new JToolBar();
		JButton jb = new JButton(S1);
		jtb.add(jb);
		jb.addActionListener(ae -> handleButtons(ae));
		jb = new JButton(S2);
		jtb.add(jb);
		jb.addActionListener(ae -> handleButtons(ae));
		jtb.add(this.jcb1);
		jtb.add(this.jcb2);
		jb = new JButton("Dump");
		jb.addActionListener(
			ae -> {
				System.out.println("--- " + (isCubic() ? "cubic (4)" : "quad (3)"));
				for (Point2D p2d : al) {
					System.out.println(p2d);
				}
				jp.repaint();
			}
		);
		jtb.add(jb);
		jcb1.addChangeListener((ce) -> jp.repaint());
		jcb2.addChangeListener((ce) -> jp.repaint());
		return jtb;
	}
	private void setCubic(boolean b) {
		this.cubic = b;
	}
	private boolean isCubic() {
		return this.cubic;
	}
	private void clearPoints() {
		al.clear();
	}
	private class MyJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Stroke stroke = new BasicStroke(3.0f);
		public MyJPanel() {
			setLayout(null);
			setBackground(Color.WHITE);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			if (bg != null && jcb2.isSelected()) {
				g2d.drawRenderedImage(bg, new AffineTransform());
			}
			g2d.setPaint(Color.RED);
			for (int i = 0; i < al.size(); i++) {
				Ellipse2D.Double ed = new Ellipse2D.Double(al.get(i).getX() - 4.0, al.get(i).getY() - 4.0, 8.0, 8.0);
				g2d.fill(ed);
			}
			g2d.setPaint(Color.BLACK);
			g2d.setStroke(this.stroke);
			int n = isCubic() ? 4 : 3;
			for (int i = 0; i < al.size() / n; i++) {
				Shape s = getThingy(i * n);
				g2d.draw(s);
			}
			g2d.dispose();
			requestFocus();
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(1024, 768);
		}
		private Shape getThingy(int i) {
			Shape s;
			if (isCubic()) {
				s = new CubicCurve2D.Double(al.get(i).getX(), al.get(i).getY(), al.get(i + 1).getX(), al.get(i + 1).getY(), al.get(i + 2).getX(), al.get(i + 2).getY(), al.get(i + 3).getX(), al.get(i + 3).getY());
			} else {
				s = new QuadCurve2D.Double(al.get(i).getX(), al.get(i).getY(), al.get(i + 1).getX(), al.get(i + 1).getY(), al.get(i + 2).getX(), al.get(i + 2).getY());
			}
			return s;
		}
	}
	private void handleButtons(ActionEvent ae) {
		if (ae.getSource() instanceof JButton) {
			JButton jb = (JButton) ae.getSource();
			if (S2.equals(jb.getText())) {
				clearPoints();
				setCubic(true);
				this.jcb1.setSelected(false);
			} else {
				clearPoints();
				setCubic(false);
				this.jcb1.setSelected(false);
			}
			jp.repaint();
		}
	}
	private class MyMouseInputListener extends MouseInputAdapter {
		private int index = -1;
		@Override
		public void mouseReleased(MouseEvent me) {
			double x = (double) me.getX();
			double y = (double) me.getY();
			if (!jcb1.isSelected()) {
				al.add(new Point2D.Double(x, y));
			}
			jp.repaint();
		}
		@Override
		public void mousePressed(MouseEvent me) {
			if (jcb1.isSelected()) {
				double x = (double) me.getX();
				double y = (double) me.getY();
				this.index = findNearest(x, y);
				jp.repaint();
			}
		}
		@Override
		public void mouseDragged(MouseEvent me) {
			if (jcb1.isSelected()) {
				double x = (double) me.getX();
				double y = (double) me.getY();
				if (index >= 0) {
					al.set(this.index, new Point2D.Double(x, y));
				}
				jp.repaint();
			}
		}
		private int findNearest(double x, double y) {
			int index = -1;
			int i = 0;
			for (i = 0; i < al.size(); i++) {
				if (al.get(i).distance(x, y) < 5) {
					index = i;
					break;
				}
			}
			return index;
		}
	}
	private class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent ke) {
			if (ke.getKeyCode() == KeyEvent.VK_Z && ke.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK) {
				if (al.size() != 0) {
					al.remove(al.size() - 1);
					jp.repaint();
				}
			}
		}
	}
}
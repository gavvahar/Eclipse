package edu.mccc.cos210.swing;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Star {

	public Star() {
		JFrame jf = new JFrame("Star");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new MyJPanel();
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(Star::new);
	}

	class MyJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int FPS = 30;
		private long lastTime = 0;
		private double rotation1 = 0.0;
		private double rotDelta1 = 1.0;

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

			//g2d.setColor(Color.BLACK);
        	//g2d.draw(createDefaultStar(50, 200, 200));
			var s2d = createDefaultStar(50, 200, 200);
			AffineTransform at = new AffineTransform();
			at.rotate(Math.toRadians(rotation1));
			rotation1 += rotDelta1;
			Shape s = at.createTransformedShape(s2d);
			g2d.setPaint(new Color(128, 128, 0));
			g2d.fill(s);
			g2d.setPaint(Color.BLACK);
			g2d.draw(s);

			g2d.dispose();
			Toolkit.getDefaultToolkit().sync();
		}
	}
	private static Shape createDefaultStar(double radius, double centerX,
        double centerY)
    {
        return createStar(centerX, centerY, radius, radius * 2.63, 5,
            Math.toRadians(-18));
    }

    private static Shape createStar(double centerX, double centerY,
        double innerRadius, double outerRadius, int numRays,
        double startAngleRad)
    {
        Path2D path = new Path2D.Double();
        double deltaAngleRad = Math.PI / numRays;
        for (int i = 0; i < numRays * 2; i++)
        {
            double angleRad = startAngleRad + i * deltaAngleRad;
            double ca = Math.cos(angleRad);
            double sa = Math.sin(angleRad);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0)
            {
                relX *= outerRadius;
                relY *= outerRadius;
            }
            else
            {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            if (i == 0)
            {
                path.moveTo(centerX + relX, centerY + relY);
            }
            else
            {
                path.lineTo(centerX + relX, centerY + relY);
            }
        }
        path.closePath();
        return path;
    }
}

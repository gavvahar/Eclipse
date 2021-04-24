package main;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.beans.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;

public class Square extends JPanel {
		private static final long serialVersionUID = 1L;
		private int row;
		private int col;
		private Color cellColor;
		private Piece piece;

		public Square(int row, int col, Piece piece) {
			setLayout(null);
			setEnabled(true);
			this.row = row;
			this.col = col;
			this.piece = piece;
						
			//if((row!=0 && col!=0 && (row + col) % 2 == 0 )) {
			if((row + col) % 2 == 0 ) {
				this.cellColor = Color.WHITE;
			} else {
				this.cellColor = Color.BLACK;
			}
				
			setBackground(this.cellColor);
			setBorder(new MatteBorder(1, 1, 1, 1, new Color(200, 200, 224)));
		}

		@Override 
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			GraphicsEnvironment.getLocalGraphicsEnvironment();

			g2d.setFont(new Font("LucidaSans", Font.PLAIN, 80));
			//Piece piece = pieces[row][col];
			g2d.setPaint(piece.color);
			g2d.drawString(piece.pieceType, 5, 70);	
							
			g2d.dispose();
		}
}
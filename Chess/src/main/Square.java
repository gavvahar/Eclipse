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
		private Color color;
		public Square(int row, int col) {
			setLayout(null);
			setEnabled(true);
			this.row = row;
			this.col = col;
						
			//if((row!=0 && col!=0 && (row + col) % 2 == 0 )) {
			if((row + col) % 2 == 0 ) {
				this.color = Color.WHITE;
			} else {
				this.color = Color.BLACK;
			}
				
			setBackground(this.color);
			setBorder(new MatteBorder(1, 1, 1, 1, new Color(200, 200, 224)));
		}

		private void setRow(Graphics2D g2d)		{
			String queen = "\u265B";
			String king = "\u265A";
			String castle = "\u265C";
			String knight = "\u265E";
			String bishop = "\u265D";
			
			if(col == 0 || col == 7)
			{
				g2d.drawString(castle, 5, 70);
			}
			else if(col == 1 || col == 6)
			{
				g2d.drawString(knight, 5, 70);
			}
			else if(col == 2 || col == 5)
			{
				g2d.drawString(bishop, 5, 70);
			}
			else if(col == 4)
			{
				g2d.drawString(king, 5, 70);
			}
			else if(col == 3)
			{
				g2d.drawString(queen, 5, 70);
			}
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
			
				
			if(row == 0 && col < 8)
			{
				g2d.setFont(new Font("LucidaSans", Font.PLAIN, 80));
				g2d.setPaint(color.GREEN);
				setRow(g2d);
			}
			else if (row == 1 && col < 8) {
				g2d.setPaint(color.GREEN);
			g2d.setFont(new Font("LucidaSans", Font.PLAIN, 80));
			g2d.drawString("\u265F", 5, 70);
			} else if ( row == 6 && col < 8) {
				g2d.setPaint(color.RED);
				g2d.setFont(new Font("LucidaSans", Font.PLAIN, 80));
				//g2d.setPaint(color.BLACK);
				g2d.drawString("\u265F", 5, 70);	
			} else if ( row == 7 && col < 8) {
				//JLabel wKnight = new
				g2d.setFont(new Font("LucidaSans", Font.PLAIN, 80));
				g2d.setPaint(color.RED);
				setRow(g2d);
				//g2d.fill((Shape) color.BLUE);
				//g2d.drawString("\u2658", 5, 70);
			}
			
			//JLabel pawnLabel = new JLabel("\u265F");
			//jp.add(pawnLabel);
			
			//Ellipse2D e2d = new Ellipse2D.Double(0.0, 0.0, getWidth() - 2.0, getHeight() - 2.0);
			//g2d.setPaint(Color.BLUE);
			//g2d.fill(e2d);
			g2d.dispose();
		}
}
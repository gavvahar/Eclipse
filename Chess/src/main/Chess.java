package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.beans.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;




//test
//ayo
//one more
// second


public class Chess {
		private static final int SIZE = 8;
	    //private static final int SIZE = 9;
		private Square[][] squares;
		private java.util.Set<Square> hotSpots = new java.util.HashSet<>();
		private Square startSpot = null;
		private Square endSpot = null;
		private JPanel jp = new JPanel();
		
		
		public Chess() {
			
			JFrame jf = new JFrame("Chess");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			

			jp.setLayout(new GridLayout(SIZE, SIZE));
			jp.setBorder(BorderFactory.createLineBorder(Color.black, 35));
			//
			
			jp.setPreferredSize(new Dimension(800 / SIZE, 800 / SIZE));
			//PropertyChangeListener pcl = this::propertyChange;
			MouseListener ml = new ChessMouseListener();
			this.squares = new Square[SIZE][SIZE];
			for (int row = 0; row < SIZE; row++) {
				for (int col = 0; col < SIZE; col++) {
					Square square = new Square(row, col);
					jp.add(square);
					this.squares[row][col] = square;
					//spot.addPropertyChangeListener(pcl);
					
					
					
					
						//JLabel pawnLabel = new JLabel("\u265F");
						//jp.add(pawnLabel);
								
								
								
						//char Piece = '\u265F';
					
					
				
					
					//for (int i = 0; i < 8; i++) {
					//	squares [1][i].add(Piece);    
					//}
					square.addMouseListener(ml);
				}
			}
			
			//TOP BUTTONS
			JPanel topLayout = new JPanel(new GridBagLayout());
			JPanel topButtons = new JPanel(new GridLayout(1, 2, 5, 0));
				
			JButton defaultPlacement = new JButton("Default Placement");
			JButton clearButton = new JButton("Clear Board");
			topButtons.add(defaultPlacement);
			topButtons.add(clearButton);
			
			topLayout.add(topButtons);
			topLayout.setBackground(Color.BLACK);
			topButtons.setBackground(Color.BLACK);
			jf.add(topLayout, BorderLayout.NORTH);
			
			
			//Bottom Buttons
			JPanel pbLayout = new JPanel(new GridBagLayout());
			JPanel botButtons = new JPanel(new GridLayout(1, 12, 5, 0));
				
			JButton WKing = new JButton("\u2654");
			JButton WQueen = new JButton("\u2655");
			JButton WRook = new JButton("\u2656");
			JButton WBishop = new JButton("\u2657");
			JButton WKnight = new JButton("\u2658");
			JButton WPawn = new JButton("\u2659");
				
			JButton BKing = new JButton("\u265A");
			JButton BQueen = new JButton("\u265B");
			JButton BRook = new JButton("\u265C");
			JButton BBishop = new JButton("\u265D");
			JButton BKnight = new JButton("\u265E");
			JButton BPawn = new JButton("\u265F");
			
			botButtons.add(WKing);
			botButtons.add(WQueen);
			botButtons.add(WRook);
			botButtons.add(WBishop);
			botButtons.add(WKnight);
			botButtons.add(WPawn);
			botButtons.add(BKing);
			botButtons.add(BQueen);
			botButtons.add(BRook);
			botButtons.add(BBishop);
			botButtons.add(BKnight);
			botButtons.add(BPawn);
				
			pbLayout.add(botButtons);
			pbLayout.setBackground(Color.BLACK);
			botButtons.setBackground(Color.BLACK);
			jf.add(pbLayout, BorderLayout.SOUTH);
			
			jf.add(jp, BorderLayout.CENTER);
			jf.setSize(800, 800);
			jf.setLocationRelativeTo(null);
			jf.setResizable(false);
			jf.setVisible(true);
		}
	
public static void main(String... args) {
	EventQueue.invokeLater(Chess::new);
}
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
		//
		
		
		//if((row!=0 && col!=0 && (row + col) % 2 == 0 )) {
		if((row + col) % 2 == 0 ) {
			this.color = Color.WHITE;
		} else {
			this.color = Color.BLACK;
		}
			
		//this.color = Math.random() < 0.5 ? Color.WHITE : Color.BLACK;
		
		
		
		setBackground(this.color);
		setBorder(new MatteBorder(1, 1, 1, 1, new Color(200, 200, 224)));
	}
	/*public enum Pieces
	{
		
		QUEEN("\u265B"), KING("\u265A");
		private Pieces(String piece)
		{
			this.piece = piece;
		}
		private String piece;
	}*/
	private void setRow(Graphics2D g2d)
		{
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

 

private class ChessMouseListener extends MouseAdapter {
	@Override
	public void mouseReleased(MouseEvent e) {
		Square square = (Square) e.getSource();
		square.getParent().repaint();
	}
	
	
	
	
public void propertyChange(PropertyChangeEvent evt) {
	
		}
}


}


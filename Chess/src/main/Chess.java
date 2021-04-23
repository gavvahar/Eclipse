package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.beans.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;

public class Chess {
	private static final int SIZE = 8;
	private Square[][] squares;
	private java.util.Set<Square> hotSpots = new java.util.HashSet<>();
	private Square startSpot = null;
	private Square endSpot = null;
	private JPanel jp = new JPanel();
	private Piece[][] pieces = new Piece[SIZE][SIZE];
		
	public Chess() {
		
		JFrame jf = new JFrame("Chess");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		jp.setLayout(new GridLayout(SIZE, SIZE));
		jp.setBorder(BorderFactory.createLineBorder(Color.black, 35));
				
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
				
				square.addMouseListener(ml);
			}
		}

		jf.add(jp, BorderLayout.CENTER);
		jf.setSize(800, 800);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setVisible(true);
	}
	public Piece getPiece(int row, int col)
	{
		return pieces[row][col];
	}
	public void setPiece(Piece piece, int row, int col)
	{
		pieces[row][col] = piece;
	}
	public static void main(String... args) {
		EventQueue.invokeLater(Chess::new);
	}

}


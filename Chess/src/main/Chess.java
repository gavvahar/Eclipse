package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.beans.*;

import javax.swing.*;

public class Chess {
	private static final int SIZE = 8;
	private Square[][] squares;
	private java.util.Set<Square> hotSpots = new java.util.HashSet<>();
	private Square startSpot = null;
	private Square endSpot = null;
	private JPanel jp = new JPanel();
	//private Piece[][] pieces = new Piece[SIZE][SIZE];
		
	public Chess() {
		
		JFrame jf = new JFrame("Chess");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		jp.setLayout(new GridLayout(SIZE, SIZE));
		jp.setBorder(BorderFactory.createLineBorder(Color.black, 10));
				
		jp.setPreferredSize(new Dimension(800 / SIZE, 800 / SIZE));
		//PropertyChangeListener pcl = this::propertyChange;
		MouseListener ml = new ChessMouseListener();
		this.squares = new Square[SIZE][SIZE];

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				Piece piece = getInitialPieces(row,col);
				Square square = new Square(row, col, piece);
				jp.add(square);
				this.squares[row][col] = square;
				//spot.addPropertyChangeListener(pcl);
				
				square.addMouseListener(ml);
			}
		}

		JPanel leftPanel = getLeftPanel();
		jf.add(leftPanel, BorderLayout.WEST);

		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.BLACK);
		jf.add(rightPanel, BorderLayout.EAST);
		

		jf.add(jp, BorderLayout.CENTER);
		jf.setSize(800, 800);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setVisible(true);
	}

	private Piece getInitialPieces(int row, int col)	{
		int pieceSize = 70;
		String pawn = "\u265F";
		Color color = Color.GREEN;
		String strPiece = "";
		
		if (row == 0 )
			strPiece = getPiece(row,col);	
		else if (row == 1 ) {
			strPiece = pawn;
		} else if ( row == 6 ) {
			color  = Color.RED;
			strPiece = pawn;
		} else if ( row == 7 ) {
			color  = Color.RED;
			strPiece = getPiece(row,col);	
		}


		return ( new Piece(color, strPiece));
	}
	private String getPiece(int row, int col) {
		String strPiece = "";
		String queen = "\u265B";
		String king = "\u265A";
		String castle = "\u265C";
		String knight = "\u265E";
		String bishop = "\u265D";

		if(col == 0 || col == 7) {
			strPiece = castle;
		} else if(col == 1 || col == 6)		{
			strPiece =  knight;
		} 	else if(col == 2 || col == 5) {
			strPiece =  bishop;
		}	else if(col == 4) 		{
			strPiece =  king ;
		} 	else if(col == 3) 		{
			strPiece =  queen;
		}
	
		return strPiece;

	}
	private JPanel getLeftPanel()	{
		JPanel pbLayout = new JPanel(new GridBagLayout());
		//GridLayout(int rows, int cols, int hgap, int vgap)
		JPanel leftPanelNumbers = new JPanel(new GridLayout(8, 1, 0, 50));

		for (int row = 7; row >= 0; row--) {
			JPanel jb = new JPanel( String.valueOf(row) );
			
			leftPanelNumbers.add(jb);
		}
			
		pbLayout.add(leftPanelNumbers);
		pbLayout.setBackground(Color.BLACK);
		leftPanelNumbers.setBackground(Color.BLACK);
		return pbLayout;
	}

	public static void main(String... args) {
		EventQueue.invokeLater(Chess::new);
	}

}


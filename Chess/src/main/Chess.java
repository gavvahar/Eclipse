package main;

import java.awt.*;
import java.awt.event.*;
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
		jp.setBorder(BorderFactory.createLineBorder(Color.black,5));
				
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

		JPanel leftPanel = getLeftBottomPanel(false);
		jf.add(leftPanel, BorderLayout.WEST);

		JPanel bottomPanel = getLeftBottomPanel(true);
		jf.add(bottomPanel, BorderLayout.SOUTH);


		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.BLACK);
		jf.add(rightPanel, BorderLayout.EAST);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.BLACK);
		jf.add(topPanel, BorderLayout.NORTH);

		jf.add(jp, BorderLayout.CENTER);
		jf.setSize(800, 800);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setVisible(true);
	}

	private Piece getInitialPieces(int row, int col)	{
		//int pieceSize = 70;
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
	private JPanel getLeftBottomPanel(boolean alphabet)	{
		JPanel panelLayout = new JPanel(new GridBagLayout());
		//GridLayout(int rows, int cols, int hgap, int vgap)
		JPanel panelLeftBottom;  
		String alpha[] = {"A", "B","C","D","E", "F", "G","H"};
		String numbers[] = {"8","7","6","5","4","3","2","1"};
		String arr[];

		if (alphabet){
			arr = alpha;
			panelLeftBottom = new JPanel(new GridLayout(1, 8, 47, 0));
		//	JButton jb = new JButton("");
		//	panelLeftBottom.add(jb);
		} else {
			arr = numbers;
			panelLeftBottom = new JPanel(new GridLayout(8, 1, 0, 45));
		}

		for (int index = 0; index < arr.length ;index++) {

			JPanel jp = new JPanel();
			jp.setPreferredSize(new Dimension(43, 40));
			JLabel jl = new JLabel( arr[index] );
			jl.setFont(new Font("Serif", Font.BOLD, 18));
			jl.setForeground(Color.ORANGE);
			jp.add(jl);

			jp.setBackground(Color.BLACK);
			panelLeftBottom.add(jp);
		}
			
		panelLayout.add(panelLeftBottom);
		panelLayout.setBackground(Color.BLACK);
		panelLeftBottom.setBackground(Color.BLACK);
		return panelLayout;
	}

	public static void main(String... args) {
		EventQueue.invokeLater(Chess::new);
	}

}


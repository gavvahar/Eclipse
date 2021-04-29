package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;






public class ChessUnchanged {
		private static final int SIZE = 8;
		private Square[][] squares;
		private java.util.Set<Square> hotSpots = new java.util.HashSet<>();
		private Square startSpot = null;
		private Square endSpot = null;
		private JPanel jp = new JPanel();
		
		
		public ChessUnchanged() {
			
			JFrame jf = new JFrame("Chess");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jp.setLayout(new GridLayout(SIZE, SIZE));
			jp.setBorder(BorderFactory.createLineBorder(Color.black));
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
	
public static void main(String... args) {
	EventQueue.invokeLater(ChessUnchanged::new);
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
		if((row + col) % 2 == 0 ) {
			this.color = Color.WHITE;
		} else {
			this.color = Color.BLACK;
		}
			
		//this.color = Math.random() < 0.5 ? Color.WHITE : Color.BLACK;
		
		
		
		setBackground(this.color);
		setBorder(new MatteBorder(1, 1, 1, 1, new Color(200, 200, 224)));
	}
 }

private class ChessMouseListener extends MouseAdapter {
	@Override
	public void mouseReleased(MouseEvent e) {
		Square square = (Square) e.getSource();
	}
public void propertyChange(PropertyChangeEvent evt) {
	
		}
}
}


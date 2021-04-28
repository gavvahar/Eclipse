package main;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;

public class Square extends JPanel
{
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
	//26
	
	
	public Color getBackground() {
		return this.cellColor;
	}
	
	public void setCellColor(Color color) {
		this.cellColor = color;
	}
	
	public Color getCellColor() {
		return cellColor;
	}
	
	public void highlight()
	{
		Color color = this.cellColor;
		
		if (color == Color.BLACK || color == Color.WHITE)
		{
			setCellColor(Color.YELLOW);
			JLabel label = new JLabel(row + " : " + col);
			label.setFont(new Font("Serif", Font.BOLD, 18));
			label.setForeground(Color.ORANGE);
			//label.setText(row + " : " + col);
			add(label);
		} else if ((cellColor == Color.YELLOW) && (row + col) % 2 == 0 )
		{
				setCellColor(Color.WHITE);
		} else if (cellColor == Color.YELLOW) {
				setCellColor(Color.BLACK);
		}
	}
}
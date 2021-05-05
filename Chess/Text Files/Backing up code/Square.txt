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
	private static Color prevColor;
	private Piece piece ;
	private static Square prevSquare ;
	private static Piece startPiece, endPiece;
	//29

	private Square endSpot;
	private Square startSpot;
	//
	private static int startRow,  startCol;
	private static int endRow,  endCol;
	private Board board = Board.getInstance();
	
	//
	private static String highlightedPiece = "";

	public Square(int row, int col, Piece piece)
	{
		setLayout(null);
		setEnabled(true);
		this.row = row;
		this.col = col;
		this.piece = piece;
					
		//if((row!=0 && col!=0 && (row + col) % 2 == 0 )) {
		if((row + col) % 2 == 0 )
		{
			this.cellColor = Color.WHITE;
		} else
		{
			this.cellColor = Color.BLACK;
		}
			
		setBackground(this.cellColor);
		setBorder(new MatteBorder(1, 1, 1, 1, new Color(200, 200, 224)));
	}

	@Override 
	protected void paintComponent(Graphics g)
	{
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
	
	public Color getBackground()
	{
		return this.cellColor;
	}
		
	public Piece getPiece()
	{
		return this.piece;
	}
		
	public void setCellColor(Color color)
	{
		this.cellColor = color;
	}
	//26
	
	public Color getCellColor()
	{
		return cellColor;
	}
	//30
	
	public int getRow()
	{
		return row;
	}
	
	public void setRow(int row)
	{
		this.row = row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public void setCol(int col)
	{
		this.col = col;
	}
	
	//
	public int getStartRow()
	{
		return startRow;
	}
	
	public void setStartRow(int row)
	{
		this.startRow = row;
	}
	
	public int getStartCol()
	{
		return startCol;
	}
	
	public void setStartCol(int col)
	{
		this.startCol = col;
	}
	
	public int getEndRow()
	{
		return endRow;
	}
	
	public void setEndRow(int row)
	{
		this.endRow = row;
	}
	
	public int getEndCol()
	{
		return endCol;
	}
	
	public void setEndCol(int col)
	{
		this.endCol = col;
	}
	
	//public static void setHighlightedPiece(String pieceType) {
		//highlightedPiece = pieceType;
	//}
	
	//public void setHighlightedPiece(String pieceType) {
	//	highlightedPiece = new String(pieceType);
	//}
    //30
	
		
	public void highlight()
	{
		Color color = this.cellColor;	
		//28
		//Square startSpot = new Square(row,col,piece);
			
		//28
		//setStartSpot(startSpot);
		//System.out.println(row + " this is a row");
		//System.out.println(col + " this is a col");
		if (this.piece.pieceType.equals("\u265B"))
		{
			highlightedPiece = "\u265B";
		} else if (this.piece.pieceType.equals("\u265A"))
		{
			highlightedPiece = "\u265A";
		} else if (this.piece.pieceType.equals("\u265C"))
		{
			highlightedPiece = "\u265C";
		} else if (this.piece.pieceType.equals("\u265E"))
		{
			highlightedPiece = "\u265E";
		} else if (this.piece.pieceType.equals("\u265D"))
		{
			highlightedPiece = "\u265D";
		} else if (this.piece.pieceType.equals("\u265F"))
		{
			highlightedPiece = "\u265F";
		}	
	
		this.startRow = this.row;
		this.startCol = this.col;
		this.startPiece = this.piece;
		this.prevColor = this.cellColor;
		this.prevSquare = this;
		//System.out.println("Start Piece : " + startPiece.pieceType );
		this.cellColor = Color.YELLOW;
			//highlightedPiece = new String(this.piece.pieceType);
			
			//System.out.println(highlightedPiece + " a");
			
			//System.out.println(startRow + " this is a StartRow");
			//System.out.println(startCol + " this is a StartCol");
			
			
		if (color == Color.BLACK || color == Color.WHITE)
		{
			setCellColor(Color.YELLOW);
		} else if ((cellColor == Color.YELLOW) && (row + col) % 2 == 0 )
		{
			setCellColor(Color.WHITE);
		} else if (cellColor == Color.YELLOW)
		{
			setCellColor(Color.BLACK);
		}
			//28
	    /*if (piece.pieceType.equals("\u265B"))
		{
	    		System.out.println("It is a queen");
	    }*/
	    	//isMoveValid();
	    	//28
	}
	public void highlightEndPoint()
	{
		Color color = this.cellColor;

		this.endRow = this.row;
		this.endCol = this.col;
		this.endPiece = this.piece;
			
		if (color == Color.BLACK || color == Color.WHITE)
		{
//			setCellColor(Color.GREEN);
			
		}
		isMoveValid();
		//JOptionPane.showMessageDialog(null, "THE MOVE IS VALID", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
	}
		//26
		
		public void setEndSpot(Square endSpot)
		{
			this.endSpot = endSpot;
		}
		
		public Square getEndSpot(int row, int col)
		{
			return endSpot;
		}
		
		public void setStartSpot(Square startSpot)
		{
			this.startSpot = startSpot;
		}
		
		public Square getStartSpot(int row, int col)
		{
			return startSpot;
		}
		
		
        //28
        public void isMoveValid()
		{
        	//System.out.println(startCol);
        	//System.out.println(startRow);
           	//System.out.println(endRow);
           	//System.out.println(endCol);
      
        	
        	//if (piece.pieceType.equals("\u265F") && pawnMove()) 
        	//System.out.println(highlightedPiece + " c");
        	
        	if(highlightedPiece.equals("\u265F") && (pawnMove() == true))
        	{
        		System.out.println("Pawn. Move is valid");
        	//return true;
        		
        	}else if (highlightedPiece.equals("\u265F") && (pawnMove() == false))
			{
        		System.out.println("Pawn. Move is not valid");
        	}else if (highlightedPiece.equals("\u265E") && (knightMove() == true))
			{
        		System.out.println("Knight. Move is valid");
        	}else if (highlightedPiece.equals("\u265E") && (knightMove() == false))
			{
        		System.out.println("Knight. Move is not valid");
        	}else if (highlightedPiece.equals("\u265D") && (bishopMove() == true))
			{
        		System.out.println("Bishop. Move is valid");
        	}else if (highlightedPiece.equals("\u265D") && (bishopMove() == false))
			{
        		System.out.println("Bishop. Move is not valid");
        	}else if (highlightedPiece.equals("\u265C") && (rookMove() == true))
			{
        		System.out.println("Rook. Move is valid");
        	}else if (highlightedPiece.equals("\u265C") && (rookMove() == false))
			{
        		System.out.println("Rook. Move is not valid");
        	}else if (highlightedPiece.equals("\u265B") && (queenMove() == true))
			{
        		System.out.println("Queen. Move is valid");
        	}else if (highlightedPiece.equals("\u265B") && (queenMove() == false))
			{
        		System.out.println("Queen. Move is not valid");
        	}else if (highlightedPiece.equals("\u265A") && (kingMove() == true))
			{
        		System.out.println("King. Move is valid");
        	}else if (highlightedPiece.equals("\u265A") && (kingMove() == false))
			{
        		System.out.println("King. Move is not valid");
        	}
        	//	return false;
        	//}
        }
        //28

	public boolean pawnMove()
	{
		/*System.out.println("Start : " + startRow + ", " + startCol + " End : " + endRow + ", " + endCol);
		System.out.println("Piece : " + startPiece.pieceType + ", " + endPiece.pieceType);
		System.out.println("End Piece : "  + endPiece.pieceType);*/
		if(startPiece.color == Color.GREEN) {
		 	if (startCol == endCol) {
				if (((endRow - 1) == startRow) || ( (startRow == 1) && ((endRow - 2) == startRow))){
				movePiece();
				return true;
				}
			} else if ((startCol == (endCol - 1)) || (startCol == (endCol + 1))){
				if (((endRow - 1) == startRow) && endPiece.color == Color.RED) {
					movePiece();
					endPiece.pieceType = "";
				    return true;
				}
			}
		} 
		else if (startPiece.color == Color.RED)
		{
			//System.out.println("Its red");
			if (startCol == endCol) {
				if (((endRow + 1) == startRow) || (startRow == 6) && ((endRow + 2) == startRow)) {
					movePiece();
					return true;
				}
			}else if ((startCol == (endCol - 1)) || (startCol == (endCol + 1))){
				if (((endRow + 1) == startRow) && endPiece.color == Color.GREEN) {
					movePiece();
					endPiece.pieceType = "";
				    return true;
				}
			}
		}
		return false;
	}    
    public boolean knightMove()
    {
    	if (startPiece.color != endPiece.color) 
    	{  
    		endPiece.pieceType = "";
        if(((endCol+2) == startCol) && (endRow + 1) == startRow)
        {
            movePiece();
			return true;
        } else if (((endCol+1) == startCol) && (endRow + 2) == startRow) 
        {
            movePiece();
			return true;
        } else if (((endCol-1) == startCol) && (endRow + 2) == startRow) 
        {
            movePiece();
			return true;
        } else if (((endCol-2) == startCol) && (endRow + 1) == startRow)
        {
            movePiece();
			return true;
        }else if (((endCol-2) == startCol) && (endRow - 1) == startRow)
        {
            movePiece();
			return true;
        }else if (((endCol-1) == startCol) && (endRow - 2) == startRow)
        {
            movePiece();
			return true;
        }else if (((endCol+1) == startCol) && (endRow - 2) == startRow)
        {
            movePiece();
			return true;
        }else if (((endCol+2) == startCol) && (endRow - 1) == startRow)
        {
            movePiece();
			return true;
        } 
    	}
		return false;
    }
    public boolean bishopMove()
    {
        int[] n = new int [8];	
        for (int i = 1; i<n.length; i ++)
		{
        	if(((endCol + i) == startCol) && (endRow + i) == startRow)
            {
            	movePiece();
				return true;
            } else if (((endCol - i) == startCol) && (endRow + i) == startRow) 
            {
            	movePiece();
				return true;
            } else if (((endCol - i) == startCol) && (endRow - i) == startRow) 
            {
            	movePiece();
				return true;
            } else if (((endCol + i) == startCol) && (endRow - i) == startRow)
            {
            	movePiece();
				return true;
            }  	
        }
			return false;
    }
        
    public boolean rookMove() 
    {	
		// see if the rook is moving in cols or rows
		if(endCol == startCol)
		{
			//check if there are any pieces in between ( to code later )
			//System.out.println("Same column");
			if(isColBlocked(startRow, endRow, endCol))
			{
				return false;
			}
			movePiece();
			return true;
		} else if(endRow == startRow)
		{
			if(isRowBlocked(startCol, endCol, endRow))
			{
				return false;
			}
			movePiece();
			return true;
		}
		else
		{
			return false;
		}
    }
    private boolean isColBlocked(int start, int end, int Col)
	{
		//System.out.println("Start : " + start + " end : " + end + " col : " + col);
		
		if(start < end) {
			for(int i = start + 1; i <= end; i++)
			{
				if(board.squares[i][col].piece.pieceType != "")
				{
					//System.out.println("Blocked by " + board.squares[i][col].piece.pieceType);
					return true;
				}
			}
		} else {
			for(int i = start - 1; i >= end; i--)
			{
				if(board.squares[i][col].piece.pieceType != "")
				{
					//System.out.println("Blocked by " + board.squares[i][col].piece.pieceType);
					return true;
				}
			}

		}
		return false;
	}
	private boolean isRowBlocked(int start, int end, int Row)
	{
		//System.out.println("Start : " + start + " end : " + end + " row : " + row);
		
		if(start < end) {
			for(int i = start + 1; i <= end; i++)
			{
				if(board.squares[row][i].piece.pieceType != "")
				{
					//System.out.println("Blocked by " + board.squares[row][i].piece.pieceType);
					return true;
				}
			}
		} else {
			for(int i = start - 1; i >= end; i--)
			{
				if(board.squares[row][i].piece.pieceType != "")
				{
					//System.out.println("Blocked by " + board.squares[row][i].piece.pieceType);
					return true;
				}
			}

		}
		return false;
	}
	public boolean queenMove() 
    {
		
		int[] n = new int [8];
        for (int i = 1; i<n.length; i ++)
		{
        	if(bishopMove() == true || rookMove() == true)
            {
				movePiece();
            	return true;
            }   	
        } 
		return false;
    } 
	
	public boolean kingMove()
    {
        if(((endCol+1) == startCol) && (endRow == startRow))
        {
            movePiece();
			return true;
        } else if (((endCol+1) == startCol) && (endRow + 1) == startRow) 
        {
            movePiece();
			return true;
        } else if (((endCol == startCol) && (endRow + 1) == startRow)) 
        {
            movePiece();
			return true;
        } else if (((endCol-1) == startCol) && (endRow + 1) == startRow)
        {
            movePiece();
			return true;
        }else if (((endCol-1) == startCol) && (endRow == startRow))
        {
            movePiece();
			return true;
        }else if (((endCol - 1) == startCol) && (endRow - 1) == startRow)
        {
            movePiece();
			return true;
        }else if (((endCol == startCol) && (endRow - 1) == startRow))
        {
            movePiece();
			return true;
        }else if (((endCol+1) == startCol) && (endRow - 1) == startRow)
        {
            movePiece();
			return true;
        } else
        {
			return false;
        } 
    }
	
	
	
	public void movePiece()
	{
		this.piece = this.startPiece;
		this.prevSquare.piece = this.endPiece;
		this.prevSquare.cellColor = this.prevColor;
		
		this.prevSquare.repaint();
		this.repaint();
	}
}
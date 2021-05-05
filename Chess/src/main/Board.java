package main;

public class Board
{
    private static Board board = null;
    public int startCol, startRow;
    public Piece piece;
    public Square square;
    public Square[][] squares = new Square[SIZE][SIZE];
    private static final int SIZE = 8;
    public static Board getInstance()
    {
        if(board == null)
        {
            board = new Board();
        }
        return board;
    }
}
package main;
import java.awt.*;
public class Piece
{
    public Color color;
    public String pieceType;
    public int size;
    public Piece(Color color, String pieceType)
    {
        this.color = color;
        this.pieceType = pieceType;
        
    }
    public String getPieceType()
    {
        return pieceType;
    }   
}
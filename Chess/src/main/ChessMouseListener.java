package main;
import java.awt.event.*;
import java.beans.*;
public class ChessMouseListener extends MouseAdapter
{
    @Override
    public void mouseReleased(MouseEvent e) {
        Square square = (Square) e.getSource();
        square.getParent().repaint();
    }
    
    public void propertyChange(PropertyChangeEvent evt) {
    
    }
}

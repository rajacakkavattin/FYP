package DiagramDrawingApplication.FigureDrawing;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//The interface of mouse listener used in figure drawing
public interface IDrawingListener extends MouseListener, MouseMotionListener {

    void mouseClicked(MouseEvent e);

    void mousePressed(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mouseEntered(MouseEvent e);

    void mouseExited(MouseEvent e);

    void mouseDragged(MouseEvent e);

    void mouseMoved(MouseEvent e);
}

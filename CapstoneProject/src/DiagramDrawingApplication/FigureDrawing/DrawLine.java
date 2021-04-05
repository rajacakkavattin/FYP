package DiagramDrawingApplication.FigureDrawing;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawLine {
    private ArrayList<Figure> figureList;

    public DrawLine(ArrayList<Figure> figureL){
        this.figureList = figureL;
    }

    public void show(JFrame frame) {
        //remove redundant listener before drawing
        for (MouseListener listener: frame.getListeners(MouseListener.class)){
            if(listener instanceof IDrawingListener){
                frame.removeMouseListener(listener);
            }
        }
        for (MouseMotionListener listener: frame.getListeners(MouseMotionListener.class)){
            if(listener instanceof IDrawingListener){
                frame.removeMouseMotionListener(listener);
            }
        }

        Listener drawer = new Listener(frame, figureList);
        frame.addMouseListener(drawer);
        frame.addMouseMotionListener(drawer);
    }

    //The mouse listener for line drawing
    private class Listener implements IDrawingListener{
        private int x1,y1,x2,y2;
        private JFrame frame;
        private ArrayList<Figure> figureList;
        private Line prevLine;

        public Listener(JFrame frame, ArrayList<Figure> figureL){
            this.figureList = figureL;
            this.frame = frame;
        }

        @Override
        public void mouseClicked(MouseEvent e){}

        @Override
        public void mousePressed(MouseEvent e){
            this.x1=e.getX();
            this.y1=e.getY();
            Line line = new Line(x1, y1, x1, y1, frame);
            line.Draw(Color.black);
            prevLine = line;
        }

        @Override
        public void mouseReleased(MouseEvent e){
            this.x2=e.getX();
            this.y2=e.getY();
            Line line = new Line(x1, y1, x2, y2, frame);
            line.Draw(Color.black);
            figureList.add(line);

        }

        @Override
        public void mouseEntered(MouseEvent e){}

        @Override
        public void mouseExited(MouseEvent e){}

        @Override
        public void mouseDragged(MouseEvent e) {
            x2=e.getX();
            y2=e.getY();
            Line line = new Line(x1, y1, x2, y2, frame);
            prevLine.clear(figureList);
            line.Draw(Color.black);
            prevLine = line;
        }

        @Override
        public void mouseMoved(MouseEvent e) {}
    }
}
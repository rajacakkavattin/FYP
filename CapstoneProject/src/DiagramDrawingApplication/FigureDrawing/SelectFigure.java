package DiagramDrawingApplication.FigureDrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SelectFigure {
    private ArrayList<Figure> figureList;

    public SelectFigure(ArrayList<Figure> fList, JFrame frame){
        this.figureList = fList;

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

        Listener selector = new Listener(frame, figureList);
        //KeyBoard key = new KeyBoard(selector, figureList);
        frame.addMouseListener(selector);
        frame.addMouseMotionListener(selector);
        //frame.addKeyListener(key);
    }

    private class Listener implements IDrawingListener {
        int x, y;
        private Figure ChosenOne;
        private ArrayList<Figure> figureList;
        private JFrame frame;
        private Figure prevFigure;

        public Listener(JFrame frame, ArrayList<Figure> figureL) {
            this.frame = frame;
            this.figureList = figureL;
            KeyBoard kListener = new KeyBoard(this, figureList);
            frame.addKeyListener(kListener);
        }

        public Figure getSelected(){
            return ChosenOne;
        }

        public Figure selection(){
            Figure iterFigure;
            for(int i = (figureList.size()-1); i >= 0 ; i--){
                iterFigure = figureList.get(i);
                if(iterFigure.getType() == 0){
                    double length = distance(iterFigure.x1, iterFigure.y1, iterFigure.x2, iterFigure.y2);
                    double distSum = distance(iterFigure.x1, iterFigure.y1, x, y) + distance(iterFigure.x2, iterFigure.y2, x, y);
                    if(distSum <= (length*1.01)){
                        Figure rtFigure = iterFigure;
                        //figureList.remove(i);
                        return rtFigure;
                    }
                }else{
                    if(inRect(x, y, iterFigure)){
                        Figure rtFigure = iterFigure;
                        //figureList.remove(i);
                        return rtFigure;
                    }
                }
            }
            return null;
        }

        public boolean inRect(int X, int Y, Figure f){
            if(f.x1 >= f.x2){
                if(X >= f.x2 && X <=f.x1){
                    if(f.y1 >= f.y2){
                        if(Y >= f.y2 && Y <= f.y1){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        if(Y <= f.y2 && Y >= f.y1){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }else{
                    return false;
                }
            }else{
                if(X >= f.x1 && X <=f.x2){
                    if(f.y1 >= f.y2){
                        if(Y >= f.y2 && Y <= f.y1){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        if(Y <= f.y2 && Y >= f.y1){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }else{
                    return false;
                }
            }
        }

        public double distance(int x1, int y1, int x2, int y2){
            double dist = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
            return dist;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            this.x = e.getX();
            this.y = e.getY();
            this.ChosenOne = selection();
            if(ChosenOne != null){
                ChosenOne.clear(figureList);
                ChosenOne.Draw(Color.red);
                prevFigure = ChosenOne;
            }else{
                for(Figure f : figureList){
                    f.Draw(Color.black);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            this.x = e.getX();
            this.y = e.getY();
            this.ChosenOne = selection();
            if(ChosenOne != null){
                ChosenOne.clear(figureList);
                ChosenOne.Draw(Color.red);
                prevFigure = ChosenOne;
            }else{
                for(Figure f : figureList){
                    f.Draw(Color.black);
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int endX = e.getX();
            int endY = e.getY();
            int xChange = x - endX;
            int yChange = y - endY;
            if(ChosenOne != null){
                figureList.remove(ChosenOne);
                if(ChosenOne.getType() == 0){
                    Line line = new Line(ChosenOne.x1 - xChange, ChosenOne.y1 - yChange, ChosenOne.x2 - xChange, ChosenOne.y2 - yChange, frame);
                    figureList.remove(ChosenOne);
                    ChosenOne.clear(figureList);
                    line.Draw(Color.red);
                    ChosenOne = line;
                    figureList.add(line);
                }else{
                    Rectangle rect = new Rectangle(ChosenOne.x1 - xChange, ChosenOne.y1 - yChange, ChosenOne.x2 - xChange, ChosenOne.y2 - yChange, frame);
                    figureList.remove(ChosenOne);
                    ChosenOne.clear(figureList);
                    rect.Draw(Color.red);
                    ChosenOne = rect;
                    figureList.add(rect);
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {
            int dragX = e.getX();
            int dragY = e.getY();
            int xChange = x - dragX;
            int yChange = y - dragY;
            if(ChosenOne != null){
                figureList.remove(ChosenOne);
                if(ChosenOne.getType() == 0){
                    Line line = new Line(ChosenOne.x1 - xChange, ChosenOne.y1 - yChange, ChosenOne.x2 - xChange, ChosenOne.y2 - yChange, frame);
                    prevFigure.clear(figureList);
                    line.Draw(Color.red);
                    prevFigure = line;
                }else{
                    Rectangle rect = new Rectangle(ChosenOne.x1 - xChange, ChosenOne.y1 - yChange, ChosenOne.x2 - xChange, ChosenOne.y2 - yChange, frame);
                    prevFigure.clear(figureList);
                    rect.Draw(Color.red);
                    prevFigure = rect;
                }
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
        private class KeyBoard implements KeyListener {
            Listener pListener;
            private ArrayList<Figure> figureList;

            public KeyBoard(Listener l, ArrayList<Figure> fList){
                this.figureList = fList;
                this.pListener = l;
            }

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_DELETE){
                    Figure delFigure = pListener.getSelected();
                    if(delFigure != null){
                        figureList.remove(delFigure);
                        delFigure.clear(figureList);
                    }
                }
            }
        }
    }


}
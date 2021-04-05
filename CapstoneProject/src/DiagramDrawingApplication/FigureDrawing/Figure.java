package DiagramDrawingApplication.FigureDrawing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Figure {
    int x1,y1,x2,y2;
    private int figureType;
    private JFrame frame;

    public Figure(int x1, int y1, int x2, int y2, JFrame frame){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.frame = frame;
    }

    public void setType(int type){
        this.figureType = type;
    }

    public int getType(){
        return figureType;
    }

    public void Draw(Color c){
        if (figureType == 0){
            Line line = new Line(x1, y1, x2, y2, frame);
            line.Draw(Color.black);
        }else{
            Rectangle rect = new Rectangle(x1, y1, x2, y2, frame);
            rect.Draw(Color.black);
        }

    }

    public void clear(ArrayList<Figure> fList){
        if (figureType == 0){
            Line line = new Line(x1, y1, x2, y2, frame);
            line.clear(fList);
        }else{
            Rectangle rect = new Rectangle(x1, y1, x2, y2, frame);
            rect.clear(fList);
        }
    }
}

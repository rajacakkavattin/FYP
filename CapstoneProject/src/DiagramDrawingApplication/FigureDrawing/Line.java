package DiagramDrawingApplication.FigureDrawing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Line extends Figure {
    int x1,y1,x2,y2;
    private Graphics line;

    public Line(int x1, int y1, int x2, int y2, JFrame frame){
        super(x1, y1, x2, y2, frame);
        super.setType(0);
        this.line = frame.getGraphics();
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void Draw(Color c){
        line.setColor(c);
        line.drawLine(x1,y1,x2,y2);
    }

    public void clear(ArrayList<Figure> figureL){
        Draw(Color.white);
        for(Figure f : figureL){
            if(f != null){
                f.Draw(Color.black);
            }
        }
    }
}


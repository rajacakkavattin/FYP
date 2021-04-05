package DiagramDrawingApplication.FigureDrawing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Rectangle extends Figure {
    int x1,y1,x2,y2;
    private Graphics rect;

    public Rectangle(int x1, int y1, int x2, int y2, JFrame frame) {
        super(x1, y1, x2, y2, frame);
        super.setType(1);
        this.rect = frame.getGraphics();
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void Draw(Color c){
        rect.setColor(c);
        rect.drawLine(x1,y1,x2,y1);
        rect.drawLine(x2,y1,x2,y2);
        rect.drawLine(x2,y2,x1,y2);
        rect.drawLine(x1,y2,x1,y1);
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

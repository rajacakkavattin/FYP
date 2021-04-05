package DiagramDrawingApplication.DiagramDrawing;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;

//The abstract class of drawing diagrams
public abstract class ChartDrawing extends ApplicationFrame {
    private static String datafile;
    private static Dataset dataset;
    private static JPanel chartPanel;

    public ChartDrawing(String title) {
        super( title );
    }

    public abstract Dataset createDataset();

    public abstract JFreeChart createChart();

    public abstract void createDemoPanel();

    public abstract void setPane(JFrame frame);
}
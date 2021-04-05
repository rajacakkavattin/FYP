package DiagramDrawingApplication.DiagramDrawing;

import java.io.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.*;

public class BarChart extends ChartDrawing {
    private static String datafile;
    private static CategoryDataset dataset;
    private static JPanel chartPanel;

    public BarChart(String title, String filename)
    {
        super( title );
        this.datafile = filename;
        this.dataset = createDataset();
        createDemoPanel();
    }

    //create dataset according to the input .csv file
    public CategoryDataset createDataset(){
        DefaultCategoryDataset inputdata = new DefaultCategoryDataset( );
        try {
            BufferedReader in = new BufferedReader(new FileReader(datafile));
            String str;
            while ((str = in.readLine()) != null) {
                String[] sp = str.split(",");
                inputdata.addValue(Integer.parseInt(sp[2]) , sp[0] , sp[1]);
            }
        } catch (IOException e) {
            System.out.println("File Not Found!");
        }
        return inputdata;
    }

    //Create a JFreeChart object of the chart
    public JFreeChart createChart()
    {
        JFreeChart chart = ChartFactory.createBarChart(
                datafile,
                "Course",
                "Score",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return chart;
    }

    //These two methods are for diagram display
    public void createDemoPanel()
    {
        JFreeChart chart = createChart();
        JPanel DemoPanel = new ChartPanel( chart );
        this.chartPanel = DemoPanel;
    }
    public void setPane(JFrame frame){
        frame.setContentPane(chartPanel);
    }
}

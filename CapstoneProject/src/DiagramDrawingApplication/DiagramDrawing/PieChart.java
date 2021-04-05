package DiagramDrawingApplication.DiagramDrawing;

import java.io.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

//The class to draw pie chart
public class PieChart extends ChartDrawing {
    private static String datafile;
    private static PieDataset dataset;
    private static JPanel chartPanel;

    public PieChart(String title, String filename)
    {
        super( title );
        this.datafile = filename;
        this.dataset = createDataset();
        createDemoPanel();
    }

    //create dataset according to the input .csv file
    public DefaultPieDataset createDataset()
    {
        DefaultPieDataset inputdata = new DefaultPieDataset();
        try {
            BufferedReader in = new BufferedReader(new FileReader(datafile));
            String str;
            while ((str = in.readLine()) != null) {
                String[] sp = str.split(",");
                inputdata.setValue( sp[0] , Integer.parseInt(sp[1]));
            }
        } catch (IOException e) {
            System.out.println("File Not Found!");
        }
        return inputdata;
    }

    //Create a JFreeChart object of the chart
    public JFreeChart createChart()
    {
        JFreeChart chart = ChartFactory.createPieChart(
                datafile,
                dataset,
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

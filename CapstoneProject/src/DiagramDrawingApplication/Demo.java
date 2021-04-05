package DiagramDrawingApplication;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;
import DiagramDrawingApplication.DiagramDrawing.*;
import DiagramDrawingApplication.FigureDrawing.*;

//This is the GUI part of this application
//Except the uncompleted functions, you do not need to modify other code of this part
public class Demo extends JFrame {
    public ArrayList<Figure> figureList;

    public Demo()  {
        //The mainmenu frame
        JFrame MainMenu = new JFrame();
        MainMenu.setTitle("Diagram Drawing Application");
        MainMenu.setSize(800, 600);
        MainMenu.setLayout(null);
        MainMenu.setDefaultCloseOperation(3);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        JLabel AppTitle = new JLabel("Diagram Drawing Application");
        Font font1 = new Font("Times New Roman", Font.BOLD, 40);
        Font font2 = new Font("Times New Roman", Font.BOLD, 24);
        AppTitle.setFont(font1);
        panel1.add(AppTitle);
        panel1.setBounds(100, 80, 600, 80);
        MainMenu.add(panel1);

        JLabel AuthorTitle = new JLabel("By CAO Pai");
        AuthorTitle.setFont(font2);
        panel5.add(AuthorTitle);
        panel5.setBounds(100, 160, 600, 80);
        MainMenu.add(panel5);

        Dimension dim1 = new Dimension(240, 60);
        JButton jb1 = new JButton("Figure Drawing");
        JButton jb2 = new JButton("Chart Drawing");
        JButton jb3 = new JButton("Exit");
        jb1.setFont(font2);
        jb2.setFont(font2);
        jb3.setFont(font2);
        jb1.setPreferredSize(dim1);
        jb2.setPreferredSize(dim1);
        jb3.setPreferredSize(dim1);
        panel2.add(jb1);
        panel3.add(jb2);
        panel4.add(jb3);
        panel2.setBounds(280, 240, 240, 60);
        panel3.setBounds(280, 320, 240, 60);
        panel4.setBounds(280, 400, 240, 60);
        MainMenu.add(panel2);
        MainMenu.add(panel3);
        MainMenu.add(panel4);

        //The figure drawing frame
        JFrame drawFigure = new JFrame();
        drawFigure.getContentPane().setBackground(Color.white);
        drawFigure.setTitle("Diagram Drawing Application");
        drawFigure.setSize(1280, 720);
        drawFigure.setLayout(null);
        MenuBar menu=new MenuBar();
        menu.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        drawFigure.setMenuBar(menu);
        Menu m1=new Menu("File");
        Menu m2=new Menu("Draw");
        menu.add(m1);
        menu.add(m2);
        MenuItem m11=new MenuItem("Clear");
        MenuItem m12=new MenuItem("Back");
        m1.add(m11);
        m1.add(m12);
        MenuItem m21=new MenuItem("Select");
        MenuItem m22=new MenuItem("Line");
        MenuItem m23=new MenuItem("Rectangle");
        m2.add(m21);
        m2.add(m22);
        m2.add(m23);

        //The diagram drawing frame
        JFrame drawChart = new JFrame();
        drawChart.setTitle("Diagram Drawing Application");
        drawChart.setSize(800, 600);
        drawChart.setLayout(null);
        JPanel panel21 = new JPanel();
        panel21.setBounds(100, 80, 600, 80);
        JLabel label = new JLabel("Chart Type: ");
        JComboBox cmb=new JComboBox();
        label.setFont(font2);
        cmb.setFont(font2);
        cmb.addItem("Pie Chart");
        cmb.addItem("Bar Chart");
        cmb.addItem("Line Chart");
        panel21.add(label);
        panel21.add(cmb);
        drawChart.add(panel21);
        JPanel panel22 = new JPanel();
        JPanel panel23 = new JPanel();
        JButton jb21 = new JButton("Draw");
        JButton jb22 = new JButton("Back");
        jb21.setFont(font2);
        jb22.setFont(font2);
        jb21.setPreferredSize(dim1);
        jb22.setPreferredSize(dim1);
        panel22.add(jb21);
        panel23.add(jb22);
        panel22.setBounds(280, 240, 240, 60);
        panel23.setBounds(280, 320, 240, 60);
        drawChart.add(panel22);
        drawChart.add(panel23);

        RefineryUtilities.centerFrameOnScreen( MainMenu );
        RefineryUtilities.centerFrameOnScreen( drawChart );
        RefineryUtilities.centerFrameOnScreen( drawFigure );

        MainMenu.setVisible(true);

        //Buttons for mainmenu
        jb1.addActionListener(new ActionListener() {    //Start drawing figure
            public void actionPerformed(ActionEvent e) {
                MainMenu.setVisible(false);
                drawFigure.setVisible(true);
            }
        });
        jb2.addActionListener(new ActionListener() {    //Start drawing diagram
            public void actionPerformed(ActionEvent e) {
                MainMenu.setVisible(false);
                drawChart.setVisible(true);
            }
        });
        jb3.addActionListener(new ActionListener() {    //Exit
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Buttons for drawFigure
        m11.addActionListener(new ActionListener() {    //Clear the drawing board
            public void actionPerformed(ActionEvent e){
                drawFigure.dispose();
                drawFigure.setVisible( true );
            }
        });
        m12.addActionListener(new ActionListener() {    //Back to mainmenu
            public void actionPerformed(ActionEvent e) {
                drawFigure.dispose();
                MainMenu.setVisible(true);
            }
        });

        this.figureList = new ArrayList<>();

        m21.addActionListener(new ActionListener() {    //Function of selecting figures
            public void actionPerformed(ActionEvent e) {
                new SelectFigure(figureList, drawFigure);
            }
        });
        m22.addActionListener(new ActionListener(){     //Function of drawing lines
            public void actionPerformed(ActionEvent e) {
                DrawLine line = new DrawLine(figureList);
                line.show(drawFigure);
            }
        });
        m23.addActionListener(new ActionListener(){     //Function of drawing rectangle
            public void actionPerformed(ActionEvent e) {
                DrawRectangle rectangle = new DrawRectangle(figureList);
                rectangle.show(drawFigure);
            }
        });





        //Buttons for drawaChart
        jb22.addActionListener(new ActionListener() {   //Back to mainmenu
            public void actionPerformed(ActionEvent e) {
                drawChart.dispose();
                MainMenu.setVisible(true);
            }
        });
        //Record the choice of the ComboBox, 0:pie chart; 1:bar chart; 2:line chart
        final int[] chartType = new int[1];
        cmb.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                chartType[0] = cmb.getSelectedIndex();
            }
        });

        //A frame to display the diagram
        JFrame DiagramBoard = new JFrame();
        DiagramBoard.setSize( 560 , 360 );
        RefineryUtilities.centerFrameOnScreen( DiagramBoard );
        DiagramBoard.setDefaultCloseOperation(2);

        //Drawing diagram
        jb21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filename= JOptionPane.showInputDialog("Input Dataset Filename:"); //input the filename
                switch(chartType[0]){
                    case 0:     //Drawing pie chart
                        PieChart pieDemo = new PieChart("Pie Chart", filename);
                        pieDemo.setPane( DiagramBoard );
                        DiagramBoard.setVisible( true );
                        break;
                    case 1:     //Drawing bar chart
                        //System.out.println("Finish this by your self");
                        BarChart barDemo = new BarChart("Bar Chart", filename);
                        barDemo.setPane( DiagramBoard );
                        DiagramBoard.setVisible( true );
                        break;
                    case 2:     //Drawing line chart
                        //System.out.println("Finish this by your self");
                        LineChart lineDemo = new LineChart("Line Chart", filename);
                        lineDemo.setPane( DiagramBoard );
                        DiagramBoard.setVisible( true );
                        break;
                }
            }
        });
    }
    public void paint(Graphics g){
        super.paint(g);
        for(Figure f: figureList){
            f.Draw(Color.black);
        }
    }
}
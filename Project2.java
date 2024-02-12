
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

public class Project2
{
    public static void main(String[] args)
    {
        new Project2();
    }

    public Project2()
    {    
        GeometricObject[] g;
        
        //opens file and stores it in Scanner object called input 
        File input = new File("Project2 input file.txt");

        //Exception handling preparing for an error when opening file
        Scanner in = null;
        try
        {
            in = new Scanner(input);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }

        int rectNumber; //number of rectangles
        int ctr = 0; //rectangles read so far
        
        rectNumber = in.nextInt(); //reads number of rectangles (first line in file)
        g = new GeometricObject[rectNumber]; //creates array of rectangles with size of rectNumber

        //reads info of rectangles from file
        for(int i=0; i<rectNumber; i++)
        {
            double x1 = in.nextDouble();
            double y1 = in.nextDouble();
            Point p1 = new Point(x1, y1);   //Creating a point object with the coordinates of the first point

            double x2 = in.nextDouble();
            double y2 = in.nextDouble();
            Point p2 = new Point(x2, y2);   //Creating a point object with the coordinates of the second point

            Rectangle rect = new Rectangle(p1, p2); //Creating a rectangle object with the two points
            rect.setInteriorColor(new Color(0, 128, 128, 128));

            g[ctr++] = rect;

        }        
        
        in.close();  

        //drawing
        FrameDisplay frame = new FrameDisplay(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

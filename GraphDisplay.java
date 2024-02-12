
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Defines the panel the drawings will be made in.
 * 
 * @author Prof. Antonio Hernandez
 */
public class GraphDisplay extends JPanel implements MouseListener, MouseMotionListener
{
    //geometric objects that are painted on this panel
    GeometricObject[] gArray;
    private BufferedImage image; //background image
    String description; //description of map element

    
    private int initialX; //x-coord. of previous mouse location
    private int initialY; //y-coord. of previous mouse location
    private int rectLoc; // location in gArray of selected rectangle. -1 if none has been selected

    public GraphDisplay(int frameWidth, int frameHeight, GeometricObject[] g)
    {
        this.gArray = g;
        //this.rectNames = names; 
        description = "";
        Dimension d = new Dimension(frameWidth, frameHeight);
        setPreferredSize(d); //sets the preferred size of this panel
        addMouseMotionListener(this);
        
        //opens/read image file
        try
        {
            image = ImageIO.read(new File("fiu map.png"));
            System.out.println("Image file loaded successfully!");
        }
        catch (IOException ex)
        {
            System.out.println("Image file not found!");
        }                   
    }

    public void mouseClicked(MouseEvent e){
        int x = e.getPoint().x;
        int y = e.getPoint().y;
        Point p = new Point(e.getPoint().x, e.getPoint().y);
        
        Rectangle[] rArray = new Rectangle[gArray.length];
        for (int i = 0; i<rArray.length; i++)
        {
            rArray[i] = (Rectangle)gArray[i];
        }

        rectLoc = Algorithms.isPointInSet(rArray, p);
        if (rectLoc != -1)
        {
            Rectangle r = (Rectangle)gArray[rectLoc];
            r.setInteriorColor(Color.RED);
            repaint();
        }
    }

    //Translates selected rectangle if one has been slected
    public void mouseDragger(MouseEvent e){
        if(rectLoc != -1){
            double vX = e.getPoint().x - initialX;
            double vY = e.getPoint().y - initialY;

            Vector v = new Vector(vX, vY);
            Rectangle r = ((Rectangle)gArray[rectLoc]);

            repaint();

            initialX = e.getX();
            initialY = e.getY();
        }
    }

    public void mouseEntered(MouseEvent e){
        setCursor(Cursor.getDefaultCursor());
    }

    public void mouseExited(MouseEvent e){
        setCursor(Cursor.getDefaultCursor());
    }

    public void mouseDragged(MouseEvent e){}

    //This method is called when the mouse is moved over a GraphDisplay object
    public void mouseMoved(MouseEvent e)
    {
        //is current mouse location inside a geometric object?
        int x = e.getPoint().x;
        int y = e.getPoint().y;
        Point p = new Point(e.getPoint().x, e.getPoint().y);

        //Cast geometric object array to rectangle array
        Rectangle[] rArray = new Rectangle[gArray.length];
        String[] nameArray = new String[gArray.length]; // new array to store rectangle names

        //loop through each element in the gArray array and cast it to a Rectangle object
        for (int i = 0; i<rArray.length; i++)
        {
            rArray[i] = (Rectangle)gArray[i];
            nameArray[i] = "PG" + (i+1); // set name for each rectangle
        }      

        int index = Algorithms.isPointInSet(rArray, p);
        if (index == -1)
            description = ""; // mouse is not inside a rectangle, so clear the description
        else
        {
            String name = nameArray[index]; // get name of rectangle at index
            description = name; // set description to name of rectangle
        }

        repaint(); 
    }

    public void mousePressed(MouseEvent e){
        initialX = e.getX();
        initialY = e.getY();

        int x = e.getPoint().x;
        int y = e.getPoint().y;
        Point p = new Point(e.getPoint().x, e.getPoint().y);

        //Cast geometric object array to rectangle array
        Rectangle[] rArray = new Rectangle[gArray.length];
        for (int i = 0; i<rArray.length; i++)
        {
            rArray[i] = (Rectangle)gArray[i];
        }
        rectLoc = Algorithms.isPointInSet(rArray, p);
    }
    
    /**
     * Paints this panel; the system invokes it every time
     * it deems necessary to redraw the panel.
     */
    public void paint(Graphics g)
    {        
        super.paint(g); //clears window
        
        //draws background image
        Dimension d = getSize();
        g.drawImage(image, 0, 0, d.width, d.height, this);
        
        //outputs description
        g.setColor(new Color(64, 64, 64, 128));
        g.fillRoundRect(0, 420, 150, 30, 5, 5); //bottomleft textfield for output
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 12));
        g.drawString(description, 20, 440); //outputs description of map element
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
}

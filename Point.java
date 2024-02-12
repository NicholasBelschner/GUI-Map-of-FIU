import java.awt.*;

//This class is representing a point given by its x and y coordinates. 
public class Point extends GeometricObject{
    private double x;
    private double y; 

    // Gives default coordinates 0
    public Point(){
        x = y = 0;
    }

    //Translates this point by given vector. @param v given vector
    public void translate(Vector v){
        x += v.getX();
        y += v.getY();
    }

    //Rotates this point about the origin by the given angle. @param angle given angle
    public void rotate(double angle){
        double angleRadians = Math.toRadians(angle);

        double sine = Math.sin(angleRadians);
        double cosine = Math.cos(angleRadians);

        double x1 = x*cosine - y*sine;
        double y1 = x*sine + y*cosine;

        x = x1;
        y = y1; 
    }

    // This is illistrating a point object 
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    //Now returning the coordinates
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    //Setting the x&y coordinates of the two points
    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    // Constructing a string describing this point and returning the value
    public String toString(){
        return "POINT (" + x + ", " + y + ")" + super.toString();
    }

    public void draw(Graphics g){
        int radius = 5;

        g.setColor(getInteriorColor());
        g.fillOval((int)x - radius, (int)y - radius, 2 * radius, 2 * radius);
        g.setColor(getBoundaryColor());
        g.drawOval((int)x - radius, (int)y - radius, 2 * radius, 2 * radius);
    }

    public double distance(Point p){
        double deltaX = this.x - p.getX();
        double deltaY = this.y - p.getY();
    
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}


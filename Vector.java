public class Vector {
    private double x; // x component for vector
    private double y; // y component for vector 

    //Instantiates a null vector
    public Vector() {
        x = y = 0;
    }

    //Instantiates a Vector object with components (x, y).
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Add this vector to the givin one. 
    public Vector add(Vector v) {
        return new Vector(this.x + v.x, this.y + v.y);
    }

    //Subtract this vector from the given one. 
    public double direction(){
        double angle = Math.toDegrees(Math.atan2(y, x));
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    //Calculates magnitude (length) of this vector. 
    public double magnitude(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    //Computes a vector with same magnitude and direction opposite to that of this vector.
    public Vector neg(){
        return new Vector(-x, -y);
    }

    //Sets the x and y coordenates of this vector
    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    //Subtracts the given vector for this one
    public Vector subtract(Vector v){
        return new Vector(this.x - v.x, this.y - v.y);
    }

    //Constructing string containing a description of this vector
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

}

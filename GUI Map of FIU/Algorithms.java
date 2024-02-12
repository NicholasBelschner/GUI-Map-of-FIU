public class Algorithms{
    public static int isPointInSet(Rectangle[] rArray, Point p){
        for (int i = 0; i < rArray.length; i++) {
            if (rArray[i].isPointInRectangle(p))
            return i;
        }
        return -1;
    }
}
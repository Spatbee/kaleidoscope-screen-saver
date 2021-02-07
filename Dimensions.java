public class Dimensions {
    
    private static int wedges = 10;
    private static double radius = 320;
    private static double centerX = 320;
    private static double centerY = 320;

    public static void setWedges(int newValue) {
        wedges = newValue;
    }

    public static int getNumberOfWedges() {
        return wedges;
    }

    public static double getDegreesPerWedge() {
        return (double) 360 / wedges;
    }

    public static double getCircleRadius() {
        return radius;
    }

    public static double getCenterX() {
        return centerX;
    }

    public static double getCenterY() {
        return centerY;
    }
}

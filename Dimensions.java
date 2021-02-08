public class Dimensions {
    
    private static int wedges = 10;
    private static double radius = 320;
    private static int xOffset = 50;
    private static int yOffset = 50;
    private static int framesPerSecond = 20;
    private static int pixelsPerSecond = 20;

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

    public static int getXOffset() {
        return xOffset;
    }

    public static int getYOffset() {
        return yOffset;
    }

    public static int getFramesPerSecond() {
        return framesPerSecond;
    }

    public static int getPixelsPerSecond() {
        return pixelsPerSecond;
    }

    public static int getPixelsPerFrame() {
        return pixelsPerSecond / framesPerSecond;
    }
}

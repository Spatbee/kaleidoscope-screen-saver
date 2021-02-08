import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class ImageHandler {
    private BufferedImage image;
    private int leftOfArcBase;
    private int offset;

    public ImageHandler() {
        try {
            image = ImageIO.read(new File("color.png"));
        } catch (IOException ex) {
            // handle exception...
        }
        leftOfArcBase = (int)Math.ceil(Dimensions.getCircleRadius() * Math.sin(Math.toRadians(Dimensions.getDegreesPerWedge()/2)));
        offset = leftOfArcBase;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getOffset() {
        return offset;
    }

    public void stepImageRight() {
        offset += Dimensions.getPixelsPerFrame();
    }
}

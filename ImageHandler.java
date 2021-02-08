import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class ImageHandler {
    private BufferedImage image;
    private int halfSliceWidth;
    private int offset;

    public ImageHandler() {
        try {
            image = ImageIO.read(new File("color.png"));
        } catch (IOException ex) {
            // handle exception...
        }
        halfSliceWidth = (int)Math.ceil(image.getHeight() * Math.sin(Math.toRadians(Dimensions.getDegreesPerWedge()/2)));
        offset = halfSliceWidth;
    }

    public BufferedImage getImage() {
        return image.getSubimage(offset - halfSliceWidth, 0, halfSliceWidth * 2, image.getHeight());
    }

    public int getOffset() {
        return offset;
    }

    public void stepImageRight() {
        offset += Dimensions.getPixelsPerFrame();
    }

    public int getHalfSliceWidth() {
        return halfSliceWidth;
    }
}

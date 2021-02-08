import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.geom.Arc2D;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImageDrawer {

    private ImageHandler imageHandler;
    private Graphics graphics;

    private transient final ActionListener updateListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawKaleidoscope();
        }
    };

    public ActionListener getUpdateListener() {
        return updateListener;
    }

    public ImageDrawer(Graphics graphics) {
        this.graphics = graphics;
        imageHandler = new ImageHandler();
        
    }

    protected void drawKaleidoscope() {
        int imageOffset = imageHandler.getOffset();
        System.out.println("offset: " + imageOffset);
        BufferedImage original = imageHandler.getImage();
        double scale = Dimensions.getCircleRadius() / original.getHeight();
        BufferedImage image = new AffineTransformOp(AffineTransform.getScaleInstance(scale, scale), AffineTransformOp.TYPE_NEAREST_NEIGHBOR).filter(original, null);
        AffineTransform reverseTransformation = AffineTransform.getTranslateInstance(image.getWidth(), 0);
        reverseTransformation.concatenate(AffineTransform.getScaleInstance(-1,1));
        BufferedImage reversedImage = new AffineTransformOp(reverseTransformation, AffineTransformOp.TYPE_NEAREST_NEIGHBOR).filter(image, null);
        imageHandler.stepImageRight();
        long startTime = System.currentTimeMillis();
        for(int wedge = 0; wedge < Dimensions.getNumberOfWedges(); wedge++) {
            
            Shape pie = new Arc2D.Double(
                Dimensions.getXOffset(), 
                Dimensions.getYOffset(), 
                Dimensions.getCircleRadius() * 2, 
                Dimensions.getCircleRadius() * 2, 
                Dimensions.getDegreesPerWedge() * wedge, 
                Dimensions.getDegreesPerWedge(), Arc2D.PIE
            );
            graphics.setClip(pie);
            double rotationRequired = Math.toRadians(-Dimensions.getDegreesPerWedge() * wedge + 90 - Dimensions.getDegreesPerWedge() / 2);
            BufferedImage imageToDraw;
            int arcOriginX;
            if(wedge % 2 == 0) {
                imageToDraw = reversedImage;
                arcOriginX = 
                    reversedImage.getWidth() - imageOffset;
            } else {
                imageToDraw = image;
                arcOriginX = imageOffset;
            }

            AffineTransform transformation = AffineTransform.getTranslateInstance(Dimensions.getCircleRadius() - arcOriginX, 0);
            transformation.concatenate(AffineTransform.getRotateInstance(rotationRequired, arcOriginX, imageToDraw.getHeight()));
            AffineTransformOp op = new AffineTransformOp(transformation, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            graphics.drawImage(op.filter(imageToDraw, null), Dimensions.getXOffset(), Dimensions.getYOffset(), null);
            
        }
        System.out.println((System.currentTimeMillis() - startTime) + "ms");
       
    }

}

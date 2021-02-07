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

public class ImagePanel extends JPanel{

    private transient BufferedImage image;
    private transient BufferedImage reversedImage;

    public ImagePanel() {
        try {
            image = ImageIO.read(new File("color.png"));
            AffineTransform reverseTransformation = AffineTransform.getTranslateInstance(image.getWidth(), 0);
            reverseTransformation.concatenate(AffineTransform.getScaleInstance(-1,1));
            AffineTransformOp reverseOp = new AffineTransformOp(reverseTransformation, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            reversedImage = reverseOp.filter(image, null);
        } catch (IOException ex) {
            // handle exception...
        }
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long startTime = System.currentTimeMillis();
        for(int wedge = 0; wedge < Dimensions.getNumberOfWedges(); wedge++) {
            
            Shape pie = new Arc2D.Double(
                Dimensions.getCenterX() - Dimensions.getCircleRadius(), 
                Dimensions.getCenterY() - Dimensions.getCircleRadius(), 
                Dimensions.getCircleRadius() * 2, 
                Dimensions.getCircleRadius() * 2, 
                Dimensions.getDegreesPerWedge() * wedge, 
                Dimensions.getDegreesPerWedge(), Arc2D.PIE
            );
            g.setClip(pie);
            double rotationRequired = Math.toRadians(-Dimensions.getDegreesPerWedge() * wedge + 90 - Dimensions.getDegreesPerWedge() / 2);
            BufferedImage imageToDraw;
            double arcOriginX;
            if(wedge % 2 == 0) {
                imageToDraw = reversedImage;
                arcOriginX = reversedImage.getWidth() - Dimensions.getCircleRadius() * .5;
            } else {
                imageToDraw = image;
                arcOriginX = Dimensions.getCircleRadius() * .5;
            }

            AffineTransform transformation = AffineTransform.getTranslateInstance(Dimensions.getCenterX() - arcOriginX, 0);
            transformation.concatenate(AffineTransform.getRotateInstance(rotationRequired, arcOriginX, Dimensions.getCenterY()));
            AffineTransformOp op = new AffineTransformOp(transformation, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            g.drawImage(op.filter(imageToDraw, null), 0, 0, null);
            
        }
        System.out.println(System.currentTimeMillis() - startTime);
       
    }

}

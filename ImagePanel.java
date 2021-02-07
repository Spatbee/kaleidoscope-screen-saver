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

    public ImagePanel() {
       try {                
          image = ImageIO.read(new File("color.png"));
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
            //Dimensions.getDegreesPerWedge() * wedge
            double rotationRequired = Math.toRadians(-Dimensions.getDegreesPerWedge() * wedge + 90 - Dimensions.getDegreesPerWedge() / 2);
            
            AffineTransform transformation = AffineTransform.getTranslateInstance(Dimensions.getCenterX() * .5, 0);
            transformation.concatenate(AffineTransform.getRotateInstance(rotationRequired, Dimensions.getCenterX()/2, Dimensions.getCenterY()));
            AffineTransformOp op = new AffineTransformOp(transformation, AffineTransformOp.TYPE_BILINEAR);
            g.drawImage(op.filter(image, null), 0, 0, null);
        }
        System.out.println(System.currentTimeMillis() - startTime);
       
    }

}

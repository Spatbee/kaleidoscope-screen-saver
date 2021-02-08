import javax.swing.JFrame;
import javax.swing.Timer;


public class AppWindow extends JFrame {
    private final Timer timer = new Timer(1000/Dimensions.getFramesPerSecond(), null);
    public AppWindow() {
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);

        ImageDrawer imageDrawer = new ImageDrawer(getGraphics());

        timer.addActionListener(imageDrawer.getUpdateListener());
        timer.start();
        
        
    }
}

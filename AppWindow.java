import javax.swing.JFrame;

public class AppWindow extends JFrame {
    public AppWindow() {
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);

        add(new ImagePanel());
        paintAll(getGraphics());
        
    }
}

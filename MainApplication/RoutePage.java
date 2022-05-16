package MainApplication;

import javax.swing.*;
import java.awt.*;

public class RoutePage extends JPanel {
    private JButton button1;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JPanel Routebepaling;
    private JLabel labelRoutebepaling;
    private JPanel RouteContent;
    private JPanel RouteMap;
    private JPanel RouteScroll;
    private JScrollPane ReturnScroll;
    private JScrollPane OrderScroll;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public RoutePage() {
        add(Routebepaling);
        Routebepaling.setPreferredSize(new Dimension(screenWidth, 500));
        Routebepaling.setVisible(true);
    }
}

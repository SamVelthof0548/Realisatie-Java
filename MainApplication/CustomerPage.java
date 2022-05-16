package MainApplication;

import javax.swing.*;
import java.awt.*;

public class CustomerPage extends JPanel {

    private JPanel Klanten;
    private JLabel labelKlanten;
    private JTable JTabelKlanten;
    private JPanel klantenWeergeven;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public CustomerPage(){
        add(Klanten);
        Klanten.setPreferredSize(new Dimension(screenWidth, 500));
        Klanten.setVisible(true);
    }
}

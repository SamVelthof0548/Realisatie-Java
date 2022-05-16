package MainApplication;

import javax.swing.*;
import java.awt.*;

public class StockPage extends JPanel {
    private JPanel Voorraad;
    private JLabel labelVoorraad;
    private JPanel voorraadWeergeven;
    private JTable JTabelVoorraad;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public StockPage(){
        add(Voorraad);
        Voorraad.setPreferredSize(new Dimension(screenWidth, 500));
        Voorraad.setVisible(true);
    }
}

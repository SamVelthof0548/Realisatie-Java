package MainApplication;

import javax.swing.*;
import java.awt.*;

public class ReturnPage extends JPanel {

    private JPanel Retouren;
    private JLabel retourenLabel;
    private JPanel retourenWeergeven;
    private JTable JTabelRetouren;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public ReturnPage(){
        add(Retouren);
        Retouren.setPreferredSize(new Dimension(screenWidth, 500));
        Retouren.setVisible(true);
    }
}

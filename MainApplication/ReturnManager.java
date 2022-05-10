package MainApplication;

import javax.swing.*;
import java.awt.*;

public class ReturnManager extends JPanel {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public ReturnManager() {
        this.setVisible(false);
        this.setLayout(new GridLayout());
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
    }
}

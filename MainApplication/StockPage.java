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

    private void createUIComponents() {
        String[] StockColumnNames = {
                "Art.nr.",
                "Productnaam",
                "Prijs",
                "Afmetingen lxbxh cm",
                "Aantal op voorraad",
                "EAN-nummer"
        };

        String[][] StockData = {
                {"001","koffiemok zwart","€8,95","10x10x15","50","810150324100"},
                {"002","koffiemok blauw","€8,95","10x10x15","50","810150324120"}
        };

        JTabelVoorraad = new JTable(StockData,StockColumnNames);
    }

    private void $$$setupUI$$$() {
        createUIComponents();
    }
}

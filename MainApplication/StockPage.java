package MainApplication;

import javax.swing.*;
import java.awt.*;
import DataBaseConnection.SQLMethods;

public class StockPage extends JPanel
{
    private JPanel Voorraad;
    private JLabel labelVoorraad;
    private JScrollPane voorraadWeergeven;
    private JTable JTabelVoorraad;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public StockPage()
    {
        add(Voorraad);
        Voorraad.setPreferredSize(new Dimension(screenWidth, 500));
        Voorraad.setVisible(true);
    }

    private void createUIComponents()
    {
        SQLMethods sql = new SQLMethods();
        sql.CreateDataBaseConnection();

        String[] StockColumnNames = {"Productnummer","Productnaam","Afmetingen","Gewicht","Barcode","Belastingtarief","Inkoopprijs","Verkoopprijs","Hoeveelheid"};
        String[][] StockData = sql.ViewStockData();

        JTabelVoorraad = new JTable(StockData,StockColumnNames);
    }
}

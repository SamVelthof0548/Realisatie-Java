package MainApplication;

import javax.swing.*;
import java.awt.*;
import javax.swing.JTable;
import DataBaseConnection.SQLMethods;

public class CustomerPage extends JPanel
{
    private JPanel Klanten;
    private JLabel labelKlanten;
    private JScrollPane klantenWeergeven;
    private JTable JTabelKlanten;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public CustomerPage()
    {
        add(Klanten);
        Klanten.setPreferredSize(new Dimension(screenWidth, 500));
        Klanten.setVisible(true);
    }

    private void createUIComponents()
    {
        SQLMethods sql = new SQLMethods();
        sql.CreateDataBaseConnection();

        String[] CustomerColumnNames = {"Klantnummer","Geslacht","Voornaam","Tussenvoegsel","Achternaam","Geboortedatum","Mailadres","Telefoonnummer","Adres","Postcode","Woonplaats"};
        String[][] CustomerData = sql.ViewCustomerData();

        JTabelKlanten = new JTable(CustomerData,CustomerColumnNames);
    }
}

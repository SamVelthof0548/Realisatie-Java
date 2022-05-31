package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import DataBaseConnection.SQLMethods;
import MainApplication.CustomerChange.CustomerChangeDialog;

import static MainApplication.MainApplication.setframe;

public class CustomerPage extends JPanel implements ActionListener
{
    private JPanel Klanten;
    private JLabel labelKlanten;
    private JScrollPane klantenWeergeven;
    private JTable JTabelKlanten;
    private JButton JBtoevoegenKlant;
    private JButton JBwijzigenKlant;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public CustomerPage()
    {
        add(Klanten);
        Klanten.setPreferredSize(new Dimension(screenWidth, 500));
        JBtoevoegenKlant.addActionListener(this);
        JBwijzigenKlant.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==JBtoevoegenKlant)
        {
            CustomerAdd customerAdd = new CustomerAdd(setframe);
        }
        if (e.getSource()==JBwijzigenKlant)
        {
            CustomerChangeDialog customerChangeDialog = new CustomerChangeDialog(setframe);
        }
    }
}

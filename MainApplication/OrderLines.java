package MainApplication;

import javax.swing.*;
import java.awt.*;
import DataBaseConnection.SQLMethods;

public class OrderLines extends JFrame
{
    public OrderLines(Object orderID)
    {
        SQLMethods sqlMethods = new SQLMethods();
        sqlMethods.CreateDataBaseConnection();
        sqlMethods.getOrderLinesVariables(orderID);

        JFrame OrderLines = new JFrame();
        setTitle("Ordernummer: "+orderID);
        setSize(600,400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,1));

        String name;
        if(sqlMethods.suffix_o.equals("")||sqlMethods.suffix_o==null)
        {
            name = sqlMethods.firstName_o+" "+sqlMethods.lastName_o;
        }
        else
        {
            name = sqlMethods.firstName_o+" "+sqlMethods.suffix_o+" "+sqlMethods.lastName_o;
        }

        JPanel PData = new JPanel(new GridLayout(10,2));
        PData.add(new JLabel("Ordernummer:"));
        PData.add(new JLabel(sqlMethods.orderID_o));
        PData.add(new JLabel("Klantnummer:"));
        PData.add(new JLabel(sqlMethods.customerID_o));
        PData.add(new JLabel("Status:"));
        PData.add(new JLabel(sqlMethods.status_o));
        PData.add(new JLabel("Naam:"));
        PData.add(new JLabel(name));
        PData.add(new JLabel("Mailadres:"));
        PData.add(new JLabel(sqlMethods.emailAddress_o));
        PData.add(new JLabel("Telefoonnummer:"));
        PData.add(new JLabel(sqlMethods.mobilePhone_o));
        PData.add(new JLabel("Adres:"));
        PData.add(new JLabel(sqlMethods.address_o));
        PData.add(new JLabel("Postcode:"));
        PData.add(new JLabel(sqlMethods.postalCode_o+" "+sqlMethods.placeOfResidence_o));
        PData.add(new JLabel("Orderdatum:"));
        PData.add(new JLabel(sqlMethods.orderDate));
        PData.add(new JLabel("Verwachte leverdatum:"));
        PData.add(new JLabel(sqlMethods.expectedDeliveryDate));

        JScrollPane PTable = new JScrollPane();

        add(PData);
        add(PTable);
        setVisible(true);
    }
}

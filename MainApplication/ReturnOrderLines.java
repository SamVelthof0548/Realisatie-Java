package MainApplication;

import javax.swing.*;
import java.awt.*;
import DataBaseConnection.SQLMethods;

public class ReturnOrderLines extends JFrame
{
    public ReturnOrderLines(Object returnOrderID)
    {
        SQLMethods sqlMethods = new SQLMethods();
        sqlMethods.CreateDataBaseConnection();
        sqlMethods.getReturnOrderLinesVariables(returnOrderID);

        JFrame ReturnOrderLines = new JFrame();
        setTitle("Retourordernummer: "+returnOrderID);
        setSize(1000,400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,1));

        String name;
        if(sqlMethods.suffix_r.equals("")||sqlMethods.suffix_r==null)
        {
            name = sqlMethods.firstName_r+" "+sqlMethods.lastName_r;
        }
        else
        {
            name = sqlMethods.firstName_r+" "+sqlMethods.suffix_r+" "+sqlMethods.lastName_r;
        }

        JPanel PData = new JPanel(new GridLayout(10,2));
        PData.add(new JLabel("Retourordernummer:"));
        PData.add(new JLabel(sqlMethods.returnOrderID_r));
        PData.add(new JLabel("Oorspronkelijk ordernummer:"));
        PData.add(new JLabel(sqlMethods.orderID_r));
        PData.add(new JLabel("Klantnummer:"));
        PData.add(new JLabel(sqlMethods.customerID_r));
        PData.add(new JLabel("Status:"));
        PData.add(new JLabel(sqlMethods.status_r));
        PData.add(new JLabel("Naam:"));
        PData.add(new JLabel(name));
        PData.add(new JLabel("Mailadres:"));
        PData.add(new JLabel(sqlMethods.emailAddress_r));
        PData.add(new JLabel("Telefoonnummer:"));
        PData.add(new JLabel(sqlMethods.mobilePhone_r));
        PData.add(new JLabel("Adres:"));
        PData.add(new JLabel(sqlMethods.address_r));
        PData.add(new JLabel("Postcode:"));
        PData.add(new JLabel(sqlMethods.postalCode_r+" "+sqlMethods.placeOfResidence_r));
        PData.add(new JLabel("Datum retour aangemaakt:"));
        PData.add(new JLabel(sqlMethods.returnOrderDate));

        add(PData);
        setVisible(true);

    }
}

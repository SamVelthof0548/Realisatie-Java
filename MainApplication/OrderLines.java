package MainApplication;

import javax.swing.*;
import java.awt.*;

public class OrderLines extends JFrame
{
    public OrderLines(Object OrderID)
    {
        JFrame OrderLines = new JFrame();
        setTitle("Ordernummer: "+OrderID);
        setSize(600,600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,1));

        JPanel PData = new JPanel(new GridLayout(2,1));
        PData.add(new JLabel("Ordernummer:"));
        PData.add(new JLabel());
        PData.add(new JLabel("Klantnummer:"));
        PData.add(new JLabel());
        PData.add(new JLabel("Naam:"));
        PData.add(new JLabel());
        PData.add(new JLabel("Mailadres:"));
        PData.add(new JLabel());
        PData.add(new JLabel("Telefoonnummer:"));
        PData.add(new JLabel());
        PData.add(new JLabel("Adres:"));
        PData.add(new JLabel());
        PData.add(new JLabel("Postcode:"));
        PData.add(new JLabel());
        PData.add(new JLabel("Orderdatum:"));
        PData.add(new JLabel());


        JScrollPane PTable = new JScrollPane();

        add(PData);
        add(PTable);
        setVisible(true);
    }
}

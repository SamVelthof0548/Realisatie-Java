package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductDialog extends JDialog implements ActionListener
{
    private JButton JBtoevoegen;
    private JButton JBannuleren;
    private JTextField JTproductnaam, JTproductgrootte, JTproductgewicht, JTeancode, JTbelastigspercentage, JTprijs, JTverkoopprijs;

    public ProductDialog(JFrame frame)
    {
        super (frame,true);
        setTitle("Product Toevoegen");
        setLayout(new GridLayout(4,4));
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

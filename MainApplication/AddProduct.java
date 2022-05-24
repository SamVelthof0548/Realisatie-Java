package MainApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProduct extends JDialog implements ActionListener {
    private JPanel contentPane;
    private JPanel buttons;
    private JPanel labels_textfield;
    private JTextField JTproductNaam,JTproductGrootte,JTproductGewicht,JTeancode,JTbelastingpercentage,JTprijs,JTverkoopprijs;
    private JLabel JLproductNaam,JLproductGrootte,JLproductGewicht,JLeancode,JLbelastingpercentage,JLprijs,JLverkooprijs;
    private String productNaam,productGrootte,eancode;
    private double productGewicht,belastingpercentage,prijs,verkooprijs;
    private JPanel Panel1,Panel2,Panel3,Panel4,Panel5,Panel6,Panel7;
    private JButton JBproductToevoegen;
    private JButton JBannuleren;

    public AddProduct(JFrame frame)
    {
        super(frame,true);
        add(contentPane);
        setTitle("Product Toevoegen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==JBproductToevoegen)
        {
            try
            {
                productNaam=JTproductNaam.getText();
                productGrootte=JTproductGrootte.getText();
                productGewicht=Double.parseDouble(JTproductGewicht.getText());
                eancode=JTeancode.getText();
                belastingpercentage=Double.parseDouble(JTbelastingpercentage.getText());
                prijs=Double.parseDouble(JTprijs.getText());
                verkooprijs=Double.parseDouble(JTverkoopprijs.getText());
            }
            catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null,"Vul bij gewicht, EAN-code, belastingspercentage, prijs en verkoopprijs een nummer in!","FOUT!!",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource()==JBannuleren)
        {
            setVisible(false);
        }
    }
}

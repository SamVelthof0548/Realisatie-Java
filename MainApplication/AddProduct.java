package MainApplication;

import javax.swing.*;
import java.awt.event.*;

public class AddProduct extends JDialog implements ActionListener{
    private JPanel contentPane;
    private JPanel buttons;
    private JPanel label_textfield;
    private JTextField JTproductNaam,JTproductGrootte,JTproductGewicht,JTeancode,JTbelastingpercentage,JTprijs,JTverkoopprijs;
    private JLabel JLproductNaam,JLproductGrootte,JLproductGewicht,JLeancode,JLbelastingpercentage,JLprijs,JLverkoopprijs;
    private String productNaam,productGrootte,eancode;
    private double productGewicht,belastingpercentage,prijs,verkooprijs;
    private JPanel Panel1,Panel2,Panel3,Panel4,Panel5,Panel6,Panel7;
    private JButton JBtoevoegenProduct,JBannuleren;

    public AddProduct() {
        setContentPane(contentPane);
        setModal(true);
        pack();

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }

//    public static void main(String[] args) {
//        AddProduct dialog = new AddProduct();
//    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==JBtoevoegenProduct)
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

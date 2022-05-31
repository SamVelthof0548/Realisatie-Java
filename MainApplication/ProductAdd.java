package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DataBaseConnection.SQLMethods;

public class ProductAdd extends JDialog implements ActionListener
{
    private JPanel label_textfield,button;
    private JTextField JTproductNaam,JTproductGrootte,JTproductGewicht,JTeancode,JTbelastingpercentage,JTprijs,JTverkoopprijs,JTvoorraad;
    private JLabel JLproductNaam,JLproductGrootte,JLproductGewicht,JLeancode,JLbelastingpercentage,JLprijs,JLverkoopprijs,JLvoorraad;
    private JButton JBtoevoegenProduct,JBannuleren;
    private String productNaam,productGrootte,eancode;
    private double productGewicht,belastingpercentage,prijs,verkooprijs;
    private int voorraad;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    static boolean gelukt;

    public ProductAdd(JFrame frame){
        super(frame,true);
        setLayout(new GridLayout(2,1));
        pack();
        setSize(new Dimension((screenWidth), 200));
        setTitle("Product Toevoegen");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        label_textfield=new JPanel(new GridLayout(2,8));

        label_textfield.add(JLproductNaam=new JLabel("Productnaam"));
        label_textfield.add(JLproductGrootte=new JLabel("Afmetingen"));
        label_textfield.add(JLproductGewicht=new JLabel("Gewicht"));
        label_textfield.add(JLeancode=new JLabel("Barcode"));
        label_textfield.add(JLbelastingpercentage=new JLabel("Belastingtarief"));
        label_textfield.add(JLprijs=new JLabel("Inkoopprijs"));
        label_textfield.add(JLverkoopprijs=new JLabel("Verkoopprijs"));
        label_textfield.add(JLvoorraad=new JLabel("Voorraad"));

        label_textfield.add(JTproductNaam=new JTextField());
        label_textfield.add(JTproductGrootte=new JTextField());
        label_textfield.add(JTproductGewicht=new JTextField());
        label_textfield.add(JTeancode=new JTextField());
        label_textfield.add(JTbelastingpercentage=new JTextField());
        label_textfield.add(JTprijs=new JTextField());
        label_textfield.add(JTverkoopprijs=new JTextField());
        label_textfield.add(JTvoorraad=new JTextField());

        button=new JPanel(new GridLayout(1,2));
        button.setPreferredSize(new Dimension(screenWidth,100));

        button.add(JBtoevoegenProduct=new JButton("Toevoegen Product"));
        JBtoevoegenProduct.addActionListener(this);
        button.add(JBannuleren=new JButton("Annuleren"));
        JBannuleren.addActionListener(this);

        add(label_textfield);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==JBtoevoegenProduct)
        {
            SQLMethods sql = new SQLMethods();
            sql.CreateDataBaseConnection();
            try
            {
                productNaam=JTproductNaam.getText();
                productGrootte=JTproductGrootte.getText();
                productGewicht=Double.parseDouble(JTproductGewicht.getText());
                eancode=JTeancode.getText();
                belastingpercentage=Double.parseDouble(JTbelastingpercentage.getText());
                prijs=Double.parseDouble(JTprijs.getText());
                verkooprijs=Double.parseDouble(JTverkoopprijs.getText());
                voorraad=Integer.parseInt(JTvoorraad.getText());
                gelukt=true;
            }
            catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null,"Vul bij gewicht, EAN-code, belastingspercentage, prijs en verkoopprijs een getal in!","FOUT!!",JOptionPane.ERROR_MESSAGE);
                gelukt=false;
            }
            sql.addProduct(productNaam,productGrootte,productGewicht,eancode,belastingpercentage,prijs,verkooprijs,voorraad);
            if (gelukt)
            {
                setVisible(false);
            }
        }
        else if (e.getSource()==JBannuleren)
        {
            setVisible(false);
        }
    }
}

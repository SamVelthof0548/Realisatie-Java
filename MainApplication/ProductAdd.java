package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductAdd extends JDialog implements ActionListener
{
    private JPanel label_textfield,button;
    private JTextField JTproductNaam,JTproductGrootte,JTproductGewicht,JTeancode,JTbelastingpercentage,JTprijs,JTverkoopprijs;
    private JLabel JLproductNaam,JLproductGrootte,JLproductGewicht,JLeancode,JLbelastingpercentage,JLprijs,JLverkoopprijs;
    private JButton JBtoevoegenProduct,JBannuleren;
    private String productNaam,productGrootte,eancode;
    private double productGewicht,belastingpercentage,prijs,verkooprijs;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public ProductAdd(JFrame frame){
        super(frame,true);
        setLayout(new GridLayout(2,1));
        pack();
        setSize(new Dimension((screenWidth), 200));
        setTitle("Product Toevoegen");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        label_textfield=new JPanel(new GridLayout(2,7));

        label_textfield.add(JLproductNaam=new JLabel("Product Naam"));
        label_textfield.add(JLproductGrootte=new JLabel("Product Grootte"));
        label_textfield.add(JLproductGewicht=new JLabel("Product Gewicht"));
        label_textfield.add(JLeancode=new JLabel("EAN-Code"));
        label_textfield.add(JLbelastingpercentage=new JLabel("Belastingpercentage"));
        label_textfield.add(JLprijs=new JLabel("Prijs"));
        label_textfield.add(JLverkoopprijs=new JLabel("Verkoopprijs"));

        label_textfield.add(JTproductNaam=new JTextField(20));
        label_textfield.add(JTproductGrootte=new JTextField(15));
        label_textfield.add(JTproductGewicht=new JTextField(10));
        label_textfield.add(JTeancode=new JTextField(20));
        label_textfield.add(JTbelastingpercentage=new JTextField(10));
        label_textfield.add(JTprijs=new JTextField(10));
        label_textfield.add(JTverkoopprijs=new JTextField(10));

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

    public static void main (String[] args)
    {
        ProductAdd productAdd = new ProductAdd(new JFrame());
    }

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

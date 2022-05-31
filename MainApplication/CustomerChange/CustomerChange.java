package MainApplication.CustomerChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DataBaseConnection.SQLMethods;

public class CustomerChange extends JDialog implements ActionListener {
    private int klantnummer;
    private JTextField JTproductNaam,JTproductgrootte,JTproductgewicht,JTeancode,JTbelastingpercentage,JTinkoopprijs,JTverkoopprijs,JTvoorraad;
    private JLabel JLproductNaam,JLproductgrootte,JLproductgewicht,JLeancode,JLbelastingpercentage,JLinkoopprijs,JLverkoopprijs,JLvoorraad;
    private JButton JBwijzigen,JBannuleren;
    private String nieuweNaam,nieuweGrootte,nieuweEancode;
    private double nieuwGewicht,nieuwBelastingpercentage,nieuweInkoopprijs,nieuweVerkoopprijs;
    private int nieuweVoorraad;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    static boolean gelukt;

    public CustomerChange(JFrame frame, int klantnummer)
    {
        super(frame,true);
        klantnummer =this.klantnummer;
        pack();
        SQLMethods sqlMethods = new SQLMethods();
        sqlMethods.CreateDataBaseConnection();
        sqlMethods.getProductData(klantnummer);
        setSize(new Dimension(screenWidth,300));
        setTitle("Product wijzigen");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(new GridLayout(2,1));

        JPanel gegevens= new JPanel();
        gegevens.setLayout(new GridLayout(2,8));

        gegevens.add(JLproductNaam=new JLabel("Productnaam"));
        gegevens.add(JLproductgrootte=new JLabel("Afmetingen"));
        gegevens.add(JLproductgewicht=new JLabel("Gewicht"));
        gegevens.add(JLeancode=new JLabel("EAN-Code"));
        gegevens.add(JLbelastingpercentage=new JLabel("Belastingpercentage"));
        gegevens.add(JLinkoopprijs=new JLabel("Inkoopprijs"));
        gegevens.add(JLverkoopprijs=new JLabel("Verkoopprijs"));
        gegevens.add(JLvoorraad=new JLabel("Voorraad"));

        gegevens.add(JTproductNaam=new JTextField(sqlMethods.productnaam));
        gegevens.add(JTproductgrootte=new JTextField(sqlMethods.productgrootte));
        gegevens.add(JTproductgewicht=new JTextField(sqlMethods.productgewicht));
        gegevens.add(JTeancode=new JTextField(sqlMethods.eancode));
        gegevens.add(JTbelastingpercentage=new JTextField(sqlMethods.belastingpercentage));
        gegevens.add(JTinkoopprijs=new JTextField(sqlMethods.inkoopprijs));
        gegevens.add(JTverkoopprijs=new JTextField(sqlMethods.verkoopprijs));
        gegevens.add(JTvoorraad=new JTextField(sqlMethods.voorraad));

        JPanel button=new JPanel();
        button.setLayout(new GridLayout(1,2));
        button.add(JBwijzigen=new JButton("Wijziging doorvoeren"));
        button.add(JBannuleren=new JButton("Annuleren"));
        JBwijzigen.addActionListener(this);
        JBannuleren.addActionListener(this);

        add(gegevens);
        add(button);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==JBwijzigen)
        {
            SQLMethods sql = new SQLMethods();
            sql.CreateDataBaseConnection();
            try
            {
                nieuweNaam=JTproductNaam.getText();
                nieuweGrootte=JTproductgrootte.getText();
                nieuwGewicht=Double.parseDouble(JTproductgewicht.getText());
                nieuweEancode=JTeancode.getText();
                nieuwBelastingpercentage=Double.parseDouble(JTbelastingpercentage.getText());
                nieuweInkoopprijs=Double.parseDouble(JTinkoopprijs.getText());
                nieuweVerkoopprijs=Double.parseDouble(JTverkoopprijs.getText());
                nieuweVoorraad=Integer.parseInt(JTvoorraad.getText());
                gelukt=true;
            }
            catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null,"Vul bij gewicht, EAN-code, belastingspercentage, prijs en verkoopprijs een getal in!","FOUT!!",JOptionPane.ERROR_MESSAGE);
                gelukt=false;
            }
            sql.updateProduct(klantnummer,nieuweNaam,nieuweGrootte,nieuwGewicht,nieuweEancode,nieuwBelastingpercentage,nieuweInkoopprijs,nieuweVerkoopprijs,nieuweVoorraad);
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

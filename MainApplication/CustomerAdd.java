package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataBaseConnection.SQLMethods;

public class CustomerAdd extends JDialog implements ActionListener
{
    private JPanel label_textfield,button;
    private JTextField JTgeslacht,JTvoornaam,JTtussenvoegsel,JTachternaam,JTgeboortedatum,JTmailadres,JTtelefoonnummer,JTadres,JTpostcode,JTwoonplaats;
    private JLabel JLgeslacht,JLvoornaam,JLtussenvoegsel,JLachternaam,JLgeboortedatum,JLmailadres,JLtelefoonnummer,JLadres,JLpostcode,JLwoonplaats;
    private JButton JBtoevoegenKlant,JBannuleren;
    private String geslacht,voornaam,tussenvoegsel,achternaam,mailadres,telefoonnummer,adres,postcode,woonplaats;
    private java.sql.Date geboortedatum;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    static Date formatGBD;
    static boolean tussenvoegselboolean;

    public CustomerAdd(JFrame frame){
        super(frame,true);
        setLayout(new GridLayout(2,1));
        pack();
        setSize(new Dimension((screenWidth), 200));
        setTitle("Klant Toevoegen");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        label_textfield=new JPanel(new GridLayout(2,10));

        label_textfield.add(JLgeslacht=new JLabel("Geslacht"));
        label_textfield.add(JLvoornaam=new JLabel("Voornaam"));
        label_textfield.add(JLtussenvoegsel=new JLabel("Tussenvoegsel"));
        label_textfield.add(JLachternaam=new JLabel("Achternaam"));
        label_textfield.add(JLgeboortedatum=new JLabel("Geboortedatum"));
        label_textfield.add(JLmailadres=new JLabel("Mailadres"));
        label_textfield.add(JLtelefoonnummer=new JLabel("Telefoonnummer"));
        label_textfield.add(JLadres=new JLabel("Adres"));
        label_textfield.add(JLpostcode=new JLabel("Postcode"));
        label_textfield.add(JLwoonplaats=new JLabel("Woonplaats"));

        label_textfield.add(JTgeslacht=new JTextField());
        label_textfield.add(JTvoornaam=new JTextField());
        label_textfield.add(JTtussenvoegsel=new JTextField());
        label_textfield.add(JTachternaam=new JTextField());
        label_textfield.add(JTgeboortedatum=new JTextField());
        label_textfield.add(JTmailadres=new JTextField());
        label_textfield.add(JTtelefoonnummer=new JTextField());
        label_textfield.add(JTadres=new JTextField());
        label_textfield.add(JTpostcode=new JTextField());
        label_textfield.add(JTwoonplaats=new JTextField());

        button=new JPanel(new GridLayout(1,2));
        button.setPreferredSize(new Dimension(screenWidth,100));

        button.add(JBtoevoegenKlant=new JButton("Toevoegen Klant"));
        JBtoevoegenKlant.addActionListener(this);
        button.add(JBannuleren=new JButton("Annuleren"));
        JBannuleren.addActionListener(this);

        add(label_textfield);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==JBtoevoegenKlant)
        {
            SQLMethods sql = new SQLMethods();
            sql.CreateDataBaseConnection();
            try
            {
                geslacht=JTgeslacht.getText();
                voornaam=JTvoornaam.getText();
                if (JTtussenvoegsel.getText()!=null){
                    tussenvoegsel=JTtussenvoegsel.getText();
                    tussenvoegselboolean=true;
                }
                achternaam=JTachternaam.getText();
                formatGBD=new SimpleDateFormat("yyyy/MM/dd").parse(JTgeboortedatum.getText());
                geboortedatum=new java.sql.Date(formatGBD.getTime());
                mailadres=JTmailadres.getText();
                telefoonnummer=JTtelefoonnummer.getText();
                adres=JTadres.getText();
                postcode=JTpostcode.getText();
                woonplaats=JTwoonplaats.getText();
            }
            catch (ParseException pe)
            {
                JOptionPane.showMessageDialog(null,"Vul geboortedatum in als dd/mm/yyyy!","FOUT!!",JOptionPane.ERROR_MESSAGE);
            }
            if (tussenvoegselboolean)
            {
                sql.addKlant(geslacht,voornaam,tussenvoegsel,achternaam,geboortedatum,mailadres,telefoonnummer,adres,postcode,woonplaats);
            }
            else
            {
                sql.addKlant1(geslacht,voornaam,achternaam, (java.sql.Date)geboortedatum,mailadres,telefoonnummer,adres,postcode,woonplaats);
            }
            setVisible(false);
        }
        else if (e.getSource()==JBannuleren)
        {
            setVisible(false);
        }
    }
}

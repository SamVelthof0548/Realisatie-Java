package MainApplication.CustomerChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import DataBaseConnection.SQLMethods;

public class CustomerChange extends JDialog implements ActionListener {
    private int klantnummer=CustomerChangeDialog.klantnummer;
    private JTextField JTgeslacht, JTvoornaam, JTtussenvoegsel, JTachternaam, JTgeboortedatum, JTmailadres, JTtelefoonnummer, JTadres,JTpostcode,JTwoonplaats;
    private JLabel JLgeslacht, JLvoornaam, JLtussenvoegsel,JLachternaam, JLgeboortedatum, JLmailadres, JLtelefoonnummer, JLadres, JLpostcode, JLwoonplaats;
    private JButton JBwijzigen,JBannuleren;
    private String nieuwGeslacht,nieuweVoornaam, nieuwTussenvoegsel,nieuweAchternaam,nieuweMailadres,nieuwTelefoonnummer,nieuwAdres,nieuwePostcode,nieuweWoonplaats;
    private Date nieuweGeboortedatum;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    static java.util.Date formatGBD;
    static boolean gelukt;

    public CustomerChange(JFrame frame)
    {
        super(frame,true);
        SQLMethods sqlMethods = new SQLMethods();
        sqlMethods.CreateDataBaseConnection();
        sqlMethods.getCustomerData(klantnummer);
        setSize(new Dimension(screenWidth,300));
        setTitle("Klant wijzigen");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(new GridLayout(2,1));

        JPanel gegevens= new JPanel();
        gegevens.setLayout(new GridLayout(2,10));

        gegevens.add(JLgeslacht =new JLabel("Geslacht"));
        gegevens.add(JLvoornaam =new JLabel("Voornaam"));
        gegevens.add(JLtussenvoegsel =new JLabel("Tussenvoegsel"));
        gegevens.add(JLachternaam=new JLabel("Achternaam"));
        gegevens.add(JLgeboortedatum =new JLabel("Geboortedatum"));
        gegevens.add(JLmailadres =new JLabel("Mailadres"));
        gegevens.add(JLtelefoonnummer =new JLabel("Telefoonnummer"));
        gegevens.add(JLadres =new JLabel("Adres"));
        gegevens.add(JLpostcode =new JLabel("Postcode"));
        gegevens.add(JLwoonplaats =new JLabel("Woonplaats"));

        gegevens.add(JTgeslacht =new JTextField(sqlMethods.geslacht));
        gegevens.add(JTvoornaam =new JTextField(sqlMethods.voornaam));
        gegevens.add(JTtussenvoegsel =new JTextField(sqlMethods.tussenvoegsel));
        gegevens.add(JTachternaam =new JTextField(sqlMethods.achternaam));
        gegevens.add(JTgeboortedatum =new JTextField(sqlMethods.geboortedatum));
        gegevens.add(JTmailadres =new JTextField(sqlMethods.mailadres));
        gegevens.add(JTtelefoonnummer =new JTextField(sqlMethods.telefoonnummer));
        gegevens.add(JTadres =new JTextField(sqlMethods.adres));
        gegevens.add(JTpostcode =new JTextField(sqlMethods.postcode));
        gegevens.add(JTwoonplaats =new JTextField(sqlMethods.woonplaats));

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
                nieuwGeslacht= JTgeslacht.getText();
                nieuweVoornaam= JTvoornaam.getText();
                nieuwTussenvoegsel = JTtussenvoegsel.getText();
                nieuweAchternaam = JTachternaam.getText();
                formatGBD=new SimpleDateFormat("yyyy/MM/dd").parse(JTgeboortedatum.getText());
                nieuweGeboortedatum=new java.sql.Date(formatGBD.getTime());
                nieuweMailadres= JTmailadres.getText();
                nieuwTelefoonnummer = JTtelefoonnummer.getText();
                nieuwAdres = JTadres.getText();
                nieuwePostcode = JTpostcode.getText();
                nieuweWoonplaats = JTwoonplaats.getText();
                gelukt=true;
            }
            catch (ParseException pe)
            {
                JOptionPane.showMessageDialog(null,"Vul  geboortedatum in als yyyy/mm/dd!","FOUT!!",JOptionPane.ERROR_MESSAGE);
                gelukt=false;
            }
            sql.updateCustomer(klantnummer,nieuwGeslacht,nieuweVoornaam,nieuwTussenvoegsel, nieuweAchternaam,nieuweGeboortedatum,nieuweMailadres,nieuwTelefoonnummer,nieuwAdres,nieuwePostcode,nieuweWoonplaats);
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

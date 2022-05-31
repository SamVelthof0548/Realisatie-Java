package MainApplication.CustomerChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerChangeDialog extends JDialog implements ActionListener {
    JButton JBoke;
    JTextField JTcustomerID;
    JLabel JLvraag;
    static JFrame frame;
    static int klantnummer;
    static boolean gelukt;

    public CustomerChangeDialog(JFrame frame)
    {
        super(frame,true);
        CustomerChangeDialog.frame=frame;
        setTitle("Klant Wijzigen");
        setLayout(new GridLayout(3,1));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(new Dimension(600, 200));
        setLocationRelativeTo(null);

        add(JLvraag=new JLabel("Klantnummer van het te wijzigen product"));
        add(JTcustomerID =new JTextField(10));
        add(JBoke=new JButton("Oké"));
        JBoke.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==JBoke)
        {
            try
            {
                klantnummer =Integer.parseInt(JTcustomerID.getText());
                gelukt=true;
            }
            catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null,"Klantnummer moet een getal zijn!","FOUT!!",JOptionPane.ERROR_MESSAGE);
                gelukt=false;
            }
            if (gelukt)
            {
                CustomerChange customerChange = new CustomerChange(frame);
                setVisible(false);
            }
        }
    }
}

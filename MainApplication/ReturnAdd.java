package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataBaseConnection.SQLMethods;

public class ReturnAdd extends JDialog implements ActionListener
{
    private JPanel label_textfield,button;
    private JTextField JTorderID,JTklantID,JTretourdatum,JTopmerkingen;
    private JLabel JLorderID,JLklantID,JLretourdatum,JLopmerkingen;
    private JButton JBtoevoegenRetour,JBannuleren;
    private String opmerkingen;
    private int orderID,klantID;
    private java.sql.Date retourdatum;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    static Date formatDate;
    static boolean gelukt;

    public ReturnAdd(JFrame frame){
        super(frame,true);
        setLayout(new GridLayout(2,1));
        pack();
        setSize(new Dimension((screenWidth), 200));
        setTitle("Retour Toevoegen");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        label_textfield=new JPanel(new GridLayout(2,4));

        label_textfield.add(JLorderID=new JLabel("OrderID"));
        label_textfield.add(JLklantID=new JLabel("KlantID"));
        label_textfield.add(JLretourdatum=new JLabel("Retourdatum"));
        label_textfield.add(JLopmerkingen=new JLabel("Opmerkingen"));

        label_textfield.add(JTorderID=new JTextField());
        label_textfield.add(JTklantID=new JTextField());
        label_textfield.add(JTretourdatum=new JTextField());
        label_textfield.add(JTopmerkingen=new JTextField());

        button=new JPanel(new GridLayout(1,2));
        button.setPreferredSize(new Dimension(screenWidth,100));

        button.add(JBtoevoegenRetour=new JButton("Toevoegen Order"));
        JBtoevoegenRetour.addActionListener(this);
        button.add(JBannuleren=new JButton("Annuleren"));
        JBannuleren.addActionListener(this);

        add(label_textfield);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==JBtoevoegenRetour)
        {
            SQLMethods sql = new SQLMethods();
            sql.CreateDataBaseConnection();
            try
            {
                orderID=Integer.parseInt(JTorderID.getText());
                klantID=Integer.parseInt(JTklantID.getText());
                try
                {
                    formatDate=new SimpleDateFormat("yyyy/MM/dd").parse(JTretourdatum.getText());
                    retourdatum=new java.sql.Date(formatDate.getTime());
                }
                catch (ParseException pe)
                {
                    JOptionPane.showMessageDialog(null,"Vul retourdatum in als dd/mm/yyyy!","FOUT!!",JOptionPane.ERROR_MESSAGE);
                }
                opmerkingen=JTopmerkingen.getText();
                gelukt=true;
            }
            catch (NumberFormatException nfe)
            {
                gelukt=false;
                JOptionPane.showMessageDialog(null,"Vul bij orderID en klantID een getal in!","FOUT!!",JOptionPane.ERROR_MESSAGE);
            }
            sql.addReturn(orderID,klantID,retourdatum,opmerkingen);
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

package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataBaseConnection.SQLMethods;

public class OrderAdd extends JDialog implements ActionListener
{
    private JPanel label_textfield,button;
    private JTextField JTklantID,JTorderdatum,JTverwachteLeverdatum,JTopmerkingen;
    private JLabel JLklantID,JLorderdatum,JLverwachteLeverdatum,JLopmerkingen;
    private JButton JBtoevoegenOrder,JBannuleren;
    private String opmerkingen;
    private int klantID;
    private java.sql.Date orderdatum,verwachteLeverdatum;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    static Date formatDate;

    public OrderAdd(JFrame frame){
        super(frame,true);
        setLayout(new GridLayout(2,1));
        pack();
        setSize(new Dimension((screenWidth), 200));
        setTitle("Order Toevoegen");
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        label_textfield=new JPanel(new GridLayout(2,4));

        label_textfield.add(JLklantID=new JLabel("KlantID"));
        label_textfield.add(JLorderdatum=new JLabel("Orderdatum"));
        label_textfield.add(JLverwachteLeverdatum=new JLabel("Verwachte Leverdatum"));
        label_textfield.add(JLopmerkingen=new JLabel("Opmerkingen"));

        label_textfield.add(JTklantID=new JTextField());
        label_textfield.add(JTorderdatum=new JTextField());
        label_textfield.add(JTverwachteLeverdatum=new JTextField());
        label_textfield.add(JTopmerkingen=new JTextField());

        button=new JPanel(new GridLayout(1,2));
        button.setPreferredSize(new Dimension(screenWidth,100));

        button.add(JBtoevoegenOrder=new JButton("Toevoegen Order"));
        JBtoevoegenOrder.addActionListener(this);
        button.add(JBannuleren=new JButton("Annuleren"));
        JBannuleren.addActionListener(this);

        add(label_textfield);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==JBtoevoegenOrder)
        {
            SQLMethods sql = new SQLMethods();
            sql.CreateDataBaseConnection();
            try
            {
                klantID=Integer.parseInt(JTklantID.getText());
                try
                {
                    formatDate=new SimpleDateFormat("yyyy/MM/dd").parse(JTorderdatum.getText());
                    orderdatum=new java.sql.Date(formatDate.getTime());
                    formatDate=new SimpleDateFormat("yyyy/MM/dd").parse(JTverwachteLeverdatum.getText());
                    verwachteLeverdatum=new java.sql.Date(formatDate.getTime());
                }
                catch (ParseException pe)
                {
                    JOptionPane.showMessageDialog(null,"Vul orderdatum en verwachte leverdatum in als dd/mm/yyyy!","FOUT!!",JOptionPane.ERROR_MESSAGE);
                }
                opmerkingen=JTopmerkingen.getText();
            }
            catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null,"Vul bij klantID een getal in!","FOUT!!",JOptionPane.ERROR_MESSAGE);
            }
            sql.addOrder(klantID,orderdatum,verwachteLeverdatum,opmerkingen);
            setVisible(false);
        }
        else if (e.getSource()==JBannuleren)
        {
            setVisible(false);
        }
    }
}

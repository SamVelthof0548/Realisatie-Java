package MainApplication.OrderlineChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DataBaseConnection.SQLMethods;

public class OrderlineChange extends JDialog implements ActionListener {
    private int orderlinenr=OrderlineChangeDialog.orderlinenummer;
    private JTextField JTproductID, JThoeveelheid;
    private JLabel JLproductID, JLhoeveelheid;
    private JButton JBwijzigen,JBannuleren;
    private int nieuweProductID, nieuweHoeveelheid;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    static boolean gelukt;

    public OrderlineChange(JFrame frame)
    {
        super(frame,true);
        SQLMethods sqlMethods = new SQLMethods();
        sqlMethods.CreateDataBaseConnection();
        sqlMethods.getOrderlineData(orderlinenr);
        setSize(new Dimension(screenWidth,300));
        setTitle("Order wijzigen");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(new GridLayout(2,1));

        JPanel gegevens= new JPanel();
        gegevens.setLayout(new GridLayout(2,2));

        gegevens.add(JLproductID =new JLabel("ProductID"));
        gegevens.add(JLhoeveelheid =new JLabel("Voorraad"));

        gegevens.add(JTproductID =new JTextField(sqlMethods.productid));
        gegevens.add(JThoeveelheid =new JTextField(sqlMethods.hoeveelheid));

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
                nieuweProductID= Integer.parseInt(JTproductID.getText());
                nieuweHoeveelheid= Integer.parseInt(JThoeveelheid.getText());
                gelukt=true;
            }
            catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null,"Vul bij productID en hoeveelheid een getal in!","FOUT!!",JOptionPane.ERROR_MESSAGE);
                gelukt=false;
            }
            sql.updateOrderlines(orderlinenr,nieuweProductID,nieuweHoeveelheid);
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

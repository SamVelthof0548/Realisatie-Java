package MainApplication.OrderlineChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderlineChangeDialog extends JDialog implements ActionListener {
    JButton JBoke;
    JTextField JTorderlineID;
    JLabel JLvraag;
    static JFrame frame;
    static int orderlinenummer;
    static boolean gelukt;

    public OrderlineChangeDialog(JFrame frame)
    {
        super(frame,true);
        OrderlineChangeDialog.frame=frame;
        setTitle("Order Wijzigen");
        setLayout(new GridLayout(3,1));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(new Dimension(600, 200));
        setLocationRelativeTo(null);

        add(JLvraag=new JLabel("Orderregelnummer van de te wijzigen orderregel"));
        add(JTorderlineID =new JTextField(10));
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
                orderlinenummer =Integer.parseInt(JTorderlineID.getText());
                gelukt=true;
            }
            catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null,"Klantnummer moet een getal zijn!","FOUT!!",JOptionPane.ERROR_MESSAGE);
                gelukt=false;
            }
            if (gelukt)
            {
                OrderlineChange orderChange = new OrderlineChange(frame);
                setVisible(false);
            }
        }
    }
}

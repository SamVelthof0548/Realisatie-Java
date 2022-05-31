package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductChangeDialog extends JDialog implements ActionListener {
    JButton JBoke;
    JTextField JTproductID;
    JLabel JLvraag;
    static JFrame frame;
    private int productnummer;
    static boolean gelukt;

    public ProductChangeDialog(JFrame frame)
    {
        super(frame,true);
        this.frame=frame;
        setTitle("Product Wijzigen");
        setLayout(new GridLayout(3,1));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();
        setSize(new Dimension(600, 200));
        setLocationRelativeTo(null);

        add(JLvraag=new JLabel("Productnummer van het te wijzigen product"));
        add(JTproductID=new JTextField(10));
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
                productnummer=Integer.parseInt(JTproductID.getText());
                gelukt=true;
            }
            catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null,"Productnummer moet een getal zijn!","FOUT!!",JOptionPane.ERROR_MESSAGE);
                gelukt=false;
            }
            if (gelukt)
            {
                ProductChange productChange = new ProductChange(frame,productnummer);
                setVisible(false);
            }
        }
    }
}

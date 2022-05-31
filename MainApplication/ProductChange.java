package MainApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductChange extends JDialog implements ActionListener {
    JTextField JTproductNaam;

    public ProductChange(JFrame frame,int productnummer)
    {
        super(frame,true);
        add(JTproductNaam=new JTextField(,10))
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

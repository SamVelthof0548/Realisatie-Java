package MainApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DataBaseConnection.SQLMethods;

public class ProductChange extends JDialog implements ActionListener {
    JTextField JTproductNaam;

    public ProductChange(JFrame frame,int productnummer)
    {
        super(frame,true);
        SQLMethods sqlMethods = new SQLMethods();
        sqlMethods.CreateDataBaseConnection();
        sqlMethods.getProductData(productnummer);

        add(JTproductNaam=new JTextField(sqlMethods.productnaam,10));
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

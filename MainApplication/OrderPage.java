package MainApplication;

import javax.swing.*;
import java.awt.*;

public class OrderPage extends JFrame{


    private JPanel Orders;
    private JLabel labelOrders;
    private JPanel ordersWeergeven;
    private JTable JTabelOrders;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public OrderPage(){
        add(Orders);
        Orders.setPreferredSize(new Dimension(screenWidth, 500));
        Orders.setVisible(false);
    }

    public void visible(boolean value)
    {
        Orders.setVisible(value);
    }
}

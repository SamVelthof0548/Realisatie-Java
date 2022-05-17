package MainApplication;

import javax.swing.*;
import java.awt.*;

public class OrderPage extends JPanel {


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
        Orders.setVisible(true);
    }

    private void createUIComponents() {

    }
}

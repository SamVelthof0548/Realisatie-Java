package MainApplication;

import javax.swing.*;
import java.awt.*;

public class OrderPage extends JPanel {


    private JPanel Orders;
    private JLabel labelOrders;
    private JScrollPane ordersWeergeven;
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
        String[] OrderColumnNames = {
                "Ordernr.",
                "Klantnr.",
                "Bezorg datum",
                "Opmerking",
                "Status"
        };

        String[][] OrderData = {
                {"001","800","23-04-2022","","Open"},
                {"002","123","26-01-2022","Graag kartonnen doos","Ontvangen"}
        };

        JTabelOrders = new JTable(OrderData,OrderColumnNames);
    }
}

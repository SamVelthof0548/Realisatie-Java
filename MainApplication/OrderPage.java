package MainApplication;

import DataBaseConnection.SQLMethods;
import javax.swing.*;
import java.awt.*;

public class OrderPage extends JPanel
{
    private JPanel Orders;
    private JLabel labelOrders;
    private JScrollPane ordersWeergeven;
    private JTable JTabelOrders;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public OrderPage()
    {
        add(Orders);
        Orders.setPreferredSize(new Dimension(screenWidth, 500));
        Orders.setVisible(true);
    }

    private void createUIComponents()
    {
        SQLMethods sql = new SQLMethods();
        sql.CreateDataBaseConnection();

        String[] OrderColumnNames = {"Ordernummer","Klantnummer","Orderdatum","Verwachte leverdatum","Opmerkingen","Status"};
        String[][] OrderData = sql.ViewOrderData();

        JTabelOrders = new JTable(OrderData,OrderColumnNames);
    }
}

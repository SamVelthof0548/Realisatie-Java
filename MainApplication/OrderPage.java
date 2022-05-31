package MainApplication;

import DataBaseConnection.SQLMethods;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        JTabelOrders.setDefaultEditor(Object.class, null);
        JTabelOrders.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {
                    int row = JTabelOrders.getSelectedRow();
                    Object OrderID = JTabelOrders.getValueAt(row,0);
                    OrderLines o = new OrderLines(OrderID);

                }
            }
        });
    }
}

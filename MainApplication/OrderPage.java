package MainApplication;

import DataBaseConnection.SQLMethods;
import MainApplication.OrderlineChange.OrderlineChangeDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static MainApplication.MainApplication.setframe;

public class OrderPage extends JPanel implements ActionListener
{
    private JPanel Orders;
    private JLabel labelOrders;
    private JScrollPane ordersWeergeven;
    private JTable JTabelOrders;
    private JButton JBtoevoegenOrders;
    private JButton JBwijzigenOrder;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public OrderPage()
    {
        add(Orders);
        Orders.setPreferredSize(new Dimension(screenWidth, 500));
        JBtoevoegenOrders.addActionListener(this);
        JBwijzigenOrder.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==JBtoevoegenOrders)
        {
            OrderAdd orderAdd = new OrderAdd(setframe);
        }
        if (e.getSource()==JBwijzigenOrder)
        {
            OrderlineChangeDialog changeDialog =new OrderlineChangeDialog(setframe);
        }
    }
}

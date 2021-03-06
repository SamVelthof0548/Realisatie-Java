package MainApplication;

import DataBaseConnection.SQLMethods;
import MainApplication.OrderlineChange.OrderlineChangeDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        JTabelOrders.setDefaultEditor(Object.class, null);
        JTabelOrders.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {
                    int row = JTabelOrders.getSelectedRow();
                    Object orderID = JTabelOrders.getValueAt(row,0);
                    OrderLines o = new OrderLines(orderID);
                }
            }
        });
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

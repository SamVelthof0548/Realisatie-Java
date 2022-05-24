package MainApplication;

import DataBaseConnection.SQLMethods;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

public class RoutePage extends JPanel implements ActionListener {
    private JButton button1;
    private JPanel Routebepaling;
    private JLabel labelRoutebepaling;
    private JPanel RouteContent;
    private JPanel RouteMap;
    private JPanel RouteScroll;
    private JScrollPane scrollReturns;
    private JScrollPane scrollOrders;
    private JList orderlist;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public RoutePage() {
        add(Routebepaling);
        Routebepaling.setPreferredSize(new Dimension(screenWidth, 500));
        Routebepaling.setVisible(true);

        button1.addActionListener(this);
    }

    private void createUIComponents() throws SQLException {
        SQLMethods sql = new SQLMethods();
        sql.CreateDataBaseConnection();

        String[] OrderData = sql.ViewOrderDataRoutepage();

        orderlist = new JList(OrderData); //data has type Object[]
        orderlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        orderlist.setLayoutOrientation(JList.VERTICAL_WRAP);
        orderlist.setVisibleRowCount(-1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(Arrays.toString(orderlist.getSelectedValues()));
    }
}

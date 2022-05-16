package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication extends JFrame implements ActionListener{
    private JButton BtnRoute;
    private JButton BtnReturns;
    private JButton BtnStock;
    private JButton BtnCustomer;
    private JButton BtnOrders;
    private JPanel MainFrame;
    private JPanel MainScreen;

    private RoutePage routePage;
    private StockPage stockPage;
    private CustomerPage customerPage;
    private OrderPage orderPage;
    private ReturnPage returnPage;

    public MainApplication()
    {
        setTitle("KBS Project");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(MainFrame);

        importPages();

        BtnRoute.addActionListener(this);
        BtnReturns.addActionListener(this);
        BtnStock.addActionListener(this);
        BtnCustomer.addActionListener(this);
        BtnOrders.addActionListener(this);

        setVisible(true);
    }

    public void importPages()
    {
        MainScreen.add(routePage = new RoutePage());

        // TODO: add these pages
        MainScreen.add(stockPage = new StockPage());
        MainScreen.add(customerPage = new CustomerPage());
        MainScreen.add(orderPage = new OrderPage());
        MainScreen.add(returnPage = new ReturnPage());
    }

    public void actionPerformed(ActionEvent e) {
        routePage.visible(true);

        // TODO: add these pages
        stockPage.visible(false);
        customerPage.visible(false);
        orderPage.visible(false);
        returnPage.visible(false);

        if (e.getSource() == BtnRoute) {
            routePage.setVisible(true);
        }

        // TODO: add these pages
        if (e.getSource() == BtnStock) {
            stockPage.setVisible(true);
        }
        if (e.getSource() == BtnCustomer) {
            customerPage.setVisible(true);
        }
        if (e.getSource() == orderButton) {
            orderPage.setVisible(true);
        }
        if (e.getSource() == returnButton) {
            returnPage.setVisible(true);
        }
    }
}

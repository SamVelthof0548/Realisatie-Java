package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication extends JFrame implements ActionListener{
    private JButton BtnRoute, BtnReturns, BtnStock, BtnCustomer, BtnOrders;
    private JPanel MainFrame, MainScreen;
    private CardLayout card;
    private RoutePage routePage;
    private StockPage stockPage;
    private CustomerPage customerPage;
    private OrderPage orderPage;
    private ReturnPage returnPage;
    private AddProduct addProductPage;
    static JFrame setframe;

    public MainApplication()
    {
        setframe = this;
        setTitle("KBS Project");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(MainFrame); // set content pane (navbar)

        card = new CardLayout(); // create CardLayout
        MainScreen.setLayout(card); // set CardLayout as MainScreen

        // add pages to the MainScreen CardLayout
        MainScreen.add(routePage = new RoutePage(), "route");
        MainScreen.add(stockPage = new StockPage(), "stock");
        MainScreen.add(customerPage = new CustomerPage(), "customer");
        MainScreen.add(orderPage = new OrderPage(), "orders");
        MainScreen.add(returnPage = new ReturnPage(), "returns");

        // add action listeners
        BtnRoute.addActionListener(this);
        BtnReturns.addActionListener(this);
        BtnStock.addActionListener(this);
        BtnCustomer.addActionListener(this);
        BtnOrders.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == BtnRoute) {
            card.show(MainScreen, "route");
        }
        if (e.getSource() == BtnStock) {
            card.show(MainScreen, "stock");
        }
        if (e.getSource() == BtnCustomer) {
            card.show(MainScreen, "customer");
        }
        if (e.getSource() == BtnOrders) {
            card.show(MainScreen, "orders");
        }
        if (e.getSource() == BtnReturns) {
            card.show(MainScreen, "returns");
        }
    }
}

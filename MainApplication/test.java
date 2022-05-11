package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test extends JFrame implements ActionListener {
    private JButton routeButton;
    private JButton stockButton;
    private JButton customerButton;
    private JButton orderButton;
    private JButton returnButton;
    private RouteManager routePage;
    private StockManager stockPage;
    private CustomerManager customerPage;
    private OrderManager orderPage;
    private ReturnManager returnPage;

    public test()
    {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("KBS Project");
        this.setLayout(new GridLayout(2,1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        NavBar();
        importPages();

        routePage.setVisible(true);

        this.setResizable(true);
        this.setVisible(true);
    }

    private void NavBar()
    {
        this.setLayout(new FlowLayout());

        // Routebepaling pagina
        this.add(routeButton = new JButton("Routebepaling"));
        routeButton.addActionListener(this);

        // Voorraad pagina
        this.add(stockButton = new JButton("Voorraad"));
        stockButton.addActionListener(this);

        // Klanten pagina
        this.add(customerButton = new JButton("Klanten"));
        customerButton.addActionListener(this);

        // Orders pagina
        this.add(orderButton = new JButton("Orders"));
        orderButton.addActionListener(this);

        // Retourzendingen pagina
        this.add(returnButton = new JButton("Retourzendingen"));
        returnButton.addActionListener(this);
    }

    public void importPages()
    {
        this.add(routePage = new RouteManager());
        this.add(stockPage = new StockManager());
        this.add(customerPage = new CustomerManager());
        this.add(orderPage = new OrderManager());
        this.add(returnPage = new ReturnManager());
    }

    public void actionPerformed(ActionEvent e) {
        routePage.setVisible(false);
        stockPage.setVisible(false);
        customerPage.setVisible(false);
        orderPage.setVisible(false);
        returnPage.setVisible(false);

        if (e.getSource() == routeButton) {
            routePage.setVisible(true);
        }
        if (e.getSource() == stockButton) {
            stockPage.setVisible(true);
        }
        if (e.getSource() == customerButton) {
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

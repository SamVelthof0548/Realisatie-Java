package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication extends JFrame implements ActionListener {
    private JButton routeManager;
    private JButton stockManager;
    private JButton customerManager;
    private JButton orderManager;
    private JButton returnManager;

    public MainApplication()
    {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setUndecorated(true);
        this.setLayout(new FlowLayout());

        // insert navbar
        this.NavBar();

        this.setTitle("KBS Project");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void NavBar()
    {
        // Routebepaling pagina
        this.add(routeManager = new JButton("Routebepaling"));
        routeManager.addActionListener(this);

        // Voorraad pagina
        this.add(stockManager = new JButton("Voorraad"));
        stockManager.addActionListener(this);

        // Klanten pagina
        this.add(customerManager = new JButton("Klanten"));
        customerManager.addActionListener(this);

        // Orders pagina
        this.add(orderManager = new JButton("Orders"));
        orderManager.addActionListener(this);

        // Retourzendingen pagina
        this.add(returnManager = new JButton("Retourzendingen"));
        returnManager.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

    }
}

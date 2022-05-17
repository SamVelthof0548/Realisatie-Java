package MainApplication;

import javax.swing.*;
import java.awt.*;
import javax.swing.JTable;

public class CustomerPage extends JPanel {

    private JPanel Klanten;
    private JLabel labelKlanten;
    private JScrollPane klantenWeergeven;
    private JTable JTabelKlanten;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public CustomerPage(){
        add(Klanten);
        Klanten.setPreferredSize(new Dimension(screenWidth, 500));
        Klanten.setVisible(true);
    }

    private void createUIComponents() {
        String[] CustomerColumnNames = {
                "Klantnr.",
                "Naam",
                "E-mail",
                "Woonplaats",
                "Postcode",
                "Huisnr."
        };

        String[][] CustomerData = {
                {"001","Liam Elschot","Iemand@hotmail.com","Steenwijk","8441 op","900"},
                {"002","Liam Elschot","Iemand@hotmail.com","Steenwijk","8441 op","900"}
        };

        JTabelKlanten = new JTable(CustomerData,CustomerColumnNames);
    }
}

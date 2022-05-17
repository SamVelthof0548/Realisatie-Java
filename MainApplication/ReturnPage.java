package MainApplication;

import javax.swing.*;
import java.awt.*;

public class ReturnPage extends JPanel {

    private JPanel Retouren;
    private JLabel retourenLabel;
    private JTable JTabelRetouren;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public ReturnPage(){
        add(Retouren);
        Retouren.setPreferredSize(new Dimension(screenWidth, 500));
        Retouren.setVisible(true);
    }

    private void createUIComponents() {
        String[] ReturnColumnNames = {
                "Retournr.",
                "Ordernr.",
                "Klantnr.",
                "Retour datum",
                "Opmerking",
                "Status"
        };

        String[][] ReturnData = {
                {"001","007","800","23-03-2022","Verkeerde kleur","Open"},
                {"002","090","123","23-01-2022","Kapot geleverd","Ontvangen"}
        };

        JTabelRetouren = new JTable(ReturnData,ReturnColumnNames);
    }
}

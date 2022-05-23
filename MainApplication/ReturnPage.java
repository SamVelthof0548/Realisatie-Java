package MainApplication;

import javax.swing.*;
import java.awt.*;
import DataBaseConnection.SQLMethods;

public class ReturnPage extends JPanel
{

    private JPanel Retouren;
    private JLabel retourenLabel;
    private JTable JTabelRetouren;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public ReturnPage()
    {
        add(Retouren);
        Retouren.setPreferredSize(new Dimension(screenWidth, 500));
        Retouren.setVisible(true);
    }

    private void createUIComponents()
    {
        SQLMethods sql = new SQLMethods();
        sql.CreateDataBaseConnection();

        String[] ReturnColumnNames = {"Retournummer","Ordernummer","Klantnummer","Retourdatum","Opmerkingen","Status"};
        String[][] ReturnData = sql.ViewReturnData();

        JTabelRetouren = new JTable(ReturnData,ReturnColumnNames);
    }
}

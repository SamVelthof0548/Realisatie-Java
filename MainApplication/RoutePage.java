package MainApplication;

import DataBaseConnection.SQLMethods;
import javax.swing.*;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import java.util.ArrayList;

import org.jxmapviewer.viewer.GeoPosition;

public class RoutePage extends JPanel implements ActionListener {
    private JButton button1;
    private JPanel Routebepaling;
    private JLabel labelRoutebepaling, labelReturns, labelOrders;
    private JPanel RouteContent, RouteScroll;
    private JScrollPane scrollReturns, scrollOrders;

    private JList orderlist, returnlist;
    private ArrayList<double[]> coordinates; // longitude and latitude of selected addresses
    private ArrayList<GeoPosition> geopositions; // longitude and latitude of optimized route points
    private ArrayList<String> streetHints; // street hints to help Graphhopper api locate coordinates

    // set dimensions of the screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    TSPsolution solution;

    public RoutePage() {
        add(Routebepaling);
        Routebepaling.setPreferredSize(new Dimension(screenWidth, 500));
        Routebepaling.setVisible(true);
        button1.addActionListener(this);
    }

    private void createUIComponents() throws SQLException {
        SQLMethods sql = new SQLMethods();
        sql.CreateDataBaseConnection();

        DefaultListModel OrderData = sql.ViewOrderDataRoutepage();
        DefaultListModel ReturnData = sql.ViewReturnDataRoutepage();

        orderlist = new JList(OrderData); // data has type Object[]
        orderlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        orderlist.setLayoutOrientation(JList.VERTICAL_WRAP);
        orderlist.setVisibleRowCount(-1);

        returnlist = new JList(ReturnData); // data has type Object[]
        returnlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        returnlist.setLayoutOrientation(JList.VERTICAL_WRAP);
        returnlist.setVisibleRowCount(-1);
    }

    // get longitude and latitude of the selected JList items
    public double[] getLongLang(String zipcode)
    {
        double[] coords = new double[2];
        HttpResponse<JsonNode> result;
        String apikey = "6c4a4b27-9322-4060-b108-0f68c74da030";
        result = Unirest.get("https://graphhopper.com/api/1/geocode?q="+zipcode+"&locale=en&debug=true&key="+apikey).asJson();

        JSONArray hits = result.getBody().getObject().getJSONArray("hits");

        try {
            JSONObject hits1 = hits.getJSONObject(0);
            JSONObject point = hits1.getJSONObject("point");
            coords[0] = Double.parseDouble(point.getString("lat"));
            coords[1] = Double.parseDouble(point.getString("lng"));
        } catch (JSONException e) {
            JOptionPane.showMessageDialog(null,"Onbekende locatie","FOUT!!",JOptionPane.ERROR_MESSAGE);
        }

        return coords;
    }

    // get the selected values from the JList
    public void getValues() {
        Object[] orders = orderlist.getSelectedValues();
        Object[] returns = returnlist.getSelectedValues();

        coordinates = new ArrayList<>();
        streetHints = new ArrayList<>();

        for (Object order : orders) {
            String input = String.valueOf(order);
            coordinates.add(getLongLang(input));
            streetHints.add(input);
        }

        for (Object retour : returns) {
            String input = String.valueOf(retour);
            coordinates.add(getLongLang(input));
            streetHints.add(input);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getValues(); // get selected values

        double startLon = coordinates.get(0)[1];
        double startLat = coordinates.get(0)[0];

        // start the solution by passing the;
        // @start longitude and latitude
        // @the longitude and latitude of every selected point
        // @the street name of the selected points
        if (coordinates.stream().count() < 2 ) {
            JOptionPane.showMessageDialog(null,"Gebruik meer dan 1 locatie","FOUT!!",JOptionPane.ERROR_MESSAGE);
            return;
        }

        solution = new TSPsolution(startLon, startLat, coordinates, streetHints);

        // create a map to display the optimal route
        solution.createRouting(); // show map
    }
}

package MainApplication;

import DataBaseConnection.SQLMethods;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import java.util.ArrayList;
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
    private ArrayList<String> geolocations;

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

        DefaultListModel OrderData = sql.ViewOrderDataRoutepage();

        orderlist = new JList(OrderData); //data has type Object[]
        orderlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        orderlist.setLayoutOrientation(JList.VERTICAL_WRAP);
        orderlist.setVisibleRowCount(-1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object[] orders = orderlist.getSelectedValues();
        geolocations = new ArrayList<>();

        for (Object order : orders) {
            String input = String.valueOf(order);
            geolocations.add(getGeolocation(input));
        }
    }

    public String getGeolocation(String zipcode)
    {
        HttpResponse<JsonNode> result;
        String apikey = "6c4a4b27-9322-4060-b108-0f68c74da030";
        result = Unirest.get("https://graphhopper.com/api/1/geocode?q="+zipcode+"&locale=en&debug=true&key="+apikey).asJson();

        JSONArray hits = result.getBody().getObject().getJSONArray("hits");
        JSONObject hits1 = hits.getJSONObject(0);
        JSONObject point = hits1.getJSONObject("point");
        String lat = point.getString("lat");
        String lng = point.getString("lng");

        return lat+", "+lng;
    }
}

package MainApplication;

import DataBaseConnection.SQLMethods;
import javax.swing.*;

import MainApplication.routepainter.RoutePainter;
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
import java.util.HashSet;
import java.util.List;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;


public class RoutePage extends JPanel implements ActionListener {
    private JButton button1;
    private JPanel Routebepaling;
    private JLabel labelRoutebepaling;
    private JPanel RouteContent, RouteMap, RouteScroll;
    private JScrollPane scrollReturns, scrollOrders;
    private JList orderlist;
    private ArrayList<double[]> geolocations;
    private ArrayList<GeoPosition> geopositions;
    private ArrayList<GeoPosition> coordWaypoints;

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

        createRouting();
    }

    public double[] getGeolocation(String zipcode)
    {
        double[] coords = new double[2];
        HttpResponse<JsonNode> result;
        String apikey = "6c4a4b27-9322-4060-b108-0f68c74da030";
        result = Unirest.get("https://graphhopper.com/api/1/geocode?q="+zipcode+"&locale=en&debug=true&key="+apikey).asJson();

        JSONArray hits = result.getBody().getObject().getJSONArray("hits");
        JSONObject hits1 = hits.getJSONObject(0);
        JSONObject point = hits1.getJSONObject("point");
        coords[0] = Double.parseDouble(point.getString("lat"));
        coords[1] = Double.parseDouble(point.getString("lng"));

        return coords;
    }

    public void createRouting(){
        JXMapViewer mapViewer = new JXMapViewer();

        // Display the viewer in a JFrame
        JFrame frame = new JFrame("JXMapviewer2 Example 2");
        frame.getContentPane().add(mapViewer);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // add coordinates to geopositions arraylist
        geopositions = new ArrayList<>();
        for (double[] coords : geolocations) {
            geopositions.add(new GeoPosition(coords));
        }

        // Create a track from the geo-positions
        java.util.List<GeoPosition> track = geopositions;
        RoutePainter routePainter = new RoutePainter(track);

        // Set the focus
        mapViewer.zoomToBestFit(new HashSet<GeoPosition>(track), 0.7);

        coordWaypoints = new ArrayList<>();
        coordWaypoints.addAll(geopositions);

        // Create a compound painter that uses both the route-painter and the waypoint-painter
        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(routePainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
        mapViewer.setOverlayPainter(painter);
    }
}

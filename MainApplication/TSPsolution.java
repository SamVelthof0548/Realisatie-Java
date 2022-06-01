package MainApplication;

import MainApplication.routepainter.RoutePainter;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TSPsolution {
    private JSONObject headerData;
    private ArrayList<double[]> geolocations;

    public TSPsolution(double startLon, double startLat, ArrayList<double[]> coordinates, ArrayList<String> streetHints) {
        // create the header data to pass to the Graphhopper api
        headerData = createHeaderData(startLon, startLat, coordinates, streetHints);
        // fetch the response data from the Graphhopper api
        JSONObject responseData = (JSONObject) generateSolution(headerData);

        // parse the response data from the Graphhopper api
        JSONArray routes = responseData.getJSONArray("routes");
        JSONObject activities = routes.getJSONObject(0);
        JSONArray activities1 = activities.getJSONArray("activities");

        // put the parsed data in an ArrayList @geolocations
        geolocations = new ArrayList<>();
        for (int i=0; i<activities1.length(); i++) {
            double[] latlon = new double[2];
            latlon[0] = activities1.getJSONObject(i).getJSONObject("address").getDouble("lat");
            latlon[1] = activities1.getJSONObject(i).getJSONObject("address").getDouble("lon");

            geolocations.add(latlon);
        }
    }

    // function to generate optimal routing solution
    public JSONObject generateSolution(JSONObject headerData) {
        String apikey = "6c4a4b27-9322-4060-b108-0f68c74da030";

        HttpResponse<JsonNode> response = Unirest.post("https://graphhopper.com/api/1/vrp?key="+ apikey)
            .header("Content-Type", "application/json")
            .body(headerData)
            .asJson();

        try {
            JSONObject solution = response.getBody().getObject().getJSONObject("solution");
            return solution;
        } catch (JSONException e) {
            JOptionPane.showMessageDialog(null,"Je kunt niet meer dan 5 locaties gebruiken, verhoog je abonnement www.graphhopper.com","FOUT!!",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    // function to create main header data
    public JSONObject createHeaderData(double startLon, double startLat, ArrayList<double[]> coordinates, ArrayList<String> streetHints) {
        JSONObject headerData = new JSONObject();
        String streetHint = streetHints.get(0);
        JSONArray vehicles = createVehicleObject(startLon, startLat, streetHint);
        JSONArray services = createServiceObject(coordinates, streetHints);

        headerData.put("vehicles", vehicles);
        headerData.put("services", services);

        return headerData;
    }

    // function to create vehicle sub header data
    public JSONArray createVehicleObject(double startLon, double startLat, String streetHint) {
        JSONArray vehicles = new JSONArray();
        JSONObject vehicle = new JSONObject();
        JSONObject startAddress = new JSONObject();

        vehicle.put("vehicle_id", "bezorger");

        startAddress.put("location_id", "test");
        startAddress.put("lon", startLon);
        startAddress.put("lat", startLat);
        startAddress.put("street_hint", streetHint);

        vehicle.put("start_address", startAddress);
        vehicles.put(vehicle);

        return vehicles;
    }

    // function to create service sub header data
    public JSONArray createServiceObject(ArrayList<double[]> coordinates, ArrayList<String> streetHints) {
        JSONArray services = new JSONArray();

        int id = 1;
        coordinates.remove(0);
        streetHints.remove(0);
        for (double[] coord : coordinates) {
            JSONObject service = new JSONObject();
            JSONObject coords = new JSONObject();

            coords.put("location_id", String.valueOf(id));
            coords.put("lon", coord[1]);
            coords.put("lat", coord[0]);
            coords.put("street_hint", streetHints.get(id-1));

            service.put("id", String.valueOf(id));
            service.put("name", String.valueOf(id));
            service.put("address", coords);

            services.put(service);

            id++;
        }

        return services;
    }

    // create the map with the solution
    public void createRouting() {
        JXMapViewer mapViewer = new JXMapViewer();

        // Display the viewer in a JFrame
        JFrame frame = new JFrame("JXMapviewer2 Example 2");
        frame.getContentPane().add(mapViewer);
        frame.setSize(800, 600);
        frame.setVisible(true);

        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // add coordinates to geopositions arraylist
        ArrayList<GeoPosition> geopositions = new ArrayList<>();
        for (double[] coords : geolocations) {
            geopositions.add(new GeoPosition(coords));
        }

        // Create a track from the geo-positions
        java.util.List<GeoPosition> track = geopositions;
        RoutePainter routePainter = new RoutePainter(track);

        // Set the focus
        mapViewer.zoomToBestFit(new HashSet<GeoPosition>(track), 0.7);

        // Create a compound painter that uses both the route-painter and the waypoint-painter
        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(routePainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
        mapViewer.setOverlayPainter(painter);
    }
}

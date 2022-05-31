package DataBaseConnection;

import java.math.BigDecimal;
import java.sql.*;

public class SQLMethods
{

//    Variabelen die nodig zijn voor interacties met de database.
    private Connection c;
    private Statement s;
    private ResultSet rs;

//    Functie om een connectie met de database aan te maken.
    public void CreateDataBaseConnection()
    {
        try
        {
            String url = "jdbc:mysql://localhost/nerdygadgetsnl";
            String user = "root";
            String password = "";

            c = DriverManager.getConnection(url, user, password);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private int GetRowCount(ResultSet resultSet)
    {
        int rowCount = 0;
        try
        {
            while (resultSet.next())
            {
                rowCount++;
            }
            resultSet.beforeFirst();
            return rowCount;
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
        return 0;
    }


//    Functie om de data uit de stockitems tabel op te halen.
    public String[][] ViewStockData()
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT * FROM stockitems AS si LEFT JOIN stockitemholdings AS sih ON si.StockItemID = sih.StockItemID;");

            String[][] data = new String[GetRowCount(rs)][9];

            int i = 0;
            while (rs.next())
            {
                int stockItemID = rs.getInt("StockItemID");
                String stockItemName = rs.getString("StockItemName");
                String unitSize = rs.getString("UnitSize");
                String unitWeight = rs.getString("UnitWeight");
                String eanCode = rs.getString("EANCode");
                String taxRate = rs.getString("TaxRate");
                String unitPrice = rs.getString("UnitPrice");
                String unitRetailPrice = rs.getString("UnitRetailPrice");
                String quantity = rs.getString("QuantityOnHand");

                data[i][0] = stockItemID + "";
                data[i][1] = stockItemName;
                data[i][2] = unitSize + " cm";
                data[i][3] = unitWeight + " Kg";
                data[i][4] = eanCode;
                data[i][5] = taxRate + "%";
                data[i][6] = "€" + unitPrice;
                data[i][7] = "€" + unitRetailPrice;
                data[i][8] = quantity;

                i++;
            }
            return data;
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
        return null;
    }

//    Functie om de data uit de customers op tabel te halen.
    public String[][] ViewCustomerData()
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT * FROM customers;");

            String[][] data = new String[GetRowCount(rs)][11];

            int i = 0;
            while (rs.next())
            {
                int customerID = rs.getInt("CustomerID");
                String sex = rs.getString("Sex");
                String firstName = rs.getString("FirstName");
                String suffix = rs.getString("Suffix");
                String lastName = rs.getString("LastName");
                String birthDate = rs.getString("BirthDate");
                String emailAddress = rs.getString("EmailAddress");
                String mobilePhone = rs.getString("MobilePhone");
                String address = rs.getString("Address");
                String postalCode = rs.getString("PostalCode");
                String placeOfResidence = rs.getString("PlaceOfResidence");

                data[i][0] = customerID + "";
                data[i][1] = sex;
                data[i][2] = firstName;
                data[i][3] = suffix;
                data[i][4] = lastName;
                data[i][5] = birthDate;
                data[i][6] = emailAddress;
                data[i][7] = mobilePhone;
                data[i][8] = address;
                data[i][9] = postalCode;
                data[i][10] = placeOfResidence;

                i++;
            }
            return data;
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
        return null;
    }

//    Functie om de data uit de orders tabel op te halen.
    public String[][] ViewOrderData()
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT * FROM orders;");

            String[][] data = new String[GetRowCount(rs)][6];

            int i = 0;
            while (rs.next())
            {
                int orderID = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String orderDate = rs.getString("OrderDate");
                String expectedDeliveryDate = rs.getString("ExpectedDeliveryDate");
                String comments = rs.getString("Comments");
                String status = rs.getString("Status");

                data[i][0] = orderID + "";
                data[i][1] = customerID + "";
                data[i][2] = orderDate;
                data[i][3] = expectedDeliveryDate;
                data[i][4] = comments;
                data[i][5] = status;

                i++;
            }
            return data;
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
        return null;
    }

    public String[] ViewOrderDataRoutepage()
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT o.OrderID, c.CustomerID, c.Address, c.PostalCode, c.PlaceOfResidence FROM orders AS o LEFT JOIN customers AS c on o.CustomerID = c.CustomerID WHERE o.status = 'Open' ORDER BY o.OrderID;");

            String[] data = new String[GetRowCount(rs)];

            int i = 0;
            while (rs.next())
            {
                int orderID = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String address = rs.getString("Address");
                String postalCode = rs.getString("PostalCode");
                String placeOfResidence = rs.getString("PlaceOfResidence");

                data[i] = orderID + " " + customerID + " " + address + " " + postalCode + " " + placeOfResidence;

                i++;
            }

            return data;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return new String[]{"Geen orders beschikbaar"};
    }

    public String[] ViewReturnDataRoutepage()
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT o.OrderID, c.CustomerID, c.Address, c.PostalCode, c.PlaceOfResidence FROM orders AS o LEFT JOIN customers AS c on o.CustomerID = c.CustomerID ORDER BY o.OrderID;");

            String[] data = new String[GetRowCount(rs)];

            int i = 0;
            while (rs.next())
            {
                int orderID = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String address = rs.getString("Address");
                String postalCode = rs.getString("PostalCode");
                String placeOfResidence = rs.getString("PlaceOfResidence");

                data[i] = orderID + " " + customerID + " " + address + " " + postalCode + " " + placeOfResidence;

                i++;
            }

            return data;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return new String[]{"Geen orders beschikbaar"};
    }

//    Functie om de data uit de returnorders tabel op te halen.
    public String[][] ViewReturnData()
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT * FROM returnorders;");

            String[][] data = new String[GetRowCount(rs)][6];

            int i = 0;
            while (rs.next())
            {
                int returnOrderID = rs.getInt("ReturnOrderID");
                int orderID = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String returnOrderDate = rs.getString("ReturnOrderDate");
                String comments = rs.getString("Comments");
                String status = rs.getString("Status");

                data[i][0] = returnOrderID + "";
                data[i][1] = orderID + "";
                data[i][2] = customerID + "";
                data[i][3] = returnOrderDate;
                data[i][4] =comments;
                data[i][5] = status;

                i++;
            }
            return data;
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
        return null;
    }

    public String[][] viewOrderLinesData(Object orderID)
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT ol.OrderLineID, ol.OrderID, ol.StockItemID, s.StockItemName, ol.Quantity, s.EANCode, s.TaxRate, s.UnitRetailPrice FROM orderlines AS ol JOIN stockitems AS s ON ol.StockItemID = s.StockItemID WHERE OrderID = "+orderID+" ORDER BY OrderLineID;");

            String[][] data = new String[GetRowCount(rs)][8];

            int i = 0;
            int aantalResultaten = 1;
            while (rs.next())
            {
                int stockItemID = rs.getInt("StockItemID");
                String stockItemName = rs.getString("StockItemName");
                int quantity = rs.getInt("Quantity");
                String eanCode = rs.getString("EANCode");
                float taxRate = rs.getFloat("TaxRate");
                float unitRetailPrice = rs.getFloat("UnitRetailPrice");

                data[i][0] = aantalResultaten+"";
                data[i][1] = stockItemID+"";
                data[i][2] = stockItemName;
                data[i][3] = quantity+"";
                data[i][4] = eanCode;
                data[i][5] = taxRate+"%";
                data[i][6] = "€"+unitRetailPrice;
                data[i][7] = "€"+(unitRetailPrice*quantity);

                i++;
                aantalResultaten++;
            }
            return data;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String orderID_o,customerID_o,orderDate,expectedDeliveryDate,status_o,firstName_o,suffix_o,lastName_o,emailAddress_o,mobilePhone_o,address_o,postalCode_o,placeOfResidence_o;
    public void getOrderLinesVariables(Object orderID)
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT o.OrderID, o.CustomerID, o.OrderDate, o.ExpectedDeliveryDate, o.Status, c.FirstName, c.Suffix, c.LastName, c.EmailAddress, c.MobilePhone, c.Address, c.PostalCode, c.PlaceOfResidence FROM orders AS o JOIN customers AS c on o.CustomerID = c.CustomerID WHERE OrderID = "+orderID+" ORDER BY OrderID;");

            while (rs.next())
            {
                orderID_o = rs.getString("OrderID");
                customerID_o = rs.getString("CustomerID");
                orderDate = rs.getString("OrderDate");
                expectedDeliveryDate = rs.getString("ExpectedDeliveryDate");
                status_o = rs.getString("Status");
                firstName_o = rs.getString("FirstName");
                suffix_o = rs.getString("Suffix");
                lastName_o = rs.getString("LastName");
                emailAddress_o = rs.getString("EmailAddress");
                mobilePhone_o = rs.getString("MobilePhone");
                address_o = rs.getString("Address");
                postalCode_o = rs.getString("PostalCode");
                placeOfResidence_o = rs.getString("PlaceOfResidence");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
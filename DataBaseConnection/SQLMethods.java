package DataBaseConnection;

import javax.swing.*;
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
            String password = "123456789";

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
                data[i][6] = "???" + unitPrice;
                data[i][7] = "???" + unitRetailPrice;
                data[i][8] = quantity;

                i++;
            }
            c.close();
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
            c.close();
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
            c.close();
            return data;
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
        return null;
    }

    public DefaultListModel ViewOrderDataRoutepage()
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT o.OrderID, c.CustomerID, c.Address, c.PostalCode, c.PlaceOfResidence FROM orders AS o LEFT JOIN customers AS c on o.CustomerID = c.CustomerID WHERE o.status = 'Open' ORDER BY o.OrderID;");


            DefaultListModel model = new DefaultListModel();

            int i = 0;
            while (rs.next())
            {
                int orderID = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String address = rs.getString("Address");
                String postalCode = rs.getString("PostalCode");
                String placeOfResidence = rs.getString("PlaceOfResidence");

                model.addElement(orderID + ", " + placeOfResidence + ", " + postalCode + ", " + address);

                i++;
            }

            return model;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public DefaultListModel ViewReturnDataRoutepage()
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT r.OrderID, c.CustomerID, c.Address, c.PostalCode, c.PlaceOfResidence FROM returnorders AS r LEFT JOIN customers AS c on r.CustomerID = c.CustomerID WHERE r.status = 'open' ORDER BY r.OrderID;");

            DefaultListModel model = new DefaultListModel();

            int i = 0;
            while (rs.next())
            {
                int orderID = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String address = rs.getString("Address");
                String postalCode = rs.getString("PostalCode");
                String placeOfResidence = rs.getString("PlaceOfResidence");

                model.addElement(orderID + ", " + placeOfResidence + ", " + postalCode + ", " + address);

                i++;
            }

            return model;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
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
            c.close();
            return data;
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
        return null;
    }

    //functie om de ingevulde waardes van een nieuw product in de database te zetten
    public void addProduct(String productNaam,String productGrootte,double productGewicht,String eancode,double belastingpercentage,double prijs,double verkoopprijs,int voorraad)
    {
        try
        {
            String query="insert into stockitems (StockItemName,UnitSize,UnitWeight,EANCode,TaxRate,UnitPrice,UnitRetailPrice,LastEditedWhen)"
                    + " values (?,?,?,?,?,?,?,current_timestamp())";

            PreparedStatement stmt = c.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,productNaam);
            stmt.setString(2,productGrootte);
            stmt.setDouble(3,productGewicht);
            stmt.setString(4,eancode);
            stmt.setDouble(5,belastingpercentage);
            stmt.setDouble(6,prijs);
            stmt.setDouble(7,verkoopprijs);

            stmt.execute();

            rs  = stmt.getGeneratedKeys();
            int result = 0;
            if (rs.next())
            {
                result=rs.getInt(1);
            }

            String query2="insert into stockitemholdings (StockItemID,QuantityOnHand,LastEditedWhen)"
                    + " values (?,?,current_timestamp())";

            PreparedStatement stmt2 = c.prepareStatement(query2);
            stmt2.setInt(1,result);
            stmt2.setInt(2,voorraad);

            stmt2.execute();

            c.close();
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
    }

    //functie om de ingevulde waardes van een nieuwe klant in de database te zetten
    //**deze functie is als een klant WEL een tussenvoegsel heeft**
    public void addKlant(String geslacht,String voornaam,String tussenvoegsel,String achternaam,Date geboortedatum,String mailadres,String telefoonnummer,String adres,String postcode,String woonplaats)
    {
        try
        {
            String query="insert into customers (Sex,FirstName,Suffix,LastName,BirthDate,EmailAddress,MobilePhone,Address,PostalCode,PlaceOfResidence,LastEditedWhen)"
                    + " values (?,?,?,?,?,?,?,?,?,?,current_timestamp())";

            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1,geslacht);
            stmt.setString(2,voornaam);
            stmt.setString(3,tussenvoegsel);
            stmt.setString(4,achternaam);
            stmt.setDate(5,geboortedatum);
            stmt.setString(6,mailadres);
            stmt.setString(7,telefoonnummer);
            stmt.setString(8,adres);
            stmt.setString(9,postcode);
            stmt.setString(10,woonplaats);

            stmt.execute();
            c.close();
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
    }

    //functie om de ingevulde waardes van een nieuwe klant in de database te zetten
    //**deze functie is als een klant NIET een tussenvoegsel heeft**
    public void addKlant1(String geslacht,String voornaam,String achternaam,Date geboortedatum,String mailadres,String telefoonnummer,String adres,String postcode,String woonplaats)
    {
        try
        {
            String query="insert into customers (Sex,FirstName,Suffix,LastName,BirthDate,EmailAddress,MobilePhone,Address,PostalCode,PlaceOfResidence,LastEditedWhen)"
                    + " values (?,?,?,?,?,?,?,?,?,?,current_timestamp())";

            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1,geslacht);
            stmt.setString(2,voornaam);
            stmt.setString(3,"");
            stmt.setString(4,achternaam);
            stmt.setDate(5,geboortedatum);
            stmt.setString(6,mailadres);
            stmt.setString(7,telefoonnummer);
            stmt.setString(8,adres);
            stmt.setString(9,postcode);
            stmt.setString(10,woonplaats);

            stmt.execute();
            c.close();
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
    }

    public void addOrder(int klantID,Date orderdatum,Date verwachtLeverdatum,String opmerkingen)
    {
        try
        {
            String query="insert into orders (CustomerID, OrderDate, ExpectedDeliveryDate, Comments, Status, LastEditedWhen)"
                    + " values (?,?,?,?,?,current_timestamp())";

            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1,klantID);
            stmt.setDate(2,orderdatum);
            stmt.setDate(3,verwachtLeverdatum);
            stmt.setString(4,opmerkingen);
            stmt.setString(5,"Open");

            stmt.execute();
            c.close();
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
    }

    public void addReturn(int orderID,int klantID,Date retourdatum,String opmerkingen)
    {
        try
        {
            String query="insert into returnorders (OrderID, CustomerID, ReturnOrderDate, Comments, Status, LastEditedWhen)"
                    + " values (?,?,?,?,?,current_timestamp())";

            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1,orderID);
            stmt.setInt(2,klantID);
            stmt.setDate(3,retourdatum);
            stmt.setString(4,opmerkingen);
            stmt.setString(5,"Open");

            stmt.execute();
            c.close();
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
    }

    public String productnaam,productgrootte,productgewicht,eancode,belastingpercentage,inkoopprijs,verkoopprijs,voorraad;
    public void getProductData(int productnummer)
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("select s.StockItemName,s.UnitSize,s.UnitWeight,s.EANCode,s.TaxRate,s.UnitPrice,s.UnitRetailPrice,sh.QuantityOnHand from stockitems as s join stockitemholdings as sh on s.StockItemID = sh.StockItemID where s.StockItemID ="+productnummer);

            while (rs.next())
            {
                productnaam = rs.getString("StockItemName");
                productgrootte = rs.getString("UnitSize");
                productgewicht = rs.getString("UnitWeight");
                eancode = rs.getString("EANCode");
                belastingpercentage = rs.getString("TaxRate");
                inkoopprijs = rs.getString("UnitPrice");
                verkoopprijs = rs.getString("UnitRetailPrice");
                voorraad = rs.getString("QuantityOnHand");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void updateProduct(int productnummer,String naam,String grootte,double gewicht,String eancode,double belastingpercentage,double inkoopprijs, double verkoopprijs,int voorraad)
    {
        try
        {
            String query="update stockitems set StockItemName=?,UnitSize=?,UnitWeight=?,EANCode=?,TaxRate=?,UnitPrice=?,UnitRetailPrice=?,LastEditedWhen=current_timestamp() where StockItemID="+productnummer+";";

            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1,naam);
            stmt.setString(2,grootte);
            stmt.setDouble(3,gewicht);
            stmt.setString(4,eancode);
            stmt.setDouble(5,belastingpercentage);
            stmt.setDouble(6,inkoopprijs);
            stmt.setDouble(7,verkoopprijs);

            stmt.execute();

            rs  = stmt.getGeneratedKeys();
            int result = 0;
            if (rs.next())
            {
                result=rs.getInt(1);
            }

            String query2="update stockitemholdings set StockItemID=?,QuantityOnHand=?,LastEditedWhen=current_timestamp() where StockItemID="+productnummer+";";

            PreparedStatement stmt2 = c.prepareStatement(query2);
            stmt2.setInt(1,productnummer);
            stmt2.setInt(2,voorraad);

            stmt2.execute();

            c.close();
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
    }

    public String geslacht,voornaam,tussenvoegsel,achternaam,geboortedatum,mailadres,telefoonnummer,adres,postcode,woonplaats;
    public void getCustomerData(int klantnummer)
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("select * from customers WHERE CustomerID="+klantnummer);

            while (rs.next())
            {
                geslacht = rs.getString("Sex");
                voornaam = rs.getString("FirstName");
                tussenvoegsel = rs.getString("Suffix");
                achternaam = rs.getString("LastName");
                geboortedatum = rs.getString("BirthDate");
                mailadres = rs.getString("EmailAddress");
                telefoonnummer = rs.getString("MobilePhone");
                adres = rs.getString("Address");
                postcode = rs.getString("PostalCode");
                woonplaats = rs.getString("PlaceOfResidence");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void updateCustomer(int klantnummer,String geslacht,String voornaam,String tussenvoegsel,String achternaam,Date geboortedatum,String mailadres,String telefoonnummer,String adres,String postcode,String woonplaats)
    {
        try
        {
            String query="UPDATE customers SET Sex=?,FirstName=?,Suffix=?,LastName=?,BirthDate=?,EmailAddress=?,MobilePhone=?,Address=?,PostalCode=?,PlaceOfResidence=?,LastEditedWhen=current_timestamp() WHERE CustomerID="+klantnummer;

            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1,geslacht);
            stmt.setString(2,voornaam);
            stmt.setString(3,tussenvoegsel);
            stmt.setString(4,achternaam);
            stmt.setDate(5,geboortedatum);
            stmt.setString(6,mailadres);
            stmt.setString(7,telefoonnummer);
            stmt.setString(8,adres);
            stmt.setString(9,postcode);
            stmt.setString(10,woonplaats);

            stmt.execute();

            c.close();
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
    }

    public String hoeveelheid,productid;
    public void getOrderlineData(int ordernr)
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("select * from orderlines WHERE OrderlineID="+ordernr);

            while (rs.next())
            {
                productid = rs.getString("StockItemID");
                hoeveelheid = rs.getString("Quantity");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void updateOrderlines(int orderlinenr,int productid,int hoeveelheid)
    {
        try
        {
            String query="UPDATE orderlines SET StockItemID=?,Quantity=?,LastEditedWhen=current_timestamp() WHERE OrderlineID="+orderlinenr;

            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1,productid);
            stmt.setInt(2,hoeveelheid);

            stmt.execute();

            c.close();
        }
        catch (Exception ex) {System.out.println(ex.getMessage());}
    }

    public String[][] viewOrderLinesData(Object orderID)
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT ol.OrderlineID, ol.OrderID, ol.StockItemID, s.StockItemName, ol.Quantity, s.EANCode, s.TaxRate, s.UnitRetailPrice FROM orderlines AS ol JOIN stockitems AS s ON ol.StockItemID = s.StockItemID WHERE OrderID = "+orderID+" ORDER BY OrderLineID;");

            String[][] data = new String[GetRowCount(rs)][8];

            int i = 0;
            while (rs.next())
            {
                int orderlineID = rs.getInt("OrderlineID");
                int stockItemID = rs.getInt("StockItemID");
                String stockItemName = rs.getString("StockItemName");
                int quantity = rs.getInt("Quantity");
                String eanCode = rs.getString("EANCode");
                float taxRate = rs.getFloat("TaxRate");
                float unitRetailPrice = rs.getFloat("UnitRetailPrice");

                data[i][0] = orderlineID +"";
                data[i][1] = stockItemID+"";
                data[i][2] = stockItemName;
                data[i][3] = quantity+"";
                data[i][4] = eanCode;
                data[i][5] = taxRate+"%";
                data[i][6] = "???"+unitRetailPrice;
                data[i][7] = "???"+Math.round((unitRetailPrice*quantity)*100.0)/100.0;

                i++;
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

    public String[][] viewReturnOrderLinesData(Object returnOrderID)
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT rl.ReturnOrderlineID, rl.ReturnOrderID, s.StockItemID, s.StockItemName, rl.Quantity, s.EANCode, s.TaxRate, s.UnitRetailPrice FROM returnorderlines AS rl JOIN stockitems AS s ON rl.StockItemID = s.StockItemID WHERE rl.ReturnOrderID="+returnOrderID+";");

            String[][] data = new String[GetRowCount(rs)][8];

            int i = 0;
            while (rs.next())
            {
                int returnOrderlineID = rs.getInt("ReturnOrderlineID");
                int stockItemID = rs.getInt("StockItemID");
                String stockItemName = rs.getString("StockItemName");
                int quantity = rs.getInt("Quantity");
                String eanCode = rs.getString("EANCode");
                float taxRate = rs.getFloat("TaxRate");
                float unitRetailPrice = rs.getFloat("UnitRetailPrice");

                data[i][0] = returnOrderlineID +"";
                data[i][1] = stockItemID+"";
                data[i][2] = stockItemName;
                data[i][3] = quantity+"";
                data[i][4] = eanCode;
                data[i][5] = taxRate+"%";
                data[i][6] = "???"+unitRetailPrice;
                data[i][7] = "???"+Math.round((unitRetailPrice*quantity)*100.0)/100.0;

                i++;
            }
            return data;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String returnOrderID_r, orderID_r,customerID_r,returnOrderDate,status_r,firstName_r,suffix_r,lastName_r,emailAddress_r,mobilePhone_r,address_r,postalCode_r,placeOfResidence_r;
    public void getReturnOrderLinesVariables(Object returnOrderID)
    {
        try
        {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery("SELECT r.ReturnOrderID, r.OrderID, r.CustomerID, r.ReturnOrderDate, r.Status, c.FirstName, c.Suffix, c.LastName, c.EmailAddress, c.MobilePhone, c.Address, c.PostalCode, c.PlaceOfResidence FROM returnorders AS r JOIN customers AS c on r.CustomerID = c.CustomerID WHERE r.ReturnOrderID="+returnOrderID+";");

            while (rs.next())
            {
                returnOrderID_r = rs.getString("ReturnOrderID");
                orderID_r = rs.getString("OrderID");
                customerID_r = rs.getString( "CustomerID");
                returnOrderDate = rs.getString("ReturnOrderDate");
                status_r = rs.getString("Status");
                firstName_r = rs.getString("FirstName");
                suffix_r = rs.getString("Suffix");
                lastName_r = rs.getString("LastName");
                emailAddress_r = rs.getString("EmailAddress");
                mobilePhone_r = rs.getString("MobilePhone");
                address_r = rs.getString("Address");
                postalCode_r = rs.getString("PostalCode");
                placeOfResidence_r = rs.getString("PlaceOfResidence");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

//    public void updateOrderStatus(int orderId) {
//        try
//        {
//            String query="UPDATE orders SET Status='Afgerond' WHERE OrderID="+orderId;
//            PreparedStatement stmt = c.prepareStatement(query);
//            stmt.execute();
//            c.close();
//        }
//        catch (Exception ex) {System.out.println(ex.getMessage());}
//    }
}
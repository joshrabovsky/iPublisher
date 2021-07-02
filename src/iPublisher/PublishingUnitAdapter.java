package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PublishingUnitAdapter {

    //declaring a connection
    Connection connection;

    //Constructor
    public PublishingUnitAdapter(Connection conn, Boolean reset) throws SQLException{
        //initializing the connection
        connection = conn;
        //if resetting the DB
        if(reset){
            Statement stmt = connection.createStatement();
            try{
                //Delete all 3 tables
                //Title has no references, therefore deleted first
                stmt.execute("DROP TABLE Titles");
                //Authors deleted second, titles is the only one that references it
                stmt.execute("DROP TABLE Authors");
                //publishing unit last, since authors references publishing units
                stmt.execute("DROP TABLE PublishingUnits");
            }catch(SQLException ex){
                //error
            }finally{
                //Create the table with necessary columns
                stmt.execute("CREATE TABLE PublishingUnits ("
                        + "UnitNo INT NOT NULL PRIMARY KEY, "
                        + "Region CHAR(30) NOT NULL, "
                        + "Name CHAR(30) NOT NULL, "
                        + "Address CHAR(60) NOT NULL "
                        + ")");
            }
            populateSamples();
        }
    }

    //Add some predefined data
    public void populateSamples() throws SQLException{
        this.insertUnit(1, "Chakma", "London", "123 Western Road");
        this.insertUnit(2, "UCC", "Toronto", "675 Alien Way");
        this.insertUnit(3, "Talbot", "Ottawa", "22 Spongebob Stret");
    }

    //Get the maximum ID currently in the DB and add one so next insert has unique ID
    public int getMax() throws SQLException{
        int num = 0;
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT MAX(UnitNo) from PublishingUnits";
        ResultSet rs = stmt.executeQuery(sqlStatement);
        while (rs.next()){
            num = rs.getInt(1) + 1;
        }
        return num;
    }

    //Insert a Unit
    public void insertUnit(int num, String name, String region, String address) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO PublishingUnits (UnitNo, Name, Region, Address) "
                + "VALUES (" + num + " , '" + name + "' , '" + region + "', '"+address+"')");
    }

    //Delete a unit through unit id
    public void deleteUnit(int id) throws SQLException{
        Statement stmt = connection.createStatement();
        String sqlStatement = "DELETE FROM PublishingUnits WHERE UnitNo = "+id;
        stmt.executeUpdate(sqlStatement);
    }

    //update unit with id identifying the correct row
    public void updateUnit(int id, String name, String address, String region) throws SQLException{
        Statement stmt = connection.createStatement();
        String sqlStatement = "UPDATE PublishingUnits "
                        +"SET Name = '"+name+"', Address = '"+address+"', Region = '"+region+"' "
                        +"WHERE UnitNo = "+id;
        stmt.executeUpdate(sqlStatement);
    }

    //finding the unit and returning a publishing unit
    public PublishingUnit findUnit(int id) throws SQLException{
        //Getting all the entire DB
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM PublishingUnits";
        ResultSet result = stmt.executeQuery(sqlStatement);
        PublishingUnit unit = null;
        //going through each row
        while(result.next()){
            int rsId = result.getInt("UnitNo");
            //if current row's unit no = id passed into method
            if(rsId == id){
                //Create a new publishing unit with the row's info
                String name = result.getString("Name");
                String address = result.getString("Address");
                String region = result.getString("Region");
                unit = new PublishingUnit(id,name,address,region);
                break;
            }
        }
        //return unit
        return unit;
    }

    //Get the entire list of units
    public ObservableList<PublishingUnit> getUnitList() throws SQLException{
        //creating a list
        ObservableList<PublishingUnit> list = FXCollections.observableArrayList();
        //getting the entire DB
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM PublishingUnits";
        ResultSet result = stmt.executeQuery(sqlStatement);

        //adding all the units
        while(result.next()){
            int id = result.getInt("UnitNo");
            String name = result.getString("Name");
            String address = result.getString("Address");
            String region = result.getString("Region");
            list.add(new PublishingUnit(id,name,address,region));
        }
        return list;
    }

    //get a list of publisher unit names used for combobox
    public ObservableList<String> getUnitNameList() throws SQLException{
        ObservableList<String> list = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM PublishingUnits";
        ResultSet result = stmt.executeQuery(sqlStatement);

        while(result.next()){
            int id = result.getInt("UnitNo");
            String region = result.getString("Region");
            String unit = id+"-"+region;
            list.add(unit);
        }
        return list;
    }

}
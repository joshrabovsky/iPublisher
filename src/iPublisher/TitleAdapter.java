package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TitleAdapter {

    //Declaring connection
    Connection connection;

    public TitleAdapter(Connection conn, Boolean reset) throws SQLException {
        //Initializing connection
        connection = conn;
        //If reset DB
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                //Drop the table and this table only. No references to titles.
                stmt.execute("DROP TABLE Titles");
            } catch (SQLException ex) {
                //
            } finally {
                // Create the table of titles
                stmt.execute("CREATE TABLE Titles ("
                        + "TitleID INT NOT NULL PRIMARY KEY, "
                        + "Name CHAR(30) NOT NULL, "
                        + "ISBN CHAR(15) NOT NULL, "
                        + "EditionNo INT NOT NULL, "
                        + "PublicationDate CHAR(30) NOT NULL, "
                        + "PublicationQTY INT NOT NULL, "
                        + "Author INT NOT NULL REFERENCES Authors (AuthorID) ON DELETE CASCADE"
                        + ")");
            }
        }
    }

    //insert a new title
    public void insertTitle(int titleID, String name, String ISBN,
                            int editionNo, String date, int qty, int authorID) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Titles (TitleID, Name, ISBN, EditionNo, PublicationDate, PublicationQTY, Author) "
                + "VALUES (" + titleID + ", '" + name + "' , '"
                + ISBN + "', "+editionNo+", '"+date+"', "+qty+", "+authorID+")");
    }

    //delete a title with the unique id passed as an argument
    public void deleteTitle(int id) throws SQLException{
        Statement stmt = connection.createStatement();
        String sqlStatement = "DELETE FROM Titles WHERE TitleID = "+id+"";
        stmt.executeUpdate(sqlStatement);
    }

    //Update the the row that has the titleID with the info
    public void updateTitle(int titleID, String name, String ISBN,
                             int editionNo, String date, int qty, int authorID) throws SQLException{
        Statement stmt = connection.createStatement();
        String sqlStatement = "UPDATE Titles "
                +" SET Name = '"+name+"', ISBN = '"+ISBN
                +"', EditionNo = "+editionNo+", PublicationDate = '"+date+"', PublicationQTY = "
                +qty+", Author = "+authorID+""
                +" WHERE titleID = "+titleID+"";
        stmt.executeUpdate(sqlStatement);
    }

    //find a return the title with the titleID
    public Title findTitle(int titleID) throws SQLException{
        //Get all titles
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Titles";
        ResultSet result = stmt.executeQuery(sqlStatement);
        Title title = null;

        while(result.next()){
            //Get the id of the current row
            int id = result.getInt("TitleID");
            //if the id passed matches the current row's id
            if(titleID == id){
                String name = result.getString("Name");
                String ISBN = result.getString("ISBN");
                int editionNo = result.getInt("EditionNo");
                String date = result.getString("PublicationDate");
                int qty = result.getInt("PublicationQTY");
                int authorID = result.getInt("Author");
                //Creating a new title with this ID, author's ID, and other info
                title = new Title(titleID,name,ISBN,editionNo,date,qty,authorID);
                break;
            }
        }
        return title;
    }

    //Return  list of all titles
    public ObservableList<Title> getTitles() throws SQLException{
        ObservableList<Title> list = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Titles";
        ResultSet result = stmt.executeQuery(sqlStatement);

        while(result.next()){
            int titleID = result.getInt("TitleID");
            String name = result.getString("Name");
            String ISBN = result.getString("ISBN");
            int editionNo = result.getInt("EditionNo");
            String date = result.getString("PublicationDate");
            int qty = result.getInt("PublicationQTY");
            int authorID = result.getInt("Author");
            list.add(new Title(titleID,name,ISBN,editionNo,date,qty,authorID));
        }
        return list;
    }

    //Return a list of title strings used for combobox. Contains the unique ID to fetch info on it.
    public ObservableList<String> getTitlesInfoList() throws SQLException{
        ObservableList<String> list = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Titles";
        ResultSet result = stmt.executeQuery(sqlStatement);

        while(result.next()){
            int titleID = result.getInt("TitleID");
            String name = result.getString("Name");
            String ISBN = result.getString("ISBN");
            String value = titleID+"-"+name+"-"+ISBN;
            list.add(value);
        }
        return list;
    }

    //Returns the current max ID + 1 so that the inserted element can have a unique key
    public int getMax() throws SQLException{
        int num = 1;
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT MAX(TitleID) from Titles";
        ResultSet rs = stmt.executeQuery(sqlStatement);
        while (rs.next()){
            num = rs.getInt(1)+1;
        }

        return num;
    }

}
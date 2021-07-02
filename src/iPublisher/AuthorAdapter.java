package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorAdapter {

    //Connection to DB
    Connection connection;

    public AuthorAdapter(Connection conn, Boolean reset) throws SQLException {
        //Hooking connection
        connection = conn;
        //Only if reset DB
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                //Drop Titles then Authors, must drop titles first as it references Authors
                //and has no references itself
                stmt.execute("DROP TABLE Titles");
                stmt.execute("DROP TABLE Authors");
            } catch (SQLException ex) {
                //
            } finally {
                //Creating the Table of Authors in DB
                stmt.execute("CREATE TABLE Authors ("
                        + "AuthorID INT NOT NULL PRIMARY KEY, "
                        + "Name CHAR(30) NOT NULL, "
                        + "Address CHAR(60) NOT NULL, "
                        + "EmailAddress CHAR(30) NOT NULL, "
                        + "PublishingUnit INT NOT NULL REFERENCES PublishingUnits (UnitNo) ON DELETE CASCADE"
                        + ")");
            }
        }
    }

    //Inserting an author into the DB
    public void insertAuthor(int authorID, String name, String address, String emailAddress, int unitNo) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Authors (AuthorID, Name, Address, EmailAddress, PublishingUnit) "
                + "VALUES (" + authorID + ", '" + name + "' , '"
                + address + "', '"+emailAddress+"', "+unitNo+")");
    }

    //Deleting an author from the DB using its unique ID
    public void deleteAuthor(int id) throws SQLException{
        Statement stmt = connection.createStatement();
        String sqlStatement = "DELETE FROM Authors WHERE AuthorId = "+id+"";
        stmt.executeUpdate(sqlStatement);
    }

    //Updating the properties of an author in the DB with the authorID as the identifier for the row
    public void updateAuthor(int authorID, String name, String address, String emailAddress, int unitID) throws SQLException{
        Statement stmt = connection.createStatement();
        String sqlStatement = "UPDATE Authors"
                +" SET Name = '"+name+"', Address = '"+address+"', EmailAddress = '"
                +emailAddress+"', PublishingUnit = "+unitID
                +" WHERE authorID = "+authorID+"";
        stmt.executeUpdate(sqlStatement);
    }

    //Find and retunr an author using the authorID
    public Author findAuthor(int authorID) throws SQLException{
        //Getting all authors
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Authors";
        ResultSet result = stmt.executeQuery(sqlStatement);
        Author author = null;
        while(result.next()){
            //getting the authorID of the current row
            int id = result.getInt("AuthorID");
            //If the ID passed = this row's id
            if(authorID == id){
                //getting the properties
                String name = result.getString("Name");
                String address = result.getString("Address");
                String emailAddress = result.getString("EmailAddress");
                //The unit ID attached is also a property for easy searching in the editing scetions
                int unitId = result.getInt("PublishingUnit");
                author = new Author(authorID,name,address,emailAddress, unitId);
                break;
            }
        }
        return author;
    }

    //Return a list of all the authors in the DB
    public ObservableList<Author> getAuthors() throws SQLException{
        ObservableList<Author> list = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Authors";
        ResultSet result = stmt.executeQuery(sqlStatement);

        while(result.next()){
            int authorID = result.getInt("AuthorID");
            String name = result.getString("Name");
            String address = result.getString("Address");
            String emailAddress = result.getString("emailAddress");
            int unitNo = result.getInt("PublishingUnit");
            list.add(new Author(authorID,name,address,emailAddress,unitNo));
        }
        return list;
    }

    //Return a list of all the authors in the DB, however in the string form (meant for ComboBox's)
    public ObservableList<String> getAuthorsInfoList() throws SQLException{
        ObservableList<String> list = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Authors";
        ResultSet result = stmt.executeQuery(sqlStatement);

        while(result.next()){
            int authorID = result.getInt("AuthorID");
            String name = result.getString("Name");
            String value = authorID+"-"+name;
            int unitID = result.getInt("PublishingUnit");
            list.add(value);
        }
        return list;
    }

    //Returns the current max ID + 1 so that the inserted element can have a unique key
    public int getMax() throws SQLException{
        int num = 1;
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT MAX(AuthorID) from Authors";
        ResultSet rs = stmt.executeQuery(sqlStatement);
        while (rs.next()){
            num = rs.getInt(1)+1;
        }

        return num;
    }

}

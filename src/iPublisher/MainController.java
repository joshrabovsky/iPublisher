package iPublisher;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author joshrabovsky
 */
public class MainController implements Initializable {

    //all the adaptors
    private AuthorAdapter authors;
    private PublishingUnitAdapter publisherUnits;
    private TitleAdapter titles;
    //connection to Database
    private Connection conn;

    //attaching the main menu
    @FXML
    private MenuBar mainMenu;

    //ONLY IN DEPTH FOR THIS ONE SINCE SAME CODE
    //Creating publishing unit page
    public void addPublishingUnit() throws Exception{
        //new publishing unit adapter
        publisherUnits = new PublishingUnitAdapter(conn, false);
        //create the FXML loader for this FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPublisherUnit.fxml"));
        //load the FXML
        Parent addPublishingUnit = (Parent) fxmlLoader.load();
        //Get the controller of the scene
        AddPublisherUnitController addPublishingUnitController = (AddPublisherUnitController) fxmlLoader.getController();
        //call the set model method
        addPublishingUnitController.setModel(publisherUnits);

        //Creating a scene with the contents of the FXML File
        Scene scene = new Scene(addPublishingUnit);
        //Creating the stage which holds the scene
        Stage stage = new Stage();
        //Adding the scene
        stage.setScene(scene);
        //Add the western logo to the title
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        //set the title
        stage.setTitle("Add New Publishing Unit");
        stage.initModality(Modality.APPLICATION_MODAL);

        //Show the stage
        stage.show();
    }

    //Creating editDeletePublishingUnit page
    public void editDeletePublishingUnit() throws Exception{
        publisherUnits = new PublishingUnitAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditDeletePublisherUnit.fxml"));

        Parent editDeletePublishingUnit = (Parent) fxmlLoader.load();
        EditDeletePublisherUnitController editDeletePublisherUnitController = (EditDeletePublisherUnitController) fxmlLoader.getController();
        editDeletePublisherUnitController.setModel(publisherUnits);

        Scene scene = new Scene(editDeletePublishingUnit);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Edit/Delete Publishing Unit");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    //Creating addAuthor page
    public void addAuthor() throws Exception{
        authors = new AuthorAdapter(conn, false);
        publisherUnits = new PublishingUnitAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAuthor.fxml"));
        Parent addAuthors = (Parent) fxmlLoader.load();
        AddAuthorController addAuthorController = (AddAuthorController) fxmlLoader.getController();
        addAuthorController.setModel(publisherUnits, authors);

        Scene scene = new Scene(addAuthors);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Add New Author");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    //Creating editDeleteAuthor page
    public void editDeleteAuthor() throws Exception{
        publisherUnits = new PublishingUnitAdapter(conn, false);
        authors = new AuthorAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditDeleteAuthor.fxml"));
        Parent editDeleteAuthor = (Parent) fxmlLoader.load();
        EditDeleteAuthorController editDeleteAuthorController = (EditDeleteAuthorController) fxmlLoader.getController();
        editDeleteAuthorController.setModel(authors,publisherUnits);

        Scene scene = new Scene(editDeleteAuthor);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Edit/Delete Author");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    //Creating addTitle page
    public void addTitle() throws Exception{
        titles = new TitleAdapter(conn, false);
        authors = new AuthorAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTitle.fxml"));
        Parent addTitle = (Parent) fxmlLoader.load();
        AddTitleController addTitleController = (AddTitleController) fxmlLoader.getController();
        addTitleController.setModel(titles, authors);

        Scene scene = new Scene(addTitle);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Add New Title");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    //Creating editDeleteTitle page
    public void editDeleteTitle() throws Exception{
        authors = new AuthorAdapter(conn, false);
        titles = new TitleAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditDeleteTitle.fxml"));
        Parent editDeleteTitle = (Parent) fxmlLoader.load();
        EditDeleteTitleController editDeleteTitleController = (EditDeleteTitleController) fxmlLoader.getController();
        editDeleteTitleController.setModel(authors, titles);

        Scene scene = new Scene(editDeleteTitle);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Edit/Delete Title");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    //Show the about page
    public void showAbout() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent About = (Parent) fxmlLoader.load();

        Scene scene = new Scene(About);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    //Exit the application
    public void exit() {

        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    //Reset the database
    @FXML
    public void resetDB() {
        try {
            // create publisher units DBl
            publisherUnits = new PublishingUnitAdapter(conn, true);
            displayAlert("Publisher Units table has been created");

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
        try {
            // create authors DB
            authors = new AuthorAdapter(conn, true);
            displayAlert("Authors table has been reset");

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
        try {
            // create titles DB
            titles = new TitleAdapter(conn, true);
            displayAlert("Titles table has been reset");

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    //display alert if error
    private void displayAlert(String msg) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }

    //initially called whe application is booted up
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:iPublisherDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }
    }

}


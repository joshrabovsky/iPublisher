package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddTitleController {

    //Connecting FXML elements
    @FXML
    ComboBox<String> authorBox;
    @FXML
    Button cancelBtn;
    @FXML
    Button saveBtn;
    @FXML
    TextField nameTF;
    @FXML
    TextField ISBNTF;
    @FXML
    TextField editionNoTF;
    @FXML
    TextField publicationDateTF;
    @FXML
    TextField publicationQTYTF;

    //attaching adapters
    private TitleAdapter titleAdapter;
    private AuthorAdapter authorAdapter;


    //Grabbing adapters from main controller
    public void setModel(TitleAdapter titleAdapter, AuthorAdapter authorAdapter){
        this.titleAdapter = titleAdapter;
        this.authorAdapter = authorAdapter;
        populateAuthorBox();
    }

    //Setting the authorBox to contain all authors
    public void populateAuthorBox(){
        try{
            authorBox.setItems(authorAdapter.getAuthorsInfoList());
        }catch(SQLException ex){
        }
    }

    //Close the stage
    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    //Save to the title DB
    @FXML
    public void save(){
        try{
            //Getting the next unique element
            int titleID = titleAdapter.getMax();
            //Gathering the TextFields
            String name = nameTF.getText();
            String ISBN = ISBNTF.getText();
            int editionNo = Integer.parseInt(editionNoTF.getText());
            String publicationDate = publicationDateTF.getText();
            int qty = Integer.parseInt(publicationQTYTF.getText());
            //Getting the authorID from the combobox
            int authorID = Integer.parseInt(authorBox.getValue().split("-")[0].trim());
            //Inserting a new title into the DB
            titleAdapter.insertTitle(titleID,name,ISBN,editionNo,publicationDate,qty,authorID);
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }catch(NumberFormatException ex){
            displayAlert("Field requires a number");
        }
        //Close stage
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    //display alert
    private void displayAlert(String s) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
            controller.setAlertText(s);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }


}

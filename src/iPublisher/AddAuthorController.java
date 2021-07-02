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

public class AddAuthorController {

    //Connect FXML items
    @FXML
    ComboBox<String> publisherUnitBox;
    @FXML
    TextField nameTF;
    @FXML
    TextField addressTF;
    @FXML
    TextField emailAddressTF;
    @FXML
    Button cancelBtn;
    @FXML
    Button saveBtn;

    //Create adapters
    private PublishingUnitAdapter unitAdapter;
    private AuthorAdapter authorAdapter;

    //creating the model by attaching adapters (allow for access of database)
    public void setModel(PublishingUnitAdapter unitAdapter, AuthorAdapter authorAdapter){
        this.unitAdapter = unitAdapter;
        this.authorAdapter = authorAdapter;
        populatePublisherBox();
    }

    //Setting the publisher combo box to allow the author to be connected to the publishing unit
    public void populatePublisherBox(){
        try{
            publisherUnitBox.setItems(unitAdapter.getUnitNameList());
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
    }

    //cancel the interaction
    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    //save the interaction to the data
    @FXML
    public void save(){
        try{
            //Retrieve the next unique ID
            int authorID = authorAdapter.getMax();
            //Retrieve TextFieldInfo
            String name = nameTF.getText();
            String address = addressTF.getText();
            String emailAddress = emailAddressTF.getText();
            //The first number is the unique ID of the publishing unit
            String unitNo = publisherUnitBox.getValue().split("-")[0];
            //convert it to an integer
            int unitNum = Integer.parseInt(unitNo.trim());
            //create an author
            authorAdapter.insertAuthor(authorID,name,address,emailAddress,unitNum);
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        //close stage
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    //Display the alert page
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

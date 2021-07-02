package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddPublisherUnitController{

    //Connect FXML elements
    @FXML
    Button cancelBtn;
    @FXML
    Button saveBtn;
    @FXML
    TextField nameTF;
    @FXML
    TextField addressTF;
    @FXML
    TextField regionTF;

    //create publishing unit adapter
    private PublishingUnitAdapter unitAdapter;

    //attach adapter
    public void setModel(PublishingUnitAdapter adapter) {unitAdapter = adapter;}

    //close the stage
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    //save the stage
    public void save(){
        try{
            //retrieve TextField elements
            String name = nameTF.getText();
            String address = addressTF.getText();
            String region = regionTF.getText();
            //Get the next unique ID
            int max = unitAdapter.getMax();
            //create a new publishing unit
            unitAdapter.insertUnit(max,name,region,address);
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        //close staf
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    //displaying alert stage
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

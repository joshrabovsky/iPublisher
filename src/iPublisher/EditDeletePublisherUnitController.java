package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class EditDeletePublisherUnitController {

    //Connecting the elements
    @FXML
    ComboBox<String> publishingUnitBox;
    @FXML
    TextField nameTF;
    @FXML
    TextField addressTF;
    @FXML
    TextField regionTF;
    @FXML
    Button clearBtn;
    @FXML
    Button updateBtn;
    @FXML
    Button deleteBtn;
    @FXML
    Button exitBtn;

    //Declaring the adapters
    private PublishingUnitAdapter publishingUnitAdapter;
    private AuthorAdapter authorAdapter;
    private TitleAdapter titleAdapter;

    //Connecting the adapters
    public void setModel(PublishingUnitAdapter publishingAdapter){
        this.publishingUnitAdapter = publishingAdapter;
        buildPublishingUnitsBox();
    }

    //Building the publishing units combobox
    public void buildPublishingUnitsBox(){
        try{
            publishingUnitBox.setItems(publishingUnitAdapter.getUnitNameList());
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
    }

    //Close the stage
    @FXML
    public void cancel(){
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    //clear the text fields
    @FXML
    public void clear(){
        nameTF.setText("");
        regionTF.setText("");
        addressTF.setText("");
    }

    //Delete the publisher
    @FXML
    public void delete(){
        //Get unique publisher ID
        String boxVal = publishingUnitBox.getValue();
        int id = Integer.parseInt(boxVal.split("-")[0].trim());
        try{
            //delete through ID
            publishingUnitAdapter.deleteUnit(id);
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        //Close stage
        Stage stage = (Stage) deleteBtn.getScene().getWindow();
        stage.close();
    }

    //Update the publishing unit
    @FXML
    public void update(){
        try{
            //Get the ID
            String boxVal = publishingUnitBox.getValue();
            int id = Integer.parseInt(boxVal.split("-")[0].trim());
            //update the publishing unit
            publishingUnitAdapter.updateUnit(id, nameTF.getText().trim(),
                    addressTF.getText().trim(), regionTF.getText().trim());
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        //close the stage
        Stage stage = (Stage) updateBtn.getScene().getWindow();
        stage.close();
    }

    //Fill the fields
    @FXML
    public void fillFields(){
        //Get the ID
        String boxVal = publishingUnitBox.getValue();
        int id = Integer.parseInt(boxVal.split("-")[0].trim());
        PublishingUnit unit = null;
        try{
            //Get the unit through the ID
            unit = publishingUnitAdapter.findUnit(id);
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        if (unit != null){
            //Setting the text fields with the unit inof
            nameTF.setText(unit.getName().trim());
            addressTF.setText(unit.getAddress().trim());
            regionTF.setText(unit.getRegion().trim());
        }else{
            displayAlert("Unit does not exist. Should not appear");
        }
    }

    //Display the alert
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


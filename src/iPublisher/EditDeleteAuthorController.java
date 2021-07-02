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

public class EditDeleteAuthorController {

    //Connecting the elements
    @FXML
    ComboBox<String> authorBox;
    @FXML
    ComboBox<String> publishingUnitBox;
    @FXML
    Button exitBtn;
    @FXML
    Button clearBtn;
    @FXML
    Button updateBtn;
    @FXML
    Button deleteBtn;
    @FXML
    TextField nameTF;
    @FXML
    TextField addressTF;
    @FXML
    TextField emailAddressTF;

    //Declaring the adapters
    private AuthorAdapter authorAdapter;
    private PublishingUnitAdapter publishingUnitAdapter;

    //A list of all units in the DB
    final ObservableList<PublishingUnit> unitData = FXCollections.observableArrayList();

    //Connecting the adapters
    public void setModel(AuthorAdapter authorAdapter, PublishingUnitAdapter publishingUnitAdapter){
        this.authorAdapter = authorAdapter;
        this.publishingUnitAdapter = publishingUnitAdapter;
        buildAuthorBox();
    }

    //Building the author box
    private void buildAuthorBox(){
        try{
            authorBox.setItems(authorAdapter.getAuthorsInfoList());
            //also setting the publishing box through the publishing adapter
            publishingUnitBox.setItems(publishingUnitAdapter.getUnitNameList());
            //also adding all publishers to unitData
            unitData.addAll(publishingUnitAdapter.getUnitList());
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

    //Clear all text Fields
    @FXML
    public void clear(){
        nameTF.setText("");
        addressTF.setText("");
        emailAddressTF.setText("");
    }

    //Update the author
    @FXML
    public void update(){
        try{
            //Getting author ID
            String boxVal = authorBox.getValue();
            int id = Integer.parseInt(boxVal.split("-")[0].trim());
            //Getting publishing unit ID
            boxVal = publishingUnitBox.getValue();
            int unitId = Integer.parseInt(boxVal.split("-")[0].trim());
            //Updating the author with values including ID's and TextFields
            authorAdapter.updateAuthor(id, nameTF.getText().trim(),
                    addressTF.getText().trim(), emailAddressTF.getText().trim(), unitId);
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        //Close the stage
        Stage stage = (Stage) updateBtn.getScene().getWindow();
        stage.close();
    }

    //Delete the author
    @FXML
    public void delete(){
        //Get author ID
        String boxVal = authorBox.getValue();
        int id = Integer.parseInt(boxVal.split("-")[0].trim());
        try{
            //Delete author through ID
            authorAdapter.deleteAuthor(id);
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        //Close the stage
        Stage stage = (Stage) deleteBtn.getScene().getWindow();
        stage.close();
    }

    //Filling the fields
    @FXML
    public void fillFields(){
        //getting the current authorID
        int authorID = Integer.parseInt(authorBox.getValue().split("-")[0].trim());
        Author author = null;
        PublishingUnit unit = null;
        try{
            //getting the author with the authorID
            author = authorAdapter.findAuthor(authorID);
            //getting the unit with the unitID
            unit = publishingUnitAdapter.findUnit(author.getAffiliatedUnitId());
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        if (author != null && unit != null){
            //Setting text fields
            nameTF.setText(author.getName().trim());
            addressTF.setText(author.getAddress().trim());
            emailAddressTF.setText(author.getEmailAddress().trim());

            //Setting publishing box with this author's publishing unit
            int unitId = unit.getId();
            String unitRegion = unit.getRegion();
            publishingUnitBox.setValue(unitId+"-"+unitRegion);

        }else{
            displayAlert("Unit does not exist. Should not appear");
        }
    }

    //Display alert
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

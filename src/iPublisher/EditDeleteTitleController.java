package iPublisher;

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

public class EditDeleteTitleController {

    //Connecting the elements
    @FXML
    ComboBox<String> titlesBox;
    @FXML
    ComboBox<String> authorBox;
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
    TextField ISBNTF;
    @FXML
    TextField editionNoTF;
    @FXML
    TextField publicationDateTF;
    @FXML
    TextField publicationQTYTF;

    //Declaring the adapters
    private TitleAdapter titleAdapter;
    private AuthorAdapter authorAdapter;

    //Attaching the adapters
    public void setModel(AuthorAdapter authorAdapter, TitleAdapter titleAdapter){
        this.authorAdapter = authorAdapter;
        this.titleAdapter = titleAdapter;
        buildComboBox();
    }

    //Building the combo boxes
    public void buildComboBox(){
        try{
            //setting teh title and author box
            titlesBox.setItems(titleAdapter.getTitlesInfoList());
            authorBox.setItems(authorAdapter.getAuthorsInfoList());
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
    }

    //close the stag
    @FXML
    public void cancel(){
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    //clear the text fields
    @FXML
    public void clear(){
        nameTF.setText("");
        ISBNTF.setText("");
        editionNoTF.setText("");
        publicationDateTF.setText("");
        publicationQTYTF.setText("");
    }

    //update the title
    @FXML
    public void update(){
        try{
            //getting the title ID
            int id = Integer.parseInt(titlesBox.getValue().split("-")[0].trim());
            //getting the author ID
            int authorId = Integer.parseInt(authorBox.getValue().split("-")[0].trim());
            //updating the title
            titleAdapter.updateTitle(
                    id,
                    nameTF.getText().trim(),
                    ISBNTF.getText().trim(),
                    Integer.parseInt(editionNoTF.getText()),
                    publicationDateTF.getText().trim(),
                    Integer.parseInt(publicationQTYTF.getText()),
                    authorId
                    );
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }catch(NumberFormatException ex){
            displayAlert("Field requires a number");
        }
        //close the stage
        Stage stage = (Stage) updateBtn.getScene().getWindow();
        stage.close();
    }

    //delete title
    @FXML
    public void delete(){
        //get title ID
        String boxVal = titlesBox.getValue();
        int id = Integer.parseInt(boxVal.split("-")[0].trim());
        try{
            //delete title through ID
            titleAdapter.deleteTitle(id);
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        //close stage
        Stage stage = (Stage) deleteBtn.getScene().getWindow();
        stage.close();
    }

    //fill the fields
    @FXML
    public void fillFields(){
        //get title ID
        int titleID = Integer.parseInt(titlesBox.getValue().split("-")[0].trim());
        Title title = null;
        Author author = null;
        try{
            //get title through id
            title = titleAdapter.findTitle(titleID);
            //get author through title
            author = authorAdapter.findAuthor(title.getAuthorID());
        }catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        if (title != null && author != null){
            //setting the fields
            nameTF.setText(title.getName().trim());
            ISBNTF.setText(title.getISBN().trim());
            editionNoTF.setText(title.getEditionNo()+"");
            publicationDateTF.setText(title.getPublicationDate());
            publicationQTYTF.setText(title.getPublicationQTY()+"");
            //setting the author combobox
            authorBox.setValue(author.getAuthorID()+"-"+author.getName());
        }else{
            displayAlert("Unit does not exist. Should not appear");
        }
    }

    //display alert if error
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iPublisher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joshrabovsky
 */
public class AboutController  {

    //Exit page button
    @FXML
    Button okBtn;

    //exit
    public void exit() {
        Stage stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
    }

}

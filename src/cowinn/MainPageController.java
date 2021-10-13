/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cowinn;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author dhruvpatel
 */
public class MainPageController implements Initializable {

    static Stage stg1;
    
    @FXML
    private Button adminBTN;
    
    @FXML
    private Button userBTN;

    @FXML
    void adminLoginFunc(ActionEvent event) {
        try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signinup.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            this.stg1 = stage;
            stage.setScene(new Scene(root));  
            stage.show();
            //
            SigninupController hp1 = fxmlLoader.getController();
            hp1.hideUser();
            //
            Cowinn.stg.close();
    } catch(Exception e) {
       e.printStackTrace();
      }

    }
    
    @FXML
    void btn1(ActionEvent event) throws Exception {
        try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signinup.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            MainPageController.stg1 = stage;
            stage.setScene(new Scene(root));  
            stage.show();
            Cowinn.stg.close();
    } catch(Exception e) {
       e.printStackTrace();
      }
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    @FXML
    void defaultA(MouseEvent event) {
        adminBTN.setStyle("-fx-background-color: #f5b437");

    }

    @FXML
    void defaultR(MouseEvent event) {
         userBTN.setStyle("-fx-background-color: #f5b437");

    }

    @FXML
    void glowA(MouseEvent event) {
        adminBTN.setStyle("-fx-background-color: #bf9036");

    }

    @FXML
    void glowR(MouseEvent event) {
        userBTN.setStyle("-fx-background-color: #bf9036");
    }

    private ObjectProperty<Background> color(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

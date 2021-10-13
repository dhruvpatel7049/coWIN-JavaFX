/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cowinn;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dhruvpatel
 */
public class AdminHomePageController implements Initializable {
    
    String id;
    String name;
    static Stage stg3;
    
    @FXML
    private Label Label_name;

    @FXML
    private Pane profilePane;

    @FXML
    private Button profileBTN;

    @FXML
    private Button logoutBTN;

    @FXML
    private Pane dashboardPane;

    @FXML
    private Label TRLabel;

    @FXML
    private Label d1Label;

    @FXML
    private Label d2Label;

    @FXML
    private ProgressIndicator Progress1;

    @FXML
    private Label dose1Label;

    @FXML
    private ProgressIndicator Progress2;

    @FXML
    private Label dose2Label;

    @FXML
    private ProgressIndicator Progress4;

    @FXML
    private Label vacLabel;

    @FXML
    private ProgressIndicator Progress3;

    @FXML
    private Label genLabel;

    @FXML
    void changeBlackL(MouseEvent event) {
        logoutBTN.setTextFill(Color.web("#000000"));//default : black

    }

    @FXML
    void changeBlackP(MouseEvent event) {
        profileBTN.setTextFill(Color.web("#000000"));//default : black

    }

    @FXML
    void changeBlueL(MouseEvent event) {
        logoutBTN.setTextFill(Color.web("#277be8"));//onClick : blue

    }

    @FXML
    void changeBlueP(MouseEvent event) {
        profileBTN.setTextFill(Color.web("#277be8"));//onClick : blue

    }

    @FXML
    void logout(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            HomePageController.stg3 = stage;
            stage.setScene(new Scene(root));  
            stage.show();
            SigninupController.stg2.close();
        }
        catch(Exception e) {
            e.printStackTrace();}

    }

    @FXML
    void quitProfile(MouseEvent event) {
        profilePane.setVisible(false);

    }

    @FXML
    void viewProfile(MouseEvent event) {
        Label l = Label_name;
        l.setOnMousePressed(evt -> {
        if (evt.isPrimaryButtonDown()) {
            Label_name.setTextFill(Color.web("#277be8"));//onClick : blue
        }
        });
        l.setOnMouseReleased(evt -> {
        Label_name.setTextFill(Color.web("#000000"));//default : black
        
        if(profilePane.isVisible())
            profilePane.setVisible(false);
        else
            profilePane.setVisible(true);
        });

    }

    @FXML
    void viewUserDetails(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }


    
    public void session(String id1,String name1){
        
        name=name1;
        id=id1;
        Label_name.setText("Hello, "+name);
        
        //   DASHBOARD
        //-----------------
        //-----------------
        
        int r = 0;
        int d1 = 0;
        int d2 = 0;
        int nM = 0;
        int nF = 0;
        int nCovaxin = 0;
        int nCovishield = 0;
        
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        Statement stmt;
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cowin","root","");
                stmt = con.createStatement();
                
                
                rs = stmt.executeQuery ("SELECT COUNT(name) FROM login;");
                
                while (rs.next()){
                    r=rs.getInt(1);
                    
                }
                
                rs = stmt.executeQuery ("SELECT COUNT(name) FROM login WHERE status = \"R\" OR status = \"V\";");
                while (rs.next()){
                    d1=rs.getInt(1);
                }
                rs = stmt.executeQuery ("SELECT COUNT(name) FROM login WHERE status = \"V\";");
                while (rs.next()){
                    d2=rs.getInt(1);
                }
                
                rs = stmt.executeQuery ("SELECT COUNT(name) FROM login WHERE gender = \"M\";");
                while (rs.next()){
                    nM=rs.getInt(1);
                }
                rs = stmt.executeQuery ("SELECT COUNT(name) FROM login WHERE gender = \"F\";");
                while (rs.next()){
                    nF=rs.getInt(1);
                }
                rs = stmt.executeQuery ("SELECT COUNT(id) FROM vaccination WHERE vaccine = \"covaxin\";");
                while (rs.next()){
                    nCovaxin=rs.getInt(1);
                }
                rs = stmt.executeQuery ("SELECT COUNT(id) FROM vaccination WHERE vaccine = \"covishield\";");
                while (rs.next()){
                    nCovishield=rs.getInt(1);
                }
        }
            catch(SQLException e){
                System.out.println(e);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(VaccinationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        Float p1 = (float)d1/r;
        Float p2 = (float)d2/r;
        
        Progress1.setProgress(p1);
        Progress2.setProgress(p2);
        
        dose1Label.setText(""+(int)(Math.round(p1*100))+"%");
        dose2Label.setText(""+(int)(Math.round(p2*100))+"%");
        
        TRLabel.setText(""+r);
        d1Label.setText(""+d1);
        d2Label.setText(""+d2);
        
        Float p3 = (float)nM/(nM + nF);
        Float p4 = (float)nCovaxin/(nCovaxin + nCovishield);
        Progress3.setProgress(p3);
        Progress4.setProgress(p4);
        //done;
        
        
    }

    
    
}

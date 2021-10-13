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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author dhruvpatel
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField tf1;
    @FXML
    private PasswordField pf1;
    @FXML
    private Button inBTN;
    @FXML
    private TextField tf3;
    @FXML
    private PasswordField pf2;
    @FXML
    private Button upBTN;
    @FXML
    private TextField tf2;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
     
    
    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
            
    @FXML
    void login(ActionEvent event){
        String pno = tf1.getText();
        String pass = pf1.getText();
        
        if(pno.equals("") && pass.equals(""))
        {
            infoBox("Phone No. or Password not found", null, "ERROR");
        }
        else
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cowin","root","");
                pst = con.prepareStatement("Select * from login where mobno=? and password=?");
                pst.setString(1,pno);
                pst.setString(2,pass);
                
                rs = pst.executeQuery();
                if(rs.next())
                {
                    infoBox("Successfully Logged in", null, "Success");
                }
                else
                {
                    infoBox("Login Failed", null, "ERROR");
                    tf1.setText("");
                    pf1.setText("");
                    tf1.requestFocus();
                }
                    
                    
                
                
            }
            catch(SQLException | ClassNotFoundException e){
                System.out.println(e);
            }
            
        }

    }

    @FXML
    void signup(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cowinn;

import static cowinn.SigninupController.infoBox;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author dhruvpatel
 */
public class VaccinationPageController implements Initializable {
    
    static Stage stg4;
    String id;
    String name;
    String mob;
    String vacc;
    String status1;

    @FXML
    private Label Label_name;

    @FXML
    private Pane profilePane;

    @FXML
    private Button profileBTN;

    @FXML
    private Button logoutBTN;
    
    @FXML
    private Pane RegisterPane;

    @FXML
    private DatePicker dobDate;

    @FXML
    private TextField nameLabel;

    @FXML
    private RadioButton covaxinRB;

    @FXML
    private ToggleGroup vaccine;

    @FXML
    private RadioButton covishieldRB;

    @FXML
    private RadioButton maleRB;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton femaleRB;

    @FXML
    private RadioButton otherRB;

    @FXML
    private TextField phoneLabel;

    @FXML
    private TextField addressLabel;

    @FXML
    private DatePicker vaccineDate;

    @FXML
    private Button registerBTN;
    
    @FXML
    private Pane secDosePane;

    @FXML
    private TextField nameLabel1;

    @FXML
    private TextField vaccineLabel1;

    @FXML
    private RadioButton covaxinRB1;

    @FXML
    private ToggleGroup vaccine1;
    
    @FXML
    private Label vaccLabel;


    @FXML
    private RadioButton covishieldRB1;

    @FXML
    private TextField phoneLabel1;

    @FXML
    private DatePicker vaccineDate1;

    @FXML
    private Button RegisterBTN1;

    @FXML
    void default1(MouseEvent event) {
        registerBTN.setStyle("-fx-background-color: #6abf34");

    }

    @FXML
    void default2(MouseEvent event) {
        RegisterBTN1.setStyle("-fx-background-color:  #6abf34");

    }

    @FXML
    void glow1(MouseEvent event) {
        registerBTN.setStyle("-fx-background-color: #568a36");
    }

    @FXML
    void glow2(MouseEvent event) {
        RegisterBTN1.setStyle("-fx-background-color:  #568a36");

    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
     
    
    

    @FXML
    void registerFUNC(ActionEvent event) {
        /*
        Button r = registerBTN;
        r.setOnMousePressed(evt -> {
        if (evt.isPrimaryButtonDown()) {
            registerBTN.setStyle("-fx-background-color: #bf9036");
        }
        });
        r.setOnMouseReleased(evt -> {
        registerBTN.setStyle("-fx-background-color: #f5b437");
        });
                
        */
        
        Statement stmt;
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cowin","root","");
                stmt = con.createStatement();
                
            
                rs = stmt.executeQuery ("SELECT mobno FROM `login` WHERE id ="+id+" ");
                while (rs.next())
                    mob =rs.getString(1);
                
            }
            catch(SQLException e){
                System.out.println(e);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(VaccinationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            nameLabel.setText(name);
            phoneLabel.setText(mob);
            
            dobDate.setPromptText("date of birth");
            vaccineDate.setPromptText("Vaccine Type");
        
        
        String gen="M";
        if(femaleRB.isSelected())
            gen="F";
        else if(otherRB.isSelected())
            gen="O";
        
        String vac="";
        if(covaxinRB.isSelected())
            vac="covaxin";
        else if(covishieldRB.isSelected())
            vac="covishield";
        
        
        
        try {
            
             pst = con.prepareStatement("insert into vaccination (id,name,mobno,vaccine,dose1) values (?,?,?,?,?)");
             pst.setString(1,id);
             pst.setString(2,name);
             pst.setString(3,mob);
             pst.setString(4,vac);
             pst.setString(5,((TextField)vaccineDate.getEditor()).getText());
             pst.execute();
             
             
             
             
             pst = con.prepareStatement("UPDATE login SET address =?,gender=?,dob=? WHERE id="+id+"");
             
             pst.setString(1,addressLabel.getText());
             pst.setString(2,gen);
             pst.setString(3,((TextField)dobDate.getEditor()).getText());
             pst.execute();
             
             infoBox("Registered for First Dose", null, "Message");
             pst = con.prepareStatement("UPDATE login SET status='R' WHERE id="+id+"");
             pst.execute();
             gotoHome();
             
//gotoHome(pno);
                
            }
         catch(SQLException e){
             
             System.out.println(e);
             
         }
    }
    
    @FXML
    void register2FUNC(ActionEvent event) {
        
        
        
        Statement stmt;
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cowin","root","");
                stmt = con.createStatement();
                
            
                rs = stmt.executeQuery ("SELECT mobno FROM `login` WHERE id ="+id+" ");
                while (rs.next())
                    mob =rs.getString(1);
                
                rs = stmt.executeQuery ("SELECT vaccine FROM vaccination WHERE id ="+id+" ");
                while (rs.next())
                    vacc =rs.getString(1);
                
            }
            catch(SQLException e){
                System.out.println(e);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(VaccinationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
        
        
        
        
        
        try {
            pst = con.prepareStatement("UPDATE vaccination SET dose2 =? WHERE id="+id+"");
            pst.setString(1,((TextField)vaccineDate1.getEditor()).getText());
            pst.execute();
            
            infoBox("Registered for Second Dose", null, "Message");
            pst = con.prepareStatement("UPDATE login SET status='V' WHERE id="+id+"");
            pst.execute();
            gotoHome();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
            
        
        

    
        

    


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
            VaccinationPageController.stg4 = stage;
            stage.setScene(new Scene(root));  
            stage.show();
            HomePageController.stg3.close();
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    void gotoHome(MouseEvent event) {
        gotoHome();

    }
    
    public void gotoHome()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            SigninupController.stg2 = stage;
            stage.setScene(new Scene(root));  
            stage.show();
            
            HomePageController hp = fxmlLoader.getController();
            hp.session(id,name);
            HomePageController.stg3.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

public void session(String id1,String name1){
        
        Label_name.setText("Hello, "+name1);
        id=id1;
        name = name1;
        nameLabel.setText(name);
        nameLabel.setText(name);
        
        Statement stmt;
        
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cowin","root","");
                stmt = con.createStatement();
                
                rs = stmt.executeQuery ("SELECT status FROM `login` WHERE id ="+id+" ");
                while (rs.next())
                    status1 =rs.getString(1);
            
                rs = stmt.executeQuery ("SELECT mobno FROM `login` WHERE id ="+id+" ");
                while (rs.next())
                    mob =rs.getString(1);
                
                rs = stmt.executeQuery ("SELECT vaccine FROM vaccination WHERE id ="+id+" ");
                while (rs.next())
                    vacc =rs.getString(1);
                
            }
            catch(SQLException e){
                System.out.println(e);
            } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
            
            if(status1.equals("NR"))
            {
                RegisterPane.setVisible(true);
                infoBox("Register your First Dose", null, "Vaccine Registration");
            }
            else if(status1.equals("R"))
            {
                secDosePane.setVisible(true);
                if(vacc.equals("covaxin"))
           {
               covaxinRB1.setSelected(true);
               
           }
           else if(vacc.equals("covishield"))
           {
               covishieldRB1.setSelected(true);
               
           }
                infoBox("Register your Second Dose", null, "Vaccine Registration");
            }
            else
            {
                infoBox("Already Vaccinated", null, "Message");
                gotoHome();
                
            }
            
            nameLabel.setText(name);
            phoneLabel.setText(mob);
            nameLabel1.setText(name);
            phoneLabel1.setText(mob);
            
            
           
    }    
    
}

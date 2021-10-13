package cowinn;

import static cowinn.FXMLDocumentController.infoBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SigninupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button adminLoginBTN;

    @FXML
    private Button userLoginBTN;

    @FXML
    private Pane adminPane;
    
    @FXML
    private TextField TF31;

    @FXML
    private PasswordField TF32;

    @FXML
    private Pane userPane;

    @FXML
    private TextField TF11;

    @FXML
    private PasswordField TF12;

    @FXML
    private TextField TF22;

    @FXML
    private PasswordField TF23;

    @FXML
    private TextField TF21;
    
    

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
    
    static Stage stg2;
    
    @FXML
    void loginAdminFunc(ActionEvent event) {
        
        String username = TF31.getText();
        String pass = TF32.getText();
        
        if(username.equals("") && pass.equals(""))
        {
            infoBox("Username or Password not found", null, "Message");
        }
        else
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cowin","root","");
                Statement stmt=con.createStatement();
                pst = con.prepareStatement("Select * from Admin where username=? and password=?");
                pst.setString(1,username);
                pst.setString(2,pass);
                
                rs = pst.executeQuery();
                if(rs.next())
                {
                    infoBox("Logged in Successfully", null, "Success");
                    gotoAdminHome(username);
                    
                }
                
                else
                {
                    infoBox("Login Failed", null, "Message");
                    TF31.setText("");
                    TF32.setText("");
                }
                    
                    
                
                
            }
            catch(SQLException | ClassNotFoundException e){
                System.out.println(e);
            }
            
        }

    }
    
    
    @FXML
    void signUpFunc(ActionEvent event) {
        String name = TF21.getText();
        String pno = TF22.getText();
        String pass = TF23.getText();
        
        if(name.equals("") && pno.equals("") && pass.equals(""))
        {
            infoBox("Please enter all the details", null, "Message");
        }
        else
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cowin","root","");
                pst = con.prepareStatement("insert into login (name,mobno,password,status) values (?,?,?,?)");
                pst.setString(1,name);
                pst.setString(2,pno);
                pst.setString(3,pass);
                pst.setString(4,"NR");
                pst.execute();
                
                infoBox("Logged in Successfully", null, "Success");
                
                gotoHome(pno);
                
            }
            catch(SQLException | ClassNotFoundException e){
                System.out.println(e);
            }
            
            
        }

    }
    
    
    @FXML
    void signinFunc(ActionEvent event) {
        String pno = TF11.getText();
        String pass = TF12.getText();
        
        if(pno.equals("") && pass.equals(""))
        {
            infoBox("Phone No. or Password not found", null, "Message");
        }
        else
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cowin","root","");
                Statement stmt=con.createStatement();
                pst = con.prepareStatement("Select * from login where mobno=? and password=?");
                pst.setString(1,pno);
                pst.setString(2,pass);
                
                rs = pst.executeQuery();
                if(rs.next())
                {
                    infoBox("Logged in Successfully", null, "Success");
                    gotoHome(pno);
                    
                }
                
                else
                {
                    infoBox("Login Failed", null, "Message");
                    TF11.setText("");
                    TF12.setText("");
                    TF11.requestFocus();
                }
                    
                    
                
                
            }
            catch(SQLException | ClassNotFoundException e){
                System.out.println(e);
            }
            
        }

    }
    
    public void gotoHome(String pno){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cowin","root","");
                Statement stmt=con.createStatement();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        SigninupController.stg2 = stage;
                        stage.setScene(new Scene(root));  
                        stage.show();
                        MainPageController.stg1.close();
                        
                        HomePageController hp = fxmlLoader.getController();
                        
                        rs = stmt.executeQuery ("SELECT id,name FROM `login` WHERE mobno = "+pno+"");
                        while (rs.next())
                            hp.session(rs.getString (1), rs.getString (2));
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
        
    }
    
    @FXML
    void backToHome(MouseEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            SigninupController.stg2 = stage;
            stage.setScene(new Scene(root));  
            stage.show();
            MainPageController.stg1.close();
        }
        catch(Exception e) {
            e.printStackTrace();}

    }
    
    public void gotoAdminHome(String username){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cowin","root","");
                Statement stmt=con.createStatement();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminHomePage.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        SigninupController.stg2 = stage;
                        stage.setScene(new Scene(root));  
                        stage.show();
                        MainPageController.stg1.close();
                        
                        AdminHomePageController ahp = fxmlLoader.getController();
                        
                        rs = stmt.executeQuery ("SELECT id,name FROM Admin WHERE username = '"+username+"'");
                        while (rs.next())
                            ahp.session(rs.getString (1), rs.getString (2));
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
        
    }


    @FXML
    void initialize() {
        assert TF11 != null : "fx:id=\"TF11\" was not injected: check your FXML file 'Signinup.fxml'.";
        assert TF12 != null : "fx:id=\"TF12\" was not injected: check your FXML file 'Signinup.fxml'.";
        assert TF22 != null : "fx:id=\"TF22\" was not injected: check your FXML file 'Signinup.fxml'.";
        assert TF23 != null : "fx:id=\"TF23\" was not injected: check your FXML file 'Signinup.fxml'.";
        assert TF21 != null : "fx:id=\"TF21\" was not injected: check your FXML file 'Signinup.fxml'.";

    }
    
    public void hideUser(){
        
        userPane.setVisible(false);
        adminLoginBTN.setVisible(false);
        
        adminPane.setVisible(true);
        userLoginBTN.setVisible(true);
    }
    public void hideAdmin(){
        
        userPane.setVisible(true);
        adminLoginBTN.setVisible(true);
        
        adminPane.setVisible(false);
        userLoginBTN.setVisible(false);
        
        
        
        
        
    }
}

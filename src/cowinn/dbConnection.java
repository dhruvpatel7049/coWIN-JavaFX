/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cowinn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dhruvpatel
 */


public class dbConnection {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement stmt;

    public dbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cowin","root","");
            Statement stmt=con.createStatement();
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
}

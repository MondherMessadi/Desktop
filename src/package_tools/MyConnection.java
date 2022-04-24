/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_tools;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.sql.Connection;



/**
 *
 * @author Mondher
 */
public class MyConnection {
public static MyConnection instance;
public String url="jbdc:mysql://localhost:3306/forsa";
public String login="root";
public String pwd="";
Connection cnx;

    public MyConnection() { 
     /*  try {
           // Class.forName("com.mysql.jdbc.Driver");        
            System.err.println("testtest");
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage()+"test");
        } */
     
    try {       
      cnx=  DriverManager.getConnection("jdbc:mysql://localhost:3306/forsa?"+"user=root");   
      System.out.println("connexion etablie");
    } catch (SQLException ex) {
       System.err.println(ex.getMessage());       
    }
  
    }

    public Connection getCnx(){
         return cnx;}
  public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }

}


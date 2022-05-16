/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package package_services;

/**
 *
 *
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import package_tools.MyConnection;
import package_entities.Reclamation;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import package_entities.severity;


public class CRUD_Severity {
    
    ///////////////////////////////ajout severity methode 1
public void ajouterSeverity(severity s){
     try {
        String requete2 =
                "INSERT INTO severity_C (name)"
                + "VALUES(?)";
        //dynamic values ?,?
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
        pst.setString(1,s.getName());
  
        pst.executeUpdate();
         System.out.println("Severity ajoutée methode 2");
    } catch (SQLException ex) {
   System.out.println(ex.getMessage());
    }
}
///////////////////////////////////////////
public ObservableList<severity> afficherSeverity(){
    //List<Reclamation> myList = new ArrayList<>();
     ObservableList<severity>myList=FXCollections.observableArrayList();
    try { 
        String requete = 
            "SELECT * FROM severity_C";
        Statement st= new MyConnection().getCnx().createStatement();
      ResultSet rs =  st.executeQuery(requete);
      while(rs.next())
      {
          severity S = new severity();
          S.setId(rs.getInt("id"));
          S.setName(rs.getString("name"));
     
          myList.add(S);
         
      }
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }
 return myList;
}
/////////////////////////////delete Severity/////////////////////////////////
public void deleteSeverity(severity S){
   
    
    try {  
        String requete = 
        "DELETE from severity_C where id="+S.getId();
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
         
        //pst.setInt(1,R.getId());
        Statement st= new MyConnection().getCnx().createStatement();
        st.executeUpdate(requete);
        //execute update for "insert" "update" or "delete requete
        //or executequery for "select" requete
       // System.out.println("deleting reclamation id :"+ R.getId()+" "+requete);
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }

}

}

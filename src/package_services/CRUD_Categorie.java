/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_services;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import package_tools.MyConnection;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import package_entities.Categorie;


/**
 *
 * @author Mondher
 */
public class CRUD_Categorie {
    
    public void ajouterCategorie(){
    String requete = 
            "INSERT INTO categorie (Name)"
            + "VALUES('test1',1)";
    
    try {
        Statement st= new MyConnection().getCnx().createStatement();
        st.executeUpdate(requete);
        //execute update for "insert" "update" or "delete requete
        //or executequery for "select" requete
        System.out.println("Categorie ajoutée methode 1");
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }

}


///////////////////////////////ajout reclamation methode 2
public void ajouterCategorie2(Categorie c){
    try {
        String requete2 =
                "INSERT INTO categorie (Name)"
                + "VALUES(?)";
        //dynamic values ?,?
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
        pst.setString(1,c.getName());
        pst.executeUpdate();
         System.out.println("Categorie ajoutée ");
    } catch (SQLException ex) {
   System.out.println(ex.getMessage());
    }
}


///////////////////////////////////////////////afficher///////////////////
public ObservableList<Categorie> afficherCategorie(){
    //List<Reclamation> myList = new ArrayList<>();
     ObservableList<Categorie>myList=FXCollections.observableArrayList();
    try { 
        String requete = 
            "SELECT * FROM categorie";
        Statement st= new MyConnection().getCnx().createStatement();
      ResultSet rs =  st.executeQuery(requete);
      while(rs.next())
      {
          Categorie C = new Categorie();
          C.setId(rs.getInt("id"));
          C.setName(rs.getString("Name"));
          
          myList.add(C);
      }
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }
 return myList;
}
/////////////////////////////delete reclamation/////////////////////////////////
public void deleteCategorie(Categorie C){
   
    
    try {  
        String requete = 
        "DELETE from categorie where id="+C.getId();
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
public void updateCategorie(Categorie C){ 
    try {  
        System.out.println("updating categorie  : "+C);
        String requete = 
                "UPDATE categorie SET Name = '"+C.getName()+"' where id="+C.getId();
         System.out.println("updating categrie  : "+requete);
         PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        
        //pst.setInt(1,R.getId());
        Statement st= new MyConnection().getCnx().createStatement();
        st.executeUpdate(requete);
        //execute update for "insert" "update" or "delete requete
        //or executequery for "select" requete
        
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }

}
    
}

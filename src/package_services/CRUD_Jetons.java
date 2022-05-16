/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package package_services;

/**
 *
 * @author mahdi
 */



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
import package_entities.Jetons;


public class CRUD_Jetons {
    
    
///////////////////////////////ajout reclamation methode 1
public void ajouterJeton(){
    String requete = 
            "INSERT INTO jetons (Name,Description,Price,clics,category)"
            + "VALUES('test1','test2','test3','test4',4)";
    
    try {
        Statement st= new MyConnection().getCnx().createStatement();
        st.executeUpdate(requete);
        //execute update for "insert" "update" or "delete requete
        //or executequery for "select" requete
        System.out.println("jeton ajoutée methode 1");
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }

}


///////////////////////////////ajout reclamation methode 2
public void ajouterJeton2(Jetons j){
    try {
        String requete2 =
                "INSERT INTO jetons (Name,Description,Price,clics,category)"
                + "VALUES(?,?,?,?,?)";
        //dynamic values ?,?
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
        pst.setString(1,j.getName());
        pst.setString(2,j.getDescription());
        pst.setString(3,j.getPrice());
        pst.setString(4,j.getClics());
        pst.setInt(5,j.getCategory());
        pst.executeUpdate();
         System.out.println("Jeton ajoutée ");
    } catch (SQLException ex) {
   System.out.println(ex.getMessage());
    }
}


///////////////////////////////////////////////afficher///////////////////
public ObservableList<Jetons> afficherJeton(){
    //List<Reclamation> myList = new ArrayList<>();
     ObservableList<Jetons>myList=FXCollections.observableArrayList();
    try { 
        String requete = 
            "SELECT * FROM jetons";
        Statement st= new MyConnection().getCnx().createStatement();
      ResultSet rs =  st.executeQuery(requete);
      while(rs.next())
      {
          Jetons R = new Jetons();
          R.setId(rs.getInt("id"));
          R.setName(rs.getString("Name"));
          R.setDescription(rs.getString("Description"));
          R.setPrice(rs.getString("Price"));
          R.setClics(rs.getString("clics"));
          R.setCategory(rs.getInt("category"));
          myList.add(R);
      }
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }
 return myList;
}
///////////////////////////////////////////////afficher///////////////////
public ObservableList<Jetons> afficherJetontriee(){
    //List<Reclamation> myList = new ArrayList<>();
     ObservableList<Jetons>myList=FXCollections.observableArrayList();
    try { 
        String requete = 
            "SELECT * FROM jetons ORDER BY name DESC;";
        Statement st= new MyConnection().getCnx().createStatement();
      ResultSet rs =  st.executeQuery(requete);
      while(rs.next())
      {
          Jetons R = new Jetons();
          R.setId(rs.getInt("id"));
          R.setName(rs.getString("Name"));
          R.setDescription(rs.getString("Description"));
          R.setPrice(rs.getString("Price"));
          R.setClics(rs.getString("clics"));
          R.setCategory(rs.getInt("category"));
          myList.add(R);
      }
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }
 return myList;
}
/////////////////////////////delete reclamation/////////////////////////////////
public void deleteJeton(Jetons R){
   
    
    try {  
        String requete = 
        "DELETE from jetons where id="+R.getId();
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
public void updateJeton(Jetons R){ 
    try {  
        System.out.println("updating jeton  : "+R);
        String requete = 
                "UPDATE jetons SET Name = '"+R.getName()+"', Description= '"+R.getDescription()+"', Price='"+R.getPrice()+"',clics='"+R.getClics()+"',category='"+R.getCategory()+"' where id="+R.getId();
         System.out.println("updating jeton  : "+requete);
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
//////////////////////pie chart//////////////////
public Map<String, Integer> piechart1(){

    Map<String, Integer> stats = new HashMap<>();
    try { 
        String requete = 
            "SELECT COUNT(id) FROM jetons where category=1";
        Statement st= new MyConnection().getCnx().createStatement();
      ResultSet rs =  st.executeQuery(requete);
      while(rs.next())
      {
       
          Integer x=rs.getInt(1);
          stats.put("category 1",x);
       //  System.out.println("results"+x);
        // System.out.println("results.2"+Integer.toString(x));
         
      }
      //
       String requete2 = 
            "SELECT COUNT(id) FROM jetons where category=2";
        Statement st2= new MyConnection().getCnx().createStatement();
      ResultSet rs2 =  st2.executeQuery(requete2);
      while(rs2.next())
      {
        
          Integer x2=rs2.getInt(1);
           stats.put("category 2",x2);
    
         
      }
      //
       String requete3 = 
            "SELECT COUNT(id) FROM jetons where category=3";
        Statement st3= new MyConnection().getCnx().createStatement();
      ResultSet rs3 =  st.executeQuery(requete3);
      while(rs3.next())
      {  
          Integer x3=rs3.getInt(1);
           stats.put("category 3",x3);
       //  System.out.println("results3"+x);
      //   System.out.println("results3.2"+Integer.toString(x));
      }
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }
 return stats;
}

}

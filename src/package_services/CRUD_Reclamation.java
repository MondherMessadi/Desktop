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
import package_entities.Reclamation;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import package_entities.severity;


public class CRUD_Reclamation {
    
    
///////////////////////////////ajout reclamation methode 1
public void ajouterRec(){
    String requete = 
            "INSERT INTO reclamation (phone_number,Email,Description,severity_id,LastMod)"
            + "VALUES('test1','test2','test3','test4','test5')";
    
    try {
        Statement st= new MyConnection().getCnx().createStatement();
        st.executeUpdate(requete);
        //execute update for "insert" "update" or "delete requete
        //or executequery for "select" requete
        System.out.println("reclamation ajoutée methode 1");
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }

}


///////////////////////////////ajout reclamation methode 2
public void ajouterRec2(Reclamation R){
    
    try {
        String requete2 =
                "INSERT INTO reclamation (phone_number,Email,Description,etat,severity_id,LastMod)"
                + "VALUES(?,?,?,0,?,?)";
        //dynamic values ?,?
        
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
        pst.setString(1,R.getPhone_number());
        pst.setString(2,R.getEmail());
        pst.setString(3,R.getDescription());
        pst.setInt(4,R.getSeverity().getId());
        pst.setString(5,R.getLastMod());
        pst.executeUpdate();
         System.out.println("reclamation ajoutée methode 2");
    } catch (SQLException ex) {
   System.out.println(ex.getMessage());
    }
}


///////////////////////////////////////////////afficher///////////////////
public ObservableList<Reclamation> afficherReclamation(){
    //List<Reclamation> myList = new ArrayList<>();
     ObservableList<Reclamation>myList=FXCollections.observableArrayList();
    try { 
          
        String requete = 
            "SELECT * FROM Reclamation r,severity_C s where s.id=r.severity_id";
        Statement st= new MyConnection().getCnx().createStatement();
      ResultSet rs =  st.executeQuery(requete);
      while(rs.next())
      {
             severity sev = new severity(rs.getInt("s.id"),rs.getString("s.name"));
             
      
          Reclamation R = new Reclamation();
          R.setId(rs.getInt("id"));
          R.setEmail(rs.getString("Email"));
          R.setDescription(rs.getString("Description"));
          R.setPhone_number(rs.getString("phone_number"));
          R.setEtat(rs.getInt("etat"));
          R.setSeverity(sev);
          R.setLastMod(rs.getString("LastMod"));
          myList.add(R);
         
      }
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }
       System.out.println("rec list "+myList);
 return myList;
}
/////////////////////////////delete reclamation/////////////////////////////////
public void deleteRec(Reclamation R){
   
    
    try {  
        String requete = 
        "DELETE from Reclamation where id="+R.getId();
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

/////////////////////////////send email///////////

public void sendEmail(Integer Reclamationid){
   
    
    try {  
        String requete = 
                     "UPDATE Reclamation SET etat = '"+1+"' where id="+Reclamationid;

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
///////////////////////////////////////////////search by ///////////////////
public ObservableList<Reclamation> Searchby(String Search){
    //List<Reclamation> myList = new ArrayList<>();
     ObservableList<Reclamation>myList=FXCollections.observableArrayList();
    try { 
      
          String requete = 
            "SELECT * FROM Reclamation r,severity_C s where s.id=r.severity_id  And (r.Email ="+Search+" OR r.phone_number ="+Search+" OR r.Description ="+Search+" )";
        System.out.println("requete"+requete);
        Statement st= new MyConnection().getCnx().createStatement();
      ResultSet rs =  st.executeQuery(requete);
      while(rs.next())
      {
          severity c = new severity(rs.getInt("s.id"),rs.getString("s.name"));
          
          Reclamation R = new Reclamation();
          R.setId(rs.getInt("id"));
          R.setEmail(rs.getString("Email"));
          R.setDescription(rs.getString("Description"));
          R.setPhone_number(rs.getString("phone_number"));
          R.setEtat(rs.getInt("etat"));
          R.setSeverity(c);
          R.setLastMod(rs.getString("LastMod"));
          myList.add(R);
         
      }
      
        
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }
 return myList;
}

//////////////////////pie chart//////////////////
public Map<String, Integer> piechart1(){

    Map<String, Integer> stats = new HashMap<>();
    try { 
        String requete = 
            "SELECT COUNT(severity_id) FROM Reclamation where severity_id=1";
        Statement st= new MyConnection().getCnx().createStatement();
      ResultSet rs =  st.executeQuery(requete);
      while(rs.next())
      {
       
          Integer x=rs.getInt(1);
          stats.put("Normal",x);
       //  System.out.println("results"+x);
        // System.out.println("results.2"+Integer.toString(x));
         
      }
      //
       String requete2 = 
            "SELECT COUNT(severity_id) FROM Reclamation where severity_id=2";
        Statement st2= new MyConnection().getCnx().createStatement();
      ResultSet rs2 =  st2.executeQuery(requete2);
      while(rs2.next())
      {
        
          Integer x2=rs2.getInt(1);
           stats.put("Average",x2);
    
         
      }
      //
       String requete3 = 
            "SELECT COUNT(severity_id) FROM Reclamation where severity_id=3";
        Statement st3= new MyConnection().getCnx().createStatement();
      ResultSet rs3 =  st.executeQuery(requete3);
      while(rs3.next())
      {  
          Integer x3=rs3.getInt(1);
           stats.put("high",x3);
       //  System.out.println("results3"+x);
      //   System.out.println("results3.2"+Integer.toString(x));
      }
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }
 return stats;
}
////////////////////////////////////////pie chart 2 for stat
public Map<String, Integer> piechart2(){

    Map<String, Integer> stats = new HashMap<>();
    try { 
        String requete = 
            "SELECT COUNT(etat) FROM Reclamation where etat=1";
        Statement st= new MyConnection().getCnx().createStatement();
      ResultSet rs =  st.executeQuery(requete);
      while(rs.next())
      {
       
          Integer x=rs.getInt(1);
          stats.put("responded",x);
       //  System.out.println("results"+x);
        // System.out.println("results.2"+Integer.toString(x));
         
      }
      //
       String requete2 = 
            "SELECT COUNT(etat) FROM Reclamation where etat=0";
        Statement st2= new MyConnection().getCnx().createStatement();
      ResultSet rs2 =  st2.executeQuery(requete2);
      while(rs2.next())
      {
        
          Integer x2=rs2.getInt(1);
           stats.put("pending",x2);
    
         
      }
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }
 return stats;
}
}

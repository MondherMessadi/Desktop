/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import package_entities.demande_maintenance;
import package_tools.MyConnection;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceMaintenance {
    private Connection cnx;
    private Statement st; 
    private PreparedStatement pst;
    //private ResultSet ls;
    private MyConnection ds = MyConnection.getInstance();


    public void ajouter(demande_maintenance p) throws SQLException{
        String req = "insert into demande_maintenance(nom,email,numtel,sujet,message) values ('"+p.getNom()+"', '"+p.getEmail()+"', '"+p.getNumtel()+"', '"+p.getSujet()+"', '"+p.getMessage()+"')";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
    
    public void ajouter2(demande_maintenance p) throws SQLException{
        String req = "insert into demande_maintenance(nom,email,numtel,sujet,message) values (?,?,?,?,?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, p.getNom());
        st.setString(2, p.getEmail());
        st.setInt(3, p.getNumtel());
        st.setString(4, p.getSujet());
        st.setString(2, p.getMessage());
        
        st.executeUpdate();
    }

   
    public void modifier(demande_maintenance R) throws SQLException{ 
    try {  
        System.out.println("updating Demande  : "+R);
    String requete ="UPDATE demande_maintenance SET nom = '"+R.getNom()+"', email= '"+R.getEmail()+"', "
    + "numtel='"+R.getNumtel()+"',sujet='"+R.getSujet()+"',message='"+R.getMessage()+"' where id="+R.getId();
    System.out.println("updating demande_maintenance  : "+requete);
    Statement st = ds.getCnx().createStatement();
        st.executeUpdate(requete);
        
        
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }

        
    }


    
 
    public ObservableList<demande_maintenance> getAll() {
        ObservableList<demande_maintenance>myList=FXCollections.observableArrayList();
    try { 
        String requete = 
            "SELECT * FROM demande_maintenance";
        Statement st = ds.getCnx().createStatement();
      ResultSet rs =  st.executeQuery(requete);
      while(rs.next())
      {
          demande_maintenance R = new demande_maintenance();
          R.setId(rs.getInt("id"));
          R.setNom(rs.getString("nom"));
          R.setEmail(rs.getString("email"));
          R.setNumtel(rs.getInt("numtel"));
          R.setSujet(rs.getString("sujet"));
          R.setMessage(rs.getString("message"));
          myList.add(R);
      }
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }
 return myList;
}

    public void supprimer(demande_maintenance d) throws SQLException {
String requete = "DELETE from demande_maintenance where id="+d.getId();
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        Statement st= new MyConnection().getCnx().createStatement();
        st.executeUpdate(requete);    }
    
    
    public List<demande_maintenance> TrieNom() {
        String req="select nom,email,numtel,sujet,message from demande_maintenance order by nom asc";
        ObservableList<demande_maintenance>myList=FXCollections.observableArrayList();
        try {
            Statement st = ds.getCnx().createStatement();
            ResultSet rs =  st.executeQuery(req);
            while(rs.next())
            {
            demande_maintenance R = new demande_maintenance();
          //R.setId(rs.getInt("id"));
          R.setNom(rs.getString("nom"));
          R.setEmail(rs.getString("email"));
          R.setNumtel(rs.getInt("numtel"));
          R.setSujet(rs.getString("sujet"));
          R.setMessage(rs.getString("message"));
          myList.add(R);
            }
        } catch (SQLException ex) {
            Logger.getLogger(demande_maintenance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myList;
    }

    }
    
 

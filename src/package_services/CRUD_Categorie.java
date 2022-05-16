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
public class CRUD_Categorie implements IService<Categorie>{
    Connection cnx = MyConnection.getInstance().getCnx();
    private Statement st; 
    private PreparedStatement pst;
    private ResultSet ls;
    
    public void ajouter2() throws SQLException{
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



public void ajouter(Categorie c) throws SQLException{
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



public ObservableList<Categorie> afficherCategorie() throws SQLException{
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



public void modifier(Categorie C){ 
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

    public List<Categorie> getAll() throws SQLException {
        String req="select * from categorie";
        List<Categorie> list = new ArrayList<>();
        
        try {
            st=cnx.createStatement();
            ls=st.executeQuery(req);
            while(ls.next())
            {
                list.add(new Categorie(ls.getInt(1),ls.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void supprimer(Categorie R) throws SQLException {
       try {  
        String requete = 
        "DELETE from categorie where id="+R.getId();
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
   
    
    public List<Categorie> ListClasse() {
        List<Categorie> Mylist = new ArrayList<>();
        try {
            String requete = "select * from categorie ORDER BY name ASC ";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
      ResultSet e = pst.executeQuery();
            while (e.next()) {
                Categorie pre = new Categorie();
             
             pre.setId(e.getInt("Id"));
            pre.setName(e.getString("Name"));
            
           
           
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerU(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    

    
}

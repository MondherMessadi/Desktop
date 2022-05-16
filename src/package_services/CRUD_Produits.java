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
import package_entities.Categorie;
import package_entities.Produits;
import package_tools.MyConnection;

/**
 *
 * @author Mondher
 */
public class CRUD_Produits implements IService<Produits>{

   Connection cnx = MyConnection.getInstance().getCnx();
     private Statement st; 
    private PreparedStatement pst;
    private ResultSet ls;

    @Override
    public void ajouter(Produits p) throws SQLException{
        try {
            String req = "INSERT INTO `produits` (`categorie_id`, `titre`, `prix`, `etat`, `lieu`, `description`, `image`, `email`) VALUES  ('"+p.getCat().getId()+"', '" + p.getTitre() + "', '" + p.getPrix() + "', '" + p.getEtat() + "', '" + p.getLieu() + "', '" + p.getDescription() + "', '" + p.getImage() + "', '" + p.getEmail() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produits created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Produits p) throws SQLException {
        try {
            String req = "INSERT INTO `produits` (`categorie_id`,`titre`, `prix`, `etat`, `lieu`, `description`, `image`, `email`, `etat_produit`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getCat().getId());
            ps.setString(2, p.getTitre());
            ps.setFloat(3, p.getPrix());
            ps.setString(4, p.getEtat());
            ps.setString(5, p.getLieu());
            ps.setString(6, p.getDescription());
            ps.setString(7, p.getImage());
            ps.setString(8, p.getEmail());
            ps.setString(9, p.getEtatProduit());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Produits R) throws SQLException {
        try {  
        String requete = 
        "DELETE from produit where id="+R.getId();
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

    @Override
    public void modifier(Produits p) throws SQLException {
        try {
            String req = "UPDATE produits SET  `categorie_id`='"+p.getCat().getId()+"', `Titre`='"+p.getTitre()+"', `Prix`='"+p.getPrix()+"', `Etat`='"+ p.getEtat()+"', `Lieu`='"+p.getLieu()+"', `Description`='"+p.getDescription()+"', `Image`='"+p.getImage()+"', `Email`='"+p.getEmail()+"', `etat_produit`='"+p.getEtatProduit()+"' WHERE `produits`.`id`="+p.getId();
             System.out.println("test req"+req);
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Produits> getAll() throws SQLException {
        List<Produits> list = new ArrayList<>();
        try {
            String req = "SELECT  produits p categorie c where c.id= p.categorie_id FROM ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                System.out.println("id = "+rs.getString("id")+  "  categorie_id = "+rs.getString("categorie_id")+  "  Titre ="+ rs.getString("Titre") + "  Prix =" + rs.getString("Prix") + "  Etat =" + rs.getString("Etat") + "  Lieu =" + rs.getString("Lieu") + "  Description=" + rs.getString("Description") + "  Image =" + rs.getString("Image") + "  Email =" + rs.getString("Email") + "  etat_produit="+rs.getString("etat_produit") );
                
           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    
    
    public List<Produits> ListClasse(  ) {
        List<Produits> Mylist = new ArrayList<>();
        try {
            String requete = "SELECT * from produits p , categorie c where c.id=p.categorie_id ORDER BY titre ASC ";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
      ResultSet e = pst.executeQuery();
            while (e.next()) {
                Produits pre = new Produits();
             
            Produits ls = new Produits();
                 Categorie c = new Categorie(e.getInt("c.id"),e.getString("c.name"));
     
                 
            ls.setCat(c);
            pre.setCat(c);
            pre.setId(e.getInt("Id"));
            pre.setTitre(e.getString("Titre"));
            pre.setPrix(e.getInt("Prix"));
            pre.setEtat(e.getString("Etat"));
            pre.setLieu(e.getString("Lieu"));
            pre.setDescription(e.getString("description"));
            pre.setImage(e.getString("Image"));
            pre.setEmail(e.getString("Email"));
            pre.setEtatProduit(e.getString("etat_produit"));
           
           
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    

    public ObservableList<Produits> afficherProduits(){
        ObservableList<Produits>myList=FXCollections.observableArrayList();
    try { 
        String requete = 
            "SELECT * from produits p , categorie c where c.id=p.categorie_id";
        Statement st= new MyConnection().getCnx().createStatement();
      ResultSet rs =  st.executeQuery(requete);
      while(rs.next())
      {
          Produits R = new  Produits();
          Categorie c = new Categorie(rs.getInt("c.id"),rs.getString("c.name"));
          
          R.setCat(c);
          R.setTitre(rs.getString("Titre"));
          R.setPrix(rs.getFloat("prix"));
          R.setEtat(rs.getString("Etat"));
          R.setLieu(rs.getString("Lieu"));
          R.setDescription(rs.getString("description"));
          R.setImage(rs.getString("Image"));
          R.setEmail(rs.getString("email"));
          R.setEtatProduit(rs.getString("etat_produit"));
          
          myList.add(R);
      }
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }
 return myList;
}
   
    @Override
    public boolean delete(int id) throws SQLException{
int result = 0;
        try {
        pst = cnx.prepareStatement("delete from produits where id=(?);");
        pst.setInt(1, id);
        result = pst.executeUpdate();

        } catch (SQLException ex) {
        Logger.getLogger(IService.class.getName()).log(Level.SEVERE, null,
       ex);
        }
        if (result == 1) {
        return true;
        }
        return false; 
    }
    
    
    @Override
    public List<Produits> displayAll() throws SQLException{
        List<Produits> Mylist = new ArrayList<>();
        try {
            String requete = "SELECT * from produits p , categorie c where c.id=p.categorie_id ORDER BY titre ASC ";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
      ResultSet e = pst.executeQuery();
            while (e.next()) {
                Produits pre = new Produits();
             
            Produits ls = new Produits();
                 Categorie c = new Categorie(e.getInt("c.id"),e.getString("c.name"));
     
                 
            ls.setCat(c);
            pre.setCat(c);
            pre.setId(e.getInt("Id"));
            pre.setTitre(e.getString("Titre"));
            pre.setPrix(e.getInt("Prix"));
            pre.setEtat(e.getString("Etat"));
            pre.setLieu(e.getString("Lieu"));
            pre.setDescription(e.getString("description"));
            pre.setImage(e.getString("Image"));
            pre.setEmail(e.getString("Email"));
            pre.setEtatProduit(e.getString("etat_produit"));
           
           
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    
    public List<Produits> afficherP() {
        List<Produits> list = new ArrayList<>();
        
        try {
            String req = "SELECT  titre, description, image , prix  FROM produits";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Produits( rs.getString("Titre"),rs.getString("Description"),rs.getString("image"),rs.getFloat("prix")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public void supprimerU(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
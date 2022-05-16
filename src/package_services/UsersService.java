/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_services;



import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import package_entities.Users;
import package_tools.MyConnection;

/**
 *
 * @author HAMOUDA
 */
public class UsersService implements IService<Users> {
   
    Connection cnx = MyConnection.getInstance().getCnx();

    public static Users currentUser = new Users();
    
    @Override
    public void ajouter(Users p) {
        try {
            String req = "INSERT INTO `users` (`email`, `roles` ,`password`,`genre`,`nom`,`prenom`,`image`) VALUES ('" + p.getEmail() + "', '" + p.getRole() + "', '"+mdpconvert( p.getPassword()) + "', '" + p.getGenre() + "', '" + p.getNom()+ "', '" + p.getPrenom()+ "', '" +p.getImage()+  "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("user created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Users p) {
        try {
            String req = "INSERT INTO `users` (`nom`, `prenom`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, p.getPrenom());
            ps.setString(1, p.getNom());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerU(int id) {
        try {
            String req = "DELETE FROM `users` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Users p) {
        try {
           /* String req = "UPDATE `users` SET `email` = '" + p.getEmail() + "', `password` = '" + p.getPassword() + "', `genre` = '" + p.getGenre()+ "', `nom` = '" + p.getNom() + "', `prenom` = '" + p.getPrenom()+ "', `image` = '" + p.getImage() + "' WHERE `users`.`id` = " + p.getId();*/
           String req = "UPDATE `users` SET `email` = '" + p.getEmail() + "', `genre` = '" + p.getGenre()+ "', `nom` = '" + p.getNom() + "', `prenom` = '" + p.getPrenom()+  "' WHERE `users`.`id` = " + p.getId();

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("user updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Users> getAll() {
        ObservableList<Users> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from users";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Users p = new Users(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    } 

    public boolean getUtilisateurByEmail(String email) {
        boolean exist = false;

        try {
            String sql = "SELECT * FROM users where email=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, email);

            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {
                exist = true;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return exist;
}
    
       public boolean getUtilisateurByCin(String cin) {

        boolean exist = false;
        try {
            String sql = "SELECT * FROM utilisateurs where cin=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, cin);
            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {
                exist = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return exist;

    }

public int ajouterUtilisateur(Users p) {//autoincrement
        int resultat = 0;
        String sql = "INSERT INTO `users` (`email`, `roles` ,`password`,`genre`,`nom`,`prenom`,`image`) VALUES ('" + p.getEmail() + "', '" + p.getRole() + "', '"+ p.getPassword() + "', '" + p.getGenre() + "', '" + p.getNom()+ "', '" + p.getPrenom()+ "', '" + p.getImage()+ "')";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
      
            ste.executeUpdate();
            System.out.println("utilisateur ajoutÃ© avec succÃ©es");
            resultat = 1;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return resultat;
    }
    public String Seconnecter(String email, String motdepasse) {
        String sql = "Select * from users where email=?";
        String result = "failed";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setString(1, email);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                String pwd = rs.getString("password");
                if (mdpconvert(motdepasse).equals(pwd)) {
                 
                  result = "success";

                } else {
                    System.out.println("mdp incorrecte");
                }

            } else {

                System.out.println("email introuvable");

            }

        } catch (SQLException ex) {
        }
        return result;

    }

 public String getRoleByCin(String email)  {

        String roles = "";
        try {
            String sql = "SELECT `roles` FROM `users` WHERE `email` ='" + email + "'";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();//resultat requete sql

            while (rs.next()) {
                roles = rs.getString("roles");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return roles;
    
 } public String mdpconvert(String p) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(p.getBytes());
            BigInteger pwd = new BigInteger(1, messageDigest);
            String hashpwd = pwd.toString(16);
            return hashpwd;

        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void supprimer(Users p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Users> displayAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
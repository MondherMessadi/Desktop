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
import java.util.ArrayList;
import java.util.List;
import package_entities.reponse;
import package_tools.MyConnection;
/**
 *
 * @author maroo
 */
public class ServiceReponse implements IService<reponse>{
    private MyConnection ds = MyConnection.getInstance();
    
    public void ajouter(reponse p) throws SQLException{
        String req = "insert into reponse(subject,mail,object) values ('"+p.getSubject()+"', '"+p.getMail()+"', '"+p.getObject()+"')";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
    public void ajouter2(reponse p) throws SQLException {
        String req = "insert into reponse(subject,mail,object) values (?,?,?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, p.getSubject());
        st.setString(2, p.getMail());
        st.setString(3, p.getObject());
        //st.setInt(4, p.getdemande());
        st.executeUpdate();
    }

    @Override
    public void modifier(reponse p) throws SQLException {
String req = "UPDATE `reponse` SET `subject` = '"+p.getSubject()+"', '"+p.getMail()+"', '"+p.getObject();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `reponse` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<reponse> getAll() throws SQLException {
            List<reponse> list = new ArrayList<>();
        
        String req = "Select * from reponse";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            /*demande_maintenance p = new demande_maintenance(rs.getInt("id"));
            list.add(p);*/
        }
        
        return list;
    }

    @Override
    public void supprimer(reponse p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<reponse> displayAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerU(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

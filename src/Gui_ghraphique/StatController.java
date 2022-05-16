/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import package_tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mondher
 */
public class StatController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Statement st;
    private ResultSet rs;
    @FXML
    private PieChart piechart;
    @FXML
    private Button Retourid;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection cnx = MyConnection.getInstance().getCnx();
        stat();
    }    
    
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    
     private void stat()
    {
          
                   Connection cnx = MyConnection.getInstance().getCnx();

      try {
           
          String query = "SELECT COUNT(id), etat_produit FROM produits GROUP BY etat_produit" ;
       
             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("etat_produit"),rs.getInt("COUNT(id)")));
            }     
        } catch (SQLException ex) {
        }
      
        piechart.setTitle("*Statistiques des produit*");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }

    @FXML
    private void Retourid(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("afficheproduitdashboard"); 
                           Retourid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    
}
    


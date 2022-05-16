/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import package_entities.demande_maintenance;
import package_services.ServiceMaintenance;

/**
 * FXML Controller class
 *
 * @author maroo
 */
public class ModifierController implements Initializable {
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfNumtel;
    @FXML
    private TextField tfSujet;
    @FXML
    private TextField tfMessage;
    demande_maintenance d =null; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void setelementtoupdate(demande_maintenance d){
        this.d=d;
    System.out.println("les champs a etes modifier :"+ d.toString());
    String s = String.valueOf(d.getNumtel());
    this.tfNom.setText(d.getNom());
    this.tfemail.setText(d.getEmail());
    
    this.tfNumtel.setText(s);
    
    this.tfSujet.setText(d.getSujet());
    this.tfMessage.setText(d.getMessage());
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        String nom = tfNom.getText();
            String email = tfemail.getText();
            String message = tfMessage.getText();
              String sujet = tfSujet.getText();      
              int numtel = Integer.parseInt(tfNumtel.getText());
            ServiceMaintenance SM =new ServiceMaintenance(); 
            this.d.setNom(nom);
            this.d.setEmail(email);
            this.d.setNumtel(numtel);
            this.d.setSujet(sujet);
            this.d.setMessage(message);   
            
           SM.modifier(d);
          FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("ddashboard"); 
                          tfNom.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
            
           }

    @FXML
    private void annuler(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("ddashboard"); 
                           tfNom.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    

}
    


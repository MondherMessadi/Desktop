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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import package_entities.demande_maintenance;
import package_services.ServiceMaintenance;

/**
 * FXML Controller class
 *
 * @author maroo
 */
public class SupprimerDemandeController implements Initializable {

    @FXML
    private Button btn_delete;
    private TextField tfNom;
    private TextField tfemail;
    private TextField tfNumtel;
    private TextField tfSujet;
    private TextField tfMessage;
    demande_maintenance d=null;
    @FXML
    private TextArea textfieldid;
    @FXML
    private Button cancelid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setdm(demande_maintenance d){
 
         this.d=d;
    }
    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        ServiceMaintenance sup =new ServiceMaintenance();  
       sup.supprimer(this.d);
          FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("ddashboard"); 
                           textfieldid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }

    }

    public void settext(String message){
   this.textfieldid.setText(message);
    }

    @FXML
    private void cancel(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("ddashboard"); 
                           textfieldid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
        
    }
}

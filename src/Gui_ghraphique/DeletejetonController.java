/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import package_entities.Jetons;
import package_services.CRUD_Jetons;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class DeletejetonController implements Initializable {
 Jetons j=null;
    @FXML
    private Button confirmid;
    @FXML
    private Button cancelid;
    @FXML
    private TextArea textfieldid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      
        
    }    
     public void setjet(Jetons j){
 
         this.j=j;
    }

    @FXML
    private void deletejeton(ActionEvent event) {
          CRUD_Jetons CRUD =new CRUD_Jetons();  
       CRUD.deleteJeton(this.j);
          FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("affichejetondashboard"); 
                           textfieldid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    

    @FXML
    private void cancel(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("affichejetondashboard"); 
                           textfieldid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
        
    }
     public void settext(String message){
   this.textfieldid.setText(message);
    }
}

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
import package_entities.Reclamation;
import package_services.CRUD_Reclamation;

/**
 * FXML Controller class
 *
 * 
 */
public class ConfirmdeleteController implements Initializable {

    @FXML
    private TextArea textid;
    @FXML
    private Button confirmdelid;
    @FXML
    private Button canceldelid;
    
    Reclamation rec=null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     //  this.textid.setText("voulez vous supprimer la reclamation d'id : "+this.rec.getId());
   
    }    

    @FXML
    private void delete(ActionEvent event) { 
        CRUD_Reclamation CRUD =new CRUD_Reclamation();  
       CRUD.deleteRec(this.rec);
       System.out.println("reclamation deleted : confirm deletecontroller");
          FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("Reclamation details"); 
                           textid.getScene().setRoot(root);
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
                             SBC.loadPage("Reclamation details"); 
                           textid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    public void setrec(Reclamation rec){
    this.rec=rec;
    }
    
     public void settext(String message){
   this.textid.setText(message);
    }
}

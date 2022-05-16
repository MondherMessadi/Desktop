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
import package_entities.severity;
import package_services.CRUD_Severity;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class ConfirmdeleteseverityController implements Initializable {

    @FXML
    private TextArea textid;
    @FXML
    private Button confirmid;
    @FXML
    private Button cancelid;
 severity sev=null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirm(ActionEvent event) {
             CRUD_Severity CRUD =new CRUD_Severity();  
       CRUD.deleteSeverity(this.sev);
       System.out.println("sev deleted : confirm deletecontroller");
          FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("severitylist"); 
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
                             SBC.loadPage("severitylist"); 
                           textid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
   public void setsev(severity sev){
    this.sev=sev;
    } 
   public void settext(String message){
   this.textid.setText(message);
    }
}

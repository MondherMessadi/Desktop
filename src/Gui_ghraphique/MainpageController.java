/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class MainpageController implements Initializable {

    @FXML
    private BorderPane bpid;
    @FXML
    private Button produitmpid;
    @FXML
    private Button jetonmpid;
    @FXML
    private Button Recmpid;
    @FXML
    private Button maintenancempid;
    private Button todashboardid;
    @FXML
    private TextField jetonsnumberid;
     static int jetonsclics;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    @FXML
    private void produitmp(ActionEvent event) {
        loadPage("frontproduits");
        
        
    }

    @FXML
    private void jetonmp(ActionEvent event) {
               loadPage("affichejeton"); 
    }

    @FXML
    private void Reclamationmp(ActionEvent event) {
          loadPage("Reclamation");   
    }

    @FXML
    private void maintenancemp(ActionEvent event) {
        loadPage("AjouterDemande");
        
    }

    private void todashboard(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml")); 
                      try {
                        Parent root = loader.load();
                            todashboardid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    
    
        public void loadPage(String page)
    {
        Parent root =  null;
        try {
            root =FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bpid.setCenter(root);
        
    }

     public void setjetons(int message){
         System.out.println("calleedd jetons ");
         jetonsclics+=message;
        this.jetonsnumberid.setText(""+jetonsclics);
    }
    

  

   

}

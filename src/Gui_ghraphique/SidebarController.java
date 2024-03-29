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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * 
 */
public class SidebarController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button backid;
    @FXML
    private Button prodif;
    @FXML
    private Button productid;
    @FXML
    private Button jetonsid;
    @FXML
    private Button maintenanceid;
    @FXML
    private Button Recid;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button categorieid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     

    @FXML
    private void tolandingpage(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpage.fxml")); 
                      try {
                        Parent root = loader.load();
                            backid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }

    @FXML
    private void profiles(ActionEvent event) {
        loadPage("GestionUser");
    }

    @FXML
    private void produits(ActionEvent event) {
        loadPage("afficheproduitdashboard");
    }
    
    @FXML
    private void categorieD(ActionEvent event) {
        loadPage("affichecategoriedashboard");
    }

    @FXML
    private void jetons(ActionEvent event) {
            //loadPage("affichejetondashboard");
           // loadPage("Ajoutjeton");
              loadPage("affichejetondashboard");
    }

    @FXML
    private void Maintenance(ActionEvent event) {
        loadPage("ddashboard");
    }

    @FXML
    private void Reclamation(ActionEvent event) {
           loadPage("Reclamation Details");
    }
      
    
    public void loadPage(String page)
    {
        Parent root =  null;
        try {
            root =FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
        
    }

    
}

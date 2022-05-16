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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import package_entities.Jetons;
import package_services.CRUD_Jetons;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AjoutjetonController implements Initializable {

    @FXML
    private TextField nameid;
    @FXML
    private TextField descid;
    @FXML
    private TextField priceid;
    @FXML
    private TextField clicid;
    @FXML
    private RadioButton cat1id;
    @FXML
    private ToggleGroup grprad;
    @FXML
    private RadioButton cat2id;
    @FXML
    private RadioButton cat3id;
    @FXML
    private Button addid;
    @FXML
    private Button cancelid;
ValidationSupport validationsupport = new ValidationSupport();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             validationsupport.registerValidator(nameid,Validator.createEmptyValidator("Text is required"));
                validationsupport.registerValidator(descid,Validator.createEmptyValidator("Text is required"));
        validationsupport.registerValidator(priceid,Validator.createEmptyValidator("Text is required"));
         validationsupport.registerValidator(clicid,Validator.createEmptyValidator("Text is required"));
    }    

    @FXML
    private void ajoutjeton(ActionEvent event) {
          CRUD_Jetons CRUD =new CRUD_Jetons();  
        String name = nameid.getText();
            if(name.length()<3)
            {nameid.setStyle("-fx-border-clor:red;-fx-border-width:2px;");
                       new animatefx.animation.Shake(nameid).play();
            }
            
            String desc = descid.getText();
             if(desc.length()<5)
            {descid.setStyle("-fx-border-clor:red;-fx-border-width:2px;");
                       new animatefx.animation.Shake(descid).play();
            }
            String price = priceid.getText();
            
              if(price.length()==0)
            {priceid.setStyle("-fx-border-clor:red;-fx-border-width:2px;");
                       new animatefx.animation.Shake(priceid).play();
            }
              String clics = clicid.getText();
                if(clics.length()==0)
            {clicid.setStyle("-fx-border-clor:red;-fx-border-width:2px;");
                       new animatefx.animation.Shake(clicid).play();
            }
                
                if(name.length()>=3 && desc.length()>=5 && price.length()!=0 && clics.length()>=0)  
            {
                Jetons j = new Jetons();
                
                j.setName(name);
                j.setClics(clics);
                j.setDescription(desc);
                j.setPrice(price);
               if(cat1id.isSelected()){j.setCategory(1);}
               else if(cat2id.isSelected()){j.setCategory(2);}
               else if(cat3id.isSelected()){j.setCategory(3);}
               CRUD.ajouterJeton2(j);
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                    try { 
                        Parent root =loader.load();
                        //ReclamationDetailsController RDC = loader.getController();
                        //RDC.setTextid(phonenumber);
                        SidebarController SBC = loader.getController();
                        SBC.loadPage("affichejetondashboard"); 
                                           
                        nameid.getScene().setRoot(root);                  
                        } 
                    catch (IOException ex) {
                        System.out.println("error "+ex.getMessage());

                    }
            
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
                           nameid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    
}

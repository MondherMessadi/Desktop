/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Ghraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import package_entities.Reclamation;
import package_services.CRUD_Reclamation;
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import package_entities.severity;
/**
 * FXML Controller class
 *
 */
public class ReclamationController implements Initializable {
   
    @FXML
    private TextField Phonenumberid;
    @FXML
    private TextField Emailid;
    @FXML
    private TextField descriptionid;
    @FXML
    private Button validerid;
    @FXML
    private RadioButton highid;
    @FXML
    private RadioButton normalid;
    @FXML
    private RadioButton averageid;
    @FXML
    private ToggleGroup sev;

    
    ValidationSupport validationsupport = new ValidationSupport();
    @FXML
    private Label EVlabel;
    @FXML
    private Label PVlabel;
    @FXML
    private Label DVlabel;
    /**
     * Initializes the controller class.
     */
    severity sv;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        validationsupport.registerValidator(Emailid,Validator.createEmptyValidator("Text is required"));
                validationsupport.registerValidator(Phonenumberid,Validator.createEmptyValidator("Text is required"));
        validationsupport.registerValidator(descriptionid,Validator.createEmptyValidator("Text is required"));

        
        
    }    

    @FXML
    private void SaveReclamation(ActionEvent event) {
     
        
            String phonenumber = Phonenumberid.getText();
            if(phonenumber.length()!=8)
            {
                Phonenumberid.setStyle("-fx-border-clor:red;-fx-border-width:2px;");
                       new animatefx.animation.Shake(Phonenumberid).play();
                       
                        PVlabel.setText("phone number is required and must contains 8 characters");
            }
             else{
             PVlabel.setText(" ");
             }
            
            String email = Emailid.getText();
             if(email.length()<5)
            {Emailid.setStyle("-fx-border-clor:red;-fx-border-width:2px;");
                       new animatefx.animation.Shake(Emailid).play();
                       EVlabel.setText("Email is required and must contains at least 5 characters");
            }
             else{
             EVlabel.setText(" ");
             }
            String description = descriptionid.getText();
            
              if(description.length()<3)
            {descriptionid.setStyle("-fx-border-clor:red;-fx-border-width:2px;");
                       new animatefx.animation.Shake(descriptionid).play();
                       DVlabel.setText("Description is required and must contains at least 3 characters");
            }
             else{
             DVlabel.setText(" ");
             }
              
            if(description.length()>=3 && email.length()>=5 && phonenumber.length()==8)  
            {
                        if(highid.isSelected()){
                            //this.severity=highid.getText();
                            this.sv=new severity(3,"high");
                        }
                        else if(averageid.isSelected()){
                           // this.severity=averageid.getText();
                          this.sv=new severity(2,"Average");

                        }
                        else {
                            //this.severity=normalid.getText();
                         this.sv=new severity(1,"Normal");

                        }
                        CRUD_Reclamation CRUD =new CRUD_Reclamation(); 
                        
                        
                        Reclamation rec = new Reclamation(phonenumber,email,description,this.sv,java.time.LocalDate.now().toString(),0);
                        CRUD.ajouterRec2(rec);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpage.fxml"));
                    try {
                        Parent root =loader.load();
                        //ReclamationDetailsController RDC = loader.getController();
                        //RDC.setTextid(phonenumber);
                        Phonenumberid.getScene().setRoot(root);
                        
                        } 
                    catch (IOException ex) {
                        System.out.println("error "+ex.getMessage());

                    }
           }
        
    }

  
    
}

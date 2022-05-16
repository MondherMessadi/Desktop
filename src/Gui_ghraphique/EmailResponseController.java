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
import javafx.scene.control.TextField;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.scene.control.Label;
import package_services.CRUD_Reclamation;
/**
 * FXML Controller class
 *
 * 
 */
public class EmailResponseController implements Initializable {

    @FXML
    private TextArea Descid;
    @FXML
    private TextField Emailid;
    @FXML
    private Button RBid;
    @FXML
    private Button cancelid;
    ValidationSupport validationsupport = new ValidationSupport();
    @FXML
    private Label Descvalidationlabel;
 Integer reclamationid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                validationsupport.registerValidator(Descid,Validator.createEmptyValidator("Text is required"));

    }    

    @FXML
    private void ResponseEmail(ActionEvent event) {
        if(Descid.getText().length()<3)
            {Descid.setStyle("-fx-border-clor:red;-fx-border-width:4px;");
           new animatefx.animation.Shake(Descid).play();
           this.Descvalidationlabel.setText("Description is required and must contains at least 3 characterrs");
            }
        
        else{
            ///////////////////
             this.Descvalidationlabel.setText(" ");
          String host="yasmine.bechiekh@gmail.com";  //← my email address
        final String user="yasmine.bechiekh@gmail.com";//← my email address
        final String password="hmkzvietjpmttwoh";//change accordingly   //← my email password
        String to=this.Emailid.getText();             
        // session object
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //My message :
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Forsa Reponse Reclamation  ");
            //Text in emial :
            //message.setText("  → Text message for Your Appointement ← ");
            //Html code in email :
            message.setContent(
                    "<h4 style =\"color:blue\" > Bonjour, </h4> <br/> <h5> "+this.Descid.getText() +"</h5>",
                    "text/html");

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully via mail ");
               
       CRUD_Reclamation CRUD =new CRUD_Reclamation();  
       CRUD.sendEmail(this.reclamationid);
              FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("Reclamation details"); 
                           Descid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }

        } catch (MessagingException e) {e.getMessage();}
           
        }
    }
    /////////////////////////////////////////////////////////////
    public void setTextid(String message){
    this.Emailid.setText(message);
    }
    public void setreclamationid(Integer id)
    {
    this.reclamationid=id;
    }

    @FXML
    private void cancel(ActionEvent event) {
       
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml")); 
                
                        try {
                        Parent root = loader.load();
                       
                           SidebarController SBC = loader.getController();
                             SBC.loadPage("Reclamation details"); 
                            Emailid.getScene().setRoot(root);
                         
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
}

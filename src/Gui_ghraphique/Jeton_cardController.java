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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import package_entities.Jetons;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class Jeton_cardController implements Initializable {

    @FXML
    private Label nameid;
    @FXML
    private Label priceid;
    @FXML
    private Label descid;
    @FXML
    private Button buyid;
private Jetons jeton;
Integer clics =0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onbuy(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpage.fxml"));
         
         
                              
             try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                              MainpageController MPC = loader.getController();
                            MPC.setjetons(this.clics);
                            MPC.loadPage("affichejeton");
                              nameid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }                 
                              
            ///////////////////
           
          String host="ayarii.mahdii@gmail.com";  //← my email address
        final String user="ayarii.mahdii@gmail.com";//← my email address
        final String password="azglzlzwxmgemzxs";//change accordingly   //← my email password

        String to="ayarii.mahdii@gmail.com";
                

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
            message.setSubject("Forsa Jetons");
            //Text in emial :
            //message.setText("  → Text message for Your Appointement ← ");
            //Html code in email :
            message.setContent(
                    "<h4 style =\"color:black\" > Bonjour chere client, </h4> <br/> <h5> vous avez achete "+this.descid.getText() +" </h5>",
                    "text/html");

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully via mail ");
               
      
          

        } catch (MessagingException e) {e.printStackTrace();}
            ///////////////////
        
        
    }
  public void setdata(Jetons jeton){
  this.jeton=jeton;
  nameid.setText(this.jeton.getName());
  priceid.setText(this.jeton.getPrice());
  this.descid.setText(this.jeton.getDescription());
  System.out.println("test "+Integer.parseInt(this.jeton.getClics()));
  this.clics=Integer.parseInt(this.jeton.getClics());
   
  } 
}

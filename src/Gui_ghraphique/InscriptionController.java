/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;


import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import package_entities.Users;
import package_services.UsersService;

/**
 * FXML Controller class
 *
 * @author HAMOUDA
 */
public class InscriptionController implements Initializable {

    private UsersService US = new UsersService();
    @FXML
    private TextField ftnom;
    @FXML
    private TextField ftprenom;
    @FXML
    private TextField ftmail;
    @FXML
    private TextField ftmdp;
    @FXML
    private TextField ftcomfirm_mdp;
    @FXML
    private TextField ftgenre;
    @FXML
    private AnchorPane patern1;
    @FXML
    private Button inscription;
    int code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void SignUpAction(ActionEvent event) {

    }
    private Parent root;

    public int code_conf() {
        int min = 10000;
        int max = 99999;
        int generatedCode = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return generatedCode;
    }

    

    @FXML
    private void ajouter(ActionEvent event) throws IOException, AddressException {

        if (ftnom.getText().isEmpty() == true
                || ftprenom.getText().isEmpty() == true
                || ftmail.getText().isEmpty() == true || ftmdp.getText().isEmpty() == true
                || ftcomfirm_mdp.getText().isEmpty() == true
                || ftgenre.getText().isEmpty() == true
                || !ftmdp.getText().equals(ftcomfirm_mdp.getText())) {
            JOptionPane.showMessageDialog(null, " Veuileez vérifier les champs ");
        } else if (US.getUtilisateurByEmail(ftmail.getText()) == true) {
            JOptionPane.showMessageDialog(null, "Un compte a  cette adresse ");
            System.out.println("utilisateur existe deja (email)");
        } else {
            Users u = new Users(ftmail.getText(), "", ftmdp.getText(), ftgenre.getText(), ftnom.getText(), ftprenom.getText(), "");
            System.out.println(u);
            US.ajouter(u);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Personne added !", ButtonType.OK);
            a.showAndWait();
            code = code_conf();
            String from = "mohamed.hcini@esprit.tn";
            String to = ftmail.getText();
            String host = "localhost";
            String sub = "votre comptea ete bien cree";
            String content = "votre code est " + code;

            Properties pp = new Properties();
            pp.put("mail.smtp.auth", "true");
            pp.put("mail.smtp.starttls.enable", "true");
            pp.put("mail.smtp.host", "smtp.gmail.com");
            pp.put("mail.smtp.port", "587");
            Session s = Session.getDefaultInstance(pp, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("mohamed.hcini@esprit.tn", "213JMT5981");
                }

            });

            try {
                MimeMessage m = new MimeMessage(s);
                m.setFrom(from);
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                m.setSubject(sub);
                m.setText(content);
                Transport.send(m);
                System.out.println("success");

            } catch (Exception e) {
                e.printStackTrace();
            }

           
           
            
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("CodeConfirmation.fxml"));
                        try {
                        Parent root = loader.load(); 
                            CodeConfirmationController confController = loader.getController();
                            confController.getCode(code);
                            inscription.getScene().setRoot(root);
                        } catch (IOException ex) {
                          System.out.println("err "+ex.getMessage());
                        }
            
            
            
           
            
            
            /*
             FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpage.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             FXMLLoader loaderr = new FXMLLoader(getClass().getResource("CodeConfirmation.fxml"));
                             CodeConfirmationController confController = loaderr.getController();
            confController.getCode(code);
                             MainpageController SBC = loader.getController();
                             SBC.loadPage("CodeConfirmation"); 
                           inscription.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
            */

        }
    }

}

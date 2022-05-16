/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import package_entities.demande_maintenance;
import package_entities.reponse;
import package_services.ServiceMaintenance;
import package_services.ServiceReponse;
import package_tools.MyConnection;
/**
 * FXML Controller class
 *
 * @author maroo
 */
public class EnvoyerReponseController implements Initializable {

    @FXML
    private AnchorPane anchropane;
    @FXML
    private Button btn_ajout;
    @FXML
    private TextField tfSubject;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextArea tfObject;
    demande_maintenance d=null;
    demande_maintenance dmm;
    int id=0;
    ResultSet rs;
    Statement st;
    PreparedStatement pst;
    private Connection cnx;
    String from,to,host,sub,content;
    @FXML
    private ComboBox<demande_maintenance> num;
    /**
     * Initializes the controller class.
     */
    final ObservableList<demande_maintenance> options= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void fillComboBox() throws SQLException
    {
       
        try {
            List<demande_maintenance> list=new ArrayList<>();
            cnx=MyConnection.getInstance().getCnx();
            String query="select * from demande_maintenance";
             pst=cnx.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next())
            {
                dmm=new demande_maintenance(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));
                list.add(dmm);

                
            }
            options.addAll(list);
             num.getItems().setAll(list);
        } catch (SQLException ex) {
        }
        String query1="select id from demande_maintenance where email=? and numtel=?";
            
            pst=cnx.prepareStatement(query1);
            pst.setString(1,dmm.getEmail());
            pst.setInt(2,dmm.getNumtel());
            pst.executeQuery();
             while(rs.next())
            {
                id=rs.getInt(1);
            }
    }
    public void setdm(demande_maintenance d){
 
         this.d=d;
    }


    @FXML
    private void ajout(ActionEvent event) throws IOException{
        
        from ="mohamedhadji603@gmail.com";
        to =tfEmail.getText();
        host="localhost";
        sub=tfSubject.getText();
        content=tfObject.getText();
        Properties P = new Properties();
        P.put("mail.smtp.auth","true");
        P.put("mail.smtp.starttls.enable","true");
        P.put("mail.smtp.host","smtp.gmail.com");
        P.put("mail.smtp.port","587");
        Session session = Session.getInstance(P, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("forsa.techniciens@gmail.com", "azeqsdwxc,;:!123456789");

            }

        });
        try {
            MimeMessage m = new MimeMessage(session);
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(sub); 
            
            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodypart= new MimeBodyPart();
            textBodypart.setText("Demande de maintenance");
            MimeBodyPart pdfAttachement = new MimeBodyPart();
            pdfAttachement.attachFile("src/pdfdemande/demamde de maintenance.pdf");
            emailContent.addBodyPart(textBodypart);
            emailContent.addBodyPart(pdfAttachement);
            m.setText(content);
            m.setContent(emailContent);
            Transport.send(m);
            System.out.println("email envoyer");
        } catch (Exception e) {
             e.printStackTrace();
        }
    
        String email = tfEmail.getText();
        if (tfSubject.getText().isEmpty() || tfEmail.getText().isEmpty() || tfObject.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Remplire tous Les champs", ButtonType.OK);
            a.showAndWait();
        }
               else if (email.matches("\\w{3,}")){
        Alert a = new Alert(Alert.AlertType.ERROR, "email doit etre exemple@gmail.com", ButtonType.OK);
            a.showAndWait();
        }
        else {
            try {
                ServiceReponse sp = new ServiceReponse();
                reponse p = new reponse(tfSubject.getText(), tfEmail.getText(),tfObject.getText());
                sp.ajouter(p);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Votre email a ete envoyer!", ButtonType.OK);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ddashboard.fxml"));
                Parent root = loader.load();
                tfEmail.getScene().setRoot(root);
                
            } catch (SQLException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                a.showAndWait();
            }
        }
        
    }
    
    @FXML
    private void annuler(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("ddashboard"); 
                           tfSubject.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import package_entities.Categorie;
import package_entities.Produits;
import package_services.CRUD_Produits;
import package_tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mondher
 */
public class UpdateproduitController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfEtat;
    @FXML
    private TextField tfLieu;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfImage;
    @FXML
    private TextField tfEtatP;
    @FXML
    private ComboBox<Categorie> tfCategorieId;
    @FXML
    private TextArea tfDescriptin;
    @FXML
    private ImageView uploadIm;
    
    String uploads = "D:\\FORSA\\public\\layout\\img\\";
    private String path = "", imgname = "", fn="";
    
    ResultSet rs;
    Statement st;
    PreparedStatement pst;
    private Connection cnx;
    Categorie cat ;
    int id=0;
    
    final ObservableList<Categorie> options= FXCollections.observableArrayList();
    
    Produits p=null ;
    @FXML
    private Button modifierid;
    @FXML
    private Button cancelid;

    String from,to,host,sub,content;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // TODO
            fillComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void setelementtoupdate(Produits p){
     this.p=p;
    System.out.println("elemtn to update :"+ p.toString());
    String s = String.valueOf(p.getPrix());
    this.tfTitre.setText(p.getTitre());
    this.tfPrix.setText(s);
    this.tfCategorieId.setValue(p.getCat());
    this.tfEtat.setText(p.getEtat());
    this.tfDescriptin.setText(p.getDescription());
    this.tfLieu.setText(p.getLieu());
    this.tfEmail.setText(p.getEmail());
    this.tfImage.setText(p.getImage());
    this.tfEtatP.setText(p.getEtatProduit());
    
    System.out.println(p);
    
    }
    
    public void fillComboBox() throws SQLException
    {
       
        try {
            List<Categorie> list=new ArrayList<>();
            cnx=MyConnection.getInstance().getCnx();
            String query="select * from categorie";
            
            pst=cnx.prepareStatement(query);
                  //  pst.setString(1, query);
            rs=pst.executeQuery();
            while(rs.next())
            {
//                int i=rs.getInt("id_vehicule");   
//                String st=Integer.toString(i);
//                System.out.println(st);
//                options.add(st);
                cat =new Categorie(rs.getInt(1),rs.getString(2)); 
                list.add(cat);
                
            }
            options.addAll(list);
             tfCategorieId.getItems().setAll(list);

            System.out.println("aaa");
            System.out.println(options);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query1="select id from categorie where name=?";
            
            pst=cnx.prepareStatement(query1);
            pst.setString(1,cat.getName());
            
            rs=pst.executeQuery();
             while(rs.next())
            {
                id=rs.getInt(1);
            }
    }

    @FXML
    private void AddIm(ActionEvent event) throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        path= f.getAbsolutePath();
        imgname = f.getName();
        //tfImage.setText(imgname);
        fn = imgname;
        Image getAbsolutePath = null;

        String dd = uploads + f.getName();
        File dest = new File(dd);
        this.copyFile(f, dest);

        System.out.println(dd);
        

        uploadIm.setImage(new Image("file:" + dest.getAbsolutePath()));
        
    }
    
    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        try (
                FileChannel in = new FileInputStream(sourceFile).getChannel();
                FileChannel out = new FileOutputStream(destFile).getChannel();) {

            out.transferFrom(in, 0, in.size());
        }
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException{
        from ="forsa.techniciens@gmail.com";
        to =tfEmail.getText();
        host="localhost";
        String s ="FORSA Produit Modifier";
        sub=s;
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
            textBodypart.setText("Votre Produit à été Modifier par l'administrateur !!");
            MimeBodyPart pdfAttachement = new MimeBodyPart();
            pdfAttachement.attachFile("src/pdfproduit/Produit Modifier.pdf");
            emailContent.addBodyPart(textBodypart);
            emailContent.addBodyPart(pdfAttachement);
            //m.setText(content);
            m.setContent(emailContent);
            Transport.send(m);
            System.out.println("email envoyer");
        } catch (Exception e) {
             e.printStackTrace();
        }
        
        Categorie categorie =tfCategorieId.getValue();
        String titre =tfTitre.getText();
     float prix = Float.parseFloat(tfPrix.getText());
     String etat = tfEtat.getText();
     String lieu =tfLieu.getText();
   
     String description = tfDescriptin.getText();
    String email =tfEmail.getText();
    
   String image = tfImage.getText();
    String etatProduit = (tfEtatP.getText());
   
   
        CRUD_Produits CRUD =new CRUD_Produits(); 
            this.p.setTitre(titre);
            this.p.setPrix(prix);
            this.p.setCat(categorie);
            this.p.setEtat(etat);
            this.p.setDescription(description);
            this.p.setEmail(email);
            this.p.setEtatProduit(etatProduit);
            this.p.setLieu(lieu);
            this.p.setImage(image);
              
            System.out.println("testp"+p);
           CRUD.modifier(p);
          FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("afficheproduitdashboard"); 
                          modifierid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
                        
                        //tab.refrech();
            }
    

    @FXML
    private void cancelid(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("afficheproduitdashboard"); 
                           cancelid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    
}

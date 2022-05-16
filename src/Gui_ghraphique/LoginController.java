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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import package_services.UsersService;

/**
 * FXML Controller class
 *
 * @author HAMOUDA
 */
public class LoginController implements Initializable {
private UsersService US = new UsersService();
    @FXML
    private AnchorPane parent;
    @FXML
    private TextField ftmail;
    @FXML
    private PasswordField ftmdp;
    @FXML
    private Button login;
    @FXML
    private Label Inscriid;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO US = new UsersService();
    }    
   
private Parent root;

    @FXML
    private void btn_login(ActionEvent event) throws IOException {
        
        
        String email = ftmail.getText();
        String pwd = ftmdp.getText();
        if (email.isEmpty() || pwd.isEmpty()) {
            JOptionPane.showMessageDialog(null, " Veuileez vérifier les champs ");
        } else {
            UsersService us = new UsersService();

            String output = us.Seconnecter(email, pwd);
             System.out.println(output);
            if (output == "success") {
                String roles =us.getRoleByCin(email);
                                        
                    System.out.println(roles);
                if (roles.equals("")) {

                   FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpage.fxml"));

                    try {
                        Parent root = loader.load();
                        MainpageController SBC = loader.getController();
                        SBC.loadPage("Frontproduits"); 
                        login.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    System.out.println("role is not valid");
                    
                    
                } else  
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));

                    try {
                        Parent root = loader.load();
                        login.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
             
                    System.out.println("role is valid");
                }
            } 

        }
        
        
        
        
        
    /*FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionUser.fxml"));    
    root = loader.load();
    Scene scene = new Scene(root);
    login.setScene(scene);
    login.setMaximized(true);
    login.show();*/
    }

    @FXML
    private void Inscription(MouseEvent event) {
       
                        try {
                        Parent root = FXMLLoader.load(getClass().getResource("Inscription.fxml")) ;
                        
                           Inscriid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    
}

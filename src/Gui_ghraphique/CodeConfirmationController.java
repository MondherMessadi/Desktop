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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HAMOUDA
 */
public class CodeConfirmationController implements Initializable {

    @FXML
    private AnchorPane ftcode_conf;
    @FXML
    private TextField ftcode;
    @FXML
    private Button envoyer;

    int code;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
  private Parent root;
  Stage inscriptionStage = new Stage();

    @FXML
    private void btn_envoyer(ActionEvent event) throws IOException {
        System.out.println(code);
       if (String.valueOf(code).equals(ftcode.getText()) ){
           System.out.println(code);
           
          try {
                        Parent root = FXMLLoader.load(getClass().getResource("login.fxml")) ;
                        
                           envoyer.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
       }else{
           System.out.println("codex code");
           Alert a = new Alert(Alert.AlertType.INFORMATION, "code invalide !" , ButtonType.OK );
            a.showAndWait();}
       
    }
    
    public void getCode(int code) {
        this.code = code;
    }
    
}

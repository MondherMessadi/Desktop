/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import package_entities.demande_maintenance;
import package_services.ServiceMaintenance;

/**
 * FXML Controller class
 *
 * @author maroo
 */
public class AjouterDemandeController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfNumtel;
    @FXML
    private TextField tfSujet;
    @FXML
    private TextField tfMessage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void ajouterDemande(ActionEvent event) throws IOException {
        String nom = tfNom.getText();
            String email = tfemail.getText();
            String message = tfMessage.getText();
            String sujet = tfSujet.getText();
            int numtel = Integer.parseInt(tfNumtel.getText());
        if (tfNom.getText().isEmpty() || tfemail.getText().isEmpty() || tfSujet.getText().isEmpty()|| tfMessage.getText().isEmpty()|| tfNumtel.getText().isEmpty()) {
            
            Alert a = new Alert(Alert.AlertType.ERROR, "Remplire tous Les champs", ButtonType.OK);
            a.showAndWait();
        }
        else if ((nom.length()<5)){
        Alert a = new Alert(Alert.AlertType.ERROR, "nom doit etere plus de 5 charactere", ButtonType.OK);
            a.showAndWait();
        }
        else if (email.matches("\\w{3,}")){
        Alert a = new Alert(Alert.AlertType.ERROR, "email doit etre exemple@gmail.com", ButtonType.OK);
            a.showAndWait();
        }
        else if (tfNumtel.getText().length()!=8){
        Alert a = new Alert(Alert.AlertType.ERROR, "numero doite etre de 8 chiffre", ButtonType.OK);
            a.showAndWait();
        }
        else if ((sujet.length()<5)){
        Alert a = new Alert(Alert.AlertType.ERROR, "Sujet doit etere plus de 5 charactere", ButtonType.OK);
            a.showAndWait();
        }
        else if ((message.length()<5)){
        Alert a = new Alert(Alert.AlertType.ERROR, "Sujet doit etere plus de 5 charactere", ButtonType.OK);
            a.showAndWait();
        }
        else {
            try {
                ServiceMaintenance sp = new ServiceMaintenance();
                //int numtel = Integer.parseInt(tfNumtel.getText());
                demande_maintenance p = new demande_maintenance(tfNom.getText(), tfemail.getText(),numtel, tfSujet.getText(), tfMessage.getText());
                sp.ajouter(p);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Votre demande a ete envoyer!", ButtonType.OK);
                a.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheDemande.fxml"));
                Parent root = loader.load();
                tfNom.getScene().setRoot(root);          
                AfficheDemandeController apc = loader.getController();
                apc.setNom(tfNom.getText());
                apc.setEmail(tfemail.getText());
                apc.setNumtel(numtel);
                apc.setSujet(tfSujet.getText());
                apc.setMessage(tfMessage.getText());
                
            } catch (SQLException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                a.showAndWait();
            }
        }
    
    }
}

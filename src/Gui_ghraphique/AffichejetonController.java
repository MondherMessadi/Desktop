/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import package_entities.Jetons;
import package_services.CRUD_Jetons;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AffichejetonController implements Initializable {

    @FXML
    private HBox hboxid;
    @FXML
    private ScrollPane spid;
    @FXML
    private AnchorPane apid;
    @FXML
    private GridPane gpid;
ObservableList<Jetons>list;
   private boolean order;
    @FXML
    private Button orderbyid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.order=true;
        CRUD_Jetons CRUD =new CRUD_Jetons();  
       list=CRUD.afficherJetontriee();
       int column =1;
       int row =0;
     try {  
                for(int i=0;i<list.size();i++)
              {
            //  FXMLLoader fxmlLoader = new FXMLLoader();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("jeton_card.fxml")); 

                       // fxmlLoader.setLocation(getClass(),getResource("/Source Package/Gui_ghraphique/jeton_card.fxml"));
                       AnchorPane anchorpane = loader.load();
                    
             Jeton_cardController itemcontroller=loader.getController();
             itemcontroller.setdata(list.get(i));
             if(column==5)
             {
             column=0;
             row++;
             }
             gpid.add(anchorpane,column++,row);
             GridPane.setMargin(anchorpane, new Insets(10));
             
              }
     }
      catch (IOException ex) {
                Logger.getLogger(AffichejetonController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }    

    @FXML
    private void orderby(ActionEvent event) {
        this.order=!this.order;
        if (this.order==true)
        {
             CRUD_Jetons CRUD =new CRUD_Jetons();  
       list=CRUD.afficherJetontriee();
       int column =1;
       int row =0;
     try {  
                for(int i=0;i<list.size();i++)
              {
            //  FXMLLoader fxmlLoader = new FXMLLoader();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("jeton_card.fxml")); 

                       // fxmlLoader.setLocation(getClass(),getResource("/Source Package/Gui_ghraphique/jeton_card.fxml"));
                       AnchorPane anchorpane = loader.load();
                    
             Jeton_cardController itemcontroller=loader.getController();
             itemcontroller.setdata(list.get(i));
             if(column==5)
             {
             column=0;
             row++;
             }
             gpid.add(anchorpane,column++,row);
             GridPane.setMargin(anchorpane, new Insets(10));
             
              }
     }
      catch (IOException ex) {
                Logger.getLogger(AffichejetonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
        CRUD_Jetons CRUD =new CRUD_Jetons();  
       list=CRUD.afficherJeton();
       int column =1;
       int row =0;
     try {  
                for(int i=0;i<list.size();i++)
              {
            //  FXMLLoader fxmlLoader = new FXMLLoader();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("jeton_card.fxml")); 

                       // fxmlLoader.setLocation(getClass(),getResource("/Source Package/Gui_ghraphique/jeton_card.fxml"));
                       AnchorPane anchorpane = loader.load();
                    
             Jeton_cardController itemcontroller=loader.getController();
             itemcontroller.setdata(list.get(i));
             if(column==5)
             {
             column=0;
             row++;
             }
             gpid.add(anchorpane,column++,row);
             GridPane.setMargin(anchorpane, new Insets(10));
             
              }
     }
      catch (IOException ex) {
                Logger.getLogger(AffichejetonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

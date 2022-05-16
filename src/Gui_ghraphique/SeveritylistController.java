/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import package_entities.severity;
import package_services.CRUD_Severity;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class SeveritylistController implements Initializable {

    @FXML
    private AnchorPane apid;
    @FXML
    private TableView<severity> tab;
    @FXML
    private TableColumn<severity, Integer> cid;
    @FXML
    private TableColumn<severity,String> cname;
    @FXML
    private TableColumn<severity, String> aid;
    @FXML
    private Button severitybuttonid;
    @FXML
    private Button tablebuttonid;
    @FXML
    private Button statbuttonid;
 ObservableList<severity>list;
  severity sev = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            
        
     CRUD_Severity CRUD =new CRUD_Severity(); 
       list=CRUD.afficherSeverity();
        System.out.println("ur list"+list);
        // TODO
        cid.setCellValueFactory(new PropertyValueFactory<severity,Integer>("id"));
        cname.setCellValueFactory(new PropertyValueFactory<severity,String>("name"));
        
        //Aid.setCellValueFactory(new Button("send email"));///////////////////////////////////
             Callback<TableColumn<severity, String>, TableCell<severity, String>> cellFoctory = (TableColumn<severity, String> param) -> {
            // make cell containing buttons
            final TableCell<severity, String> cell = new TableCell<severity, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                      Button deletebutton = new Button("delete");
                    
                      
     

                     
        /////////////////////////////* delete severity *///////////////////////////////////////
               deletebutton.setOnMouseClicked((MouseEvent event) -> {
                      sev = tab.getSelectionModel().getSelectedItem();
                     // System.out.println(rec);
                     
                     if(sev != null)
                            {  FXMLLoader loader = new FXMLLoader(getClass().getResource("Confirmdeleteseverity.fxml"));
                               try {
                               Parent root = loader.load(); 
                                   ConfirmdeleteseverityController CDC = loader.getController();
                                    CDC.setsev(sev);
                                    CDC.settext("voulez vous supprimer severity  : "+sev.getName());
                                    tab.getScene().setRoot(root);
                               } catch (IOException ex) {
                                   Logger.getLogger(ReclamationDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                               }
                     }
                    
                         
                     // CRUD.deleteRec(rec);
                   
                     
                      
                      });
            
            
      
                      
                      
                        HBox managebtn = new HBox(deletebutton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deletebutton, new Insets(2, 2, 0, 3));
                     

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        
        ///////////////////////////////////////////////////////////////////////////////////
        aid.setCellFactory(cellFoctory);    
        
              
            
        tab.setItems(list);
        
    }    

    @FXML
    private void severitybutton(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("severitylist"); 
                           tablebuttonid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }

    @FXML
    private void tablebutton(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("Reclamation details"); 
                           tablebuttonid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }

    @FXML
    private void statbutton(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("piechartseverity"); 
                           tablebuttonid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    
}

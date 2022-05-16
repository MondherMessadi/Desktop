/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import package_entities.demande_maintenance;
import package_entities.reponse;
import package_services.ServiceMaintenance;

/**
 * FXML Controller class
 *
 * @author maroo
 */
public class DdashboardController implements Initializable {
    
    @FXML
    private TableView<demande_maintenance> tab;
    @FXML
    private TableColumn<demande_maintenance, String> nid;
    @FXML
    private TableColumn<demande_maintenance, String> eid;
    @FXML
    private TableColumn<demande_maintenance, Integer> numid;
    @FXML
    private TableColumn<demande_maintenance, String> sid;
    @FXML
    private TableColumn<demande_maintenance, String> mid;
    @FXML
    private TableColumn<demande_maintenance, String> Aid;
    demande_maintenance dm = null;
    reponse r = null;
 ObservableList<demande_maintenance>list  = FXCollections.observableArrayList();;
    @FXML
    private TextField search;
    @FXML
    private Button TrieNom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              ServiceMaintenance CRUD =new ServiceMaintenance();  
       list=CRUD.getAll();
        
        // TODO
        nid.setCellValueFactory(new PropertyValueFactory<demande_maintenance,String>("nom"));
       eid.setCellValueFactory(new PropertyValueFactory<demande_maintenance,String>("email"));
        numid.setCellValueFactory(new PropertyValueFactory<demande_maintenance,Integer>("numtel"));
        sid.setCellValueFactory(new PropertyValueFactory<demande_maintenance,String>("sujet"));   
        mid.setCellValueFactory(new PropertyValueFactory<demande_maintenance,String>("message"));        //Aid.setCellValueFactory(new Button("send email"));///////////////////////////////////
             Callback<TableColumn<demande_maintenance, String>, TableCell<demande_maintenance, String>> cellFoctory = (TableColumn<demande_maintenance, String> param) -> {
            final TableCell<demande_maintenance, String> cell = new TableCell<demande_maintenance, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                      Button deletebutton = new Button("delete");
                       Button updatebutton = new Button("update");
                       Button Reponsebutton = new Button("Reponder");
                       Button pdfbtn = new Button("PDF");
                      
     
                      updatebutton.setOnMouseClicked((MouseEvent event) -> {
                     dm = tab.getSelectionModel().getSelectedItem();
                      System.out.println(dm);
                      
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("modifier.fxml"));
                        try {
                        Parent root = loader.load(); 
                            ModifierController ERC = loader.getController();
                             ERC.setelementtoupdate(dm);
                             tab.getScene().setRoot(root);
                        } catch (IOException ex) {
                          System.out.println("err "+ex.getMessage());
                        }
                      
                      
                      });
        
               deletebutton.setOnMouseClicked((MouseEvent event) -> {
                     dm = tab.getSelectionModel().getSelectedItem();
                     
                     if(dm != null)
                            {  FXMLLoader loader = new FXMLLoader(getClass().getResource("supprimerDemande.fxml"));
                               try {
                               Parent root = loader.load(); 
                                   SupprimerDemandeController CDC = loader.getController();
                                    CDC.setdm(dm);
                                    CDC.settext("voulez vous supprimer Le de demande de  : "+dm.getEmail());
                                    tab.getScene().setRoot(root);
                               } catch (IOException ex) {
                                 System.out.println("error :"+ex.getMessage());
                               }
                     }
                      });
                      
               pdfbtn.setOnMouseClicked((MouseEvent event) -> {
                     dm = tab.getSelectionModel().getSelectedItem();
                     String path="";
                     if(dm != null)
                            { 
                                Document doc=new Document();
                                try {
                                    PdfWriter.getInstance(doc, new FileOutputStream(path+"Demanade De Maintenace.pdf"));
                                    doc.open();
                                    PdfPTable tbl=new PdfPTable(5);
                                    tbl.addCell("Nom et Prenom");
                                    tbl.addCell("Email");
                                    tbl.addCell("numero de telephone");
                                    tbl.addCell("Sujet");
                                    tbl.addCell("Message");
                                    //doc.add(new Paragraph(tbl));
                                    /*for (int i=0;i< tab.getCo();i++)
                                    {
                                        String np=tab.getValueAt(i,0).toString();
                                        String em=tab.getValueAt(i,1).toString();
                                        String num=tab.getValueAt(i,2).toString();
                                        String sj=tab.getValueAt(i,3).toString();
                                        String mg=tab.getValueAt(i,4).toString();
                                        tbl.addCell(np);
                                        tbl.addCell(em);
                                        tbl.addCell(num);
                                        tbl.addCell(sj);
                                        tbl.addCell(mg);

                                    }*/
                                } catch (Exception e) {
                                }
                     }
                      });
            Reponsebutton.setOnMouseClicked((MouseEvent event) -> {
                     dm = tab.getSelectionModel().getSelectedItem();
                     
                     if(dm != null)
                            {  FXMLLoader loader = new FXMLLoader(getClass().getResource("EnvoyerReponse.fxml"));
                               try {
                               Parent root = loader.load(); 
                                   EnvoyerReponseController CDC = loader.getController();
                                    CDC.setdm(dm);
                                    tab.getScene().setRoot(root);
                               } catch (IOException ex) {
                                 System.out.println("error :"+ex.getMessage());
                               }
                     }
                      });
      
                      
                      
                        HBox managebtn = new HBox(updatebutton,deletebutton,Reponsebutton,pdfbtn);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deletebutton, new Insets(2, 2, 0, 3));
                        deletebutton.setStyle("-fx-background-color:#ff1c1c;-fx-text-fill:white");
                       HBox.setMargin(updatebutton, new Insets(2, 3, 0, 2));
                       updatebutton.setStyle("-fx-background-color:#1dad00;-fx-text-fill:white");
                       HBox.setMargin(Reponsebutton, new Insets(2, 4, 0, 1));
                       Reponsebutton.setStyle("-fx-background-color:#00c3ff;-fx-text-fill:white");
                       HBox.setMargin(pdfbtn, new Insets(2, 5, 0, 1));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        
        ///////////////////////////////////////////////////////////////////////////////////
        Aid.setCellFactory(cellFoctory);    
               
            
        
         
         
     tab.setItems(list);   
      
    }    

    @FXML
    private void TrieNom(ActionEvent event) {
        ServiceMaintenance vs=new ServiceMaintenance();
        ObservableList<demande_maintenance> observableList = null;
        observableList = FXCollections.observableArrayList(vs.TrieNom());
        tab.setItems(observableList);
        tab.setItems(observableList);
         nid.setCellValueFactory(new PropertyValueFactory<>("nom"));
       eid.setCellValueFactory(new PropertyValueFactory<>("email"));
        numid.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        sid.setCellValueFactory(new PropertyValueFactory<>("sujet"));   
        mid.setCellValueFactory(new PropertyValueFactory<>("message")); 
    }

    @FXML
    private void search(KeyEvent event) {
        FilteredList<demande_maintenance> filteredData = new FilteredList<>(list, b -> true);  
 search.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(demande -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
   
    if (demande.getNom().toLowerCase().contains(lowerCaseFilter) ) {
      return true;
    } else if (demande.getEmail().toLowerCase().contains(lowerCaseFilter)) {
      return true;
    } else if (String.valueOf(demande.getNumtel()).contains(lowerCaseFilter)) {
         return true;
    } else if (demande.getSujet().toLowerCase().contains(lowerCaseFilter)) {
      return true;
    } else if (demande.getMessage().toLowerCase().contains(lowerCaseFilter)) 
     return true;
        else  
          return false;
   });
  });  
  SortedList<demande_maintenance> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tab.comparatorProperty());  
  tab.setItems(sortedData);      
    }
      
}

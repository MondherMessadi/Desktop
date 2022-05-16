/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import package_entities.Reclamation;
import package_services.CRUD_Reclamation;
import javafx.util.Callback;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import package_entities.severity;
import package_tools.MyConnection;
/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class ReclamationDetailsController implements Initializable {

    @FXML
    private TableColumn<Reclamation, String> Eid;
    @FXML
    private TableColumn<Reclamation, String> phid;
    @FXML
    private TableColumn<Reclamation, String> Did;
    @FXML
    private TableColumn<severity, String> Sid;
    @FXML
    private TableColumn<Reclamation, String> Aid;
    @FXML
    private TextField textfieldid;
    @FXML
    private TableView<Reclamation> tab;
    @FXML
    private TableColumn<Reclamation, String> dateid;
    
    Reclamation rec = null;
 ObservableList<Reclamation>list;
 ObservableList<Reclamation>testlist;
    @FXML
    private AnchorPane apid;
    @FXML
    private Button tablebuttonid;
    @FXML
    private Button statbuttonid;
    @FXML
    private Button searchBid;
    @FXML
    private Button excelid;
    @FXML
    private Button severitybuttonid;
    /**
     * Initializes the controller class.
     */
       
 /*   */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
     CRUD_Reclamation CRUD =new CRUD_Reclamation(); 
       list=CRUD.afficherReclamation();
        System.out.println("ur list"+list);
        // TODO
        Eid.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("Email"));
        phid.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("phone_number"));
        Did.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("Description"));
        Sid.setCellValueFactory(new PropertyValueFactory<>("Severity"));   
        dateid.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("LastMod"));
        //Aid.setCellValueFactory(new Button("send email"));///////////////////////////////////
             Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctory = (TableColumn<Reclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                      Button deletebutton = new Button("delete");
                       Button responsebutton = new Button("response");
                      
     
         ////////////////////////////////response reclamation//////////////////////////////////
                      responsebutton.setOnMouseClicked((MouseEvent event) -> {
                      rec = tab.getSelectionModel().getSelectedItem();
                      System.out.println(rec);
                      
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("emailResponse.fxml"));
                        try {
                        Parent root = loader.load(); 
                            EmailResponseController ERC = loader.getController();
                             ERC.setTextid(rec.getEmail());
                             ERC.setreclamationid(rec.getId());
                             tab.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(ReclamationDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                      
                      
                      });
        /////////////////////////////* delete reclamation *///////////////////////////////////////
               deletebutton.setOnMouseClicked((MouseEvent event) -> {
                      rec = tab.getSelectionModel().getSelectedItem();
                     // System.out.println(rec);
                     System.out.println("testing rec ::::::::: "+rec);
                     if(rec != null)
                            {  FXMLLoader loader = new FXMLLoader(getClass().getResource("Confirmdelete.fxml"));
                               try {
                               Parent root = loader.load(); 
                                   ConfirmdeleteController CDC = loader.getController();
                                    CDC.setrec(rec);
                                    CDC.settext("voulez vous supprimer la reclamation d'id : "+rec.getId());
                                    tab.getScene().setRoot(root);
                               } catch (IOException ex) {
                                   Logger.getLogger(ReclamationDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                               }
                     }
                    
                         
                     // CRUD.deleteRec(rec);
                   
                     
                      
                      });
            
            
      
                      
                      
                        HBox managebtn = new HBox(responsebutton,deletebutton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deletebutton, new Insets(2, 2, 0, 3));
                       HBox.setMargin(responsebutton, new Insets(2, 3, 0, 2));

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
    //public void setTextid(String message){
   // this.textfieldid.setText(message);
    //}

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
  /*  private void search(ActionEvent event) {
    String x= this.textfieldid.getText();
    System.out.println("search :"+x);
    CRUD_Reclamation CRUD =new CRUD_Reclamation(); 
    if(x.length()>0)
    { 
 testlist=CRUD.Searchby(x);
  tab.setItems(testlist);
    }
    else{
   // CRUD.afficherReclamation();
    tab.setItems(list);
    }
    }*/
//////////////////////////////////////
     @FXML
       private void search(KeyEvent event) {
        FilteredList<Reclamation> filteredData = new FilteredList<>(list, b -> true);  
 textfieldid.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(rec -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
   
    if (rec.getEmail().toLowerCase().contains(lowerCaseFilter) ) {
      return true;
    } else if (rec.getDescription().toLowerCase().contains(lowerCaseFilter)) {
      return true;
    } else if (String.valueOf(rec.getPhone_number()).contains(lowerCaseFilter)) {
         return true;
    }
        else  
          return false;
   });
  });  
  SortedList<Reclamation> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tab.comparatorProperty());  
  tab.setItems(sortedData);      
    }
    //////////////////////////////////////////////////////
    @FXML
    private void downloadexcel(ActionEvent event) throws SQLException, FileNotFoundException, IOException  {
        
        //////////
        
           
           
            String requete = 
            "SELECT * FROM Reclamation";
                PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
      ResultSet rs =  pst.executeQuery(requete);
           
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Détails Reclamation");
            HSSFRow header = sheet.createRow(0);
           
           
           
            header.createCell(0).setCellValue("id");
            header.createCell(1).setCellValue("phone_number");
            header.createCell(2).setCellValue("Email");
            header.createCell(3).setCellValue("Description");
            header.createCell(4).setCellValue("LastMod");
                 header.createCell(5).setCellValue("etat");

           
            int index = 1;
            while(rs.next()){
                HSSFRow row = sheet.createRow(index);
               
                row.createCell(0).setCellValue(rs.getInt("id"));
                row.createCell(1).setCellValue(rs.getString("phone_number"));
                row.createCell(2).setCellValue(rs.getString("Email"));
                row.createCell(3).setCellValue(rs.getString("Description"));
                row.createCell(4).setCellValue(rs.getString("LastMod"));
                row.createCell(5).setCellValue(rs.getString("etat"));
                index++;
            }
           
            FileOutputStream file = new FileOutputStream("C:\\Users\\mahdi\\Desktop\\Reclamation.xls");
            wb.write(file);
            file.close();
            JOptionPane.showMessageDialog(null, "Exportation 'EXCEL' effectuée avec succés");
           
            pst.close();
            rs.close();
   
    
        
        ////////
        
        
    }

  

    
        
    
    
}

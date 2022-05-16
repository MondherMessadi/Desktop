/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import package_entities.Jetons;
import package_services.CRUD_Jetons;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AffichejetondashboardController implements Initializable {

    @FXML
    private TableView<Jetons> tab;
    @FXML
    private TableColumn<Jetons, String> nid;
    @FXML
    private TableColumn<Jetons, String> did;
    @FXML
    private TableColumn<Jetons, String> pid;
    @FXML
    private TableColumn<Jetons, String> cid;
    @FXML
    private TableColumn<Jetons, Integer> catid;
    @FXML
    private TableColumn<Jetons, String> Aid;
Jetons jet = null;
 ObservableList<Jetons>list;
    @FXML
    private Button addnewjetonid;
    @FXML
    private Button tablebuttonid;
    @FXML
    private Button statsbuttonid;
    @FXML
    private Button pdfid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      CRUD_Jetons CRUD =new CRUD_Jetons();  
       list=CRUD.afficherJeton();
        
        // TODO
        nid.setCellValueFactory(new PropertyValueFactory<Jetons,String>("Name"));
       pid.setCellValueFactory(new PropertyValueFactory<Jetons,String>("Price"));
        did.setCellValueFactory(new PropertyValueFactory<Jetons,String>("Description"));
        cid.setCellValueFactory(new PropertyValueFactory<Jetons,String>("clics"));   
        catid.setCellValueFactory(new PropertyValueFactory<Jetons,Integer>("category"));
        //Aid.setCellValueFactory(new Button("send email"));///////////////////////////////////
             Callback<TableColumn<Jetons, String>, TableCell<Jetons, String>> cellFoctory = (TableColumn<Jetons, String> param) -> {
            // make cell containing buttons
            final TableCell<Jetons, String> cell = new TableCell<Jetons, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                      Button deletebutton = new Button("delete");
                       Button updatebutton = new Button("update");
                      
     
         ////////////////////////////////update jeton//////////////////////////////////
                      updatebutton.setOnMouseClicked((MouseEvent event) -> {
                     jet = tab.getSelectionModel().getSelectedItem();
                      System.out.println(jet);
                      
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("updatejeton.fxml"));
                        try {
                        Parent root = loader.load(); 
                            UpdatejetonController ERC = loader.getController();
                             ERC.setelementtoupdate(jet);
                             tab.getScene().setRoot(root);
                        } catch (IOException ex) {
                          System.out.println("err "+ex.getMessage());
                        }
                      
                      
                      });
        /////////////////////////////* delete jeton *///////////////////////////////////////
               deletebutton.setOnMouseClicked((MouseEvent event) -> {
                     jet = tab.getSelectionModel().getSelectedItem();
                     // System.out.println(rec);
                    // System.out.println("testing rec ::::::::: "+rec);
                     if(jet != null)
                            {  FXMLLoader loader = new FXMLLoader(getClass().getResource("deletejeton.fxml"));
                               try {
                               Parent root = loader.load(); 
                                   DeletejetonController CDC = loader.getController();
                                    CDC.setjet(jet);
                                    CDC.settext("voulez vous supprimer jeton : "+jet.getClics());
                                    tab.getScene().setRoot(root);
                               } catch (IOException ex) {
                                 //  Logger.getLogger(ReclamationDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                                 System.out.println("error :"+ex.getMessage());
                               }
                     }
                    
                         
                     // CRUD.deleteRec(rec);
                   
                     
                      
                      });
            
            
      
                      
                      
                        HBox managebtn = new HBox(updatebutton,deletebutton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deletebutton, new Insets(2, 2, 0, 3));
                       HBox.setMargin(updatebutton, new Insets(2, 3, 0, 2));

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
    private void addnewjeton(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutjeton.fxml"));
                               try {
                               Parent root = loader.load(); 
                                 //  DeletejetonController CDC = loader.getController();
                                   // CDC.setjet(jet);
                                    //CDC.settext("voulez vous supprimer jeton : "+jet.getClics());
                                    tab.getScene().setRoot(root);
                               } catch (IOException ex) {
                                 //  Logger.getLogger(ReclamationDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                                 System.out.println("error :"+ex.getMessage());
                               }
    }

    @FXML
    private void table(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("affichejetondashboard"); 
                           tablebuttonid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }

    @FXML
    private void stats(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             SidebarController SBC = loader.getController();
                             SBC.loadPage("piechartcategory"); 
                           tablebuttonid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }

    }

    @FXML
    private void downloadpdf(ActionEvent event) {
        //////////////
        
        Document doc = new Document();
        //String FILE_NAME = "E:\\java_pdf\\chillyfacts.pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\mahdi\\Desktop\\jeton.pdf"));
            doc.open();
            Paragraph p = new Paragraph();
            p.add("List des jetons");
            p.setAlignment(Element.ALIGN_CENTER);
            
            
            doc.add(p);
            PdfPTable table = new PdfPTable(3); // 3 columns.
            table.setSpacingBefore(20f);

            PdfPCell cell1 = new PdfPCell(new Paragraph("name"));
             PdfPCell cell2 = new PdfPCell(new Paragraph("Description "));
            PdfPCell cell3 = new PdfPCell(new Paragraph("price "));
          
            cell1.setBackgroundColor(BaseColor.RED);
            cell2.setBackgroundColor(BaseColor.RED);
            cell3.setBackgroundColor(BaseColor.RED);
            cell1.setPadding(5);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
             CRUD_Jetons CRUD =new CRUD_Jetons();  
       list=CRUD.afficherJeton();
          
            for (int i=0; i<list.size();i++) {
                String nom=list.get(i).getName();
                String price=list.get(i).getPrice();
                String description=list.get(i).getDescription();
               // DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
               // String strDate = dateFormat.format(date); 
                table.addCell(nom);
                table.addCell(description);
                table.addCell(price);
            }
            doc.add(table);
            
            Desktop.getDesktop().open(new File("C:\\Users\\mahdi\\Desktop\\jeton.pdf"));
            
            doc.close();
            
            
        } catch (FileNotFoundException ex) {
           System.out.println("error");
        } catch (DocumentException ex) {
             System.out.println("error");
        } catch (IOException ex) {
            System.out.println("error");
        }
    
        
        
        ///////////
    }
    
}

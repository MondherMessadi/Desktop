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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import package_entities.Categorie;
import package_services.CRUD_Categorie;
import package_tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mondher
 */
public class AffichecategoriedashboardController implements Initializable {

    @FXML
    private TableView<Categorie> tab;
    @FXML
    private TableColumn<Categorie, String> nid;
    @FXML
    private TableColumn<Categorie, String> Aid;
    
    Categorie cat = null;
    
 ObservableList<Categorie>list;
 
    @FXML
    private Button addnewcategorieid;
    @FXML
    private TextField nameid;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CRUD_Categorie CRUD =new CRUD_Categorie();  
       list=CRUD.afficherCategorie();
       nid.setCellValueFactory(new PropertyValueFactory<Categorie, String>("Name"));
       
       Callback<TableColumn<Categorie, String>, TableCell<Categorie, String>> cellFoctory = (TableColumn<Categorie, String> param) -> {
            // make cell containing buttons
            final TableCell<Categorie, String> cell = new TableCell<Categorie, String>() {
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
                     cat = tab.getSelectionModel().getSelectedItem();
                     System.out.println(cat);
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                        try {
                        Parent root = loader.load(); 
                            SidebarController SBC = loader.getController();
                            SBC.loadPage("affichecategoriedashboard");
                             //setelementtoupdate(cat);
                             String name = nameid.getText();

                              CRUD_Categorie CRUD = new CRUD_Categorie();
                              cat.setName(name);
                              CRUD.updateCategorie(cat);
                              //clearFiled();
                              affiche();
                          
                     
                              tab.getScene().setRoot(root);
                        } catch (IOException ex) {
                          System.out.println("err "+ex.getMessage());
                        }
                     });
        /////////////////////////////* delete jeton *///////////////////////////////////////
                deletebutton.setOnMouseClicked((MouseEvent event) -> {
                     cat = tab.getSelectionModel().getSelectedItem();
                     // System.out.println(rec);
                    // System.out.println("testing rec ::::::::: "+rec);
                     
                             FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                               try {
                               Parent root = loader.load(); 
                                   SidebarController SBC = loader.getController();
                                    SBC.loadPage("affichecategoriedashboard");
                                    CRUD_Categorie CRUD = new CRUD_Categorie();
                                    CRUD.deleteCategorie(cat);
                                    affiche();
                                    tab.getScene().setRoot(root);
                               } catch (IOException ex) {
                                 //  Logger.getLogger(ReclamationDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                                 System.out.println("error :"+ex.getMessage());
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
    private void AddCategorie(ActionEvent event) {
        if(verifUserChamps() ){ 
                        if ( controlSaisie()){
        CRUD_Categorie CRUD = new CRUD_Categorie();
        String name = nameid.getText();
       if (nameid.getText().isEmpty() ){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Svp ecrire le nom du categorie !" , ButtonType.OK );
            a.showAndWait();
       }
       if(name.length()<2)
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Min 3 chractere !" , ButtonType.OK );
            a.showAndWait();
            }
        if(name.length()>=2 )  
            {
                Categorie c = new Categorie();
                c.setName(name);
                CRUD.ajouterCategorie2(c);
                affiche();
                clearFiled();
            }
        
    }
        }}
    
    
    public void affiche() {
               
      nid.setCellValueFactory(new PropertyValueFactory<>("name"));
      ObservableList<Categorie> obl =FXCollections.observableArrayList();
        obl=show1(); 
      tab.setItems(obl);
      System.out.println(""+obl);
                  
    }
    
    public ObservableList<Categorie> show1()
    { 
        try {
               ObservableList<Categorie> obl =FXCollections.observableArrayList();
                             Connection cnx = MyConnection.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select * FROM categorie");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Categorie ls = new Categorie();        
                 ls.setName(rs.getString("name"));
                  System.out.println("");
         obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    }

    
    public void clearFiled(){
        nameid.setText(null);
        
    }
    
      public void setelementtoupdate(Categorie c){
        this.cat=c;
    System.out.println("elemtn to update :"+ c.toString());
    this.nameid.setText(c.getName());
      }
    
      
       public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

       nameid.setStyle(styledefault);

       
        if ( nameid.getText().equals("")) {
             nameid.setStyle(style);
            verif = 1;
        }
 
       
        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show() ; 
        
        return false;
    }
       
       public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

        if(checkIfStringContainsNumber(nameid.getText())){
            alert.setContentText("Le nom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
        if(checkIfStringContainsSymbol(nameid.getText())){
            alert.setContentText("Le nom ne doit pas contenir des symboles");
            alert.showAndWait();
            return false;
        }
        return true;
        
       }
        
        
      
       public boolean checkIfNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
       
       return true;
    }
        public boolean checkIfSymbol(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
       
       return true;
    }
    
    public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9") ){
                return true;
            }
        }
        return false;
    }
    
    public boolean checkIfStringContainsSymbol(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("[") || str.contains("") || str.contains("'") || str.contains("-") || str.contains("]") || str.contains("_") || str.contains("}") || str.contains("{") || str.contains("8") || str.contains("9") || str.contains(",") ||str.contains(".") || str.contains("/") || str.contains("*") || str.contains("-") ||
                    str.contains("+") || str.contains("#") || str.contains("@") || str.contains("(") || str.contains(")") || str.contains("=") || str.contains("&") || str.contains("%") || str.contains("$") || str.contains("£") || str.contains("§") || str.contains("?") || str.contains("!") || str.contains(":")
                    || str.contains(";") || str.contains("~")){
                return true;
            }
        }
        return false;
    }
        
}

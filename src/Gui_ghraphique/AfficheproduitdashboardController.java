/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
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
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import package_entities.Categorie;
import package_entities.Produits;
import package_services.CRUD_Categorie;
import package_services.CRUD_Produits;
import package_tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mondher
 */
public class AfficheproduitdashboardController implements Initializable {

    @FXML
    private TableView<Produits> tab;
    @FXML
    private TableColumn<Produits, String> nid;
    @FXML
    private TableColumn<Categorie, String> catid;
    @FXML
    private TableColumn<Produits, Float> pid;
    @FXML
    private TableColumn<Produits, String> eid;
    @FXML
    private TableColumn<Produits, String> lid;
    @FXML
    private TableColumn<Produits, String> did;
    @FXML
    private TableColumn<Produits, String> iid;
    @FXML
    private TableColumn<Produits, String> emid;
    @FXML
    private TableColumn<Produits, String> epid;
    @FXML
    private TextField text_rech;
    @FXML
    private Button statid;
    @FXML
    private TableColumn mid;
    @FXML
    private TableColumn sid;
    private TableColumn<Produits, String> tv_iddelivery;
   
    ObservableList<Produits>list;
    Produits po = null;
    
    String from,to,host,sub,content;
    
    
    /**
     * Initializes the controller class.
     */
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         search_user();
        try {
            // TODO
            CRUD_Produits ds=new CRUD_Produits();
            
                ObservableList<Produits> observableList = null;
            observableList = FXCollections.observableArrayList(ds.displayAll());
            tab.setItems(observableList);
            
            tab.setItems(observableList);
            
            //tv_iddelivery.setCellValueFactory(new PropertyValueFactory<>("id_delivery"));
            nid.setCellValueFactory(new PropertyValueFactory<>("Titre"));
      catid.setCellValueFactory(new PropertyValueFactory<>("Cat"));
      pid.setCellValueFactory(new PropertyValueFactory<>("Prix"));
      eid.setCellValueFactory(new PropertyValueFactory<>("Etat"));
      lid.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
      did.setCellValueFactory(new PropertyValueFactory<>("Description"));
      iid.setCellValueFactory(new PropertyValueFactory<>("Image"));
      emid.setCellValueFactory(new PropertyValueFactory<>("Email"));
      epid.setCellValueFactory(new PropertyValueFactory<>("EtatProduit"));
        } catch (SQLException ex) {
            Logger.getLogger(AfficheproduitdashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /*Delete*/
        
         Callback<TableColumn<Produits, String>, TableCell<Produits, String>> cellFactory1 = (params) -> {
            final TableCell<Produits, String> cell = new TableCell<Produits, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                 
                        final Button deleteButton = new Button("Delete");
                       
                        deleteButton.setPrefSize(75,15);
                        deleteButton.setStyle("-fx-base: #e60000");
                        deleteButton.setOnAction(event -> {
                            Produits f = tab.getItems().get(getIndex());
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                            a.setContentText("Vous etes sur de supprimer " +f.getTitre()+" ?");
                            Optional<ButtonType> action=a.showAndWait();
                            if(action.get()==ButtonType.OK){
                                  CRUD_Produits vs;
                                  
                            try {
                                vs = new CRUD_Produits();
                                vs.delete(f.getId());
                                System.out.println(f);
                                tab.refresh();
                                from ="forsa.techniciens@gmail.com";
                                to =f.getEmail();
                                host="localhost";
                                String s ="FORSA Produit Supprimer";
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
                                    textBodypart.setText("Votre Produit à été Supprimer par l'administrateur Attention !!");
                                    MimeBodyPart pdfAttachement = new MimeBodyPart();
                                    pdfAttachement.attachFile("src/pdfproduit/Produit Supprimer.pdf");
                                    emailContent.addBodyPart(textBodypart);
                                    emailContent.addBodyPart(pdfAttachement);
                                    //m.setText(content);
                                    m.setContent(emailContent);
                                    Transport.send(m);
                                    System.out.println("email envoyer");
                                } catch (Exception e) {
                                     e.printStackTrace();
                                }
                                tab.refresh();

                                } catch (SQLException ex) {
                                    Logger.getLogger(AfficheproduitdashboardController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                 //tab.refresh();
                                }
                            

                        });
                        setGraphic(deleteButton);
                        setText(null);
                    }
                }
            };

            return cell;
        };
         /*Edit*/
         
         Callback<TableColumn<Produits, String>, TableCell<Produits, String>> cellFactory2= (params) -> {
            final TableCell<Produits, String> cell = new TableCell<Produits, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Image image = new Image(getClass().getResourceAsStream("edit.png"));
                        final Button editButton = new Button("Edit");
                        
                       //editButton.setGraphic(new ImageView(image));
                       editButton.setStyle("-fx-base: #78C1FF");
                       editButton.setPrefSize(55,15);
                        editButton.setOnMouseClicked((MouseEvent event) -> {
                             po = tab.getSelectionModel().getSelectedItem();
                      System.out.println(po);
                      
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("updateproduit.fxml"));
                        try {
                        Parent root = loader.load(); 
                            UpdateproduitController ERC = loader.getController();
                             ERC.setelementtoupdate(po);
                             tab.getScene().setRoot(root);
                        } catch (IOException ex) {
                          System.out.println("err "+ex.getMessage());
                        }
                      
                     

                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };
         
            return cell;
    }; 
        
        sid.setCellFactory(cellFactory1);
        mid.setCellFactory(cellFactory2);
        search_user();
                
        //col_edit.setCellFactory(cellFactory2);
                 }   

    
    /*
   
    public ObservableList<Produits> show1()
    { 
       

           try {
               ObservableList<Produits> obl =FXCollections.observableArrayList();
                             Connection cnx = MyConnection.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("SELECT * from produits p , categorie c where c.id=p.categorie_id");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Produits ls = new Produits();
                 Categorie c = new Categorie(rs.getInt("c.id"),rs.getString("c.name"));
                 
                 
                 
               
                 
                 System.out.print(c);
                 ls.setCat(c);
                
                 
                 //ls.setCategorie_id(rs.getInt("categorie_id"));
                 
                 ls.setTitre(rs.getString("Titre"));
                 ls.setPrix(rs.getFloat("prix"));
                 ls.setEtat(rs.getString("Etat"));
                 ls.setLieu(rs.getString("Lieu"));
                 ls.setDescription(rs.getString("description"));
                 ls.setImage(rs.getString("Image"));
                 ls.setEmail(rs.getString("email"));
                 ls.setEtatProduit(rs.getString("etat_produit"));
                 

                  System.out.println("");
                  obl.add(ls);
                             }
                             return obl;

                         } catch (SQLException ex) {
                           System.out.println("Erreur"+ex);
                         }
                      return null;
               } 
    
    public void affiche() {
        
               
      nid.setCellValueFactory(new PropertyValueFactory<>("Titre"));
      catid.setCellValueFactory(new PropertyValueFactory<>("Cat"));
      pid.setCellValueFactory(new PropertyValueFactory<>("Prix"));
      eid.setCellValueFactory(new PropertyValueFactory<>("Etat"));
      lid.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
      did.setCellValueFactory(new PropertyValueFactory<>("Description"));
      iid.setCellValueFactory(new PropertyValueFactory<>("Image"));
      emid.setCellValueFactory(new PropertyValueFactory<>("Email"));
      epid.setCellValueFactory(new PropertyValueFactory<>("EtatProduit"));
      ObservableList<Produits> obl =FXCollections.observableArrayList();
      obl=show1(); 
      tab.setItems(obl);
      System.out.println(""+obl);

                      
    }

*/
    
    
    void search_user() {          
         nid.setCellValueFactory(new PropertyValueFactory<>("Titre"));
      catid.setCellValueFactory(new PropertyValueFactory<>("Cat"));
      pid.setCellValueFactory(new PropertyValueFactory<>("Prix"));
      eid.setCellValueFactory(new PropertyValueFactory<>("Etat"));
      lid.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
      did.setCellValueFactory(new PropertyValueFactory<>("Description"));
      iid.setCellValueFactory(new PropertyValueFactory<>("Image"));
      emid.setCellValueFactory(new PropertyValueFactory<>("Email"));
      epid.setCellValueFactory(new PropertyValueFactory<>("EtatProduit"));
     
       
        CRUD_Produits ser= new CRUD_Produits();
       
        List<Produits> li =ser.ListClasse();
        ObservableList<Produits> data = FXCollections.observableArrayList(li);
                tab.setItems(data);

        FilteredList<Produits> filteredData = new FilteredList<>(data, b -> true);  
 text_rech.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
   
    if (person.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username                 
    }else  
          return false; // Does not match.
   });
  });  
  SortedList<Produits> sortedData = new SortedList<Produits>(filteredData);  
  sortedData.comparatorProperty().bind(tab.comparatorProperty());  
  tab.setItems(sortedData);      
    }
 
    @FXML
    private void Stat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml")); 
                      try {
                        Parent root = loader.load();
                            statid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }

   

    
    
    
   

    
    
    
}

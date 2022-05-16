/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;
import package_entities.Categorie;
import package_entities.Produits;
import package_services.CRUD_Produits;
import package_tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mondher
 */
public class AjoutproduitController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfEtat;
    @FXML
    private TextField tfLieu;
    @FXML
    private TextArea tfDescriptin;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfImage;
    @FXML
    private ComboBox<Categorie> tfCategorieId;
    @FXML
    private TextField tfEtatP;

    ResultSet rs;
    Statement st;
    PreparedStatement pst;
    private Connection cnx;
    Categorie cat ;
    int id=0;
    
    final ObservableList<Categorie> options= FXCollections.observableArrayList();
    
    //Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private ImageView uploadIm;
    
    String uploads = "D:\\FORSA\\public\\layout\\img\\";
    private String path = "", imgname = "", fn="";
    @FXML
    private Button ajoutid;
    @FXML
    private Button retourid;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            fillComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    public void fillComboBox() throws SQLException
    {
       
        try {
            List<Categorie> list=new ArrayList<>();
            cnx=MyConnection.getInstance().getCnx();
            String query="select * from categorie";
            
            pst=cnx.prepareStatement(query);
                  //  pst.setString(1, query);
            rs=pst.executeQuery();
            while(rs.next())
            {
//                int i=rs.getInt("id_vehicule");   
//                String st=Integer.toString(i);
//                System.out.println(st);
//                options.add(st);
                cat =new Categorie(rs.getInt(1),rs.getString(2)); 
                list.add(cat);
                
            }
            options.addAll(list);
             tfCategorieId.getItems().setAll(list);

            System.out.println("aaa");
            System.out.println(options);
        } catch (SQLException ex) {
            Logger.getLogger(AjoutproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query1="select id from categorie where name=?";
            
            pst=cnx.prepareStatement(query1);
            pst.setString(1,cat.getName());
            
            rs=pst.executeQuery();
             while(rs.next())
            {
                id=rs.getInt(1);
            }
    }
    
    
    
    
  

    @FXML
    private void ajouterProduit(ActionEvent event) throws SQLException {
        
       
        
        Image pho = new Image("/SmallT.jpg");
        
                        if ( controlSaisie()){
        float Prix = Float.parseFloat(tfPrix.getText());
        
        String etatProduit = (tfEtatP.getText());
    
        CRUD_Produits sp = new CRUD_Produits();
             Categorie cat=tfCategorieId.getItems().get(tfCategorieId.getSelectionModel().getSelectedIndex());

        //int idveh = Integer.parseInt(txt_idvehicuel.getText());
        sp.ajouter2(new Produits(  cat , tfTitre.getText(), tfEtat.getText(), tfLieu.getText(), tfDescriptin.getText(), imgname , tfEmail.getText() , etatProduit , Prix));
    
        
          Notifications notificationBuilder = Notifications.create()
                                                     .title("Produit Ajouter")
                                                      .text("Produit ajouter avec succée")
                                                     .graphic(new ImageView(pho))
                                                       
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_RIGHT) ;
         notificationBuilder.show();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpage.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             MainpageController SBC = loader.getController();
                             SBC.loadPage("frontproduits"); 
                           retourid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    
                        }

    }

    @FXML
    private void AddIm(ActionEvent event) throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        path= f.getAbsolutePath();
        imgname = f.getName();
        //tfImage.setText(imgname);
        fn = imgname;
        Image getAbsolutePath = null;

        String dd = uploads + f.getName();
        File dest = new File(dd);
        this.copyFile(f, dest);

        System.out.println(dd);
        

        uploadIm.setImage(new Image("file:" + dest.getAbsolutePath()));
        
    }
    
    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        try (
                FileChannel in = new FileInputStream(sourceFile).getChannel();
                FileChannel out = new FileOutputStream(destFile).getChannel();) {

            out.transferFrom(in, 0, in.size());
        }
    }
    
    
    

    
    
    public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

       tfTitre.setStyle(styledefault);

       
        if ( tfTitre.getText().equals("")) {
             tfTitre.setStyle(style);
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
         

        if(checkIfStringContainsNumber(tfTitre.getText())){
            alert.setContentText("Le nom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
        if(checkIfStringContainsSymbol(tfTitre.getText())){
            alert.setContentText("Le nom ne doit pas contenir des symboles");
            alert.showAndWait();
            return false;
        }
        if(checkIfStringContainsNumber(tfEtat.getText())){
            alert.setContentText("L'Etat ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
        if(checkIfStringContainsSymbol(tfEtat.getText())){
            alert.setContentText("L'Etat ne doit pas contenir des symboles");
            alert.showAndWait();
            return false;
        }
        if(checkIfStringContainsSymbol(tfLieu.getText())){
            alert.setContentText("Le lieu ne doit pas contenir des symboles");
            alert.showAndWait();
            return false;
        }
        if(checkIfStringContainsNumber(tfLieu.getText())){
            alert.setContentText("Le lieu ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
        if(checkIfStringContainsSymbol(tfDescriptin.getText())){
            alert.setContentText("La descriptionne doit pas contenir des symboles");
            alert.showAndWait();
            return false;
        }
        if(checkIfStringContainsLettre(tfPrix.getText())){
            alert.setContentText("Le prix ne doit pas contenir des lettres");
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
        public boolean checkIfLettre(){
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
            if(str.contains("[") || str.contains("'") || str.contains("-") || str.contains("]") || str.contains("_") || str.contains("}") || str.contains("{") || str.contains("8") || str.contains("9") || str.contains(",") ||str.contains(".") || str.contains("/") || str.contains("*") || str.contains("-") ||
                    str.contains("+") || str.contains("#") || str.contains("@") || str.contains("(") || str.contains(")") || str.contains("=") || str.contains("&") || str.contains("%") || str.contains("$") || str.contains("£") || str.contains("§") || str.contains("?") || str.contains("!") || str.contains(":")
                    || str.contains(";") || str.contains("~")){
                return true;
            }
        }
        return false;
    }
    public boolean checkIfStringContainsLettre(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("A") || str.contains("B") || str.contains("C") || str.contains("D") || str.contains("E") || str.contains("F") || str.contains("G") || str.contains("H") || str.contains("I") ||
                str.contains("J") || str.contains("K") || str.contains("L") || str.contains("M") || str.contains("N") || str.contains("O") || str.contains("P") || str.contains("Q") || str.contains("R") || str.contains("S") || 
                    str.contains("T") || str.contains("U") || str.contains("V") || str.contains("W") || str.contains("X") || str.contains("Y") || str.contains("Z") || str.contains("a") || str.contains("b") || str.contains("c") ||
                    str.contains("d") || str.contains("u") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("i") || str.contains("j") ||  str.contains("k") || str.contains("l") || str.contains("m") || 
                    str.contains("n") || str.contains("o") || str.contains("p") || str.contains("q") || str.contains("r") || str.contains("s") || str.contains("t") || 
                    str.contains("u") || str.contains("v") || str.contains("w") || str.contains("x") || str.contains("y") || str.contains("z") || str.contains("Z")
                    ){
                return true;
            }
        }
        return false;
    }

    @FXML
    private void retouridf(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpage.fxml"));
                        try {
                        Parent root = loader.load(); 
                           // EmailResponseController ERC = loader.getController();
                             //ERC.setTextid(rec.getEmail());
                             MainpageController SBC = loader.getController();
                             SBC.loadPage("frontproduits"); 
                           retourid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    
    
    
}

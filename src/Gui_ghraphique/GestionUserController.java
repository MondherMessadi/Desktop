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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static java.util.Collections.list;
import static java.util.Collections.sort;
import static java.util.Collections.sort;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import package_entities.Users;
import package_services.UsersService;
import package_tools.MyConnection;
import sun.print.resources.serviceui;

/**
 * FXML Controller class
 *
 * @author HAMOUDA
 */
public class GestionUserController implements Initializable {

    @FXML
    private TableColumn<Users, String> colnom;
    @FXML
    private TableColumn<Users, String> colprenom;
    @FXML
    private TableColumn<Users, String> colgenre;
    @FXML
    private TableColumn<Users, String> colemail;
    @FXML
    private TableColumn<Users, String> colmdp;
    @FXML
    private TextField ftnom;
    @FXML
    private TextField ftprenom;
    @FXML
    private TextField ftgenre;
    @FXML
    private TextField ftemail;
    @FXML
    private TableView<Users> utilisateur;

    private UsersService US = new UsersService();
    ObservableList<Users> userslist;
    int id =0;
    @FXML
    private Button gestionuser;
    @FXML
    private TextField ftsearch;
     UsersService UP = new UsersService();
    private ObservableList<Users> getTableList() {
       
        ObservableList<Users> List = UP.getAll();
        return List ;
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colnom.setCellValueFactory(new PropertyValueFactory<Users, String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Users, String>("prenom"));
        colgenre.setCellValueFactory(new PropertyValueFactory<Users, String>("genre"));
        colemail.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
        colmdp.setCellValueFactory(new PropertyValueFactory<Users, String>("password"));
        
        refresh();
        
    }
    
    private void refresh (){
        userslist=US.getAll();
        utilisateur.setItems(userslist);
    }

    @FXML
    private void ajouter(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
        Users user = new Users(id,ftemail.getText(),ftgenre.getText(),ftnom.getText(),ftprenom.getText());
         US.modifier(user);
         refresh();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Users user = utilisateur.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.INFORMATION, "vous ete sure ? !", ButtonType.OK);
        a.showAndWait();
        US.supprimerU(user.getId());
        
           refresh();
    }

    @FXML
    private void rowClicked(MouseEvent event) {
        Users user = utilisateur.getSelectionModel().getSelectedItem();
        id=user.getId();
        ftnom.setText(user.getNom());
        ftprenom.setText(user.getPrenom());
        ftgenre.setText(user.getGenre());
        ftemail.setText(user.getEmail());
        
    }

    @FXML
    private void btn_pdf(ActionEvent event) {
        Document doc = new Document();
        String FILE_NAME = "E:\\java_pdf\\chillyfacts.pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\HAMOUDA\\Documents\\NetBeansProjects\\Forsa\\src\\User.pdf"));
            doc.open();
            Paragraph p = new Paragraph();
            p.add("Foodine");
            p.setAlignment(Element.ALIGN_CENTER);
            
            
            doc.add(p);
            PdfPTable table = new PdfPTable(3); // 2 columns.
            table.setSpacingBefore(20f);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Nom "));
            PdfPCell cell2 = new PdfPCell(new Paragraph("prenom"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("email"));
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell3.setBackgroundColor(BaseColor.GRAY);

            cell1.setPadding(5);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            userslist = US.getAll();
            for (int i=0; i<userslist.size();i++) {
                String nom=userslist.get(i).getNom();
                String prenom=userslist.get(i).getPrenom();
                String email=userslist.get(i).getEmail();

             
                table.addCell(nom);
                table.addCell(prenom);
                table.addCell(email);
            }
            doc.add(table);
            
            Desktop.getDesktop().open(new File("C:\\Users\\HAMOUDA\\Documents\\NetBeansProjects\\Forsa\\src\\User.pdf"));
            
            doc.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
FilteredList<Users> filter = new FilteredList<>(getTableList(), e -> true);
    SortedList<Users> sort = new SortedList<>(filter);
    @FXML
    private void search(javafx.scene.input.KeyEvent event) {
        ftsearch.setOnKeyReleased(e -> {
                  

            ftsearch.textProperty().addListener((observable, oldValue, newValue) -> {
               filter.setPredicate(t -> {
                   if (newValue == null || newValue.isEmpty()) {
                       return true;
                   }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(t.getNom()).toLowerCase().contains(lowerCaseFilter) ) {
                        return true;
                    } else {
                        return false;
                   }
                });
            });
               
            sort.comparatorProperty().bind(utilisateur.comparatorProperty());
          utilisateur.setItems(sort);
      });
    }

    @FXML
    private void btn_excel(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
               Connection con = MyConnection.getInstance().getCnx();        
            String query = "Select * from users";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
           
           
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Détails users");
            HSSFRow header = sheet.createRow(0);
           
           
           
            header.createCell(0).setCellValue("ID");
           header.createCell(1).setCellValue("nom");
            header.createCell(2).setCellValue("prenom");
            header.createCell(3).setCellValue("email");
             

           
            int index = 1;
            while(rs.next()){
                HSSFRow row = sheet.createRow(index);
               
                row.createCell(0).setCellValue(rs.getInt("id"));
                row.createCell(1).setCellValue(rs.getString("nom"));
                row.createCell(2).setCellValue(rs.getString("prenom"));
                row.createCell(3).setCellValue(rs.getString("email"));
                index++;
            }
           
            FileOutputStream file = new FileOutputStream("C:\\Users\\HAMOUDA\\Documents\\NetBeansProjects\\Forsa\\Evenement.xls");
            wb.write(file);
            file.close();
            JOptionPane.showMessageDialog(null, "Exportation 'EXCEL' effectuée avec succés");
           
            pst.close();
            rs.close();
   
    }
    }



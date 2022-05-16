/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import package_services.CRUD_Jetons;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class PiechartcategoryController implements Initializable {

    @FXML
    private Button tabid;
    @FXML
    private Button statid;
    @FXML
    private PieChart pichartid;
    @FXML
    private Label percentid;
Map<String, Integer> stats;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               CRUD_Jetons CRUD =new CRUD_Jetons();  
   stats= CRUD.piechart1();
       
        ObservableList<PieChart.Data> pieChartData=
                FXCollections.observableArrayList(
                        new PieChart.Data("category 1", stats.get("category 1")),
                        new PieChart.Data("category 2", stats.get("category 2")), 
                         new PieChart.Data("category 3", stats.get("category 3"))
                      );
     pichartid.setData(pieChartData);
     pichartid.setTitle("category piechart");
     percentid.setTextFill(Color.BLACK);
     for (final PieChart.Data data :pichartid.getData()){
         data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                 new EventHandler<MouseEvent>(){
                 @Override public void handle(MouseEvent e){
                 //System.out.println("mouse clicked");
                 percentid.setTranslateX(e.getSceneX()-percentid.getLayoutX());
                 percentid.setTranslateY(e.getSceneY()-percentid.getLayoutY());
                 percentid.setText(String.valueOf(data.getPieValue())); 
                 
                 }
                 }
                 );
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
                           tabid.getScene().setRoot(root);
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
                           tabid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    
}

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
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import package_services.CRUD_Reclamation;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class PiechartseverityController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private PieChart piechart2;
    @FXML
    private Button tablebuttonid;
    @FXML
    private Button statbuttonid;
    
Map<String, Integer> stats;
Map<String, Integer> stats2;
    @FXML
    private Label percentlabel;
    @FXML
    private Label purcentlabel2;
    @FXML
    private Button severitybuttonid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          CRUD_Reclamation CRUD =new CRUD_Reclamation();  
   stats= CRUD.piechart1();
       
        ObservableList<PieChart.Data> pieChartData=
                FXCollections.observableArrayList(
                        new PieChart.Data("Normal", stats.get("Normal")),
                        new PieChart.Data("Average", stats.get("Average")), 
                         new PieChart.Data("high", stats.get("high"))
                      );
     piechart.setData(pieChartData);
     piechart.setTitle("Severity purcentage");
     percentlabel.setTextFill(Color.BLACK);
     for (final PieChart.Data data :piechart.getData()){
         data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                 new EventHandler<MouseEvent>(){
                 @Override public void handle(MouseEvent e){
                 //System.out.println("mouse clicked");
                 percentlabel.setTranslateX(e.getSceneX()-percentlabel.getLayoutX());
                 percentlabel.setTranslateY(e.getSceneY()-percentlabel.getLayoutY());
                 percentlabel.setText(String.valueOf(data.getPieValue())); 
                 
                 }
                 }
                 );
     }
     
     
     
     //////////////////////////////////
     stats2=CRUD.piechart2();
      ObservableList<PieChart.Data> pieChartData2=
                FXCollections.observableArrayList(
                new PieChart.Data("responded",stats2.get("responded")),
                new PieChart.Data("pending",stats2.get("pending"))
                 );
     piechart2.setData(pieChartData2);
        piechart2.setTitle("Email response stats");
      purcentlabel2.setTextFill(Color.BLACK);
     for (final PieChart.Data data2 :piechart2.getData()){
         data2.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                 new EventHandler<MouseEvent>(){
                 @Override public void handle(MouseEvent e){
                 //System.out.println("mouse clicked");
                 purcentlabel2.setTranslateX(e.getSceneX()-purcentlabel2.getLayoutX());
                 purcentlabel2.setTranslateY(e.getSceneY()-purcentlabel2.getLayoutY());
                 purcentlabel2.setText(String.valueOf(data2.getPieValue())); 
                 
                 }
                 }
                 );
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
    
}

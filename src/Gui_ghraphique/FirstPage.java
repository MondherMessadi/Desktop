/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mahdi
 */
public class FirstPage extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {         
           // Parent root = FXMLLoader.load(getClass().getResource("Reclamation.fxml")) ;    
           Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml")) ;
            Scene scene = new Scene(root,1500,900);  
           // primaryStage.setTitle("envoyer une reclamation");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
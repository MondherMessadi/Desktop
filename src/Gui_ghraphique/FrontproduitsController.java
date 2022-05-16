/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.MyListener;
import package_entities.Produits;
import package_services.CRUD_Produits;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FrontproduitsController implements Initializable {

    
             String uploads = "D:\\FORSA\\public\\layout\\img\\";

    @FXML
    private TextField tfRecherche;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Label DESC;
        private MyListener myListener;

    List<Produits> Produits = new ArrayList<>();

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    CRUD_Produits sp = new CRUD_Produits();
    private Button retourid;
    @FXML
    private Button ajouterpid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        // TODO

         Produits = sp.afficherP();
        if (Produits.size() > 0) {
            setChosenFruit(Produits.get(0));
            
            myListener = new MyListener() {
            @Override
                public void onClickListener(Produits Produits) {
                    setChosenFruit(Produits);
                }     
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < Produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

        ItemController itemController = fxmlLoader.getController();
                itemController.setData(Produits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    
    
    
     private void setChosenFruit(Produits Produits) {
        fruitNameLable.setText(Produits.getTitre());
        fruitPriceLabel.setText("" + Produits.getPrix());
        DESC.setText(Produits.getDescription());
     fruitImg.setImage(new Image("file:" + uploads + Produits.getImage()));
    }

    @FXML
    private void rechercheProd(MouseEvent event) {
    }

    @FXML
    private void DESC(MouseEvent event) {
    }

    @FXML
    private void ASC(MouseEvent event) {
    }

    

    @FXML
    private void ajouterp(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutproduit.fxml")); 
                      try {
                        Parent root = loader.load();
                            ajouterpid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
    }
    
    

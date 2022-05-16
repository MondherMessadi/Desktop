/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_ghraphique;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.MyListener;
import package_entities.Produits;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    
    
        private Produits Produits;
    private MyListener myListener;

     String uploads = "D:\\FORSA\\public\\layout\\img\\";
    private String path = "", imgname = "", fn="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
     
     public void setData(Produits Produits, MyListener myListener) {
        this.Produits = Produits;
        this.myListener = myListener;
        nameLabel.setText(""+Produits.getTitre());
        priceLable.setText(""+ Produits.getPrix());
      img.setImage(new Image("file:" + uploads + Produits.getImage()));
     }

    @FXML
    private void click(MouseEvent event) {
            myListener.onClickListener(Produits);

    }
    
}

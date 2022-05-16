/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_entities;

import java.util.Objects;

/**
 *
 * @author Mondher
 */
public class Produits {
   private int id ;
    private Categorie cat ;
    private String Titre, Etat, Lieu, Description, image, Email, etatProduit;
    private float Prix ;

    public Produits() {
    }

    public Produits(int id, Categorie cat, String Titre, String Etat, String Lieu, String Description, String image, String Email, String etatProduit, float Prix) {
        this.id = id;
        this.cat = cat;
        this.Titre = Titre;
        this.Etat = Etat;
        this.Lieu = Lieu;
        this.Description = Description;
        this.image = image;
        this.Email = Email;
        this.etatProduit = etatProduit;
        this.Prix = Prix;
    }

    public Produits(Categorie cat, String Titre, String Etat, String Lieu, String Description, String image, String Email, String etatProduit, float Prix) {
        this.cat = cat;
        this.Titre = Titre;
        this.Etat = Etat;
        this.Lieu = Lieu;
        this.Description = Description;
        this.image = image;
        this.Email = Email;
        this.etatProduit = etatProduit;
        this.Prix = Prix;
    }

    public Produits(String Titre, String Description, String image, float Prix) {
        this.Titre = Titre;
        this.Description = Description;
        this.image = image;
        this.Prix = Prix;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEtatProduit() {
        return etatProduit;
    }

    public void setEtatProduit(String etatProduit) {
        this.etatProduit = etatProduit;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    @Override
    public String toString() {
        return "Produits{" + "id=" + id + ", cat=" + cat + ", Titre=" + Titre + ", Etat=" + Etat + ", Lieu=" + Lieu + ", Description=" + Description + ", image=" + image + ", Email=" + Email + ", etatProduit=" + etatProduit + ", Prix=" + Prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produits other = (Produits) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.Prix) != Float.floatToIntBits(other.Prix)) {
            return false;
        }
        if (!Objects.equals(this.Titre, other.Titre)) {
            return false;
        }
        if (!Objects.equals(this.Etat, other.Etat)) {
            return false;
        }
        if (!Objects.equals(this.Lieu, other.Lieu)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        if (!Objects.equals(this.etatProduit, other.etatProduit)) {
            return false;
        }
        if (!Objects.equals(this.cat, other.cat)) {
            return false;
        }
        return true;
    }

   
    
    
    
    
}

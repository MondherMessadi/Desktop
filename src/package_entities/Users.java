/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_entities;


import java.util.Objects;

/**
 *
 * @author HAMOUDA
 */
public class Users {
    private int id; 
    private String email,role,password,activation_token,reset_token,genre,nom,prenom,image;

    public Users(int id, String email, String role, String password,  String genre, String nom, String prenom, String image) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
        this.genre = genre;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
    }

    public Users(int id, String email, String role, String password, String activation_token, String reset_token, String genre, String nom, String prenom, String image) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
        this.activation_token = activation_token;
        this.reset_token = reset_token;
        this.genre = genre;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
    }
    

    public Users(String email, String role, String password, String activation_token, String reset_token, String genre, String nom, String prenom, String image) {
        this.email = email;
        this.role = role;
        this.password = password;
        this.activation_token = activation_token;
        this.reset_token = reset_token;
        this.genre = genre;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
    }

    public Users(int id, String email, String password, String genre, String nom, String prenom, String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.genre = genre;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
    }

    public Users(String email, String role, String password, String genre, String nom, String prenom) {
        this.email = email;
        this.role = role;
         this.password = password;
        this.genre = genre;
        this.nom = nom;
        this.prenom = prenom;
       
    }

    public Users(String email, String role, String password, String genre, String nom, String prenom, String image) {
        this.email = email;
        this.role = role;
        this.password = password;
        this.genre = genre;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
    }

    public Users(int id, String email, String genre, String nom, String prenom) {
        this.id = id;
        this.email = email;
        this.genre = genre;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Users(String role) {
        this.role = role;
    }

    public Users() {
    }

  
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.email);
        hash = 13 * hash + Objects.hashCode(this.role);
        hash = 13 * hash + Objects.hashCode(this.password);
        hash = 13 * hash + Objects.hashCode(this.activation_token);
        hash = 13 * hash + Objects.hashCode(this.reset_token);
        hash = 13 * hash + Objects.hashCode(this.genre);
        hash = 13 * hash + Objects.hashCode(this.nom);
        hash = 13 * hash + Objects.hashCode(this.prenom);
        hash = 13 * hash + Objects.hashCode(this.image);
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
        final Users other = (Users) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.activation_token, other.activation_token)) {
            return false;
        }
        if (!Objects.equals(this.reset_token, other.reset_token)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", email=" + email + ", role=" + role + ", password=" + password + ", activation_token=" + activation_token + ", reset_token=" + reset_token + ", genre=" + genre + ", nom=" + nom + ", prenom=" + prenom + ", image=" + image + '}';
    }
    
    
    
}

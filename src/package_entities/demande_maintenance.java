/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_entities;

import java.util.Objects;


/**
 *
 * @author maroo
 */
public class demande_maintenance {
    private int id,numtel;
    private String nom, email,sujet,message;
    
    public demande_maintenance() {
    }
    public demande_maintenance(String nom, String email, int numtel, String sujet, String message) {
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
        this.sujet = sujet;
        this.message = message;
    }
    public demande_maintenance(int id, String nom, String email, int numtel, String sujet, String message) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
        this.sujet = sujet;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public int getNumtel() {
        return numtel;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getSujet() {
        return sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "demande_maintenance{ "+ "nom=" + nom + ", email=" + email + ", numtel=" + numtel + ", sujet=" + sujet + ", message=" + message + '}';
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
        final demande_maintenance other = (demande_maintenance) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.numtel != other.numtel) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }

}
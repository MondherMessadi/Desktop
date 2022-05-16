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
public class reponse {
    private int id,demande;
    private String subject, mail,object;
    
    
    public reponse() {
    }

    public reponse(String subject, String mail, String object) {
        this.subject = subject;
        this.mail = mail;
        this.object = object;
        ;
    }
   
    public reponse(int id, String subject, String mail, String object, int demande) {
        this.id = id;              
        this.subject = subject;
        this.mail = mail;
        this.object = object;
        this.demande = demande;
    }

    public int getId() {
        return id;
    }

    public int getdemande() {
        return demande;
    }


    public String getSubject() {
        return subject;
    }

    public String getMail() {
        return mail;
    }

    public String getObject() {
        return object;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setdemande(int demande) {
        this.demande = demande;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "reponse{" + "id=" + id +  ", subject=" + subject + ", mail=" + mail + ", object=" + object + ", demande=" + demande  +'}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final reponse other = (reponse) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.demande != other.demande) {
            return false;
        }
        
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.object, other.object)) {
            return false;
        }
        return true;
    }
    
}

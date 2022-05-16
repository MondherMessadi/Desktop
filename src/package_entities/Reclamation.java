/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package package_entities;

public class Reclamation {  
    Integer id;
    String phone_number;
    String Email;
    String Description;
    private severity Severity;
    String LastMod;
    Integer etat;

    public severity getSeverity() {
        return Severity;
    }

    public void setSeverity(severity Severity) {
        this.Severity = Severity;
    }

    public Reclamation(Integer id, String phone_number, String Email, String Description, severity Severity, String LastMod, Integer etat) {
        this.id = id;
        this.phone_number = phone_number;
        this.Email = Email;
        this.Description = Description;
        this.Severity = Severity;
        this.LastMod = LastMod;
        this.etat = etat;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

 

    public Reclamation() {
    }

    public Reclamation(String phone_number, String Email, String Description, severity Severity, String LastMod, Integer etat ) {
        this.phone_number = phone_number;
        this.Email = Email;
        this.Description = Description;
        this.Severity = Severity;
        this.LastMod = LastMod;
          this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", phone_number=" + phone_number + ", Email=" + Email + ", Description=" + Description + ", Severity=" + Severity + ", LastMod=" + LastMod + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

  

    public String getLastMod() {
        return LastMod;
    }

    public void setLastMod(String LastMod) {
        this.LastMod = LastMod;
    }

    
}

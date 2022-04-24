/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_entities;

/**
 *
 * @author Mondher
 */
public class Categorie {
    Integer  id;
      String Name;

    public Categorie() {
    }

    public Categorie(Integer id, String Name) {
        this.id = id;
        this.Name = Name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", Name=" + Name + '}';
    }
      
      
    
}

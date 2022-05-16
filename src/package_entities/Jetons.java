/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package package_entities;

/**
 *
 * @author mahdi
 */
public class Jetons
{
      Integer  id;
      String Name;
      String Description;
      String Price;
      String clics;
      Integer category;

    public Jetons(Integer id, String Name, String Description, String Price, String clics, Integer category) {
        this.id = id;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.clics = clics;
        this.category = category;
    }
    
 

    public Jetons() {
    }

    @Override
    public String toString() {
        return "Jeton{" + "id=" + id + ", Name=" + Name + ", Description=" + Description + ", Price=" + Price + ", clics=" + clics + '}';
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
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

    public String getClics() {
        return clics;
    }

    public void setClics(String clics) {
        this.clics = clics;
    }

  
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

 
   
      
}

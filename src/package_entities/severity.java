/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package package_entities;


public class severity {
Integer id;
String name;

    public severity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public severity() {
    }

    @Override
    public String toString() {
        return  name +"";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
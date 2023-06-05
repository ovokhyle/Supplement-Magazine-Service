/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.io.Serializable;

/**
 * @File Name: Magazine.java
 * @Details: A magazine class to handle magazine details
 *
 *
 * @author Khyle
 */
public class Magazine implements Serializable {

    private String name;
    private double weeklyCost;

    /*
    *Default constructor
     */
    public Magazine() {
        this.name = "";
        this.weeklyCost = 0;
    }

    /*
    *A constructor that takes in parameters to set name and weeklyCost values
    *@param name: String, weeklyCost: double
     */
    public Magazine(String name, double weeklyCost) {
        this.name = name;
        this.weeklyCost = weeklyCost;
    }

    /*
    *Gets magazine name
    *@return name: String
     */
    public String getName() { //Magazine name getter
        return name;
    }

    /*
    *Sets magazine name
    *@param name: String
     */
    public void setName(String name) { //Magazine name setter
        this.name = name;
    }

    /*
    *Gets weeklyCost
    *@param weeklyCost: double
     */
    public double getWeeklyCost() { //Weekly cost getter
        return weeklyCost;
    }

    /*
    *Sets weeklyCost
    *@param weeklyCost: double
     */
    public void setWeeklyCost(double weeklyCost) { //Weekly cost setter
        this.weeklyCost = weeklyCost;
    }

}

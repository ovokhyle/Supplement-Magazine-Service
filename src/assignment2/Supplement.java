/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.io.Serializable;

/**
 * @File Name: Supplement.java
 * @Details: A Supplement class to handle supplement details
 *
 *
 * @author Khyle
 */
public class Supplement implements Serializable {

    private String suppName;
    private double weeklyCost;

    /*
    *Default Constructor
     */
    public Supplement() {
        suppName = "";
        weeklyCost = 0;
    }

    /*
    *Constructor that takes in parameters to set suppName and weeklyCost
     */
    public Supplement(String suppName, double weeklyCost) {
        this.suppName = suppName;
        this.weeklyCost = weeklyCost;
    }

    /*
    *Gets Supplement name
    *@return suppName: String
     */
    public String getSuppName() {
        return suppName;
    }

    /*
    *Sets Supplement name
    *@param suppName: String
     */
    public void setSuppName(String suppName) { //suppName setter
        this.suppName = suppName;
    }

    /*
    *Gets weeklyCost
    *@return weeklyCost: double
     */
    public double getWeeklyCost() { //weeklyCost getter
        return weeklyCost;
    }

    /*
    *Sets weeklyCost
    *@param weeklyCost: double
     */
    public void setWeeklyCost(double weeklyCost) { //weeklyCost setter
        this.weeklyCost = weeklyCost;
    }

}

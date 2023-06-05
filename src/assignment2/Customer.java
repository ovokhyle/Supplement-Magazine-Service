/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @File Name: Customer.java
 * @Details: A Customer class to handle customer details
 *
 *
 * @author Khyle
 */
public class Customer implements Serializable {

    private String name;
    private String email;
    private String address;

    /**
     * Default Constructor
     */
    public Customer() {
        name = "";
        email = "";
        address = "";
    }

    /*Constructor that takes parameters to set customer name and email
    *@param nam: String, emails: String
     */
    public Customer(String nam, String emails, String addy) {
        name = nam;
        email = emails;
        address = addy;
    }

    /*
    *Gets customer name
    *@return name: String
     */
    public String getName() {
        return name;
    }

    /*
    *Sets customer name
    *@param nam: String
     */
    public void setName(String nam) { //Customer name setter
        name = nam;
    }

    /*
    *Gets customer email
    *@return email: String
     */
    public String getEmail() { //Customer email getter
        return email;
    }

    /*
    *Sets customer email
    *@param emails: String
     */
    public void setEmail(String emails) { //Customer email setter
        email = emails;
    }

    /*
    *Gets customer address
    *@return address: String
     */
    public String getAddress() { //Customer address getter
        return address;
    }

    /*
    *Sets customer address
    *@param address: String
     */
    public void setAddress(String addy) { //Customer email setter
        address = addy;
    }

}

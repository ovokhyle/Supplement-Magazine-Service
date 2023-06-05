/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @File Name: CustomerHandler.java
 * @Details: A magazine class to handle and store Associate and Paying Customer
 * details
 * @Assumptions: Customers can have the same name but not the same email address
 * @author Khyle
 */
public class CustomerHandler implements Serializable {

    private ArrayList<PayingCustomer> pcustomerList;
    private ArrayList<AssociateCustomer> acustomerList;

    /*
    *Default Constructor to initialise arraylists
     */
    public CustomerHandler() {
        this.pcustomerList = new ArrayList<PayingCustomer>();
        this.acustomerList = new ArrayList<AssociateCustomer>();
    }

    /*
    *Gets Paying Customer ArrayList
    *@return pcustomerList
     */
    public ArrayList<PayingCustomer> getPcustomerList() {
        return pcustomerList;
    }

    /*
    *Sets Paying Customer ArrayList
    *@param pcustomerList: ArrayList
     */
    public void setPcustomerList(ArrayList<PayingCustomer> pcustomerList) {
        this.pcustomerList = pcustomerList;
    }

    /*
    *Gets Associate Customer ArrayList
    *@return acustomerList
     */
    public ArrayList<AssociateCustomer> getAcustomerList() {
        return acustomerList;
    }

    /*
    *Sets Associate Customer ArrayList
    *@param acustomerList: ArrayList
     */
    public void setAcustomerList(ArrayList<AssociateCustomer> acustomerList) {
        this.acustomerList = acustomerList;
    }

    /*
    *Adds AssociateCustomer to ArrayList
    *@param newCust: AssociateCustomer
    *@return: true: boolean
     */
    public boolean addAssociateCustomer(AssociateCustomer newcust) {

        if (this.acustomerList.contains(newcust)) {
            System.out.println("Associate customer " + newcust.getName() + " already exists\n");
            return false;
        } else {
            this.acustomerList.add(newcust);
            System.out.println("\nAssociate Customer " + newcust.getName() + " added.");
        }

        return true;
    }

    /*
    *Adds AssociateCustomer to ArrayList by taking in name and email as parameters
    *@param name:String, email:String
    *@return true: boolean
     */
    public boolean addAssociateCustomerByName(String name, String email, String address) {
        AssociateCustomer newcust = new AssociateCustomer(name, email, address);

        if (this.acustomerList.contains(newcust)) {
            System.out.println("Associate customer " + newcust.getName() + " already exists");
            return false;
        } else {
            this.acustomerList.add(newcust);
            System.out.println("\nAssociate Customer " + newcust.getName() + " added.");
        }

        for (int i = 0; i < acustomerList.size() - 1; i++) {
            if (email == this.acustomerList.get(i).getEmail()) {
                this.acustomerList.remove(i);
            }
        }

        return true;
    }

    /*
    *Removes Associate Customer from end of ArrayList
    *@return false: boolean
     */
    public boolean removeAssociateCustomer() {
        int lastCustomerindex = this.acustomerList.size() - 1;
        if (lastCustomerindex >= 0) {
            this.acustomerList.remove(lastCustomerindex);
            return true;
        } else {
            return false;
        }
    }


    /*
    *Removes Associate Customer from ArrayList by taking an object as parameter
    *@return false: boolean
    *@param: ac: AssociateCustomer
     */
    public boolean removeAssociateCustomerByObject(AssociateCustomer ac) {

        for (int i = 0; i < this.acustomerList.size(); i++) {
            if (this.acustomerList.contains(ac)) {
                this.acustomerList.remove(ac);
                System.out.println("Customer " + ac.getName() + " removed");
                return true;
            } else {
                System.out.println("Customer not found");
            }
        }

        return false;
    }

    /*
    *Removes Associate Customer from ArrayList by taking a String as parameter
    *@return false: boolean
    *@param: name:String
     */
    public boolean removeAssociateCustomerByName(String name) {
        int lastCustomerindex = this.acustomerList.size() - 1;
        for (int i = 0; i < this.acustomerList.size(); i++) {
            AssociateCustomer ac = this.acustomerList.get(i);
            if (ac.getName().equalsIgnoreCase(name)) {
                this.acustomerList.remove(i);
                System.out.println("Customer " + name + " removed");
                return true;
            }
        }
        return false;
    }

    /*
    *Adds Paying Customer from ArrayList by taking an object as parameter
    *@return true: boolean
    *@param: newcust: PayingCustomer
     */
    public boolean addPayingCustomer(PayingCustomer newcust) {

        if (this.pcustomerList.contains(newcust)) {
            System.out.println("Paying customer " + newcust.getName() + " already exists\n");
            return false;
        } else {
            this.pcustomerList.add(newcust);
            System.out.println("\nPaying Customer " + newcust.getName() + " added.");
        }

        return true;
    }

    /*
    *Adds Paying Customer from ArrayList by taking in name and email Strings as parameter
    *@return true: boolean
    *@param: name: String, email: String
     */
    public boolean addPayingCustomerByName(String name, String email, String address) {
        PayingCustomer newcust = new PayingCustomer(name, email, address);

        if (this.pcustomerList.contains(newcust)) {
            System.out.println("Paying customer " + newcust.getName() + " already exists");
            return false;
        } else {
            this.pcustomerList.add(newcust);
            System.out.println("\nPaying Customer " + newcust.getName() + " added.");
        }

        for (int i = 0; i < pcustomerList.size() - 1; i++) {
            if (email == this.pcustomerList.get(i).getEmail()) {
                this.pcustomerList.remove(i);
            }
        }

        return true;
    }

    /*
    *Removes Paying Customer from end of ArrayList
    *@return false: boolean
     */
    public boolean removePayingCustomer() {
        int lastCustomerindex = this.pcustomerList.size() - 1;
        if (lastCustomerindex >= 0) {
            this.pcustomerList.remove(lastCustomerindex);
            return true;
        } else {
            return false;
        }
    }

    /*
    *Removes Paying Customer from ArrayList by taking an object as parameter
    *@return false: boolean
    *@param: pc: PayingCustomer
     */
    public boolean removePayingCustomerByObject(PayingCustomer pc) {

        for (int i = 0; i < this.pcustomerList.size(); i++) {
            if (this.pcustomerList.contains(pc)) {
                this.pcustomerList.remove(pc);
                System.out.println("Customer " + pc.getName() + " removed");
                return true;
            } else {
                System.out.println("Customer not found");
            }
        }

        return false;
    }

    /*
    *Removes Associate Customer from ArrayList by taking a String as parameter
    *@return false: boolean
    *@param: name:String
     */
    public boolean removePayingCustomerByName(String name) {
        int lastCustomerindex = this.pcustomerList.size() - 1;
        for (int i = 0; i < this.pcustomerList.size(); i++) {
            PayingCustomer pc = this.pcustomerList.get(i);
            if (pc.getName().equalsIgnoreCase(name)) {
                this.pcustomerList.remove(i);
                System.out.println("Customer " + name + " removed");
                return true;
            }
        }
        return false;
    }
}

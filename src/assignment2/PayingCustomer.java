/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.io.Serializable;
import java.util.*;

/**
 * @File Name: PayingCustomer.java
 * @Details: A PayingCustomer class to handle paying customers and their
 * associate customers
 * @Assumption: Paying Customers have their own array of supplements
 * @author Khyle
 */
public class PayingCustomer extends Customer implements Serializable {

    private Payment method;
    private ArrayList<AssociateCustomer> associateList;
    private ArrayList<Supplement> supplement;

    /*
    *Default constructor
     */
    public PayingCustomer() {
        super();
        this.associateList = new ArrayList<AssociateCustomer>();
        this.supplement = new ArrayList<Supplement>();
        this.method = new Payment();
    }

    /*
    *Constructor that sets customer values by taking in parameters
    *@param: names: String
     */
    public PayingCustomer(String names, String emails, String addy) {
        super(names, emails, addy);
        this.associateList = new ArrayList();
        this.supplement = new ArrayList();
        this.method = new Payment();
    }

    /*
    *Gets Payment method
    *@return method: Payment
     */
    public Payment getPaymentmethod() {
        return method;
    }

    /*
    *Sets Payment method
    *@param met: Payment
     */
    public void setPaymentmethod(Payment met) {
        method = met;
    }

    /*
    *Gets Associate Customer list
    *@return associateList: ArrayList
     */
    public ArrayList<AssociateCustomer> getAssociate() {
        return associateList;
    }


    /*
    *Sets Supplement ArrayList
    *@param: supplement: ArrayList
     */
    public void setSupplement(ArrayList<Supplement> supplement) {
        this.supplement = supplement;
    }

    /*
    *Adds Associate Customer to ArrayList by taking an object as a parameter
    *@return true: boolean
    *@param: acus: AssociateCustomer
     */
    public boolean addAssociate(AssociateCustomer acus) {
        if (this.associateList.contains(acus)) { //Error message for duplicates
            System.out.println("Associate customer already exists\n");
            return false;
        } else {
            this.associateList.add(acus); //Adds customer to ArrayList
            System.out.println("\nAssociate Customer " + acus.getName() + " added.");
        }

        return true;
    }

    /*
    *Removes Associate Customer from end of ArrayList
    *@return false: boolean
     */
    public boolean removeAssociateCustomer() {
        int lastCustomerindex = this.associateList.size() - 1;
        if (lastCustomerindex >= 0) {
            this.associateList.remove(lastCustomerindex); //Removes last index in arraylist
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

        for (int i = 0; i < this.associateList.size(); i++) { //Loops through arraylist
            if (this.associateList.contains(ac)) { //if object exists
                this.associateList.remove(ac); //removes objects
                System.out.println("Customer " + ac.getName() + " removed");
                return true;
            } else {
                System.out.println("Customer not found"); //Error message if object isn't found
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
        int lastCustomerindex = this.associateList.size() - 1;
        for (int i = 0; i < this.associateList.size(); i++) { //loops through arraylist
            AssociateCustomer ac = this.associateList.get(i); //if parameter is equal to arraylist elemenet
            if (ac.getName().equalsIgnoreCase(name)) {
                this.associateList.remove(i); //remove element
                System.out.println("Customer " + name + " removed");
                return true;
            }
        }
        return false;
    }

    /*
    *Gets Supplement ArrayList
     */
    public ArrayList<Supplement> getSupplement() {
        return this.supplement;
    }

    /*
    *Adds supplement to ArrayList by taking an object as parameter
    *@return true: boolean
    *@param: supplement: Supplement
     */
    public boolean addSupplement(Supplement supplement) {
        if (this.supplement.contains(supplement)) { //Error message if supplement already exists in the arraylist
            System.out.println("Supplement " + supplement.getSuppName() + " already in list");
            return false;
        } else {
            this.supplement.add(supplement); //Adds object to arraylist
            System.out.println("\nSupplement " + supplement.getSuppName() + " added.");
        }
        return true;
    }

    /*
    *Removes Supplement from ArrayList by taking an object as parameter
    *@return false: boolean
    *@param: sup: Supplement
     */
    public boolean removeSupplementByObject(Supplement sup) {

        for (int i = 0; i < this.supplement.size(); i++) { //Loops through arraylist
            if (this.supplement.contains(sup)) { //if object exists
                this.supplement.remove(sup); //remove from arraylist
                System.out.println("Supplement " + sup.getSuppName() + " removed");
                return true;
            } else {
                System.out.println("Supplement not found"); //Error message
            }
        }

        return false;
    }

    /*
    *Removes Supplement from ArrayList by taking an object as parameter
    *@return false: boolean
    *@param: sup: Supplement
     */
    public boolean removeSupplementByName(String name) {
        int lastCustomerindex = this.supplement.size() - 1;
        for (int i = 0; i < this.supplement.size(); i++) { //loops through arraylist
            Supplement sups = this.supplement.get(i);
            if (sups.getSuppName().equalsIgnoreCase(name)) { //if parameter is equal to arraylist element
                this.supplement.remove(i); //remove element
                System.out.println("Supplement " + name + " removed");
                return true;
            }
        }
        return false;
    }

    /*
    *Prints AssociateCustomer details
     */
    public void printAssociateCustomer() {
        System.out.println("\nPaying Customer: " + this.getName());
        System.out.println("\nSupplements: ");
        for (int i = 0; i < this.supplement.size(); i++) { //Loops through supplement arraylist
            System.out.println("\t" + this.supplement.get(i).getSuppName() + " $" + this.supplement.get(i).getWeeklyCost() + "\n"); //display supplements
        }
        System.out.println("Associates: ");
        for (int i = 0; i < this.associateList.size(); i++) { //Loops thorough associate arraylist
            System.out.println("\n\t" + this.associateList.get(i).getName() //Prints details
            );
            if (!this.associateList.get(i).getSupplement().isEmpty()) {
                System.out.println("\n\tSupplements: ");
            } else {
                System.out.println("\n\tSupplements: None");
            }
            this.associateList.get(i).printSupplements();
        }
    }

}

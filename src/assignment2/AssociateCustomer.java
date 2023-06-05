/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.util.ArrayList;

/**
 * @File Name: AssociateCustomer.java
 * @Details: An AssociateCustomer class to handle associate customers
 * @author Khyle
 */
public class AssociateCustomer extends Customer {

    private ArrayList<Supplement> supplement;
    private double total;

    /*
    *Default constructor
     */
    public AssociateCustomer() {
        super();
        this.supplement = new ArrayList();
    }

    /*
    *Constructor that sets customer values by taking in parameters
    *@param: names: String
     */
    public AssociateCustomer(String nam, String emails, String addy) {
        super(nam, emails, addy);
        this.supplement = new ArrayList();
    }

    /*
    *Gets Supplement ArrayList
    *@return supplement: ArrayList
     */
    public ArrayList<Supplement> getSupplement() {
        return this.supplement;
    }

    /*
    *Adds Supplement to ArrayList by taking an object as a parameter
    *@return true: boolean
    *@param: supplement: Supplement
     */
    public boolean addSupplement(Supplement supplement) {
        if (this.supplement.contains(supplement)) { //Error message for duplicates
            System.out.println("Supplement already in list");
            return false;
        } else {
            this.supplement.add(supplement); //Adds object to arraylist
            System.out.println("\nSupplement " + supplement.getSuppName() + " added.");
        }
        return true;
    }

    /*
    *Removes Supplement from ArrayList by taking an object as a parameter
    *@return false: boolean
    *@param: sup: Supplement
     */
    public boolean removeSupplementByObject(Supplement sup) {

        for (int i = 0; i < this.supplement.size(); i++) { //loops through arraylist
            if (this.supplement.contains(sup)) { //if object exist
                this.supplement.remove(sup); //remove object
                System.out.println("Supplement " + sup.getSuppName() + " removed");
                return true;
            } else {
                System.out.println("Supplement not found"); //error message
            }
        }

        return false;
    }

    /*
    *Removes Supplement from ArrayList by taking a String as a parameter
    *@return false: boolean
    *@param: name: String
     */
    public boolean removeSupplementByName(String name) {
        int lastCustomerindex = this.supplement.size() - 1;
        for (int i = 0; i < this.supplement.size(); i++) { //loops through arraylist
            Supplement sups = this.supplement.get(i);
            if (sups.getSuppName().equalsIgnoreCase(name)) { //if parameter is equal to element
                this.supplement.remove(i); //remove element
                System.out.println("Supplement " + name + " removed");
                return true;
            }
        }
        return false;
    }

    public double getTotal() {
        total = 0;
        for (int i = 0; i < this.supplement.size(); i++) { //loops through arraylist
            Supplement sups = this.supplement.get(i);
            total += this.supplement.get(i).getWeeklyCost();
        }
        total = total * 4;
        return total;
    }

    /*
    *Prints Supplement details
     */
    public void printSupplements() {
        //System.out.println("Associate Customer Supplements: ");
        for (int i = 0; i < this.supplement.size(); i++) {
            System.out.println("\t" + this.supplement.get(i).getSuppName() + " $" + this.supplement.get(i).getWeeklyCost());
        }
    }
}

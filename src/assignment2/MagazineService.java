/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import assignment2.Supplement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @File Name: MagazineService.java
 * @Details: To store and handle existing magazines and supplements and any
 * associated calls to Customer Handler
 * @Assumptions: Each customer has subscription to all magazines
 * @author Khyle
 */
public class MagazineService implements Serializable {

    private ArrayList<Magazine> magazines;
    private ArrayList<Supplement> supplement;
    private CustomerHandler ch = new CustomerHandler();
    private double totalbilling = 0;

    /*
    *Default constructor
     */
    public MagazineService() {
        magazines = new ArrayList<Magazine>();
        supplement = new ArrayList<Supplement>();
    }

    /*
    *Constructor that takes in parameters to set ArrayLists
    *@param: magazines: ArrayList, supplement: ArrayList
     */
    public MagazineService(ArrayList<Magazine> magazines, ArrayList<Supplement> supplements) {
        this.magazines = magazines;
        this.supplement = supplements;
    }

    /*
    *Constructor that takes in parameters to set ArrayLists
    *@param: magazines: ArrayList
     */
    public MagazineService(ArrayList<Magazine> magazines) {
        this.magazines = magazines;
    }

    /*
    *Gets Magazine ArrayList
    *@return magazines: ArrayList
     */
    public ArrayList<Magazine> getMagazines() {
        return magazines;
    }

    /*
    *Adds magazine to arraylist
    *@return true: boolean
    *@param: magazines: Magazine
     */
    public boolean setMagazines(Magazine magazines) {
        if (this.magazines.contains(magazines)) { //Duplicate error
            System.out.println("Magazine " + magazines.getName() + " already in list");
            return false;
        } else {
            this.magazines.add(magazines); //Adds object to ArrayList
            System.out.println("\nMagazine " + magazines.getName() + " added.");
        }
        return true;
    }

    /*
    *Gets supplement arraylist
    *@param: supplement: ArrayList
     */
    public ArrayList<Supplement> getSupplements() {
        return supplement;
    }

    /*
    *Adds supplements to arraylist
    *@return true: boolean
    *@param: supplements: Supplement
     */
    public boolean setSupplements(Supplement supplements) {
        if (this.supplement.contains(supplements)) { //Duplicates error
            System.out.println("Supplement already in list");
            return false;
        }
        this.supplement.add(supplements); //Adds object to arraylist
        return true;
    }

    /*
    *Adds AssociateCustomer to CustomerHandler ArrayList
    *@param: name: String, email: String
     */
    public void addAssociateCustomer(String name, String email, String address) {
        ch.addAssociateCustomerByName(name, email, address); //calls function from CustomerHandler
    }

    /*
    *Adds AssociateCustomer to CustomerHandler ArrayList by taking an object as a parameter
    *@param: ac: AssociateCustomer
     */
    public void addAssociateCustomerByObject(AssociateCustomer ac) {
        ch.addAssociateCustomer(ac); //calls function from CustomerHandler
    }

    /*
    *Removes AssociateCustomer from CustomerHandler ArrayList by taking a String as a parameter
    *@param: name: String
     */
    public void removeAssociateCustomer(String name) {
        ch.removeAssociateCustomerByName(name); //calls function from CustomerHandler
    }

    /*
    *Removes AssociateCustomer from CustomerHandler ArrayList by taking an object as a parameter
    *@param: ac: AssociateCustomer
     */
    public void removeAssociateCustomerbyObject(AssociateCustomer ac) {
        ch.removeAssociateCustomerByObject(ac); //calls function from CustomerHandler
    }

    /*
    *Gets associate customer arraylist
    *@param: ch.getAcustomerList: ArrayList
     */
    public ArrayList<AssociateCustomer> getAssociateCustomer() {
        return ch.getAcustomerList();
    }

    public void addPayingCustomer(String name, String email, String address) {
        ch.addPayingCustomerByName(name, email, address); //calls function from CustomerHandler
    }

    /*
    *Adds PayingCustomer to CustomerHandler ArrayList by taking an object as a parameter
    *@param: pc: PayingCustomer
     */
    public void addPayingCustomerByObject(PayingCustomer pc) {
        ch.addPayingCustomer(pc); //calls function from CustomerHandler
    }

    /*
    *Removes AssociateCustomer from CustomerHandler ArrayList by taking a String as a parameter
    *@param: name: String
     */
    public void removePayingCustomer(String name) {
        ch.removePayingCustomerByName(name); //calls function from CustomerHandler
    }

    /*
    *Removes PayingCustomer from CustomerHandler ArrayList by taking an object as a parameter
    *@param: pc: PayingCustomer
     */
    public void removePayingCustomerbyObject(PayingCustomer pc) {
        ch.removePayingCustomerByObject(pc); //calls function from CustomerHandler
    }

    /*
    *Gets paying customer arraylist
    *@param: ch.getPcustomerList: ArrayList
     */
    public ArrayList<PayingCustomer> getPayingCustomer() {
        return ch.getPcustomerList();
    }

    /*
    *Adds Supplement from ArrayList by taking an object as a parameter
    *@return true: boolean
    *@param: supplement: Supplement
     */
    public boolean addSupplement(Supplement supplement) {
        if (this.supplement.contains(supplement)) { //Duplicate error
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
            if (this.supplement.contains(sup)) { //if object exists
                this.supplement.remove(sup); //remove from list
                System.out.println("Supplement " + sup.getSuppName() + " removed");
                return true;
            } else {
                System.out.println("Supplement not found");
            }
        }

        return false;
    }

    /*
    *Removes Supplement from ArrayList by taking a String as a parameter
    *@return false: boolean
    *@param: name:String
     */
    public boolean removeSupplementByName(String name) {
        int lastCustomerindex = this.supplement.size() - 1;
        for (int i = 0; i < this.supplement.size(); i++) { //loops through arraylist
            Supplement sups = this.supplement.get(i);
            if (sups.getSuppName().equalsIgnoreCase(name)) { //if parameter is equal to element
                this.supplement.remove(i); //remove element from list
                System.out.println("Supplement " + name + " removed");
                return true;
            }
        }
        return false;
    }

    /*
    *Adds Magazine from ArrayList by taking an object as a parameter
    *@return true: boolean
    *@param: magazine: Magazine
     */
    public boolean addMagazine(Magazine magazine) {
        if (this.magazines.contains(magazine)) { //Duplicate error
            System.out.println("Magazine is already in list");
            return false;
        } else {
            this.magazines.add(magazine); //Adds object to arraylist
            System.out.println("\nMagazine " + magazine.getName() + " added.");
        }
        return true;
    }

    /*
    *Removes Magazine from ArrayList by taking an object as a parameter
    *@return false: boolean
    *@param: mag: Magazine
     */
    public boolean removeMagazineByObject(Magazine mag) {

        for (int i = 0; i < this.magazines.size(); i++) { //loops through arraylist
            if (this.magazines.contains(mag)) { //if object exists
                this.magazines.remove(mag); //remove from list
                System.out.println("Magazine " + mag.getName() + " removed");
                return true;
            } else {
                System.out.println("Magazine not found");
            }
        }

        return false;
    }

    /*
    *Removes Magazine from ArrayList by taking a String as a parameter
    *@return false: boolean
    *@param: name:String
     */
    public boolean removeMagazineByName(String name) {
        int lastCustomerindex = this.magazines.size() - 1;
        for (int i = 0; i < this.magazines.size(); i++) { //loops through arraylist
            Magazine mag = this.magazines.get(i);
            if (mag.getName().equalsIgnoreCase(name)) { //if parameter is equal to element
                this.magazines.remove(i); //remove element from list
                System.out.println("Magazine " + name + " removed");
                return true;
            }
        }

        return false;
    }


    /*
    *Prints Associate Customer details
     */
    public void printAssociateCustomer() {
        for (int i = 0; i < ch.getAcustomerList().size(); i++) {
            System.out.println("Associate Customer Name: " + ch.getAcustomerList().get(i).getName() + "\n");
        }
    }

    /*
    *Prints Paying Customer details
     */
    public void printPayingCustomer() {
        for (int i = 0; i < ch.getPcustomerList().size(); i++) {
            System.out.println("Paying Customer Name: " + ch.getPcustomerList().get(i).getName() + "\n");
        }
    }

    /*
    *Prints weekly emails to customers
     */
    public void printWeeklyemail() {
        for (int k = 0; k <= 4; k++) { //loops four times for four weeks of magazines
            System.out.println("----------------------------------------------------------------------------");
            for (int j = 0; j < this.magazines.size(); j++) {
                for (int i = 0; i < ch.getAcustomerList().size(); i++) {
                    System.out.println("Magazine: " + this.magazines.get(j).getName());
                    System.out.println("\nFrom: MagMan@gmail.com");
                    System.out.println("To: " + ch.getAcustomerList().get(i).getEmail());
                    System.out.println("Subject: Weekly Magazine");
                    System.out.println("\n" + "Dear " + ch.getAcustomerList().get(i).getName() + ",\n");
                    System.out.println("Your Magazine is ready for viewing and your supplements are listed below.\n");
                    System.out.println("Supplement List: ");
                    ch.getAcustomerList().get(i).printSupplements();
                    System.out.println("\nRegards, ");
                    System.out.println("\nMagMan\n");
                }
            }
            System.out.println("----------------------------------------------------------------------------");
        }
    }

    /*
    *Prints monthly emails to customers
     */
    public double getTotalBilling(PayingCustomer pc) {
        double totalmag = 0;
        double totalsup = 0;
        double totalasssup = 0;
        totalbilling = 0;

        for (int j = 0; j < this.magazines.size(); j++) {
            totalmag += this.getMagazines().get(j).getWeeklyCost(); //Calculates total magazine cost
        }

        totalmag = totalmag * 4; //4 weeks of subscription

        for (int i = 0; i < pc.getAssociate().size(); i++) {
            for (int j = 0; j < pc.getAssociate().get(i).getSupplement().size(); j++) {
                totalasssup += pc.getAssociate().get(i).getSupplement().get(j).getWeeklyCost(); //Calculates total associate customer supplements cost

            }
        }

        for (int j = 0; j < pc.getSupplement().size(); j++) {
            totalsup += pc.getSupplement().get(j).getWeeklyCost(); //Calculates total paying customer supplements cost
        }

        totalsup += totalasssup;
        totalsup = totalsup * 4; //4 weeks of subscription
        totalbilling = totalmag + totalsup;

        return totalbilling;
    }


    /*
    *Prints monthly emails to customers
     */
    public void printMonthlyemail(PayingCustomer pc) {

        double totalmag = 0;
        double totalsup = 0;
        double totalasssup = 0;
        double sum = 0;

        for (int j = 0; j < this.magazines.size(); j++) {
            totalmag += this.getMagazines().get(j).getWeeklyCost(); //Calculates total magazine cost
        }

        totalmag = totalmag * 4; //4 weeks of subscription

        for (int i = 0; i < pc.getAssociate().size(); i++) {
            for (int j = 0; j < pc.getAssociate().get(i).getSupplement().size(); j++) {
                totalasssup += pc.getAssociate().get(i).getSupplement().get(j).getWeeklyCost(); //Calculates total associate customer supplements cost

            }
        }

        for (int j = 0; j < pc.getSupplement().size(); j++) {
            totalsup += pc.getSupplement().get(j).getWeeklyCost(); //Calculates total paying customer supplements cost
        }

        totalsup += totalasssup;
        totalsup = totalsup * 4; //4 weeks of subscription
        sum = totalmag + totalsup;

        System.out.println("----------------------------------------------------------------------------");

        System.out.println("\nFrom: MagMan@gmail.com");
        System.out.println("To: " + pc.getEmail());
        System.out.println("Subject: Monthly Billing");
        System.out.println("\n" + "Dear " + pc.getName() + ",\n");
        System.out.println("Your Monthly Billing Statement is ready for viewing.\n");
        System.out.println("<----------------------------------------------------------Monthly Bill---------------------------------------------------------->");
        pc.printAssociateCustomer();
        System.out.println("\n\nTotal magazine subscription bill: $" + totalmag);
        System.out.println("\nTotal supplement subscription bill: $" + totalsup);
        System.out.println("\nTotal Bill: $" + sum);
        System.out.println("\nYour Payment Details: \n");
        pc.getPaymentmethod().printPayment();
        System.out.println("\n<----------------------------------------------------------End---------------------------------------------------------->");
        System.out.println("\nRegards, ");
        System.out.println("\nMagMan\n");

        System.out.println("----------------------------------------------------------------------------");
    }


    /*
    *Prints magazine details
     */
    public void printMagazines() {
        for (int j = 0; j < this.magazines.size(); j++) {
            System.out.println(this.getMagazines().get(j).getName() + " $" + this.getMagazines().get(j).getWeeklyCost());
        }
    }

    /*
    *Prints supplement details
     */
    public void printSupplements() {
        for (int j = 0; j < this.supplement.size(); j++) {
            System.out.println(this.getSupplements().get(j).getSuppName() + " $" + this.getSupplements().get(j).getWeeklyCost());
        }
    }
}

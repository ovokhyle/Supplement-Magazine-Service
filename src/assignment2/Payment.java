/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.io.Serializable;

/**
 *
 * @author Khyle
 */
public class Payment implements Serializable {

    private String method;
    private int bankNo;

    /*
    *Default constructor
     */
    public Payment() {
        method = "None";
        bankNo = 0;
    }

    /*
    *Constructor that takes in method and bankNo
    *@param method: String, bankNo:int
     */
    public Payment(String method, int bankNo) {
        this.method = method;
        this.bankNo = bankNo;
    }

    /*
    *Gets customer name
    *@return method: String
     */
    public String getMethod() {
        return method;
    }

    /*
    *Sets payment method
    *@param method: String
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /*
    *Gets bankNo
    *@return bankNo: int
     */
    public int getBankNo() {
        return bankNo;
    }

    /*
    *Sets bankNo
    *@param bankNo: int
     */
    public void setBankNo(int bankNo) {
        this.bankNo = bankNo;
    }

    /*
    *Prints payment details
     */
    public void printPayment() {
        System.out.println("Payment method: " + method);
        System.out.println("Account No.: " + bankNo);
    }

}

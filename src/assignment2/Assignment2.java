/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package assignment2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 *
 * @author Khyle
 */
public class Assignment2 extends Application {

    @Override
    public void start(Stage primaryStage) {

        ViewScene vs = new ViewScene();

        EditScene es = new EditScene();

        CreateScene cs = new CreateScene();

        //Creates TabPane
        TabPane tabPane = new TabPane();

        //Adds content to tab pane
        Tab tab1 = new Tab("View");
        tab1.setContent(vs.ViewScene());
        tabPane.getTabs().add(tab1);
        Tab tab2 = new Tab("Edit");
        tab2.setContent(es.EditScene());
        tabPane.getTabs().add(tab2);
        Tab tab3 = new Tab("Create");
        tab3.setContent(cs.CreateScene());
        tabPane.getTabs().add(tab3);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane);

        primaryStage.setTitle("MAGAZINE SERVICE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Declares precoded magazines, supplements, and customers for testing

        /*Magazine mag = new Magazine("FHM", 14.20);
        Magazine mag1 = new Magazine("Vogue", 16.50);
        Magazine mag2 = new Magazine("Reader's Digest", 21.00);
        Magazine mag3 = new Magazine("TIME", 12.50);

        Supplement sup = new Supplement("Centrum", 15.00);
        Supplement sup1 = new Supplement("Cherifer", 12.50);
        Supplement sup2 = new Supplement("Multivitamin", 25.00);
        Supplement sup3 = new Supplement("Swisse", 30.00);

        PayingCustomer c2 = new PayingCustomer("Kanye", "kanyewest@gmail.com", "Chicago");
        PayingCustomer c3 = new PayingCustomer("Wayne", "lilwayne@gmail.com", "New Orleans");
        PayingCustomer c4 = new PayingCustomer("Pop", "popsmoke@gmail.com", "Brooklyn");

        AssociateCustomer ass1 = new AssociateCustomer("Cole", "jcole@gmail.com", "The Ville");
        AssociateCustomer ass2 = new AssociateCustomer("Juice", "juice@gmail.com", "Chicago");
        AssociateCustomer ass3 = new AssociateCustomer("Tjay", "liltjay@gmail.com", "Brooklyn");
        AssociateCustomer ass4 = new AssociateCustomer("Polo", "polog@gmail.com", "Chicago");

        //Adds elements to appropriate ArrayLists through MagazineService
        MagazineService ms = new MagazineService();

        ms.setSupplements(sup);
        ms.setSupplements(sup1);
        ms.setSupplements(sup2);
        ms.setSupplements(sup3);

        ms.setMagazines(mag);
        ms.setMagazines(mag1);
        ms.setMagazines(mag2);
        ms.setMagazines(mag3);

        ms.addPayingCustomerByObject(c1);
        ms.addPayingCustomerByObject(c2);
        ms.addPayingCustomerByObject(c3);
        ms.addPayingCustomerByObject(c4);

        ms.addAssociateCustomerByObject(ass1);
        ms.addAssociateCustomerByObject(ass2);
        ms.addAssociateCustomerByObject(ass3);
        ms.addAssociateCustomerByObject(ass4);

        c1.addAssociate(ass4);
        c1.addAssociate(ass3);

        c1.addSupplement(sup);
        c1.addSupplement(sup2);
        c1.addSupplement(sup3);

        c2.addAssociate(ass1);
        c2.addAssociate(ass3);

        c2.addSupplement(sup3);
        c2.addSupplement(sup2);

        ass1.addSupplement(sup3); //Adding supplements to associate customer
        ass1.addSupplement(sup2);
        ass1.addSupplement(sup1);
        ass3.addSupplement(sup3);
        ass4.addSupplement(sup2);
        ass4.addSupplement(sup3);

        Payment payment = new Payment("Bank", 340201314);
        Payment payment1 = new Payment("Card", 30293101);

        c2.setPaymentmethod(payment);

        c1.setPaymentmethod(payment1);

        try {
            FileOutputStream fos = new FileOutputStream("./dat/Magazine.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ms);
            fos.close();
            oos.close();
        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("IO");
        } */

 /*try {
            FileInputStream fis = new FileInputStream("./dat/Magazine.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            MagazineService o = null;
            o = (MagazineService) ois.readObject();
        } catch (IOException i) {
            System.out.println("IO");
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("CNF");
        } */
        launch(args);
    }

}

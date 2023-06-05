/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package assignment2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @File Name: ViewScene.java
 * @Details: Displays customer and supplement information
 * @author Khyle
 */
public class ViewScene {

    private final ObservableList<PayingCustomer> payingcustomers = FXCollections.observableArrayList(); //All paying customers
    private final ListView<PayingCustomer> payingcustomerListView = new ListView<PayingCustomer>(payingcustomers);

    private final ObservableList<AssociateCustomer> associatecustomers = FXCollections.observableArrayList(); //All associate customers
    private final ListView<AssociateCustomer> associatecustomerListView = new ListView<AssociateCustomer>(associatecustomers);

    private final ObservableList<Supplement> supplements = FXCollections.observableArrayList(); //All supplements
    private final ListView<Supplement> supplementListView = new ListView<Supplement>(supplements);
    private final ListView<Supplement> customersupplementListView = new ListView<Supplement>(); //Supplements of a customer
    private final ListView<Double> priceListView = new ListView<Double>(); //Price of supplements

    private final ObservableList<AssociateCustomer> associates = FXCollections.observableArrayList(); //Associates of a paying customer
    private final ListView<AssociateCustomer> associatesListView = new ListView<AssociateCustomer>(associates);

    private final ListView<Double> associatestotalListView = new ListView<Double>(); //Total billing of an associate customer

    private final ObservableList<Magazine> magazines = FXCollections.observableArrayList(); //List of magazines

    private final Text customerLabel = new Text();
    private final Text nameLabel = new Text();
    private final TextField nameText = new TextField();
    private final Text emailLabel = new Text();
    private final TextField emailText = new TextField();
    private final Text addLabel = new Text();
    private final TextField addText = new TextField();

    private final Text associatecustomerLabel = new Text();

    private final Text totalLabel = new Text();
    private final TextField totalText = new TextField();

    private final Text suppLabel = new Text();
    private final Text suppinfoLabel = new Text();
    private final Text supplementLabel = new Text();
    private final TextField supplementText = new TextField();
    private final Text suppriceLabel = new Text();
    private final TextField suppriceText = new TextField();

    private final ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private MagazineService ms = new MagazineService();
    private final Button refresh = new Button();

    private final ObservableList<String> files = FXCollections.observableArrayList();


    /*
     * Reads file and populates Observable lists with information
     * @param filename:String
     */
    private void readFile(String filename) {

        try {
            FileInputStream fis = new FileInputStream("./dat/" + filename); //Reads file using passed in parameter filename
            ObjectInputStream ois = new ObjectInputStream(fis);
            ms = (MagazineService) ois.readObject();
            payingcustomers.clear();
            for (int i = 0; i < ms.getPayingCustomer().size(); i++) {
                payingcustomers.add(ms.getPayingCustomer().get(i));
            }
            supplements.clear();
            for (int i = 0; i < ms.getSupplements().size(); i++) {
                supplements.add(ms.getSupplements().get(i));
            }
            //associates.clear();
            associatecustomers.clear();
            for (int i = 0; i < ms.getAssociateCustomer().size(); i++) {
                associatecustomers.add(ms.getAssociateCustomer().get(i));
                //associates.add(o.getAssociateCustomer().get(i));
            }
            magazines.clear();
            for (int i = 0; i < ms.getMagazines().size(); i++) {
                magazines.add(ms.getMagazines().get(i));
            }
        } catch (IOException i) {
            System.out.println("IO");
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("CNF");
        }

    }

    /*
     * Reads's dat folder and puts the file names into array then
     * populate ChoiceBox and passes file into readFile(filename); for reading the file
     * @param filename:String
     */
    private void loadFile() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("./dat"))) { //Reads dat folder files
            for (Path file : stream) {
                if (!files.contains(file.getFileName().toString())) {
                    files.add(file.getFileName().toString());
                    choiceBox.setItems(files);
                    System.out.println(file.getFileName());
                }
            }
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println(ex);
        }

        choiceBox.setValue(choiceBox.getItems().get(0));
        readFile(choiceBox.getItems().get(0));

        choiceBox.setOnAction((event) -> { //Sets choiceBox values
            nameText.clear();
            emailText.clear();
            addText.clear();
            totalText.clear();
            supplementText.clear();
            suppriceText.clear();
            payingcustomerListView.getItems().clear();
            customersupplementListView.getItems().clear();          //Clears list views and text boxes
            priceListView.getItems().clear();
            associatesListView.getItems().clear();
            associatestotalListView.getItems().clear();
            supplementListView.getItems().clear();
            priceListView.getItems().clear();
            String selectedItem = choiceBox.getSelectionModel().getSelectedItem();
            readFile(selectedItem); //Passes choiceBox item into readFile for reading
        });

    }

    /*
     * Checks for any file changes by reloading and rereading file
     *
     */
    private void refreshView() {
        refresh.setText("Refresh");

        refresh.setOnAction((event) -> {
            nameText.clear();
            emailText.clear();
            addText.clear();
            totalText.clear();
            supplementText.clear();
            suppriceText.clear();
            payingcustomerListView.getItems().clear();
            customersupplementListView.getItems().clear();
            priceListView.getItems().clear();
            associatesListView.getItems().clear();
            associatestotalListView.getItems().clear();
            associatecustomerListView.getItems().clear();
            supplementListView.getItems().clear();
            priceListView.getItems().clear();
            loadFile();
            String selectedItem = choiceBox.getSelectionModel().getSelectedItem();
            readFile(selectedItem);
        });
    }

    /*
     * Populates Paying Customer List View and Its relevant information
     */
    private void populateCustomer() {

        //Label for list of paying customers
        customerLabel.setText("List of Paying Customers");

        //Label for name
        nameLabel.setText("Name");

        nameText.setEditable(false);

        //Label for email
        emailLabel.setText("Email");

        emailText.setEditable(false);

        //Label for address
        addLabel.setText("Address");

        addText.setEditable(false);

        //Label for total monthly bill
        totalLabel.setText(("Total Monthly Bill"));

        totalText.setEditable(false);

        payingcustomerListView.setCellFactory(param -> new ListCell<PayingCustomer>() { //Displays paying customer name as string
            protected void updateItem(PayingCustomer pname, boolean empty) {
                super.updateItem(pname, empty);

                if (empty || pname == null || pname.getName() == null) {
                    setText(null);
                } else {
                    setText(pname.getName());
                }
            }
        }
        );

        payingcustomerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PayingCustomer>() { //Sets paying customer values
            public void changed(ObservableValue<? extends PayingCustomer> ov, PayingCustomer old_val, PayingCustomer new_val) {
                if (!payingcustomerListView.getItems().isEmpty()) {
                    MagazineService ms = new MagazineService();
                    nameText.setText(new_val.getName());
                    emailText.setText(new_val.getEmail());
                    addText.setText(new_val.getAddress());

                    customersupplementListView.getItems().clear();
                    priceListView.getItems().clear();
                    for (int i = 0; i < new_val.getSupplement().size(); i++) {
                        customersupplementListView.setItems(FXCollections.<Supplement>observableArrayList(new_val.getSupplement())); //Sets paying customer's supplements
                        priceListView.getItems().add(new_val.getSupplement().get(i).getWeeklyCost());
                    }

                    associatesListView.getItems().clear();
                    associatestotalListView.getItems().clear();
                    for (int i = 0; i < new_val.getAssociate().size(); i++) { //Sets paying customer's associate customers
                        associatesListView.setItems(FXCollections.<AssociateCustomer>observableArrayList(new_val.getAssociate()));
                        associatestotalListView.getItems().add(new_val.getAssociate().get(i).getTotal());
                    }

                    double total = ms.getTotalBilling(new_val);
                    String totals = Double.valueOf(total).toString();
                    totalText.setText(totals);
                }
            }
        }
        );

        payingcustomerListView.setOnMouseClicked(event -> {
            PayingCustomer new_val = payingcustomerListView.getSelectionModel().getSelectedItem();
            if (!payingcustomerListView.getItems().isEmpty()) {
                MagazineService ms = new MagazineService();
                nameText.setText(new_val.getName());
                emailText.setText(new_val.getEmail());
                addText.setText(new_val.getAddress());

                customersupplementListView.getItems().clear();
                priceListView.getItems().clear();
                for (int i = 0; i < new_val.getSupplement().size(); i++) {
                    customersupplementListView.setItems(FXCollections.<Supplement>observableArrayList(new_val.getSupplement())); //Sets paying customer's supplements
                    priceListView.getItems().add(new_val.getSupplement().get(i).getWeeklyCost());
                }

                associatesListView.getItems().clear();
                associatestotalListView.getItems().clear();
                for (int i = 0; i < new_val.getAssociate().size(); i++) { //Sets paying customer's associate customers
                    associatesListView.setItems(FXCollections.<AssociateCustomer>observableArrayList(new_val.getAssociate()));
                    associatestotalListView.getItems().add(new_val.getAssociate().get(i).getTotal());
                }

                double total = ms.getTotalBilling(new_val);
                String totals = Double.valueOf(total).toString();
                totalText.setText(totals);
            }
        });

        associatesListView.setCellFactory(param -> new ListCell<AssociateCustomer>() { //Displays paying customer's associate customers as string

            protected void updateItem(AssociateCustomer cname, boolean empty) {
                super.updateItem(cname, empty);

                if (empty || cname == null || cname.getName() == null) {
                    setText(null);
                } else {
                    setText(cname.getName());
                }
            }
        }
        );

    }

    /*
     * Populates associate customer list view
     */
    private void populateAssociates() {
        associatecustomerLabel.setText("List of Associate Customers");

        associatecustomerListView.setCellFactory(param -> new ListCell<AssociateCustomer>() { //Displays associate customers names as string
            protected void updateItem(AssociateCustomer aname, boolean empty) {
                super.updateItem(aname, empty);

                if (empty || aname == null || aname.getName() == null) {
                    setText(null);
                } else {
                    setText(aname.getName());
                }
            }
        }
        );

        associatecustomerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AssociateCustomer>() { //Sets associate customer values
            public void changed(ObservableValue<? extends AssociateCustomer> ov, AssociateCustomer old_val, AssociateCustomer new_val) {
                if (!associatecustomerListView.getItems().isEmpty()) {
                    customersupplementListView.getItems().clear();
                    priceListView.getItems().clear();
                    for (int i = 0; i < new_val.getSupplement().size(); i++) { //Sets associate customer;s supplements
                        customersupplementListView.setItems(FXCollections.<Supplement>observableArrayList(new_val.getSupplement()));
                        priceListView.getItems().add(new_val.getSupplement().get(i).getWeeklyCost());
                    }

                    associatesListView.getItems().clear();
                    associatestotalListView.getItems().clear();

                    nameText.setText(new_val.getName());
                    emailText.setText(new_val.getEmail());
                    addText.setText(new_val.getAddress());

                    double total = new_val.getTotal();
                    String totals = Double.valueOf(total).toString();
                    totalText.setText(totals);
                }
            }
        }
        );

        associatecustomerListView.setOnMouseClicked(event -> {
            AssociateCustomer new_val = associatecustomerListView.getSelectionModel().getSelectedItem();
            if (!associatecustomerListView.getItems().isEmpty()) {
                customersupplementListView.getItems().clear();
                priceListView.getItems().clear();
                for (int i = 0; i < new_val.getSupplement().size(); i++) { //Sets associate customer;s supplements
                    customersupplementListView.setItems(FXCollections.<Supplement>observableArrayList(new_val.getSupplement()));
                    priceListView.getItems().add(new_val.getSupplement().get(i).getWeeklyCost());
                }

                associatesListView.getItems().clear();
                associatestotalListView.getItems().clear();

                nameText.setText(new_val.getName());
                emailText.setText(new_val.getEmail());
                addText.setText(new_val.getAddress());

                double total = new_val.getTotal();
                String totals = Double.valueOf(total).toString();
                totalText.setText(totals);
            }
        });

    }

    /*
     * Populates supplement list view
     */
    private void populateSupplements() {
        suppLabel.setText("List of Supplements");

        //Label for supplement information
        suppinfoLabel.setText("Supplement Information");

        //Label for supplement
        supplementLabel.setText("Supplement Name");

        //Text field for supplement
        supplementText.setEditable(false);

        //Label for supplement price
        suppriceLabel.setText("Supplement Price");

        //Text field for supplement price
        suppriceText.setEditable(false);

        supplementListView.setCellFactory(param -> new ListCell<Supplement>() { //Displays supplement list view items as string
            protected void updateItem(Supplement supname, boolean empty) {
                super.updateItem(supname, empty);

                if (empty || supname == null || supname.getSuppName() == null) {
                    setText(null);
                } else {
                    setText(supname.getSuppName());
                }
            }
        }
        );

        customersupplementListView.setCellFactory(param -> new ListCell<Supplement>() { //Displays customer's supplement list view items as string
            protected void updateItem(Supplement supname, boolean empty) {
                super.updateItem(supname, empty);

                if (empty || supname == null || supname.getSuppName() == null) {
                    setText(null);
                } else {
                    setText(supname.getSuppName());
                }
            }
        }
        );

        supplementListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplement>() { //Listener for change of value and sets the text supplement text boxes with information
            public void changed(ObservableValue<? extends Supplement> ov, Supplement old_val, Supplement new_val) {
                if (!supplementListView.getItems().isEmpty()) {
                    supplementText.setText(new_val.getSuppName());
                    suppriceText.setText(String.valueOf(new_val.getWeeklyCost()));
                }
            }
        }
        );

    }


    /*
     * Inserts all the items into GridPanes and BorderPanes for displaying
     * @return borderPane
     */
    public BorderPane ViewScene() {
        loadFile();

        refreshView();

        populateCustomer(); //Populates Paying Customers List Views

        populateAssociates(); //Populates Associate Customers List Views

        populateSupplements(); //Populates Supplements List Views

        //Label for information
        Text infoLabel = new Text("Customer Information");

        //Label for supplements
        Text suppsLabel = new Text("Supplements:");

        //Label for associate customers
        Text associateLabel = new Text("Associate Customers:");;

        //VBox to store left list views
        VBox vbox = new VBox();
        //vbox.setSpacing(10);
        vbox.getChildren().addAll(customerLabel, payingcustomerListView, associatecustomerLabel, associatecustomerListView, suppLabel, supplementListView);

        associatesListView.setMinSize(70, 70);
        customersupplementListView.setMinSize(70, 70);

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(500, 550);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(infoLabel, 0, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameText, 1, 1);

        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailText, 1, 2);

        gridPane.add(addLabel, 0, 3);
        gridPane.add(addText, 1, 3);

        gridPane.add(suppsLabel, 0, 4);
        gridPane.add(customersupplementListView, 0, 5);
        gridPane.add(priceListView, 1, 5);

        gridPane.add(associateLabel, 0, 7);
        gridPane.add(associatesListView, 0, 8);
        gridPane.add(associatestotalListView, 1, 8);

        gridPane.add(totalLabel, 0, 10);
        gridPane.add(totalText, 1, 10);

        gridPane.add(suppinfoLabel, 0, 12);

        gridPane.add(supplementLabel, 0, 13);
        gridPane.add(supplementText, 1, 13);

        gridPane.add(suppriceLabel, 0, 14);
        gridPane.add(suppriceText, 1, 14);

        infoLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        suppinfoLabel.setStyle("-fx-font: normal bold 15px 'serif' ");

        GridPane topPane = new GridPane();

        topPane.setVgap(5);
        topPane.setHgap(5);

        topPane.add(choiceBox, 0, 0);
        topPane.add(refresh, 1, 0);

        //Creating a Border Pane
        BorderPane borderPane = new BorderPane();

        //Setting size for the pane
        borderPane.setMaxSize(800, 400);

        //Setting the padding
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setTop(topPane);
        borderPane.setLeft(vbox);
        borderPane.setCenter(gridPane);

        return borderPane;
    }

}

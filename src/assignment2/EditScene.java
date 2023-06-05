/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package assignment2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;

/**
 * @File Name: EditScene.java
 * @Details: Edits customer and supplement information
 * @author Khyle
 */
public class EditScene {

    private final ObservableList<PayingCustomer> payingcustomers = FXCollections.observableArrayList();
    private final ObservableList<AssociateCustomer> associatecustomers = FXCollections.observableArrayList();
    private final ObservableList<Supplement> supplements = FXCollections.observableArrayList();
    private final ObservableList<String> files = FXCollections.observableArrayList();

    private final ListView<Supplement> supplementListView = new ListView<Supplement>(); //List View of all supplements

    private final ListView<Supplement> customersupplementListView = new ListView<Supplement>(); // List View of paying customer supplements
    private final ListView<AssociateCustomer> associatesListView = new ListView<AssociateCustomer>(); //List view of associate customers associated with a paying customer
    private final ListView<AssociateCustomer> fullassociatesListView = new ListView<AssociateCustomer>(); //List view of associate customers not associated with a paying customer

    private final ListView<Supplement> associatesupplementListView = new ListView<Supplement>(); //List View of associate customer supplements
    private final ListView<Supplement> nonassociatesupplementListView = new ListView<Supplement>(); //List View of supplements that associate customers don't have

    private final Text payingcustomerLabel = new Text();
    private final TableView<PayingCustomer> payingcustomertable = new TableView<PayingCustomer>(); //Table View for adding, deleting, and editing paying customers

    private final Text associatecustomerLabel = new Text();
    private final TableView<AssociateCustomer> associatecustomertable = new TableView<AssociateCustomer>(); //Table View for adding, deleting, and editing associate customers

    private final Text suppLabel = new Text();
    private final TableView<Supplement> supptable = new TableView<Supplement>(); //Table View for adding, deleting, and editing supplements

    private final HBox payhb = new HBox();
    private final HBox asshb = new HBox();
    private final HBox hb = new HBox();

    private final Button addpaysupBtn = new Button();
    private final Button delpaysupBtn = new Button();

    private final Button addassBtn = new Button();
    private final Button delassBtn = new Button();

    private final Button addasssupBtn = new Button();
    private final Button delasssupBtn = new Button();

    private final Button refresh = new Button();

    private final TextField addSupp = new TextField();
    private final TextField addPrice = new TextField();

    private final Button addSuppBtn = new Button();
    private final Button delSuppBtn = new Button();

    private final ChoiceBox<String> choiceBox = new ChoiceBox<>();

    private final Button saveBtn = new Button();

    private MagazineService ms = new MagazineService();

    /*
     * Reads file and populates Observable lists with information
     * @param filename:String
     */
    private void readFile(String filename) {
        try {
            FileInputStream fis = new FileInputStream("./dat/" + filename); //Reads file using the passed in parameter filename
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
            associatecustomers.clear();
            for (int i = 0; i < ms.getAssociateCustomer().size(); i++) {
                associatecustomers.add(ms.getAssociateCustomer().get(i));
            }

        } catch (IOException i) {
            System.out.println("IO");
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("CNF");
        }

    }

    /*
     * Saves file information into selected magazine service
     * @param filename:String
     */
    private void Save(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream("./dat/" + filename); //Writes to passed in parameter filename
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ms);
            fos.close();
            oos.close();
            System.out.println("Save Successful");
        } catch (IOException i) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Saving");
            String s = " Please Enter valid text and save. ";
            alert.setContentText(s);
            i.printStackTrace();
            System.out.println("IO");
        }

    }

    /*
     * Reads file and populates Observable lists with information
     * @param filename:String
     */
    private void loadFile() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("./dat"))) { //Reads dat folder and populates choiceBox
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

        choiceBox.setOnAction((event) -> { //Clears list views and reads the file again

            supplementListView.getItems().clear();
            customersupplementListView.getItems().clear();

            associatesListView.getItems().clear();
            fullassociatesListView.getItems().clear();

            associatesupplementListView.getItems().clear();
            nonassociatesupplementListView.getItems().clear();

            String selectedItem = choiceBox.getSelectionModel().getSelectedItem();
            readFile(selectedItem); //Reads selected file
        });

    }

    /*
     * Checks for any file changes by reloading and rereading file
     *
     */
    private void refreshView() {
        refresh.setText("Refresh");

        refresh.setOnAction((event) -> { //Reset list views
            supplementListView.getItems().clear();
            customersupplementListView.getItems().clear();

            associatesListView.getItems().clear();
            fullassociatesListView.getItems().clear();

            associatesupplementListView.getItems().clear();
            nonassociatesupplementListView.getItems().clear();

            loadFile(); //Loads selected file
            String selectedItem = choiceBox.getSelectionModel().getSelectedItem();
            readFile(selectedItem); //Reads selected file
        });
    }

    /*
     * Populates Paying Customer Table View and
     * the supplement and associates list views
     */
    private void populatePayingTable() {
        payingcustomerLabel.setText("List of Paying Customers");

        payingcustomertable.setEditable(true);

        TableColumn nameCol = new TableColumn<PayingCustomer, String>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<PayingCustomer, String>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<PayingCustomer, String>>() {
            @Override
            public void handle(CellEditEvent<PayingCustomer, String> t) { //Edits paying customer name
                ((PayingCustomer) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setName(t.getNewValue());
                System.out.println("Paying Customer Name Updated");
            }
        }
        );

        TableColumn emailCol = new TableColumn<PayingCustomer, String>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<PayingCustomer, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
                new EventHandler<CellEditEvent<PayingCustomer, String>>() {
            @Override
            public void handle(CellEditEvent<PayingCustomer, String> t) { //Edits paying customer email
                ((PayingCustomer) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEmail(t.getNewValue());
                System.out.println("Paying Customer Email Updated");
            }
        }
        );

        TableColumn addressCol = new TableColumn<PayingCustomer, String>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<PayingCustomer, String>("address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(
                new EventHandler<CellEditEvent<PayingCustomer, String>>() {
            @Override
            public void handle(CellEditEvent<PayingCustomer, String> t) { //Edits paying customer address
                ((PayingCustomer) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setAddress(t.getNewValue());
                System.out.println("Paying Customer Address Updated");
            }
        }
        );

        payingcustomertable.setItems(payingcustomers);
        payingcustomertable.getColumns().addAll(nameCol, emailCol, addressCol); //Adds items to payingcustomertable

        payingcustomertable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PayingCustomer>() {

            public void changed(ObservableValue<? extends PayingCustomer> ov, PayingCustomer old_val, PayingCustomer new_val) { //Populates information in list views based on selcted payingcustomertable item
                if (!payingcustomertable.getItems().isEmpty()) {
                    customersupplementListView.getItems().clear();
                    supplementListView.getItems().clear();
                    final HashSet<Supplement> supplementhash = new HashSet<>();
                    for (int i = 0; i < supplements.size(); i++) {
                        supplementhash.add(supplements.get(i));
                    }

                    supplementListView.setItems(FXCollections.<Supplement>observableArrayList(supplementhash));

                    for (int i = 0; i < new_val.getSupplement().size(); i++) {
                        customersupplementListView.getItems().add((new_val.getSupplement().get(i)));
                        supplementhash.removeAll(customersupplementListView.getItems());
                        supplementListView.setItems(FXCollections.<Supplement>observableArrayList(supplementhash));
                    }

                    final HashSet<AssociateCustomer> associatehash = new HashSet<>();
                    for (int i = 0; i < associatecustomers.size(); i++) {
                        associatehash.add(associatecustomers.get(i));
                    }

                    fullassociatesListView.setItems(FXCollections.<AssociateCustomer>observableArrayList(associatehash));

                    associatesListView.getItems().clear();
                    for (int i = 0; i < new_val.getAssociate().size(); i++) {
                        associatesListView.getItems().add(new_val.getAssociate().get(i));
                        associatehash.removeAll(associatesListView.getItems());
                        fullassociatesListView.setItems(FXCollections.<AssociateCustomer>observableArrayList(associatehash));
                    }
                }
            }
        }
        );

        supplementListView.setCellFactory(param -> new ListCell<Supplement>() { //Displays supplementListView names as strings
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

        customersupplementListView.setCellFactory(param -> new ListCell<Supplement>() { //Displays customersupplementListView names as strings
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

        associatesListView.setCellFactory(param -> new ListCell<AssociateCustomer>() { //Displays associatesListView names as strings
            protected void updateItem(AssociateCustomer name, boolean empty) {
                super.updateItem(name, empty);

                if (empty || name == null || name.getName() == null) {
                    setText(null);
                } else {
                    setText(name.getName());
                }
            }
        }
        );

        fullassociatesListView.setCellFactory(param -> new ListCell<AssociateCustomer>() { //Displays fullassociatesListView names as string
            protected void updateItem(AssociateCustomer name, boolean empty) {
                super.updateItem(name, empty);

                if (empty || name == null || name.getName() == null) {
                    setText(null);
                } else {
                    setText(name.getName());
                }
            }
        }
        );

        //Add and Delete Buttons for Paying Customers
        TextField addP = new TextField();
        addP.maxWidth(10);
        addP.setPromptText("Name");
        TextField addPemail = new TextField();
        addPemail.setPromptText("Email");
        TextField addPaddress = new TextField();
        addPaddress.setPromptText("Address");

        Button addPBtn = new Button("Add");

        addPBtn.setOnAction(new EventHandler<ActionEvent>() { //Adds paying customer into magazine service
            @Override
            public void handle(ActionEvent e) {
                if (addP.getText().isEmpty() != true && addPemail.getText().isEmpty() != true && addPaddress.getText().isEmpty() != true) {
                    PayingCustomer paying = new PayingCustomer(addP.getText(), addPemail.getText(), addPaddress.getText());
                    payingcustomers.add(paying);
                    ms.addPayingCustomerByObject(paying);
                    addP.clear();
                    addPemail.clear();
                    addPaddress.clear();
                } else {
                    System.out.println("Please fill out text fields.");
                }
            }
        });
        addPBtn.setMinWidth(40);

        Button delPBtn = new Button("Delete");
        delPBtn.setOnAction(new EventHandler<ActionEvent>() { //Deletes paying customer from magazine service
            @Override
            public void handle(ActionEvent e) {
                PayingCustomer selectedItem = payingcustomertable.getSelectionModel().getSelectedItem();
                payingcustomers.remove(selectedItem);
                ms.removePayingCustomerbyObject(selectedItem);
                payingcustomertable.getItems().remove(selectedItem);
            }
        });
        delPBtn.setMinWidth(60);

        payhb.setSpacing(5);
        payhb.getChildren().addAll(addP, addPemail, addPaddress, addPBtn, delPBtn);

        addpaysupBtn.setText("Add Supplement");
        delpaysupBtn.setText("Remove Supplement");

        addassBtn.setText("Add Associate");
        delassBtn.setText("Remove Associate");

        addpaysupBtn.setOnAction(new EventHandler<ActionEvent>() { //Adds supplements to paying customer
            @Override
            public void handle(ActionEvent e) {
                PayingCustomer pay = payingcustomertable.getSelectionModel().getSelectedItem();
                if (!supplementListView.getSelectionModel().isEmpty()) {
                    pay.getSupplement().add(supplementListView.getSelectionModel().getSelectedItem());
                    customersupplementListView.getItems().add(supplementListView.getSelectionModel().getSelectedItem());
                    supplementListView.getItems().remove(supplementListView.getSelectionModel().getSelectedItem());
                }
            }
        });

        delpaysupBtn.setOnAction(new EventHandler<ActionEvent>() { //Removes paying customer's supplements
            @Override
            public void handle(ActionEvent e) {
                PayingCustomer pay = payingcustomertable.getSelectionModel().getSelectedItem();
                if (!customersupplementListView.getSelectionModel().isEmpty()) {
                    pay.getSupplement().remove(customersupplementListView.getSelectionModel().getSelectedItem());
                    supplementListView.getItems().add(customersupplementListView.getSelectionModel().getSelectedItem());
                    customersupplementListView.getItems().remove(customersupplementListView.getSelectionModel().getSelectedItem());
                }
            }
        });

        addassBtn.setOnAction(new EventHandler<ActionEvent>() { //Adds associate customers to paying customer
            @Override
            public void handle(ActionEvent e) {
                PayingCustomer pay = payingcustomertable.getSelectionModel().getSelectedItem();
                if (!fullassociatesListView.getSelectionModel().isEmpty()) {
                    pay.getAssociate().add(fullassociatesListView.getSelectionModel().getSelectedItem());
                    associatesListView.getItems().add(fullassociatesListView.getSelectionModel().getSelectedItem());
                    fullassociatesListView.getItems().remove(fullassociatesListView.getSelectionModel().getSelectedItem());
                }
            }
        });

        delassBtn.setOnAction(new EventHandler<ActionEvent>() { //Removes associate customers from paying customer
            @Override
            public void handle(ActionEvent e) {
                PayingCustomer pay = payingcustomertable.getSelectionModel().getSelectedItem();
                if (!associatesListView.getSelectionModel().isEmpty()) {
                    pay.getAssociate().remove(associatesListView.getSelectionModel().getSelectedItem());
                    fullassociatesListView.getItems().add(associatesListView.getSelectionModel().getSelectedItem());
                    associatesListView.getItems().remove(associatesListView.getSelectionModel().getSelectedItem());
                }
            }
        });

    }

    /*
     * Populates Associate Customer Table View and
     * the associate supplement list view
     */
    private void populateAssociateTable() {
        associatecustomerLabel.setText("List of Associate Customers");

        associatecustomertable.setEditable(true);

        TableColumn assnameCol = new TableColumn<AssociateCustomer, String>("Name");
        assnameCol.setCellValueFactory(new PropertyValueFactory<AssociateCustomer, String>("name"));
        assnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        assnameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<AssociateCustomer, String>>() {
            @Override
            public void handle(CellEditEvent<AssociateCustomer, String> t) { //Edits associate customer name
                ((AssociateCustomer) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setName(t.getNewValue());
            }
        }
        );

        TableColumn assemailCol = new TableColumn<AssociateCustomer, String>("Email");
        assemailCol.setCellValueFactory(new PropertyValueFactory<AssociateCustomer, String>("email"));
        assemailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        assemailCol.setOnEditCommit(
                new EventHandler<CellEditEvent<AssociateCustomer, String>>() { //Edits associate customer email
            @Override
            public void handle(CellEditEvent<AssociateCustomer, String> t) {
                ((AssociateCustomer) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEmail(t.getNewValue());
            }
        }
        );

        TableColumn assaddressCol = new TableColumn<AssociateCustomer, String>("Address");
        assaddressCol.setCellValueFactory(new PropertyValueFactory<PayingCustomer, String>("address"));
        assaddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        assaddressCol.setOnEditCommit(
                new EventHandler<CellEditEvent<AssociateCustomer, String>>() { //Edits associate customer address
            public void handle(CellEditEvent<AssociateCustomer, String> t) {
                ((AssociateCustomer) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setAddress(t.getNewValue());
            }
        }
        );

        associatecustomertable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AssociateCustomer>() { //Populates information in associate supplement list view based on selcted associatecustomertable item

            public void changed(ObservableValue<? extends AssociateCustomer> ov, AssociateCustomer old_val, AssociateCustomer new_val) {
                if (!associatecustomertable.getItems().isEmpty()) {
                    associatesupplementListView.getItems().clear();

                    final HashSet<Supplement> associatesuphash = new HashSet<>();
                    for (int i = 0; i < supplements.size(); i++) {
                        associatesuphash.add(supplements.get(i));
                    }

                    nonassociatesupplementListView.setItems(FXCollections.<Supplement>observableArrayList(associatesuphash));

                    for (int i = 0; i < new_val.getSupplement().size(); i++) {
                        associatesupplementListView.getItems().add(new_val.getSupplement().get(i));
                        associatesuphash.removeAll(associatesupplementListView.getItems());
                        nonassociatesupplementListView.setItems(FXCollections.<Supplement>observableArrayList(associatesuphash));
                    }
                }
            }
        }
        );

        associatecustomertable.setItems(associatecustomers);
        associatecustomertable.getColumns().addAll(assnameCol, assemailCol, assaddressCol);

        associatesupplementListView.setCellFactory(param -> new ListCell<Supplement>() { //Displays associatesupplementListView items as strings
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

        nonassociatesupplementListView.setCellFactory(param -> new ListCell<Supplement>() { //Displays nonassociatesupplementListView items as strings
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

        //Add and Delete Buttons for Associate Customers
        TextField addA = new TextField();
        addA.setPromptText("Name");
        TextField addAemail = new TextField();
        addAemail.setPromptText("Email");
        TextField addAaddress = new TextField();
        addAaddress.setPromptText("Address");

        Button addABtn = new Button("Add");
        addABtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (addA.getText().isEmpty() != true && addAemail.getText().isEmpty() != true && addAaddress.getText().isEmpty() != true) { //Adds associate customers
                    AssociateCustomer ass = new AssociateCustomer(addA.getText(), addAemail.getText(), addAaddress.getText());
                    associatecustomers.add(ass);
                    ms.addAssociateCustomerByObject(ass);

                    final HashSet<AssociateCustomer> associatehash = new HashSet<>();
                    for (int i = 0; i < associatecustomers.size(); i++) {
                        associatehash.add(associatecustomers.get(i));
                    }

                    fullassociatesListView.setItems(FXCollections.<AssociateCustomer>observableArrayList(associatehash));

                    for (int i = 0; i < associatehash.size(); i++) {
                        associatehash.removeAll(associatesListView.getItems());
                        fullassociatesListView.setItems(FXCollections.<AssociateCustomer>observableArrayList(associatehash));
                    }

                    addA.clear();
                    addAemail.clear();
                    addAaddress.clear();
                } else {
                    System.out.println("Please fill out text fields.");
                }
            }
        }
        );
        addABtn.setMinWidth(40);

        Button delABtn = new Button("Delete");

        delABtn.setOnAction(
                new EventHandler<ActionEvent>() { //Deletes associate customers
            @Override
            public void handle(ActionEvent e
            ) {
                AssociateCustomer selectedItem = associatecustomertable.getSelectionModel().getSelectedItem();
                associatecustomers.remove(selectedItem);
                associatecustomertable.getItems().remove(selectedItem);
                ms.removeAssociateCustomerbyObject(selectedItem);
                associatesListView.getItems().remove(selectedItem);
                fullassociatesListView.getItems().remove(selectedItem);
            }
        }
        );
        delABtn.setMinWidth(60);

        asshb.setSpacing(10);
        asshb.getChildren().addAll(addA, addAemail, addAaddress, addABtn, delABtn);

        addasssupBtn.setText("Add Supplement");
        delasssupBtn.setText("Remove Supplement");

        addasssupBtn.setOnAction(new EventHandler<ActionEvent>() { //Adds supplements to an associate customer
            @Override
            public void handle(ActionEvent e) {
                AssociateCustomer ac = associatecustomertable.getSelectionModel().getSelectedItem();
                if (!nonassociatesupplementListView.getSelectionModel().isEmpty()) {
                    ac.getSupplement().add(nonassociatesupplementListView.getSelectionModel().getSelectedItem());
                    associatesupplementListView.getItems().add(nonassociatesupplementListView.getSelectionModel().getSelectedItem());
                    nonassociatesupplementListView.getItems().remove(nonassociatesupplementListView.getSelectionModel().getSelectedItem());
                }
            }
        });

        delasssupBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) { //Deletes supplements from an associate customer
                AssociateCustomer ac = associatecustomertable.getSelectionModel().getSelectedItem();
                if (!associatesupplementListView.getSelectionModel().isEmpty()) {
                    ac.getSupplement().remove(associatesupplementListView.getSelectionModel().getSelectedItem());
                    nonassociatesupplementListView.getItems().add(associatesupplementListView.getSelectionModel().getSelectedItem());
                    associatesupplementListView.getItems().remove(associatesupplementListView.getSelectionModel().getSelectedItem());
                }
            }
        });

    }

    /*
     * Populates Supplement Table View
     */
    private void populateSupplementTable() {
        suppLabel.setText("List of Supplements");

        addSupp.setPromptText("Name");
        addPrice.setPromptText("Price");
        addSuppBtn.setText("Add");
        delSuppBtn.setText("Delete");

        supptable.setEditable(true);

        TableColumn supnameCol = new TableColumn<Supplement, String>("Name");

        supnameCol.setCellValueFactory(
                new PropertyValueFactory<Supplement, String>("suppName"));
        supnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        supnameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Supplement, String>>() { //Edits supplement name
            @Override
            public void handle(CellEditEvent<Supplement, String> t
            ) {
                ((Supplement) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setSuppName(t.getNewValue());
            }
        }
        );

        TableColumn priceCol = new TableColumn<Customer, String>("Price");

        priceCol.setCellValueFactory(
                new PropertyValueFactory<Customer, String>("weeklyCost"));
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Supplement, Double>>() { //Edits supplement weekly cost
            @Override
            public void handle(CellEditEvent<Supplement, Double> t
            ) {
                ((Supplement) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setWeeklyCost(t.getNewValue());
                System.out.println("Price Updated");
            }
        }
        );

        supptable.setItems(supplements);

        supptable.getColumns().addAll(supnameCol, priceCol);

        //Add and Delete Buttons for Supplements
        addSuppBtn.setOnAction(
                new EventHandler<ActionEvent>() { //Adds supplements
            @Override
            public void handle(ActionEvent e
            ) {
                try {
                    double price = Double.parseDouble(addPrice.getText());
                    Supplement sup = new Supplement(addSupp.getText(), price);
                    supplements.add(sup);

                    supplementListView.getItems().clear();
                    final HashSet<Supplement> supplementhash = new HashSet<>();
                    for (int i = 0; i < supplements.size(); i++) {
                        supplementhash.add(supplements.get(i));
                    }

                    supplementListView.setItems(FXCollections.<Supplement>observableArrayList(supplementhash));

                    for (int i = 0; i < supplementhash.size(); i++) {
                        supplementhash.removeAll(customersupplementListView.getItems());
                        supplementListView.setItems(FXCollections.<Supplement>observableArrayList(supplementhash));
                    }

                    ms.addSupplement(sup);
                    addSupp.clear();
                    addPrice.clear();
                } catch (NumberFormatException i) {
                    System.out.println("Please out text fields and enter a number for price");
                    i.printStackTrace();
                }
            }
        }
        );
        addSuppBtn.setMinWidth(40);

        delSuppBtn.setOnAction(
                new EventHandler<ActionEvent>() { //Deletes supplements
            @Override
            public void handle(ActionEvent e) {
                Supplement selectedItem = supptable.getSelectionModel().getSelectedItem();
                supplements.remove(selectedItem);
                supptable.getItems().remove(selectedItem);
                ms.removeSupplementByObject(selectedItem);
                customersupplementListView.getItems().remove(selectedItem);
                supplementListView.getItems().remove(selectedItem);
            }
        }
        );
        delSuppBtn.setMinWidth(60);

        hb.setSpacing(10);
        hb.getChildren().addAll(addSupp, addPrice, addSuppBtn, delSuppBtn);

    }

    /*
     * Sets save button action by passing in selected choiceBox item to Save();
     */
    private void SaveFile() {
        //Save Button
        saveBtn.setText("Save");
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Save(choiceBox.getSelectionModel().getSelectedItem());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Save Successful");
                String s = "Save Successful";
                alert.setContentText(s);
                alert.showAndWait();
            }
        });
    }

    /*
     * Inserts all the items into GridPanes and BorderPanes for displaying
     * @return borderPane
     */
    public BorderPane EditScene() {

        loadFile();

        populatePayingTable();

        populateAssociateTable();

        populateSupplementTable();

        refreshView();

        SaveFile();

        Text payingsupLabel = new Text("Paying Customer Supplements");
        Text payingassLabel = new Text("Paying Customer Associates");
        Text asssupLabel = new Text("Associate Customer Supplements");

        //GridPane for Customer and Supplement Information
        GridPane infoPane = new GridPane();

        infoPane.setMaxSize(450, 550);
        infoPane.setPadding(new Insets(10, 10, 10, 10));
        infoPane.setAlignment(Pos.CENTER);

        infoPane.setVgap(5);
        infoPane.setHgap(5);

        infoPane.add(saveBtn, 0, 0);

        infoPane.add(refresh, 1, 0);

        infoPane.add(payingsupLabel, 0, 1);
        infoPane.add(customersupplementListView, 0, 2);
        infoPane.add(supplementListView, 1, 2);
        infoPane.add(delpaysupBtn, 0, 3);
        infoPane.add(addpaysupBtn, 1, 3);

        infoPane.add(payingassLabel, 0, 4);
        infoPane.add(associatesListView, 0, 5);
        infoPane.add(fullassociatesListView, 1, 5);
        infoPane.add(delassBtn, 0, 6);
        infoPane.add(addassBtn, 1, 6);

        infoPane.add(asssupLabel, 0, 7);
        infoPane.add(associatesupplementListView, 0, 8);
        infoPane.add(nonassociatesupplementListView, 1, 8);
        infoPane.add(delasssupBtn, 0, 9);
        infoPane.add(addasssupBtn, 1, 9);

        //GridPane for Customer and Supplements Table View
        GridPane customerPane = new GridPane();

        customerPane.setVgap(5);
        customerPane.setHgap(5);
        customerPane.setMaxWidth(350);
        customerPane.setMinHeight(550);

        customerPane.add(payingcustomerLabel, 0, 0);
        customerPane.add(payingcustomertable, 0, 1);
        customerPane.add(payhb, 0, 2);

        customerPane.add(suppLabel, 0, 3);
        customerPane.add(supptable, 0, 4);
        customerPane.add(hb, 0, 5);

        customerPane.add(associatecustomerLabel, 0, 6);
        customerPane.add(associatecustomertable, 0, 7);
        customerPane.add(asshb, 0, 8);

        //GridPane for magazine dropdown(choiceBox) and refresh
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

        borderPane.setLeft(customerPane);

        borderPane.setCenter(infoPane);

        return borderPane;

    }

}

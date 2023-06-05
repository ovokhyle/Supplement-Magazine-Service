/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package assignment2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * @File Name: CreateScene.java
 * @Details: Creates a new Magazine Service
 * @author Khyle
 */
public class CreateScene {

    private final ArrayList<Magazine> magazines = new ArrayList<Magazine>();
    private final ArrayList<Supplement> sups = new ArrayList<Supplement>();
    private final ObservableList<String> files = FXCollections.observableArrayList();

    private final TextField addText = new TextField();

    private final Text addLabel = new Text();

    private final TextField costText = new TextField();

    private final Text costLabel = new Text();

    private final Button addBtn = new Button("Add");

    private MagazineService o = new MagazineService();

    /*
     * Saves data into specified file
     *
     */
    private void Save(MagazineService ms, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream("./dat/" + filename + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ms);
            fos.close();
            oos.close();
            System.out.println("Save Successful");
        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("IO");
        }
    }

    /*
     * Reads dat folder and checks existing magazines
     *
     */
    private void readExistingMagazines() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("./dat"))) {
            for (Path file : stream) {
                if (!files.contains(file.getFileName().toString())) {
                    files.add(file.getFileName().toString());
                }
            }
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println(ex);
        }
    }

    /*
     * Creates new magazine service and saves into dat folder
     *
     */
    private void addMagazine() {

        addLabel.setText("Magazine Name");

        addText.setPromptText("Magazine Name");

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                // Show error alert dialog
                String txt = addText.getText().trim();
                String msg = "Text saved: ";
                boolean valid = true;

                if (txt.isEmpty()) { //If text box is empty shows error alert

                    valid = false;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Saving");
                    String s = " Please Enter valid text and save. ";
                    alert.setContentText(s);

                    alert.showAndWait();
                    msg = "Invalid text entered: ";
                }
                for (int i = 0; i < files.size(); i++) { //If magazine already exists in dat folder, shows error alert
                    System.out.println(files.get(i));
                    if (files.get(i).equals(addText.getText() + ".dat")) {
                        valid = false;
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("File already exists");
                        String s = "Magazine already exists";
                        alert.setContentText(s);

                        alert.showAndWait();
                        msg = "Magazine already exists";
                    }
                }

                if (!valid) { //If invalid put cursor on text box
                    addText.requestFocus();
                } else { //If valid shows save successful alery
                    valid = true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Save Successful");
                    String s = "Magazine Created";
                    alert.setContentText(s);

                    alert.showAndWait();
                    msg = "Magazine Created";

                    //double cost = Double.parseDouble(costText.getText());
                    //Magazine magazine = new Magazine(addText.getText(), cost);
                    MagazineService ms = new MagazineService(magazines, sups); //Creates new magazine service
                    Save(ms, addText.getText()); //Passes magazine service into save for saving into file
                    addText.clear();
                }
            }
        });

    }

    /*
     * Inserts all the items into GridPanes and BorderPanes for displaying
     * @return borderPane
     */
    public BorderPane CreateScene() {

        readExistingMagazines();

        addMagazine();

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

        gridPane.add(addLabel, 0, 0);
        gridPane.add(addText, 1, 0);

        //gridPane.add(costLabel, 0, 1);
        //gridPane.add(costText, 1, 1);
        gridPane.add(addBtn, 1, 4);

        BorderPane borderPane = new BorderPane();

        //Setting size for the pane
        borderPane.setMaxSize(800, 400);

        //Setting the padding
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setCenter(gridPane);

        return borderPane;

    }

}

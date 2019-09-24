package management_system;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class gui extends Application {

  private static List<String> criticalClinics = new ArrayList<>();
  @Override
  public void start(Stage primaryStage) throws Exception {
    Stock.initConnectionToDB();
    primaryStage.setTitle("Stock System");

    GridPane gridPane = formPane();
    addUIControls(gridPane);
    Scene scene = new Scene(gridPane, 800, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String [] args) {
    launch(args);

  }
  private GridPane formPane() {
    //creating new GridPane
    GridPane gridPane = new GridPane();

    gridPane.setAlignment(Pos.CENTER);
    gridPane.setPadding(new Insets(40,40,40,40));
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    ColumnConstraints columnOneConstraints = new ColumnConstraints(100,100,Double.MAX_VALUE);
    columnOneConstraints.setHalignment(HPos.RIGHT);

    ColumnConstraints columnTwoConstraints = new ColumnConstraints(200,200,Double.MAX_VALUE);
    columnTwoConstraints.setHgrow(Priority.ALWAYS);
    gridPane.getColumnConstraints().addAll(columnOneConstraints,columnTwoConstraints);

    return gridPane;

  }
  private void addUIControls(final GridPane gridPane) {
       // Add Header
       Label headerLabel = new Label("Medication Stock");
       headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
       gridPane.add(headerLabel, 0,0,2,1);
       GridPane.setHalignment(headerLabel, HPos.CENTER);
       GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

       // Add Name Label
       Label clinicLabel = new Label("Clinic:");
       gridPane.add(clinicLabel, 0,1);

       // Add Name Text Field
       final TextField nameField = new TextField();
       nameField.setPrefHeight(40);
       gridPane.add(nameField, 1,1);


       Label medLabel = new Label("Medication:  ");
       gridPane.add(medLabel, 0, 2);

       final TextField medField = new TextField();
       medField.setPrefHeight(40);
       gridPane.add(medField, 1, 2);

       Label quantityLabel = new Label("Quantity: ");
       gridPane.add(quantityLabel, 0, 3);

       final TextField quantityField = new TextField();
       quantityField.setPrefHeight(40);
       gridPane.add(quantityField, 1, 3);

       Label dataLabel = new Label("Low Meds ");
       gridPane.add(dataLabel, 0, 4);

       final TextArea clinicsLow = new TextArea();
       clinicsLow.setPrefHeight(60);
       gridPane.add(clinicsLow, 1, 4);
       String lowClinics = Stock.retrieveListOfLow();
       clinicsLow.setText(lowClinics);

       // Add Insert Button
       Button insertButton = new Button("Insert");
       insertButton.setPrefHeight(40);
       insertButton.setDefaultButton(true);
       insertButton.setPrefWidth(100);
       gridPane.add(insertButton, 0, 10, 2, 1);
       GridPane.setHalignment(insertButton, HPos.RIGHT);
       GridPane.setMargin(insertButton, new Insets(20, 0,20,0));

       insertButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               if(nameField.getText().isEmpty()) {
                   showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter Clinic Name");
                   return;
               }
               if(medField.getText().isEmpty()) {
                   showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter the name of the medication");
                   return;
               }
               if(quantityField.getText().isEmpty()) {
                   showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter quantity");
                   return;
               } else {
                 int Q = Integer.parseInt(quantityField.getText());
                 Stock.insertData(nameField.getText(), medField.getText(),Q);
               }

               showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Insertion Complete", "Success");
           }
       });

       Button updateButton = new Button("Update");
       updateButton.setPrefHeight(40);
       updateButton.setDefaultButton(true);
       updateButton.setPrefWidth(100);
       gridPane.add(updateButton, 0, 10, 2, 1);
       GridPane.setHalignment(updateButton, HPos.CENTER);
       GridPane.setMargin(updateButton, new Insets(20, 0,20,0));

       updateButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               if(nameField.getText().isEmpty()) {
                   showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter Clinic Name");
                   return;
               }
               if(medField.getText().isEmpty()) {
                   showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter the name of the medication");
                   return;
               }
               if(quantityField.getText().isEmpty()) {
                   showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter quantity");
                   return;
               }else {
                 int q = Integer.parseInt(quantityField.getText());
                 Stock.updateData(nameField.getText(),medField.getText(),q);

               }

               showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Update Complete", "Success");
           }
       });

       Button retButton = new Button("Retrieve");
       retButton.setPrefHeight(40);
       retButton.setDefaultButton(true);
       retButton.setPrefWidth(100);
       gridPane.add(retButton, 0, 10, 2, 1);
       GridPane.setHalignment(retButton, HPos.LEFT);
       GridPane.setMargin(retButton, new Insets(20, 0,20,0));

       retButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {

           }
       });
   }
   private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}

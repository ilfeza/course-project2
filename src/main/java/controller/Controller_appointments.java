package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Model;

import java.sql.SQLException;

public class Controller_appointments implements EventHandler<ActionEvent> {
    ComboBox<String> animalsComboBox;
    Label lbl;

    ComboBox<String> breedsComboBox;

    private Stage stage;
    private Model model;

    private Group root = new Group();
    private Scene scene = new Scene(root, 720, 480);

    public Controller_appointments(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;
        animalsComboBox = new ComboBox<>();
        ObservableList<String> animalsList = model.animals.getAllAnimals2();
        animalsComboBox.setItems(animalsList);

        lbl = new Label();
        animalsComboBox.addEventHandler(ActionEvent.ACTION, this);

        lbl.setLayoutX(200);
        lbl.setLayoutY(100);

        animalsComboBox.setLayoutX(100);
        animalsComboBox.setLayoutY(100);
        root.getChildren().add(animalsComboBox);
        root.getChildren().add(lbl);


        breedsComboBox = new ComboBox<>();
        ObservableList<String> breedsList = model.diseases.getAllDisease2();
        breedsComboBox.setItems(breedsList);

        breedsComboBox.setLayoutX(300);
        breedsComboBox.setLayoutY(100);
        root.getChildren().add(breedsComboBox);


        //FlowPane root = new FlowPane(10, 10, animalsComboBox, lbl);

        //Scene scene = new Scene(root, 720, 480);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == animalsComboBox){
                lbl.setText(model.animals.getOwner(animalsComboBox.getValue()));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

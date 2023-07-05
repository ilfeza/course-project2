package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;

import java.sql.SQLException;

public class Controller_addAnimal  implements EventHandler<ActionEvent> {
    private Stage stage;
    private Model model;
    private Button done = new Button("Готово");
    private Button add = new Button("Добавить");
    private Button addowner= new Button("Добавить владельца");
    private TextField textField_name = new TextField();
    private Label label_name = new Label("Имя животного");
    private Label label_breed = new Label("Порода");
    private Label label_owner = new Label("Владелец");
    private Group root = new Group();
    private Scene scene = new Scene(root, 720, 480);
    ComboBox<String> breedsComboBox;

    ComboBox<String> ownersComboBox;





    public Controller_addAnimal(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;
        label_name.setLayoutX(100);
        label_name.setLayoutY(75);
        root.getChildren().add(label_name);

        textField_name.setLayoutX(100);
        textField_name.setLayoutY(100);
        root.getChildren().add(textField_name);

        label_breed.setLayoutX(300);
        label_breed.setLayoutY(75);
        root.getChildren().add(label_breed);

        breedsComboBox = new ComboBox<>();
        ObservableList<String> breedsList = model.breeds.getAllBreeds2();
        breedsComboBox.setItems(breedsList);

        breedsComboBox.setLayoutX(300);
        breedsComboBox.setLayoutY(100);
        root.getChildren().add(breedsComboBox);


        ownersComboBox = new ComboBox<>();
        ObservableList<String> ownersList = model.owners.getAllOwners();
        ownersComboBox.setItems(ownersList);

        ownersComboBox.setLayoutX(500);
        ownersComboBox.setLayoutY(100);
        root.getChildren().add(ownersComboBox);

        label_owner.setLayoutX(500);
        label_owner.setLayoutY(75);
        root.getChildren().add(label_owner);

        addowner.setLayoutX(100);
        addowner.setLayoutY(300);
        addowner.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(addowner);

        done.setLayoutX(300);
        done.setLayoutY(300);
        done.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(done);

        add.setLayoutX(500);
        add.setLayoutY(300);
        add.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(add);





        stage.setScene(scene);
        stage.show();

    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if(actionEvent.getSource() == addowner) {
                stage.setScene(null);
                new Controller_addOwner(model, stage);
            }
            else if(actionEvent.getSource() == done) {
                stage.setScene(null);
                new Controller_account(model, stage);
            }
            else if(actionEvent.getSource() == add) {
                stage.setScene(null);
                model.animals.addAnimals(textField_name.getText(),breedsComboBox.getValue(),ownersComboBox.getValue());
                new Controller_addAnimal(model, stage);
            }


        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

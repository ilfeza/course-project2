package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;

import java.sql.SQLException;

public class Controller_animals implements EventHandler<ActionEvent> {
    private Stage stage;
    private Model model;

    private Label label_name = new Label("Имя животного");
    private Label label_breed = new Label("Порода");
    private Label label_owner_name = new Label("Имя владельца");
    private Label label_owner_address = new Label("Адрес");
    private Label label_owner_phone = new Label("Телефон");

    private TextField textField_name = new TextField();
    private TextField textField_breed = new TextField();
    private TextField textField_owner_name = new TextField();
    private TextField textField_owner_address = new TextField();
    private TextField textField_owner_phone = new TextField();

    private Button done = new Button("Готово");
    private Button create = new Button("Добавить");

    private Group root = new Group();
    private Scene scene = new Scene(root, 720, 480);

    public Controller_animals(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;

        label_name.setLayoutX(100);
        label_name.setLayoutY(75);
        root.getChildren().add(label_name);

        textField_name.setLayoutX(150);
        textField_name.setLayoutY(100);
        root.getChildren().add(textField_name);

        label_breed.setLayoutX(100);
        label_breed.setLayoutY(125);
        root.getChildren().add(label_breed);

        textField_breed.setLayoutX(150);
        textField_breed.setLayoutY(150);
        root.getChildren().add(textField_breed);

        label_owner_name.setLayoutX(400);
        label_owner_name.setLayoutY(75);
        root.getChildren().add(label_owner_name);

        textField_owner_name.setLayoutX(450);
        textField_owner_name.setLayoutY(100);
        root.getChildren().add(textField_owner_name);

        label_owner_address.setLayoutX(400);
        label_owner_address.setLayoutY(125);
        root.getChildren().add(label_owner_address);

        textField_owner_address.setLayoutX(450);
        textField_owner_address.setLayoutY(150);
        root.getChildren().add(textField_owner_address);

        label_owner_phone.setLayoutX(400);
        label_owner_phone.setLayoutY(175);
        root.getChildren().add(label_owner_phone);

        textField_owner_phone.setLayoutX(450);
        textField_owner_phone.setLayoutY(200);
        root.getChildren().add(textField_owner_phone);

        done.setLayoutX(450);
        done.setLayoutY(300);
        done.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(done);

        create.setLayoutX(300);
        create.setLayoutY(300);
        create.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(create);





        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {

            if (actionEvent.getSource() == done) {
                stage.setScene(null);
                new Controller_account(model, stage);
            }
            if (actionEvent.getSource() == create) {
                model.addAnimal(textField_name.getText(), textField_breed.getText(), textField_owner_name.getText(), textField_owner_address.getText(), textField_owner_phone.getText());
                stage.setScene(null);
                new Controller_animals(model, stage);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

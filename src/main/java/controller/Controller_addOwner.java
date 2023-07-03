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

public class Controller_addOwner implements EventHandler<ActionEvent> {

    private Stage stage;
    private Model model;

    private Group root = new Group();
    private Scene scene = new Scene(root, 720, 480);
    private Button add = new Button("Добавить");
    private Button done = new Button("Готово");

    private Label label_name = new Label("Имя");
    private Label label_phone = new Label("Телефон");
    private Label label_address = new Label("Адрес");

    private TextField textField_name = new TextField();
    private TextField textField_phone = new TextField();
    private TextField textField_address = new TextField();

    public Controller_addOwner(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;

        label_name.setLayoutX(100);
        label_name.setLayoutY(100);
        root.getChildren().add(label_name);

        label_phone.setLayoutX(100);
        label_phone.setLayoutY(200);
        root.getChildren().add(label_phone);

        label_address.setLayoutX(100);
        label_address.setLayoutY(300);
        root.getChildren().add(label_address);

        textField_name.setLayoutX(300);
        textField_name.setLayoutY(100);
        root.getChildren().add(textField_name);

        textField_phone.setLayoutX(300);
        textField_phone.setLayoutY(200);
        root.getChildren().add(textField_phone);

        textField_address.setLayoutX(300);
        textField_address.setLayoutY(300);
        root.getChildren().add(textField_address);


        add.setLayoutX(100);
        add.setLayoutY(400);
        add.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(add);

        done.setLayoutX(300);
        done.setLayoutY(400);
        done.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(done);





        stage.setScene(scene);
        stage.show();

    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if(actionEvent.getSource() == done) {
                stage.setScene(null);
                new Controller_addAnimal(model, stage);
            }
            else if (actionEvent.getSource() == add){
                model.owners.addOwnes(textField_name.getText(),textField_address.getText(),textField_phone.getText());
                stage.setScene(null);
                new Controller_addOwner(model, stage);
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

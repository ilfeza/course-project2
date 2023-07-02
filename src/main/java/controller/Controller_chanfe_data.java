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

public class Controller_chanfe_data implements EventHandler<ActionEvent> {
    private Stage stage;
    private Model model;
    private Group root = new Group();
    private Scene scene = new Scene(root, 720, 480);

    private TextField textField_name = new TextField();
    private TextField textField_address = new TextField();
    private TextField textField_phone_number = new TextField();
    private Button change = new Button("Изменить");
    private Label label_change_data = new Label("Изменить рег. данные");
    private Label label_name = new Label("Имя");
    private Label label_address = new Label("Адрес");
    private Label label_phone_number = new Label("Телефон");




    public Controller_chanfe_data(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;

        label_change_data.setLayoutX(360);
        label_change_data.setLayoutY(20);
        root.getChildren().add(label_change_data);

        label_name.setLayoutX(20);
        label_name.setLayoutY(60);
        root.getChildren().add(label_name);

        label_address.setLayoutX(20);
        label_address.setLayoutY(100);
        root.getChildren().add(label_address);

        label_phone_number.setLayoutX(20);
        label_phone_number.setLayoutY(140);
        root.getChildren().add(label_phone_number);

        textField_name.setLayoutX(220);
        textField_name.setLayoutY(60);
        root.getChildren().add(textField_name);

        textField_address.setLayoutX(220);
        textField_address.setLayoutY(100);
        root.getChildren().add(textField_address);

        textField_phone_number.setLayoutX(220);
        textField_phone_number.setLayoutY(140);
        root.getChildren().add(textField_phone_number);

        change.setLayoutX(360);
        change.setLayoutY(200);
        change.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(change);

        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            stage.setScene(null);
            model.change_data(textField_name.getText(), textField_address.getText(), textField_phone_number.getText());
            new Controller_account(this.model, this.stage);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

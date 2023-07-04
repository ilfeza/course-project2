package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Doctors_password;
import model.Logins;
import model.Model;

import java.sql.SQLException;
import java.util.List;

public class Controller_A_addDoctors  implements EventHandler<ActionEvent> {
    private final Stage stage;
    private final Model model;

    private final Group root = new Group();
    private final Scene scene = new Scene(root, 720, 480);
    private final TextField textField_login = new TextField();
    private final TextField textField_password = new TextField();
    private final TextField textField_name = new TextField();
    private final TextField textField_address = new TextField();
    private final TextField textField_phone = new TextField();

    private final Label label_login = new Label("Логин");
    private final Label label_password = new Label("Пароль");
    private final Label label_erroe = new Label("Значения введены некорректно");
    private final Label label_name = new Label("Имя");
    private final Label label_address = new Label("Адрес");
    private final Label label_phone = new Label("Телефон");

    private final Button done = new Button("Готово");
    private final Button add = new Button("Добавить");

    private Doctors_password selectedDoctors;



    private final Button create = new Button("Добавить");
    private final Button update = new Button("Изменить");
    private final Button delete = new Button("Удалить");

    public Controller_A_addDoctors(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;



        TableView<Doctors_password> table = new TableView<>();
        table.setPrefWidth(360);
        table.setPrefHeight(480);


        TableColumn<Doctors_password, String> loginColumn = new TableColumn<>("login");
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        table.getColumns().add(loginColumn);


        TableColumn<Doctors_password, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(nameColumn);

        TableColumn<Doctors_password, String> addressColumn = new TableColumn<>("Адрес");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        table.getColumns().add(addressColumn);

        TableColumn<Doctors_password, String> phoneColumn = new TableColumn<>("Телефон");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        table.getColumns().add(phoneColumn);

        List<Doctors_password> doctors_passwords = model.doctors_password.getAllDoctors();
        // добавляем породы в таблицу
        table.getItems().addAll(doctors_passwords);

        Pane root = new Pane();
        root.getChildren().add(table);

        Scene scene = new Scene(root, 720, 480);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedDoctors = newSelection;
                textField_login.setText(String.valueOf(selectedDoctors.getLogin()));
                textField_name.setText(selectedDoctors.getName());
                textField_address.setText(selectedDoctors.getAddress());
                textField_phone.setText(selectedDoctors.getPhone());
            }
        });


        textField_login.setLayoutX(400);
        textField_login.setLayoutY(50);
        root.getChildren().add(textField_login);

        textField_password.setLayoutX(400);
        textField_password.setLayoutY(125);
        root.getChildren().add(textField_password);

        textField_name.setLayoutX(400);
        textField_name.setLayoutY(200);
        root.getChildren().add(textField_name);

        textField_address.setLayoutX(400);
        textField_address.setLayoutY(275);
        root.getChildren().add(textField_address);

        textField_phone.setLayoutX(400);
        textField_phone.setLayoutY(350);
        root.getChildren().add(textField_phone);


        label_login.setLayoutX(400);
        label_login.setLayoutY(25);
        root.getChildren().add(label_login);

        label_password.setLayoutX(400);
        label_password.setLayoutY(100);
        root.getChildren().add(label_password);

        label_name.setLayoutX(400);
        label_name.setLayoutY(175);
        root.getChildren().add(label_name);

        label_address.setLayoutX(400);
        label_address.setLayoutY(250);
        root.getChildren().add(label_address);

        label_phone.setLayoutX(400);
        label_phone.setLayoutY(325);
        root.getChildren().add(label_phone);

        create.setLayoutX(370);
        create.setLayoutY(400);
        create.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(create);

        update.setLayoutX(440);
        update.setLayoutY(400);
        update.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(update);

        delete.setLayoutX(510);
        delete.setLayoutY(400);
        delete.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(delete);

        done.setLayoutX(580);
        done.setLayoutY(400);
        done.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(done);

        label_erroe.setLayoutX(600);
        label_erroe.setLayoutY(400);


        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == done) {
                stage.setScene(null);
                new Controller_A_Account(model, stage);
            }
            if (actionEvent.getSource() == create) {
                System.out.println(Logins.hashString(textField_login.getText()));
                model.doctors_password.createDoctors(textField_login.getText(),
                        textField_login.getText(),
                        textField_name.getText(),
                        textField_address.getText(),
                        textField_phone.getText());
                stage.setScene(null);
                new Controller_A_addDoctors(model, stage);
            }
            else if (actionEvent.getSource() == update) {
                model.doctors_password.updeteDoctors(textField_login.getText(),
                        textField_login.getText(),
                        textField_name.getText(),
                        textField_address.getText(),
                        textField_phone.getText());
                stage.setScene(null);
                new Controller_A_addDoctors(model, stage);
            }
            else if (actionEvent.getSource() == delete) {
                model.doctors_password.deleteDoctors(textField_login.getText(),
                        textField_login.getText(),
                        textField_name.getText(),
                        textField_address.getText(),
                        textField_phone.getText());
                stage.setScene(null);
                new Controller_A_addDoctors(model, stage);
            }


        }
        catch (SQLException e){
            System.out.println("1");
            root.getChildren().add(label_erroe);


        }
    }
}

package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Breeds;
import model.Model;

import java.sql.SQLException;
import java.util.List;

public class Controller_A_breeds implements EventHandler<ActionEvent> {
    private Stage stage;
    private Model model;
    private Breeds selectedBreed;



    private Button done = new Button("Готово");

    private TextField textField_id = new TextField();
    private TextField textField_name = new TextField();

    private Label label_name = new Label("Имя");
    private Label label_id = new Label("№");

    private Button create = new Button("Добавить");
    private Button update = new Button("Изменить");
    private Button delete = new Button("Удалить");
    private Group root = new Group();

    public Controller_A_breeds(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;

        System.out.println(model.breeds.getAllBreeds().get(2).getId());


        TableView<Breeds> table = new TableView<>();
        table.setPrefWidth(250);
        table.setPrefHeight(200);


        TableColumn<Breeds, Integer> idColumn = new TableColumn<>("№");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        table.getColumns().add(idColumn);


        TableColumn<Breeds, String> nameColumn = new TableColumn<>("Название");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(nameColumn);

        List<Breeds> breedsList = model.breeds.getAllBreeds();
        // добавляем породы в таблицу
        table.getItems().addAll(breedsList);

        Pane root = new Pane();
        root.getChildren().add(table);
        Scene scene = new Scene(root, 720, 480);
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedBreed = newSelection;
                textField_id.setText(String.valueOf(selectedBreed.getId()));
                textField_name.setText(selectedBreed.getName());
            }
        });









        label_id.setLayoutX(360);
        label_id.setLayoutY(50);
        root.getChildren().add(label_id);

        label_name.setLayoutX(360);
        label_name.setLayoutY(100);
        root.getChildren().add(label_name);

        textField_id.setLayoutX(400);
        textField_id.setLayoutY(50);
        root.getChildren().add(textField_id);

        textField_name.setLayoutX(400);
        textField_name.setLayoutY(100);
        root.getChildren().add(textField_name);

        create.setLayoutX(300);
        create.setLayoutY(200);
        create.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(create);

        update.setLayoutX(370);
        update.setLayoutY(200);
        update.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(update);

        delete.setLayoutX(440);
        delete.setLayoutY(200);
        delete.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(delete);

        done.setLayoutX(300);
        done.setLayoutY(300);
        done.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(done);

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
                model.breeds.createBreeds(textField_id.getText(), textField_name.getText());
                stage.setScene(null);
                new Controller_A_breeds(model, stage);
            } else if (actionEvent.getSource() == update) {
                if (selectedBreed != null) {
                    model.breeds.updeteBreeds(String.valueOf(selectedBreed.getId()), textField_name.getText());
                    stage.setScene(null);
                    new Controller_A_breeds(model, stage);
                }
            } else if (actionEvent.getSource() == delete) {
                if (selectedBreed != null) {
                    model.breeds.deleteBreeds(String.valueOf(selectedBreed.getId()), textField_name.getText());
                    stage.setScene(null);
                    new Controller_A_breeds(model, stage);
                }
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

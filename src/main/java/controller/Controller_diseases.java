package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.BreedDiseaseResult;
import model.Model;

import java.sql.SQLException;
import java.util.List;

public class Controller_diseases implements EventHandler<ActionEvent> {
    private Stage stage;
    private Model model;
    private Button done = new Button("Готово");

    private TextField textField_id = new TextField();
    private TextField textField_breed = new TextField();
    private TextField textField_scientific = new TextField();
    private TextField textField_general = new TextField();

    private Label label_breed = new Label("Порода");
    private Label label_id = new Label("№");
    private Label label_scientific = new Label("Научное название");
    private Label label_general = new Label("Общее название");

    private Button create = new Button("Добавить");
    private Button update = new Button("Изменить");
    private Button delete = new Button("Удалить");
    private BreedDiseaseResult selectedBreed;

    public Controller_diseases(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;

        TableView<BreedDiseaseResult> table = new TableView<>();
        table.setPrefWidth(480);
        table.setPrefHeight(480);

        /*
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedBreed = newSelection;
                textField_id.setText(String.valueOf(selectedBreed.getId()));
                textField_name.setText(selectedBreed.getName());
            }
        })

         */

        TableColumn<BreedDiseaseResult, Integer> idColumn = new TableColumn<>("№");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("breedId"));
        table.getColumns().add(idColumn);

        TableColumn<BreedDiseaseResult, String> breed_name_column = new TableColumn<>("Порода");
        breed_name_column.setCellValueFactory(new PropertyValueFactory<>("breedName"));
        table.getColumns().add(breed_name_column);

        TableColumn<BreedDiseaseResult, String> scientific_name_column = new TableColumn<>("Научное название");
        scientific_name_column.setCellValueFactory(new PropertyValueFactory<>("scientificName"));
        table.getColumns().add(scientific_name_column);

        TableColumn<BreedDiseaseResult, String> disease_general_name_column = new TableColumn<>("Общее название");
        disease_general_name_column.setCellValueFactory(new PropertyValueFactory<>("diseaseGeneralName"));
        table.getColumns().add(disease_general_name_column);

        List<BreedDiseaseResult> breedDiseaseResult = model.breedDiseaseResult.getBreedDiseaseResults();
        // добавляем породы в таблицу
        table.getItems().addAll(breedDiseaseResult);

        Pane root = new Pane();
        root.getChildren().add(table);


        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedBreed = newSelection;
                textField_id.setText(String.valueOf(selectedBreed.getBreedId()));
                textField_breed.setText(selectedBreed.getBreedName());
                textField_scientific.setText(selectedBreed.getScientificName());
                textField_general.setText(selectedBreed.getDiseaseGeneralName());
            }
        });

        label_id.setLayoutX(500);
        label_id.setLayoutY(25);
        root.getChildren().add(label_id);

        label_breed.setLayoutX(500);
        label_breed.setLayoutY(75);
        root.getChildren().add(label_breed);

        label_scientific.setLayoutX(500);
        label_scientific.setLayoutY(125);
        root.getChildren().add(label_scientific);

        label_general.setLayoutX(500);
        label_general.setLayoutY(175);
        root.getChildren().add(label_general);

        textField_id.setLayoutX(550);
        textField_id.setLayoutY(50);
        root.getChildren().add(textField_id);

        textField_breed.setLayoutX(550);
        textField_breed.setLayoutY(100);
        root.getChildren().add(textField_breed);

        textField_scientific.setLayoutX(550);
        textField_scientific.setLayoutY(150);
        root.getChildren().add(textField_scientific);

        textField_general.setLayoutX(550);
        textField_general.setLayoutY(200);
        root.getChildren().add(textField_general);

        create.setLayoutX(500);
        create.setLayoutY(300);
        create.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(create);

        update.setLayoutX(570);
        update.setLayoutY(300);
        update.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(update);

        delete.setLayoutX(640);
        delete.setLayoutY(300);
        delete.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(delete);


        done.setLayoutX(500);
        done.setLayoutY(350);
        done.addEventHandler(ActionEvent.ACTION, this);

        Scene scene = new Scene(root, 720, 480);
        root.getChildren().add(done);

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
            if (actionEvent.getSource() == update) {
                model.breedDiseaseResult.updeteBreedDisease(textField_id.getText(), textField_breed.getText(),textField_scientific.getText(),textField_general.getText());
                stage.setScene(null);
                new Controller_diseases(model, stage);
            }
            else if (actionEvent.getSource() == create){
                model.breedDiseaseResult.createBreedDisease(textField_id.getText(), textField_breed.getText(),textField_scientific.getText(),textField_general.getText());
                stage.setScene(null);
                new Controller_diseases(model, stage);
            }
            else if (actionEvent.getSource() == delete){
                model.breedDiseaseResult.deleteBreedDisease(textField_id.getText(), textField_breed.getText(),textField_scientific.getText(),textField_general.getText());
                stage.setScene(null);
                new Controller_diseases(model, stage);
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}

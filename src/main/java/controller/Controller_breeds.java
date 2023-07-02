package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Breeds;
import model.Model;

import java.sql.SQLException;
import java.util.List;

public class Controller_breeds implements EventHandler<ActionEvent> {
    private Stage stage;
    private Model model;
    private Group root = new Group();
    private Scene scene = new Scene(root, 720, 480);
    private Button done = new Button("Готово");

    public Controller_breeds(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;

        System.out.println(model.breeds.getAllBreeds().get(2).getId());


        TableView<Breeds> table = new TableView<>();
        table.setPrefWidth(250);
        table.setPrefHeight(200);

        // столбец для вывода имени
        TableColumn<Breeds, Integer> idColumn = new TableColumn<>("№");
        // определяем фабрику для столбца с привязкой к свойству id
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        // добавляем столбец
        table.getColumns().add(idColumn);

        // столбец для вывода названия породы
        TableColumn<Breeds, String> nameColumn = new TableColumn<>("Название");
        // определяем фабрику для столбца с привязкой к свойству name
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(nameColumn);

        // получаем список всех пород
        List<Breeds> breedsList = model.breeds.getAllBreeds();
        // добавляем породы в таблицу
        table.getItems().addAll(breedsList);

        FlowPane root = new FlowPane(10, 10, table);
        Scene scene = new Scene(root, 300, 250);



        done.setLayoutX(360);
        done.setLayoutY(200);
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
                new Controller_chanfe_data(model, stage);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

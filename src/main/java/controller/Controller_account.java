package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Model;

import java.sql.SQLException;

public class Controller_account  implements EventHandler<ActionEvent> {
    private Stage stage;
    private Model model;
    private Button change_data = new Button("Изменить рег. данные");
    private Button breeds = new Button("Список пород");
    private Button diseases = new Button("Список распространенных заболеваний");
    private Button animals = new Button("Добавить животное");
    private Button appointment = new Button("Приём");
    private Group root = new Group();
    private Scene scene = new Scene(root, 720, 480);


    public Controller_account(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;

        change_data.setLayoutX(360);
        change_data.setLayoutY(140);
        change_data.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(change_data);
        
        breeds.setLayoutX(360);
        breeds.setLayoutY(200);
        breeds.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(breeds);

        diseases.setLayoutX(360);
        diseases.setLayoutY(260);
        diseases.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(diseases);

        animals.setLayoutX(360);
        animals.setLayoutY(320);
        animals.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(animals);

        appointment.setLayoutX(360);
        appointment.setLayoutY(380);
        appointment.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(appointment);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if(actionEvent.getSource() == change_data) {
                stage.setScene(null);
                new Controller_change_data(model, stage);
            }
            else if (actionEvent.getSource() == breeds){
                stage.setScene(null);
                new Controller_breeds(model,stage);
            }
            else if(actionEvent.getSource() == diseases){
                stage.setScene(null);
                new Controller_diseases(model,stage);
            }
            else if(actionEvent.getSource() == animals){
                stage.setScene(null);
                new Controller_animals(model,stage);
            }
            else if(actionEvent.getSource() == appointment){
                stage.setScene(null);
                new Controller_appointments(model,stage);
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}

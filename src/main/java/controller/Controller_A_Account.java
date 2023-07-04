package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Model;

import java.sql.SQLException;

public class Controller_A_Account  implements EventHandler<ActionEvent> {
    private Stage stage;
    private Model model;
    private Button add_Doctor = new Button("Добавить врача");
    private Button breeds = new Button("Список пород");
    private Button diseases = new Button("Распространенные заболевания");
    private Button animals = new Button("Добавить животное");
    private Button appointment = new Button("Приём");
    private Button addanimal = new Button("Добавить животное");
    private Button exit = new Button("Выход");

    private Group root = new Group();
    private Scene scene = new Scene(root, 720, 480);


    public Controller_A_Account(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;
        System.out.println("1");


        add_Doctor.setLayoutX(300);
        add_Doctor.setLayoutY(50);
        add_Doctor.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(add_Doctor);

        breeds.setLayoutX(300);
        breeds.setLayoutY(250);
        breeds.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(breeds);

        diseases.setLayoutX(300);
        diseases.setLayoutY(200);
        diseases.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(diseases);


        appointment.setLayoutX(300);
        appointment.setLayoutY(100);
        appointment.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(appointment);

        addanimal.setLayoutX(300);
        addanimal.setLayoutY(150);
        addanimal.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(addanimal);

        exit.setLayoutX(300);
        exit.setLayoutY(400);
        exit.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(exit);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {

            if (actionEvent.getSource() == add_Doctor){
                stage.setScene(null);
                new Controller_A_addDoctors(model,stage);
            }

            if (actionEvent.getSource() == breeds){
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
            else if(actionEvent.getSource() == addanimal){
                stage.setScene(null);
                new Controller_addAnimal(model,stage);
            }
            else if(actionEvent.getSource() == exit){
                stage.setScene(null);
                new Controller_authorization(model,stage);
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}

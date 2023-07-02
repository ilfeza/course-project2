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
    private Group root = new Group();
    private Scene scene = new Scene(root, 720, 480);


    public Controller_account(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;

        change_data.setLayoutX(360);
        change_data.setLayoutY(140);
        change_data.addEventHandler(ActionEvent.ACTION, this);
        root.getChildren().add(change_data);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == change_data){


        }

    }
}

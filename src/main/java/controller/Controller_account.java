package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Model;

import java.sql.SQLException;

public class Controller_account  implements EventHandler<ActionEvent> {
    private Stage stage;
    private Model model;

    public Controller_account(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;


    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}

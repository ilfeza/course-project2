package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

import java.sql.SQLException;

public class Controller_authorization implements EventHandler<ActionEvent> {
    private Stage stage;
    private Model model;
    private FlowPane panel;
    private TextField textFieldName;
    private TextField textFieldPassword;
    private Button buttonNext;
    private Label labelA;
    private Label labelL;
    private Label labelP;
    private Label labelE;

    public Controller_authorization(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;

        Group group = new Group();
        Pane s = new Pane();


        panel = new FlowPane();
        panel.setAlignment(Pos.CENTER);
        panel.setPrefSize(720, 480);

        textFieldName = new TextField();
        textFieldPassword = new TextField();
        buttonNext = new Button("Далее");
        labelA = new Label("Вход");
        labelL = new Label("Введите логин");
        labelP = new Label("Введите пароль");
        labelE = new Label("Неверный логин или пароль");

        // Установите расположение и стиль элементов управления, добавьте их в панель

        buttonNext.setOnAction(this);
        panel.setVisible(false);


    }

    @Override
    public void handle(ActionEvent event) {
        try {
            if (model.authorization(textFieldName.getText(), textFieldPassword.getText()) > 0) {
                System.out.println("!!!!");
                panel.setVisible(false);
                model.authentications.setLogin(textFieldName.getText());
                model.authentications.setHash_password(textFieldPassword.getText());
                //new Authentications(textFieldName.getText(), textFieldPassword.getText());
                //new ControllerAccount(model, screen);
            } else {
                panel.setVisible(false);
                panel.getChildren().add(labelE);
                panel.setVisible(true);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

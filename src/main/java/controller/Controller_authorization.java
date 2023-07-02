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

public class Controller_authorization implements EventHandler<ActionEvent> {
    private Stage stage;
    private Model model;
    private TextField textFieldName;
    private TextField textFieldPassword;
    private Button next;
    private Label labelA;
    private Label labelL;
    private Label labelP;
    private Label labelE;
    private Group root = new Group();
    private Scene scene = new Scene(root, 720, 480);

    public Controller_authorization(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;


        textFieldName = new TextField();
        textFieldName.setLayoutX(220);
        textFieldName.setLayoutY(60);
        root.getChildren().add(textFieldName);

        textFieldPassword = new TextField();
        textFieldPassword.setLayoutX(220);
        textFieldPassword.setLayoutY(100);
        root.getChildren().add(textFieldPassword);

        labelA = new Label("Вход");
        labelA.setLayoutX(360);
        labelA.setLayoutY(20);
        root.getChildren().add(labelA);

        labelL = new Label("Логин");
        labelL.setLayoutX(20);
        labelL.setLayoutY(60);
        root.getChildren().add(labelL);

        labelP = new Label("Пароль");
        labelP.setLayoutX(20);
        labelP.setLayoutY(100);
        root.getChildren().add(labelP);

        next = new Button("Далее");
        next.addEventHandler(ActionEvent.ACTION, this);
        next.setLayoutX(360);
        next.setLayoutY(140);
        root.getChildren().add(next);


        stage.setScene(scene);
        stage.show();


        /*
        Pane s = new Pane();


        panel = new FlowPane();
        panel.setAlignment(Pos.CENTER);
        panel.setPrefSize(720, 480);


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

         */
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if(model.authorization(textFieldName.getText(),textFieldPassword.getText())>0){
                System.out.println("!!!!");
                stage.setScene(null);
                model.authentications.setLogin(textFieldName.getText());
                model.authentications.setHash_password(textFieldPassword.getText());
                //new Authentications(textField_name.getText(), textField_password.getText());
                //new Controller_account(this.model, this.stage);
            }
            else{
                labelE = new Label("Неверный логин или пароль");
                labelE.setLayoutX(360);
                labelE.setLayoutY(200);
                root.getChildren().add(labelE);
                stage.setScene(scene);
                stage.show();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }
}

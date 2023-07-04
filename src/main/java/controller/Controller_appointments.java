package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;

import java.sql.SQLException;

public class Controller_appointments implements EventHandler<ActionEvent> {
    ComboBox<String> animalsComboBox;
    Label lbl;

    ComboBox<String> breedsComboBox;

    private Label label_name = new Label("Кличка");
    private Label label_nameOwner = new Label("Имя владельца");
    private Label label_disease = new Label("Заболевание");
    private Label label_date = new Label("Дата и время(YYYY-MM-DD hh:mm:ss)");
    private Label label_error = new Label("Неправильный формат значений");
    private Button add_appointment= new Button("Добавить");
    private TextField textField_date = new TextField();
    private Button done = new Button("Готово");

    private Stage stage;
    private Model model;

    private Group root = new Group();
    private Scene scene = new Scene(root, 720, 480);

    public Controller_appointments(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;
        animalsComboBox = new ComboBox<>();
        ObservableList<String> animalsList = model.animals.getAllAnimals2();
        animalsComboBox.setItems(animalsList);

        lbl = new Label();
        animalsComboBox.addEventHandler(ActionEvent.ACTION, this);

        lbl.setLayoutX(200);
        lbl.setLayoutY(100);

        label_nameOwner.setLayoutX(200);
        label_nameOwner.setLayoutY(75);
        root.getChildren().add(label_nameOwner);

        label_name.setLayoutX(100);
        label_name.setLayoutY(75);
        root.getChildren().add(label_name);

        animalsComboBox.setLayoutX(100);
        animalsComboBox.setLayoutY(100);
        root.getChildren().add(animalsComboBox);
        root.getChildren().add(lbl);

        textField_date.setLayoutX(300);
        textField_date.setLayoutY(100);
        root.getChildren().add(textField_date);

        label_date.setLayoutX(300);
        label_date.setLayoutY(75);
        root.getChildren().add(label_date);




        breedsComboBox = new ComboBox<>();
        ObservableList<String> breedsList = model.diseases.getAllDisease2();
        breedsComboBox.setItems(breedsList);
        label_disease.setLayoutX(100);
        label_disease.setLayoutY(175);
        root.getChildren().add(label_disease);

        breedsComboBox.setLayoutX(100);
        breedsComboBox.setLayoutY(200);
        root.getChildren().add(breedsComboBox);

        done.setLayoutX(400);
        done.setLayoutY(300);
        done.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(done);

        add_appointment.setLayoutX(500);
        add_appointment.setLayoutY(300);
        add_appointment.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(add_appointment);

        label_error.setLayoutX(100);
        label_error.setLayoutY(300);
        label_error.setTextFill(Color.RED);


        //FlowPane root = new FlowPane(10, 10, animalsComboBox, lbl);

        //Scene scene = new Scene(root, 720, 480);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == animalsComboBox){
                lbl.setText(model.animals.getOwner(animalsComboBox.getValue()));
            }
            else if(actionEvent.getSource() == done) {
                stage.setScene(null);
                new Controller_account(model, stage);
            }
            else if(actionEvent.getSource() == add_appointment) {
                if (textField_date.getText()=="" || animalsComboBox.getValue()=="" || lbl.getText()=="" || breedsComboBox.getValue()==""){
                    root.getChildren().add(label_error);
                }
                //System.out.println(animalsComboBox.getValue());
                else {
                    model.addAppointnent(textField_date.getText(), animalsComboBox.getValue(),lbl.getText(),breedsComboBox.getValue());
                    stage.setScene(null);
                    new Controller_account(model, stage);
                }

            }

        } catch (SQLException e) {
            //stage.setScene(null);



            throw new RuntimeException(e);
        }

    }
}

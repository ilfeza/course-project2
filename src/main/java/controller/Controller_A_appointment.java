package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.AppoinmentsName;
import model.Breeds;
import model.Model;

import java.sql.SQLException;
import java.util.List;

public class Controller_A_appointment implements EventHandler<ActionEvent> {
    ComboBox<String> animalsComboBox;
    Label lbl;

    ComboBox<String> breedsComboBox;
    private Breeds selectedAppoint;


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

    public Controller_A_appointment(Model model, Stage stage) throws SQLException {
        this.model = model;
        this.stage = stage;


        TableView<AppoinmentsName> table = new TableView<>();
        table.setPrefWidth(720);
        table.setPrefHeight(200);
        table.setLayoutY(280);


        TableColumn<AppoinmentsName, String> dateColumn = new TableColumn<>("date & time");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_and_time"));
        table.getColumns().add(dateColumn);


        TableColumn<AppoinmentsName, String> nameColumn = new TableColumn<>("Кличка");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("animals_name"));
        table.getColumns().add(nameColumn);

        TableColumn<AppoinmentsName, String> ownerColumn = new TableColumn<>("Владелец");
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owners_name"));
        table.getColumns().add(ownerColumn);

        TableColumn<AppoinmentsName, String> doctorsColumn = new TableColumn<>("Врач");
        doctorsColumn.setCellValueFactory(new PropertyValueFactory<>("doctors_name"));
        table.getColumns().add(doctorsColumn);


        List<AppoinmentsName> appoinmentsName = model.appointments.getAllAppoinments();
        // добавляем породы в таблицу
        table.getItems().addAll(appoinmentsName);

        Pane root = new Pane();
        root.getChildren().add(table);
        Scene scene = new Scene(root, 720, 480);



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
        done.setLayoutY(200);
        done.addEventHandler(ActionEvent.ACTION,this);
        root.getChildren().add(done);

        add_appointment.setLayoutX(500);
        add_appointment.setLayoutY(200);
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
                new Controller_A_account(model, stage);
            }
            else if(actionEvent.getSource() == add_appointment) {
                if (textField_date.getText()=="" || animalsComboBox.getValue()=="" || lbl.getText()=="" || breedsComboBox.getValue()==""){
                    root.getChildren().add(label_error);
                }
                //System.out.println(animalsComboBox.getValue());
                else {
                    model.addAppointnent(textField_date.getText(), animalsComboBox.getValue(),lbl.getText(),breedsComboBox.getValue());
                    stage.setScene(null);
                    new controller.Controller_A_appointments(model, stage);
                }

            }

        } catch (SQLException e) {
            //stage.setScene(null);



            throw new RuntimeException(e);
        }

    }
}

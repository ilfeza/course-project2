package main;

import controller.Controller_authorization;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        new Controller_authorization(model, primaryStage);

        /*

        Group group = new Group();
        Scene scene = new Scene(group, 720, 480);

        primaryStage.setScene(scene);

        primaryStage.show();

         */
    }
}

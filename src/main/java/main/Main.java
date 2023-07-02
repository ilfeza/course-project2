package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();

        Group group = new Group();
        Scene scene = new Scene(group, 720, 480);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}

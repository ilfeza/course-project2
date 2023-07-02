package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Screen extends Application {
    private static int _WIDTH = 720;
    private static int _HEIGHT = 480;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Veterinary clinic");
        primaryStage.setWidth(_WIDTH);
        primaryStage.setHeight(_HEIGHT);
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        FlowPane root = new FlowPane();
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }
}


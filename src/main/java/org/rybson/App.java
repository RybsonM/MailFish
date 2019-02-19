package org.rybson;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.rybson.view.ViewFactory;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        ViewFactory viewFactory = ViewFactory.defaultFactory;
        Scene scene = viewFactory.getMainScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

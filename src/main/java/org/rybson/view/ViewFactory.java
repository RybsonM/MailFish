package org.rybson.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.rybson.controller.AbstractController;
import org.rybson.controller.EmailDetailsController;
import org.rybson.controller.MainViewController;
import org.rybson.controller.ModelAccess;

import java.io.IOException;

public class ViewFactory {

    private final String DEFAULT_CSS = "css/style.css";
    private final String EMAIL_DETAILS_FXML = "fxml/EmailDetailsLayout.fxml";
    private final String MAIN_SCREEN_FXML = "fxml/MainLayout.fxml";

    public static ViewFactory defaultFactory = new ViewFactory();
    private ModelAccess modelAccess = new ModelAccess();
    private MainViewController mainViewController;
    private EmailDetailsController emailDetailsController;

    public Scene getMainScene() {
        mainViewController = new MainViewController(modelAccess);
        return initializeScene(MAIN_SCREEN_FXML, mainViewController);
    }

    public Scene getEmailDetailsScene() {
        emailDetailsController = new EmailDetailsController(modelAccess);
        return initializeScene(EMAIL_DETAILS_FXML, emailDetailsController);
    }

    private Scene initializeScene(String fxmlPath, AbstractController controller) {
        FXMLLoader loader;
        Parent parent;
        Scene scene;
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlPath));
            loader.setController(controller);
            parent = loader.load();
        } catch (IOException e) {
            return null;
        }
        scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getClassLoader().getResource(DEFAULT_CSS).toExternalForm());
        return scene;
    }
}

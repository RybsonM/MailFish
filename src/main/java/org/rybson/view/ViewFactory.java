package org.rybson.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public Node resolveIcon(String treeItemValue){
        String lowerCaseTreeItemValue = treeItemValue.toLowerCase();
        ImageView returnIcon;
        try {
            if(lowerCaseTreeItemValue.contains("inbox")){
                returnIcon= new ImageView(new Image(getClass().getClassLoader().getResource("images/inbox.png").toExternalForm()));
            } else if(lowerCaseTreeItemValue.contains("sent")){
                returnIcon= new ImageView(new Image(getClass().getClassLoader().getResource("images/sent2.png").toExternalForm()));
            } else if(lowerCaseTreeItemValue.contains("spam")){
                returnIcon= new ImageView(new Image(getClass().getClassLoader().getResource("images/spam.png").toExternalForm()));
            } else if(lowerCaseTreeItemValue.contains("@")){
                returnIcon= new ImageView(new Image(getClass().getClassLoader().getResource("images/email.png").toExternalForm()));
            } else{
                returnIcon= new ImageView(new Image(getClass().getClassLoader().getResource("images/folder.png").toExternalForm()));
            }
        } catch (NullPointerException e) {
            System.out.println("Invalid image location!!!");
            e.printStackTrace();
            returnIcon = new ImageView();
        }

        returnIcon.setFitHeight(16);
        returnIcon.setFitWidth(16);

        return returnIcon;
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

package org.rybson.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.rybson.model.EmailMessageBean;
import org.rybson.model.SampleData;
import org.rybson.service.MainViewService;
import org.rybson.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends AbstractController implements Initializable {

    @FXML
    private TreeView<String> emailFoldersTreeView;
    private TreeItem<String> root = new TreeItem<>();
    private MenuItem showDetails = new MenuItem("show details");
    @FXML
    private TableView<EmailMessageBean> emailTableView;
    @FXML
    private TableColumn<EmailMessageBean, String> subjectCol;
    @FXML
    private TableColumn<EmailMessageBean, String> senderCol;
    @FXML
    private TableColumn<EmailMessageBean, String> sizeCol;
    @FXML
    private WebView messageRenderer;
    @FXML
    private Button buttonOne;

    private SampleData dataSample = new SampleData();


    public MainViewController(ModelAccess modelAccess) {
        super(modelAccess);
    }

    @FXML
    void buttonOneAction(ActionEvent event) {
        System.out.println("Clicked");
    }
    public void initialize(URL location, ResourceBundle resources) {
        ViewFactory viewFactory = ViewFactory.defaultFactory;
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        senderCol.setCellValueFactory(new PropertyValueFactory<>("sender"));
        sizeCol.setCellValueFactory(new PropertyValueFactory("size"));
        MainViewService.sizeComparator();

        emailFoldersTreeView.setRoot(root);
        root.setValue("ryba.marcin.test@gmail.com");
        TreeItem<String> inbox = new TreeItem<>("Inbox", resolveIcon("Inbox"));
        TreeItem<String> sent = new TreeItem<>("Sent", resolveIcon("Sent"));
        TreeItem<String> subItemOne = new TreeItem<>("SubItemOne", resolveIcon("Folder"));
        TreeItem<String> subItemTwo = new TreeItem<>("SubItemTwo", resolveIcon("Folder"));
        sent.getChildren().addAll(subItemOne, subItemTwo);
        TreeItem<String> spam = new TreeItem<>("Spam", resolveIcon("Spam"));
        TreeItem<String> trash = new TreeItem<>("Trash", resolveIcon("Trash"));

        root.getChildren().addAll(inbox, sent, spam, trash);

        root.setValue("ryba.marcin.test@gmail.com");
        root.setGraphic(resolveIcon(root.getValue()));
        root.setExpanded(true);
        emailTableView.setContextMenu(new ContextMenu(showDetails));

        emailFoldersTreeView.setOnMouseClicked(e -> {
            TreeItem<String> item = emailFoldersTreeView.getSelectionModel().getSelectedItem();
            if (item != null) {
                emailTableView.setItems(dataSample.emailFolders.get(item.getValue()));
            }
        });
        emailTableView.setOnMouseClicked(e -> {
            EmailMessageBean message = emailTableView.getSelectionModel().getSelectedItem();
            if (message != null) {
                getModelAccess().setSelectedMessage(message);
                messageRenderer.getEngine().loadContent(message.getContent());
            }
        });
        showDetails.setOnAction(e -> {
            Scene scene = viewFactory.getEmailDetailsScene();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            System.out.println("menu item clicked");
        });
    }

    private Node resolveIcon(String treeItemValue) {
        String lowerCaseTreeItemValue = treeItemValue.toLowerCase();
        ImageView returnIcon;
        try {
            if (lowerCaseTreeItemValue.contains("inbox"))
                returnIcon = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/inbox.png")));
            else if (lowerCaseTreeItemValue.contains("sent"))
                returnIcon = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/sent2.png")));
            else if (lowerCaseTreeItemValue.contains("spam"))
                returnIcon = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/spam.png")));
            else if (lowerCaseTreeItemValue.contains("@"))
                returnIcon = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/email.png")));

            else if (lowerCaseTreeItemValue.contains("trash"))
                returnIcon = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/folder.png")));
            else
                returnIcon = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/folder.png")));
        } catch (NullPointerException e) {
            System.out.println("Invalid image location");
            e.printStackTrace();
            return new ImageView();
        }
        returnIcon.setFitHeight(16);
        returnIcon.setFitWidth(16);
        return returnIcon;
    }
}

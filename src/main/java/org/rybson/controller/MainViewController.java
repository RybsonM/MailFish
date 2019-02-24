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
import org.rybson.controller.services.CreateAndRegisterEmailAccountService;
import org.rybson.model.EmailMessageBean;
import org.rybson.model.SampleData;
import org.rybson.model.folder.EmailFolderBean;
import org.rybson.model.table.BoldableRowFactor;
import org.rybson.service.MainViewService;
import org.rybson.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends AbstractController implements Initializable {

    @FXML
    private TreeView<String> emailFoldersTreeView;
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

    @FXML
    void changeReadAction() {
        EmailMessageBean message = getModelAccess().getSelectedMessage();
        if (message != null) {
            boolean value = message.isRead();
            message.setRead(!value);
            EmailFolderBean<String> selectedFolder = getModelAccess().getSelectedFolder();
            if (selectedFolder != null) {
                if (value) {
                    selectedFolder.incrementUnreadMessagesCount(1);
                } else {
                    selectedFolder.decrementUnreadedMessagesCount();
                }
            }
        }
    }

    public MainViewController(ModelAccess modelAccess) {
        super(modelAccess);
    }

    public void initialize(URL location, ResourceBundle resources) {
        changeReadAction();
        emailTableView.setRowFactory(e -> new BoldableRowFactor<>());
        ViewFactory viewFactory = ViewFactory.defaultFactory;
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        senderCol.setCellValueFactory(new PropertyValueFactory<>("Sender"));
        sizeCol.setCellValueFactory(new PropertyValueFactory("Size"));
        MainViewService.sizeComparator();

        EmailFolderBean<String> root = new EmailFolderBean<>("");
        emailFoldersTreeView.setRoot(root);
        emailFoldersTreeView.setShowRoot(false);

        CreateAndRegisterEmailAccountService createAndRegisterEmailAccountService =
                new CreateAndRegisterEmailAccountService("marcin.ryba20.test@gmail.com", "marcin.ryb@20", root, getModelAccess());
        createAndRegisterEmailAccountService.start();

        emailTableView.setContextMenu(new ContextMenu(showDetails));

        emailFoldersTreeView.setOnMouseClicked(e -> {
            EmailFolderBean<String> item = (EmailFolderBean<String>) emailFoldersTreeView.getSelectionModel().getSelectedItem();
            if (item != null && !item.isTopElement()) {
                emailTableView.setItems(item.getData());
                getModelAccess().setSelectedFolder(item);
                getModelAccess().setSelectedMessage(null);
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
}

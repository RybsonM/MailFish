package org.rybson.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import org.rybson.model.EmailMessageBean;

import java.net.URL;
import java.util.ResourceBundle;

public class EmailDetailsController extends AbstractController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label senderLabel;

    public EmailDetailsController(ModelAccess modelAccess) {
        super(modelAccess);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        EmailMessageBean selectedMessage = getModelAccess().getSelectedMessage();
        System.out.println("EmailDetails initializable");
        subjectLabel.setText("Subject: " + selectedMessage.getSubject());
        senderLabel.setText("Sender: " + selectedMessage.getSender());

        webView.getEngine().loadContent(selectedMessage.getContent());
    }
}

package org.rybson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.rybson.model.EmailAccountBean;
import org.rybson.model.EmailMessageBean;

public class TestApp {

    public static void main(String[] args) {
        final EmailAccountBean accountBean = new EmailAccountBean("marcin.ryba20.test@gmail.com", "marcin.ryb@20");
        ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();
        accountBean.addEmailsToData(data);
    }
}

package org.rybson.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SampleData {

    public static final ObservableList<EmailMessageBean> Inbox = FXCollections.observableArrayList(
            new EmailMessageBean("Where is my money?", "tomek@yahoo.com", 5500000, "<html>Hello from Tomek!!!<h3>xDxD + <br> </h3></html>", true)

    );
    public static final ObservableList<EmailMessageBean> Sent = FXCollections.observableArrayList(
            new EmailMessageBean("Hi! long time, no see", "example@yahoo.com", 22, "<html>Hi! long time, no see<h3>KINGA+ <br> </h3></html>", false)
    );
    public static final ObservableList<EmailMessageBean> Spam = FXCollections.observableArrayList(
            new EmailMessageBean("You won't belive this: click and see!!!", "serious@company.com", 22000000, "<html>You are smart<h3>Kinga + <br> </h3></html>", false),
            new EmailMessageBean("Like and share if you care!!", "book@face.com", 200, "<html>Your likes make a huge difference!!!<h3>Kinga + <br> </h3></html>", false)
    );
}

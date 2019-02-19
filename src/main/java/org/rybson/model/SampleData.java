package org.rybson.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public class SampleData {

    private final ObservableList<EmailMessageBean> Inbox = FXCollections.observableArrayList(
            new EmailMessageBean("Hello from Sefu!!!", "aaa@yahoo.com", 5500000, "<html>Hello from Sefu!!!<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("Hello from Barosanu", "bbb@yahoo.com", 200, "<html>Hello from Barosanu<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("Hello from Sefu!!!asdas", "ccc@yahoo.com", 10, "<html>Hello from Sefu!!!asdas<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("Hello from Barosanuasdasas", "ddd@yahoo.com", 6300, "<html>Hello from Barosanuasdasas<h3>lorem ipsum + <br> </h3></html>")
    );
    private final ObservableList<EmailMessageBean> Sent = FXCollections.observableArrayList(
            new EmailMessageBean("Hi! long time, no see", "example@yahoo.com", 22, "<html>Hi! long time, no see<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("Hi! Check this!!", "example@yahoo.com", 200, "<html>You are the best<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("Where are my money???", "example@yahoo.com", 10, "<html>You ask me???<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("Escape plans", "example@yahoo.com", 6300, "<html>Basement and then tun to the left<h3>lorem ipsum + <br> </h3></html>")
    );
    private final ObservableList<EmailMessageBean> Spam = FXCollections.observableArrayList(
            new EmailMessageBean("You won't belive this: click and see!!!", "serious@company.com", 22000000, "<html>You are smart<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("Like and share if you care!!", "book@face.com", 200, "<html>Your likes make a huge difference!!!<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("You just won 2 cents! Click to claim!", "ss@bail.com", 10, "<html>Give us first 3 cents!<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("You got a virus!", "safe@brousing.com", 6300, "<html>Formatting computer.....<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("You won't belive this: click and see!!!", "serious@company.com", 22000000, "<html>You are smart<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("Like and share if you care!!", "book@face.com", 200, "<html>Your likes make a huge difference!!!<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("You just won 2 cents! Click to claim!", "ss@bail.com", 10, "<html>Give us first 3 cents!<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("You got a virus!", "safe@brousing.com", 6300, "<html>Formatting computer.....<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("You won't belive this: click and see!!!", "serious@company.com", 22000000, "<html>You are smart<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("Like and share if you care!!", "book@face.com", 200, "<html>Your likes make a huge difference!!!<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("You just won 2 cents! Click to claim!", "ss@bail.com", 10, "<html>Give us first 3 cents!<h3>lorem ipsum + <br> </h3></html>"),
            new EmailMessageBean("You got a virus!", "safe@brousing.com", 6300, "<html>Formatting computer.....<h3>lorem ipsum + <br> </h3></html>")
    );
    public Map<String, ObservableList<EmailMessageBean>> emailFolders = new HashMap<>();

    public SampleData(){
        emailFolders.put("Inbox", Inbox);
        emailFolders.put("Sent", Sent);
        emailFolders.put("Spam", Spam);
    }


}

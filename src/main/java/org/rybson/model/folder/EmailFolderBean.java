package org.rybson.model.folder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import org.rybson.model.EmailMessageBean;
import org.rybson.view.ViewFactory;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;

public class EmailFolderBean<T> extends TreeItem<String> {

    private boolean topElement = false;
    private int unreadMessage;
    private String name;
    private String complateName;
    private ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();

    //Constructor for top elements.
    public EmailFolderBean(String value) {
        super(value, ViewFactory.defaultFactory.resolveIcon(value));
        this.name = value;
        this.complateName = value;
        topElement = true;
        this.setExpanded(true);
    }

    public EmailFolderBean(String value, String completeName) {
        super(value, ViewFactory.defaultFactory.resolveIcon(value));
        this.name = value;
        this.complateName = completeName;
    }

    private void updateValue() {
        if (unreadMessage > 0) {
            this.setValue((String) (name + "(" + unreadMessage + ")"));
        } else {
            this.setValue(name);
        }
    }

    public void incrementUnreadMessagesCount(int newMessages) {
        unreadMessage = unreadMessage + newMessages;
        updateValue();
    }

    public void decrementUnreadedMessagesCount() {
        unreadMessage--;
        updateValue();
    }


    public boolean isTopElement() {
        return topElement;
    }

    public ObservableList<EmailMessageBean> getData() {
        return data;
    }

    public void addEmail(int possition, Message message) throws MessagingException {
        boolean isRead = message.getFlags().contains(Flags.Flag.SEEN);
        EmailMessageBean emailMessageBean = new EmailMessageBean(message.getSubject(),
                message.getFrom()[0].toString(),
                message.getSize(),
                "",
                isRead);
        if (possition < 0) {
            data.add(emailMessageBean);
        } else {
            data.add(possition, emailMessageBean);
        }
        if (!isRead) {
            incrementUnreadMessagesCount(1);
        }

    }
}
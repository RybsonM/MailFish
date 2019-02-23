package org.rybson.model;

import javafx.beans.property.SimpleStringProperty;
import org.rybson.model.table.AbstractTableItem;

import java.util.HashMap;
import java.util.Map;

public class EmailMessageBean extends AbstractTableItem {

    public static Map<String, Integer> formattedValues = new HashMap<>();
    private SimpleStringProperty subject;
    private SimpleStringProperty sender;
    private SimpleStringProperty size;
    private String content;

    public EmailMessageBean(String subject, String sender, int size, String content, boolean isRead) {
        super(isRead);
        this.subject = new SimpleStringProperty(subject);
        this.sender = new SimpleStringProperty(sender);
        this.size = new SimpleStringProperty(formatSize(size));
        this.content = content;
    }

    public static Map<String, Integer> getFormattedValues() {
        return formattedValues;
    }

    private String formatSize(int size) {
        String returnValue;
        if (size <= 0) {
            returnValue = "0";
        } else if (size < 1024) {
            returnValue = size + " B";
        } else if (size < 1048576) {
            returnValue = size / 1024 + " kB";
        } else {
            returnValue = size / 1048576 + " MB";
        }
        formattedValues.put(returnValue, size);
        return returnValue;
    }

    public String getSubject() {
        return subject.get();
    }

    public String getContent() {
        return content;
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public String getSender() {
        return sender.get();
    }

    public SimpleStringProperty senderProperty() {
        return sender;
    }

    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    @Override
    public String toString() {
        return "EmailMessageBean{" +
                "subject=" + subject +
                ", sender=" + sender +
                ", size=" + size +
                ", content='" + content + '\'' +
                '}';
    }
}

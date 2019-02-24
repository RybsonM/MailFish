package org.rybson.controller;

import org.rybson.model.EmailMessageBean;
import org.rybson.model.folder.EmailFolderBean;

import javax.mail.Folder;
import java.util.ArrayList;
import java.util.List;

public class ModelAccess {

    private EmailMessageBean selectedMessage;
    private EmailFolderBean<String> selectedFolder;
    private List<Folder> folderList = new ArrayList<>();

    public EmailMessageBean getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(EmailMessageBean selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public EmailFolderBean<String> getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedFolder(EmailFolderBean<String> selectedFolder) {
        this.selectedFolder = selectedFolder;
    }

    public List<Folder> getFolderList() {
        return folderList;
    }

    public void addFolder(Folder folder) {
        folderList.add(folder);
    }
}

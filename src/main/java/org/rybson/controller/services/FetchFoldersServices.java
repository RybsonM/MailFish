package org.rybson.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.rybson.controller.ModelAccess;
import org.rybson.model.EmailAccountBean;
import org.rybson.model.folder.EmailFolderBean;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;

public class FetchFoldersServices extends Service<Void> {

    private EmailFolderBean<String> foldersRoot;
    private EmailAccountBean emailAccountBean;
    private ModelAccess modelAccess;
    private static int NUMBER_OF_FETCH_FOLDER_SERVICES_ACTIVE = 0;

    public FetchFoldersServices(EmailFolderBean<String> foldersRoot, EmailAccountBean emailAccountBean, ModelAccess modelAccess) {
        this.foldersRoot = foldersRoot;
        this.emailAccountBean = emailAccountBean;
        this.modelAccess = modelAccess;
        this.setOnSucceeded(e ->
                NUMBER_OF_FETCH_FOLDER_SERVICES_ACTIVE--);
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                NUMBER_OF_FETCH_FOLDER_SERVICES_ACTIVE++;
                if (emailAccountBean != null) {
                    Folder[] folders = emailAccountBean.getStore().getDefaultFolder().list();
                    for (Folder folder : folders) {
                        modelAccess.addFolder(folder);
                        EmailFolderBean<String> item = new EmailFolderBean<>(folder.getName(), folder.getFullName());
                        foldersRoot.getChildren().add(item);
                        item.setExpanded(true);
                        addMessageListener(folder, item);

                        FetchMessagesServices fetchMessagesMainFolder = new FetchMessagesServices(item, folder);
                        fetchMessagesMainFolder.start();

                        System.out.println("Added: " + folder.getName());

                        Folder[] subFolders = folder.list();
                        for (Folder subFolder : subFolders) {
                            modelAccess.addFolder(subFolder);
                            EmailFolderBean<String> subItem = new EmailFolderBean<>(subFolder.getName(), subFolder.getFullName());
                            item.getChildren().add(subItem);
                            addMessageListener(subFolder, subItem);

                            FetchMessagesServices fetchMessagesSubFolder = new FetchMessagesServices(subItem, subFolder);
                            fetchMessagesSubFolder.start();

                            System.out.println("Added: " + subFolder.getName());
                        }
                    }
                }
                return null;
            }
        };
    }

    private void addMessageListener(Folder folder, EmailFolderBean<String> item) {
        folder.addMessageCountListener(new MessageCountAdapter() {
            @Override
            public void messagesAdded(MessageCountEvent e) {
                for (int i = 0; i < e.getMessages().length; i++) {
                    try {
                        Message currentMessage = folder.getMessage(folder.getMessageCount() - i);
                        item.addEmail(0, currentMessage);
                    } catch (MessagingException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    public static boolean noServiceActive() {
        return NUMBER_OF_FETCH_FOLDER_SERVICES_ACTIVE == 0;
    }
}
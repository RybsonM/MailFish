package org.rybson.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.rybson.controller.ModelAccess;
import org.rybson.model.EmailAccountBean;
import org.rybson.model.EmailConstants;
import org.rybson.model.folder.EmailFolderBean;

public class CreateAndRegisterEmailAccountService extends Service<Integer> {

    private String emailAddress;
    private String password;
    private EmailFolderBean<String> folderRoot;
    private ModelAccess modelAccess;

    public CreateAndRegisterEmailAccountService(String emailAddress, String password, EmailFolderBean<String> folderRoot, ModelAccess modelAccess) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.folderRoot = folderRoot;
        this.modelAccess = modelAccess;
    }

    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() {
                EmailAccountBean emailAccountBean = new EmailAccountBean(emailAddress, password);
                if (emailAccountBean.getLoginState() == EmailConstants.LOGIN_STATE_SUCCEDED) {
                    EmailFolderBean<String> emailFolderBean = new EmailFolderBean<>(emailAddress);
                    folderRoot.getChildren().add(emailFolderBean);
                    FetchFoldersServices fetchFoldersServices = new FetchFoldersServices(emailFolderBean, emailAccountBean, modelAccess);
                    fetchFoldersServices.start();
                }
                return 0;
            }
        };
    }
}
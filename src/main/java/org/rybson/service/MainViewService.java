package org.rybson.service;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import org.rybson.model.EmailMessageBean;

import java.util.Comparator;

public class MainViewService {

    @FXML
    private static TableColumn<EmailMessageBean, String> sizeCol = new TableColumn<>();

    public static void sizeComparator() {
        sizeCol.setComparator(new Comparator<String>() {
            Integer firstValue, secondValue;

            @Override
            public int compare(String o1, String o2) {
                firstValue = EmailMessageBean.getFormattedValues().get(o1);
                secondValue = EmailMessageBean.getFormattedValues().get(o2);
                return firstValue.compareTo(secondValue);
            }
        });
    }
}
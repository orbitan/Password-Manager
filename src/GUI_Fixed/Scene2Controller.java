package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Scene2Controller implements Initializable {
    @FXML
    private ListView<String> myListView;

    @FXML
    private Text myLabel;

    @FXML
    private Text passwortFeld;

    Map<String, String> passwoerter = new HashMap<String, String>() {{
        put("Amazon", "amazonpasswort");
        put("Google", "googlepasswort");
        put("myCampus", "myCampuspasswort");
        put("Reddit", "amazonpasswort");
        put("HackerRank", "googlepasswort");
        put("Example", "myCampuspasswort");
        put("Example2", "amazonpasswort");
        put("Example3", "googlepasswort");
        put("Example4", "myCampuspasswort");
        put("lOREM", "amazonpasswort");
        put("ePsum", "googlepasswort");
        put("worte", "myCampuspasswort");
        put("w3Source", "amazonpasswort");
        put("careFS", "googlepasswort");
        put("IUBH", "myCampuspasswort");
        put("bing", "amazonpasswort");
        put("edge", "googlepasswort");
        put("windows", "myCampuspasswort");
    }};

    String currentPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myListView.getItems().addAll(passwoerter.keySet());
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentPage = myListView.getSelectionModel().getSelectedItem();
                myLabel.setText(currentPage);
                passwortFeld.setText(passwoerter.get(currentPage));

            }
        });

    }


    public void switchToScene1(ActionEvent event) {
    }

    public void passwortHinzufügen(ActionEvent event) {
        System.out.println("Passwort hinzufügen");
    }

    public void passwortBearbeiten(ActionEvent event) {

        System.out.println("Passwort bearbeiten");

    }
}

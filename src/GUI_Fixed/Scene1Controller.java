package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;


public class Scene1Controller {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    protected String PASSWORT = "123";
    @FXML
    PasswordField passwortEingeben;
    @FXML
    Text falschesPasswort;


    public void loginCheck(ActionEvent event) throws IOException{
        if (passwortEingeben.getText().equals("123")){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene2.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            String css = this.getClass().getResource("applicationScene2.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        }else {
            falschesPasswort.setText("Falsches Passwort");


        }


    }




    public void switchToScene2(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene2.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        String css = this.getClass().getResource("applicationScene2.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();

    }


    public void bestaetigen(javafx.event.ActionEvent actionEvent) {
        System.out.println("Passwort bestaetigen");
    }
    public void anlegen(javafx.event.ActionEvent actionEvent) {
        System.out.println("Neues Passwort anlegen");
    }
    public void eingeben(javafx.event.ActionEvent actionEvent) {
        System.out.println("Passwort eingegeben");
    }

    public void switchToScene1(ActionEvent event) {
    }
}

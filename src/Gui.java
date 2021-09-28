package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Objects;

public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
        Scene scene = new Scene(root);

        String css = this.getClass().getResource("applicationScene1.css").toExternalForm();
        scene.getStylesheets().add(css);


        primaryStage.setTitle("Passwort-Manager");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) { launch(args);}
}
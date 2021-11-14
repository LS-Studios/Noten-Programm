package lsstudios.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lsstudios.calculator.Calculator;

import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(".Screens/AnmeldeScreen.fxml"));
        primaryStage.setTitle("Notenprogramm");
        Scene newScene = new Scene(root);
        primaryStage.setScene(newScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
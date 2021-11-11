package lsstudios.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lsstudios.calculator.Calculator;

import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Test.fxml"));
        primaryStage.setTitle("Notenprogramm");
        primaryStage.setScene(new Scene(root, 580, 350));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }

}
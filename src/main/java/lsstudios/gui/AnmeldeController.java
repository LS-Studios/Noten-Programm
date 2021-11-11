package lsstudios.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnmeldeController implements Initializable {

    //Variablen
    @FXML
    private TextField BenutzernameTF;
    @FXML
    private TextField PasswortTF;
    @FXML
    private Button RegistrierenBtn;
    @FXML
    private Button AnmeldenBtn;
    @FXML
    private Label welcomeText;
    @FXML
    private Pane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Registrieren() throws IOException {
        //Parent root = FXMLLoader.load(getClass().getClassLoader().resources("RegistrierenScreen.fxml"));
        //Stage window = (Stage)[XXX] .getScene().getWindow();
        // window.setScene(new Scene(root,960, 540));
    }
    public void Test(){
        System.out.println("curaz");
    }
    public void Anmelden() throws IOException {
        //System.out.println("Hello");

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("NotenÜbersichtScreen.fxml"));
        Stage window = (Stage) pane.getScene().getWindow();

        Scene newScene = new Scene(root);
        newScene.setFill(Color.TRANSPARENT);
        window.setScene(newScene);
    }
}
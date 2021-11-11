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
import lsstudios.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnmeldeController implements Initializable {

    //Variablen
    @FXML
    private TextField VornameTF;
    @FXML
    private TextField NachnameTF;
    @FXML
    private TextField PasswortTF;
    @FXML
    private Button RegistrierenBtn;
    @FXML
    private Button AnmeldeBtn;
    @FXML
    private Pane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Database.DBPath = "C:/Users/stubb/IdeaProjects/Notenprogramm/src/main/java/lsstudios/database/file/Notenprogramm.db";
        Database.CreateBenutzerTable();
        Database.CreateFachTable();
        Database.CreateNoteTable();
        Database.CreateFachBelegungTable();
    }

    public void Registrieren() throws IOException {
        Database.ChangeScreen("RegestrierenScreen.fxml", pane);
    }
    public void Anmelden() throws IOException {
        try {
            Database.benutzerId = Database.GetBenutzerId(VornameTF.getText(), NachnameTF.getText(), PasswortTF.getText());
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "Benutzer nicht vorhanden", "Bitte gebe gültige Daten an!");
        }
        System.out.println(Database.benutzerId);
        Database.ChangeScreen("NotenÜbersichtScreen.fxml", pane);
    }
}
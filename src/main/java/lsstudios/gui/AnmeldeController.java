package lsstudios.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
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
        Database.SetUp();

        //Set Color for Theme
        if (Database.benutzerId != -1) {
            Database.ChangeColorTheme(Database.GetDataOfBenutzer().colorTheme, pane);
        }
    }

    public void Registrieren() throws IOException {
        Database.ChangeScreen(".Screens/RegestrierenScreen.fxml", pane);
    }
    public void Anmelden() throws IOException {
        Database.benutzerId = Database.GetBenutzerId(VornameTF.getText(), NachnameTF.getText(), PasswortTF.getText());

        if (Database.benutzerId != -1) {
            Database.ChangeScreen(".Screens/NotenÜbersichtScreen.fxml", pane);
        } else {
            PopUps.ErrorPopUp("Error", "Benutzer nicht vorhanden", "Bitte gebe gültige Daten an!");
        }
    }
}
package lsstudios.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegestrierenController implements Initializable {


    //Variablen Textfields: Benutzername, Passwort, PasswortWiederholen, Choicefields: 1-6,0-15, Buttons: Anmelden, Registrieren


    @FXML
    private TextField Benutzername;
    @FXML
    private TextField Passwort;
    @FXML
    private TextField PasswortWiederholen;
    @FXML
    private ChoiceBox NotensystemUnterstufe;
    @FXML
    private ChoiceBox NotensystemOberstufe;
    @FXML
    private Button Anmelden;
    @FXML
    private Button Registrieren;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Anmelden() throws IOException {
        //Parent root = FXMLLoader.load(getClass().getClassLoader().resources("AnmeldenScreen.fxml"));
        //Stage window = (Stage) name eines objektes vom zum button zugehörigen Fenster.getScene().getWindow();
        // window.setScene(new Scene(root,960, 540));


    }
}
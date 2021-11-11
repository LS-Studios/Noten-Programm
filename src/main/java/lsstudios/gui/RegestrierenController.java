package lsstudios.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lsstudios.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegestrierenController implements Initializable {


    //Variablen
    @FXML
    private TextField VornameTF;
    @FXML
    private TextField NachnameTF;
    @FXML
    private TextField PasswortTF;
    @FXML
    private CheckBox NotensystemUnterstufe1CB;
    @FXML
    private CheckBox NotensystemOberstufe2CB;
    @FXML
    private Button AnmeldenBtn;
    @FXML
    private Button RegistrierenBtn;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Anmelden() throws IOException {
        Database.ChangeScreen("AnmeldeScreen.fxml", pane);
    }

    public void Regestrieren() throws IOException {
        int notenSystem = 0;
        if(NotensystemUnterstufe1CB.isSelected()) {
            notenSystem = 0;
        } else {
            notenSystem = 1;
        }

        Database.AddDataToBenutzer(VornameTF.getText(), NachnameTF.getText(), PasswortTF.getText(), notenSystem);
        Database.ChangeScreen("AnmeldeScreen.fxml", pane);
    }

    public void ChangeSelectUnterstuffe() {
        NotensystemUnterstufe1CB.setSelected(true);
        NotensystemOberstufe2CB.setSelected(false);
    }

    public void ChangeSelectOberstuffe() {
        NotensystemUnterstufe1CB.setSelected(false);
        NotensystemOberstufe2CB.setSelected(true);
    }
}
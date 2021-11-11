package lsstudios.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import lsstudios.database.Database;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.spi.ResourceBundleControlProvider;

public class NotenHinzufügenController implements Initializable {
    @FXML //Die Variablen
    private Label WelcomeText;
    @FXML
    private Button HinzufügenButton;
    @FXML
    private ChoiceBox FachCB;
    @FXML
    private RadioButton KleineNoteRadioButton;
    @FXML
    private TextField AufgabeTextField;
    @FXML
    private TextField NoteTextField;
    @FXML
    private RadioButton KlausurRadioButton;
    @FXML
    private Button ZurückButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO Angewählte Fächer hinzufügen
        FachCB.getItems().addAll("Deutsch", "Mathe", "Englisch", "...");
    }
    //Die Methoden

    public void Klausur(){

    }

    public void KleineNote(){

    }

    public void Hinzufügen(){
        //Parent root = FXMLLoader.load(getClass().getClassLoader().resources("Notenübersicht.fxml"));
        //Stage window = Stage //(Name eines Objektes von zum Fenster der begehrung)

        //Window.setScene(new Scene(root, 960, 540))
    }

    public void Wahl(){

    }

    public void Zurück(){

    }
    public void Test(){
        System.out.println("Cock");
    }
}
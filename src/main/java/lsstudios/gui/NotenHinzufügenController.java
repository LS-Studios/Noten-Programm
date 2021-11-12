package lsstudios.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lsstudios.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotenHinzufügenController implements Initializable {

    @FXML
    private TextField AufgabeTF;

    @FXML
    private ChoiceBox FachCB;

    @FXML
    private RadioButton KlausurNoteRB;

    @FXML
    private RadioButton KleineNoteRB;

    @FXML
    private Button NoteHinzufügenButton;

    @FXML
    private TextField NoteTF;

    @FXML
    private Button ZurückBtn;

    @FXML
    private AnchorPane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KleineNoteRB.setSelected(true);

        for (Integer i : Database.GetDataOfFachBelegung(Database.benutzerId)) {
            FachCB.getItems().addAll(Database.GetFachName(i));
        }
    }

    @FXML
    void KlausurNote(ActionEvent event) {
        KlausurNoteRB.setSelected(true);
        KleineNoteRB.setSelected(false);
    }

    @FXML
    void KleineNote(ActionEvent event) {
        KlausurNoteRB.setSelected(false);
        KleineNoteRB.setSelected(true);
    }

    @FXML
    void NoteHinzufügen(ActionEvent event) {
        String notenWert = "Oberstufe";
        if(KleineNoteRB.isSelected()) {
            notenWert = "KleineNote";
        } else {
            notenWert = "KlausurNote";
        }

        Database.AddDataToNote(Database.benutzerId, Database.GetFachId((String)FachCB.getValue()), Integer.parseInt(NoteTF.getText()), notenWert, 0);
    }

    @FXML
    void Zurück(ActionEvent event) throws IOException {
        Database.ChangeScreen("NotenÜbersichtScreen.fxml", pane);
    }
}

package lsstudios.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private Label FachnameText;

    @FXML
    private RadioButton KlausurNoteRB;

    @FXML
    private RadioButton KleineNoteRB;

    @FXML
    private Button NoteHinzufügenBtn;

    @FXML
    private TextField NoteTF;

    @FXML
    private Button ZurückBtn;

    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KleineNoteRB.setSelected(true);

        FachnameText.setText(Database.GetFachName(Database.fachIdToEdit));
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
    void NoteHinzufügen(ActionEvent event) throws IOException {
        String notenWert = "Oberstufe";
        if(KleineNoteRB.isSelected()) {
            notenWert = "KleineNote";
        } else {
            notenWert = "KlausurNote";
        }

        Database.AddDataToNote(Database.benutzerId, Database.fachIdToEdit, Integer.parseInt(NoteTF.getText()), notenWert, 0);

        Database.ChangeScreen("FachinformationenBearbeiten.fxml", pane);
    }

    @FXML
    void Zurück(ActionEvent event) throws IOException {
        Database.ChangeScreen("FachinformationenBearbeiten.fxml", pane);
    }
}

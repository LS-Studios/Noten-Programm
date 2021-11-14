package lsstudios.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lsstudios.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotenHinzufügenController implements Initializable {

    @FXML
    private TextField AufgabeTF;

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

    public static Pane globalPane;

    //Variables
    private double x,y;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set ColorTheme
        Database.ChangeColorTheme(Database.GetDataOfBenutzer().colorTheme, pane);
    }

    @FXML
    void NoteHinzufügen(ActionEvent event) throws IOException {
        String notenWert = "Oberstufe";
        if(KleineNoteRB.isSelected()) {
            notenWert = "KleineNote";
        } else {
            notenWert = "KlausurNote";
        }

        switch (Database.GetDataOfBenutzer().notensystem) {
            case "Oberstufe":
                if (Integer.parseInt(NoteTF.getText()) > 15 || Integer.parseInt(NoteTF.getText()) < 1) {
                    PopUps.ErrorPopUp("Fehler", "Falsche Note eingegeben", "Bitte Wähle eine Note zwischen 1(Schlecht) und 15(Gut) aus!");
                    return;
                }
                break;
            case "Unterstufe":
                if (Integer.parseInt(NoteTF.getText()) > 6 || Integer.parseInt(NoteTF.getText()) < 1) {
                    PopUps.ErrorPopUp("Fehler", "Falsche Note eingegeben", "Bitte Wähle eine Note zwischen 1(Gut) und 6(Schlecht) aus!");
                    return;
                }
                break;
        }

        Database.AddDataToNote(Database.benutzerId, Database.fachIdToEdit, Integer.parseInt(NoteTF.getText()), AufgabeTF.getText(), notenWert, 0);

        Database.ChangeScreen(".Screens/NotenÜbersichtScreen.fxml", NotenÜbersichtController.globalPane);
        Database.ChangeScreen(".Screens/FachinformationenBearbeiten.fxml", FachinformationBearbeitenController.globalPane);
        Database.CloseScreen(pane);
    }

    @FXML
    void Zurück(ActionEvent event) throws IOException {
        Database.CloseScreen(pane);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() -y);

    }
}

package lsstudios.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lsstudios.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FachinformationBearbeitenController implements Initializable {

    @FXML
    private Label FachnameText;

    @FXML
    private Label KlausurNotenText;

    @FXML
    private Label KleineNotenText;

    @FXML
    private Button NoteHinzufügen;

    @FXML
    private Button BestätigenBtn;

    @FXML
    private Button NoteLöschen;

    @FXML
    private TextField ThemaTF;

    @FXML
    private Button ZurückBtn;

    @FXML
    private AnchorPane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FachnameText.setText(Database.GetFachName(Database.fachIdToEdit));

    }

    @FXML
    public void Bestätigen() throws IOException {
        Database.RemoveDataOfFachinfos(Database.benutzerId, Database.fachIdToEdit);
        Database.AddDataToFachInfos(Database.benutzerId, Database.fachIdToEdit, ThemaTF.getText());
        Database.ChangeScreen("NotenÜbersichtScreen.fxml", pane);
    }

    @FXML
    void Zurück(ActionEvent event) throws IOException {
        Database.ChangeScreen("NotenÜbersichtScreen.fxml", pane);
    }
}

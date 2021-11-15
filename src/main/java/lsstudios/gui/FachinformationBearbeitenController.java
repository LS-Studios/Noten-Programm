package lsstudios.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lsstudios.calculator.Calculator;
import lsstudios.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FachinformationBearbeitenController implements Initializable {

    @FXML
    private Button NoteHinzufügenBtn;

    @FXML
    private Button BestätigenBtn;

    @FXML
    private Button NoteLöschenBtn;

    @FXML
    private TextField ThemaTF;

    @FXML
    private Button ZurückBtn;

    //region TableViwe
    @FXML
    private TableView<NotenWerte> table;

    @FXML
    private TableColumn<NotenWerte, String> aufgabenColumn;

    @FXML
    private TableColumn<NotenWerte, Integer> notenColumn;

    @FXML
    private TableColumn<NotenWerte, String> notenwertColumn;
    //endregion

    @FXML
    private AnchorPane pane;

    public static Pane globalPane;

    //Variables
    private double x,y;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        globalPane = pane;

        notenColumn.setCellValueFactory(new PropertyValueFactory<NotenWerte, Integer>("note"));
        aufgabenColumn.setCellValueFactory(new PropertyValueFactory<NotenWerte, String>("aufgabe"));
        notenwertColumn.setCellValueFactory(new PropertyValueFactory<NotenWerte, String>("notenwert"));

        table.setItems(GetFachWerte());

        if(NotenHinzufügenController.globalPane != null)
            Database.CloseScreen(NotenHinzufügenController.globalPane);

        ThemaTF.setText(Database.GetThemaOfFach(Database.benutzerId, Database.fachIdToEdit));

        //Set ColorTheme
        Database.ChangeColorTheme(Database.GetDataOfBenutzer().colorTheme, pane);
    }

    public ObservableList<NotenWerte> GetFachWerte() {
        ObservableList<NotenWerte> notenWerte = FXCollections.observableArrayList();
        for (Integer note : Database.GetNoten(Database.benutzerId, Database.fachIdToEdit, "KleineNote")) {
            String aufgabe = Database.GetAufgabeOfNote(Database.benutzerId, Database.fachIdToEdit, note, "KleineNote");
            notenWerte.add(new NotenWerte(note, aufgabe, "Kleine Note"));
        }
        for (Integer note : Database.GetNoten(Database.benutzerId, Database.fachIdToEdit, "KlausurNote")) {
            String aufgabe = Database.GetAufgabeOfNote(Database.benutzerId, Database.fachIdToEdit, note, "KlausurNote");
            notenWerte.add(new NotenWerte(note, aufgabe, "Klausur Note"));
        }
        return notenWerte;
    }

    @FXML
    public void Bestätigen() throws IOException {
        Database.RemoveDataOfFachinfos(Database.benutzerId, Database.fachIdToEdit);
        Database.AddDataToFachInfos(Database.benutzerId, Database.fachIdToEdit, ThemaTF.getText());
        Database.ChangeScreen(".Screens/NotenÜbersichtScreen.fxml", NotenÜbersichtController.globalPane);
        Database.CloseScreen(pane);
    }

    @FXML
    void NoteHinzufügen(ActionEvent event) throws IOException {
        Database.PopUpScreen(".Screens/NoteHinzufügenScreen.fxml", Database.GetFachName(Database.fachIdToEdit) + " eine Note Hinzufügen",pane);
    }

    @FXML
    void NoteLöschen(ActionEvent event) throws IOException {
        try {
            table.getItems().remove(table.getSelectionModel().getSelectedItem());
            Database.RemoveDataOfNote(Database.benutzerId, Database.fachIdToEdit, table.getSelectionModel().getSelectedItem().note, (table.getSelectionModel().getSelectedItem().notenwert).replaceAll("\\s",""));
        } catch (Exception e) {
            PopUps.ErrorPopUp("Fehler", "Keine Note Ausgewählt", "Bitte Wähle eine Note aus um sie löschen zu können!");
        }
    }

    @FXML
    void Zurück(ActionEvent event) {
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

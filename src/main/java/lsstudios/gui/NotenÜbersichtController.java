package lsstudios.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lsstudios.calculator.Calculator;
import lsstudios.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class NotenÜbersichtController implements Initializable {
    //Variablen
    @FXML
    private Label HalloText;

    //region Buttons

    @FXML
    private Button FachinfosBearbeitenBtn;
    @FXML
    private Button HilfeButton;
    @FXML
    private Button EinstellungenButton;
    @FXML
    private Button AbmeldenButton;
    //endregion

    @FXML
    private TableView<FachWerte> table;
    @FXML
    private TableColumn<FachWerte, String> fachColumn;
    @FXML
    private TableColumn<FachWerte, String> themaColumn;
    @FXML
    private TableColumn<FachWerte, ArrayList<Integer>> kleineNoteColumn;
    @FXML
    private TableColumn<FachWerte, ArrayList<Integer>> klausurNoteColumn;
    @FXML
    private TableColumn<FachWerte, Integer> schnittNoteColumn;

    @FXML
    private Label GesamtSchnitt;

    @FXML
    private Pane pane;

    public static Pane globalPane;

    //Methoden
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        globalPane = pane;

        fachColumn.setCellValueFactory(new PropertyValueFactory<FachWerte, String>("fach"));
        themaColumn.setCellValueFactory(new PropertyValueFactory<FachWerte, String>("thema"));
        kleineNoteColumn.setCellValueFactory(new PropertyValueFactory<FachWerte, ArrayList<Integer>>("kleineNoten"));
        klausurNoteColumn.setCellValueFactory(new PropertyValueFactory<FachWerte, ArrayList<Integer>>("klausurNoten"));
        schnittNoteColumn.setCellValueFactory(new PropertyValueFactory<FachWerte, Integer>("schnitt"));

        table.setItems(GetFachWerte());

        HalloText.setText(Database.GetDataOfBenutzer().vorname);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Set ColorTheme
        Database.ChangeColorTheme(Database.GetDataOfBenutzer().colorTheme, pane);

        GesamtSchnitt.setText("" + Calculator.total(Database.GetAllNoten(Database.benutzerId), Database.GetDataOfBenutzer().notensystem));
    }

    public ObservableList<FachWerte> GetFachWerte() {
        ObservableList<FachWerte> fachWerte = FXCollections.observableArrayList();
        if (Database.GetDataOfFachBelegung(Database.benutzerId).size() > 0) {

            for (Integer i : Database.GetDataOfFachBelegung(Database.benutzerId)) {
                ArrayList<Integer> kleineNoten = Database.GetNoten(Database.benutzerId, i, "KleineNote");
                ArrayList<Integer> klausurNoten = Database.GetNoten(Database.benutzerId, i, "KlausurNote");
                fachWerte.add(new FachWerte(Database.GetFachName(i), Database.GetThemaOfFach(Database.benutzerId, i), kleineNoten, klausurNoten, Calculator.average(kleineNoten, klausurNoten, Database.GetDataOfBenutzer().notensystem)));

            }
            return fachWerte;
        } else {
            return fachWerte;
        }
    }

    public void FachinfosBearbeiten() throws IOException {

        try {
            Database.fachIdToEdit = Database.GetFachId(table.getSelectionModel().getSelectedItem().fach);

            Database.PopUpScreen(".Screens/FachinformationenBearbeiten.fxml", table.getSelectionModel().getSelectedItem().fach + " Bearbeiten", pane);
        } catch (Exception e) {
            PopUps.ErrorPopUp("Fehler", "Kein Fach Ausgewählt", "Bitte Wähle ein Fach aus um es bearbeiten zu können!");
            System.out.println(e);
        }
    }
    public void Abmelden() throws IOException {
        Database.benutzerId = -1;
        Database.ChangeScreen(".Screens/AnmeldeScreen.fxml", pane);
    }
    public void Hilfe(){

    }
    public void Einstellungen() throws IOException {
        Database.ChangeScreen(".Screens/EinstellungenScreen.fxml", pane);
    }

}
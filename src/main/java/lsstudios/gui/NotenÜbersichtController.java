package lsstudios.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import lsstudios.calculator.Calculator;
import lsstudios.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private TableColumn<FachWerte, Integer> klausurNoteColumn;
    @FXML
    private TableColumn<FachWerte, Integer> schnittNoteColumn;

    @FXML
    private Pane pane;

    //Methoden
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fachColumn.setCellValueFactory(new PropertyValueFactory<FachWerte, String>("fach"));
        themaColumn.setCellValueFactory(new PropertyValueFactory<FachWerte, String>("thema"));
        kleineNoteColumn.setCellValueFactory(new PropertyValueFactory<FachWerte, ArrayList<Integer>>("kleineNoten"));
        klausurNoteColumn.setCellValueFactory(new PropertyValueFactory<FachWerte, Integer>("klausurNoten"));
        schnittNoteColumn.setCellValueFactory(new PropertyValueFactory<FachWerte, Integer>("schnitt"));

        HalloText.setText(Database.GetDataOfBenutzer().vorname);

        table.setItems(GetFachWerte());


    }

    public ObservableList<FachWerte> GetFachWerte() {
        ObservableList<FachWerte> fachWerte = FXCollections.observableArrayList();
        for (Integer i : Database.GetDataOfFachBelegung(Database.benutzerId)) {
            fachWerte.add(new FachWerte(Database.GetFachName(i), Database.GetThemaOfFach(Database.benutzerId, i), Database.GetNoten(Database.benutzerId, i, "KleineNote"), Database.GetNoten(Database.benutzerId, i, "KlausurNote"), 0));
        }
        return fachWerte;
    }

    public void FachinfosBearbeiten() throws IOException {

        try {
            Database.fachIdToEdit = Database.GetFachId(table.getSelectionModel().getSelectedItem().fach);

            Database.ChangeScreen("FachinformationenBearbeiten.fxml", pane);
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "Kein Fach Ausgewählt", "Bitte Wähle ein Fach aus um es bearbeiten zu können!");
        }
    }
    public void Abmelden() throws IOException {
        Database.ChangeScreen("AnmeldeScreen.fxml", pane);
    }
    public void Hilfe(){

    }
    public void Einstellungen(){

    }

}
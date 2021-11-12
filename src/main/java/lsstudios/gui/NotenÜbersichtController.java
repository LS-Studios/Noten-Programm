package lsstudios.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import lsstudios.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NotenÜbersichtController implements Initializable {
    //Variablen
    @FXML
    private Label BiologieGr;

    @FXML
    private Label BiologieKlein;

    @FXML
    private Label BiologieSch;

    @FXML
    private TextField BiologieThemaTF;

    @FXML
    private Label ChemieGr;

    @FXML
    private Label ChemieKlein;

    @FXML
    private Label ChemieSch;

    @FXML
    private TextField ChemieThemaTF;

    @FXML
    private Label DeutschGr;

    @FXML
    private Label DeutschKlein;

    @FXML
    private Label DeutschSch;

    @FXML
    private TextField DeutschThemaTF;

    @FXML
    private Label EnglischGr;

    @FXML
    private Label EnglischKlein;

    @FXML
    private Label EnglischSch;

    @FXML
    private TextField EnglischThemaTF;

    @FXML
    private Label GeografieGr;

    @FXML
    private Label GeografieKlein;

    @FXML
    private Label GeografieSch;

    @FXML
    private TextField GeografieThemaTF;

    @FXML
    private Label GeschichteGr;

    @FXML
    private Label GeschichteKlein;

    @FXML
    private Label GeschichteSch;

    @FXML
    private TextField GeschichteThemaTF;

    @FXML
    private Label InformatikGr;

    @FXML
    private Label InformatikKlein;

    @FXML
    private Label InformatikSch;

    @FXML
    private TextField InformatikThemaTF;

    @FXML
    private Label KunstGr;

    @FXML
    private Label KunstKlein;

    @FXML
    private Label KunstSch;

    @FXML
    private TextField KunstThemaTF;

    @FXML
    private TextField LatainThemaTF;

    @FXML
    private Label LateinGr;

    @FXML
    private Label LateinKlein;

    @FXML
    private Label LateinSch;

    @FXML
    private Label MathematikGr;

    @FXML
    private Label MathematikKlein;

    @FXML
    private Label MathematikSch;

    @FXML
    private TextField MathematikThemaTF;

    @FXML
    private Label MusikGr;

    @FXML
    private Label MusikKlein;

    @FXML
    private Label MusikSch;

    @FXML
    private TextField MusikThemaTF;

    @FXML
    private Label PhysikGr;

    @FXML
    private Label PhysikKlein;

    @FXML
    private Label PhysikSch;

    @FXML
    private TextField PhysikThemaTF;

    @FXML
    private Label ReligionGr;

    @FXML
    private Label ReligionKlein;

    @FXML
    private Label ReligionSch;

    @FXML
    private TextField ReligionThemaTF;

    @FXML
    private Label SchwedischGr;

    @FXML
    private Label SchwedischKlein;

    @FXML
    private Label SchwedischSch;

    @FXML
    private TextField SchwedischThemaTF;

    @FXML
    private Label SozialkundeGr;

    @FXML
    private Label SozialkundeKlein;

    @FXML
    private Label SozialkundeSch;

    @FXML
    private TextField SozialkundeThemaTF;

    @FXML
    private Label SpanischGr;

    @FXML
    private Label SpanischKlein;

    @FXML
    private Label SpanischSch;

    @FXML
    private TextField SpanischThemaTF;

    @FXML
    private Label SportGr;

    @FXML
    private Label SportKlein;

    @FXML
    private Label SportSch;

    @FXML
    private TextField SportThemaTF;

    @FXML
    private Label WirtschaftGr;

    @FXML
    private Label WirtschaftKlein;

    @FXML
    private Label WirtschaftSch;

    @FXML
    private TextField WirtschaftThemaTF;

    @FXML
    private Label HalloText;

    //region Buttons

    @FXML
    private Button NotenHinzufügenButton;
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
            fachWerte.add(new FachWerte(Database.GetFachName(i), "", null, 0, 0));
        }
        return fachWerte;
    }

    public void NoteHinzufügen() throws IOException {
        Database.ChangeScreen("NoteHinzufügenScreen.fxml", pane);
    }
    public void Abmelden() throws IOException {
        Database.ChangeScreen("AnmeldeScreen.fxml", pane);
    }
    public void Hilfe(){

    }
    public void Einstellungen(){

    }

}
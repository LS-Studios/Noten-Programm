package lsstudios.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotenÜbersichtController implements Initializable {
    //Variablen
    @FXML
    private Button NoteHinzufügenButton;
    @FXML
    private Button AbmeldenButton;
    @FXML
    private Button HilfeButton;
    @FXML
    private Button EinstellungenButton;

    //BiologieGr ist Feld für große Note (Klausur) in Biologie, ...Klein ist das Feld für die kleine Note und ...Sch ist das Feld für den Schnitt
    @FXML
    private Label BiologieGr;

    @FXML
    private Label BiologieKlein;

    @FXML
    private Label BiologieSch;

    @FXML
    private Label ChemieGr;

    @FXML
    private Label ChemieKlein;

    @FXML
    private Label ChemieSch;

    @FXML
    private Label DeutschGr;

    @FXML
    private Label DeutschKlein;

    @FXML
    private Label DeutschSch;

    @FXML
    private Label EnglischGr;

    @FXML
    private Label EnglischKlein;

    @FXML
    private Label EnglischSch;

    @FXML
    private Label GeografieGr;

    @FXML
    private Label GeografieKlein;

    @FXML
    private Label GeografieSch;

    @FXML
    private Label GeschichteGr;

    @FXML
    private Label GeschichteKlein;

    @FXML
    private Label GeschichteSch;

    @FXML
    private Label InformatikGr;

    @FXML
    private Label InformatikKlein;

    @FXML
    private Label InformatikSch;

    @FXML
    private Label KunstGr;

    @FXML
    private Label KunstKlein;

    @FXML
    private Label KunstSch;

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
    private Label MusikGr;

    @FXML
    private Label MusikKlein;

    @FXML
    private Label MusikSch;

    @FXML
    private Label PhysikGr;

    @FXML
    private Label PhysikKlein;

    @FXML
    private Label PhysikSch;

    @FXML
    private Label ReligionGr;

    @FXML
    private Label ReligionKlein;

    @FXML
    private Label ReligionSch;

    @FXML
    private Label SchwedischGr;

    @FXML
    private Label SchwedischKlein;

    @FXML
    private Label SchwedischSch;

    @FXML
    private Label SozialkundeGr;

    @FXML
    private Label SozialkundeKlein;

    @FXML
    private Label SozialkundeSch;

    @FXML
    private Label SpanischGr;

    @FXML
    private Label SpanischKlein;

    @FXML
    private Label SpanischSch;

    @FXML
    private Label SportGr;

    @FXML
    private Label SportKlein;

    @FXML
    private Label SportSch;

    @FXML
    private Label WirtschaftGr;

    @FXML
    private Label WirtschaftKlein;

    @FXML
    private Label WirtschaftSch;

    @FXML
    private Pane pane;

    //Methoden
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void Test(){
        System.out.println("Hello!");
    }

    public void NoteHinzufügen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML-files/CreateFileScreen.fxml"));
        Stage window = (Stage) pane.getScene().getWindow();

        Scene newScene = new Scene(root);
        newScene.setFill(Color.TRANSPARENT);
        window.setScene(newScene);
    }
    public void Abmelden(){

    }
    public void Hilfe(){

    }
    public void Einstellungen(){

    }

}
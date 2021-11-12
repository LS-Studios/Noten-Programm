package lsstudios.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lsstudios.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HilfeScreenController implements Initializable {


    //Variablen
    @FXML
    private TextField VornameTF;
    @FXML
    private TextField NachnameTF;
    @FXML
    private TextField PasswortTF;
    @FXML
    private CheckBox NotensystemUnterstufe1CB;
    @FXML
    private CheckBox NotensystemOberstufe2CB;
    @FXML
    private Button AnmeldenBtn;
    @FXML
    private Button RegistrierenBtn;

    //<editor-fold desc="Fächer CheckBoxes">
    @FXML
    private CheckBox BiologieCB;

    @FXML
    private CheckBox BiologieLKCB;

    @FXML
    private CheckBox ChemieCB;

    @FXML
    private CheckBox ChemieLKCB;

    @FXML
    private CheckBox DeutschCB;

    @FXML
    private CheckBox DeutschLKCB;

    @FXML
    private CheckBox EnglischCB;

    @FXML
    private CheckBox EnglischLKCB;

    @FXML
    private CheckBox GeografieCB;

    @FXML
    private CheckBox GeografieLKCB;

    @FXML
    private CheckBox GeschichteCB;

    @FXML
    private CheckBox GeschichteLKCB;

    @FXML
    private CheckBox InformatikCB;

    @FXML
    private CheckBox InformatikLKCB;

    @FXML
    private CheckBox KunstCB;

    @FXML
    private CheckBox KunstLKCB;

    @FXML
    private CheckBox LatainCB;

    @FXML
    private CheckBox LatainLKCB;

    @FXML
    private CheckBox MathematikCB;

    @FXML
    private CheckBox MathematikLKCB;

    @FXML
    private CheckBox MusikCB;

    @FXML
    private CheckBox MusikLKCB;

    @FXML
    private CheckBox PhysikCB;

    @FXML
    private CheckBox PhysikLKCB;

    @FXML
    private CheckBox ReligionCB;

    @FXML
    private CheckBox ReligionLKCB;

    @FXML
    private CheckBox SchwedischCB;

    @FXML
    private CheckBox SchwedischLKCB;

    @FXML
    private CheckBox SozialkundeCB;

    @FXML
    private CheckBox SozialkundeLKCB;

    @FXML
    private CheckBox SpanischCB;

    @FXML
    private CheckBox SpanischLKCB;

    @FXML
    private CheckBox SportCB;

    @FXML
    private CheckBox SportLKCB;

    @FXML
    private CheckBox WirtschaftCB;

    @FXML
    private CheckBox WirtschaftLKCB;
    //</editor-fold>

    @FXML
    private Pane pane;

    private ArrayList<CheckBox> fächerCB = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NotensystemOberstufe2CB.setSelected(true);

        fächerCB.add(DeutschCB);
        fächerCB.add(EnglischCB);
        fächerCB.add(LatainCB);
        fächerCB.add(SchwedischCB);
        fächerCB.add(SpanischCB);
        fächerCB.add(KunstCB);
        fächerCB.add(MusikCB);
        fächerCB.add(ReligionCB);
        fächerCB.add(GeografieCB);
        fächerCB.add(GeschichteCB);
        fächerCB.add(SozialkundeCB);
        fächerCB.add(WirtschaftCB);
        fächerCB.add(BiologieCB);
        fächerCB.add(ChemieCB);
        fächerCB.add(InformatikCB);
        fächerCB.add(MathematikCB);
        fächerCB.add(PhysikCB);
        fächerCB.add(SportCB);
    }

    public void Anmelden() throws IOException {
        Database.ChangeScreen("AnmeldeScreen.fxml", pane);
    }

    public void Regestrieren() throws IOException {
        if (Database.GetBenutzerId(VornameTF.getText(), NachnameTF.getText(), PasswortTF.getText()) == -1) {
            String notenSystem = "Oberstufe";
            if (NotensystemUnterstufe1CB.isSelected()) {
                notenSystem = "Unterstufe";
            } else {
                notenSystem = "Oberstufe";
            }

            Database.AddDataToBenutzer(VornameTF.getText(), NachnameTF.getText(), PasswortTF.getText(), notenSystem);
            Database.ChangeScreen("AnmeldeScreen.fxml", pane);
            for (CheckBox cb : fächerCB) {
                if (cb.isSelected()) {
                    Database.AddDataToFachBelegung(Database.GetBenutzerLength(), Database.GetFachId(cb.getText()));
                    Database.AddDataToFachInfos(Database.GetBenutzerLength(), Database.GetFachId(cb.getText()), "");
                }
            }
        } else {
            PopUps.ErrorPopUp("Fehler", "Die Eingegebenden Daten sind bereits vergeben!", "Wähle Sie andere Daten oder melden Sie an.");
        }
    }

    public void ChangeSelectUnterstuffe() {
        NotensystemUnterstufe1CB.setSelected(true);
        NotensystemOberstufe2CB.setSelected(false);
    }

    public void ChangeSelectOberstuffe() {
        NotensystemUnterstufe1CB.setSelected(false);
        NotensystemOberstufe2CB.setSelected(true);
    }
}
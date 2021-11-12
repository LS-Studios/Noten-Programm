package lsstudios.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import lsstudios.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class RegestrierenController implements Initializable {


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

    //region Fächerwahl
    @FXML
    private CheckBox BiologieCB;

    @FXML
    private RadioButton BiologieGKCB;

    @FXML
    private RadioButton BiologieLKCB;

    @FXML
    private CheckBox ChemieCB;

    @FXML
    private RadioButton ChemieGKCB;

    @FXML
    private RadioButton ChemieLKCB;

    @FXML
    private CheckBox DeutschCB;

    @FXML
    private RadioButton DeutschGKCB;

    @FXML
    private RadioButton DeutschLKCB;

    @FXML
    private CheckBox EnglischCB;

    @FXML
    private RadioButton EnglischGKCB;

    @FXML
    private RadioButton EnglischLKCB;

    @FXML
    private CheckBox GeografieCB;

    @FXML
    private RadioButton GeografieGKCB;

    @FXML
    private RadioButton GeografieLKCB;

    @FXML
    private CheckBox GeschichteCB;

    @FXML
    private RadioButton GeschichteGKCB;

    @FXML
    private RadioButton GeschichteLKCB;

    @FXML
    private CheckBox InformatikCB;

    @FXML
    private RadioButton InformatikGKCB;

    @FXML
    private RadioButton InformatikLKCB;

    @FXML
    private CheckBox KunstCB;

    @FXML
    private RadioButton KunstGKCB;

    @FXML
    private RadioButton KunstLKCB;

    @FXML
    private CheckBox LatainCB;

    @FXML
    private RadioButton LatainGKCB;

    @FXML
    private RadioButton LatainLKCB;

    @FXML
    private CheckBox MathematikCB;

    @FXML
    private RadioButton MathematikGKCB;

    @FXML
    private RadioButton MathematikLKCB;

    @FXML
    private CheckBox MusikCB;

    @FXML
    private RadioButton MusikGKCB;

    @FXML
    private RadioButton MusikLKCB;

    @FXML
    private CheckBox PhysikCB;

    @FXML
    private RadioButton PhysikGKCB;

    @FXML
    private RadioButton PhysikLKCB;

    @FXML
    private CheckBox ReligionCB;

    @FXML
    private RadioButton ReligionGKCB;

    @FXML
    private RadioButton ReligionLKCB;

    @FXML
    private CheckBox SchwedischCB;

    @FXML
    private RadioButton SchwedischGKCB;

    @FXML
    private RadioButton SchwedischLKCB;

    @FXML
    private CheckBox SozialkundeCB;

    @FXML
    private RadioButton SozialkundeGKCB;

    @FXML
    private RadioButton SozialkundeLKCB;

    @FXML
    private CheckBox SpanischCB;

    @FXML
    private RadioButton SpanischGKCB;

    @FXML
    private RadioButton SpanischLKCB;

    @FXML
    private CheckBox SportCB;

    @FXML
    private RadioButton SportGKCB;

    @FXML
    private RadioButton SportLKCB;

    @FXML
    private CheckBox WirtschaftCB;

    @FXML
    private RadioButton WirtschaftGKCB;

    @FXML
    private RadioButton WirtschaftLKCB;
    //endregion

    @FXML
    private Pane pane;

    private ArrayList<CheckBox> fächerCB = new ArrayList<>();
    private ArrayList<CheckBox> fächerNotenWertCB = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            for (CheckBox cb : fächerCB) {
                if (cb.isSelected()) {
                    Database.AddDataToFachBelegung(Database.GetBenutzerLength(), Database.GetFachId(cb.getText()));
                    Database.AddDataToFachInfos(Database.GetBenutzerLength(), Database.GetFachId(cb.getText()), "");
                }
            }
            Database.ChangeScreen("AnmeldeScreen.fxml", pane);
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
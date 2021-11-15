package lsstudios.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lsstudios.database.Database;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

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
    private RadioButton ColorThemeDunkelRB;
    @FXML
    private RadioButton ColorThemeHellRB;

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
    private GridPane grid;

    @FXML
    private ScrollPane scrol;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Anmelden() throws IOException {
        Database.ChangeScreen(".Screens/AnmeldeScreen.fxml", pane);
    }

    public void Regestrieren() throws IOException {
        if (Database.GetBenutzerId(VornameTF.getText(), NachnameTF.getText(), PasswortTF.getText()) == -1) {
            String notenSystem = "Oberstufe";
            if (NotensystemUnterstufe1CB.isSelected()) {
                notenSystem = "Unterstufe";
            }
            else {
                notenSystem = "Oberstufe";
            }

            if (ColorThemeHellRB.isSelected())
                Database.AddDataToBenutzer(VornameTF.getText(), NachnameTF.getText(), PasswortTF.getText(), notenSystem, "Hell");
            else if (ColorThemeDunkelRB.isSelected())
                Database.AddDataToBenutzer(VornameTF.getText(), NachnameTF.getText(), PasswortTF.getText(), notenSystem, "Dunkel");


            for (Node node : grid.getChildren()) {
                if (node instanceof CheckBox) {
                    CheckBox cb = (CheckBox) node;
                    if(cb.isSelected()) {

                        if (!Database.GetDataOfFachBelegung(Database.benutzerId).contains(Database.GetFachId(cb.getText()))) {
                            Database.AddDataToFachBelegung(Database.benutzerId, Database.GetFachId(cb.getText()), "GK");
                            if(Database.GetThemaOfFach(Database.benutzerId, Database.GetFachId(cb.getText())) != null) {
                                Database.AddDataToFachInfos(Database.GetBenutzerLength(), Database.GetFachId(cb.getText()), "");
                            }
                        }
                    } else if (!cb.isSelected()) {
                        Database.RemoveDataOfFachBelegung(Database.benutzerId, Database.GetFachId(cb.getText()));
                    }
                } else if (node instanceof RadioButton) {
                    RadioButton rb = (RadioButton) node;
                    if (rb.isSelected()) {
                        String fach = "";

                        if (rb.getId().contains("GK")) {
                            fach = rb.getId().replace("GK", "");

                            Database.ChangeKursartOfFach(Database.benutzerId, Database.GetFachId(fach), "GK");
                        } else if (rb.getId().contains("LK")) {
                            fach = rb.getId().replace("LK", "");

                            Database.ChangeKursartOfFach(Database.benutzerId, Database.GetFachId(fach), "LK");
                        }
                    }
                }
            }

            Database.benutzerId = Database.GetBenutzerLength();

            Database.ChangeScreen(".Screens/AnmeldeScreen.fxml", pane);
        } else {
            PopUps.ErrorPopUp("Fehler", "Die Eingegebenden Daten sind bereits vergeben!", "Wähle Sie andere Daten oder melden Sie an.");
        }
    }

    @FXML
    void ChangeColorThemeHell() {
        Database.ChangeColorTheme("Hell", pane);
    }

    @FXML
    void ChangeColorThemeDunkel() {
        Database.ChangeColorTheme("Dunkel", pane);
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
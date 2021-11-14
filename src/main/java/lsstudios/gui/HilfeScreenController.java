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

    @FXML
    private Pane pane;

    private ArrayList<CheckBox> fächerCB = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set ColorTheme
        Database.ChangeColorTheme(Database.GetDataOfBenutzer().colorTheme, pane);
    }


}
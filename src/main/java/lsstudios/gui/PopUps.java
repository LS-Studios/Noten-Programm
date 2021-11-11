package lsstudios.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUps {
    public static void ErrorPopUp(String Title, String Message, String ErrorCode) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(Title);
        window.setMinWidth(250);

        Label label1 = new Label();
        label1.setText(Message);

        Label label2 = new Label();
        label2.setText(ErrorCode);
        label2.setTextFill(Paint.valueOf("Red"));

        Button closeButton = new Button();
        closeButton.setText("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox();
        layout.getChildren().setAll(label1, label2, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene newScene = new Scene(layout);
        window.setScene(newScene);
        window.showAndWait();
    }
    public static void InfoBox(String Title, String Message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(Title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(Message);

        Button closeButton = new Button();
        closeButton.setText("OK");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox();
        layout.getChildren().setAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene newScene = new Scene(layout);
        window.setScene(newScene);
        window.showAndWait();
    }
}

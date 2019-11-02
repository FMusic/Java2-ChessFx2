package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btnSingle;

    @FXML
    private Button btnMultiSingle;
    private Stage mainStage;
    private Parent root;

    @FXML
    void onMultiSingle(ActionEvent event) {
        setWindowProps();
    }

    @FXML
    void onSinglePlayerClicked(ActionEvent event) {
        setWindowProps();
        mainStage.setScene(new Scene(root, 300, 300));
    }

    private void setWindowProps() {
        mainStage = (Stage) btnSingle.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("chess_game.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

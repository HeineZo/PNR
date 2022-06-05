package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ControllerNouvelleObservation extends Controller{
    @FXML
    private AnchorPane rootPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button btnBack;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        }
    }

}

package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ControllerDernierObservation extends Controller {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnObs;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        } else if (event.getSource() == btnObs) {
            loadStage("../vue/ModifierUneObservation.fxml", event);
        }
    }

}

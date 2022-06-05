package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ControllerModifierUnProfil extends Controller {

    @FXML
    private Button btnBack;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/GererProfils.fxml", event);
        } 
    }
}

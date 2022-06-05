package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;



public class ControllerModifierFiches extends Controller{

    @FXML
    private Button btnBack;

    @FXML
    private Button btnObsChouette;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixActionAdmin.fxml", event);
        } else if (event.getSource() == btnObsChouette) {
            loadStage("../vue/ModifierUneFiche.fxml", event);
        }
    }
}

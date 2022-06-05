package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ControllerGererProfils extends Controller {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnModifierProfil;

    @FXML
    private Button btnNouveauProfil;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixActionAdmin.fxml", event);
        } else if (event.getSource() == btnModifierProfil) {
            loadStage("../vue/ModifierUnProfil.fxml", event);
        } else if (event.getSource() == btnNouveauProfil) {
            loadStage("../vue/NouveauProfil.fxml", event);
        }
    }
}

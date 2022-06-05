package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

public class ControllerModifierUneFicheEspece extends Controller {
    
    @FXML
    private Button btnBack;

    @FXML
    private ImageView delete;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixActionAdmin.fxml", event);
        }
    }
}

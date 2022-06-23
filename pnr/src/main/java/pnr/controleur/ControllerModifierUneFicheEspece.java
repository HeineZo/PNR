package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

/**
 * Manages the ModifierUneFicheEspece
 */
public class ControllerModifierUneFicheEspece extends Controller {
    
    @FXML
    private Button btnBack;

    @FXML
    private Button envoi;

    @FXML
    private ImageView delete;

    /**
     * It's a function that loads a new stage depending on the button that was clicked.
     * 
     * @param event the event that triggered the method
     */
    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixActionAdmin.fxml", event);
        } else if (event.getSource() == envoi){
            initConfirmation("ModifierFicheEspece");
            loadStage("../vue/Confirmation.fxml", event);
        }
    }
}

package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

/**
 * Manages the CreerUneFicheEspece page
 */
public class ControllerCreerUneFicheEspece extends Controller{
    
    @FXML
    private Button btnBack;

    @FXML 
    private Button envoi;

    /**
     * If the user clicks the "Back" button, load the "ChoixAction" scene. If the user clicks the "No"
     * button, load the "NouvelleObservationGCIsansNid" scene. If the user clicks the "Yes" button,
     * load the "NouvelleObservationGCIavecNid" scene
     * 
     * @param event the event that triggered the method
     */
    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixActionAdmin.fxml", event);
        } else if(event.getSource() == envoi){
            initConfirmation("CreerFicheEspece");
            loadStage("../vue/Confirmation.fxml", event);
        }
    }
}

package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ControllerCreerUneFicheEspece extends Controller{
    
    @FXML
    private Button btnBack;

    @FXML 
    private Button envoi;

    /**
     * Handles the button's action
     * @param event enabled when the button is clicked
     * @throws IOException
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

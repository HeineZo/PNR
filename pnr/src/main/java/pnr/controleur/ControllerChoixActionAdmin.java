package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ControllerChoixActionAdmin extends Controller {

    @FXML
    private Button btnChoixAction;

    @FXML
    private Button btnExport;

    @FXML
    private Button btnGererProfils;

    @FXML
    private Button btnModifierFiche;

    @FXML
    private Button btnNouvelleFiche;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnNouvelleFiche) {
            loadStage("../vue/CreerUneFicheEspece.fxml", event);
        } else if (event.getSource() == btnModifierFiche) {
            loadStage("../vue/ModifierFiches.fxml", event);
        } else if (event.getSource() == btnGererProfils) {
            loadStage("../vue/GererProfilsVide.fxml", event);
        } else if (event.getSource() == btnExport) {
            loadStage("../vue/Sauvegarde.fxml", event);
        } else if (event.getSource() == btnChoixAction) {
            loadStage("../vue/ChoixAction.fxml", event);
        }
    }

}

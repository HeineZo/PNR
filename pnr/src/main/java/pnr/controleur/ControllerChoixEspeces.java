package pnr.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerChoixEspeces extends Controller {

    @FXML
    private Button btnBatracien;

    @FXML
    private Button btnChouette;

    @FXML
    private Button btnGCI;

    @FXML
    private Button btnHippo;

    @FXML
    private Button btnLoutre;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        ControllerChoixAction controller;
        if (event.getSource() == btnBatracien) {
            initVisualiser("Batracien");
            loadStage("../vue/ChoixAction.fxml", event);
            // controller = new
            // ControllerChoixAction("../../../../../target/classes/pnr/vue/images/especes/batracien.png");
        } else if (event.getSource() == btnChouette) {
            initVisualiser("Chouette");
            loadStage("../vue/ChoixAction.fxml", event);
            // controller = new
            // ControllerChoixAction("../../../../../target/classes/pnr/vue/images/especes/chouette.png");
        } else if (event.getSource() == btnGCI) {
            initVisualiser("GCI");
            loadStage("../vue/ChoixAction.fxml", event);
            // controller = new
            // ControllerChoixAction("../../../../../target/classes/pnr/vue/images/especes/gci.png");
        } else if (event.getSource() == btnHippo) {
            initVisualiser("Hippocampe");
            loadStage("../vue/ChoixAction.fxml", event);
            // controller = new
            // ControllerChoixAction("../../../../../target/classes/pnr/vue/images/especes/hippocampe.png");
        } else if (event.getSource() == btnLoutre) {
            initVisualiser("Loutre");
            loadStage("../vue/ChoixAction.fxml", event);
            // controller = new
            // ControllerChoixAction("../../../../../target/classes/pnr/vue/images/especes/loutre.png");
        }
    }
}

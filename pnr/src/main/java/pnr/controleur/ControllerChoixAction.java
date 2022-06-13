package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
// import animatefx.animation.*;
import javafx.event.ActionEvent;

public class ControllerChoixAction extends Controller {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button btnNewObs;

    @FXML
    private Button btnVisObs;

    @FXML
    private Button btnModObs;

    @FXML
    private Button btnBack;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnNewObs) {
            loadStage("../vue/NouvelleObservation.fxml", event);
        } else if (event.getSource() == btnVisObs) {
            loadStage("../vue/Visualiser.fxml", event);
        } else if (event.getSource() == btnModObs) {
            loadStage("../vue/DernierObservation.fxml", event);
        } else if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixEspeces.fxml", event);
        }
    }

}

package pnr.controleur;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ControllerChoixNidGCI extends Controller {

    @FXML
    private Button btnBack;

    @FXML
    private MFXButton btnNon;

    @FXML
    private MFXButton btnOui;

    @FXML
    private ImageView imgEspece;

    @FXML
    private Text nameEspece;

    @FXML
    private MFXScrollPane scrollPane;

    @FXML
    private Text textChoixNid;

    @FXML
    void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        } else if (event.getSource() == btnNon) {
            loadStage("../vue/NouvelleObservationGCIsansNid.fxml", event);
        } else if (event.getSource() == btnOui) {
            loadStage("../vue/NouvelleObservationGCIavecNid.fxml", event);
        }
    }
}
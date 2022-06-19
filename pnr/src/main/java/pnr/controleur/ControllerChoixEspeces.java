package pnr.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ControllerChoixEspeces extends Controller implements Initializable{

    @FXML
    private Button btnBatracien;

    @FXML
    private Button btnChouette;

    @FXML
    private Button btnDeco;

    @FXML
    private Button btnGCI;

    @FXML
    private Button btnHippo;

    @FXML
    private Button btnLoutre;

    @FXML
    private Text nameUser;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBatracien) {
            initVisualiser("Batracien");
            loadStage("../vue/ChoixAction.fxml", event);
        } else if (event.getSource() == btnChouette) {
            initVisualiser("Chouette");
            loadStage("../vue/ChoixAction.fxml", event);
        } else if (event.getSource() == btnGCI) {
            initVisualiser("GCI");
            loadStage("../vue/ChoixAction.fxml", event);
        } else if (event.getSource() == btnHippo) {
            initVisualiser("Hippocampe");
            loadStage("../vue/ChoixAction.fxml", event);
        } else if (event.getSource() == btnLoutre) {
            initVisualiser("Loutre");
            loadStage("../vue/ChoixAction.fxml", event);
        } else if (event.getSource() == btnDeco) {
            loadStage("../vue/LandingPage.fxml", event);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String name = this.getEventSrcVisualiser();
        if (name != null) {
            this.nameUser.setText(this.getEventSrcVisualiser());
        } else {
            this.nameUser.setText("Utilisateur inconnu");
        }
    }
}

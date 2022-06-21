package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
// import animatefx.animation.*;
import javafx.event.ActionEvent;

public class ControllerChoixAction extends Controller implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private MFXButton btnModObs;

    @FXML
    private MFXButton btnNewObs;

    @FXML
    private MFXButton btnVisObs;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView imgEspece;

    @FXML
    private Text nameEspece;

    @FXML
    private AnchorPane rootPane;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPage(imgEspece, nameEspece);
    }


}

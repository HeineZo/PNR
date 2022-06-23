package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
// import animatefx.animation.*;
import javafx.event.ActionEvent;

/**
 * Manages the choice of actions
 */
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

    /**
     * It's a function that loads a new stage depending on the button that was clicked.
     * 
     * @param event the event that triggered the method
     */
    @FXML
    private void handleBtnClick(ActionEvent event) throws IOException {
        if (event.getSource() == btnNewObs) {
            if (this.getEventSrcVisualiser().equals("Batracien")) {
                loadStage("../vue/NouvelleObservationBatracien.fxml", event);

            } else if (this.getEventSrcVisualiser().equals("Chouette")) {
                loadStage("../vue/NouvelleObservationChouette.fxml", event);
            } else if (this.getEventSrcVisualiser().equals("GCI")) {
                loadStage("../vue/ChoixNidGCI.fxml", event);
            } else if (this.getEventSrcVisualiser().equals("Hippocampe")) {
                loadStage("../vue/NouvelleObservationHippocampe.fxml", event);
            } else if (this.getEventSrcVisualiser().equals("Loutre")) {
                loadStage("../vue/NouvelleObservationLoutre.fxml", event);
            }
        } else if (event.getSource() == btnVisObs) {
            loadStage("../vue/Visualiser.fxml", event);
        } else if (event.getSource() == btnModObs) {
            loadStage("../vue/DernierObservation.fxml", event);
        } else if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixEspeces.fxml", event);
        }
    }

    /**
     * This function is called when the FXML file is loaded, and it initializes the page with the image
     * and name of the species.
     * 
     * @param location the location of the FXML file
     * @param resources the resources used to localize the root object, or null if the root object was
     * not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPage(imgEspece, nameEspece);
    }
}
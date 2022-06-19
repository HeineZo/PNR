package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

// import animatefx.animation.*;
import javafx.event.ActionEvent;

public class ControllerChoixAction extends Controller implements Initializable{
    private String eventSrc;

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
    private ImageView imgEspece;

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
        this.eventSrc = this.getEventSrcVisualiser();
        String urlImage;
        if (this.eventSrc.equals("Batracien")) {
            urlImage = "../../../../../target/classes/pnr/vue/images/especes/batracien.png";
        } else if (this.eventSrc.equals("Chouette")) {
            urlImage = "../../../../../target/classes/pnr/vue/images/especes/chouette.png";
        } else if (this.eventSrc.equals("GCI")) {
            urlImage = "../../../../../target/classes/pnr/vue/images/especes/gci.png";
        } else if (this.eventSrc.equals("Hippocampe")) {
            urlImage = "../../../../../target/classes/pnr/vue/images/especes/hippocampe.png";
        } else if (this.eventSrc.equals("Loutre")) {
            urlImage = "pnr/target/classes/pnr/vue/images/especes/loutre.png";
        } else {
            urlImage = "../../../../../target/classes/pnr/vue/images/especes/batracien.png";
        }
        changeImage(urlImage);
    }

    public void changeImage(String url) {
        Image image = new Image(new File(url).toURI().toString());
        imgEspece = new ImageView(image);
    }
}

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

public class ControllerChoixAction extends Controller implements Initializable{
    private String eventSrc;

    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane btnModObs;

    @FXML
    private MFXButton btnNewObs;

    @FXML
    private AnchorPane btnVisObs;

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
        String urlImage = "";
        String nameEspece = "";
        if (this.eventSrc != null) {
            this.eventSrc = this.getEventSrcVisualiser();
            if (this.eventSrc.equals("Batracien")) {
                urlImage = "especes/batracien.png";
                nameEspece = "Batracien";
            } else if (this.eventSrc.equals("Chouette")) {
                urlImage = "especes/chouette.png";
                nameEspece = "Chouette";
            } else if (this.eventSrc.equals("GCI")) {
                urlImage = "especes/gci.png";
                nameEspece = "GCI";
            } else if (this.eventSrc.equals("Hippocampe")) {
                urlImage = "especes/hippocampe.png";
                nameEspece = "Hippocampe";
            } else if (this.eventSrc.equals("Loutre")) {
                urlImage = "especes/loutre.png";
                nameEspece = "Loutre";
            } 
        } else {
            urlImage = "especes/null.png";
            nameEspece = "Espece inconnue";
        }
        changeImage(urlImage);
        changeText(nameEspece);
    }

    public void changeImage(String url) {
        //File file = new File(url);
        //Image image = new Image(url);
        //this.imgEspece.setImage(image);

        Image imProfile = new Image(getClass().getResourceAsStream(url));
        this.imgEspece.setImage(imProfile);
    }

    public void changeText(String text) {
        this.nameEspece.setText(text);
    }
}

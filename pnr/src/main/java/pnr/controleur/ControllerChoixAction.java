package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

// import animatefx.animation.*;
import javafx.event.ActionEvent;

public class ControllerChoixAction extends Controller {
    /*
    public ControllerChoixAction(String urlImage) {
        super();
        if(urlImage != null) {
            changeImage(urlImage);
        }
    }
    */

    public void changeImage(String url) {
        Image image = new Image(new File(url).toURI().toString());
        imgEspece = new ImageView(image);
    }

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
}

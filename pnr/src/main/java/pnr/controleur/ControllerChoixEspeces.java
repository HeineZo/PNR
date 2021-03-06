package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Manages actions from the Choose Cash page
 */
public class ControllerChoixEspeces extends Controller implements Initializable{

    @FXML
    private Button btnBatracien;

    @FXML
    private Button btnChouette;

    @FXML
    private MFXButton btnDeco;

    @FXML
    private Button btnGCI;

    @FXML
    private Button btnHippo;

    @FXML
    private Button btnLoutre;

    @FXML
    private Text nameUser;

    /**
    * Handle window changes on button click
    * 
    * @param event the event that triggered the method
    */
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
            if (btnDeco.getText().equals("Retour")){
                loadStage("../vue/ChoixActionAdmin.fxml", event);
            } else {
                loadStage("../vue/LandingPage.fxml", event);
            }
        }
    }

    /**
     * I'm trying to get the name of the user who is connected to the application and display it in a
     * label
     * 
     * @param location the location of the FXML file
     * @param resources the resources used to localize the root object, or null if the root object was
     * not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultSet rs = connect.executeQuery("SELECT prenom, nom FROM Utilisateur WHERE pseudonyme='" + this.getEventSrcNomUser()+"';");
        try {
            if (rs.next()) {
                this.nameUser.setText("Bienvenue "+rs.getString("prenom")+" "+rs.getString("nom"));
            } else {
                this.nameUser.setText("Utilisateur inconnu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.getEventSrcPermission().equals("1")){
            this.btnDeco.setText("Retour");
        }
    }
}
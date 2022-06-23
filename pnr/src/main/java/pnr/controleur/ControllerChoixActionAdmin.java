package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

/**
 * Manages the choice of actions of an admin user
 */
public class ControllerChoixActionAdmin extends Controller implements Initializable {

    @FXML
    private Button btnChoixAction;

    @FXML
    private Button btnDeco;

    @FXML
    private Button btnExport;

    @FXML
    private Button btnGererProfils;

    @FXML
    private Button btnModifierFiche;

    @FXML
    private Button btnNouvelleFiche;

    @FXML
    private Text nameUser;

   /**
    * Handle window changes on button click
    * 
    * @param event the event that triggered the method
    */
    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnNouvelleFiche) {
            loadStage("../vue/CreerUneFicheEspece.fxml", event);
        } else if (event.getSource() == btnModifierFiche) {
            loadStage("../vue/ModifierFiches.fxml", event);
        } else if (event.getSource() == btnGererProfils) {
            loadStage("../vue/GererProfils.fxml", event);
        } else if (event.getSource() == btnExport) {
            loadStage("../vue/Sauvegarde.fxml", event);
        } else if (event.getSource() == btnChoixAction) {
            loadStage("../vue/ChoixEspeces.fxml", event);
        } else if (event.getSource() == btnDeco) {
            loadStage("../vue/LandingPage.fxml", event);
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
    }
}

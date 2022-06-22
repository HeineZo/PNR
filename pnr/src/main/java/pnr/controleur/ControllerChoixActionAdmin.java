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

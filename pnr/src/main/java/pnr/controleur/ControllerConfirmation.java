package pnr.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ControllerConfirmation extends Controller implements Initializable{

    @FXML
    private Button btnAccueil;

    @FXML
    private Button btn1;

    @FXML
    private Text textConfirmation;

    private String eventSrcConfirmation;
    private String eventSrcPermission;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        this.eventSrcConfirmation = this.getEventSrcConfirmation();
        this.eventSrcPermission = this.getEventSrcPermission();
        if (event.getSource() == btnAccueil) {
            if(this.eventSrcPermission.equals("0")){
                loadStage("../vue/ChoixEspeces.fxml", event);
            } else if (this.eventSrcPermission.equals("1")){
                loadStage("../vue/ChoixActionAdmin.fxml", event);
            }
        } else if (event.getSource() == btn1) {
            if(this.eventSrcConfirmation.equals("ModifierObservation")){
                loadStage("../vue/ModifierUneObservation.fxml", event);
            } else if (this.eventSrcConfirmation.equals("AjouterObservation")){
                loadStage("../vue/NouvelleObservation.fxml", event);
            } else if (this.eventSrcConfirmation.equals("SuppressionProfil")){
                loadStage("../vue/NouveauProfil.fxml", event);
            } else if (this.eventSrcConfirmation.equals("ModifierProfil")){
                loadStage("../vue/ModifierUnProfil.fxml", event);
            } else if (this.eventSrcConfirmation.equals("NouveauProfil")){
                loadStage("../vue/NouveauProfil.fxml", event);
            } else if (this.eventSrcConfirmation.equals("ModifierFicheEspece")){
                loadStage("../vue/ModifierUneFicheEspece.fxml", event);
            } else if (this.eventSrcConfirmation.equals("CreerFicheEspece")){
                loadStage("../vue/CreerUneFicheEspece.fxml", event);
            }
        }            
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.eventSrcConfirmation = this.getEventSrcConfirmation();
        if (this.eventSrcConfirmation.equals("CreerFicheEspece")) {
            this.textConfirmation.setText("Votre fiche espèce a bien été créée");
            btn1.setText("Nouvelle Création");
        } else if (this.eventSrcConfirmation.equals("ModifierFicheEspece")) {
            this.textConfirmation.setText("Votre fiche espèce a bien été modifiée");
            btn1.setText("Nouvelle modification");
        } else if (this.eventSrcConfirmation.equals("NouveauProfil")) {
            this.textConfirmation.setText("Le profil a bien été créé");
            btn1.setText("Nouveau profil");
        } else if (this.eventSrcConfirmation.equals("ModifierProfil")) {
            this.textConfirmation.setText("Le profil a bien été modifié");
            btn1.setText("Nouvelle modification");
        } else if (this.eventSrcConfirmation.equals("SuppressionProfil")) {
            this.textConfirmation.setText("Le profil a bien été supprimé");
            btn1.setText("Nouveau profil");
        } else if (this.eventSrcConfirmation.equals("AjouterObservation")) {
            this.textConfirmation.setText("Votre nouvelle observation a bien été enregistrée");
            btn1.setText("Nouvelle Observation");
        } else if (this.eventSrcConfirmation.equals("ModifierObservation")) {
            this.textConfirmation.setText("Votre nouvelle observation a bien été modifiée");
            btn1.setText("Nouvelle modification");
        }
    }

}

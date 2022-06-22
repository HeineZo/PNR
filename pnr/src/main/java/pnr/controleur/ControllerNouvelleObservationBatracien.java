package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ControllerNouvelleObservationBatracien extends Controller implements Initializable{
    @FXML
    private Button btnBack;

    @FXML
    private ImageView imgEspece = new ImageView();

    @FXML
    private Text nameEspece = new Text();

    @FXML
    private MFXButton envoi;

    @FXML
    private MFXScrollPane scrollPane;

    @FXML
    private MFXDatePicker datePicker;

    @FXML
    private MFXFilterComboBox<String> cbObservateur;
    private ObservableList<String> observateur = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtHeure;

    @FXML
    private MFXTextField txtCoordY;

    @FXML
    private MFXTextField txtCoordX;

    @FXML
    private MFXComboBox<String> cbEspece;
    private ObservableList<String> espece = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtAdulte;

    @FXML
    private MFXTextField txtPonte;

    @FXML
    private MFXTextField txtTetard;

    @FXML
    private MFXTextField txtTemperature;

    @FXML
    private MFXComboBox<String> cbTemperature;
    private ObservableList<String> temp = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbCiel;
    private ObservableList<String> ciel = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbPluie;
    private ObservableList<String> pluie = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbVent;
    private ObservableList<String> vent = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbZHTemp;
    private ObservableList<String> temporaire = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtProfondeur;

    @FXML
    private MFXTextField txtSurface;

    @FXML
    private MFXComboBox<String> cbTypeMare;
    private ObservableList<String> typeMare = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbPente;
    private ObservableList<String> pente = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbOuverture;
    private ObservableList<String> ouverture = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbNatureVege;
    private ObservableList<String> natureVege = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtVegetation;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (getUserClicked() != null) {
            idObs = getUserClicked();
            modifierObs();
            resetUserClicked();
        }

        ResultSet rs = connect.executeQuery("SELECT nom,prenom FROM Observateur ORDER BY nom,prenom;");

        try {
            while (rs.next()) {
                if (rs.getString("nom") != null){
                    this.observateur.add(rs.getString("nom"));
                } else if (rs.getString("prenom") != null){
                    this.observateur.add(rs.getString("prenom"));
                }
            }
            this.cbObservateur.setItems(this.observateur);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.ciel.add("dégagé");
        this.ciel.add("semi-dégagé");
        this.ciel.add("nuageux");
        this.cbCiel.setItems(this.ciel);

        this.pluie.add("non");
        this.pluie.add("légère");
        this.pluie.add("moyenne");
        this.pluie.add("forte");
        this.cbPluie.setItems(this.pluie);

        this.temp.add("froid");
        this.temp.add("moyen");
        this.temp.add("chaud");
        this.cbTemperature.setItems(this.temp);

        this.vent.add("non");
        this.vent.add("léger");
        this.vent.add("moyen");
        this.vent.add("fort");
        this.cbVent.setItems(this.vent);

        this.espece.add("calamite");
        this.espece.add("pelodyte");
        this.cbEspece.setItems(this.espece);

        this.temporaire.add("0");
        this.temporaire.add("1");
        this.cbZHTemp.setItems(this.temporaire);

        this.typeMare.add("Prairie");
        this.typeMare.add("Etang");
        this.typeMare.add("Marais");
        this.typeMare.add("Mare");
        this.cbTypeMare.setItems(this.typeMar);
    }

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        } else if (event.getSource() == envoi) {
            
            initConfirmation("AjouterObservation");
            loadStage("../vue/Confirmation.fxml", event);
        }
    }
}
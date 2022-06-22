package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import pnr.modele.donneeAddsOn.TabObservateur;

public class ControllerNouvelleObservationGCI extends Controller implements Initializable{
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
    private MFXFilterComboBox<TabObservateur> cbObservateur = new MFXFilterComboBox<>();
    private ObservableList<TabObservateur> observateur = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtHeure = new MFXTextField();

    @FXML
    private MFXTextField txtCoordY = new MFXTextField();

    @FXML
    private MFXTextField txtCoordX = new MFXTextField();

    @FXML
    private MFXComboBox<String> cbNature = new MFXComboBox<>();
    private ObservableList<String> nature  = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtNombre = new MFXTextField();

    @FXML
    private MFXComboBox<String> cbPresent = new MFXComboBox<>();
    private ObservableList<String> present  = FXCollections.observableArrayList();

    private String idObs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (getUserClicked() != null) {
            idObs = getUserClicked();
            modifierObs();
            resetUserClicked();
        }
        ResultSet rs = connect.executeQuery("SELECT * FROM Observateur ORDER BY nom,prenom;");

        try {
            while (rs.next()) {
                if(rs.getString("nom") != null){
                   this.observateur.add(new TabObservateur(rs.getInt("idObservateur"), rs.getString("nom"), rs.getString("prenom"))); 
                }
            }
            StringConverter<TabObservateur> converter = FunctionalStringConverter.to(person -> (person == null) ? "" : person.getNom() + " " + person.getPrenom());
            Function<String, Predicate<TabObservateur>> filterFunction = s -> obs -> StringUtils.containsIgnoreCase((CharSequence) converter.toString(obs), (CharSequence) s);
            this.cbObservateur.setItems(this.observateur);
            this.cbObservateur.setFilterFunction(filterFunction);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.nature.add("Oeuf");
        this.nature.add("Poussin");
        this.nature.add("Nid");
        this.cbNature.setItems(this.nature);

        this.present.add("non");
        this.present.add("oui");
        this.cbPresent.setItems(this.present);
    }

    @FXML
    private void handleBtnClick(ActionEvent event) throws SQLException {
        if (this.nameEspece.getText().equals("Modifier une observation")) {
            if (event.getSource() == btnBack) loadStage("../vue/DernierObservation.fxml", event);
            else if (event.getSource() == envoi) updateDonnees(event);

        } else {
            if (event.getSource() == btnBack) loadStage("../vue/ChoixAction.fxml", event);
            else if (event.getSource() == envoi) ajouteDonnees(event);
        }
    }

    private void modifierObs() {
        this.nameEspece.setText("Modifier une observation");
        this.txtCoordX.setDisable(true);
        this.txtCoordY.setDisable(true);
        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Loutre JOIN Observation ON ObsL=idObs JOIN AObserve ON ObsL=lObservation WHERE ObsL = '" + this.idObs + "';");
        try {
            while (rs.next()) {
                // this.txtDate.setText(rs.getString("dateObs"));
                // this.cbObservateur.setValue(rs.getString("lobservateur"));
                // this.txtHeure.setText(rs.getString("heureObs"));
                // this.txtCoordY.setText(rs.getString("lieu_Lambert_Y"));
                // this.txtCoordX.setText(rs.getString("lieu_Lambert_X"));
                // this.txtCommune.setText(rs.getString("commune"));
                // this.txtLieuDit.setText(rs.getString("lieuDit"));
                // this.cbIndice.setValue(rs.getString("indice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void ajouteDonnees(ActionEvent event) throws SQLException{
    }

    private void updateDonnees(ActionEvent event) throws SQLException{
    }

}

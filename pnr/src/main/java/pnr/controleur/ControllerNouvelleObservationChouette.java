package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import pnr.modele.donneeAddsOn.TabObservateur;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.DateTimeUtils;
import io.github.palexdev.materialfx.utils.StringUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ControllerNouvelleObservationChouette extends Controller implements Initializable {
    
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
    private MFXFilterComboBox<String> cbObservateur = new MFXFilterComboBox<>();
    private ObservableList<String> observateur = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtHeure;

    @FXML
    private MFXTextField txtCoordY;

    @FXML
    private MFXTextField txtCoordX;

    @FXML
    private MFXComboBox<String> cbTypeObs;
    private ObservableList<String> typeObs = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbEspece;
    private ObservableList<String> espece = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbProto;
    private ObservableList<String> protocole = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbSexe;
    private ObservableList<String> sexe = FXCollections.observableArrayList();

    private String idObs;

    @FXML
    private MFXTextField txtNumInd;

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

        this.espece.add("EFFRAIE");
        this.espece.add("CHEVECHE");
        this.espece.add("HULOTTE");
        this.cbEspece.setItems(this.espece);

        this.sexe.add("MALE");
        this.sexe.add("FEMELLE");
        this.sexe.add("INCONNU");
        this.cbSexe.setItems(this.sexe);

        this.protocole.add("0");
        this.protocole.add("1");
        this.cbProto.setItems(this.protocole);

        this.typeObs.add("SONORE");
        this.typeObs.add("VISUEL");
        this.typeObs.add("SONORE ET VISUEL");
        this.cbTypeObs.setItems(this.typeObs);
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

    // @Override
    // public void initialize(URL location, ResourceBundle resources){
    //     this.eventSrc = initPage(this.imgEspece, this.nameEspece);
    //     try {
    //         switch (eventSrc) {
    //             case "Batracien":
    //                 loadUI("../vue/ObservationBatracien.fxml");
    //                 break;
    //             case "Chouette":
    //                 loadUI("../vue/ObservationChouette.fxml");
    //                 break;
    //             case "GCI":
    //                 loadUI("../vue/ObservationGCI.fxml");
    //                 break;
    //             case "Hippocampe":
    //                 loadUI("../vue/ObservationHippocampe.fxml");
    //                 break;
    //             case "Loutre":
    //                 loadUI("../vue/ObservationLoutre.fxml");
    //                 break;
    //             default:
    //                 break;
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     // loadList();

    // }


    // private void loadList() {
    //     ArrayList<String> list = new ArrayList<String>();
        
    //     ResultSet rs = connect.executeQuery("SELECT * FROM Observateur");
    //     try {
    //         while (rs.next()){
    //             list.add(rs.getString("nom") + " " + rs.getString("prenom"));
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     ObservableList<String> listObservator = FXCollections.observableArrayList(list);
    //     observatorList.setItems(listObservator);
    //     observatorList.features().enableBounceEffect();
	// 	observatorList.features().enableSmoothScrolling(0.5);
    // }

}
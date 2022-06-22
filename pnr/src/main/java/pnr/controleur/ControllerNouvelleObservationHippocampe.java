package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
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

public class ControllerNouvelleObservationHippocampe extends Controller implements Initializable{
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
    private MFXDatePicker txtDate;

    @FXML
    // private MFXFilterComboBox<TabObservateur> cbObservateur = new MFXFilterComboBox<>();
    // private ObservableList<TabObservateur> observateur = FXCollections.observableArrayList();
    private MFXFilterComboBox<String> cbObservateur = new MFXFilterComboBox<>();
    private ObservableList<String> observateur = FXCollections.observableArrayList();
    
    @FXML
    private MFXTextField txtHeure;

    @FXML
    private MFXTextField txtCoordY;

    @FXML
    private MFXTextField txtCoordX;

    @FXML
    private ComboBox<String> cbEspece;
    private ObservableList<String> espece = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cbSexe;
    private ObservableList<String> sexe = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cbTypePeche;
    private ObservableList<String> typePeche = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtTemperature;

    @FXML
    private ComboBox<String> cbGestant;
    private ObservableList<String> gestant = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtTaille;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if (getUserClicked() != null) {
        //     idObs = getUserClicked();
        //     modifierObs();
        //     resetUserClicked();
        // }
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

        this.espece.add("Syngnathus acus");
        this.espece.add("Hippocampus guttulatus");
        this.espece.add("Hippocampus Hippocampus");
        this.espece.add("Entelurus aequoreus");
        this.cbEspece.setItems(this.espece);

        this.sexe.add("MALE");
        this.sexe.add("FEMELLE");
        this.sexe.add("INCONNU");
        this.cbSexe.setItems(this.sexe);

        this.typePeche.add("casierCrevettes");
        this.typePeche.add("casierMorgates");
        this.typePeche.add("PetitFilet");
        this.typePeche.add("verveuxAnguilles");
        this.cbTypePeche.setItems(this.typePeche);

        this.gestant.add("non");
        this.gestant.add("oui");
        this.cbGestant.setItems(this.gestant);

        this.txtHeure.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtCoordY.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtCoordX.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.cbEspece.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });        
        this.cbSexe.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });       
        this.txtDate.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.cbTypePeche.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtTemperature.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });      
        this.cbGestant.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtTaille.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
    }

    @FXML
    private void handleBtnClick(ActionEvent event) throws SQLException {
        // if (this.nameEspece.getText().equals("Modifier une observation")) {
        //     if (event.getSource() == btnBack) loadStage("../vue/DernierObservation.fxml", event);
        //     else if (event.getSource() == envoi) updateDonnees(event);
        // } else {
        if (event.getSource() == btnBack){
            loadStage("../vue/ChoixAction.fxml", event);
        }else if (event.getSource() == envoi){
            ajouteDonnees(event);
        }
    }

    // private void modifierObs() {
    //     this.nameEspece.setText("Modifier une observation");
    //     this.txtCoordX.setDisable(true);
    //     this.txtCoordY.setDisable(true);
    //     ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Loutre JOIN Observation ON ObsL=idObs JOIN AObserve ON ObsL=lObservation WHERE ObsL = '" + this.idObs + "';");
    //     try {
    //         while (rs.next()) {
    //             this.txtDate.setText(rs.getString("dateObs"));
    //             // this.cbObservateur.setValue(rs.getString("lobservateur"));
    //             this.txtHeure.setText(rs.getString("heureObs"));
    //             this.txtCoordY.setText(rs.getString("lieu_Lambert_Y"));
    //             this.txtCoordX.setText(rs.getString("lieu_Lambert_X"));
    //             this.txtCommune.setText(rs.getString("commune"));
    //             this.txtLieuDit.setText(rs.getString("lieuDit"));
    //             this.cbIndice.setValue(rs.getString("indice"));
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    private void ajouteDonnees(ActionEvent event) throws SQLException{
        ResultSet rs = connect.executeQuery("SELECT idObs FROM Observation ORDER BY idObs DESC LIMIT 1;");
        int idDerniereObs = 0;
        while (rs.next()) {
            idDerniereObs = rs.getInt("idObs");
        }
        connect.executeUpdate("INSERT INTO Lieu VALUES ("+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");  
        connect.executeUpdate("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+this.txtDate.getText()+"',null,"+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");
        System.out.println("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+this.txtDate.getText()+"','"+this.txtHeure.getText()+"','"+this.txtCoordX.getText()+"','"+this.txtCoordY.getText()+"');");
        rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
        int lObservateur = 0;
        while (rs.next()) {
            lObservateur = rs.getInt("idObservateur");
            
        }
        connect.executeUpdate("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");
        System.out.println("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");
        int estGestant = 0;
        if(this.cbGestant.getValue().equals("oui")){
            estGestant = 1;
        }
        connect.executeUpdate("INSERT INTO Obs_Hippocampe VALUES ("+(idDerniereObs + 1)+",'"+this.cbEspece.getValue()+"','"+this.cbSexe.getValue()+"',"+this.txtTemperature.getText()+",'"+this.cbTypePeche.getValue()+"',"+this.txtTaille.getText()+","+estGestant+");");  
        System.out.println("INSERT INTO Obs_Hippocampe VALUES ("+(idDerniereObs + 1)+",'"+this.cbEspece.getValue()+"','"+this.cbSexe.getValue()+"',"+this.txtTemperature.getText()+",'"+this.cbTypePeche.getValue()+"',"+this.txtTaille.getText()+","+estGestant+");");

        initConfirmation("AjouterObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }

    private void checkDisable() {
        if(!txtHeure.getText().isEmpty() && !txtCoordY.getText().isEmpty() && !txtTaille.getText().isEmpty() && !txtCoordX.getText().isEmpty() && !txtTemperature.getText().isEmpty() 
        && cbEspece.getValue()!= null && cbSexe.getValue() != null && cbTypePeche.getValue() != null && cbGestant.getValue() != null) {
            envoi.setDisable(false);
        } else {
            envoi.setDisable(true);
        }
    }
}
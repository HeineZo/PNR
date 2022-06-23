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

import pnr.modele.util.Dates;

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
    private MFXDatePicker txtDate;

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
    private MFXComboBox<String> cbEspece;
    private ObservableList<String> espece = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtAdulte;

    @FXML
    private MFXTextField txtAmplexus;

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
    
    @FXML
    private Dates date = new Dates();

    private String idObs;

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
        this.cbTypeMare.setItems(this.typeMare);

        this.pente.add("Raide");
        this.pente.add("Abrupte");
        this.pente.add("Douce");
        this.cbPente.setItems(this.pente);

        this.ouverture.add("Abritee");
        this.ouverture.add("Semi-Abritee");
        this.ouverture.add("Ouverte");
        this.cbOuverture.setItems(this.ouverture);

        this.natureVege.add("environnement");
        this.natureVege.add("bordure");
        this.natureVege.add("ripistyle");
        this.cbNatureVege.setItems(this.natureVege);

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
        this.txtAdulte.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });       
        this.txtAmplexus.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtPonte.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.txtTetard.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.txtTemperature.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.cbTemperature.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.cbCiel.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.cbPluie.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.cbVent.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.cbZHTemp.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.txtProfondeur.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.txtSurface.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.cbTypeMare.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.cbPente.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.cbOuverture.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.cbNatureVege.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });

        this.txtVegetation.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
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

    private void ajouteDonnees(ActionEvent event) throws SQLException{
        ResultSet rs = connect.executeQuery("SELECT idObs FROM Observation ORDER BY idObs DESC LIMIT 1;");
        int idDerniereObs = 0;
        while (rs.next()) {
            idDerniereObs = rs.getInt("idObs");
        }
        connect.executeUpdate("INSERT INTO Lieu VALUES ("+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");  
        String laDate = date.dateToFormat(this.txtDate.getText()); 
        connect.executeUpdate("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+laDate+"',null,"+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");
        // System.out.println("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+this.txtDate.getText()+"','"+this.txtHeure.getText()+"','"+this.txtCoordX.getText()+"','"+this.txtCoordY.getText()+"');");
        rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
        int lObservateur = 0;
        while (rs.next()) {
            lObservateur = rs.getInt("idObservateur");
            
        }
        connect.executeUpdate("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");
        // System.out.println("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");
        
        // System.out.println("INSERT INTO Lieu VALUES ("+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");
        rs = connect.executeQuery("SELECT idVegeLieu FROM Lieu_Vegetation ORDER BY idVegeLieu DESC LIMIT 1;");
        int idDerniereVegeLieu = 0;
        while (rs.next()) {
            idDerniereVegeLieu = rs.getInt("idVegeLieu");
        }
        connect.executeUpdate("INSERT INTO Lieu_Vegetation VALUES("+(idDerniereVegeLieu+1)+");");
        // System.out.println("INSERT INTO Obs_Loutre VALUES ("+(idDerniereObs + 1)+",'"+this.txtCommune.getText()+"','"+this.txtLieuDit.getText()+"','"+this.cbIndice.getValue()+"');");
        rs = connect.executeQuery("SELECT idVege FROM Vegetation ORDER BY idVege DESC LIMIT 1;");
        int idDerniereVege = 0;
        while (rs.next()) {
            idDerniereVege = rs.getInt("idVege");
        }
        connect.executeUpdate("INSERT INTO Vegetation VALUES("+(idDerniereVege+1)+",'"+this.cbNatureVege.getValue()+"','"+this.txtVegetation.getText()+"',"+(idDerniereVegeLieu+1)+");");

        rs = connect.executeQuery("SELECT zh_id FROM ZoneHumide ORDER BY zh_id DESC LIMIT 1;");
        int idDerniereZH = 0;
        while (rs.next()) {
            idDerniereZH = rs.getInt("zh_id");
        }
        connect.executeUpdate("INSERT INTO ZoneHumide VALUES("+(idDerniereZH+1)+","+this.cbZHTemp.getValue()+","+this.txtProfondeur.getText()+","+this.txtSurface.getText()+",'"+this.cbTypeMare.getValue()+"','"+this.cbPente.getValue()+"','"+this.cbOuverture.getValue()+"');");
        connect.executeUpdate("INSERT INTO Obs_Batracien VALUES("+(idDerniereObs+1)+",'"+this.cbEspece.getValue()+"',"+this.txtAdulte.getText()+","+this.txtAmplexus.getText()+","+this.txtPonte.getText()+","+this.txtTetard.getText()+","
        +this.txtTemperature.getText()+",'"+this.cbCiel.getValue()+"','"+this.cbTemperature.getValue()+"','"+this.cbVent.getValue()+"','"+this.cbPluie.getValue()+"',"+(idDerniereZH+1)+","+(idDerniereVege+1)+");");

        initConfirmation("AjouterObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }

    private void modifierObs() {
        this.nameEspece.setText("Modifier une observation");
        this.txtCoordX.setDisable(true);
        this.txtCoordY.setDisable(true);
        // ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Loutre JOIN Observation ON B=idObs JOIN AObserve ON ObsL=lObservation WHERE ObsL = " + idObs + ";");
        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Batracien LEFT JOIN Observation ON ObsB=idObs LEFT JOIN AObserve ON lobservation = idObs "+
        "LEFT JOIN Observateur ON lobservateur = idObservateur LEFT JOIN ZoneHumide ON concerne_ZH = zh_id LEFT JOIN Vegetation ON concernes_vege = idVege WHERE obsB='"+idObs+"';");
        try {
            String datePasFormate = "";
            while (rs.next()) {
                datePasFormate = rs.getString("dateObs");
                if (rs.getString("nom") != null){
                    this.cbObservateur.setText(rs.getString("nom"));
                } else if (rs.getString("prenom") != null){
                    this.cbObservateur.setText(rs.getString("prenom"));
                }

                this.txtHeure.setText(rs.getString("heureObs"));
                this.txtCoordY.setText(rs.getString("lieu_Lambert_Y"));
                this.txtCoordX.setText(rs.getString("lieu_Lambert_X"));
                this.cbEspece.setText(rs.getString("espece"));
                this.txtAdulte.setText(rs.getString("nombreAdultes"));
                this.txtAmplexus.setText(rs.getString("nombreAmplexus"));
                this.txtPonte.setText(rs.getString("nombrePonte"));
                this.txtTetard.setText(rs.getString("nombreTetard"));
                this.txtTemperature.setText(rs.getString("temperature"));
                this.cbTemperature.setText(rs.getString("meteo_temp"));
                this.cbCiel.setText(rs.getString("meteo_ciel"));
                this.cbVent.setText(rs.getString("meteo_vent"));
                this.cbPluie.setText(rs.getString("meteo_pluie"));
                this.cbZHTemp.setText(rs.getString("concerne_ZH"));
                this.txtProfondeur.setText(rs.getString("zh_profondeur"));
                this.txtSurface.setText(rs.getString("zh_surface"));
                this.cbTypeMare.setText(rs.getString("zh_typeMare"));
                this.cbPente.setText(rs.getString("zh_pente"));
                this.cbOuverture.setText(rs.getString("zh_ouverture"));
                this.cbNatureVege.setText(rs.getString("natureVege"));
                this.txtVegetation.setText(rs.getString("vegetation"));
            }
            String laDate = date.formatToDate(datePasFormate); 
            this.txtDate.setText(laDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateDonnees(ActionEvent event) throws SQLException{
        ResultSet rs = connect.executeQuery("SELECT idObs FROM Observation ORDER BY idObs DESC LIMIT 1;");
        int idDerniereObs = 0;
        while (rs.next()) {
            idDerniereObs = rs.getInt("idObs");
        }
        connect.executeUpdate("INSERT INTO Lieu VALUES ("+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");  
        String laDate = date.dateToFormat(this.txtDate.getText()); 
        connect.executeUpdate("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+laDate+"',null,"+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");
        // System.out.println("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+this.txtDate.getText()+"','"+this.txtHeure.getText()+"','"+this.txtCoordX.getText()+"','"+this.txtCoordY.getText()+"');");
        rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
        int lObservateur = 0;
        while (rs.next()) {
            lObservateur = rs.getInt("idObservateur");
            
        }
        connect.executeUpdate("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");
        
        rs = connect.executeQuery("SELECT idVegeLieu FROM Lieu_Vegetation ORDER BY idVegeLieu DESC LIMIT 1;");
        int idDerniereVegeLieu = 0;
        while (rs.next()) {
            idDerniereVegeLieu = rs.getInt("idVegeLieu");
        }
        connect.executeUpdate("INSERT INTO Lieu_Vegetation VALUES("+(idDerniereVegeLieu+1)+");");
        rs = connect.executeQuery("SELECT idVege FROM Vegetation ORDER BY idVege DESC LIMIT 1;");
        int idDerniereVege = 0;
        while (rs.next()) {
            idDerniereVege = rs.getInt("idVege");
        }
        connect.executeUpdate("INSERT INTO Vegetation VALUES("+(idDerniereVege+1)+",'"+this.cbNatureVege.getValue()+"','"+this.txtVegetation.getText()+"',"+(idDerniereVegeLieu+1)+");");

        rs = connect.executeQuery("SELECT zh_id FROM ZoneHumide ORDER BY zh_id DESC LIMIT 1;");
        int idDerniereZH = 0;
        while (rs.next()) {
            idDerniereZH = rs.getInt("zh_id");
        }
        connect.executeUpdate("INSERT INTO ZoneHumide VALUES("+(idDerniereZH+1)+","+this.cbZHTemp.getValue()+","+this.txtProfondeur.getText()+","+this.txtSurface.getText()+",'"+this.cbTypeMare.getValue()+"','"+this.cbPente.getValue()+"','"+this.cbOuverture.getValue()+"');");
        connect.executeUpdate("INSERT INTO Obs_Batracien VALUES("+(idDerniereObs+1)+",'"+this.cbEspece.getValue()+"',"+this.txtAdulte.getText()+","+this.txtAmplexus.getText()+","+this.txtPonte.getText()+","+this.txtTetard.getText()+","
        +this.txtTemperature.getText()+",'"+this.cbCiel.getValue()+"','"+this.cbTemperature.getValue()+"','"+this.cbVent.getValue()+"','"+this.cbPluie.getValue()+"',"+(idDerniereZH+1)+","+(idDerniereVege+1)+");");

        initConfirmation("ModifierObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }

    private void checkDisable() {
        if(!txtCoordY.getText().isEmpty() && !txtCoordX.getText().isEmpty() && !txtHeure.getText().isEmpty() && cbEspece.getValue() != null && !txtAdulte.getText().isEmpty() && !txtAmplexus.getText().isEmpty() && !txtPonte.getText().isEmpty() &&
        !txtTetard.getText().isEmpty() && !txtTemperature.getText().isEmpty() && cbTemperature.getValue() != null && cbCiel.getValue() != null && cbPluie.getValue() != null && cbVent.getValue() != null && cbZHTemp.getValue() != null &&
        !txtProfondeur.getText().isEmpty() && !txtSurface.getText().isEmpty() && cbTypeMare.getValue() != null && cbPente.getValue() != null && cbOuverture.getValue() != null && cbNatureVege.getValue() != null && !txtVegetation.getText().isEmpty()){
            envoi.setDisable(false);
        } else {
            envoi.setDisable(true);
        }
    }

}
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

/**
 * Manages the NouvelleObservationBatracien page
 */
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
    private MFXButton supprimer;

    @FXML
    private MFXScrollPane scrollPane;

    @FXML
    private MFXDatePicker txtDate = new MFXDatePicker();

    @FXML
    private MFXFilterComboBox<String> cbObservateur = new MFXFilterComboBox<>();
    private ObservableList<String> observateur = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtHeure = new MFXTextField();

    @FXML
    private MFXTextField txtCoordY = new MFXTextField();

    @FXML
    private MFXTextField txtCoordX = new MFXTextField();

    @FXML
    private MFXComboBox<String> cbEspece = new MFXComboBox<>();
    private ObservableList<String> espece = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtAdulte = new MFXTextField();

    @FXML
    private MFXTextField txtAmplexus = new MFXTextField();

    @FXML
    private MFXTextField txtPonte = new MFXTextField();

    @FXML
    private MFXTextField txtTetard = new MFXTextField();

    @FXML
    private MFXTextField txtTemperature = new MFXTextField();

    @FXML
    private MFXComboBox<String> cbTemperature = new MFXComboBox<>();
    private ObservableList<String> temp = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbCiel = new MFXComboBox<>();
    private ObservableList<String> ciel = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbPluie = new MFXComboBox<>();
    private ObservableList<String> pluie = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbVent = new MFXComboBox<>();
    private ObservableList<String> vent = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbZHTemp = new MFXComboBox<>();
    private ObservableList<String> temporaire = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtProfondeur = new MFXTextField();

    @FXML
    private MFXTextField txtSurface = new MFXTextField();

    @FXML
    private MFXComboBox<String> cbTypeMare = new MFXComboBox<>();
    private ObservableList<String> typeMare = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbPente = new MFXComboBox<>();
    private ObservableList<String> pente = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbOuverture = new MFXComboBox<>();
    private ObservableList<String> ouverture = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbNatureVege = new MFXComboBox<>();
    private ObservableList<String> natureVege = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtVegetation = new MFXTextField();  
    
    @FXML
    private Dates date = new Dates();

    private String idObs;

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
        this.natureVege.add("ripisyle");
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

    /**
     * It's a function that loads a new stage depending on the button that was clicked.
     * 
     * @param event the event that triggered the method
     */
    @FXML
    private void handleBtnClick(ActionEvent event) throws SQLException {
        if (this.nameEspece.getText().equals("Modifier une observation")) {
            if (event.getSource() == btnBack) loadStage("../vue/DernierObservation.fxml", event);
            else if (event.getSource() == envoi) updateDonnees(event);
            else if (event.getSource() == supprimer){
                connect.executeUpdate("DELETE FROM Obs_Batracien WHERE obsB ='"+idObs+"';");
                initConfirmation("SuppressionObservation");
                loadStage("../vue/Confirmation.fxml", event);
            } 

        } else {
            if (event.getSource() == btnBack) loadStage("../vue/ChoixAction.fxml", event);
            else if (event.getSource() == envoi) ajouteDonnees(event);
        }
    }

    /**
     * It adds data to the database
     * 
     * @param event the event that triggered the method
     */
    private void ajouteDonnees(ActionEvent event) throws SQLException{
        ResultSet rs = connect.executeQuery("SELECT idObs FROM Observation ORDER BY idObs DESC LIMIT 1;");
        int idDerniereObs = 0;
        while (rs.next()) {
            idDerniereObs = rs.getInt("idObs");
        }
        connect.executeUpdate("INSERT INTO Lieu VALUES ("+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");  
        String laDate = date.dateToFormat(this.txtDate.getText()); 
        connect.executeUpdate("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+laDate+"',null,"+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");
        
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

        initConfirmation("AjouterObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }

    /**
     * It gets the data from the database and puts it in the textfields
     */
    private void modifierObs() {
        this.nameEspece.setText("Modifier une observation");
        this.txtCoordX.setDisable(true);
        this.txtCoordY.setDisable(true);
        this.supprimer.setVisible(true);

        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Batracien LEFT JOIN Observation ON ObsB=idObs LEFT JOIN AObserve ON lobservation = idObs "+
        "LEFT JOIN Observateur ON lobservateur = idObservateur LEFT JOIN ZoneHumide ON concerne_ZH = zh_id LEFT JOIN Vegetation ON concernes_vege = idVege WHERE obsB='"+idObs+"';");
        try {
            String datePasFormate = "";
            String laDate = "";
            while (rs.next()) {
                datePasFormate = rs.getString("dateObs");
                if (rs.getString("nom") != null){
                    this.cbObservateur.setText(rs.getString("nom"));
                    this.cbObservateur.setValue(rs.getString("nom"));
                } else if (rs.getString("prenom") != null){
                    this.cbObservateur.setText(rs.getString("prenom"));
                    this.cbObservateur.setValue(rs.getString("prenom"));
                }

                this.txtHeure.setText(rs.getString("heureObs"));
                this.txtCoordY.setText(rs.getString("lieu_Lambert_Y"));
                this.txtCoordX.setText(rs.getString("lieu_Lambert_X"));
                this.cbEspece.setText(rs.getString("espece"));
                this.cbEspece.setValue(rs.getString("espece"));
                this.txtAdulte.setText(rs.getString("nombreAdultes"));
                this.txtAmplexus.setText(rs.getString("nombreAmplexus"));
                this.txtPonte.setText(rs.getString("nombrePonte"));
                this.txtTetard.setText(rs.getString("nombreTetard"));
                this.txtTemperature.setText(rs.getString("temperature"));
                this.cbTemperature.setText(rs.getString("meteo_temp"));
                this.cbTemperature.setValue(rs.getString("meteo_temp"));
                this.cbCiel.setText(rs.getString("meteo_ciel"));
                this.cbCiel.setValue(rs.getString("meteo_ciel"));
                this.cbVent.setText(rs.getString("meteo_vent"));
                this.cbVent.setValue(rs.getString("meteo_vent"));
                this.cbPluie.setText(rs.getString("meteo_pluie"));
                this.cbPluie.setValue(rs.getString("meteo_pluie"));
                this.cbZHTemp.setText(rs.getString("zh_temporaire"));
                this.cbZHTemp.setValue(rs.getString("zh_temporaire"));
                this.txtProfondeur.setText(rs.getString("zh_profondeur"));
                this.txtSurface.setText(rs.getString("zh_surface"));
                this.cbTypeMare.setText(rs.getString("zh_typeMare"));
                this.cbTypeMare.setValue(rs.getString("zh_typeMare"));
                this.cbPente.setText(rs.getString("zh_pente"));
                this.cbPente.setValue(rs.getString("zh_pente"));
                this.cbOuverture.setText(rs.getString("zh_ouverture"));
                this.cbOuverture.setValue(rs.getString("zh_ouverture"));
                this.cbNatureVege.setText(rs.getString("natureVege"));
                this.cbNatureVege.setValue(rs.getString("natureVege"));
                this.txtVegetation.setText(rs.getString("vegetation"));
            }
            if (!datePasFormate.equals("")) {
                laDate = date.formatToDate(datePasFormate); 
            }
            this.txtDate.setText(laDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * It updates the database with the new values of the fields
     * 
     * @param event the event that triggered the method
     */
    private void updateDonnees(ActionEvent event) throws SQLException{

        String laDate = date.dateToFormat(this.txtDate.getText()); 
        ResultSet rs;
        connect.executeUpdate("UPDATE Observation SET dateObs='"+laDate+"' WHERE idObs='"+idObs+"';");
        connect.executeUpdate("UPDATE Observation SET heureObs='"+this.txtHeure.getText()+"' WHERE idObs='"+idObs+"';");
        connect.executeUpdate("UPDATE Observation SET lieu_Lambert_X="+this.txtCoordX.getText()+" WHERE idObs='"+idObs+"';");
        connect.executeUpdate("UPDATE Observation SET lieu_Lambert_Y="+this.txtCoordY.getText()+" WHERE idObs='"+idObs+"';");
         

        int lObservateur = 0;
        rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
        while (rs.next()) {
            lObservateur = rs.getInt("idObservateur");
        }

        connect.executeUpdate("UPDATE AObserve SET lobservateur="+lObservateur+" WHERE lobservation="+idObs+";");
        
        
        
        int idDerniereVege = 0;
        rs = connect.executeQuery("SELECT idVege FROM Vegetation ORDER BY idVege DESC LIMIT 1;");
        while (rs.next()) {
            idDerniereVege = rs.getInt("idVege");
        }

        connect.executeUpdate("UPDATE Vegetation SET natureVege='"+cbNatureVege.getValue()+"' WHERE idVege="+idDerniereVege+";");
        connect.executeUpdate("UPDATE Vegetation SET vegetation='"+this.txtVegetation.getText()+"' WHERE idVege="+idDerniereVege+";");
        


        rs = connect.executeQuery("SELECT zh_id FROM ZoneHumide ORDER BY zh_id DESC LIMIT 1;");
        int idDerniereZH = 0;
        while (rs.next()) {
            idDerniereZH = rs.getInt("zh_id");
        }
            
        connect.executeUpdate("UPDATE ZoneHumide SET zh_temporaire="+this.cbZHTemp.getValue()+" WHERE zh_id="+idDerniereZH+";");
        connect.executeUpdate("UPDATE ZoneHumide SET zh_profondeur="+this.txtProfondeur.getText()+" WHERE zh_id="+idDerniereZH+";");
        connect.executeUpdate("UPDATE ZoneHumide SET zh_surface="+this.txtSurface.getText()+" WHERE zh_id="+idDerniereZH+";");
        connect.executeUpdate("UPDATE ZoneHumide SET zh_typeMare='"+this.cbTypeMare.getValue()+"' WHERE zh_id="+idDerniereZH+";");
        connect.executeUpdate("UPDATE ZoneHumide SET zh_pente='"+this.cbPente.getValue()+"' WHERE zh_id="+idDerniereZH+";");
        connect.executeUpdate("UPDATE ZoneHumide SET zh_ouverture='"+this.cbOuverture.getValue()+"' WHERE zh_id="+idDerniereZH+";");

        connect.executeUpdate("UPDATE Obs_Batracien SET espece='"+cbEspece.getValue()+"' WHERE ObsB="+idObs+";");
        connect.executeUpdate("UPDATE Obs_Batracien SET nombreAdultes="+this.txtAdulte.getText()+" WHERE obsB="+idObs+";");

        connect.executeUpdate("UPDATE Obs_Batracien SET nombreAmplexus="+this.txtAmplexus.getText()+" WHERE obsB="+idObs+";");
        connect.executeUpdate("UPDATE Obs_Batracien SET nombrePonte="+this.txtPonte.getText()+" WHERE obsB="+idObs+";");
        connect.executeUpdate("UPDATE Obs_Batracien SET nombreTetard="+this.txtPonte.getText()+" WHERE obsB="+idObs+";");
        connect.executeUpdate("UPDATE Obs_Batracien SET temperature="+this.txtTemperature.getText()+" WHERE obsB="+idObs+";");
        connect.executeUpdate("UPDATE Obs_Batracien SET meteo_ciel='"+this.cbCiel.getValue()+"' WHERE obsB="+idObs+";");
        connect.executeUpdate("UPDATE Obs_Batracien SET meteo_temp='"+this.cbTemperature.getValue()+"' WHERE obsB="+idObs+";");
        connect.executeUpdate("UPDATE Obs_Batracien SET meteo_vent='"+this.cbVent.getValue()+"' WHERE obsB="+idObs+";");
        connect.executeUpdate("UPDATE Obs_Batracien SET meteo_pluie='"+this.cbPluie.getValue()+"' WHERE obsB="+idObs+";");
         

        initConfirmation("ModifierObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }

    /**
     * If the textfields are empty, the button is disabled. If the textfields are not empty, the button
     * is enabled.
     */
    private void checkDisable() {
        if(txtCoordY.getText() ==null ||txtCoordX.getText()==null || txtHeure.getText()==null || cbEspece.getValue()==null || txtAdulte.getText()==null || txtAmplexus.getText()==null || txtPonte.getText()==null ||
        txtTetard.getText()==null || txtTemperature.getText()==null || cbTemperature.getValue()==null || cbCiel.getValue()==null || cbPluie.getValue()==null || cbVent.getValue()==null || cbZHTemp.getValue()==null ||
        txtProfondeur.getText()==null || txtSurface.getText()==null || cbTypeMare.getValue()==null || cbPente.getValue()==null || cbOuverture.getValue()==null || cbNatureVege.getValue()==null || txtVegetation.getText()==null ||
            
        txtCoordY.getText().trim().isEmpty() || txtCoordX.getText().trim().isEmpty() || txtHeure.getText().trim().isEmpty() || cbEspece.getValue().trim().isEmpty() || txtAdulte.getText().trim().isEmpty() || txtAmplexus.getText().trim().isEmpty() || txtPonte.getText().trim().isEmpty() ||
        txtTetard.getText().trim().isEmpty() || txtTemperature.getText().trim().isEmpty() || cbTemperature.getValue().trim().isEmpty() || cbCiel.getValue().trim().isEmpty() || cbPluie.getValue().trim().isEmpty() || cbVent.getValue().trim().isEmpty() || cbZHTemp.getValue().trim().isEmpty() ||
        txtProfondeur.getText().trim().isEmpty() || txtSurface.getText().trim().isEmpty() || cbTypeMare.getValue().trim().isEmpty() || cbPente.getValue().trim().isEmpty() || cbOuverture.getValue().trim().isEmpty() || cbNatureVege.getValue().trim().isEmpty() || txtVegetation.getText().trim().isEmpty()){
            envoi.setDisable(true);
        } else {
            envoi.setDisable(false);
        }
    }

}
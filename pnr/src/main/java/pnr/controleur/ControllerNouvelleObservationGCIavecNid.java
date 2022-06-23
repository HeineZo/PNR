package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
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

public class ControllerNouvelleObservationGCIavecNid extends Controller implements Initializable{
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
    private MFXDatePicker txtDate;

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
    private MFXComboBox<String> cbNature;
    private ObservableList<String> nature  = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtNombre;

    @FXML
    private MFXComboBox<String> cbPresent;
    private ObservableList<String> present  = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtNomPlage;

    @FXML
    private MFXComboBox<String> cbRaisonArret;
    private ObservableList<String> raisonArret  = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtNbEnvol;

    @FXML
    private MFXComboBox<String> cbProtection;
    private ObservableList<String> protection  = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtBagueMale;

    @FXML
    private MFXTextField txtBagueFemelle;

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.nature.add("Oeuf");
        this.nature.add("Poussin");
        this.nature.add("Nid");
        this.cbNature.setItems(this.nature);

        this.present.add("0");
        this.present.add("1");
        this.cbPresent.setItems(this.present);

        this.raisonArret.add("Envol");
        this.raisonArret.add("Inconnu");
        this.raisonArret.add("Maree");
        this.raisonArret.add("Pietinement");
        this.raisonArret.add("Prédation");
        this.cbRaisonArret.setItems(this.raisonArret);

        this.protection.add("0");
        this.protection.add("1");
        this.cbProtection.setItems(this.protection);
    }

    @FXML
    private void handleBtnClick(ActionEvent event) throws SQLException {
        if (this.nameEspece.getText().equals("Modifier une observation")) {
            if (event.getSource() == btnBack) loadStage("../vue/DernierObservation.fxml", event);
            else if (event.getSource() == envoi) updateDonnees(event);

        } else {
            if (event.getSource() == btnBack) loadStage("../vue/ChoixAction.fxml", event);
            else if (event.getSource() == envoi) ajouteDonnees(event);
            else if (event.getSource() == supprimer){
                connect.executeUpdate("DELETE FROM Obs_GCI WHERE obsG ='"+idObs+"';");
                initConfirmation("SuppressionObservation");
                loadStage("../vue/Confirmation.fxml", event);
            }   
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
        connect.executeUpdate("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+laDate+"','"+this.txtHeure.getText()+"',"+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");
        rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
        int lObservateur = 0;
        while (rs.next()) {
            lObservateur = rs.getInt("idObservateur");
            
        }
        connect.executeUpdate("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");
        rs = connect.executeQuery("SELECT idNid FROM Nid_GCI ORDER BY idNid DESC LIMIT 1");
        int idDernierNid = 0;
        while (rs.next()) {
            idDernierNid = rs.getInt("idNid");
            
        }
        connect.executeUpdate("INSERT INTO Nid_GCI VALUES ("+(idDernierNid+1)+",'"+this.txtNomPlage.getText()+"','"+this.cbRaisonArret.getValue()+"',"+this.txtNbEnvol.getText()+","+this.cbProtection.getValue()+",'"+this.txtBagueMale.getText()+"','"+this.txtBagueFemelle.getText()+"');");
        connect.executeUpdate("INSERT INTO Obs_GCI VALUES ("+(idDerniereObs + 1)+",'"+this.cbNature.getValue()+"',"+this.txtNombre.getText()+","+this.cbPresent.getValue()+","+(idDernierNid+1)+");");  
        
        initConfirmation("AjouterObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }

    private void modifierObs() {
        this.nameEspece.setText("Modifier une observation");
        this.txtCoordX.setDisable(true);
        this.txtCoordY.setDisable(true);
        this.supprimer.setVisible(true);

        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_GCI LEFT JOIN Observation ON ObsG=idObs LEFT JOIN AObserve ON lobservation = idObs LEFT JOIN Observateur ON lobservateur = idObservateur WHERE obsG='"+idObs+"'LEFT JOIN Nid_GCI ON leNid = idNid ;");
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
                this.txtCoordY.setText("lieu_Lambert_Y");
                this.txtCoordX.setText("lieu_Lambert_X");
                this.txtNomPlage.setText(rs.getString("nomPlage"));
                this.cbRaisonArret.setText(rs.getString("raisonArretObservation"));
                this.cbRaisonArret.setValue(rs.getString("raisonArretObservation"));
                this.txtNbEnvol.setText(rs.getString("nbEnvol"));
                this.cbProtection.setText(rs.getString("protection"));
                this.cbProtection.setValue(rs.getString("protection"));
                this.txtBagueMale.setText(rs.getString("bagueMale"));
                this.txtBagueFemelle.setText(rs.getString("bagueFemelle"));
                this.cbNature.setText(rs.getString("nature"));
                this.cbNature.setValue(rs.getString("nature"));
                this.txtNombre.setText(rs.getString("nombre"));
                this.cbPresent.setText(rs.getString("presentMaisNonObs"));
                this.cbPresent.setValue(rs.getString("presentMaisNonObs"));
            }
            if (datePasFormate.equals("")) {
                laDate = date.formatToDate(datePasFormate); 
            }
            this.txtDate.setText(laDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
        

        if (this.cbNature.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_GCI SET nature='"+this.cbNature.getValue()+"' WHERE obsG='"+idObs+"';");
        } if (this.txtNombre.getText() != null) {
            connect.executeUpdate("UPDATE Obs_GCI SET nombre='"+this.txtNombre.getText()+"' WHERE obsG='"+idObs+"';");
        } if (this.cbPresent.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_GCI SET presentMaisNonObs='"+this.cbPresent.getValue()+"' WHERE obsG='"+idObs+"';");
        }  if (this.txtNomPlage.getText() != null) {
            connect.executeUpdate("UPDATE Nid_GCI SET nomPlage='"+this.txtNomPlage.getText()+"' WHERE leNid='"+idObs+"';");
        } if (this.cbRaisonArret.getValue() != null) {
            connect.executeUpdate("UPDATE Nid_GCI SET raisonArretObservation='"+this.cbRaisonArret.getValue()+"' WHERE leNid='"+idObs+"';");
        } if (this.txtNbEnvol.getText() != null) {
            connect.executeUpdate("UPDATE Nid_GCI SET nbEnvol='"+this.txtNbEnvol.getText()+"' WHERE leNid='"+idObs+"';");
        } if (this.cbProtection.getValue() != null) {
            connect.executeUpdate("UPDATE Nid_GCI SET protocole='"+this.cbProtection.getValue()+"' WHERE leNid='"+idObs+"';");
        } if (this.txtBagueMale.getText() != null) {
            connect.executeUpdate("UPDATE Nid_GCI SET bagueMale='"+this.txtBagueMale.getText()+"' WHERE leNid='"+idObs+"';");
        } if (this.txtBagueFemelle.getText() != null) {
            connect.executeUpdate("UPDATE Nid_GCI SET bagueFemelle='"+this.txtBagueFemelle.getText()+"' WHERE leNid='"+idObs+"';");
        }

        initConfirmation("ModifierObservation");
        loadStage("../vue/Confirmation.fxml", event);
        
    }
}

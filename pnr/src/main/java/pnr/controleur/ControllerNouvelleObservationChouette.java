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
import pnr.modele.util.Dates;

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
    private MFXDatePicker txtDate;

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

    Dates date = new Dates();

    @FXML
    private MFXTextField txtNumInd = new MFXTextField();

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
        this.cbObservateur.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.cbProto.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });      
        this.cbTypeObs.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtNumInd.textProperty().addListener((observable, oldValue, newValue) -> {
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

    private void modifierObs() {
        this.nameEspece.setText("Modifier une observation");
        this.txtCoordX.setDisable(true);
        this.txtCoordY.setDisable(true);
        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Chouette LEFT JOIN Chouette ON leNumIndividu=numIndividu LEFT JOIN Observation ON numObs=idObs LEFT JOIN AObserve ON lobservation = idObs LEFT JOIN Observateur ON lobservateur = idObservateur WHERE numObs='"+idObs+"';");
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
                this.cbSexe.setText(rs.getString("sexe"));
                this.cbProto.setText(rs.getString("protocole"));
                this.cbTypeObs.setText(rs.getString("typeObs"));
                this.txtNumInd.setText(rs.getString("numIndividu"));
            }
            String laDate = date.formatToDate(datePasFormate); 
            this.txtDate.setText(laDate);
        } catch (SQLException e) {
            e.printStackTrace();
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
        rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
        int lObservateur = 0;
        while (rs.next()) {
            lObservateur = rs.getInt("idObservateur");
            
        }
        connect.executeUpdate("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");
        connect.executeUpdate("INSERT INTO Chouette VALUES ('"+this.txtNumInd.getText()+"','"+this.cbEspece.getValue()+"','"+this.cbSexe.getValue()+"');");
        connect.executeUpdate("INSERT INTO Obs_Chouette VALUES ("+this.cbProto.getValue()+",'"+this.cbTypeObs.getValue()+"','"+this.txtNumInd.getText()+"',"+(idDerniereObs + 1)+");");
        initConfirmation("AjouterObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }

    private void updateDonnees(ActionEvent event) throws SQLException{
        String laDate = date.dateToFormat(this.txtDate.getText()); 
        if (laDate != null){
            connect.executeUpdate("UPDATE Observation SET dateObs='"+laDate+"' WHERE idObs='"+idObs+"';");
        } if (this.txtHeure.getText() != null){
            connect.executeUpdate("UPDATE Observation SET heureObs='"+this.txtHeure.getText()+"' WHERE idObs='"+idObs+"';");
        } if (this.txtCoordX.getText() != null){
            connect.executeUpdate("UPDATE Observation SET lieu_Lambert_X="+this.txtCoordX.getText()+" WHERE idObs='"+idObs+"';");
        } if (this.txtCoordY.getText() != null){
            connect.executeUpdate("UPDATE Observation SET lieu_Lambert_Y="+this.txtCoordY.getText()+" WHERE idObs='"+idObs+"';");
        } 

        int lObservateur = 0;
        if (this.cbObservateur.getValue() != null){
            ResultSet rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
            while (rs.next()) {
                lObservateur = rs.getInt("idObservateur");
            }
        } if (lObservateur != 0){
            connect.executeUpdate("UPDATE AObserve SET lobservateur="+lObservateur+" WHERE lobservation="+idObs+";");
        }

        if (this.cbProto.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_Chouette SET protocole='"+this.cbProto.getValue()+"' WHERE numObs='"+idObs+"';"); 
        } if (this.cbTypeObs.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_Chouette SET typeObs='"+this.cbTypeObs.getValue()+"' WHERE numObs='"+idObs+"';");
        } if (this.txtNumInd.getText() != null) {
            connect.executeUpdate("UPDATE Obs_Chouette SET leNumIndividu='"+this.txtNumInd.getText()+"' WHERE numObs='"+idObs+"';");
        } if (this.txtNumInd.getText() != null) {
            connect.executeUpdate("UPDATE Chouette SET numIndividu='"+this.txtNumInd.getText()+"' WHERE numIndividu='"+txtNumInd+"';");
        } if (this.cbEspece.getValue() != null) {
            connect.executeUpdate("UPDATE Chouette SET espece='"+this.cbEspece.getValue()+"' WHERE numIndividu='"+txtNumInd+"';");
        } if (this.cbSexe.getValue() != null) {
            connect.executeUpdate("UPDATE Chouette SET sexe='"+this.cbSexe.getValue()+"' WHERE numIndividu='"+txtNumInd+"';");
        }

        initConfirmation("ModifierObservation");
        loadStage("../vue/Confirmation.fxml", event);

    }

    private void checkDisable() {
        // if (idObs!=null) {
        //     if(!txtHeure.getText().isEmpty() && !txtCoordY.getText().isEmpty() && !txtNumInd.getText().isEmpty() && !txtCoordX.getText().isEmpty()) {
        //         envoi.setDisable(true);
        //     } else {
        //         envoi.setDisable(false);
        //     }
        // } else {
        //     if(!txtHeure.getText().isEmpty() && !txtCoordY.getText().isEmpty() && !txtNumInd.getText().isEmpty() && !txtCoordX.getText().isEmpty()
        //     && cbTypeObs.getValue()!= null && cbSexe.getValue() != null && cbEspece.getValue() != null && cbProto.getValue() != null){
        //         envoi.setDisable(true);
        //     } else {
        //         envoi.setDisable(false);
        //     }
        // }
    }
}
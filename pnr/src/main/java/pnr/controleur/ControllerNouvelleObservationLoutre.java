package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import pnr.modele.donnee.Observateur;
import pnr.modele.donneeAddsOn.TabObservateur;
import pnr.modele.util.Dates;

public class ControllerNouvelleObservationLoutre extends Controller implements Initializable{

    @FXML
    private AnchorPane anchorPane;
    
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
    private MFXTextField txtCommune = new MFXTextField();

    @FXML
    private MFXTextField txtLieuDit = new MFXTextField();

    private int idObs;

    @FXML
    private MFXComboBox<String> cbIndice;
    private ObservableList<String> indice  = FXCollections.observableArrayList();

    private Dates date = new Dates();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (getUserClicked() != null) {
            idObs = Integer.valueOf(getUserClicked());
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

        this.indice.add("Positif");
        this.indice.add("Negatif");
        this.indice.add("Non prospection");
        this.cbIndice.setItems(this.indice);

        this.txtHeure.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtCoordY.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtCoordX.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtCommune.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });        
        this.txtLieuDit.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });       
        this.txtDate.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.cbIndice.valueProperty().addListener((observable, oldValue, newValue) -> {
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
        // ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Loutre JOIN Observation ON ObsL=idObs JOIN AObserve ON ObsL=lObservation WHERE ObsL = " + idObs + ";");
        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Loutre LEFT JOIN Observation ON ObsL=idObs LEFT JOIN AObserve ON lobservation = idObs LEFT JOIN Observateur ON lobservateur = idObservateur WHERE obsL="+idObs+";");
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
                this.txtCommune.setText(rs.getString("commune"));
                this.txtLieuDit.setText(rs.getString("lieuDit"));
                this.cbIndice.setText(rs.getString("indice"));
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
        // System.out.println("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+this.txtDate.getText()+"','"+this.txtHeure.getText()+"','"+this.txtCoordX.getText()+"','"+this.txtCoordY.getText()+"');");
        rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
        int lObservateur = 0;
        while (rs.next()) {
            lObservateur = rs.getInt("idObservateur");
            
        }
        connect.executeUpdate("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");
        // System.out.println("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");
        
        // System.out.println("INSERT INTO Lieu VALUES ("+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");
        connect.executeUpdate("INSERT INTO Obs_Loutre VALUES ("+(idDerniereObs + 1)+",'"+this.txtCommune.getText()+"','"+this.txtLieuDit.getText()+"','"+this.cbIndice.getValue()+"');");  
        // System.out.println("INSERT INTO Obs_Loutre VALUES ("+(idDerniereObs + 1)+",'"+this.txtCommune.getText()+"','"+this.txtLieuDit.getText()+"','"+this.cbIndice.getValue()+"');");

        initConfirmation("AjouterObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }


    private void updateDonnees(ActionEvent event) throws SQLException{
        String laDate = date.dateToFormat(this.txtDate.getText()); 
        connect.executeUpdate("UPDATE Observation SET dateObs='"+laDate+"', heureObs=null, lieu_Lambert_X="+this.txtCoordX.getText()+", lieu_Lambert_Y="+this.txtCoordY.getText()+" WHERE idObs="+idObs+";");
        ResultSet rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
        int lObservateur = 0;
        while (rs.next()) {
            lObservateur = rs.getInt("idObservateur");
        }
        connect.executeUpdate("UPDATE AObserve SET lObservateur="+lObservateur+" WHERE lObservation="+idObs+";");
        connect.executeUpdate("UPDATE Obs_Loutre SET commune='"+this.txtCommune.getText()+"', lieuDit='"+this.txtLieuDit.getText()+"', indice='"+this.cbIndice.getValue()+"' WHERE ObsL="+idObs+";");  
        initConfirmation("ModifierObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }
    
    private void checkDisable() {
        if(!txtCoordY.getText().isEmpty() && !txtCoordX.getText().isEmpty() && !txtCommune.getText().isEmpty() && !txtLieuDit.getText().isEmpty() 
        && !txtDate.getText().isEmpty() && cbIndice.getValue() != null ) {
            envoi.setDisable(false);
        } else {
            envoi.setDisable(true);
        }
    }
}
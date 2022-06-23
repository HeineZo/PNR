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

/**
 * Manages the NouvelleObservationGCIsansNid page
 */
public class ControllerNouvelleObservationGCIsansNid extends Controller implements Initializable{
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
    private MFXComboBox<String> cbNid;
    private ObservableList<String> leNid  = FXCollections.observableArrayList();

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

        rs = connect.executeQuery("SELECT idNid,nomPlage FROM Nid_GCI ORDER BY nomPlage");
        try {
            while (rs.next()) {
                String lesNids = rs.getString("nomPlage") +" "+ rs.getString("idNid");
                this.leNid.add(lesNids);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.cbNid.setItems(this.leNid);

        this.txtDate.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtHeure.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtCoordY.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtCoordX.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });        
        this.txtNombre.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });       
        this.txtDate.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.cbObservateur.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });        
        this.cbNature.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.cbPresent.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.cbNid.valueProperty().addListener((observable, oldValue, newValue) -> {
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
                connect.executeUpdate("DELETE FROM Obs_GCI WHERE obsG ='"+idObs+"';");
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
        connect.executeUpdate("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+laDate+"','"+this.txtHeure.getText()+"',"+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");
        rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
        int lObservateur = 0;
        while (rs.next()) {
            lObservateur = rs.getInt("idObservateur");
            
        }
        connect.executeUpdate("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");

        String[] split= this.cbNid.getValue().split(" ");
        int idDuNid = Integer.parseInt(split[1]);
        connect.executeUpdate("INSERT INTO Obs_GCI VALUES ("+(idDerniereObs + 1)+",'"+this.cbNature.getValue()+"',"+this.txtNombre.getText()+","+this.cbPresent.getValue()+","+idDuNid+");");  
        
        initNidGCI("sansNid");
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
        // ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Loutre JOIN Observation ON ObsL=idObs JOIN AObserve ON ObsL=lObservation WHERE ObsL = " + idObs + ";");
        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_GCI LEFT JOIN Observation ON ObsG=idObs LEFT JOIN AObserve ON lobservation = idObs LEFT JOIN Observateur ON lobservateur = idObservateur WHERE obsG='"+idObs+"';");
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
                this.cbNature.setText(rs.getString("nature"));
                this.cbNature.setValue(rs.getString("nature"));
                this.txtNombre.setText(rs.getString("nombre"));
                this.cbPresent.setText(rs.getString("presentMaisNonObs"));
                this.cbPresent.setValue(rs.getString("presentMaisNonObs"));
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

        if (this.cbNature.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_GCI SET nature='"+this.cbNature.getValue()+"' WHERE obsG='"+idObs+"';");
        } if (this.txtNombre.getText() != null) {
            connect.executeUpdate("UPDATE Obs_GCI SET nombre='"+this.txtNombre.getText()+"' WHERE obsG='"+idObs+"';");
        } if (this.cbPresent.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_GCI SET presentMaisNonObs='"+this.cbPresent.getValue()+"' WHERE obsG='"+idObs+"';");
        } 

        initConfirmation("ModifierObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }
    
    private void checkDisable() {
        if(txtCoordY.getText() == null || txtCoordX.getText() == null || txtHeure.getText() == null || txtNombre.getText() == null 
        || txtDate.getText() == null || cbObservateur.getValue() == null || cbNature.getValue() == null || cbPresent.getValue() == null || cbNid.getValue() == null || 
            txtCoordY.getText().trim().isEmpty() || txtCoordX.getText().trim().isEmpty() || txtHeure.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() 
        || txtDate.getText().trim().isEmpty() || cbObservateur.getValue().trim().isEmpty() || cbNature.getValue().trim().isEmpty() || cbPresent.getValue().trim().isEmpty() || cbNid.getValue().trim().isEmpty()){
            envoi.setDisable(true);
        } else {
            envoi.setDisable(false);
        }
    }
}

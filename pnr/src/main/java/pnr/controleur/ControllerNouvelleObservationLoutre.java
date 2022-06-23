package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import pnr.modele.util.Dates;

/**
 * Manages the NouvelleObservationLoutre page
 */
public class ControllerNouvelleObservationLoutre extends Controller implements Initializable{

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Button btnBack;

    @FXML
    private MFXButton supprimer;

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

    private String idObs;

    @FXML
    private MFXComboBox<String> cbIndice;
    private ObservableList<String> indice  = FXCollections.observableArrayList();

    private Dates date = new Dates();

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
                connect.executeUpdate("DELETE FROM Obs_Loutre WHERE obsL ='"+idObs+"';");
                initConfirmation("SuppressionObservation");
                loadStage("../vue/Confirmation.fxml", event);
            } 

        } else {
            if (event.getSource() == btnBack) loadStage("../vue/ChoixAction.fxml", event);
            else if (event.getSource() == envoi) ajouteDonnees(event);
        }
    }

    /**
     * It gets the data from the database and puts it in the textfields
     */
    private void modifierObs() {
        this.nameEspece.setText("Modifier une observation");
        this.txtCoordX.setDisable(true);
        this.txtCoordY.setDisable(true);
        this.supprimer.setVisible(true);
        
        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Loutre LEFT JOIN Observation ON ObsL=idObs LEFT JOIN AObserve ON lobservation = idObs LEFT JOIN Observateur ON lobservateur = idObservateur WHERE obsL='"+idObs+"';");
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
                this.txtCommune.setText(rs.getString("commune"));
                this.txtLieuDit.setText(rs.getString("lieuDit"));
                this.cbIndice.setText(rs.getString("indice"));
                this.cbIndice.setValue(rs.getString("indice"));
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
        connect.executeUpdate("INSERT INTO Obs_Loutre VALUES ("+(idDerniereObs + 1)+",'"+this.txtCommune.getText()+"','"+this.txtLieuDit.getText()+"','"+this.cbIndice.getValue()+"');");  
        initConfirmation("AjouterObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }


    /**
     * It updates the database with the new values of the fields
     * 
     * @param event the event that triggered the method
     */
    private void updateDonnees(ActionEvent event) throws SQLException{
        String laDate = date.dateToFormat(this.txtDate.getText()); 
        connect.executeUpdate("UPDATE Observation SET dateObs='"+laDate+"', heureObs='"+this.txtHeure.getText()+"', lieu_Lambert_X="+this.txtCoordX.getText()+", lieu_Lambert_Y="+this.txtCoordY.getText()+" WHERE idObs='"+idObs+"';");
        
        ResultSet rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
        int lObservateur = 0;
        while (rs.next()) {
            lObservateur = rs.getInt("idObservateur");
        }
        connect.executeUpdate("UPDATE AObserve SET lObservateur="+lObservateur+" WHERE lObservation='"+idObs+"';");
        connect.executeUpdate("UPDATE Obs_Loutre SET commune='"+this.txtCommune.getText()+"', lieuDit='"+this.txtLieuDit.getText()+"', indice='"+this.cbIndice.getValue()+"' WHERE ObsL='"+idObs+"';");  
        initConfirmation("ModifierObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }
    
    /**
     * If the textfields are empty, the button is disabled. If the textfields are not empty, the button
     * is enabled.
     */
    private void checkDisable() {
        if(txtCoordY.getText() == null || txtCoordX.getText() == null || txtCommune.getText() == null || txtLieuDit.getText() == null 
        || txtDate.getText() == null || cbIndice.getValue() == null ||
            txtCoordY.getText().trim().isEmpty() || txtCoordX.getText().trim().isEmpty() || txtCommune.getText().trim().isEmpty() || txtLieuDit.getText().trim().isEmpty() 
        || txtDate.getText().trim().isEmpty() || cbIndice.getValue().trim().isEmpty()){
            envoi.setDisable(true);
        } else {
            envoi.setDisable(false);
        }
    }
}
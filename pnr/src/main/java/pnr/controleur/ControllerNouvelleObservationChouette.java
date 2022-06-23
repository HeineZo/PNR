package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import pnr.modele.util.Dates;

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

/**
 * Manages the NouvelleObservationChouette page
 */
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
    private MFXButton supprimer;

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
            System.out.println(idObs);
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
                connect.executeUpdate("DELETE FROM Obs_Chouette WHERE numObs ="+Integer.valueOf(idObs)+";");
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
        this.txtNumInd.setDisable(true);
        this.supprimer.setVisible(true);

        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Chouette LEFT JOIN Chouette ON leNumIndividu=numIndividu LEFT JOIN Observation ON numObs=idObs LEFT JOIN AObserve ON lobservation = idObs LEFT JOIN Observateur ON lobservateur = idObservateur WHERE numObs='"+idObs+"';");
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
                this.cbSexe.setText(rs.getString("sexe"));
                this.cbSexe.setValue(rs.getString("sexe"));
                this.cbProto.setText(rs.getString("protocole"));
                this.cbProto.setValue(rs.getString("protocole"));
                this.cbTypeObs.setText(rs.getString("typeObs"));
                this.cbTypeObs.setValue(rs.getString("typeObs"));
                this.txtNumInd.setText(rs.getString("numIndividu"));
            }
            if (datePasFormate != null) {
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
        connect.executeUpdate("INSERT INTO Chouette VALUES ('"+this.txtNumInd.getText()+"','"+this.cbEspece.getValue()+"','"+this.cbSexe.getValue()+"');");
        connect.executeUpdate("INSERT INTO Obs_Chouette VALUES ("+this.cbProto.getValue()+",'"+this.cbTypeObs.getValue()+"','"+this.txtNumInd.getText()+"',"+(idDerniereObs + 1)+");");
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
        
        String numInd ="";
        rs = connect.executeQuery("SELECT numIndividu FROM Chouette JOIN Obs_Chouette ON numIndividu= leNumIndividu WHERE numObs='"+idObs+"';");
        while (rs.next()) {
            numInd = rs.getString("numIndividu");
        }
        if (this.cbProto.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_Chouette SET protocole='"+this.cbProto.getValue()+"' WHERE numObs='"+idObs+"';"); 
        } if (this.cbTypeObs.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_Chouette SET typeObs='"+this.cbTypeObs.getValue()+"' WHERE numObs='"+idObs+"';");
        } if (this.cbEspece.getValue() != null) {
            connect.executeUpdate("UPDATE Chouette SET espece='"+this.cbEspece.getValue()+"' WHERE numIndividu='"+numInd+"';");
        } if (this.cbSexe.getValue() != null) {
            connect.executeUpdate("UPDATE Chouette SET sexe='"+this.cbSexe.getValue()+"' WHERE numIndividu='"+numInd+"';");
        }


        initConfirmation("ModifierObservation");
        loadStage("../vue/Confirmation.fxml", event);

    }

    /**
     * If the textfields are empty, the button is disabled. If the textfields are not empty, the button
     * is enabled.
     */
    private void checkDisable() {
        if(txtHeure.getText()==null || txtCoordY.getText()==null || txtNumInd.getText()==null || txtCoordX.getText()==null
        || cbTypeObs.getValue()==null || cbSexe.getValue()==null || cbEspece.getValue()==null || cbProto.getValue()==null ||

            txtHeure.getText().trim().isEmpty() || txtCoordY.getText().trim().isEmpty() || txtNumInd.getText().trim().isEmpty() || txtCoordX.getText().trim().isEmpty()
        || cbTypeObs.getValue().trim().isEmpty() || cbSexe.getValue().trim().isEmpty() || cbEspece.getValue().trim().isEmpty() || cbProto.getValue().trim().isEmpty()){
            envoi.setDisable(true);
        } else {
            envoi.setDisable(false);
        }
    }
}
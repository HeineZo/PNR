package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import pnr.modele.util.Dates;

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
    private MFXComboBox<String> cbSexe = new MFXComboBox<>();
    private ObservableList<String> sexe = FXCollections.observableArrayList();

    @FXML
    private MFXComboBox<String> cbTypePeche = new MFXComboBox<>();
    private ObservableList<String> typePeche = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtTemperature = new MFXTextField();

    @FXML
    private MFXComboBox<String> cbGestant = new MFXComboBox<String>();
    private ObservableList<String> gestant = FXCollections.observableArrayList();
    

    @FXML
    private MFXTextField txtTaille = new MFXTextField();

    private String idObs;
    ArrayList<String> hippo;

    private Dates date = new Dates();

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
        this.cbObservateur.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtTaille.textProperty().addListener((observable, oldValue, newValue) -> {
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
        rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
        int lObservateur = 0;
        while (rs.next()) {
            lObservateur = rs.getInt("idObservateur");
            
        }
        connect.executeUpdate("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");

        int estGestant = 0;
        if(this.cbGestant.getValue().equals("oui")){
            estGestant = 1;
        }
        connect.executeUpdate("INSERT INTO Obs_Hippocampe VALUES ("+(idDerniereObs + 1)+",'"+this.cbEspece.getValue()+"','"+this.cbSexe.getValue()+"',"+this.txtTemperature.getText()+",'"+this.cbTypePeche.getValue()+"',"+this.txtTaille.getText()+","+estGestant+");");  
        
        initConfirmation("AjouterObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }

    private void modifierObs() {
        this.nameEspece.setText("Modifier une observation");
        this.txtCoordX.setDisable(true);
        this.txtCoordY.setDisable(true);
        
        // ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Hippocampe LEFT JOIN Observation ON ObsH=idObs LEFT JOIN AObserve ON lobservateur = idObs LEFT JOIN Observateur ON lobservateur = idObservateur WHERE obsH="+idObs+";");
        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Hippocampe LEFT JOIN Observation ON ObsH=idObs LEFT JOIN AObserve ON lobservation = idObs LEFT JOIN Observateur ON lobservateur = idObservateur WHERE obsH='"+idObs+"';");
        try {
            hippo = new ArrayList<String>();
            while (rs.next()) {
                hippo.add(rs.getString("dateObs")); //0
                hippo.add(rs.getString("nom")); //1
                hippo.add(rs.getString("prenom")); //2
                hippo.add(rs.getString("dateObs")); //3
                hippo.add(rs.getString("heureObs"));  //4
                hippo.add(rs.getString("lieu_Lambert_Y")); //5
                hippo.add(rs.getString("lieu_Lambert_X")); //6
                hippo.add(rs.getString("espece")); //7
                hippo.add(rs.getString("sexe")); //8
                hippo.add(rs.getString("typePeche")); //9
                hippo.add(rs.getString("temperatureEau")); //10
                hippo.add(rs.getString("taille")); //11
                hippo.add(rs.getString("gestant")); //12

            }
            String laDate = date.formatToDate(hippo.get(0)); 
            this.txtDate.setText(laDate);
            this.txtHeure.setText(hippo.get(4));
            this.txtCoordY.setText(hippo.get(5));
            this.txtCoordX.setText(hippo.get(6));
            this.cbEspece.setText(hippo.get(7));
            this.cbSexe.setText(hippo.get(8));
            this.cbTypePeche.setText(hippo.get(9));
            this.txtTemperature.setText(hippo.get(10));
            this.txtTaille.setText(hippo.get(11));

            if (hippo.get(1) != null){
                this.cbObservateur.setText(hippo.get(1));
            } else if (hippo.get(2) != null){
                this.cbObservateur.setText(hippo.get(2));
            }

            if(hippo.get(12).equals("0")){
                this.cbGestant.setText("non");
            } else {
                this.cbGestant.setText("oui");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        int estGestant = 0;
        if(this.cbGestant.getValue() != null && this.cbGestant.getValue().equals("oui")){
            estGestant = 1;
        }

        if (this.cbEspece.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_Hippocampe SET espece='"+this.cbEspece.getValue()+"' WHERE obsH='"+idObs+"';");
        } if (this.cbSexe.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_Hippocampe SET sexe='"+this.cbSexe.getValue()+"' WHERE obsH='"+idObs+"';");
        } if (this.txtTemperature.getText() != null) {
            connect.executeUpdate("UPDATE Obs_Hippocampe SET temperatureEau="+this.txtTemperature.getText()+" WHERE obsH='"+idObs+"';");
        } if (this.txtTaille.getText() != null) {
            connect.executeUpdate("UPDATE Obs_Hippocampe SET taille="+this.txtTaille.getText()+" WHERE obsH='"+idObs+"';");
        } if (this.cbTypePeche.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_Hippocampe SET typePeche='"+this.cbTypePeche.getValue()+"' WHERE obsH='"+idObs+"';");
        } if (cbGestant.getValue() != null) {
            connect.executeUpdate("UPDATE Obs_Hippocampe SET gestant="+estGestant+" WHERE obsH='"+idObs+"';");
        }

        
        initConfirmation("ModifierObservation");
        loadStage("../vue/Confirmation.fxml", event);
    }



    private void checkDisable() {
        if (idObs!=null) {
            if(txtCoordY.getText().isEmpty() || txtTaille.getText().isEmpty() || txtCoordX.getText().isEmpty() || txtTemperature.getText().isEmpty() || txtDate.getText().isEmpty()) {
                envoi.setDisable(true);
            } else {
                envoi.setDisable(false);
            }
        } else {
            if(txtCoordY.getText().isEmpty() || txtTaille.getText().isEmpty() || txtCoordX.getText().isEmpty() || txtTemperature.getText().isEmpty() || txtDate.getText().isEmpty()
            || cbEspece.getValue() == null|| cbSexe.getValue()==null || cbTypePeche.getValue()==null || cbObservateur.getValue()==null || cbGestant.getValue()==null){
                envoi.setDisable(true);
            } else {
                envoi.setDisable(false);
            }
        }
        
    }
}
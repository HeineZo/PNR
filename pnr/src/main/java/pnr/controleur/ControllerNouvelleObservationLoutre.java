package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
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
    private ComboBox<String> cbObservateur;
    private ObservableList<String> observateur = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtHeure;

    @FXML
    private MFXTextField txtCoordY;

    @FXML
    private MFXTextField txtCoordX;

    @FXML
    private MFXTextField txtCommune;

    @FXML
    private MFXTextField txtLieuDit;

    @FXML
    private ComboBox<String> cbProto;

    private String eventSrc;

    @FXML
    private ComboBox<String> cbIndice;
    private ObservableList<String> indice  = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modifierObs();

        ResultSet rs = connect.executeQuery("SELECT nom,prenom FROM Observateur ORDER BY nom,prenom;");

        try {
            while (rs.next()) {
                if(rs.getString("nom") != null){
                   this.observateur.add(rs.getString("nom")); 
                } else {
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
    }

    @FXML
    private void handleBtnClick(
    ActionEvent event) throws SQLException {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        } else if (event.getSource() == envoi) {
            ajouteDonnees(event);
        }
    }

    private void modifierObs() {
        this.eventSrc = getUserClicked();
        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Loutre JOIN Observation ON ObsL=idObs WHERE ObsL = '" + this.eventSrc + "';");
        try {
            while (rs.next()) {
                // this.datePicker.setText(rs.getString("dateObs"));
                // this.cbObservateur.setValue(rs.getString("observateur"));
                this.txtHeure.setText(rs.getString("heureObs"));
                this.txtCoordY.setText(rs.getString("lieu_Lambert_Y"));
                this.txtCoordX.setText(rs.getString("lieu_Lambert_X"));
                this.txtCommune.setText(rs.getString("commune"));
                this.txtLieuDit.setText(rs.getString("lieuDit"));
                this.cbIndice.setValue(rs.getString("indice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void ajouteDonnees(ActionEvent event) throws SQLException{
        if ((!(this.txtDate.getText().equals(""))) && (!(this.cbObservateur.getValue().equals("Observateur"))) && (!(this.txtHeure.getText().equals(""))) && 
        (!(this.txtCoordX.getText().equals(""))) && (!(this.txtCoordY.getText().equals(""))) && (!(this.txtCommune.getText().equals(""))) && 
        (!(this.txtLieuDit.getText().equals("")))&& (!(this.cbIndice.getValue().equals("Indice")))){
            ResultSet rs = connect.executeQuery("SELECT idObs FROM Observation ORDER BY idObs DESC LIMIT 1;");
            int idDerniereObs = 0;
            while (rs.next()) {
                idDerniereObs = rs.getInt("idObs");
            }
            connect.executeUpdate("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+this.txtDate.getText()+"',null,'"+this.txtCoordX.getText()+"','"+this.txtCoordY.getText()+"');");
            System.out.println("INSERT INTO Observation VALUES ("+(idDerniereObs + 1)+",'"+this.txtDate.getText()+"','"+this.txtHeure.getText()+"','"+this.txtCoordX.getText()+"','"+this.txtCoordY.getText()+"');");
            rs = connect.executeQuery("SELECT idObservateur FROM Observateur WHERE nom='"+this.cbObservateur.getValue()+"' OR prenom ='"+this.cbObservateur.getValue()+"';");
            int lObservateur = 0;
            while (rs.next()) {
                lObservateur = rs.getInt("idObservateur");
                
            }
            connect.executeUpdate("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");
            System.out.println("INSERT INTO AObserve VALUES ("+lObservateur+","+(idDerniereObs + 1)+");");
            connect.executeUpdate("INSERT INTO Lieu VALUES ("+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");  
            System.out.println("INSERT INTO Lieu VALUES ("+this.txtCoordX.getText()+","+this.txtCoordY.getText()+");");
            connect.executeUpdate("INSERT INTO Obs_Loutre VALUES ("+(idDerniereObs + 1)+",'"+this.txtCommune.getText()+"','"+this.txtLieuDit.getText()+"','"+this.cbIndice.getValue()+"');");  
            System.out.println("INSERT INTO Obs_Loutre VALUES ("+(idDerniereObs + 1)+",'"+this.txtCommune.getText()+"','"+this.txtLieuDit.getText()+"','"+this.cbIndice.getValue()+"');");

            initConfirmation("AjouterObservation");
            loadStage("../vue/Confirmation.fxml", event);
        } else {
            super.error("Veuillez remplir tous les champs",anchorPane);
        }

    }
}

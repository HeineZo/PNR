package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ControllerModifierUnProfil extends Controller implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private Button envoi;

    @FXML
    private Button supprimer;

    @FXML
    private ComboBox<String> credentials;

    @FXML
    private TextField prenom = new TextField();

    @FXML
    private TextField password = new TextField();

    @FXML
    private TextField pseudonyme = new TextField();

    private String eventSrc;

    private ObservableList<String> permissionChoices = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.permissionChoices.add("Utilisateur");
        this.permissionChoices.add("Administrateur");
        this.credentials.setItems(this.permissionChoices);
        
        this.eventSrc = this.getEventSrcNomUser();
        ResultSet rs = connect.executeQuery("SELECT * FROM Utilisateur"); 
        try {
            while (rs.next()) {
                if (rs.getString("nom").equals(this.eventSrc)) {
                    this.pseudonyme.setText(rs.getString("nom"));
                    this.prenom.setText(rs.getString("prenom"));
                    this.password.setText(rs.getString("mdpUtilisateur"));
                    if (rs.getString("permission").equals("0")){
                        this.credentials.setValue("Utilisateur");
                    } else {
                        this.credentials.setValue("Administrateur");
                    }
                }
                // System.out.println(name.setText("")); 
                // name.setText(rs.getString("nom"));//rs.getString("nom"));
                // System.out.println(rs.getString("nom"));
                // System.out.println(rs.getString("prenom"));
                // System.out.println(rs.getString("mdpUtilisateur"));
                // System.out.println(rs.getString("permission"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/GererProfils.fxml", event);
        } else if (event.getSource() == supprimer){
            initConfirmation("SuppressionProfil");
            loadStage("../vue/Confirmation.fxml", event);
        } else if (event.getSource() == envoi){
            initConfirmation("ModifierProfil");
            loadStage("../vue/Confirmation.fxml", event);
        }
    }


}

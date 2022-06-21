package pnr.controleur;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.palexdev.materialfx.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import pnr.modele.EncryptString;

public class ControllerLandingPage extends Controller {

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Button send;

    @FXML
    private MFXPasswordField password = new MFXPasswordField();

    @FXML
    private MFXTextField username = new MFXTextField();


    @FXML
    void submitForm(ActionEvent event) throws SQLException {
        
        boolean found = false;
        
        ResultSet rs = connect.executeQuery("SELECT pseudonyme, mdpUtilisateur, permission FROM Utilisateur");
        
        while (rs.next() && !found) {
            EncryptString cryptMdp = new EncryptString(username.getText());
            if(rs.getString("pseudonyme").equals(username.getText()) && rs.getString("mdpUtilisateur").equals(cryptMdp.getEncryptedPassword())) {
                found = true;
                if (rs.getString("permission").equals("0")) {
                    initNomUser(username.getText());
                    initPermission("0");
                    loadStage("../vue/ChoixEspeces.fxml", event);
                } else if (rs.getString("permission").equals("1")) {
                    initNomUser(username.getText());
                    initPermission("1");
                    loadStage("../vue/ChoixActionAdmin.fxml", event);
                } 
            } 
            
        }

        if (!found){
            super.error("Utilisateur et/ou mot de passe incorrect", anchorPane);
        } 
    }
}

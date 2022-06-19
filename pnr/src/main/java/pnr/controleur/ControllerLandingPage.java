package pnr.controleur;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.enums.NotificationPos;
import io.github.palexdev.materialfx.notifications.MFXNotificationCenterSystem;
import io.github.palexdev.materialfx.notifications.MFXNotificationSystem;
import io.github.palexdev.materialfx.notifications.base.INotification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


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
        
        ResultSet rs = connect.executeQuery("SELECT nom, mdpUtilisateur, permission FROM Utilisateur");

        // ResultSetMetaData rsmd = rs.getMetaData();
        // int columnCount = rsmd.getColumnCount();

        // The column count starts from 1
        // for (int i = 1; i <= columnCount; i++ ) {
        //     String name = rsmd.getColumnName(i);
        //     System.out.println(name);
        // }
        
        while (rs.next() && !found) {
            if(rs.getString("nom").equals(username.getText()) && rs.getString("mdpUtilisateur").equals(password.getText())) {
                found = true;
                if (rs.getString("permission").equals("0")) {
                    loadStage("../vue/ChoixEspeces.fxml", event);
                } else if (rs.getString("permission").equals("1")) {
                    loadStage("../vue/ChoixActionAdmin.fxml", event);
                } 
            } 
            
        }

        if (!found){
            super.error("Utilisateur et/ou mot de passe incorrect", anchorPane);
        } 
    }
    
	

        // if (!user) {
        //     System.err.println("Invalid credentials, please check username");
        // } else {
        //     if (credentials[0].equals(password.getText())) {
        //         pwd = true;
        //     }
        // }

        // if (!pwd) {
        //     System.err.println("Invalid credentials, please check password");
        // } else {
        //     if (credentials[1].equals(String.valueOf(0))) {
        //         loadStage("../vue/ChoixAction.fxml", event);
        //     } else {
        //         loadStage("../vue/ChoixActionAdmin.fxml", event);
        //     }
        // }
    

}

package pnr.controleur;

import java.util.logging.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import pnr.modele.WwdEmbedded;

public class ControllerLandingPage extends Controller {

    @FXML
    private Button send;

    @FXML
    private final MFXTextField username = new MFXTextField("");

    @FXML
    private final MFXPasswordField password = new MFXPasswordField("");

    private String[] credentials = new String[0];

    @FXML
    void submitForm(ActionEvent event) throws SQLException {
        boolean user = false;
        boolean pwd = false;
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_pnr", "pnr", "mdp_pnr");
        ResultSet rs = c.createStatement()
                .executeQuery("SELECT nom, mdpUtilisateur, permission FROM Utilisateur ");

        while (rs.next() && !user) {
            if (rs.getString("nom").equals(password.getText())) {
                credentials[0] = rs.getString("mdpUtilisateur");
                credentials[1] = rs.getString("permission");
                user = true;
            }
        }

        if (!user) {
            System.err.println("Invalid credentials, please check username");
        } else {
            if (credentials[0].equals(password.getText())) {
                pwd = true;
            }
        }

        if (!pwd) {
            System.err.println("Invalid credentials, please check password");
        } else {
            if (credentials[1].equals(String.valueOf(0))) {
                loadStage("../vue/ChoixAction.fxml", event);
            } else {
                loadStage("../vue/ChoixActionAdmin.fxml", event);
            }
        }
    }

}

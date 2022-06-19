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

import javafx.event.ActionEvent;

public class ControllerModifierUnProfil extends Controller implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> credentials;

    @FXML
    private TextField name = new TextField();

    @FXML
    private TextField password = new TextField();

    @FXML
    private TextField surname = new TextField();

    private String eventSrc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.eventSrc = this.getEventSrcVisualiser();
        ResultSet rs = connect.executeQuery("SELECT * FROM Utilisateur"); 
        try {
            while (rs.next()) {
                if (rs.getString("nom").equals(this.eventSrc)) {
                    this.name.setText(rs.getString("nom"));
                    this.surname.setText(rs.getString("prenom"));
                    this.password.setText(rs.getString("password"));
                    this.credentials.setValue(rs.getString("credentials"));
                }
                // System.out.println(name.setText("")); 
                name.setText(rs.getString("nom"));//rs.getString("nom"));
                // System.out.println(rs.getString("nom"));
                // System.out.println(rs.getString("prenom"));
                // System.out.println(rs.getString("mdpUtilisateur"));
                // System.out.println(rs.getString("permission"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(this.eventSrc);
    }

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/GererProfils.fxml", event);
        } 
    }


}

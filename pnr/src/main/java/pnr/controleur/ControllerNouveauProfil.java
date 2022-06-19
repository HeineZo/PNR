package pnr.controleur;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControllerNouveauProfil extends Controller {


    @FXML
    private Button btnBack;

    @FXML
    private Button btnCreer;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtMdp;

    @FXML
    private ComboBox<String> cbPerm;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/GererProfilsVide.fxml", event);
        } else if((event.getSource() == btnCreer)&&(txtMdp.getText() != null)&&(cbPerm.getValue() != null)&&(txtNom.getText() != null)) {
            connect.executeUpdate("INSERT INTO Utilisateur VALUES(2,"+this.txtPrenom.getText()+","+this.txtMdp.getText()+","+this.getPerm(this.cbPerm));
            System.out.println("Utilisateur créé");
        } else {
            System.out.println("controller profil : Données nulles");
        }
    }

    private int getPerm(ComboBox<String> perm) {
        int ret = -1;
        if(perm != null) {
            if (perm.getValue().equals("Administrateur")) {
                ret = 1;
            } else if (perm.getValue().equals("Utilisateur")) {
                ret = 0;
            }
        } else {
            System.out.println("getPerm(): combobox nulle");
        }
        return ret;
    }
}
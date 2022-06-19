package pnr.controleur;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControllerNouveauProfil extends Controller implements Initializable{


    @FXML
    private Button btnBack;

    @FXML
    private Button btnCreer;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtPseudo;

    @FXML
    private TextField txtMdp;

    @FXML
    private ComboBox<String> cbPerm;

    private ObservableList<String> permissionChoices = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.permissionChoices.add("Utilisateur");
        this.permissionChoices.add("Administrateur");
        this.cbPerm.setItems(this.permissionChoices);
    }

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/GererProfilsVide.fxml", event);
        } else if((event.getSource() == btnCreer)&&(txtMdp.getText() != null)&&(cbPerm.getValue() != null)&&(txtPrenom.getText() != null)) { 
            System.out.println(this.txtPseudo.getText());
            System.out.println(this.txtMdp.getText());
            System.out.println(this.getPerm(this.cbPerm));
            System.out.println(this.txtPrenom.getText());
            connect.executeUpdate("INSERT INTO Utilisateur VALUES('"+this.txtPseudo.getText()+"','"+this.txtMdp.getText()+"','"+this.getPerm(this.cbPerm)+"','"+this.txtPrenom.getText()+"');");
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
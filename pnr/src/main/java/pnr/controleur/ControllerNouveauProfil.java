package pnr.controleur;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControllerNouveauProfil extends Controller implements Initializable{

    @FXML
    private AnchorPane anchorPane;

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
    private void handleBtnClick(ActionEvent event) throws SQLException {
        if (event.getSource() == btnBack) {
            loadStage("../vue/GererProfilsVide.fxml", event);
        } else if((event.getSource() == btnCreer)&&(txtMdp.getText() != null)&&(cbPerm.getValue() != null)&&(txtPrenom.getText() != null)) { 
            ArrayList<String> unique = new ArrayList<String>();
            ResultSet res = connect.executeQuery("SELECT nom FROM Utilisateur");
            while(res.next()){
                String nom=res.getString("nom");
                unique.add(nom);
            }
            if(unique.contains(this.txtPseudo.getText())){
                super.error("Pseudonyme déjà utilisé",anchorPane);
            } else {
                connect.executeUpdate("INSERT INTO Utilisateur VALUES('"+this.txtPseudo.getText()+"','"+this.txtMdp.getText()+"','"+this.getPerm(this.cbPerm)+"','"+this.txtPrenom.getText()+"');");
                System.out.println("Utilisateur créé");
            }
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
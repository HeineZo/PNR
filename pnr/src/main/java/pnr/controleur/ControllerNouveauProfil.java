package pnr.controleur;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private Label livePseudo;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtMdp;

    @FXML
    private MFXTextField txtPseudo = new MFXTextField();

    @FXML
    private ComboBox<String> cbPerm;

    private ObservableList<String> permissionChoices = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.permissionChoices.add("Utilisateur");
        this.permissionChoices.add("Administrateur");
        this.cbPerm.setItems(this.permissionChoices);

        this.txtPrenom.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtNom.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtMdp.textProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        this.txtPseudo.textProperty().addListener((observable, oldValue, newValue) -> {
            this.livePseudo.setText(newValue);
            checkDisable();
        });
        this.cbPerm.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkDisable();
        });
        
    }

    @FXML
    private void handleBtnClick(ActionEvent event) throws SQLException {
        if (event.getSource() == btnBack) {
            loadStage("../vue/GererProfils.fxml", event);
        } else if(event.getSource() == btnCreer) { 
            ArrayList<String> unique = new ArrayList<String>();
            ResultSet res = connect.executeQuery("SELECT pseudonyme FROM Utilisateur");
            while(res.next()){
                String nom=res.getString("pseudonyme");
                unique.add(nom);
            }

            if(unique.contains(this.txtPseudo.getText())){
                super.error("Pseudonyme déjà utilisé",anchorPane);
            } else {
                connect.executeUpdate("INSERT INTO Utilisateur VALUES('"+this.txtPseudo.getText()+"','"+this.txtNom.getText()+"','"+this.txtPrenom.getText()+"','"+this.txtMdp.getText()+"','"+this.getPerm(this.cbPerm)+"');");
                initConfirmation("NouveauProfil");
                loadStage("../vue/Confirmation.fxml", event);
            }
        } else {
            System.out.println("controller profil : Données nulles");
        }
    }

    public int getPerm(ComboBox<String> perm) {
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

    private void checkDisable() {
        if(!txtPrenom.getText().isEmpty() && !txtNom.getText().isEmpty() && !txtMdp.getText().isEmpty() && !txtPseudo.getText().isEmpty() && cbPerm.getValue() != null) {
            btnCreer.setDisable(false);
        } else {
            btnCreer.setDisable(true);
        }
    }
}
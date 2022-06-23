package pnr.controleur;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXComboBox;
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
import pnr.modele.EncryptString;

/**
 * Manages the NouveauProfil
 */
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
    private MFXTextField txtMdp;

    @FXML
    private MFXTextField txtNom;

    @FXML
    private MFXTextField txtPrenom;

    @FXML
    private MFXTextField txtPseudo = new MFXTextField();

    @FXML
    private MFXComboBox<String> cbPerm;

    private ObservableList<String> permissionChoices = FXCollections.observableArrayList();

    /**
     * This function is called when the FXML file is loaded, and it initializes the page with the image
     * and name of the species.
     * 
     * @param location the location of the FXML file
     * @param resources the resources used to localize the root object, or null if the root object was
     * not localized.
     */
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

    /**
     * It's a function that loads a new stage depending on the button that was clicked.
     * 
     * @param event the event that triggered the method
     */
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
                EncryptString cryptMdp = new EncryptString(this.txtMdp.getText());
                connect.executeUpdate("INSERT INTO Utilisateur VALUES('"+this.txtPseudo.getText()+"','"+this.txtNom.getText()+"','"+this.txtPrenom.getText()+"','"+cryptMdp.getEncryptedPassword()+"','"+this.getPerm(this.cbPerm)+"');");
                initConfirmation("NouveauProfil");
                loadStage("../vue/Confirmation.fxml", event);
            }
        } else {
            System.out.println("controller profil : Données nulles");
        }
    }

    /**
     * It returns an integer based on the value of a combobox
     * 
     * @param perm the MFXComboBox
     * @return The value of the selected item in the combobox.
     */
    public int getPerm(MFXComboBox<String> perm) {
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

    /**
     * If the text fields are not empty and the combo box has a value, enable the button
     */
    private void checkDisable() {
        if(!txtPrenom.getText().isEmpty() && !txtNom.getText().isEmpty() && !txtMdp.getText().isEmpty() && !txtPseudo.getText().isEmpty() && cbPerm.getValue() != null) {
            btnCreer.setDisable(false);
        } else {
            btnCreer.setDisable(true);
        }
    }
}
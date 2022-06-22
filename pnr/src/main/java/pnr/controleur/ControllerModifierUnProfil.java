package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import pnr.modele.EncryptString;

public class ControllerModifierUnProfil extends Controller implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnBack;

    @FXML
    private Button envoi;

    @FXML
    private Label livePseudo;

    @FXML
    private MFXButton supprimer;

    @FXML
    private MFXComboBox<String> credentials;

    @FXML
    private MFXTextField username = new MFXTextField();

    @FXML
    private MFXTextField nom = new MFXTextField();

    @FXML
    private MFXTextField password = new MFXTextField();

    @FXML
    private MFXTextField prenom = new MFXTextField();

    private String eventSrc;


    private ObservableList<String> permissionChoices = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.permissionChoices.add("Utilisateur");
        this.permissionChoices.add("Administrateur");
        this.credentials.setItems(this.permissionChoices);
    

        this.eventSrc = getUserClicked();
        resetUserClicked();
        ResultSet rs = connect.executeQuery("SELECT * FROM Utilisateur"); 
        try {
            while (rs.next()) {
                if (rs.getString("pseudonyme").equals(this.eventSrc)) {
                    this.nom.setText(rs.getString("nom"));
                    this.prenom.setText(rs.getString("prenom"));
                    this.password.setText(rs.getString("mdpUtilisateur"));
                    this.username.setText(rs.getString("pseudonyme"));
                    if (rs.getString("permission").equals("0")){
                        this.credentials.setText("Utilisateur");
                    } else {
                        this.credentials.setText("Administrateur");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.livePseudo.setText(this.username.getText());

        this.username.textProperty().addListener((observable, oldValue, newValue) -> {
            this.livePseudo.setText(newValue);
            this.envoi.setDisable(false);
        });
        this.prenom.textProperty().addListener((observable, oldValue, newValue) -> {
            this.envoi.setDisable(false);
        });
        this.nom.textProperty().addListener((observable, oldValue, newValue) -> {
            this.envoi.setDisable(false);
        });
        this.password.textProperty().addListener((observable, oldValue, newValue) -> {
            this.envoi.setDisable(false);
        });
        this.credentials.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.envoi.setDisable(false);
        });
    }

    @FXML
    private void handleBtnClick(ActionEvent event) throws SQLException {
        if (event.getSource() == btnBack) {
            loadStage("../vue/GererProfils.fxml", event);
        }
        if (!(this.username.getText().equals(""))){
            ArrayList<String> unique = new ArrayList<String>();
            ResultSet res = connect.executeQuery("SELECT pseudonyme FROM Utilisateur WHERE pseudonyme ='"+this.username.getText()+"';");
            while(res.next()){
                String pseudo=res.getString("pseudonyme");
                unique.add(pseudo);
            }
            if ((event.getSource() == supprimer)){
                if(unique.contains(this.username.getText())){
                    connect.executeUpdate("DELETE FROM Utilisateur WHERE pseudonyme ='"+this.username.getText()+"';");
                    initConfirmation("SuppressionProfil");
                    loadStage("../vue/Confirmation.fxml", event);
                } else {
                    super.error("L'utilisateur n'existe pas",anchorPane);
                }   
            } else if (event.getSource() == envoi){
                if(unique.contains(this.username.getText())){
                    ArrayList<String> lUser = new ArrayList<String>();
                    ResultSet rs = connect.executeQuery("SELECT * FROM Utilisateur WHERE pseudonyme ='"+this.username.getText()+"';");
                    while(rs.next()){
                        lUser.add(rs.getString("nom"));
                        lUser.add(rs.getString("mdpUtilisateur"));
                        lUser.add(rs.getString("permission"));
                        lUser.add(rs.getString("prenom"));
                        lUser.add(rs.getString("pseudonyme"));
                    }

                    if ((!(lUser.get(0).equals(this.nom.getText()))) && this.nom.getText() != null){
                        connect.executeUpdate("UPDATE Utilisateur SET nom ='"+this.nom.getText()+"' WHERE pseudonyme ='"+lUser.get(4)+"' ;");
                    }
                    if ((!(lUser.get(1).equals(this.password.getText()))) && this.password.getText() != null){
                        EncryptString cryptMdp = new EncryptString(this.password.getText());
                        connect.executeUpdate("UPDATE Utilisateur SET mdpUtilisateur ='"+cryptMdp.getEncryptedPassword()+"' WHERE pseudonyme ='"+lUser.get(4)+"' ;");
                    }
                    if (lUser.get(2).equals("0")){
                        String permission = "Utilisateur";
                        if ((!(permission.equals(this.credentials.getValue()))) && this.credentials.getValue() != null){
                            connect.executeUpdate("UPDATE Utilisateur SET permission = '1' WHERE pseudonyme ='"+lUser.get(4)+"';");
                        }
                    } else {
                        String permission2 = "Administrateur";
                        if ((!(permission2.equals(this.credentials.getValue()))) && this.credentials.getValue() != null){
                            connect.executeUpdate("UPDATE Utilisateur SET permission = '0' WHERE pseudonyme ='"+lUser.get(4)+"';");
                        }
                    }
                    if ((!(lUser.get(3).equals(this.prenom.getText()))) && this.prenom.getText() != null){
                        connect.executeUpdate("UPDATE Utilisateur SET prenom ='"+this.prenom.getText()+"' WHERE pseudonyme ='"+lUser.get(4)+"';");
                    }
                    if ((!(lUser.get(4).equals(this.username.getText()))) && this.username.getText() != null){
                        connect.executeUpdate("UPDATE Utilisateur SET pseudonyme ='"+this.username.getText()+"' WHERE pseudonyme ='"+lUser.get(4)+"';");
                    }
                    initConfirmation("ModifierProfil");
                    loadStage("../vue/Confirmation.fxml", event);
                } else {
                    super.error("L'utilisateur n'existe pas",anchorPane);
                }
            }
        } else {
            super.error("Veuillez renseigner un pseudonyme",anchorPane);
        }            
    }
}

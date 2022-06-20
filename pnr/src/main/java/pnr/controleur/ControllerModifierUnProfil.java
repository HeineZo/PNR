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
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

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
    private ComboBox<String> credentials;

    @FXML
    private MFXTextField username = new MFXTextField();

    @FXML
    private TextField prenom = new TextField();

    @FXML
    private TextField password = new TextField();

    @FXML
    private TextField nom = new TextField();

    private String eventSrc;


    private ObservableList<String> permissionChoices = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.permissionChoices.add("Utilisateur");
        this.permissionChoices.add("Administrateur");
        this.credentials.setItems(this.permissionChoices);
    

        this.eventSrc = getUserClicked().split(" ")[0];
        ResultSet rs = connect.executeQuery("SELECT * FROM Utilisateur"); 
        try {
            while (rs.next()) {
                if (rs.getString("nom").equals(this.eventSrc)) {
                    this.nom.setText(rs.getString("nom"));
                    this.prenom.setText(rs.getString("prenom"));
                    this.password.setText(rs.getString("mdpUtilisateur"));
                    this.username.setText(rs.getString("pseudonyme"));
                    if (rs.getString("permission").equals("0")){
                        this.credentials.setValue("Utilisateur");
                    } else {
                        this.credentials.setValue("Administrateur");
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
        if (!(this.nom.getText().equals(""))){
            ArrayList<String> unique = new ArrayList<String>();
            ResultSet res = connect.executeQuery("SELECT nom FROM Utilisateur WHERE nom ='"+this.nom.getText()+"';");
            while(res.next()){
                String nom=res.getString("nom");
                unique.add(nom);
            }
            if ((event.getSource() == supprimer)){
                if(unique.contains(this.nom.getText())){
                    connect.executeUpdate("DELETE FROM Utilisateur WHERE nom ='"+this.nom.getText()+"';");
                    initConfirmation("SuppressionProfil");
                    loadStage("../vue/Confirmation.fxml", event);
                } else {
                    super.error("L'utilisateur n'existe pas",anchorPane);
                }   
            } else if (event.getSource() == envoi){
                if(unique.contains(this.nom.getText())){
                    ArrayList<String> lUser = new ArrayList<String>();
                    ResultSet rs = connect.executeQuery("SELECT * FROM Utilisateur WHERE nom ='"+this.nom.getText()+"';");
                    while(rs.next()){
                        lUser.add(rs.getString("nom"));
                        lUser.add(rs.getString("mdpUtilisateur"));
                        lUser.add(rs.getString("permission"));
                        lUser.add(rs.getString("prenom"));
                        lUser.add(rs.getString("pseudonyme"));
                    }

                    if (!(lUser.get(0).equals(this.nom.getText()))){
                        connect.executeUpdate("UPDATE Utilisateur SET nom ='"+this.nom.getText()+"' WHERE nom ='"+lUser.get(0)+"' ;");
                    }
                    if ((!(lUser.get(1).equals(this.password.getText()))) && this.password.getText() != null){
                        connect.executeUpdate("UPDATE Utilisateur SET mdpUtilisateur ='"+this.password.getText()+"' WHERE nom ='"+lUser.get(0)+"' ;");
                    }
                    if (lUser.get(2).equals("0")){
                        String permission = "Utilisateur";
                        if ((!(permission.equals(this.credentials.getValue()))) && this.credentials.getValue() != null){
                            connect.executeUpdate("UPDATE Utilisateur SET permission = '1' WHERE nom ='"+lUser.get(0)+"' ;");
                        }
                    } else {
                        String permission2 = "Administrateur";
                        if ((!(permission2.equals(this.credentials.getValue()))) && this.credentials.getValue() != null){
                            connect.executeUpdate("UPDATE Utilisateur SET permission = '0' WHERE nom ='"+lUser.get(0)+"' ;");
                        }
                    }
                    if ((!(lUser.get(3).equals(this.prenom.getText()))) && this.prenom.getText() != null){
                        connect.executeUpdate("UPDATE Utilisateur SET prenom ='"+this.prenom.getText()+"' WHERE nom ='"+lUser.get(0)+"' ;");
                    }
                    if ((!(lUser.get(4).equals(this.username.getText()))) && this.username.getText() != null){
                        connect.executeUpdate("UPDATE Utilisateur SET pseudonyme ='"+this.username.getText()+"' WHERE nom ='"+lUser.get(0)+"' ;");
                    }
                    initConfirmation("ModifierProfil");
                    loadStage("../vue/Confirmation.fxml", event);
                } else {
                    super.error("L'utilisateur n'existe pas",anchorPane);
                }
            }
        } else {
            super.error("Veuillez renseigner un nom",anchorPane);
        }            
    }
}

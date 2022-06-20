package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private Button supprimer;

    @FXML
    private ComboBox<String> credentials;

    @FXML
    private TextField prenom = new TextField();

    @FXML
    private TextField password = new TextField();

    @FXML
    private TextField pseudonyme = new TextField();

    private String eventSrc;

    private ObservableList<String> permissionChoices = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.permissionChoices.add("Utilisateur");
        this.permissionChoices.add("Administrateur");
        this.credentials.setItems(this.permissionChoices);

        this.eventSrc = getUserClicked().split(" ")[0];
        // System.out.println(this.eventSrc);
        ResultSet rs = connect.executeQuery("SELECT * FROM Utilisateur"); 
        try {
            while (rs.next()) {
                System.out.println(rs.getString("nom"));
                if (rs.getString("nom").equals(this.eventSrc)) {
                    this.pseudonyme.setText(rs.getString("nom"));
                    this.prenom.setText(rs.getString("prenom"));
                    this.password.setText(rs.getString("mdpUtilisateur"));
                    if (rs.getString("permission").equals("0")){
                        this.credentials.setValue("Utilisateur");
                    } else {
                        this.credentials.setValue("Administrateur");
                    }
                }
                // System.out.println(name.setText("")); 
                // name.setText(rs.getString("nom"));//rs.getString("nom"));
                // System.out.println(rs.getString("nom"));
                // System.out.println(rs.getString("prenom"));
                // System.out.println(rs.getString("mdpUtilisateur"));
                // System.out.println(rs.getString("permission"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBtnClick(ActionEvent event) throws SQLException {
        if (event.getSource() == btnBack) {
            loadStage("../vue/GererProfils.fxml", event);
        }
        if (!(this.pseudonyme.getText().equals(""))){
            ArrayList<String> unique = new ArrayList<String>();
            ResultSet res = connect.executeQuery("SELECT nom FROM Utilisateur WHERE nom ='"+this.pseudonyme.getText()+"';");
            while(res.next()){
                String nom=res.getString("nom");
                unique.add(nom);
            }
            if ((event.getSource() == supprimer)){
                if(unique.contains(this.pseudonyme.getText())){
                    connect.executeUpdate("DELETE FROM Utilisateur WHERE nom ='"+this.pseudonyme.getText()+"';");
                    initConfirmation("SuppressionProfil");
                    loadStage("../vue/Confirmation.fxml", event);
                } else {
                    super.error("L'utilisateur n'existe pas",anchorPane);
                }   
            } else if (event.getSource() == envoi){
                if(unique.contains(this.pseudonyme.getText())){
                    ArrayList<String> lUser = new ArrayList<String>();
                    ResultSet rs = connect.executeQuery("SELECT * FROM Utilisateur WHERE nom ='"+this.pseudonyme.getText()+"';");
                    while(rs.next()){
                        lUser.add(rs.getString("nom"));
                        lUser.add(rs.getString("mdpUtilisateur"));
                        lUser.add(rs.getString("permission"));
                        lUser.add(rs.getString("prenom"));
                    }

                    for (String s : lUser){
                        System.out.println(s);
                    }

                    if (!(lUser.get(0).equals(this.pseudonyme.getText()))){
                        connect.executeUpdate("UPDATE Utilisateur SET nom ='"+this.pseudonyme.getText()+"' WHERE nom ='"+lUser.get(0)+"' ;");
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
                    initConfirmation("ModifierProfil");
                    loadStage("../vue/Confirmation.fxml", event);
                } else {
                    super.error("L'utilisateur n'existe pas",anchorPane);
                }
            }
        } else {
            super.error("Veuillez renseigner un pseudonyme ",anchorPane);
        }            
    }
}

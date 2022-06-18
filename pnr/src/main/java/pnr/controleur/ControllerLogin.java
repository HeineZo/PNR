package pnr.controleur;

import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pnr.modele.WwdEmbedded;

public class ControllerLogin extends Controller  {

    @FXML
    private TextField identifiant;

    @FXML
    private TextField password;

    @FXML
    private Button send;

    private Connection c;

    public ArrayList<String> isConnected(String nom) throws SQLException {
        this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_pnr", "pnr", "mdp_pnr");
        ArrayList<String> ret = new ArrayList<String>();
        ResultSet res = null;
        String query = "SELECT * FROM Utilisateur WHERE nom = '"+nom+"'";

        Statement stmt = c.createStatement();
        res = stmt.executeQuery(query);

        while(res.next()){
            String id=res.getString("idUtilisateur");
            ret.add(id);
            String nomUtilisateur=res.getString("nom");
            ret.add(nomUtilisateur);
            String mdp=res.getString("mdpUtilisateur");
            ret.add(mdp);
            String perm=res.getString("permission");
            ret.add(perm);
        }
        res.close();

        return ret;
    }
    
    @FXML
    private void handleBtnClick(ActionEvent event) throws SQLException {
        if (event.getSource() == send) {
            ArrayList<String> lUtilisateur = isConnected(identifiant.getText());
            if (lUtilisateur.get(2).equals(password.getText())){
                if(lUtilisateur.get(3).equals(String.valueOf(0))){
                    loadStage("../vue/ChoixAction.fxml", event);
                } else {
                    loadStage("../vue/ChoixActionAdmin.fxml", event);
                }
            }
        } 
    }
}

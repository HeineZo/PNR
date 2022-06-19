package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;

public class ControllerGererProfils extends Controller implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private Button btnModifierProfil;

    @FXML
    private Button btnNouveauProfil;

    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadUI("../vue/DynamicProfil.fxml", gridPane);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixActionAdmin.fxml", event);
        } else if (event.getSource() == btnModifierProfil) {
            loadStage("../vue/ModifierUnProfil.fxml", event);
        } else if (event.getSource() == btnNouveauProfil) {
            loadStage("../vue/NouveauProfil.fxml", event);
        }
    }

    public void loadUI(String ui, GridPane gridPane) throws SQLException, IOException{
        
        int i = 0;
        int j = 0;
        GridPane root = FXMLLoader.load(getClass().getResource(ui));
        
        ResultSet rs = connect.executeQuery("SELECT nom FROM Utilisateur");
        
        while(rs.next()) {
            if (i > 2) {
                i = 0;
            } 

            ((Labeled) root.getChildren().get(1)).setText(rs.getString("nom"));
            gridPane.add(root, i, j);
            i++;
            j++;
        }
        
        connect.disconnect();
    }


}

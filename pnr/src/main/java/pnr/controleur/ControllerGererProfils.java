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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.github.palexdev.materialfx.controls.MFXTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ControllerGererProfils extends Controller implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private Button btnModifierProfil;

    @FXML
    private Button btnNouveauProfil;

    @FXML
    private MFXTableView<GridPane> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadUI("../vue/DynamicProfil.fxml", table);
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

    public void loadUI(String ui, MFXTableView<GridPane> table) throws SQLException, IOException{
        
        int i = 0;
        int j = 0;
        GridPane root = FXMLLoader.load(getClass().getResource(ui));

        ArrayList<GridPane> list = new ArrayList<GridPane>();
        
        ResultSet rs = connect.executeQuery("SELECT nom FROM Utilisateur");
        
        while(rs.next()) {
            ((Labeled) root.getChildren().get(1)).setText(rs.getString("nom"));
            list.add(root);
        }
        
        System.out.println(list.toString());
        ObservableList<GridPane> buttonList= FXCollections.observableArrayList(list);
        table.setItems(buttonList);
        
        connect.disconnect();
    }


}

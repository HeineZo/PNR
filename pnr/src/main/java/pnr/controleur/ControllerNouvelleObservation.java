package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ControllerNouvelleObservation extends Controller implements Initializable{
    @FXML
    private MFXCheckListView<String> observatorList;

    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> list = new ArrayList<String>();
        
        ResultSet rs = connect.executeQuery("SELECT * FROM Observateur");
        try {
            while (rs.next()){
                list.add(rs.getString("nom") + " " + rs.getString("prenom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> listObservator = FXCollections.observableArrayList(list);
        observatorList.setItems(listObservator);
        observatorList.features().enableBounceEffect();
		observatorList.features().enableSmoothScrolling(0.5);
        
    }

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        }
    }




}

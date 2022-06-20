package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ControllerNouvelleObservation extends Controller implements Initializable{
    @FXML
    private MFXCheckListView<String> observatorList;

    @FXML
    private Button btnBack;

    @FXML
    private Button envoi;

    @FXML
    private MFXTextField txtDate;

    @FXML
    private MFXTextField txtHeure;

    @FXML
    private MFXTextField txtCoordX;

    @FXML
    private MFXTextField txtCoordY;

    @FXML
    private ComboBox<String> cbSexe;

    @FXML
    private ComboBox<String> cbProto;

    @FXML
    private ComboBox<String> cbTypeObs;

    @FXML
    private ComboBox<String> cbEspece;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadList();

    }


    private void loadList() {
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
        } else if (event.getSource() == envoi) {
            initConfirmation("AjouterObservation");
            loadStage("../vue/Confirmation.fxml", event);
        }
    }

}

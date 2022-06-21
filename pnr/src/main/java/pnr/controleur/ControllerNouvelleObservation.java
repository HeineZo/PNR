package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
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
    private MFXScrollPane scrollPane;

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
        try {
            loadUI("../vue/ObservationChouette.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // loadList();
        
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

    public void loadUI(String ui) throws IOException{

        // int i = 0;
        // int j = 0;
        AnchorPane pane = FXMLLoader.load(getClass().getResource(ui));

        // ResultSet rs = connect.executeQuery("SELECT nom FROM Utilisateur");
        // ((Labeled) root.getChildren().get(1)).setText(rs.getString("nom"));
        // ((Labeled) root.getChildren().get(0)).setText(rs.getString("nom"));
        // System.out.println(root.getChildren().get(1));
        scrollPane.setContent(pane);

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

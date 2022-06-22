package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import pnr.modele.donnee.*;

public class ControllerDernierObservation extends Controller implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private ImageView imgEspece;

    @FXML
    private MFXListView<Observation> listView = new MFXListView<>();

    @FXML
    private Text nameEspece;

    private String table, id, eventSrc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listId = new ArrayList<>();
        eventSrc = initPage(imgEspece, nameEspece);
        switch (eventSrc) {
            case "Batracien":
                table = "JOIN Obs_Batracien ";
                id = "ON obsB=idObs";
                break;
            case "Chouette":
                table = "JOIN Obs_Chouette ";
                id = "ON numObs=idObs";
                break;
            case "GCI":
                table = "JOIN Obs_GCI ";
                id = "ON obsG=idObs";
                break;
            case "Hippocampe":
                table = "JOIN Obs_Hippocampe ";
                id = "ON obsH=idObs";
                break;
            case "Loutre":
                table = "JOIN Obs_Loutre ";
                id = "ON obsL=idObs";
                break;
            default:
                table ="";
                id = "";
                break;
        }

        ResultSet rs = connect.executeQuery("SELECT idObs, dateObs FROM Observation "+table+id+" ORDER BY dateObs DESC");
        try {
            while (rs.next()){
                listId.add(rs.getString("idObs"));
                if (rs.getDate("dateObs") != null){
                    list.add("Observation du "+new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dateObs")));
                } else {
                    list.add("Date indisponible");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listView.setItems(getMessages());
        listView.features().enableBounceEffect();
		listView.features().enableSmoothScrolling(0.5);
        listView.setCellFactory(person -> new PersonCellFactory(listView, person, "mfx-file"));
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String idClicked = new String(listView.getSelectionModel().getSelectedValues().get(1));
                System.out.println(idClicked);
                
                loadUser("../vue/NouvelleObservation"+eventSrc+".fxml", event, idClicked);
            }
        });  
    }

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        // } else if (event.getSource() == btnObs) {
        //     loadStage("../vue/ModifierUneObservation.fxml", event);
        }
    }
}

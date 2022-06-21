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
import java.util.ArrayList;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControllerDernierObservation extends Controller implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private ImageView imgEspece;

    @FXML
    private MFXListView<String> listView = new MFXListView<>();

    @FXML
    private Text nameEspece;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> list = new ArrayList<String>();
        
        ResultSet rs = connect.executeQuery("SELECT idObs, dateObs FROM Observation ORDER BY dateObs DESC");
        try {
            while (rs.next()){
                list.add("Observation du "+rs.getDate("dateObs"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> listProfile = FXCollections.observableArrayList(list);
        listView.setItems(listProfile);
        listView.features().enableBounceEffect();
		listView.features().enableSmoothScrolling(0.5);
        listView.setCellFactory(person -> new PersonCellFactory(listView, person, "mfx-file"));
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String user = new String(listView.getSelectionModel().getSelectedValues().get(0));
                
                loadUser("../vue/ModifierUneObservation.fxml", event, user);
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

package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.DateTimeUtils;
import io.github.palexdev.materialfx.utils.others.dates.DateStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ControllerNouvelleObservation extends Controller implements Initializable{
    @FXML
    private MFXCheckListView<String> observatorList;

    @FXML
    private Button btnBack;

    @FXML
    private ImageView imgEspece = new ImageView();

    @FXML
    private Text nameEspece = new Text();

    @FXML
    private MFXButton envoi;

    @FXML
    private MFXScrollPane scrollPane;

    @FXML
    private MFXDatePicker datePicker = new MFXDatePicker();

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

    private String eventSrc;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.eventSrc = initPage(this.imgEspece, this.nameEspece);
        try {
            switch (eventSrc) {
                case "Batracien":
                    loadUI("../vue/ObservationBatracien.fxml", scrollPane);
                    break;
                case "Chouette":
                    loadUI("../vue/ObservationChouette.fxml", scrollPane);
                    break;
                case "GCI":
                    loadUI("../vue/ObservationGCI.fxml", scrollPane);
                    break;
                case "Hippocampe":
                    loadUI("../vue/ObservationHippocampe.fxml", scrollPane);
                    break;
                case "Loutre":
                    loadUI("../vue/ObservationLoutre.fxml", scrollPane);
                    break;
                default:
                    break;
            }
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

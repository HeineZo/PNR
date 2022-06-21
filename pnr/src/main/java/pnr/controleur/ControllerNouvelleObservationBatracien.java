package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ControllerNouvelleObservationBatracien extends Controller implements Initializable{
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
    private MFXDatePicker datePicker;

    @FXML
    private ComboBox<String> cbObservateur;
    private ObservableList<String> observateur = FXCollections.observableArrayList();

    @FXML
    private MFXTextField txtHeure;

    @FXML
    private MFXTextField txtCoordY;

    @FXML
    private MFXTextField txtCoordX;

    @FXML
    private ComboBox<String> cbEspece;

    @FXML
    private MFXTextField txtAdulte;

    @FXML
    private MFXTextField txtPonte;

    @FXML
    private MFXTextField txtTetard;

    @FXML
    private MFXTextField txtTemperature;

    @FXML
    private ComboBox<String> cbTemperature;

    @FXML
    private ComboBox<String> cbCiel;

    @FXML
    private ComboBox<String> cbPluie;

    @FXML
    private ComboBox<String> cbVent;

    @FXML
    private MFXTextField txtZoneHumide;

    @FXML
    private MFXTextField txtVegetation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ResultSet rs = connect.executeQuery("SELECT pseudonyme FROM Utilisateur ORDER BY pseudonyme;");

        try {
            while (rs.next()) {
                this.observateur.add(rs.getString("pseudonyme"));
            }
            this.cbObservateur.setItems(this.observateur);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

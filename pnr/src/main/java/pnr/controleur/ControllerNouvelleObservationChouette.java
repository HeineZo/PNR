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

public class ControllerNouvelleObservationChouette extends Controller implements Initializable {
    
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
    private ComboBox<String> cbTypeObs;
    private ObservableList<String> typeObs = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cbEspece;
    private ObservableList<String> espece = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cbProto;
    private ObservableList<String> protocole = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cbSexe;
    private ObservableList<String> sexe = FXCollections.observableArrayList();

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

        this.espece.add("EFFRAIE");
        this.espece.add("CHEVECHE");
        this.espece.add("HULOTTE");
        this.cbEspece.setItems(this.espece);

        this.sexe.add("MALE");
        this.sexe.add("FEMELLE");
        this.sexe.add("INCONNU");
        this.cbSexe.setItems(this.sexe);

        this.protocole.add("0");
        this.protocole.add("1");
        this.cbProto.setItems(this.protocole);

        this.typeObs.add("SONORE");
        this.typeObs.add("VISUEL");
        this.typeObs.add("SONORE ET VISUEL");
        this.cbTypeObs.setItems(this.typeObs);
    }



    // @Override
    // public void initialize(URL location, ResourceBundle resources){
    //     this.eventSrc = initPage(this.imgEspece, this.nameEspece);
    //     try {
    //         switch (eventSrc) {
    //             case "Batracien":
    //                 loadUI("../vue/ObservationBatracien.fxml");
    //                 break;
    //             case "Chouette":
    //                 loadUI("../vue/ObservationChouette.fxml");
    //                 break;
    //             case "GCI":
    //                 loadUI("../vue/ObservationGCI.fxml");
    //                 break;
    //             case "Hippocampe":
    //                 loadUI("../vue/ObservationHippocampe.fxml");
    //                 break;
    //             case "Loutre":
    //                 loadUI("../vue/ObservationLoutre.fxml");
    //                 break;
    //             default:
    //                 break;
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     // loadList();

    // }


    // private void loadList() {
    //     ArrayList<String> list = new ArrayList<String>();
        
    //     ResultSet rs = connect.executeQuery("SELECT * FROM Observateur");
    //     try {
    //         while (rs.next()){
    //             list.add(rs.getString("nom") + " " + rs.getString("prenom"));
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     ObservableList<String> listObservator = FXCollections.observableArrayList(list);
    //     observatorList.setItems(listObservator);
    //     observatorList.features().enableBounceEffect();
	// 	observatorList.features().enableSmoothScrolling(0.5);
    // }

    // public void loadUI(String ui) throws IOException{

    //     // int i = 0;
    //     // int j = 0;
    //     FXMLLoader.setController(new ControllerObservationChouette());
    //     AnchorPane pane = FXMLLoader.load(getClass().getResource(ui));

    //     // ResultSet rs = connect.executeQuery("SELECT nom FROM Utilisateur");
    //     // ((Labeled) root.getChildren().get(1)).setText(rs.getString("nom"));
    //     // ((Labeled) root.getChildren().get(0)).setText(rs.getString("nom"));
    //     // System.out.println(root.getChildren().get(1));
    //     scrollPane.setContent(pane);

    // }

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

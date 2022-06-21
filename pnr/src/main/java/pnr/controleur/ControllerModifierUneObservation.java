package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.event.ActionEvent;


public class ControllerModifierUneObservation extends Controller implements Initializable{
    @FXML
    private Button btnBack;

    @FXML
    private MFXScrollPane scrollPane;

    @FXML
    private ImageView imgEspece;

    @FXML
    private Text nameEspece;
    
    @FXML
    private Button envoi;

    private String eventSrc;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.eventSrc = initPage(this.imgEspece, this.nameEspece);
        ResultSet rs;
        try {

            switch (eventSrc) {
                case "Batracien":
                    loadUI("../vue/ObservationBatracien.fxml", scrollPane);
                    rs = connect.executeQuery("SELECT * FROM Obs_Batracien"); 
                    break;
                case "Chouette":
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    ControllerChouette fooController = (ControllerChouette) fxmlLoader.getController();
                    System.out.println(fooController.toString());
                    loadUI("../vue/ObservationChouette.fxml", scrollPane);
                    rs = connect.executeQuery("SELECT * FROM Obs_Chouette"); 
                    fooController.initModify();
                    break;
                case "GCI":
                    loadUI("../vue/ObservationGCI.fxml", scrollPane);
                    rs = connect.executeQuery("SELECT * FROM Obs_GCI"); 
                    break;
                case "Hippocampe":
                    loadUI("../vue/ObservationHippocampe.fxml", scrollPane);
                    rs = connect.executeQuery("SELECT * FROM Obs_Hippocampe"); 
                    break;
                case "Loutre":
                    loadUI("../vue/ObservationLoutre.fxml", scrollPane);
                    rs = connect.executeQuery("SELECT * FROM Obs_Loutre"); 
                    break;
                default:
                    break;
            }
        } catch (IOException e)  {
            e.printStackTrace();
        }

        // try {
        //     while (rs.next()) {
        //         if (rs.getString("nom").equals(this.eventSrc)) {
        //             this.nom.setText(rs.getString("nom"));
        //             this.prenom.setText(rs.getString("prenom"));
        //             this.password.setText(rs.getString("mdpUtilisateur"));
        //             this.username.setText(rs.getString("pseudonyme"));
        //             if (rs.getString("permission").equals("0")){
        //                 this.credentials.setValue("Utilisateur");
        //             } else {
        //                 this.credentials.setValue("Administrateur");
        //             }
        //         }
        //     }
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

        // this.livePseudo.setText(this.username.getText());

        // this.username.textProperty().addListener((observable, oldValue, newValue) -> {
        //     this.livePseudo.setText(newValue);
        //     this.envoi.setDisable(false);
        // });
        // this.prenom.textProperty().addListener((observable, oldValue, newValue) -> {
        //     this.envoi.setDisable(false);
        // });
        // this.nom.textProperty().addListener((observable, oldValue, newValue) -> {
        //     this.envoi.setDisable(false);
        // });
        // this.password.textProperty().addListener((observable, oldValue, newValue) -> {
        //     this.envoi.setDisable(false);
        // });
        // this.credentials.valueProperty().addListener((observable, oldValue, newValue) -> {
        //     this.envoi.setDisable(false);
        // });
        // loadList();

    }
    
    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ModifierFiches.fxml", event);
        } else if (event.getSource() == envoi) {
            initConfirmation("ModifierObservation");
            loadStage("../vue/Confirmation.fxml", event);
        }
    }


}

package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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

public class ControllerGererProfils extends Controller implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MFXListView<String> profileList = new MFXListView<String>();

    @FXML
    private Button btnNouveauProfil;

    @FXML
    private GridPane gridPane;

    /**
     * Initializes the page
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> list = new ArrayList<String>();
        
        ResultSet rs = connect.executeQuery("SELECT nom, prenom FROM Utilisateur");
        try {
            while (rs.next()){
                list.add(rs.getString("nom") + " " + rs.getString("prenom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> listProfile = FXCollections.observableArrayList(list);
        profileList.setItems(listProfile);
        profileList.features().enableBounceEffect();
		profileList.features().enableSmoothScrolling(0.5);
        profileList.setCellFactory(pl -> new MFXListCell<String>(profileList, pl) {
            @Override
            public void render(String observation) {                
                super.render(observation);
                MFXFontIcon userIcon = new MFXFontIcon("mfx-user", 18);
                userIcon.getStyleClass().add("user-icon");
                if (userIcon != null) getChildren().add(0, userIcon);
            }
        });
        profileList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String user = new String(profileList.getSelectionModel().getSelectedValues().get(0));
                loadUser("../vue/ModifierUnProfil.fxml", event, user);
            }
        });  
    }

    /**
     * Handles the button's action
     * @param event enabled when the button is clicked
     * @throws IOException
     */
    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixActionAdmin.fxml", event);
        // } else if (event.getSource() == btnModifierProfil) {
        //     loadStage("../vue/ModifierUnProfil.fxml", event);
        } else if (event.getSource() == btnNouveauProfil) {
            loadStage("../vue/NouveauProfil.fxml", event);
        }
    }
}

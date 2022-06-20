package pnr.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import io.github.palexdev.materialfx.controls.MFXContextMenuItem;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

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
        // System.out.println(profileList.getSelectionModel());
        //When a profile is selected, the gridPane is filled with the profile's informations
        // profileList.getSelectionModel().selectionProperty().addListener(observable, oldValue, newValue) -> {
        //     if (newValue != null) {
        //         gridPane.getChildren().clear();
        //         ResultSet rs2 = connect.executeQuery("SELECT * FROM Observateur WHERE nom = '" + newValue.split(" ")[0] + "' AND prenom = '" + newValue.split(" ")[1] + "'");

        // System.out.println(profileList.getSelectionModel().selectionProperty()) ;//.addListener((observable, oldValue, newValue) -> {
            
    }

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

    public void loadUI(String ui, GridPane gridPane) throws SQLException, IOException{
        
        int i = 0;
        int j = 0;
        AnchorPane root = FXMLLoader.load(getClass().getResource(ui));
        
        ResultSet rs = connect.executeQuery("SELECT nom FROM Utilisateur");
        
        while(rs.next()) {
            if (i > 2) {
                i = 0;
                j++;
            } 

            Button b1= new Button(rs.getString("nom"));
            b1.getStyleClass().add("choice");

            // ((Labeled) root.getChildren().get(0)).setText(rs.getString("nom"));
            // System.out.println(root.getChildren().get(1));
            gridPane.add(b1, i, j);
            i++;
            
        }
        
        connect.disconnect();
    }

    public InputStream multipleFXML(String ui) throws IOException{
        URL fxmlResource = getClass().getResource(ui);
        InputStream inputStream = fxmlResource.openStream();
        byte[] buffer = new byte[8192];
        int totalBytes = 0 ;
        int bytesRead ;
        while((bytesRead = inputStream.read(buffer, totalBytes, buffer.length - totalBytes)) != -1) {
            totalBytes += bytesRead ;
            if (totalBytes == buffer.length) {
                byte[] newBuffer = new byte[2 * buffer.length];
                System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
                buffer = newBuffer ;
            }
        }
    
        inputStream.close();
        byte[] content = new byte[totalBytes];
        System.arraycopy(buffer, 0, content, 0, totalBytes);
    
        InputStream fxml = new ByteArrayInputStream(content);
        
        return fxml;
    }

}

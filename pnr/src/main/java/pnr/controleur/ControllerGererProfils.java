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
import java.util.logging.Level;
import java.util.logging.Logger;

import io.github.palexdev.materialfx.controls.MFXContextMenuItem;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.beans.Observable;
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
        profileList.setCellFactory(person -> new PersonCellFactory(profileList, person));
        profileList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                System.out.println("clicked on " + profileList.getSelectionModel().getSelectedValues());
            }
        });

            
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

	private static class PersonCellFactory extends MFXListCell<String> {
		private final MFXFontIcon userIcon;

		public PersonCellFactory(MFXListView<String> listView, String data) {
			super(listView, data);

			userIcon = new MFXFontIcon("mfx-user", 18);
            // delete-icon = new MFXFontIcon("mfx-delete", 18);
			userIcon.getStyleClass().add("user-icon");
			render(data);
		}

		@Override
		protected void render(String data) {
			super.render(data);
			if (userIcon != null) getChildren().add(0, userIcon);

		}
	}

}

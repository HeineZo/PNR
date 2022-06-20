package pnr.controleur;

import pnr.modele.donnee.*;
import pnr.modele.donneeAddsOn.*;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerVisualiserTables extends Controller implements Initializable {

    private String eventSrc;

    @FXML
    private ImageView imgEspece;

    @FXML
    private TabPane tpBatracien, tpChouette, tpGCI;

    @FXML
    private TableView<ObsBatracien> tbvBatracien;

    @FXML
    private TableView<ZoneHumide> tbvBatracienZoneHumide;

    @FXML
    private TableView<Vegetation> tbvBatracienVegetation;

    @FXML
    private TableView<Chouette> tbvChouette;

    @FXML
    private TableView<ObsChouette> tbvChouetteObs;

    @FXML
    private TableView<NidGCI> tbvGCI;

    @FXML
    private TableView<ObsGCI> tbvGCIObs;

    @FXML
    private TableView<ObsHippocampe> tbvHippocampe;

    @FXML
    private TableView<ObsLoutre> tbvLoutre;

    private ObservableList<ObsBatracien> tlistBatracien = FXCollections.observableArrayList();
    private ObservableList<ZoneHumide> tlistBatracienZoneHumide = FXCollections.observableArrayList();
    private ObservableList<Vegetation> tlistVegetation = FXCollections.observableArrayList();
    private ObservableList<Chouette> tlistChouette = FXCollections.observableArrayList();
    private ObservableList<ObsChouette> tlistChouetteObs = FXCollections.observableArrayList();
    private ObservableList<NidGCI> tlistGCI = FXCollections.observableArrayList();
    private ObservableList<ObsGCI> tlistGCIObs = FXCollections.observableArrayList();
    private ObservableList<ObsHippocampe> tlistHippocampe = FXCollections.observableArrayList();
    private ObservableList<ObsLoutre> tlistLoutre = FXCollections.observableArrayList();

    @FXML
    private Label count;

    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.eventSrc = this.getEventSrcVisualiser();

        imgIcn();
        table();
    }

    private void imgIcn() {
        String urlImage = "";

        if (this.eventSrc.equals("Batracien")) {
            urlImage = "especes/batracien.png";
        } else if (this.eventSrc.equals("Chouette")) {
            urlImage = "especes/chouette.png";
        } else if (this.eventSrc.equals("GCI")) {
            urlImage = "especes/gci.png";
        } else if (this.eventSrc.equals("Hippocampe")) {
            urlImage = "especes/hippocampe.png";
        } else if (this.eventSrc.equals("Loutre")) {
            urlImage = "especes/loutre.png";
        } else {
            urlImage = "especes/null.png";
        }

        changeImage(urlImage);
    }

    public void changeImage(String url) {
        Image imProfile = new Image(getClass().getResourceAsStream(url));
        this.imgEspece.setImage(imProfile);
    }

    private void table() {
        if (this.eventSrc.equals("Batracien")) {
            this.tpBatracien.setVisible(true);
            this.tpChouette.setVisible(false);
            this.tpGCI.setVisible(false);
            this.tbvHippocampe.setVisible(false);
            this.tbvLoutre.setVisible(false);
        } else if (this.eventSrc.equals("Chouette")) {
            this.tpBatracien.setVisible(false);
            this.tpChouette.setVisible(true);
            this.tpGCI.setVisible(false);
            this.tbvHippocampe.setVisible(false);
            this.tbvLoutre.setVisible(false);
        } else if (this.eventSrc.equals("GCI")) {
            this.tpBatracien.setVisible(false);
            this.tpChouette.setVisible(false);
            this.tpGCI.setVisible(true);
            this.tbvHippocampe.setVisible(false);
            this.tbvLoutre.setVisible(false);
        } else if (this.eventSrc.equals("Hippocampe")) {
            this.tpBatracien.setVisible(false);
            this.tpChouette.setVisible(false);
            this.tpGCI.setVisible(false);
            this.tbvHippocampe.setVisible(true);
            this.tbvLoutre.setVisible(false);
        } else if (this.eventSrc.equals("Loutre")) {
            this.tpBatracien.setVisible(false);
            this.tpChouette.setVisible(false);
            this.tpGCI.setVisible(false);
            this.tbvHippocampe.setVisible(false);
            this.tbvLoutre.setVisible(true);
        }
    }

    @FXML
    void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        }
    }
}

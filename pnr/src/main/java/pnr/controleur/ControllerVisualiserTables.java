package pnr.controleur;

import pnr.modele.donnee.*;
import pnr.modele.donneeAddsOn.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;



import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.EnumFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerVisualiserTables extends Controller implements Initializable {

    private String eventSrc;

    @FXML
    private MFXTableView<Batracien> batracien = new MFXTableView<>();
    
    @FXML
    private ImageView imgEspece;

    @FXML
    private TabPane tpBatracien, tpChouette, tpGCI;

    @FXML
    private TableColumn<?, ?> dateObs;

    @FXML
    private TableColumn<?, ?> espece;

    @FXML
    private TableColumn<?, ?> heureObs;

    @FXML
    private TableColumn<?, ?> lieu;

    @FXML
    private TableColumn<?, ?> nombreAdultes;

    @FXML
    private TableColumn<?, ?> nombreAmplexus;

    @FXML
    private TableColumn<?, ?> nombrePonte;

    @FXML
    private TableColumn<?, ?> nombreTetard;

    @FXML
    private TableColumn<?, ?> obsB;

    @FXML
    private TableColumn<?, ?> observateur;

    @FXML
    private TableView<ObsBatracien> tbvBatracien;

    @FXML
    private TableView<ZoneHumide> tbvBatracienZoneHumide;

    @FXML
    private TableView<Vegetation> tbvBatracienVegetation;

    // @FXML
    // private TableView<Chouette> tbvChouette;

    // @FXML
    // private TableView<ObsChouette> tbvChouetteObs;

    @FXML
    private TableView<NidGCI> tbvGCI;

    // @FXML
    // private TableView<ObsGCI> tbvGCIObs;

    @FXML
    private TableView<ObsHippocampe> tbvHippocampe;

    @FXML
    private TableView<ObsLoutre> tbvLoutre;

    private ObservableList<ObsBatracien> tlistBatracien = FXCollections.observableArrayList();
    private ObservableList<ZoneHumide> tlistBatracienZoneHumide = FXCollections.observableArrayList();
    private ObservableList<Vegetation> tlistVegetation = FXCollections.observableArrayList();
    // private ObservableList<Chouette> tlistChouette = FXCollections.observableArrayList();
    // private ObservableList<ObsChouette> tlistChouetteObs = FXCollections.observableArrayList();
    private ObservableList<NidGCI> tlistGCI = FXCollections.observableArrayList();
    // private ObservableList<ObsGCI> tlistGCIObs = FXCollections.observableArrayList();
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
        setupTable();
        batracien.autosizeColumnsOnInitialization();
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

    private void setupTable() {
		MFXTableColumn<Batracien> id = new MFXTableColumn<>("id", true, Comparator.comparing(Batracien::getObsB));
		MFXTableColumn<Batracien> espece = new MFXTableColumn<>("Espece", true, Comparator.comparing(Batracien::getEspece));
		MFXTableColumn<Batracien> nbrAdulte = new MFXTableColumn<>("Nombre d'adulte", true, Comparator.comparing(Batracien::getNombreAdultes));
		MFXTableColumn<Batracien> nbrAmplexus = new MFXTableColumn<>("Nombre d'amplexus", true, Comparator.comparing(Batracien::getNombreAmplexus));
		MFXTableColumn<Batracien> nbrPonte = new MFXTableColumn<>("Nombre de ponte", true, Comparator.comparing(Batracien::getNombrePonte));
		MFXTableColumn<Batracien> nbrTetard = new MFXTableColumn<>("Nombre de tétard", true, Comparator.comparing(Batracien::getNombreTetard));
		MFXTableColumn<Batracien> temp= new MFXTableColumn<>("Température", true, Comparator.comparing(Batracien::getTemperature));
		MFXTableColumn<Batracien> meteo_ciel= new MFXTableColumn<>("Météo du ciel", true, Comparator.comparing(Batracien::getMeteoCiel));
		MFXTableColumn<Batracien> meteo_temp= new MFXTableColumn<>("Ressentie de température", true, Comparator.comparing(Batracien::getMeteoTemp));
		MFXTableColumn<Batracien> meteo_vent = new MFXTableColumn<>("Type du vent", true, Comparator.comparing(Batracien::getMeteoVent));
		MFXTableColumn<Batracien> meteo_pluie = new MFXTableColumn<>("Pluie", true, Comparator.comparing(Batracien::getMeteoPluie));
		MFXTableColumn<Batracien> zh = new MFXTableColumn<>("Zone humide", true, Comparator.comparing(Batracien::getConcerneZh));
		MFXTableColumn<Batracien> vege = new MFXTableColumn<>("Végétation", true, Comparator.comparing(Batracien::getConcernesvege));

        id.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getObsB));
        espece.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getEspece));
        nbrAdulte.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getNombreAdultes));
        nbrAmplexus.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getNombreAmplexus));
        nbrPonte.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getNombrePonte));
		nbrTetard.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getNombreTetard));
		temp.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getTemperature));
		meteo_ciel.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getMeteoCiel));
		meteo_temp.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getMeteoTemp));
		meteo_vent.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getMeteoVent));
		meteo_pluie.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getMeteoPluie));
		zh.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getConcerneZh));
		vege.setRowCellFactory(person -> new MFXTableRowCell<>(Batracien::getConcernesvege));


		batracien.getTableColumns().addAll(id, espece, nbrAdulte, nbrAmplexus, nbrPonte, nbrTetard, temp, meteo_ciel, meteo_temp, meteo_vent, meteo_pluie, zh, vege);
		batracien.getFilters().addAll(
                new IntegerFilter<>("id", Batracien::getObsB),
				new StringFilter<>("Espece", Batracien::getEspece),
				new IntegerFilter<>("Nombre d'adultes", Batracien::getNombreAdultes),
				new IntegerFilter<>("Nombre d'amplexus", Batracien::getNombreAmplexus),
				new IntegerFilter<>("Nombre de ponte", Batracien::getNombrePonte),
				new IntegerFilter<>("Nombre de tétard", Batracien::getNombreTetard),
				new DoubleFilter<>("Température", Batracien::getTemperature),
				new StringFilter<>("Météo du ciel", Batracien::getMeteoCiel),
				new StringFilter<>("Ressentie de température", Batracien::getMeteoTemp),
				new StringFilter<>("Type du vent", Batracien::getMeteoVent),
				new StringFilter<>("Pluie", Batracien::getMeteoPluie),
				new IntegerFilter<>("Zone humide", Batracien::getConcerneZh),
				new IntegerFilter<>("Végétation", Batracien::getConcernesvege)
		);

        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Batracien;");
        ArrayList<Batracien> list = new ArrayList<>();
        
        try {
            while (rs.next()){
                list.add(new Batracien(
                    rs.getInt("obsB"), 
                    rs.getString("espece"),
                    rs.getInt("nombreAdultes"),
                    rs.getInt("nombreAmplexus"),
                    rs.getInt("nombrePonte"),
                    rs.getInt("nombreTetard"),
                    rs.getDouble("temperature"),
                    rs.getString("meteo_ciel"),
                    rs.getString("meteo_temp"),
                    rs.getString("meteo_vent"),
                    rs.getString("meteo_pluie"),
                    rs.getInt("concerne_ZH"),
                    rs.getInt("concernes_vege")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Batracien> listProfile = FXCollections.observableArrayList(list);
		batracien.setItems(listProfile);
	}

    

    @FXML
    void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/Visualiser.fxml", event);
        }
    }
}

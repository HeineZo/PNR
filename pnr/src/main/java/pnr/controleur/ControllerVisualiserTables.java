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
    private MFXTableView<ObsBatracien> batracien = new MFXTableView<ObsBatracien>();
    
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

    // private void setupTable() throws SQLException {
    //     ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Batracien");
    //     ResultSetMetaData rsmd = rs.getMetaData();
    //     int columnCount = rsmd.getColumnCount();
        
    //     ArrayList<String> list = new ArrayList<String>();
    //     // The column count starts from 1
    //     for (int i = 1; i <= columnCount; i++ ) {
    //      String name = rsmd.getColumnName(i);
    //      list.add(name);
    //     }

        
       
    //     // while (rs.next()) {
    //     //     if (rs.getString("nom").equals(this.eventSrc)) {
    //     //         this.nom.setText(rs.getString("nom"));
    //     //         this.prenom.setText(rs.getString("prenom"));
    //     //         this.password.setText(rs.getString("mdpUtilisateur"));
    //     //         this.username.setText(rs.getString("pseudonyme"));
    //     //         if (rs.getString("permission").equals("0")){
    //     //             this.credentials.setValue("Utilisateur");
    //     //         } else {
    //     //             this.credentials.setValue("Administrateur");
    //     //         }
    //     //     }
    //     // }
    //     MFXTableColumn<String> nameColumn = new MFXTableColumn<>("Name", true);
    //     // nameColumn.setRowCellFactory(batracien -> new MFXTableRowCell<>(ObsBatracien::getEspeceBatracien));

        
    // }
    private void setupTable() {
		MFXTableColumn<Observation> id = new MFXTableColumn<>("Id", true, Comparator.comparing(Observation::getIdObs));
		MFXTableColumn<Observation> dateObs = new MFXTableColumn<>("Date", true, Comparator.comparing(Observation::getDateObs));
		MFXTableColumn<Observation> heureObs = new MFXTableColumn<>("Heure", true, Comparator.comparing(Observation::getHeureObs));
		MFXTableColumn<Observation> lieu = new MFXTableColumn<>("Lieu", true);
		MFXTableColumn<Observateur> observateur = new MFXTableColumn<>("Observateur", true, Comparator.comparing(Observateur::getNom));
		MFXTableColumn<ObsBatracien> nbrAdulte = new MFXTableColumn<>("Nombre d'adulte", true, Comparator.comparing(ObsBatracien::getNombreAdultes));
		MFXTableColumn<ObsBatracien> nbrAmplexus= new MFXTableColumn<>("Nombre d'Amplexus'", true, Comparator.comparing(ObsBatracien::getNombreAmplexus));
		MFXTableColumn<ObsBatracien> nbrTetard= new MFXTableColumn<>("Nombre de tetard", true, Comparator.comparing(ObsBatracien::getNombreTetard));
		MFXTableColumn<ObsBatracien> nbrPonte= new MFXTableColumn<>("Nombre de ponte", true, Comparator.comparing(ObsBatracien::getNombrePonte));
		MFXTableColumn<ObsBatracien> espece = new MFXTableColumn<>("espece", true, Comparator.comparing(ObsBatracien::getEspeceBatracien));

        id.setRowCellFactory(person -> new MFXTableRowCell<>(Observation::getIdObs));
        dateObs.setRowCellFactory(person -> new MFXTableRowCell<>(Observation::getDateObs));
        heureObs.setRowCellFactory(person -> new MFXTableRowCell<>(Observation::getHeureObs));
        lieu.setRowCellFactory(person -> new MFXTableRowCell<>(Observation::getLieuObs));
        observateur.setRowCellFactory(person -> new MFXTableRowCell<>(Observateur::getNom));
        nbrAdulte.setRowCellFactory(person -> new MFXTableRowCell<>(ObsBatracien::getNombreAdultes));
		nbrAmplexus.setRowCellFactory(person -> new MFXTableRowCell<>(ObsBatracien::getNombreAmplexus));
		nbrTetard.setRowCellFactory(person -> new MFXTableRowCell<>(ObsBatracien::getNombreTetard));
		nbrPonte.setRowCellFactory(person -> new MFXTableRowCell<>(ObsBatracien::getNombrePonte));
		espece.setRowCellFactory(person -> new MFXTableRowCell<>(ObsBatracien::getEspeceBatracien) {{
			setAlignment(Pos.CENTER_RIGHT);
		}});
		nbrPonte.setAlignment(Pos.CENTER_RIGHT);


		batracien.getTableColumns().addAll(nbrAdulte, nbrAmplexus, nbrTetard, nbrPonte, espece);
		batracien.getFilters().addAll(
				// new EnumFilter<>("Name", EspeceBatracien::v),
				new IntegerFilter<>("Nombre adultes", ObsBatracien::getNombreAdultes),
				new IntegerFilter<>("Nombre Pontes", ObsBatracien::getNombrePonte)
		);

        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Batracien JOIN Observation ON idObs=obsB JOIN AObserve ON lobservation=idObs JOIN Observateur ON lobservateur=idObservateur;");
        ArrayList<ObsBatracien> list = new ArrayList<ObsBatracien>();
        
        try {
            while (rs.next()){
                int[] resObs = {rs.getInt("nombreAdultes"), rs.getInt("nombreAmplexus"), rs.getInt("nombreTetard"), rs.getInt("nombrePonte")};
                ArrayList<Observateur> observateurList = new ArrayList<Observateur>();
                observateurList.add(new Observateur(rs.getInt("idObservateur"), rs.getString("nom"), rs.getString("prenom")));
                list.add(new ObsBatracien(
                    rs.getInt("obsB"), 
                    rs.getDate("dateObs"),
                    rs.getTime("heureObs"), 
                    new Lieu(rs.getFloat("lieu_Lambert_X"),  rs.getFloat("lieu_Lambert_Y")),
                    observateurList,
                    resObs,
                    EspeceBatracien.valueOf(rs.getString("espece").toUpperCase())
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<ObsBatracien> listProfile = FXCollections.observableArrayList(list);
		batracien.setItems(listProfile);
        // list.add(new ObsBatracien(
        //     rs.getInt("obsB"), 
        //     rs.getDate("dateObs"),
        //     rs.getTime("heureObs"), 
        //     new Lieu(rs.getFloat("lieu_Lambert_X"),  rs.getFloat("lieu_Lambert_Y")),
        //     observateurList,
        //     resObs,
        //     EspeceBatracien.valueOf(rs.getString("espece").toUpperCase())
	}

    

    @FXML
    void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/Visualiser.fxml", event);
        }
    }
}

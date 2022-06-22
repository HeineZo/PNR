package pnr.controleur;

import pnr.modele.donnee.*;
import pnr.modele.donneeAddsOn.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.AnchorPane;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ControllerVisualiserTables extends Controller implements Initializable {

    private String eventSrc;

    @FXML
    private Button export;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imgEspece;

    @FXML
    private MFXTableView<Batracien> batracien = new MFXTableView<>();

    @FXML
    private MFXTableView<JointureChouette> chouette = new MFXTableView<>();

    @FXML
    private MFXTableView<Loutre> loutre = new MFXTableView<>();

    @FXML
    private Label count;

    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.eventSrc = this.getEventSrcVisualiser();
        imgIcn();
        if (this.eventSrc.equals("Batracien")) {
            setupTableBatracien();
            batracien.autosizeColumnsOnInitialization();
        } else if (this.eventSrc.equals("Chouette")) {
            setupTableChouette();
            chouette.autosizeColumnsOnInitialization();
        } else if (this.eventSrc.equals("GCI")) {
            //urlImage = "especes/gci.png";
        } else if (this.eventSrc.equals("Hippocampe")) {
            //urlImage = "especes/hippocampe.png";
        } else if (this.eventSrc.equals("Loutre")) {
            setupTableLoutre();
            loutre.autosizeColumnsOnInitialization();
        } else {
            //urlImage = "especes/null.png";
        }
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

    private void setupTableBatracien() {
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

    private void setupTableChouette() {
		MFXTableColumn<JointureChouette> id = new MFXTableColumn<>("id", true, Comparator.comparing(JointureChouette::getLeNumIndividu));
		MFXTableColumn<JointureChouette> espece = new MFXTableColumn<>("Espece", true, Comparator.comparing(JointureChouette::getEspece));
		MFXTableColumn<JointureChouette> sexe = new MFXTableColumn<>("Sexe", true, Comparator.comparing(JointureChouette::getSexe));
		MFXTableColumn<JointureChouette> protocole = new MFXTableColumn<>("Protocole", true, Comparator.comparing(JointureChouette::getProtocole));
		MFXTableColumn<JointureChouette> typeObs = new MFXTableColumn<>("Type d'observation", true, Comparator.comparing(JointureChouette::getTypeObs));
        MFXTableColumn<JointureChouette> numObs = new MFXTableColumn<>("numero de l'observation", true, Comparator.comparing(JointureChouette::getNumObs));

        id.setRowCellFactory(person -> new MFXTableRowCell<>(JointureChouette::getLeNumIndividu));
        espece.setRowCellFactory(person -> new MFXTableRowCell<>(JointureChouette::getEspece));
        sexe.setRowCellFactory(person -> new MFXTableRowCell<>(JointureChouette::getSexe));
        protocole.setRowCellFactory(person -> new MFXTableRowCell<>(JointureChouette::getProtocole));
        typeObs.setRowCellFactory(person -> new MFXTableRowCell<>(JointureChouette::getTypeObs));
        numObs.setRowCellFactory(person -> new MFXTableRowCell<>(JointureChouette::getNumObs));

		chouette.getTableColumns().addAll(id, espece, sexe, protocole, typeObs, numObs);
		chouette.getFilters().addAll(
                new StringFilter<>("id", JointureChouette::getLeNumIndividu),
				new StringFilter<>("Espece", JointureChouette::getEspece),
				new StringFilter<>("Sexe", JointureChouette::getSexe),
				new IntegerFilter<>("Protocole", JointureChouette::getProtocole),
				new StringFilter<>("Type d'observation", JointureChouette::getTypeObs),
				new IntegerFilter<>("numero de l'observation", JointureChouette::getNumObs)
		);

        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Chouette, Chouette WHERE numIndividu = leNumIndividu;");
        ArrayList<JointureChouette> list = new ArrayList<>();
        
        try {
            while (rs.next()){
                list.add(new JointureChouette(
                    rs.getString("leNumIndividu"),
                        rs.getString("espece"),
                        rs.getString("sexe"),
                        rs.getInt("protocole"),
                        rs.getString("typeObs"),
                        rs.getInt("numObs")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<JointureChouette> listProfile = FXCollections.observableArrayList(list);
		chouette.setItems(listProfile);
    }

    private void setupTableLoutre() {
		MFXTableColumn<Loutre> id = new MFXTableColumn<>("id", true, Comparator.comparing(Loutre::getObsL));
		MFXTableColumn<Loutre> commune = new MFXTableColumn<>("Commune", true, Comparator.comparing(Loutre::getCommune));
		MFXTableColumn<Loutre> lieuDit = new MFXTableColumn<>("Lieu dit", true, Comparator.comparing(Loutre::getLieuDit));
		MFXTableColumn<Loutre> indice = new MFXTableColumn<>("Indice", true, Comparator.comparing(Loutre::getIndice));

        id.setRowCellFactory(person -> new MFXTableRowCell<>(Loutre::getObsL));
        commune.setRowCellFactory(person -> new MFXTableRowCell<>(Loutre::getCommune));
        lieuDit.setRowCellFactory(person -> new MFXTableRowCell<>(Loutre::getLieuDit));
        indice.setRowCellFactory(person -> new MFXTableRowCell<>(Loutre::getIndice));

		loutre.getTableColumns().addAll(id, commune, lieuDit, indice);
		loutre.getFilters().addAll(
                new IntegerFilter<>("id", Loutre::getObsL),
				new StringFilter<>("Commune", Loutre::getCommune),
                new StringFilter<>("Lieu dit", Loutre::getLieuDit),
				new StringFilter<>("Indice", Loutre::getIndice)
		);

        ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Loutre;");
        ArrayList<Loutre> list = new ArrayList<>();
        
        try {
            while (rs.next()){
                list.add(new Loutre(
                    rs.getInt("obsL"),
                    rs.getString("commune"),
                    rs.getString("lieuDit"),
                    rs.getString("indice")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Loutre> listProfile = FXCollections.observableArrayList(list);
		loutre.setItems(listProfile);
	}

    

    @FXML
    void handleBtnClick(ActionEvent event) throws Exception {
        if (event.getSource() == btnBack) {
            loadStage("../vue/Visualiser.fxml", event);
        } else if (event.getSource() == export) {
            if (this.eventSrc.equals("Batracien")) {
                this.batracienfileCSV();
            } else if (this.eventSrc.equals("Chouette")) {
                this.chouettefileCSV();
            } else if (this.eventSrc.equals("GCI")) {
                this.GCIfileCSV();
            } else if (this.eventSrc.equals("Hippocampe")) {
                this.hippocampefileCSV();
            } else if (this.eventSrc.equals("Loutre")) {
                this.loutrefileCSV();
            }
        }
    }

    public void batracienfileCSV() throws IOException, SQLException {
        Batracien tb = new Batracien(0, null, 0, 0, 0, 0, 0, null, null, null, null, 0, 0);
        ArrayList<Batracien> addArr = new ArrayList<Batracien>();
        Writer writer = null;
        try {
            ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Batracien");
            while (rs.next()) {
                tb = new Batracien(
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
                        rs.getInt("concernes_vege"));
                addArr.add(tb);
            }
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FileChooser fChooser = new FileChooser();
            fChooser.setTitle("Choississez un fichier");
            ExtensionFilter filter = new ExtensionFilter("Comma-separated values (CSV)", "*.csv");
            fChooser.getExtensionFilters().add(filter);
            fChooser.setSelectedExtensionFilter(filter);
            fChooser.setInitialFileName("Batracien");
            String selectedDirPath = fChooser.showSaveDialog(stage).getAbsolutePath();
            File downloadedFile = new File(selectedDirPath);
            writer = new BufferedWriter(new FileWriter(downloadedFile));
            for (Batracien ut : addArr) {
                String text = ut.getObsB() + "," + ut.getEspece() + "," + ut.getNombreAdultes() + "," + ut.getNombreAmplexus() + ","
                        + ut.getNombrePonte() + "," + ut.getNombreTetard() + "," + ut.getTemperature()  + "," + ut.getMeteoCiel() + "," + ut.getMeteoTemp() 
                        + "," + ut.getMeteoVent() + "," + ut.getMeteoPluie() + "," + ut.getConcerneZh() + "," + ut.getConcernesvege() +"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

    public void loutrefileCSV() throws IOException, SQLException {
        Loutre tb = new Loutre(0, null, null, null);
        ArrayList<Loutre> addArr = new ArrayList<Loutre>();
        Writer writer = null;
        try {
            ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Loutre");
            while (rs.next()) {
                tb = new Loutre(
                        rs.getInt("obsL"),
                        rs.getString("commune"),
                        rs.getString("lieuDit"),
                        rs.getString("indice"));
                addArr.add(tb);
            }
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FileChooser fChooser = new FileChooser();
            fChooser.setTitle("Choississez un fichier");
            ExtensionFilter filter = new ExtensionFilter("Comma-separated values (CSV)", "*.csv");
            fChooser.getExtensionFilters().add(filter);
            fChooser.setSelectedExtensionFilter(filter);
            fChooser.setInitialFileName("Loutre");
            String selectedDirPath = fChooser.showSaveDialog(stage).getAbsolutePath();
            File downloadedFile = new File(selectedDirPath);
            writer = new BufferedWriter(new FileWriter(downloadedFile));
            for (Loutre ut : addArr) {
                String text = ut.getObsL() + "," + ut.getCommune() + "," + ut.getLieuDit() + "," + ut.getIndice() + "," +"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

    public void hippocampefileCSV() throws IOException, SQLException {
        Hippocampe tb = new Hippocampe(0, null, null, 0, null, 0, 0);
        ArrayList<Hippocampe> addArr = new ArrayList<Hippocampe>();
        Writer writer = null;
        try {
            ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Hippocampe");
            while (rs.next()) {
                tb = new Hippocampe(
                        rs.getInt("obsH"),
                        rs.getString("espece"),
                        rs.getString("sexe"),
                        rs.getInt("temperatureEau"),
                        rs.getString("typePeche"),
                        rs.getDouble("taille"),
                        rs.getInt("gestant"));
                addArr.add(tb);
            }
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FileChooser fChooser = new FileChooser();
            fChooser.setTitle("Choississez un fichier");
            ExtensionFilter filter = new ExtensionFilter("Comma-separated values (CSV)", "*.csv");
            fChooser.getExtensionFilters().add(filter);
            fChooser.setSelectedExtensionFilter(filter);
            fChooser.setInitialFileName("Hippocampe");
            String selectedDirPath = fChooser.showSaveDialog(stage).getAbsolutePath();
            File downloadedFile = new File(selectedDirPath);
            writer = new BufferedWriter(new FileWriter(downloadedFile));
            for (Hippocampe ut : addArr) {
                String text = ut.getObsH() + "," + ut.getEspece() + "," + ut.getSexe() + "," + ut.getTemperatureEau() + "," 
                + ut.getTypePeche() + "," + ut.getTaille() + "," + ut.getGestant() +"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

    public void chouettefileCSV() throws IOException, SQLException {
        JointureChouette tb = new JointureChouette(null, null, null, 0, null, 0);
        ArrayList<JointureChouette> addArr = new ArrayList<JointureChouette>();
        Writer writer = null;
        try {
            ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Chouette, Chouette WHERE numIndividu = leNumIndividu");
            while (rs.next()) {
                tb = new JointureChouette(
                        rs.getString("leNumIndividu"),
                        rs.getString("espece"),
                        rs.getString("sexe"),
                        rs.getInt("protocole"),
                        rs.getString("typeObs"),
                        rs.getInt("numObs"));
                addArr.add(tb);
            }
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FileChooser fChooser = new FileChooser();
            fChooser.setTitle("Choississez un fichier");
            ExtensionFilter filter = new ExtensionFilter("Comma-separated values (CSV)", "*.csv");
            fChooser.getExtensionFilters().add(filter);
            fChooser.setSelectedExtensionFilter(filter);
            fChooser.setInitialFileName("Chouette");
            String selectedDirPath = fChooser.showSaveDialog(stage).getAbsolutePath();
            File downloadedFile = new File(selectedDirPath);
            writer = new BufferedWriter(new FileWriter(downloadedFile));
            for (JointureChouette ut : addArr) {
                String text = ut.getLeNumIndividu() + "," + ut.getEspece() + "," + ut.getSexe() + "," + ut.getProtocole() + "," 
                + ut.getTypeObs() + "," + ut.getNumObs() +"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

    public void GCIfileCSV() throws IOException, SQLException {
        JointureGCI tb = new JointureGCI(0, null, null, 0, 0, null, null, 0, null, 0, 0);
        ArrayList<JointureGCI> addArr = new ArrayList<JointureGCI>();
        Writer writer = null;
        try {
            ResultSet rs = connect.executeQuery("SELECT * FROM Obs_GCI, Nid_GCI WHERE idNid = leNid");
            while (rs.next()) {
                tb = new JointureGCI(
                        rs.getInt("idNid"),
                        rs.getString("nomPlage"),
                        rs.getString("raisonArretObservation"),
                        rs.getInt("nbEnvol"),
                        rs.getInt("protection"),
                        rs.getString("bagueMale"),
                        rs.getString("bagueFemelle"),
                        rs.getInt("obsG"),
                        rs.getString("nature"),
                        rs.getInt("nombre"),
                        rs.getInt("presentMaisNonObs"));
                addArr.add(tb);
            }
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FileChooser fChooser = new FileChooser();
            fChooser.setTitle("Choississez un fichier");
            ExtensionFilter filter = new ExtensionFilter("Comma-separated values (CSV)", "*.csv");
            fChooser.getExtensionFilters().add(filter);
            fChooser.setSelectedExtensionFilter(filter);
            fChooser.setInitialFileName("GCI");
            String selectedDirPath = fChooser.showSaveDialog(stage).getAbsolutePath();
            File downloadedFile = new File(selectedDirPath);
            writer = new BufferedWriter(new FileWriter(downloadedFile));
            for (JointureGCI ut : addArr) {
                String text = ut.getIdNid() + "," + ut.getNomPlage() + "," + ut.getRaisonArretObservation() + "," + ut.getNbEnvol() + "," 
                + ut.getProtection() + "," + ut.getBagueMale() + ut.getBagueFemelle() + "," + ut.getObsG() + ","
                + ut.getNature() + ut.getNombre() + "," + ut.getPresentmainsNonObs() + "\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }
}

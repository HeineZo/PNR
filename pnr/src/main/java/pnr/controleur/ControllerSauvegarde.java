package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pnr.modele.donneeAddsOn.*;

import javafx.event.ActionEvent;

public class ControllerSauvegarde extends Controller {

    @FXML
    private Button btnBack;

    @FXML
    private Button export;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void handleBtnClick(ActionEvent event) throws Exception {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixActionAdmin.fxml", event);
        } else if (event.getSource() == export) {
            this.batracienfileCSV();
        }
    }

    public void observateurfileCSV() throws IOException, SQLException {
        TabObservateur tb = new TabObservateur(0,null, null);
        ArrayList<TabObservateur> addArr = new ArrayList<TabObservateur>();
        Writer writer = null;
        try {
            ResultSet rs = connect.executeQuery("SELECT * FROM Observateur");
            while (rs.next()) {
                tb = new TabObservateur(
                        rs.getInt("idObservateur"),
                        rs.getString("nom"),
                        rs.getString("prenom"));
                addArr.add(tb);
            }
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FileChooser fChooser = new FileChooser();
            fChooser.setTitle("Choississez un fichier");
            ExtensionFilter filter = new ExtensionFilter("Comma-separated values (CSV)", "*.csv");
            fChooser.getExtensionFilters().add(filter);
            fChooser.setSelectedExtensionFilter(filter);
            fChooser.setInitialFileName("Observateur");
            String selectedDirPath = fChooser.showSaveDialog(stage).getAbsolutePath();
            File downloadedFile = new File(selectedDirPath);
            writer = new BufferedWriter(new FileWriter(downloadedFile));
            for (TabObservateur ut : addArr) {
                String text = ut.getId() + "," + ut.getNom() + "," + ut.getPrenom() + "," + "\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

    public void batracienfileCSV() throws IOException, SQLException {
        TabBatracien tb = new TabBatracien(0, null, 0, 0, 0, 0, 0, null, null, null, null, 0, 0);
        ArrayList<TabBatracien> addArr = new ArrayList<TabBatracien>();
        Writer writer = null;
        try {
            ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Batracien");
            while (rs.next()) {
                tb = new TabBatracien(
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
            for (TabBatracien ut : addArr) {
                String text = ut.getObsB() + "," + ut.getEspece() + "," + ut.getNombreAdultes() + "," + ut.getNombreAmplexus() + ","
                        + ut.getNombrePonte() + "," + ut.getNombreTetard() + "," + ut.getTemperature()  + "," + ut.getMeteoCiel() + "," + ut.getMeteoTemp() 
                        + "," + ut.getMeteoVent() + "," + ut.getMeteoPluie() + "," + ut.getConcerneZh() + "," + ut.getConcerneVege() +"\n";
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

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
            this.hippocampefileCSV();
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
        Hippocampe tb = new Hippocampe(0, null, null, 0, null, 0, 0);
        ArrayList<Hippocampe> addArr = new ArrayList<Hippocampe>();
        Writer writer = null;
        try {
            ResultSet rs = connect.executeQuery("SELECT * FROM Obs_Chouette WHERE numIndividu = leNumIndividu");
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
            fChooser.setInitialFileName("Chouette");
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
}

package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ControllerVisualiser extends Controller implements Initializable {

    @FXML
    private PieChart pieChart;

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private ComboBox<String> comboBoxChoices;

    @FXML
    private ComboBox<String> comboBoxSpecies;

    private ObservableList<String> tlistChoices = FXCollections.observableArrayList();
    private ObservableList<String> tlistSpecies = FXCollections.observableArrayList();
    private ObservableList data = FXCollections.observableArrayList();
    private XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

    @FXML
    private Label label;

    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setVisible(true);
        pieChart.setVisible(false);
        barChart.setVisible(false);
        lineChart.setVisible(false);

        tlistChoices.add("Camembert");
        tlistChoices.add("Barres");
        tlistChoices.add("Lignes");
        comboBoxChoices.setItems(tlistChoices);

        tlistSpecies.add("Tous");
        tlistSpecies.add("Batracien");
        tlistSpecies.add("Chouette");
        tlistSpecies.add("GCI");
        tlistSpecies.add("Hippocampe");
        tlistSpecies.add("Loutre");
        comboBoxSpecies.setItems(tlistSpecies);
    }

    @FXML
    private void comboChoice(ActionEvent event) {
        comboGraph(String.valueOf(comboBoxChoices.getValue()), String.valueOf(comboBoxSpecies.getValue()));
    }

    private void comboGraph(String chart, String species) {
        if (chart != null && species != null) {
            if (chart.equals("Camembert")) {
                if (species.equals("Batracien")) {
                    try {
                        ResultSet rs = connect
                                .executeQuery("SELECT COUNT(obsB), espece FROM Obs_Batracien GROUP BY espece ");
                        while (rs.next()) {
                            data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else if (species.equals("Chouette")) {
                    try {
                        ResultSet rs = connect
                                .executeQuery("SELECT COUNT(numIndividu), espece FROM Chouette GROUP BY espece ");
                        while (rs.next()) {
                            data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else if (species.equals("Hippocampe")) {
                    try {
                        ResultSet rs = connect
                                .executeQuery("SELECT COUNT(obsH), espece FROM Obs_Hippocampe GROUP BY espece ");
                        while (rs.next()) {
                            data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else {
                    label.setVisible(true);
                }

                pieChart.getData().addAll(data);

                label.setVisible(false);
                pieChart.setVisible(true);
                barChart.setVisible(false);
                // lineChart.setVisible(false);
            } else if (chart.equals("Barres")) {
                if (species.equals("Tous")) {
                    try {
                        ResultSet rsBatracien = connect.executeQuery("SELECT COUNT(obsB) FROM Obs_Batracien ");
                        ResultSet rsChouette = connect.executeQuery("SELECT COUNT(numObs) FROM Obs_Chouette ");
                        ResultSet rsGCI = connect.executeQuery("SELECT COUNT(obsG) FROM Obs_GCI ");
                        ResultSet rsHippocampe = connect.executeQuery("SELECT COUNT(obsH) FROM Obs_Hippocampe ");
                        ResultSet rsLoutre = connect.executeQuery("SELECT COUNT(ObsL) FROM Obs_Loutre ");

                        series.getData().add(new XYChart.Data<String, Number>("Batracien", rsBatracien.getDouble(1)));
                        series.getData().add(new XYChart.Data<String, Number>("Chouette", rsChouette.getDouble(1)));
                        series.getData().add(new XYChart.Data<String, Number>("GCI", rsGCI.getDouble(1)));
                        series.getData().add(new XYChart.Data<String, Number>("Hippocampe", rsHippocampe.getDouble(1)));
                        series.getData().add(new XYChart.Data<String, Number>("Loutre", rsLoutre.getDouble(1)));

                        series.setName("nb_obs/espèce");

                        xAxis.setLabel("espèce");
                        yAxis.setLabel("observations");
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else {
                    label.setVisible(true);
                }

                barChart.getData().add(series);

                label.setVisible(false);
                pieChart.setVisible(false);
                barChart.setVisible(true);
                // lineChart.setVisible(false);
            } else if (chart.equals("Lignes")) {
                label.setVisible(false);
                pieChart.setVisible(false);
                barChart.setVisible(false);
                // lineChart.setVisible(true);
            }
        } else {
            label.setVisible(true);
        }
    }

    @FXML
    void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        }
    }
}

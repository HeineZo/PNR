package pnr.controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Time;
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

    private String eventSrc;

    @FXML
    private PieChart pieChart;

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private LineChart<Time, Double> lineChart;

    @FXML
    private ComboBox<String> comboBoxChoices;

    @FXML
    private ComboBox<String> comboBoxTypes;

    private ObservableList<String> tlistChoices = FXCollections.observableArrayList();
    private ObservableList<String> tlistTypes = FXCollections.observableArrayList();
    private ObservableList data = FXCollections.observableArrayList();
    private XYChart.Series<String, Number> seriesBar = new XYChart.Series<String, Number>();
    private XYChart.Series<Time, Double> seriesLine = new XYChart.Series<Time, Double>();

    @FXML
    private Label label;

    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.eventSrc = this.getEventSrcVisualiser();

        this.label.setVisible(true);
        this.pieChart.setVisible(false);
        this.barChart.setVisible(false);
        this.lineChart.setVisible(false);

        this.tlistChoices.add("Barres");
        this.tlistChoices.add("Camembert");
        this.tlistChoices.add("Lignes");
        this.comboBoxChoices.setItems(this.tlistChoices);
    }

    @FXML
    private void comboChoices(ActionEvent event) {
        this.comboBoxTypes.getItems().clear();

        if (String.valueOf(this.comboBoxChoices.getValue()).equals("Camembert")) {
            if (this.eventSrc.equals("Batracien")) {
                this.tlistTypes.add("Espèce");
            } else if (this.eventSrc.equals("Chouette")) {
                this.tlistTypes.add("Espèce");
            } else if (this.eventSrc.equals("GCI")) {
                this.tlistTypes.add("Plage");
            } else if (this.eventSrc.equals("Hippocampe")) {
                this.tlistTypes.add("Espèce");
            } else if (this.eventSrc.equals("Loutre")) {
                this.tlistTypes.add("Indice");
            }

            this.tlistTypes.add("Observateur");

            this.comboBoxTypes.setItems(this.tlistTypes);
        } else if (String.valueOf(this.comboBoxChoices.getValue()).equals("Barres")) {
            if (this.eventSrc.equals("Batracien")) {
                this.tlistTypes.add("Espèce");
            } else if (this.eventSrc.equals("Chouette")) {
                this.tlistTypes.add("Espèce");
            } else if (this.eventSrc.equals("GCI")) {
                this.tlistTypes.add("Plage");
            } else if (this.eventSrc.equals("Hippocampe")) {
                this.tlistTypes.add("Espèce");
            } else if (this.eventSrc.equals("Loutre")) {
                this.tlistTypes.add("Indice");
            }

            this.tlistTypes.add("Observateur");

            this.comboBoxTypes.setItems(this.tlistTypes);
        } else if (String.valueOf(this.comboBoxChoices.getValue()).equals("Lignes")) {
            if (this.eventSrc.equals("Batracien")) {
                this.tlistTypes.add("Espèce");
            } else if (this.eventSrc.equals("Chouette")) {
                this.tlistTypes.add("Espèce");
            } else if (this.eventSrc.equals("GCI")) {
                this.tlistTypes.add("Plage");
            } else if (this.eventSrc.equals("Hippocampe")) {
                this.tlistTypes.add("Espèce");
            } else if (this.eventSrc.equals("Loutre")) {
                this.tlistTypes.add("Indice");
            }

            this.tlistTypes.add("Observateur");

            this.comboBoxTypes.setItems(this.tlistTypes);
        }
    }

    @FXML
    private void comboTypes() {
        graph(String.valueOf(this.comboBoxChoices.getValue()), String.valueOf(this.comboBoxTypes.getValue()));
    }

    private void graph(String chart, String type) {
        if (chart != null) {
            if (chart.equals("Camembert")) {
                pie(String.valueOf(this.comboBoxTypes.getValue()));
            } else if (chart.equals("Barres")) {
                bar(String.valueOf(this.comboBoxTypes.getValue()));
            } else if (chart.equals("Lignes")) {
                lines(String.valueOf(this.comboBoxTypes.getValue()));
            } else {
                this.label.setVisible(true);
            }
        }
    }

    private void pie(String type) {
        if (type.equals("Espèce")) {
            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect
                            .executeQuery("SELECT COUNT(obsB), espece FROM Obs_Batracien GROUP BY espece ");
                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect
                            .executeQuery("SELECT COUNT(numIndividu), espece FROM Chouette GROUP BY espece ");
                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect
                            .executeQuery("SELECT COUNT(obsH), espece FROM Obs_Hippocampe GROUP BY espece ");
                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                this.label.setVisible(true);
            }
        } else if (type.equals("Observateur")) {
            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(obsB), nom FROM Obs_Batracien JOIN AObserve ON obsB = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");
                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(numIndividu), nom FROM Chouette JOIN AObserve ON numIndividu = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");
                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("GCI")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(obsG), nom FROM Obs_GCI JOIN AObserve ON obsG = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");
                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(obsH), nom FROM Obs_Hippocampe JOIN AObserve ON obsH = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");
                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Loutre")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(obsL), nom FROM Obs_Loutre JOIN AObserve ON obsL = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");
                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                this.label.setVisible(true);
            }
        }

        this.pieChart.getData().addAll(this.data);

        this.label.setVisible(false);
        this.pieChart.setVisible(true);
        this.barChart.setVisible(false);
        this.lineChart.setVisible(false);
    }

    private void bar(String type) {
        if (type.equals("Date")) {
            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rsBatracien = connect.executeQuery(
                            "SELECT COUNT(obsB), dateObs FROM Obs_Batracien JOIN AObserve ON lobservation = obsB JOIN Observation ON idObs = lobservation ");

                    this.seriesBar.getData()
                            .add(new XYChart.Data<String, Number>("Batracien", rsBatracien.getDouble(1)));

                    this.seriesBar.setName("nb_obs/batracien");

                    xAxis.setLabel("espèce");
                    yAxis.setLabel("observations");
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                this.label.setVisible(true);
            }
        }

        this.barChart.getData().add(this.seriesBar);

        this.label.setVisible(false);
        this.pieChart.setVisible(false);
        this.barChart.setVisible(true);
        this.lineChart.setVisible(false);
    }

    private void lines(String type) {
        if (type.equals("Heure")) {
            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT heureObs, COUNT(obsB) FROM Obs_Batracien JOIN Observation ON idObs = obsB GROUP BY heureObs ");

                    this.seriesLine.getData()
                            .add(new XYChart.Data<Time, Double>(rs.getTime(1), rs.getDouble(2)));

                    this.seriesLine.setName("heure/temps");

                    xAxis.setLabel("heure");
                    yAxis.setLabel("no_obs");
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                this.label.setVisible(true);
            }
        }

        this.lineChart.getData().add(this.seriesLine);

        this.label.setVisible(false);
        this.pieChart.setVisible(false);
        this.barChart.setVisible(false);
        this.lineChart.setVisible(true);
    }

    @FXML
    void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        }
    }
}

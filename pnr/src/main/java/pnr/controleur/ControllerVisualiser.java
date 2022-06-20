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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerVisualiser extends Controller implements Initializable {

    private String eventSrc;

    @FXML
    private ImageView imgEspece;

    @FXML
    private PieChart pieChart;

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

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

        imgIcn();

        this.label.setVisible(true);
        this.pieChart.setVisible(false);
        this.barChart.setVisible(false);
        this.lineChart.setVisible(false);

        this.tlistChoices.add("Barres");
        this.tlistChoices.add("Camembert");
        this.tlistChoices.add("Lignes");
        this.comboBoxChoices.setItems(this.tlistChoices);
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

    @FXML
    private void comboChoices(ActionEvent event) {
        this.comboBoxTypes.getItems().clear();

        String choice = String.valueOf(this.comboBoxChoices.getValue());

        if (choice.equals("Camembert")) {
            if (this.eventSrc.equals("Batracien")) {
                this.tlistTypes.add("Espèce");
            } else if (this.eventSrc.equals("Chouette")) {
                this.tlistTypes.add("Espèce");
                this.tlistTypes.add("TypeObs");
                this.tlistTypes.add("Sexe");
            } else if (this.eventSrc.equals("GCI")) {
                this.tlistTypes.add("Plage");
                this.tlistTypes.add("RaisonArrêtObs");
                this.tlistTypes.add("Nature");
            } else if (this.eventSrc.equals("Hippocampe")) {
                this.tlistTypes.add("Espèce");
                this.tlistTypes.add("Sexe");
            } else if (this.eventSrc.equals("Loutre")) {
                this.tlistTypes.add("Commune");
            }

            this.tlistTypes.add("Observateur");
        } else if (choice.equals("Barres")) {
            this.tlistTypes.add("Date");
            this.tlistTypes.add("Heure");
        } else if (choice.equals("Lignes")) {
            this.tlistTypes.add("Heure");
        }

        this.comboBoxTypes.setItems(this.tlistTypes);
    }

    @FXML
    private void comboTypes() {
        graph(String.valueOf(this.comboBoxChoices.getValue()), String.valueOf(this.comboBoxTypes.getValue()));
    }

    private void graph(String chart, String type) {
        if (chart.equals("Camembert")) {
            pie(type);
        } else if (chart.equals("Barres")) {
            bar(type);
        } else if (chart.equals("Lignes")) {
            lines(type);
        } else {
            this.label.setVisible(true);
        }
    }

    private void pie(String type) {
        this.pieChart.getData().clear();

        if (type.equals("Espèce")) {
            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect
                            .executeQuery("SELECT COUNT(*), espece FROM Obs_Batracien GROUP BY espece ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect
                            .executeQuery("SELECT COUNT(*), espece FROM Chouette GROUP BY espece ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect
                            .executeQuery("SELECT COUNT(*), espece FROM Obs_Hippocampe GROUP BY espece ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("Observateur")) {
            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), nom FROM Obs_Batracien JOIN AObserve ON obsB = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), nom FROM Obs_Chouette JOIN AObserve ON numObs = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("GCI")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), nom FROM Obs_GCI JOIN AObserve ON obsG = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), nom FROM Obs_Hippocampe JOIN AObserve ON obsH = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Loutre")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), nom FROM Obs_Loutre JOIN AObserve ON obsL = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("Plage")) {
            if (this.eventSrc.equals("GCI")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), nomPlage FROM Nid_GCI GROUP BY nomPlage ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("TypeObs")) {
            if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), typeObs FROM Obs_Chouette GROUP BY typeObs ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("Commune")) {
            if (this.eventSrc.equals("Loutre")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), commune FROM Obs_Chouette GROUP BY commune ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("Sexe")) {
            if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), sexe FROM Chouette GROUP BY sexe ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("GCI")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), bagueMale, bagueFemelle FROM Obs_GCI GROUP BY bagueMale, bagueFemelle ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), sexe FROM Obs_Hippocampe GROUP BY sexe ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("RaisonArrêtObs")) {
            if (this.eventSrc.equals("GCI")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), raisonArretObservation FROM Nid_GCI GROUP BY raisonArretObservation ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("Nature")) {
            if (this.eventSrc.equals("GCI")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), nature FROM Obs_GCI GROUP BY nature ");

                    while (rs.next()) {
                        this.data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }

        this.pieChart.setData(this.data);

        this.label.setVisible(false);
        this.pieChart.setVisible(true);
        this.barChart.setVisible(false);
        this.lineChart.setVisible(false);
    }

    private void bar(String type) {
        this.barChart.getData().clear();

        if (type.equals("Date")) {
            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), dateObs FROM Obs_Batracien JOIN AObserve ON lobservation = obsB JOIN Observation ON idObs = lobservation GROUP BY dateObs ");

                    while (rs.next()) {
                        if (rs.getString(2) != null) {
                            this.seriesBar.getData().add(new XYChart.Data<String, Number>(rs.getString(2),
                                    rs.getDouble(1)));
                        } else {
                            this.seriesBar.getData().add(
                                    new XYChart.Data<String, Number>("date inconnue", rs.getDouble(1)));
                        }
                    }

                    this.seriesBar.setName("nb_obs/batracien");

                    xAxis.setLabel("espèce");
                    yAxis.setLabel("observations");
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                this.label.setVisible(true);
            }
        } else if (type.equals("Heure")) {
            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), heureObs FROM Obs_Batracien JOIN AObserve ON lobservation = obsB JOIN Observation ON idObs = lobservation GROUP BY heureObs ");

                    while (rs.next()) {
                        if (rs.getString(2) != null) {
                            this.seriesBar.getData().add(new XYChart.Data<String, Number>(rs.getString(2),
                                    rs.getDouble(1)));
                        } else {
                            this.seriesBar.getData().add(
                                    new XYChart.Data<String, Number>("heure inconnue", rs.getDouble(1)));
                        }
                    }

                    this.seriesLine.setName("heure/temps");

                    xAxis.setLabel("heure");
                    yAxis.setLabel("no_obs");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }

        ObservableList add = FXCollections.observableArrayList(this.seriesBar);

        this.barChart.setData(add);

        this.label.setVisible(false);
        this.pieChart.setVisible(false);
        this.barChart.setVisible(true);
        this.lineChart.setVisible(false);
    }

    private void lines(String type) {
        this.lineChart.getData().clear();

        if (type.equals("Heure")) {
            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(obsB), heureObs FROM Obs_Batracien JOIN AObserve ON lobservation = obsB JOIN Observation ON idObs = lobservation GROUP BY heureObs ");

                    while (rs.next()) {
                        if (rs.getString(2) != null) {
                            this.seriesBar.getData().add(new XYChart.Data<String, Number>(rs.getString(2),
                                    rs.getDouble(1)));
                        } else {
                            this.seriesBar.getData().add(
                                    new XYChart.Data<String, Number>("heure inconnue", rs.getDouble(1)));
                        }
                    }

                    this.seriesLine.setName("heure/temps");

                    xAxis.setLabel("heure");
                    yAxis.setLabel("no_obs");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }

        ObservableList add = FXCollections.observableArrayList(this.seriesLine);

        this.lineChart.setData(add);

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

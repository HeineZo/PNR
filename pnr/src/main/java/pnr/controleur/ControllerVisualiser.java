package pnr.controleur;

import pnr.modele.util.*;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ControllerVisualiser extends Controller implements Initializable {

    private String eventSrc;

    @FXML
    private Pane pMap;

    private MapView mapView;

    @FXML
    private ImageView imgEspece;

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private ComboBox<String> comboBoxChoices;

    @FXML
    private ComboBox<String> comboBoxTypes;

    private ObservableList<String> tlistChoices = FXCollections.observableArrayList();
    private ObservableList<String> tlistTypes = FXCollections.observableArrayList();
    private ObservableList dataPie = FXCollections.observableArrayList();
    private ObservableList dataBar = FXCollections.observableArrayList();
    private ObservableList dataLine = FXCollections.observableArrayList();
    private XYChart.Series<String, Number> seriesBar = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesLine = new XYChart.Series<String, Number>();

    @FXML
    private Label label;

    @FXML
    private Button bddView;

    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.eventSrc = this.getEventSrcVisualiser();

        imgIcn();

        this.label.setVisible(true);
        this.pMap.setVisible(false);
        this.pieChart.setVisible(false);
        this.barChart.setVisible(false);
        this.lineChart.setVisible(false);

        this.tlistChoices.add("Barres");
        this.tlistChoices.add("Camembert");
        this.tlistChoices.add("Lignes");
        this.tlistChoices.add("Position");
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

        this.label.setVisible(true);
        this.pMap.setVisible(false);
        this.pieChart.setVisible(false);
        this.barChart.setVisible(false);
        this.lineChart.setVisible(false);

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
            if (this.eventSrc.equals("Batracien")) {
                //
            } else if (this.eventSrc.equals("Chouette")) {
                //
            } else if (this.eventSrc.equals("GCI")) {
                //
            } else if (this.eventSrc.equals("Hippocampe")) {
                this.tlistTypes.add("Sexe/Espece");
            } else if (this.eventSrc.equals("Loutre")) {
                //
            }
        } else if (choice.equals("Lignes")) {
            if (this.eventSrc.equals("Batracien")) {
                //
            } else if (this.eventSrc.equals("Chouette")) {
                //
            } else if (this.eventSrc.equals("GCI")) {
                //
            } else if (this.eventSrc.equals("Hippocampe")) {
                this.tlistTypes.add("Température");
            } else if (this.eventSrc.equals("Loutre")) {
                //
            }

            this.tlistTypes.add("Date");
            this.tlistTypes.add("Heure");
        } else if (choice.equals("Position")) {
            this.tlistTypes.add("Carte");
            this.tlistTypes.add("Graphe");
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
        } else if (chart.equals("Position")) {
            position(type);
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
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect
                            .executeQuery("SELECT COUNT(*), espece FROM Chouette GROUP BY espece ");

                    while (rs.next()) {
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect
                            .executeQuery("SELECT COUNT(*), espece FROM Obs_Hippocampe GROUP BY espece ");

                    while (rs.next()) {
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
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
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), nom FROM Obs_Chouette JOIN Observation ON idObs = numObs JOIN AObserve ON lobservation = idObs JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");

                    while (rs.next()) {
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("GCI")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), nom FROM Obs_GCI JOIN AObserve ON obsG = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");

                    while (rs.next()) {
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), nom FROM Obs_Hippocampe JOIN AObserve ON obsH = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");

                    while (rs.next()) {
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Loutre")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), nom FROM Obs_Loutre JOIN AObserve ON obsL = lobservation JOIN Observateur ON idObservateur = lobservateur GROUP BY nom ");

                    while (rs.next()) {
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
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
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
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
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("Commune")) {
            if (this.eventSrc.equals("Loutre")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), commune FROM Obs_Loutre GROUP BY commune ");

                    while (rs.next()) {
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
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
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("GCI")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), bagueMale, bagueFemelle FROM Obs_GCI GROUP BY bagueMale, bagueFemelle ");

                    while (rs.next()) {
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), sexe FROM Obs_Hippocampe GROUP BY sexe ");

                    while (rs.next()) {
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
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
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
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
                        this.dataPie.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }

        this.pieChart.setData(this.dataPie);

        this.label.setVisible(false);
        this.pMap.setVisible(false);
        this.pieChart.setVisible(true);
        this.barChart.setVisible(false);
        this.lineChart.setVisible(false);
    }

    private void bar(String type) {
        this.barChart.getData().clear();

        if (type.equals("Sexe/Espece")) {
            if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), sexe, espece FROM Obs_Hippocampe JOIN Observation ON idObs = obsH GROUP BY sexe, espece ");

                    while (rs.next()) {
                        if (rs.getString(2) != null) {
                            this.seriesBar.getData().add(new XYChart.Data<String, Number>(
                                    rs.getString(2) + " " + rs.getString(3), rs.getDouble(1)));
                        } else {
                            this.seriesBar.getData().add(
                                    new XYChart.Data<String, Number>("date inconnue", rs.getDouble(1)));
                        }
                    }

                    this.seriesBar.setName("nb_obs/batracien");
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                this.label.setVisible(true);
            }
        }

        this.dataBar.add(this.seriesBar);
        this.barChart.setData(this.dataBar);

        this.label.setVisible(false);
        this.pMap.setVisible(false);
        this.pieChart.setVisible(false);
        this.barChart.setVisible(true);
        this.lineChart.setVisible(false);
    }

    private void lines(String type) {
        this.lineChart.getData().clear();

        if (type.equals("Température")) {
            if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT temperatureEau, heureObs FROM Obs_Hippocampe JOIN Observation ON idObs = obsH GROUP BY temperatureEau, heureObs ");

                    while (rs.next()) {
                        if (rs.getString(2) != null) {
                            this.seriesLine.getData().add(new XYChart.Data<String, Number>(rs.getString(2),
                                    rs.getDouble(1)));
                        } else {
                            this.seriesLine.getData().add(
                                    new XYChart.Data<String, Number>("heure inconnue", rs.getDouble(1)));
                        }
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("Date")) {
            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(obsB), dateObs FROM Obs_Batracien JOIN Observation ON idObs = obsB GROUP BY dateObs ");

                    while (rs.next()) {
                        if (rs.getString(2) != null) {
                            this.seriesLine.getData().add(new XYChart.Data<String, Number>(rs.getString(2),
                                    rs.getDouble(1)));
                        } else {
                            this.seriesLine.getData().add(
                                    new XYChart.Data<String, Number>("heure inconnue", rs.getDouble(1)));
                        }
                    }

                    this.seriesLine.setName("heure/temps");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("Heure")) {
            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), heureObs FROM Obs_Batracien JOIN Observation ON idObs = obsB GROUP BY heureObs ");

                    while (rs.next()) {
                        if (rs.getString(2) != null) {
                            this.seriesLine.getData().add(new XYChart.Data<String, Number>(rs.getString(2),
                                    rs.getDouble(1)));
                        } else {
                            this.seriesLine.getData().add(
                                    new XYChart.Data<String, Number>("heure inconnue", rs.getDouble(1)));
                        }
                    }

                    this.seriesLine.setName("heure/temps");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else {
            this.label.setVisible(true);
        }

        this.dataLine.add(this.seriesLine);
        this.lineChart.setData(this.dataLine);

        this.label.setVisible(false);
        this.pMap.setVisible(false);
        this.pieChart.setVisible(false);
        this.barChart.setVisible(false);
        this.lineChart.setVisible(true);
    }

    private void position(String type) {
        if (type.equals("Carte")) {
            System.setProperty("javafx.platform", "desktop");
            System.setProperty("http.agent", "Gluon Maps/2.0.0");

            this.mapView = new MapView();
            this.mapView.setDisable(true);
            // MapPoint mpMorbihan = new MapPoint(47.227638, -2.213749);
            MapPoint mpMorbihan = new MapPoint(60.227638, -40.213749);
            this.mapView.setZoom(5);
            // this.mapView.setCenter(mpMorbihan);
            this.mapView.flyTo(0, mpMorbihan, 0.1);

            ArrayList<MapPoint> mpArray = new ArrayList<MapPoint>();

            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT lieu_Lambert_X, lieu_Lambert_y FROM Observation JOIN Obs_Batracien On obsB = idObs ");

                    while (rs.next()) {
                        if (rs.getDouble(1) != 0 && rs.getDouble(2) != 0 && rs.getString(1) != null
                                && rs.getString(2) != null) {
                            double[] save = lambert93toWGS84(rs.getDouble(1), rs.getDouble(2));
                            // System.out.println(save[0] + " " + save[1]);
                            MapPoint mapPoint = new MapPoint(save[0], save[1]);
                            mpArray.add(mapPoint);

                            MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint);
                            this.mapView.addLayer(mapLayer);
                        }
                    }

                    // PoiLayer poi = new PoiLayer();

                    // for (MapPoint mapPoint : mpArray) {
                    // poi.addPoint(mapPoint, new Circle(5, Color.BLUE));
                    // }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT lieu_Lambert_X, lieu_Lambert_y FROM Observation JOIN Chouette On numIndividu = idObs ");

                    while (rs.next()) {
                        if (rs.getDouble(1) != 0 && rs.getDouble(2) != 0 && rs.getString(1) != null
                                && rs.getString(2) != null) {
                            double[] save = lambert93toWGS84(rs.getDouble(1), rs.getDouble(2));
                            // System.out.println(save[0] + " " + save[1]);
                            MapPoint mapPoint = new MapPoint(save[0], save[1]);
                            mpArray.add(mapPoint);

                            MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint);
                            this.mapView.addLayer(mapLayer);
                        }
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("GCI")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT lieu_Lambert_X, lieu_Lambert_y FROM Observation JOIN Obs_GCI On obsG = idObs ");

                    while (rs.next()) {
                        if (rs.getDouble(1) != 0 && rs.getDouble(2) != 0 && rs.getString(1) != null
                                && rs.getString(2) != null) {
                            double[] save = lambert93toWGS84(rs.getDouble(1), rs.getDouble(2));
                            // System.out.println(save[0] + " " + save[1]);
                            MapPoint mapPoint = new MapPoint(save[0], save[1]);
                            mpArray.add(mapPoint);

                            MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint);
                            this.mapView.addLayer(mapLayer);
                        }
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT lieu_Lambert_X, lieu_Lambert_y FROM Observation JOIN Obs_Hippocampe On obsH = idObs ");

                    while (rs.next()) {
                        if (rs.getDouble(1) != 0 && rs.getDouble(2) != 0 && rs.getString(1) != null
                                && rs.getString(2) != null) {
                            double[] save = lambert93toWGS84(rs.getDouble(1), rs.getDouble(2));
                            // System.out.println(save[0] + " " + save[1]);
                            MapPoint mapPoint = new MapPoint(save[0], save[1]);
                            mpArray.add(mapPoint);

                            MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint);
                            this.mapView.addLayer(mapLayer);
                        }
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Loutre")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT lieu_Lambert_X, lieu_Lambert_y FROM Observation JOIN Obs_Loutre On obsL = idObs ");

                    while (rs.next()) {
                        if (rs.getDouble(1) != 0 && rs.getDouble(2) != 0 && rs.getString(1) != null
                                && rs.getString(2) != null) {
                            double[] save = lambert93toWGS84(rs.getDouble(1), rs.getDouble(2));
                            // System.out.println(save[0] + " " + save[1]);
                            MapPoint mapPoint = new MapPoint(save[0], save[1]);
                            mpArray.add(mapPoint);

                            MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint);
                            this.mapView.addLayer(mapLayer);
                        }
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }

            this.pMap.getChildren().add(this.mapView);

            this.label.setVisible(false);
            this.pMap.setVisible(true);
        } else if (type.equals("Graphe")) {
            if (this.eventSrc.equals("Batracien")) {
                //
            } else if (this.eventSrc.equals("Chouette")) {
                //
            } else if (this.eventSrc.equals("GCI")) {
                //
            } else if (this.eventSrc.equals("Hippocampe")) {
                //
            } else if (this.eventSrc.equals("Loutre")) {
                //
            }
        }
    }

    double[] lambert93toWGS84(double lambertE, double lambertN) {
        double GRS80E = (float) 0.081819191042816;
        double LONG_0 = 3;
        double XS = 700000;
        double YS = (float) 12655612.0499;
        double N = (float) 0.7256077650532670;
        double C = (float) 11754255.4261;

        double delX = lambertE - XS;
        double delY = lambertN - YS;
        double gamma = Math.atan(-delX / delY);
        double R = Math.sqrt(delX * delX + delY * delY);
        double latiso = Math.log(C / R) / N;

        double sinPhiit0 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * Math.sin(1)));
        double sinPhiit1 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit0));
        double sinPhiit2 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit1));
        double sinPhiit3 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit2));
        double sinPhiit4 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit3));
        double sinPhiit5 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit4));
        double sinPhiit6 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit5));

        double longRad = Math.asin(sinPhiit6);
        double latRad = gamma / N + LONG_0 / 180 * Math.PI;

        double longitude = latRad / Math.PI * 180;
        double latitude = longRad / Math.PI * 180;

        double[] values = { latitude, longitude };
        return (values);
    }

    @FXML
    void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        } else if (event.getSource() == bddView) {
            loadStage("../vue/VisualiserTables.fxml", event);
        }
    }
}

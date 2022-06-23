package pnr.controleur;

import pnr.modele.util.*;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
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

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private BarChart<String, Number> barChart = new BarChart(xAxis, yAxis);

    @FXML
    private ComboBox<String> comboBoxChoices;

    @FXML
    private ComboBox<String> comboBoxTypes;

    private ObservableList<String> tlistChoices = FXCollections.observableArrayList();
    private ObservableList<String> tlistTypes = FXCollections.observableArrayList();
    private ObservableList dataPie = FXCollections.observableArrayList();
    private ObservableList dataBar = FXCollections.observableArrayList();
    private XYChart.Series<String, Number> seriesBar = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesBar2 = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesBar3 = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesBar4 = new XYChart.Series<String, Number>();

    @FXML
    private Label label;

    @FXML
    private MFXButton bddView;

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

        this.tlistChoices.add("Barres");
        this.tlistChoices.add("Camembert");
        this.tlistChoices.add("Position");
        this.comboBoxChoices.setItems(this.tlistChoices);

        this.comboBoxTypes.setDisable(true);

        this.pieChart.setAnimated(false);
        this.barChart.setAnimated(false);
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
                this.tlistTypes.add("Aucun graphique n'est disponible pour le moment");
            } else if (this.eventSrc.equals("Chouette")) {
                this.tlistTypes.add("Sexe/Espece");
                this.tlistTypes.add("TypeObs/Espece");
            } else if (this.eventSrc.equals("GCI")) {
                this.tlistTypes.add("NbOeufs/Nid");
                this.tlistTypes.add("NbEnvols/Plage");
            } else if (this.eventSrc.equals("Hippocampe")) {
                this.tlistTypes.add("Sexe/Espece");
                this.tlistTypes.add("TypePeche/Espece");
            } else if (this.eventSrc.equals("Loutre")) {
                this.tlistTypes.add("Aucun graphique n'est disponible pour le moment");
            }
        } else if (choice.equals("Position")) {
            this.tlistTypes.add("Carte");
        }

        this.comboBoxTypes.setItems(this.tlistTypes);
        this.comboBoxTypes.setDisable(false);
    }

    @FXML
    private void comboTypes() {
        if (!this.comboBoxTypes.getValue().equals("Aucun graphique n'est disponible pour le moment")
                && this.comboBoxTypes.getValue() != null) {
            graph(this.comboBoxChoices.getValue(), this.comboBoxTypes.getValue());
        }
    }

    private void graph(String chart, String type) {
        if (chart.equals("Camembert")) {
            this.pieChart.getData().clear();

            pie(type);

            this.pieChart.setData(this.dataPie);

            this.label.setVisible(false);
            this.pMap.setVisible(false);
            this.pieChart.setVisible(true);
            this.barChart.setVisible(false);
        } else if (chart.equals("Barres")) {
            this.barChart.getData().clear();
            this.seriesBar.getData().clear();
            this.seriesBar2.getData().clear();
            this.seriesBar3.getData().clear();
            this.seriesBar4.getData().clear();

            bar(type);

            this.barChart.setData(this.dataBar);

            this.label.setVisible(false);
            this.pMap.setVisible(false);
            this.pieChart.setVisible(false);
            this.barChart.setVisible(true);
        } else if (chart.equals("Position")) {
            // this.pMap.setDisable(true);
            // this.pMap.setMouseTransparent(true);

            position(type);

            this.pMap.getChildren().add(this.mapView);

            this.label.setVisible(false);
            this.pMap.setVisible(true);
            this.pieChart.setVisible(false);
            this.barChart.setVisible(false);
        } else {
            this.label.setVisible(true);
            this.comboBoxTypes.setDisable(true);
        }
    }

    private void pie(String type) {
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
    }

    private void bar(String type) {
        if (type.equals("Sexe/Espece")) {
            if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), sexe, espece FROM Chouette WHERE sexe = 'male' GROUP BY sexe, espece ");

                    while (rs.next()) {
                        if (rs.getString(3) != null) {
                            this.seriesBar.getData()
                                    .add(new XYChart.Data<String, Number>(rs.getString(3), rs.getDouble(1)));
                        } else {
                            this.seriesBar.getData().add(
                                    new XYChart.Data<String, Number>("espece inconnue", rs.getDouble(1)));
                        }
                    }

                    ResultSet rs2 = connect.executeQuery(
                            "SELECT COUNT(*), sexe, espece FROM Chouette WHERE sexe = 'femelle' GROUP BY sexe, espece ");

                    while (rs2.next()) {
                        if (rs2.getString(3) != null) {
                            this.seriesBar2.getData()
                                    .add(new XYChart.Data<String, Number>(rs2.getString(3), rs2.getDouble(1)));
                        } else {
                            this.seriesBar2.getData().add(
                                    new XYChart.Data<String, Number>("espece inconnue", rs2.getDouble(1)));
                        }
                    }

                    ResultSet rs3 = connect.executeQuery(
                            "SELECT COUNT(*), sexe, espece FROM Chouette WHERE sexe = 'inconnu' GROUP BY sexe, espece ");

                    while (rs3.next()) {
                        if (rs3.getString(3) != null) {
                            this.seriesBar3.getData()
                                    .add(new XYChart.Data<String, Number>(rs3.getString(3), rs3.getDouble(1)));
                        } else {
                            this.seriesBar3.getData().add(
                                    new XYChart.Data<String, Number>("espece inconnue", rs3.getDouble(1)));
                        }
                    }

                    this.dataBar.addAll(this.seriesBar, this.seriesBar2, this.seriesBar3);

                    this.seriesBar.setName("male");
                    this.seriesBar2.setName("femelle");
                    this.seriesBar3.setName("inconnu");

                    this.xAxis.setLabel("espece");
                    this.yAxis.setLabel("count");
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), sexe, espece FROM Obs_Hippocampe WHERE sexe = 'male' GROUP BY sexe, espece ");

                    while (rs.next()) {
                        if (rs.getString(3) != null) {
                            this.seriesBar.getData()
                                    .add(new XYChart.Data<String, Number>(rs.getString(3), rs.getDouble(1)));
                        } else {
                            this.seriesBar.getData().add(
                                    new XYChart.Data<String, Number>("espece inconnue", rs.getDouble(1)));
                        }
                    }

                    ResultSet rs2 = connect.executeQuery(
                            "SELECT COUNT(*), sexe, espece FROM Obs_Hippocampe WHERE sexe = 'femelle' GROUP BY sexe, espece ");

                    while (rs2.next()) {
                        if (rs2.getString(3) != null) {
                            this.seriesBar2.getData()
                                    .add(new XYChart.Data<String, Number>(rs2.getString(3), rs2.getDouble(1)));
                        } else {
                            this.seriesBar2.getData().add(
                                    new XYChart.Data<String, Number>("espece inconnue", rs2.getDouble(1)));
                        }
                    }

                    ResultSet rs3 = connect.executeQuery(
                            "SELECT COUNT(*), sexe, espece FROM Obs_Hippocampe WHERE sexe = 'inconnu' GROUP BY sexe, espece ");

                    while (rs3.next()) {
                        if (rs3.getString(3) != null) {
                            this.seriesBar3.getData()
                                    .add(new XYChart.Data<String, Number>(rs3.getString(3), rs3.getDouble(1)));
                        } else {
                            this.seriesBar3.getData().add(
                                    new XYChart.Data<String, Number>("espece inconnue", rs3.getDouble(1)));
                        }
                    }

                    this.dataBar.addAll(this.seriesBar, this.seriesBar2, this.seriesBar3);

                    this.seriesBar.setName("male");
                    this.seriesBar2.setName("femelle");
                    this.seriesBar3.setName("inconnu");

                    this.xAxis.setLabel("espece");
                    this.yAxis.setLabel("count");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("TypeObs/Espece")) {
            if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), typeObs, espece FROM Obs_Chouette JOIN Chouette ON numIndividu = leNumIndividu WHERE typeObs = 'Sonore' GROUP BY typeObs, espece ");

                    while (rs.next()) {
                        if (rs.getString(3) != null) {
                            this.seriesBar.getData()
                                    .add(new XYChart.Data<String, Number>(rs.getString(3), rs.getDouble(1)));
                        } else {
                            this.seriesBar.getData().add(
                                    new XYChart.Data<String, Number>("espece inconnue", rs.getDouble(1)));
                        }
                    }

                    ResultSet rs2 = connect.executeQuery(
                            "SELECT COUNT(*), typeObs, espece FROM Obs_Chouette JOIN Chouette ON numIndividu = leNumIndividu WHERE typeObs = 'Visuel' GROUP BY typeObs, espece ");

                    while (rs2.next()) {
                        if (rs2.getString(3) != null) {
                            this.seriesBar2.getData()
                                    .add(new XYChart.Data<String, Number>(rs2.getString(3), rs2.getDouble(1)));
                        } else {
                            this.seriesBar2.getData().add(
                                    new XYChart.Data<String, Number>("espece inconnue", rs2.getDouble(1)));
                        }
                    }

                    ResultSet rs3 = connect.executeQuery(
                            "SELECT COUNT(*), typeObs, espece FROM Obs_Chouette JOIN Chouette ON numIndividu = leNumIndividu WHERE typeObs = 'Sonore et Visuel' GROUP BY typeObs, espece ");

                    while (rs3.next()) {
                        if (rs3.getString(3) != null) {
                            this.seriesBar3.getData()
                                    .add(new XYChart.Data<String, Number>(rs3.getString(3), rs3.getDouble(1)));
                        } else {
                            this.seriesBar3.getData().add(
                                    new XYChart.Data<String, Number>("espece inconnue", rs3.getDouble(1)));
                        }
                    }

                    this.dataBar.addAll(this.seriesBar, this.seriesBar2, this.seriesBar3);

                    this.seriesBar.setName("Sonore");
                    this.seriesBar2.setName("Visuel");
                    this.seriesBar3.setName("Sonore et Visuel");

                    this.xAxis.setLabel("espece");
                    this.yAxis.setLabel("count");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("NbOeufs/Nid")) {
            if (this.eventSrc.equals("GCI")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT SUM(nombre), leNid FROM Obs_GCI GROUP BY leNid ");

                    while (rs.next()) {
                        this.seriesBar.getData()
                                .add(new XYChart.Data<String, Number>(rs.getString(2), rs.getDouble(1)));
                    }

                    this.dataBar.add(this.seriesBar);

                    this.seriesBar.setName("idNid");

                    this.xAxis.setLabel("idNid");
                    this.yAxis.setLabel("nbOeufs");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("NbEnvols/Plage")) {
            if (this.eventSrc.equals("GCI")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT SUM(nbEnvol), nomPlage FROM Nid_GCI GROUP BY nomPlage ORDER BY nomPlage ");

                    while (rs.next()) {
                        this.seriesBar.getData()
                                .add(new XYChart.Data<String, Number>(rs.getString(2), rs.getDouble(1)));
                    }

                    this.dataBar.add(this.seriesBar);

                    this.seriesBar.setName("nom plage");

                    this.xAxis.setLabel("idNid");
                    this.yAxis.setLabel("nbEnvols");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } else if (type.equals("TypePeche/Espece")) {
            if (this.eventSrc.equals("Hippocampe")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT COUNT(*), typePeche, espece FROM Obs_Hippocampe WHERE typePeche = 'casierCrevettes' GROUP BY typePeche, espece ");

                    while (rs.next()) {
                        if (rs.getString(3) != null) {
                            this.seriesBar.getData()
                                    .add(new XYChart.Data<String, Number>(rs.getString(3), rs.getDouble(1)));
                        } else {
                            this.seriesBar.getData().add(
                                    new XYChart.Data<String, Number>("typePeche inconnu", rs.getDouble(1)));
                        }
                    }

                    ResultSet rs2 = connect.executeQuery(
                            "SELECT COUNT(*), typePeche, espece FROM Obs_Hippocampe WHERE typePeche = 'casierMorgates' GROUP BY typePeche, espece ");

                    while (rs2.next()) {
                        if (rs2.getString(3) != null) {
                            this.seriesBar2.getData()
                                    .add(new XYChart.Data<String, Number>(rs2.getString(3), rs2.getDouble(1)));
                        } else {
                            this.seriesBar2.getData().add(
                                    new XYChart.Data<String, Number>("typePeche inconnu", rs2.getDouble(1)));
                        }
                    }

                    ResultSet rs3 = connect.executeQuery(
                            "SELECT COUNT(*), typePeche, espece FROM Obs_Hippocampe WHERE typePeche = 'verveuxAnguilles' GROUP BY typePeche, espece ");

                    while (rs3.next()) {
                        if (rs3.getString(3) != null) {
                            this.seriesBar3.getData()
                                    .add(new XYChart.Data<String, Number>(rs3.getString(3), rs3.getDouble(1)));
                        } else {
                            this.seriesBar3.getData().add(
                                    new XYChart.Data<String, Number>("typePeche inconnu", rs3.getDouble(1)));
                        }
                    }

                    ResultSet rs4 = connect.executeQuery(
                            "SELECT COUNT(*), typePeche, espece FROM Obs_Hippocampe WHERE typePeche = 'petitFilet' GROUP BY typePeche, espece ");

                    while (rs4.next()) {
                        if (rs4.getString(3) != null) {
                            this.seriesBar4.getData()
                                    .add(new XYChart.Data<String, Number>(rs4.getString(3), rs4.getDouble(1)));
                        } else {
                            this.seriesBar4.getData().add(
                                    new XYChart.Data<String, Number>("espece inconnue", rs4.getDouble(1)));
                        }
                    }

                    this.dataBar.addAll(this.seriesBar, this.seriesBar2, this.seriesBar3, this.seriesBar4);

                    this.seriesBar.setName("casier crevettes");
                    this.seriesBar2.setName("casier morgates");
                    this.seriesBar3.setName("verveux anguilles");
                    this.seriesBar4.setName("petit filet");

                    this.xAxis.setLabel("espece");
                    this.yAxis.setLabel("type peche");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
    }

    private void position(String type) {
        if (type.equals("Carte")) {
            System.setProperty("javafx.platform", "desktop");
            System.setProperty("http.agent", "Gluon Maps/2.0.0");

            this.mapView = new MapView();
            this.mapView.setMaxSize(1920, 1080);
            MapPoint mpMorbihan = new MapPoint(47.541935030262756, -2.871214650208961);
            this.mapView.setZoom(10.5);
            this.mapView.flyTo(0, mpMorbihan, 2);

            ArrayList<MapPoint> mpArray = new ArrayList<MapPoint>();

            if (this.eventSrc.equals("Batracien")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT lieu_Lambert_X, lieu_Lambert_y FROM Observation JOIN Obs_Batracien On obsB = idObs ");

                    while (rs.next()) {
                        if (rs.getDouble(1) != 0. && rs.getDouble(2) != 0. && rs.getString(1) != null
                                && rs.getString(2) != null) {
                            double[] save = lambert93toWGS84(rs.getDouble(1), rs.getDouble(2));
                            MapPoint mapPoint = new MapPoint(save[0], save[1]);
                            mpArray.add(mapPoint);

                            MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint);
                            this.mapView.addLayer(mapLayer);
                        }
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (this.eventSrc.equals("Chouette")) {
                try {
                    ResultSet rs = connect.executeQuery(
                            "SELECT lieu_Lambert_X, lieu_Lambert_y FROM Observation JOIN Chouette On numIndividu = idObs ");

                    while (rs.next()) {
                        if (rs.getDouble(1) != 0. && rs.getDouble(2) != 0. && rs.getString(1) != null
                                && rs.getString(2) != null) {
                            double[] save = lambert93toWGS84(rs.getDouble(1), rs.getDouble(2));
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
                        if (rs.getDouble(1) != 0. && rs.getDouble(2) != 0. && rs.getString(1) != null
                                && rs.getString(2) != null) {
                            double[] save = lambert93toWGS84(rs.getDouble(1), rs.getDouble(2));
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
                        if (rs.getDouble(1) != 0. && rs.getDouble(2) != 0. && rs.getString(1) != null
                                && rs.getString(2) != null) {
                            double[] save = lambert93toWGS84(rs.getDouble(1), rs.getDouble(2));
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
                        if (rs.getDouble(1) != 0. && rs.getDouble(2) != 0. && rs.getString(1) != null
                                && rs.getString(2) != null) {
                            double[] save = lambert93toWGS84(rs.getDouble(1), rs.getDouble(2));
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
            if (this.eventSrc.equals("Batracien")) {
                loadStage("../vue/VisualiserTablesBatracien.fxml", event);
            } else if (this.eventSrc.equals("Chouette")) {
                loadStage("../vue/VisualiserTablesChouette.fxml", event);
            } else if (this.eventSrc.equals("GCI")) {
                loadStage("../vue/VisualiserTablesGCI.fxml", event);
            } else if (this.eventSrc.equals("Hippocampe")) {
                loadStage("../vue/VisualiserTablesHippocampe.fxml", event);
            } else if (this.eventSrc.equals("Loutre")) {
                loadStage("../vue/VisualiserTablesLoutre.fxml", event);
            }
        }
    }
}

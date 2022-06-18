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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControllerVisualiser extends Controller implements Initializable {

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private ComboBox<String> comboBoxChoices;

    @FXML
    private ComboBox<String> comboBoxSpecies;

    private ObservableList<String> tlistChoices = FXCollections.observableArrayList();
    private ObservableList<String> tlistSpecies = FXCollections.observableArrayList();
    private ObservableList data = FXCollections.observableArrayList();

    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pieChart.setVisible(false);
        barChart.setVisible(false);
        lineChart.setVisible(false);

        tlistChoices.add("Camembert");
        tlistChoices.add("Barres");
        tlistChoices.add("Lignes");
        comboBoxChoices.setItems(tlistChoices);

        tlistSpecies.add("Batracien");
        tlistSpecies.add("Chouette");
        tlistSpecies.add("GCI");
        tlistSpecies.add("Hippocampe");
        tlistSpecies.add("Loutre");
        comboBoxSpecies.setItems(tlistSpecies);
    }

    @FXML
    private void comboChoice(ActionEvent event) {
        if (String.valueOf(comboBoxChoices.getValue()).equals("Camembert")) {
            graph("Pie", String.valueOf(comboBoxSpecies.getValue()));
        } else if (String.valueOf(comboBoxChoices.getValue()).equals("Barres")) {
            graph("Bar", String.valueOf(comboBoxSpecies.getValue()));
            // } else if (String.valueOf(comboBox.getValue()).equals("Lignes")) {
            // graph("Line", String.valueOf(comboBoxSpecies.getValue()));
        }
    }

    private void graph(String chart, String species) {
        if (chart.equals("Pie")) {
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
            }

            pieChart.getData().addAll(data);
            pieChart.setVisible(true);
            barChart.setVisible(false);
            // lineChart.setVisible(false);
        } else if (chart.equals("bar")) {
            pieChart.setVisible(false);
            barChart.setVisible(true);
            // lineChart.setVisible(false);
        } else if (chart.equals("Line")) {
            pieChart.setVisible(false);
            barChart.setVisible(false);
            // lineChart.setVisible(true);
        }
    }

    @FXML
    void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixAction.fxml", event);
        }
    }
}

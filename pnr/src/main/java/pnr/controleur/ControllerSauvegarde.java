package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

import javafx.event.ActionEvent;

public class ControllerSauvegarde extends Controller {

    @FXML
    private Button btnBack;

    @FXML
    private Button export;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void handleBtnClick(ActionEvent event) {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixActionAdmin.fxml", event);
        } else if (event.getSource() == export){
            this.exportTableToFile("yo");
        }
    }

    private void exportTableToFile(String csvString) {
        FileChooser fileChooser = new FileChooser();
        
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        java.io.File file = fileChooser.showSaveDialog(stage);
        if(file != null){
            try {
                Files.write( file.toPath(), csvString.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

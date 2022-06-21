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

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.opencsv.CSVWriter;

import javafx.event.ActionEvent;

public class ControllerSauvegarde extends Controller {

    @FXML
    private Button btnBack;

    @FXML
    private Button export;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void handleBtnClick(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == btnBack) {
            loadStage("../vue/ChoixActionAdmin.fxml", event);
        } else if (event.getSource() == export){
            this.fileCSV("test"); 
        }
    }
    
    // private void exportTableToFile(String csvString) {
    //     FileChooser fileChooser = new FileChooser();
        
    //     Stage stage = (Stage) anchorPane.getScene().getWindow();
    //     FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    //     fileChooser.getExtensionFilters().add(extFilter);
    //     fileChooser.setInitialFileName(csvString);
    //     java.io.File file = fileChooser.showSaveDialog(stage);
    //     if(file != null){
    //         try {
    //             Files.write( file.toPath(), csvString.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    //         } catch (IOException e) {
    //             throw new RuntimeException(e);
    //         }
    //     }
    // }


    public void fileCSV(String fileName) throws IOException, SQLException{

        CSVWriter writer = new CSVWriter(new FileWriter(fileName));
        Boolean includeHeaders = true;
        ResultSet rs = connect.executeQuery("SELECT pseudonyme,nom, prenom,mdpUtilisateur,permission FROM Utilisateur");
  
        
        writer.writeAll(rs,includeHeaders);

        // PrintWriter out = new PrintWriter (fileName);
        // while (res.next()){
        //     System.out.println(res.getString("pseudonyme"));
        //     System.out.println(res.getString("nom"));
        //     System.out.println(res.getString("prenom"));
        //     System.out.println(res.getString("mdpUtilisateur"));
        //     System.out.println(res.getString("permission"));
        // }

        // InputStream in = new ByteArrayInputStream(fileName.getBytes());
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName(fileName);
        java.io.File file = fileChooser.showSaveDialog(stage);
        if(file != null){
                Files.write( file.toPath(), fileName.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
        }
    }
}

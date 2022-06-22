package pnr.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pnr.modele.donneeAddsOn.TableUtilisateur;

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
        } else if (event.getSource() == export){
            //this.fileCSV("test"); 
            this.fileCSV(); 
        }
    }

    public void fileCSV() throws IOException, SQLException{

        TableUtilisateur tb  = new TableUtilisateur(null, null, null, null, 0);
        ArrayList<TableUtilisateur> addArr = new ArrayList<TableUtilisateur>();
        Writer writer = null;
        try {
            ResultSet rs = connect.executeQuery("SELECT * FROM Utilisateur");
            while (rs.next()) {
                tb = new TableUtilisateur(
                        rs.getString("pseudonyme"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("mdpUtilisateur"),
                        rs.getInt("permission"));
                addArr.add(tb);
            }

            Stage stage = (Stage) anchorPane.getScene().getWindow();

            FileChooser fChooser = new FileChooser();
            fChooser.setTitle("Choississez un fichier");
            ExtensionFilter filter = new ExtensionFilter("Comma-separated values (CSV)", "*.csv");
            fChooser.getExtensionFilters().add(filter);
            fChooser.setSelectedExtensionFilter(filter);
            fChooser.setInitialFileName("Utilisateur");
            String selectedDirPath = fChooser.showSaveDialog(stage).getAbsolutePath();

            File downloadedFile = new File(selectedDirPath);
            writer = new BufferedWriter(new FileWriter(downloadedFile));

            for (TableUtilisateur ut : addArr) {
                String text = ut.getPseudoName() + "," + ut.getNom() + "," + ut.getPrenom() + ","+ ut.getMdp()+ ","+ ut.getPerm()+"\n";
                writer.write(text);
            }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                writer.flush();
                writer.close();
            }
        }
    }

    
    


    // public void fileCSV(String fileName) throws IOException, SQLException{

    //     CSVWriter writer = new CSVWriter(new FileWriter(fileName));
    //     ResultSet rs = connect.executeQuery("SELECT pseudonyme,nom, prenom,mdpUtilisateur,permission FROM Utilisateur");
    //     writer.writeAll(rs,true);
    //     FileChooser fileChooser = new FileChooser();
    //     Stage stage = (Stage) anchorPane.getScene().getWindow();
    //     FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    //     fileChooser.getExtensionFilters().add(extFilter);
    //     fileChooser.setInitialFileName(fileName);
    //     java.io.File file = fileChooser.showSaveDialog(stage);
    //     if(file != null){
    //             Files.write( file.toPath(), fileName.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    //     }
    // }
//}

package pnr.controleur;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {

    public void loadStage(String fxml, ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setMaximized(true);
            stage.show();
            
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

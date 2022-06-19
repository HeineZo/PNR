package pnr.controleur;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.*;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pnr.modele.ConnectVPS;

public class Controller {

    ConnectVPS connect;
    public Controller() {
        connect = new ConnectVPS("jdbc:mysql://141.94.221.193:3306/bd_pnr", "user", "Mdp@user1");
    }

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

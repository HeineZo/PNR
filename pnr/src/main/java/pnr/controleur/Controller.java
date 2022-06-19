package pnr.controleur;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
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

    public void initVisualiser(String event) {
        String fileName = "visualiserSrc.txt";
        File f = new File(fileName);

        f.delete();

        try {
            FileWriter writer = new FileWriter(fileName);

            writer.write(event);

            writer.close();
        } catch (IOException e) {
            System.out.println("File not found : " + fileName);
        }
    }

    public String getEventSrcVisualiser() {
        String fileName = "visualiserSrc.txt";
        String src = null;

        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);

            src = scanner.nextLine();

            scanner.close();
        } catch (IOException e) {
            System.out.println("File not found : " + fileName);
        }

        return src;
    }
}

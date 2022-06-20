package pnr.controleur;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import io.github.palexdev.materialfx.enums.NotificationPos;
import io.github.palexdev.materialfx.notifications.MFXNotificationCenterSystem;
import io.github.palexdev.materialfx.notifications.MFXNotificationSystem;
import io.github.palexdev.materialfx.notifications.base.INotification;
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

    public void initPermission(String event) {
        String fileName = "permissionSrc.txt";
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

    public void initNomUser(String event) {
        String fileName = "nomUserSrc.txt";
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

    public void initConfirmation(String event) {
        String fileName = "confirmationSrc.txt";
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

    public String getEventSrcPermission() {
        String fileName = "PermissionSrc.txt";
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

    public String getEventSrcNomUser() {
        String fileName = "nomUserSrc.txt";
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

    public String getEventSrcConfirmation() {
        String fileName = "confirmationSrc.txt";
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

    protected void error(String messageContent,AnchorPane anchorPane) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        MFXNotificationSystem.instance().initOwner(stage);
		MFXNotificationCenterSystem.instance().initOwner(stage);
		MFXNotificationSystem.instance()
				.setPosition(NotificationPos.BOTTOM_LEFT)
				.publish(createNotification(messageContent));
	}

    private INotification createNotification(String content) {
		ExampleNotification notification = new ExampleNotification();
		notification.setContentText(content);
		return notification;
	}
}

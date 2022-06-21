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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pnr.modele.ConnectVPS;
import io.github.palexdev.materialfx.controls.MFXListView;

public class Controller {

    ConnectVPS connect;
    public static String userClicked;

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

    public void loadUser(String fxml, MouseEvent event, String user) {
        userClicked = user;
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) ((MFXListView<?>) (event.getSource())).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setMaximized(true);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getUserClicked() {
        return userClicked;
    }

    public void initPermission(String event) {
        String fileName = "logs/permissionSrc.txt";
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
        String fileName = "logs/nomUserSrc.txt";
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
        String fileName = "logs/visualiserSrc.txt";
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
        String fileName = "logs/confirmationSrc.txt";
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
        String fileName = "logs/visualiserSrc.txt";
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
        String fileName = "logs/permissionSrc.txt";
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
        String fileName = "logs/nomUserSrc.txt";
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
        String fileName = "logs/confirmationSrc.txt";
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

    public String initPage(ImageView imgEspece, Text nameEspece) {
        String urlImage = "";
        String textEspece = "";
        String eventSrc;
        eventSrc = this.getEventSrcVisualiser();
        if (eventSrc.equals("Batracien")) {
            urlImage = "especes/batracien.png";
            textEspece = "Batracien";
        } else if (eventSrc.equals("Chouette")) {
            urlImage = "especes/chouette.png";
            textEspece = "Chouette";
        } else if (eventSrc.equals("GCI")) {
            urlImage = "especes/gci.png";
            textEspece = "GCI";
        } else if (eventSrc.equals("Hippocampe")) {
            urlImage = "especes/hippocampe.png";
            textEspece = "Hippocampe";
        } else if (eventSrc.equals("Loutre")) {
            urlImage = "especes/loutre.png";
            textEspece = "Loutre";
        } else {
            urlImage = "especes/null.png";
            textEspece = "Espece inconnue";
        }
        changeImage(urlImage, imgEspece);
        changeText(textEspece, nameEspece);
        return eventSrc;
    }

    public void changeImage(String url, ImageView imgEspece) {
        Image imProfile = new Image(getClass().getResourceAsStream(url));
        imgEspece.setImage(imProfile);
    }

    public void changeText(String text, Text nameEspece) {
        nameEspece.setText(text);
    }

    protected void error(String messageContent, AnchorPane anchorPane) {
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

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
import io.github.palexdev.materialfx.controls.MFXScrollPane;

/**
 * Main controller class for the application
 */
public class Controller {

    ConnectVPS connect;
    public static String userClicked;

    /**
     * Constrctor that initializes a connextion to the database
     */
    public Controller() {
        connect = new ConnectVPS("jdbc:mysql://141.94.221.193:3306/bd_pnr", "user", "Mdp@user1");
    }

    /**
     * Loads a given stage
     * @param fxml the name of the fxml page
     * @param event event given to the page
     */
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

    /**
     * Loads a page based on a previous String (name of an user)
     * @param fxml the fxml page to load
     * @param event event associed, page loaded with a mouse click 
     * @param user the user String that has been clicked on
     */
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

    /**
     * Gets the user that has been clicked on
     * @return the user that has been clicked on
     */
    public String getUserClicked() {
        return userClicked;
    }

    /**
     * Writes in a file the current permission
     * @param event the permission : 0 or 1
     */
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

    /**
     * Writes in a file the current username
     * @param event the username
     */
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

    /**
     * Writes in a file the current species
     * @param event the species
     */
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

    /**
     * Writes in a file the needed confirmation message
     * @param event needed confirmation message
     */
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

    /**
     * Gets the user the current species
     * @return the current species
     */
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

    /**
     * Gets the current permision
     * @return the current permision
     */
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

    /**
     * Gets the current username
     * @return the current username
     */
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

    /**
     * Gets the current confirmation name
     * @return the current confirmation name
     */
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

    /**
     * Initializes the page's picture given the previous species
     * @param imgEspece the picture that will be displayed
     * @param txtEspece the text that will be displayed
     * @return the current species
     */
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

    /**
     * Changes the image displayed
     * @param url link of the picture that will be displayed
     * @param imgEspece the picture that will be displayed
     */
    public void changeImage(String url, ImageView imgEspece) {
        Image imProfile = new Image(getClass().getResourceAsStream(url));
        imgEspece.setImage(imProfile);
    }

    /**
     * Changes the text displayed
     * @param txtEspece the text that will be displayed
     * @param nameEspece the name of the species that will be displayed
     */
    public void changeText(String text, Text nameEspece) {
        nameEspece.setText(text);
    }

    /**
     * Creates an error notification
     * @param messageContent content of the message
     * @param anchorPane the pane that contains the messgae
     */
    protected void error(String messageContent, AnchorPane anchorPane) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        MFXNotificationSystem.instance().initOwner(stage);
		MFXNotificationCenterSystem.instance().initOwner(stage);
		MFXNotificationSystem.instance()
				.setPosition(NotificationPos.BOTTOM_LEFT)
				.publish(createNotification(messageContent));
	}

    /**
     * Creates a notification that will be displayed
     * @param content content of the notification
     * @return the notification created
     */
    private INotification createNotification(String content) {
		ExampleNotification notification = new ExampleNotification();
		notification.setContentText(content);
		return notification;
	}
}

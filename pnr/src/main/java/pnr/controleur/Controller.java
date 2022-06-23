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
 * Super Controller class grouping the attributes and methods common to all controllers such as connecting to the database...
 */
public class Controller {

    ConnectVPS connect;
    public static String userClicked;

    /**
     * Controller constructor
     */
    public Controller() {
        connect = new ConnectVPS("jdbc:mysql://141.94.221.193:3306/bd_pnr", "user", "Mdp@user1");
    }

    /**
     * It loads the fxml file and sets it as the root of the current stage
     * 
     * @param fxml The name of the FXML file you want to load.
     * @param event The event that triggered the method.
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
     * It loads the fxml file into the root of the stage, and sets the stage to maximized
     * 
     * @param fxml the fxml file to load
     * @param event The event that triggered the method
     * @param user The user that was clicked on.
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
     * It returns the value of the variable userClicked.
     * 
     * @return The userClicked variable is being returned.
     */
    public String getUserClicked() {
        return userClicked;
    }

    /**
     * This function resets the userClicked variable to null
     */
    public void resetUserClicked() {
        userClicked = null;
    }

    /**
     * It loads an FXML file into a scroll pane
     * 
     * @param ui The path to the FXML file.
     * @param scrollPane The scrollpane that you want to load the UI into.
     */
    public void loadUI(String ui, MFXScrollPane scrollPane) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(ui));
        scrollPane.setContent(pane);
    }

    /**
     * It deletes the file if it exists, then creates a new file with the name "permissionSrc.txt" and
     * writes the event to it
     * 
     * @param event The event that you want to log.
     */
    public void initPermission(String event) {
        String fileName = "src/main/java/pnr/logs/permissionSrc.txt";
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
     * It deletes the file if it exists, then creates a new file with the name of the fileName variable
     * and writes the event variable to it
     * 
     * @param event the name of the user
     */
    public void initNomUser(String event) {
        String fileName = "src/main/java/pnr/logs/nomUserSrc.txt";
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
     * It deletes the file visualiserSrc.txt, then writes the event string to it
     * 
     * @param event the event to be visualised
     */
    public void initVisualiser(String event) {
        String fileName = "src/main/java/pnr/logs/visualiserSrc.txt";
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
     * It deletes the file if it exists, then creates a new file with the name "confirmationSrc.txt"
     * and writes the event to it
     * 
     * @param event the event that is being confirmed
     */
    public void initConfirmation(String event) {
        String fileName = "src/main/java/pnr/logs/confirmationSrc.txt";
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
     * It reads the first line of a text file and returns it as a string
     * 
     * @return The file name of the file that is being read.
     */
    public String getEventSrcVisualiser() {
        String fileName = "src/main/java/pnr/logs/visualiserSrc.txt";
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
     * It reads the first line of a text file and returns it as a string
     * 
     * @return The file name of the file that is being read.
     */
    public String getEventSrcPermission() {
        String fileName = "src/main/java/pnr/logs/permissionSrc.txt";
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
     * It reads a file and returns the first line of the file
     * 
     * @return The method is returning the first line of the file.
     */
    public String getEventSrcNomUser() {
        String fileName = "src/main/java/pnr/logs/nomUserSrc.txt";
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
     * It reads the first line of a text file and returns it as a string
     * 
     * @return The file name of the file that contains the source of the event.
     */
    public String getEventSrcConfirmation() {
        String fileName = "src/main/java/pnr/logs/confirmationSrc.txt";
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
     * It takes an ImageView and a TextView as parameters, and changes their content based on the value
     * of a String
     * 
     * @param imgEspece the imageView that will be changed
     * @param nameEspece Text
     * @return The eventSrc is being returned.
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
     * It takes a string and an ImageView as parameters, creates an Image object from the string, and
     * sets the ImageView's image to the Image object
     * 
     * @param url the path of the image
     * @param imgEspece the imageView that I want to change
     */
    public void changeImage(String url, ImageView imgEspece) {
        Image imProfile = new Image(getClass().getResourceAsStream(url));
        imgEspece.setImage(imProfile);
    }

    /**
     * The function changeText() takes a String and a Text object as parameters and sets the text of
     * the Text object to the String
     * 
     * @param text the text to be displayed
     * @param nameEspece the Text object that will be changed
     */
    public void changeText(String text, Text nameEspece) {
        nameEspece.setText(text);
    }

    /**
     * It creates a notification with the messageContent parameter and publishes it
     * 
     * @param messageContent The message you want to display
     * @param anchorPane The anchor pane that the notification will be displayed on.
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
     * This function creates a notification object with the content text set to the content parameter
     * 
     * @param content The text that will be displayed in the notification.
     * @return A notification object.
     */
    private INotification createNotification(String content) {
        ExampleNotification notification = new ExampleNotification();
        notification.setContentText(content);
        return notification;
    }
}
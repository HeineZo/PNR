package pnr;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

import animatefx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("vue/LandingPage.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setTitle("PNR - Page d'accueil");
        primaryStage.getIcons().add(new Image("file:vue/images/logo/logo.png"));
        scene.getStylesheets().add(getClass().getResource("vue/font/font.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        new FadeIn(root).play();
    }
}

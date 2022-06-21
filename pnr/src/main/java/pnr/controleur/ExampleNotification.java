package pnr.controleur;


import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXSimpleNotification;
import io.github.palexdev.materialfx.factories.InsetsFactory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class ExampleNotification extends MFXSimpleNotification {
    private final SimpleStringProperty contentText = new SimpleStringProperty();

    public ExampleNotification() {

        MFXIconWrapper icon = new MFXIconWrapper("mfx-exclamation-triangle", 20, 32);
        HBox header = new HBox(icon);
        header.setAlignment(Pos.CENTER);
        header.setPadding(InsetsFactory.of(5, 0, 5, 5));
        header.setMaxWidth(Double.MAX_VALUE);


        Label contentLabel = new Label();
        contentLabel.getStyleClass().add("content");
        contentLabel.textProperty().bind(contentText);
        contentLabel.setWrapText(true);
        contentLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        contentLabel.setAlignment(Pos.TOP_CENTER);


        BorderPane container = new BorderPane();
        container.getStyleClass().add("notification");
        container.setTop(header);
        container.setCenter(contentLabel);
        container.getStylesheets().add(getClass().getResource("../vue/css/style.css").toExternalForm());
        container.setPrefHeight(100);
        container.setPrefWidth(350);

        setContent(container);
    }

    public String getContentText() {
        return contentText.get();
    }

    public StringProperty contentTextProperty() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText.set(contentText);
    }
}


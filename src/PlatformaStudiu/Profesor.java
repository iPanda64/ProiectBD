package PlatformaStudiu;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Profesor extends Application {

    private String path = "file:/C:/Users/mihai/Desktop/School/An 2 Sem 1/BD/Proiect/ProiectPng/Meniu/";
    private String fontPath = "file:/C:/Users/mihai/Desktop/School/An 2 Sem 1/BD/Proiect/ProiectPng/Fonts/";

    public void start(Stage primaryStage) {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Bold.otf", 38);

        AnchorPane root = new AnchorPane();

        Label welcomeLabel = new Label("Bine ai venit, profesor!");
        welcomeLabel.setStyle("-fx-text-fill: white;");
        welcomeLabel.setFont(Satoshi);
        AnchorPane.setTopAnchor(welcomeLabel, 100.0);
        AnchorPane.setLeftAnchor(welcomeLabel, screenWidth / 2 - 200);



        root.getChildren().add(welcomeLabel);

        Scene scene = new Scene(root, screenWidth, screenHeight);

        Rectangle background = new Rectangle(0, 0, screenWidth, screenHeight);
        LinearGradient linearGradient = new LinearGradient(
                0, 0, 1, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#37C8AB")),
                new Stop(1, Color.web("#2575FC"))
        );
        background.setFill(linearGradient);

        root.getChildren().add(0, background);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Professor Page");
        primaryStage.show();
    }
}
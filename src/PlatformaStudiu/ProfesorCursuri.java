package PlatformaStudiu;

import com.mysql.cj.log.Log;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ProfesorCursuri extends Application {

    private String path = "file:/C:/Users/mihai/Desktop/School/An 2 Sem 1/BD/Proiect/ProiectPng/Meniu/";
    private String fontPath = "file:/C:/Users/mihai/Desktop/School/An 2 Sem 1/BD/Proiect/ProiectPng/Fonts/";

    public void start(Stage primaryStage) {
        System.out.println(LogareBackend.getStaticId());
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Bold.otf", 38);

        AnchorPane root = new AnchorPane();

        Label welcomeLabel = new Label("Bine ai venit, profesor!");
        welcomeLabel.setStyle("-fx-text-fill: white;");
        welcomeLabel.setFont(Satoshi);
        AnchorPane.setTopAnchor(welcomeLabel, 100.0);
        AnchorPane.setLeftAnchor(welcomeLabel, screenWidth / 2 - 50);

        Label testLabel = new Label("Cursuri");
        testLabel.setFont(Satoshi);
        AnchorPane.setTopAnchor(testLabel , 400.0);
        AnchorPane.setLeftAnchor(testLabel , screenWidth / 2 - 50);
        root.getChildren().add(testLabel);

        Image Linie = new Image(path + "Linie.png");
        ImageView devideLine = new ImageView(Linie);
        devideLine.setFitWidth(770);
        devideLine.setFitHeight(770);
        AnchorPane.setTopAnchor(devideLine, 100.0);
        AnchorPane.setRightAnchor(devideLine, 800.0);

        Image Profil = new Image(path + "Profil.png");
        ImageView profilButton = new ImageView(Profil);
        setupButton(profilButton, 100.0, 1220.0);

        Image Cursuri = new Image(path + "Cursuri.png");
        ImageView cursuriButton = new ImageView(Cursuri);
        setupButton(cursuriButton, 230.0, 1220.0);

        Image Catalog = new Image(path + "Catalog.png");
        ImageView catalogButton = new ImageView(Catalog);
        setupButton(catalogButton, 360.0, 1220.0);

        Image Activitati = new Image(path + "Activitati.png");
        ImageView activitatiButton = new ImageView(Activitati);
        setupButton(activitatiButton, 490.0, 1220.0);

        Image Iesire = new Image(path + "Iesire.png");
        ImageView iesireButton = new ImageView(Iesire);
        setupButton(iesireButton, 750.0, 1220.0);

        Image Selection = new Image(path + "Selection.png");
        ImageView currentSel = new ImageView(Selection);
        currentSel.setFitWidth(280);
        currentSel.setFitHeight(120);
        AnchorPane.setTopAnchor(currentSel, 230.0);
        AnchorPane.setRightAnchor(currentSel, 1220.0);
        root.getChildren().add(currentSel);

        ImageView sel = new ImageView(Selection);
        sel.setFitWidth(280);
        sel.setFitHeight(120);
        sel.setVisible(false);
        root.getChildren().add(sel);

        addHoverEffect(profilButton, sel, 100.0);
        addHoverEffect(catalogButton, sel, 360.0);
        addHoverEffect(activitatiButton, sel, 490.0);
        addHoverEffect(iesireButton, sel, 750.0);

        profilButton.setOnMouseClicked(e -> {
            ProfesorProfil professorPage = new ProfesorProfil();
            professorPage.start(primaryStage);
        });

        catalogButton.setOnMouseClicked(e -> {
            ProfesorCatalog professorPage = new ProfesorCatalog();
            professorPage.start(primaryStage);
        });

        activitatiButton.setOnMouseClicked(e -> {
            ProfesorActivitati professorPage = new ProfesorActivitati();
            professorPage.start(primaryStage);
        });

        iesireButton.setOnMouseClicked(e -> {
            LoginPage professorPage = new LoginPage();
            professorPage.start(primaryStage);
        });

        root.getChildren().addAll(welcomeLabel,devideLine,profilButton,cursuriButton,catalogButton,activitatiButton,iesireButton);

        Scene scene = new Scene(root, screenWidth, screenHeight);

        Rectangle background = new Rectangle(0, 0, screenWidth, screenHeight);
        LinearGradient linearGradient = new LinearGradient(
                0, 0, 1, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#37C8AB")),
                new Stop(1, Color.web("#3737c8"))
        );
        background.setFill(linearGradient);

        root.getChildren().add(0, background);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Professor Page");
        primaryStage.show();
    }

    private void setupButton(ImageView button, double topAnchor, double rightAnchor) {
        button.setFitWidth(280);
        button.setFitHeight(120);
        AnchorPane.setTopAnchor(button, topAnchor);
        AnchorPane.setRightAnchor(button, rightAnchor);
        button.setPickOnBounds(true);
    }

    private void addHoverEffect(ImageView button, ImageView sel, double topPosition) {
        button.setOnMouseEntered(e -> {
            sel.setVisible(true);
            AnchorPane.setTopAnchor(sel, topPosition);
            AnchorPane.setRightAnchor(sel, 1220.0);
        });

        button.setOnMouseExited(e -> {
            sel.setVisible(false);
        });
    }
}
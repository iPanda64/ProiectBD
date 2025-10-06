package PlatformaStudiu;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static PlatformaStudiu.LoginPage.globalId;

public class AdminProfil extends Application {

    private String path;
    private String fontPath;

    public void start(Stage primaryStage) {
        String f="file:/";
        path = System.getProperty("user.dir");
        path += "/Proiect/Meniu/";
        path = path.replace("\\", "/");
        f+=path;
        path=f;

        f="file:/";
        fontPath=System.getProperty("user.dir");
        fontPath+="/Proiect/Fonts/";
        fontPath=fontPath.replace("\\", "/");
        f+=fontPath;
        fontPath=f;

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Bold.otf", 38);

        AnchorPane root = new AnchorPane();

        AdminBackend p = new AdminBackend(globalId);

        VBox panel = createPanel();
        AnchorPane.setTopAnchor(panel, 300.0);
        AnchorPane.setLeftAnchor(panel, 500.0);
        root.getChildren().add(panel);

        VBox data = userData(p);
        AnchorPane.setTopAnchor(data, 300.0);
        AnchorPane.setLeftAnchor(data, 740.0);
        root.getChildren().add(data);

        Label welcomeLabel = new Label("Bine ai venit, administrator!");
        welcomeLabel.setStyle("-fx-text-fill: white;");
        welcomeLabel.setFont(Satoshi);
        AnchorPane.setTopAnchor(welcomeLabel, 100.0);
        AnchorPane.setLeftAnchor(welcomeLabel, screenWidth / 2 - 50);

        Image Linie = new Image(path + "Linie.png");
        ImageView devideLine = new ImageView(Linie);
        devideLine.setFitWidth(770);
        devideLine.setFitHeight(770);
        AnchorPane.setTopAnchor(devideLine, 100.0);
        AnchorPane.setRightAnchor(devideLine, 800.0);

        Image Profil = new Image(path + "Profil.png");
        ImageView profilButton = new ImageView(Profil);
        setupButton(profilButton, 100.0, 1220.0);

        Image Cauta = new Image(path + "Cauta.png");
        ImageView cautaButton = new ImageView(Cauta);
        setupButton(cautaButton, 230.0, 1220.0);

        Image Cursuri = new Image(path + "Cursuri.png");
        ImageView cursuriButton = new ImageView(Cursuri);
        setupButton(cursuriButton, 360.0, 1220.0);

        Image Adauga = new Image(path + "AdaugaAdmin.png");
        ImageView adaugaButton = new ImageView(Adauga);
        setupButton(adaugaButton, 490.0, 1220.0);

        Image Iesire = new Image(path + "Iesire.png");
        ImageView iesireButton = new ImageView(Iesire);
        setupButton(iesireButton, 750.0, 1220.0);

        Image Selection = new Image(path + "Selection.png");
        ImageView currentSel = new ImageView(Selection);
        currentSel.setFitWidth(280);
        currentSel.setFitHeight(120);
        AnchorPane.setTopAnchor(currentSel, 100.0);
        AnchorPane.setRightAnchor(currentSel, 1220.0);
        root.getChildren().add(currentSel);

        ImageView sel = new ImageView(Selection);
        sel.setFitWidth(280);
        sel.setFitHeight(120);
        sel.setVisible(false);
        root.getChildren().add(sel);

        addHoverEffect(cautaButton, sel, 230.0);
        addHoverEffect(cursuriButton, sel, 360.0);
        addHoverEffect(adaugaButton, sel, 490.0);
        addHoverEffect(iesireButton, sel, 750.0);

        cautaButton.setOnMouseClicked(e -> {
            AdminCauta professorPage = new AdminCauta();
            professorPage.start(primaryStage);
        });

        cursuriButton.setOnMouseClicked(e -> {
            AdminCursuri professorPage = new AdminCursuri();
            professorPage.start(primaryStage);
        });

        adaugaButton.setOnMouseClicked(e -> {
            AdminAdauga professorPage = new AdminAdauga();
            professorPage.start(primaryStage);
        });

        iesireButton.setOnMouseClicked(e -> {
            LoginPage professorPage = new LoginPage();
            professorPage.start(primaryStage);
        });

        root.getChildren().addAll(welcomeLabel,devideLine,profilButton,cursuriButton,cautaButton,adaugaButton,iesireButton);

        Scene scene = new Scene(root, screenWidth, screenHeight);

        Rectangle background = new Rectangle(0, 0, screenWidth, screenHeight);
        LinearGradient linearGradient = new LinearGradient(
                0, 0, 1, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#000080")),
                new Stop(1, Color.web("#3737c8"))
        );
        background.setFill(linearGradient);

        root.getChildren().add(0, background);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Professor Page");
        primaryStage.show();
    }

    private VBox userData(AdminBackend p)
    {
        VBox vbox = new VBox(25);
        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Light.otf", 32);

        Table t = p.viewProfile();

        Label numeLabel = new Label(t.getData(0,3));
        numeLabel.setStyle("-fx-text-fill: white;");
        numeLabel.setFont(Satoshi);

        Label prenumeLabel = new Label(t.getData(0,4));
        prenumeLabel.setStyle("-fx-text-fill: white;");
        prenumeLabel.setFont(Satoshi);

        Label cnpLabel = new Label(t.getData(0,2));
        cnpLabel.setStyle("-fx-text-fill: white;");
        cnpLabel.setFont(Satoshi);

        Label emailLabel = new Label(t.getData(0,6));
        emailLabel.setStyle("-fx-text-fill: white;");
        emailLabel.setFont(Satoshi);

        Label telLabel = new Label(t.getData(0,5));
        telLabel .setStyle("-fx-text-fill: white;");
        telLabel .setFont(Satoshi);

        Label ibanLabel = new Label(t.getData(0,7));
        ibanLabel.setStyle("-fx-text-fill: white;");
        ibanLabel.setFont(Satoshi);

        Label contractLabel = new Label(t.getData(0,8));
        contractLabel.setStyle("-fx-text-fill: white;");
        contractLabel.setFont(Satoshi);

        vbox.getChildren().addAll(numeLabel,prenumeLabel,cnpLabel,emailLabel,telLabel,ibanLabel,contractLabel);

        return vbox;
    }

    private VBox createPanel()
    {
        VBox vbox = new VBox(25);
        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Medium.otf", 32);

        Label numeLabel = new Label("Nume:");
        numeLabel.setStyle("-fx-text-fill: white;");
        numeLabel.setFont(Satoshi);

        Label prenumeLabel = new Label("Prenume:");
        prenumeLabel.setStyle("-fx-text-fill: white;");
        prenumeLabel.setFont(Satoshi);

        Label cnpLabel = new Label("CNP:");
        cnpLabel.setStyle("-fx-text-fill: white;");
        cnpLabel.setFont(Satoshi);

        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-text-fill: white;");
        emailLabel.setFont(Satoshi);

        Label telLabel = new Label("Nr. Telefon:");
        telLabel .setStyle("-fx-text-fill: white;");
        telLabel .setFont(Satoshi);

        Label ibanLabel = new Label("Cont IBAN:");
        ibanLabel.setStyle("-fx-text-fill: white;");
        ibanLabel.setFont(Satoshi);

        Label contractLabel = new Label("Nr. Contract:");
        contractLabel.setStyle("-fx-text-fill: white;");
        contractLabel.setFont(Satoshi);

        vbox.getChildren().addAll(numeLabel,prenumeLabel,cnpLabel,emailLabel,telLabel,ibanLabel,contractLabel);

        return vbox;
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
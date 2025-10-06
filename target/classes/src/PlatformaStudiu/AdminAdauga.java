package PlatformaStudiu;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
import jdk.jshell.execution.Util;

import static PlatformaStudiu.LoginPage.globalId;

public class AdminAdauga extends Application {

    private String path;
    private String fontPath;
    private static int currentShow = 1;

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

        Utilizator u = new Utilizator();
        VBox data = userData(u,p,primaryStage,root);
        AnchorPane.setTopAnchor(data, 280.0);
        AnchorPane.setLeftAnchor(data, 740.0);
        root.getChildren().add(data);

        createMenu(root,primaryStage);

        createPanel(p,root,primaryStage);

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

    private void createMenu(AnchorPane root, Stage primaryStage){
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
        AnchorPane.setTopAnchor(currentSel, 490.0);
        AnchorPane.setRightAnchor(currentSel, 1220.0);
        root.getChildren().add(currentSel);

        ImageView sel = new ImageView(Selection);
        sel.setFitWidth(280);
        sel.setFitHeight(120);
        sel.setVisible(false);
        root.getChildren().add(sel);

        addHoverEffect(profilButton, sel, 100.0);
        addHoverEffect(cautaButton, sel, 230.0);
        addHoverEffect(cursuriButton, sel, 360.0);
        addHoverEffect(iesireButton, sel, 750.0);

        profilButton.setOnMouseClicked(e -> {
            AdminProfil professorPage = new AdminProfil();
            professorPage.start(primaryStage);
        });

        cautaButton.setOnMouseClicked(e -> {
            AdminCauta professorPage = new AdminCauta();
            professorPage.start(primaryStage);
        });

        cursuriButton.setOnMouseClicked(e -> {
            AdminCursuri professorPage = new AdminCursuri();
            professorPage.start(primaryStage);
        });

        iesireButton.setOnMouseClicked(e -> {
            LoginPage professorPage = new LoginPage();
            professorPage.start(primaryStage);
        });

        root.getChildren().addAll(devideLine,profilButton,cursuriButton,cautaButton,adaugaButton,iesireButton);

    }

    private void createPanel(AdminBackend p,AnchorPane root, Stage primaryStage) {

        Image Download = new Image(path + "NewDownload.png");
        ImageView downloadButton = new ImageView(Download);
        setupButton(downloadButton, 100.0, 110.0);
        downloadButton.setFitWidth(130.0);
        downloadButton.setFitHeight(130.0);

        Image Selection = new Image(path + "SelButtons.png");
        ImageView currentSel = new ImageView(Selection);
        currentSel.setFitWidth(370);
        currentSel.setFitHeight(130);
        AnchorPane.setTopAnchor(currentSel, 100.0);
        if(currentShow == 1)
        {
            AnchorPane.setRightAnchor(currentSel, 710.0);
        }
        else
        {
            AnchorPane.setRightAnchor(currentSel, 280.0);
        }
        root.getChildren().add(currentSel);

        Image Toate = new Image(path + "StudentButton.png");
        ImageView astaziButton = new ImageView(Toate);
        setupButton(astaziButton, 100.0, 710.0);
        astaziButton.setFitWidth(370.5);
        astaziButton.setFitHeight(130.5);

        Image Astazi = new Image(path + "ProfesorButton.png");
        ImageView toateButton = new ImageView(Astazi);
        setupButton(toateButton, 100.0, 280.0);
        toateButton.setFitWidth(370.5);
        toateButton.setFitHeight(130.5);

        astaziButton.setOnMouseClicked(e -> {
            currentShow = 1;
            AdminAdauga professorPage = new AdminAdauga();
            professorPage.start(primaryStage);
        });

        toateButton.setOnMouseClicked(e -> {
            currentShow = 0;
            AdminAdauga professorPage = new AdminAdauga();
            professorPage.start(primaryStage);
        });

        ImageView Sel = new ImageView(Selection);
        Sel.setFitWidth(370);
        Sel.setFitHeight(130);
        Sel.setVisible(false);
        root.getChildren().add(Sel);

        Image SelDownload = new Image(path + "SelDownload.png");
        ImageView downloadSel = new ImageView(SelDownload);
        downloadSel.setFitWidth(130);
        downloadSel.setFitHeight(130);
        AnchorPane.setTopAnchor(downloadSel, 100.0);
        downloadSel.setVisible(false);
        root.getChildren().add(downloadSel);

        if(currentShow == 0)
        {
            hoverEffect(astaziButton, Sel, 710.0);
        }
        else
        {
            hoverEffect(toateButton, Sel, 280.0);
        }

        hoverEffect(downloadButton, downloadSel, 110.0);

        root.getChildren().addAll(astaziButton, toateButton);
    }

    private VBox userData(Utilizator p, AdminBackend a, Stage primaryStage, AnchorPane root) {
        VBox vbox = new VBox(1);
        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Light.otf", 32);

        TextField userField = new TextField();
        userField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        userField.setFont(Satoshi);

        TextField passField = new TextField();
        passField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        passField.setFont(Satoshi);

        TextField numeField = new TextField();
        numeField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        numeField.setFont(Satoshi);

        TextField prenumeField = new TextField();
        prenumeField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        prenumeField.setFont(Satoshi);

        TextField cnpField = new TextField();
        cnpField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        cnpField.setFont(Satoshi);

        TextField emailField = new TextField();
        emailField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        emailField.setFont(Satoshi);

        TextField telField = new TextField();
        telField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        telField.setFont(Satoshi);

        TextField ibanField = new TextField();
        ibanField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        ibanField.setFont(Satoshi);

        TextField contractField = new TextField();
        contractField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        contractField.setFont(Satoshi);

        Image Download = new Image(path + "NewAdauga.png");
        ImageView downloadButton = new ImageView(Download);
        setupButton(downloadButton, 100.0, 110.0);
        downloadButton.setFitWidth(130.0);
        downloadButton.setFitHeight(130.0);

        downloadButton.setOnMouseClicked(e -> {
            if(currentShow == 1)
            {
                a.addStudent(cnpField.getText(),numeField.getText(),prenumeField.getText(),telField.getText(),emailField.getText(),ibanField.getText(),contractField.getText(),userField.getText(),passField.getText(),"0","1");
                AdminAdauga professorPage = new AdminAdauga();
                professorPage.start(primaryStage);
            }
            else
            {
                a.addProfesor(cnpField.getText(),numeField.getText(),prenumeField.getText(),telField.getText(),emailField.getText(),ibanField.getText(),contractField.getText(),userField.getText(),passField.getText(),"0","4","Matematica");
                AdminAdauga professorPage = new AdminAdauga();
                professorPage.start(primaryStage);
            }
        });

        Image SelDownload = new Image(path + "SelDownload.png");
        ImageView downloadSel = new ImageView(SelDownload);
        downloadSel.setFitWidth(130);
        downloadSel.setFitHeight(130);
        AnchorPane.setTopAnchor(downloadSel, 100.0);
        downloadSel.setVisible(false);
        root.getChildren().add(downloadSel);

        hoverEffect(downloadButton, downloadSel, 110.0);

        root.getChildren().add(downloadButton);

        vbox.getChildren().addAll(
                userField, passField, numeField, prenumeField, cnpField, emailField, telField, ibanField, contractField
        );

        return vbox;
    }

    private VBox createPanel()
    {
        VBox vbox = new VBox(25);
        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Medium.otf", 32);

        Label idLabel = new Label("Username:");
        idLabel.setStyle("-fx-text-fill: white;");
        idLabel.setFont(Satoshi);

        Label tipLabel = new Label("Parola:");
        tipLabel.setStyle("-fx-text-fill: white;");
        tipLabel.setFont(Satoshi);

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

        vbox.getChildren().addAll(idLabel,tipLabel,numeLabel,prenumeLabel,cnpLabel,emailLabel,telLabel,ibanLabel,contractLabel);

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

    private void hoverEffect(ImageView button, ImageView sel, double topPosition) {
        button.setOnMouseEntered(e -> {
            sel.setVisible(true);
            AnchorPane.setTopAnchor(sel, 100.0);
            AnchorPane.setRightAnchor(sel, topPosition);
        });

        button.setOnMouseExited(e -> {
            sel.setVisible(false);
        });
    }
}
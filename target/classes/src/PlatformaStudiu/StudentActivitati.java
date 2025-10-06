package PlatformaStudiu;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static PlatformaStudiu.LoginPage.globalId;

public class StudentActivitati extends Application {

    private String path;
    private String fontPath;
    private static int idActivity = 0;
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

        AnchorPane root = new AnchorPane();

        StudentBackend s = new StudentBackend(globalId);

        createMenu(root, primaryStage);

        if(idActivity == 0)
        {
            createPanel(s,root,primaryStage);
        }

        ScrollPane scrollPane = activityList(s,root,primaryStage);
        String cssPath_scrollPane="src/PlatformaStudiu/styles.css";
        java.io.File cssFile_scrollPane = new java.io.File(cssPath_scrollPane);
        try {
            scrollPane.getStylesheets().add(cssFile_scrollPane.toURI().toURL().toExternalForm());
        }catch (Exception e) {e.printStackTrace();}
        AnchorPane.setTopAnchor(scrollPane, 300.0);
        AnchorPane.setRightAnchor(scrollPane, 60.0);
        scrollPane.setPrefHeight(600);
        scrollPane.setPrefWidth(1040);
        root.getChildren().add(scrollPane);

        Scene scene = new Scene(root, screenWidth, screenHeight);

        Rectangle background = new Rectangle(0, 0, screenWidth, screenHeight);
        LinearGradient linearGradient = new LinearGradient(
                0, 0, 1, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#d40055")),
                new Stop(1, Color.web("#3737c8"))
        );
        background.setFill(linearGradient);

        root.getChildren().add(0, background);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Professor Page");
        primaryStage.show();
    }

    private void createMenu(AnchorPane root, Stage primaryStage){

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

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

        Image Cauta = new Image(path + "Cauta.png");
        ImageView cautaButton = new ImageView(Cauta);
        setupButton(cautaButton, 360.0, 1220.0);

        Image Activitati = new Image(path + "Activitati.png");
        ImageView activitatiButton = new ImageView(Activitati);
        setupButton(activitatiButton, 490.0, 1220.0);

        Image Mesaje = new Image(path + "Mesaje.png");
        ImageView mesajeButton = new ImageView(Mesaje);
        setupButton(mesajeButton, 620.0, 1220.0);

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
        addHoverEffect(cursuriButton, sel, 230.0);
        addHoverEffect(cautaButton, sel, 360.0);
        addHoverEffect(mesajeButton, sel, 620.0);
        addHoverEffect(iesireButton, sel, 750.0);

        profilButton.setOnMouseClicked(e -> {
            StudentProfil professorPage = new StudentProfil();
            professorPage.start(primaryStage);
        });

        cursuriButton.setOnMouseClicked(e -> {
            StudentCursuri professorPage = new StudentCursuri();
            professorPage.start(primaryStage);
        });

        cautaButton.setOnMouseClicked(e -> {
            StudentCauta professorPage = new StudentCauta();
            professorPage.start(primaryStage);
        });

        mesajeButton.setOnMouseClicked(e -> {
            StudentMesaje professorPage = new StudentMesaje();
            professorPage.start(primaryStage);
        });

        iesireButton.setOnMouseClicked(e -> {
            LoginPage professorPage = new LoginPage();
            professorPage.start(primaryStage);
        });

        root.getChildren().addAll(devideLine,profilButton,cursuriButton,cautaButton,activitatiButton,iesireButton,mesajeButton);

    }

    private ScrollPane activityList(StudentBackend p,AnchorPane root, Stage primaryStage) {
        VBox buttonList = new VBox(10);
        buttonList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        Table t;
        if(currentShow == 0)
        {
            t = p.getActivitatiToateZilele();
        }
        else
        {
            t = p.getActivitatiZiCurenta();
        }

        if(idActivity == 0)
        {
            for(int i=0; i<t.getColCount(); i++)
            {
                Button customButton = createCustomButton(p,t.getData(i,1), t.getData(i,2), t.getData(i,3), Integer.parseInt(t.getData(i,0)), primaryStage);
                buttonList.getChildren().add(customButton);
            }
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private Button createCustomButton(StudentBackend p, String label1Text, String label2Text, String textFieldText, int idAct, Stage primaryStage) {
        AnchorPane root = new AnchorPane();

        Table t = p.getNote();

        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Image Activitate = new Image(path + "Activitate.png");
        ImageView imageView = new ImageView(Activitate);
        imageView.setFitWidth(990);
        imageView.setFitHeight(90);

        Label offset = new Label("");
        Label label1 = new Label(label1Text);
        AnchorPane.setTopAnchor(label1, 25.0);
        AnchorPane.setLeftAnchor(label1, 50.0);

        Label label2 = new Label(label2Text);
        AnchorPane.setTopAnchor(label2, 25.0);
        AnchorPane.setLeftAnchor(label2, 640.0);

        String notaFinala = new String("0");
        for(int i=0; i<t.getColCount(); i++)
        {
            if(Integer.parseInt(t.getData(i,0)) == Integer.parseInt(p.getCursId(idAct)))
            {
                notaFinala = t.getData(i,2);
            }
        }

        Label notaCurs = new Label(notaFinala);
        AnchorPane.setTopAnchor(notaCurs, 25.0);
        AnchorPane.setLeftAnchor(notaCurs, 380.0);

        label1.setStyle("-fx-background-color: transparent;-fx-font-family: 'Arial Black'; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        label2.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        notaCurs.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");

        Label textField = new Label(textFieldText);
        AnchorPane.setTopAnchor(textField, 25.0);
        AnchorPane.setLeftAnchor(textField, 880.0);
        textField.setPrefWidth(120);
        textField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");

        root.getChildren().addAll(imageView,offset, label1, label2, textField, notaCurs);

        button.setGraphic(root);

        return button;
    }

    private void createPanel(StudentBackend p,AnchorPane root, Stage primaryStage) {

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

        Image Toate = new Image(path + "Astazi.png");
        ImageView astaziButton = new ImageView(Toate);
        setupButton(astaziButton, 100.0, 710.0);
        astaziButton.setFitWidth(370.5);
        astaziButton.setFitHeight(130.5);

        Image Astazi = new Image(path + "Toate.png");
        ImageView toateButton = new ImageView(Astazi);
        setupButton(toateButton, 100.0, 280.0);
        toateButton.setFitWidth(370.5);
        toateButton.setFitHeight(130.5);

        astaziButton.setOnMouseClicked(e -> {
            currentShow = 1;
            StudentActivitati professorPage = new StudentActivitati();
            professorPage.start(primaryStage);
        });

        toateButton.setOnMouseClicked(e -> {
            currentShow = 0;
            StudentActivitati professorPage = new StudentActivitati();
            professorPage.start(primaryStage);
        });

        downloadButton.setOnMouseClicked(e -> {
            p.downloadActivitatiToateZilele();
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

        root.getChildren().addAll(astaziButton, toateButton,downloadButton);
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
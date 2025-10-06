package PlatformaStudiu;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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

public class StudentCauta extends Application {

    private String path;
    private String fontPath;
    private static int idCurs = 0;

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

        System.out.println(globalId);
        StudentBackend s = new StudentBackend(globalId);

        AnchorPane root = new AnchorPane();

        createMenu(root,primaryStage);

        ScrollPane scrollPane = cursList(s,root,primaryStage);
        String cssPath_scrollPane="src/PlatformaStudiu/styles.css";
        java.io.File cssFile_scrollPane = new java.io.File(cssPath_scrollPane);
        try {
            scrollPane.getStylesheets().add(cssFile_scrollPane.toURI().toURL().toExternalForm());
        }catch (Exception e) {e.printStackTrace();}
        AnchorPane.setTopAnchor(scrollPane, 240.0);
        AnchorPane.setRightAnchor(scrollPane, 100.0);
        scrollPane.setPrefHeight(700);
        scrollPane.setPrefWidth(1020);

        ScrollPane activityPane = activityList(s,root,primaryStage);
        try{
        activityPane.getStylesheets().add(cssFile_scrollPane.toURI().toURL().toExternalForm());
        }catch(Exception e){e.printStackTrace();}
        AnchorPane.setTopAnchor(activityPane, 240.0);
        AnchorPane.setRightAnchor(activityPane, 100.0);
        activityPane.setPrefHeight(600);
        activityPane.setPrefWidth(1020);

        if(idCurs == 0)
        {
            root.getChildren().add(scrollPane);
        }
        else
        {
            Image Back = new Image(path + "Back.png");
            ImageView backButton = new ImageView(Back);
            setupButton(backButton, 110.0, 1020.0);
            backButton.setFitWidth(100);
            backButton.setFitHeight(100);
            backButton.setOnMouseClicked(e -> {
                idCurs = 0;
                StudentCauta professorPage = new StudentCauta();
                professorPage.start(primaryStage);
            });
            root.getChildren().add(backButton);
            root.getChildren().add(activityPane);
        }

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

    private ScrollPane cursList(StudentBackend s, AnchorPane root, Stage primaryStage) {
        VBox buttonList = new VBox(10);
        buttonList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        Table t = s.getEveryCurs();

        if(idCurs == 0)
        {
            for(int i=0; i<t.getColCount(); i++)
            {
                Button customButton = createCustomButton(s,t.getData(i,1), Integer.parseInt(t.getData(i,0)), primaryStage);
                buttonList.getChildren().add(customButton);
            }
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private ScrollPane activityList(StudentBackend s, AnchorPane root, Stage primaryStage) {
        VBox buttonList = new VBox(10);
        buttonList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        Table t = s.getActivitate(idCurs);
        Table q = s.getMyActivities(idCurs);

        for(int i=0; i<t.getColCount(); i++)
        {
            boolean canJoin = true;
            for(int j=0; j<q.getColCount(); j++)
            {
                if(q.getData(j,0).equals(t.getData(i,0)))
                {
                    canJoin = false;
                }
            }

            Button customButton = createActivityButton(s,t.getData(i,1), Integer.parseInt(t.getData(i,0)), primaryStage,canJoin,Integer.parseInt(t.getData(i,0)));
            if(canJoin == false)
            {
                customButton.setOpacity(0.5);
            }
            buttonList.getChildren().add(customButton);
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private Button createActivityButton(StudentBackend s, String label1Text, int idC, Stage primaryStage, boolean canJoin, int idActivity) {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Image Activitate = new Image(path + "Activitate.png");
        ImageView imageView = new ImageView(Activitate);
        imageView.setFitWidth(990);
        imageView.setFitHeight(90);

        Label label1 = new Label(label1Text);
        label1.setStyle("-fx-text-fill: white; -fx-font-size: 34px; -fx-font-weight: bold;");

        StackPane labelStack = new StackPane(label1);
        labelStack.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(imageView, labelStack);
        stackPane.setStyle("-fx-alignment: center;");

        if(canJoin)
        {
            button.setOnAction(event -> {
                s.inscriereCurs(idCurs);
                s.inscriereActivitate(idActivity);
                StudentCauta professorPage = new StudentCauta();
                professorPage.start(primaryStage);
            });
        }

        button.setGraphic(stackPane);

        return button;
    }

    private Button createCustomButton(StudentBackend s, String label1Text, int idC, Stage primaryStage) {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Image Activitate = new Image(path + "Activitate.png");
        ImageView imageView = new ImageView(Activitate);
        imageView.setFitWidth(990);
        imageView.setFitHeight(90);

        Label label1 = new Label(label1Text);
        label1.setStyle("-fx-text-fill: white; -fx-font-size: 34px; -fx-font-weight: bold;");

        StackPane labelStack = new StackPane(label1);
        labelStack.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(imageView, labelStack);
        stackPane.setStyle("-fx-alignment: center;");

        button.setOnAction(event -> {
            idCurs = idC;
            StudentCauta professorPage = new StudentCauta();
            professorPage.start(primaryStage);
        });

        button.setGraphic(stackPane);

        return button;
    }

    private void createMenu(AnchorPane root, Stage primaryStage) {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Bold.otf", 38);

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
        AnchorPane.setTopAnchor(currentSel, 360.0);
        AnchorPane.setRightAnchor(currentSel, 1220.0);
        root.getChildren().add(currentSel);

        ImageView sel = new ImageView(Selection);
        sel.setFitWidth(280);
        sel.setFitHeight(120);
        sel.setVisible(false);
        root.getChildren().add(sel);

        addHoverEffect(profilButton, sel, 100.0);
        addHoverEffect(cursuriButton, sel, 230.0);
        addHoverEffect(activitatiButton, sel, 490.0);
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

        activitatiButton.setOnMouseClicked(e -> {
            StudentActivitati professorPage = new StudentActivitati();
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
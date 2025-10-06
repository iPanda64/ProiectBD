package PlatformaStudiu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

import static PlatformaStudiu.LoginPage.globalId;

public class ProfesorActivitati extends Application {

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

        ProfesorBackend p = new ProfesorBackend(globalId);

        AnchorPane root = new AnchorPane();

        createMenu(root,primaryStage);

        if(idActivity == 0)
        {
            createPanel(p,root,primaryStage);
        }
        else
        {
            studentList(root,primaryStage, p);
        }

        ScrollPane scrollPane = activityList(p,root,primaryStage);

        String cssPath_Pane="src/PlatformaStudiu/styles.css";
        java.io.File cssFile_Pane = new java.io.File(cssPath_Pane);
        try {
            scrollPane.getStylesheets().add(cssFile_Pane.toURI().toURL().toExternalForm());
        }catch (Exception e1) {e1.printStackTrace();}

        AnchorPane.setTopAnchor(scrollPane, 300.0);
        AnchorPane.setRightAnchor(scrollPane, 200.0);
        scrollPane.setPrefHeight(600);
        scrollPane.setPrefWidth(890);
        root.getChildren().add(scrollPane);

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

    private ScrollPane activityList(ProfesorBackend p,AnchorPane root, Stage primaryStage) {
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
                Image Back = new Image(path + "Plus.png");
                ImageView addPondere = new ImageView(Back);
                setupButton(addPondere, 100.0, 1020.0);
                addPondere.setFitWidth(80);
                addPondere.setFitHeight(80);
                Button customButton = createCustomButton(p,t.getData(i,1), t.getData(i,2), t.getData(i,3) + "%", Integer.parseInt(t.getData(i,0)), primaryStage, addPondere);
                buttonList.getChildren().add(customButton);
                AnchorPane.setTopAnchor(addPondere, i*91.0 + 302);
                AnchorPane.setRightAnchor(addPondere, 110.0);
                root.getChildren().add(addPondere);
            }
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    public void PrintStudents(ProfesorBackend p,List<String[]> students, int a) {
        Table t = p.getStudentiActivitate(idActivity);
        for(int i=0;i<t.getColCount();i++)
        {
            String[] s = new String[4];
            for(int j=0;j<t.getRowCount();j++)
            {
                s[j] = t.getData(i,j);
            }
            if(s[1]!=null)
            {
                students.add(s);
            }
        }
    }

    private void studentList(AnchorPane root, Stage primaryStage, ProfesorBackend p) {

        Image Back = new Image(path + "Back.png");
        ImageView backButton = new ImageView(Back);
        setupButton(backButton, 100.0, 1020.0);
        backButton.setFitWidth(100);
        backButton.setFitHeight(100);
        backButton.setOnMouseClicked(e -> {
            idActivity = 0;
            ProfesorActivitati professorPage = new ProfesorActivitati();
            professorPage.start(primaryStage);
        });
        root.getChildren().add(backButton);

        ScrollPane scrollPane = new ScrollPane();
        GridPane rightList = new GridPane();
        rightList.setVgap(10);
        rightList.setHgap(10);
        rightList.setPadding(new Insets(10));
        rightList.setAlignment(Pos.TOP_LEFT);

        List<String[]> students = new java.util.ArrayList<>(List.of());
        PrintStudents(p,students, idActivity);

        String listStyle = " -fx-font-size: 32px; -fx-font-family: 'SatoshiLight';fx-border-color: white; -fx-border-width: 0 0 0 0;";

        int rowIndex = 0;
        for (String[] student : students) {
            Label nameLabel = new Label("     " + student[1]);
            nameLabel.setStyle(listStyle);

            Label emailLabel = new Label(student[2]);
            emailLabel.setStyle(listStyle);

            Label numberLabel = new Label(student[3]);
            numberLabel.setStyle(listStyle);

            Label delimiter1 = new Label("          ");
            delimiter1.setStyle("-fx-font-family: 'Satoshi'; -fx-font-size: 28px; -fx-text-fill: white; -fx-padding: 0 20px;");

            Label delimiter2 = new Label("                ");
            delimiter2.setStyle("-fx-font-family: 'Satoshi'; -fx-font-size: 28px; -fx-text-fill: white; -fx-padding: 0 20px;");

            rightList.add(nameLabel, 0, rowIndex);
            rightList.add(delimiter1, 1, rowIndex);
            rightList.add(emailLabel, 2, rowIndex);
            rightList.add(delimiter2, 3, rowIndex);

            Line line = new Line();
            line.setStartX(0);
            line.setStartY(0);
            line.setEndX(550);
            line.setEndY(0);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(4);
            line.setStrokeType(StrokeType.CENTERED);

            if(rowIndex < students.size() - 1)
            {
                rightList.add(line, 0, rowIndex , 5, 2);
            }

            rowIndex++;
        }

        int butIndex = 0;
        VBox buttonList = new VBox(-2.02);
        for (String[] student : students){
            TextField numberLabel = new TextField(student[3]);
            numberLabel.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    p.notareStudent(idActivity,Integer.parseInt(student[0]),Integer.parseInt(numberLabel.getText()));
                }
            });
            numberLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");

            Line line = new Line();
            line.setStartX(0);
            line.setStartY(0);
            line.setEndX(50);
            line.setEndY(0);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(4);
            line.setStrokeType(StrokeType.CENTERED);

            buttonList.getChildren().addAll(numberLabel);
            if(butIndex < students.size() - 1)
            {
                buttonList.getChildren().addAll(line);
            }

            butIndex++;
        }
        AnchorPane.setTopAnchor(buttonList, 255.0);
        AnchorPane.setRightAnchor(buttonList, 770.0);
        root.getChildren().addAll(buttonList);

        scrollPane.setContent(rightList);
        scrollPane.setStyle("-fx-background: transparent;");

        AnchorPane.setTopAnchor(scrollPane, 250.0);
        AnchorPane.setRightAnchor(scrollPane, 260.0);
        AnchorPane.setBottomAnchor(scrollPane, -20.0);

        scrollPane.setPrefWidth(580);

        String cssPath_cursPane="src/PlatformaStudiu/scrollpane-style.css";
        java.io.File cssFile_Pane1 = new java.io.File(cssPath_cursPane);
        try {
            scrollPane.getStylesheets().add(cssFile_Pane1.toURI().toURL().toExternalForm());
        }catch (Exception e1) {e1.printStackTrace();}


        root.getChildren().add(scrollPane);
    }

    private Button createCustomButton(ProfesorBackend p, String label1Text, String label2Text, String textFieldText, int idAct, Stage primaryStage, ImageView addPondere) {
        AnchorPane root = new AnchorPane();

        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Image Activitate = new Image(path + "Activitate.png");
        ImageView imageView = new ImageView(Activitate);
        imageView.setFitWidth(880);
        imageView.setFitHeight(80);

        Label offset = new Label("");
        Label label1 = new Label(label1Text);
        AnchorPane.setTopAnchor(label1, 20.0);
        AnchorPane.setLeftAnchor(label1, 50.0);
        Label label2 = new Label(label2Text);
        AnchorPane.setTopAnchor(label2, 20.0);
        AnchorPane.setLeftAnchor(label2, 480.0);
        label1.setStyle("-fx-background-color: transparent;-fx-font-family: 'Arial Black'; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        label2.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");

        TextField textField = new TextField(textFieldText);
        AnchorPane.setTopAnchor(textField, 10.0);
        AnchorPane.setLeftAnchor(textField, 760.0);
        textField.setPrefWidth(120);
        textField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");

        addPondere.setOnMouseClicked(e -> {
            String result = textField.getText().replace("%", "");
            p.changeProcentageActivitateTo(idAct,Integer.parseInt(result));
        });

        root.getChildren().addAll(imageView,offset, label1, label2, textField);

        button.setOnAction(event -> {
            idActivity = idAct;
        ProfesorActivitati professorPage = new ProfesorActivitati();
            professorPage.start(primaryStage);
        });

        button.setGraphic(root);

        return button;
    }

    private void createPanel(ProfesorBackend p,AnchorPane root, Stage primaryStage) {

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
            ProfesorActivitati professorPage = new ProfesorActivitati();
            professorPage.start(primaryStage);
        });

        toateButton.setOnMouseClicked(e -> {
            currentShow = 0;
            ProfesorActivitati professorPage = new ProfesorActivitati();
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

    private void createMenu(AnchorPane root, Stage primaryStage) {

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        Font SatoshiBold = Font.loadFont(fontPath + "Satoshi-Bold.otf", 38);

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
        addHoverEffect(catalogButton, sel, 360.0);
        addHoverEffect(iesireButton, sel, 750.0);

        profilButton.setOnMouseClicked(e -> {
            ProfesorProfil professorPage = new ProfesorProfil();
            professorPage.start(primaryStage);
        });

        cursuriButton.setOnMouseClicked(e -> {
            ProfesorCursuri professorPage = new ProfesorCursuri();
            professorPage.start(primaryStage);
        });

        catalogButton.setOnMouseClicked(e -> {
            ProfesorCatalog professorPage = new ProfesorCatalog();
            professorPage.start(primaryStage);
        });

        iesireButton.setOnMouseClicked(e -> {
            LoginPage professorPage = new LoginPage();
            professorPage.start(primaryStage);
        });

        root.getChildren().addAll(devideLine,profilButton,cursuriButton,catalogButton,activitatiButton,iesireButton);

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
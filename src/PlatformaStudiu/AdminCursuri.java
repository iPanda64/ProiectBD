package PlatformaStudiu;

import javafx.application.Application;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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

public class AdminCursuri extends Application {

    private String path;
    private String fontPath;
    private static int idCurs = 0;
    private static int filter = 0;
    private static int currentShow = 1;
    private static String cautaCurs;

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

        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Medium.otf", 38);

        AnchorPane root = new AnchorPane();

        AdminBackend a = new AdminBackend(globalId);

        createMenu(root,primaryStage);

        ScrollPane scrollPane = cursList(a,root,primaryStage);

        String cssPath_Pane="src/PlatformaStudiu/styles.css";
        java.io.File cssFile_Pane = new java.io.File(cssPath_Pane);
        try {
            scrollPane.getStylesheets().add(cssFile_Pane.toURI().toURL().toExternalForm());
        }catch (Exception e1) {e1.printStackTrace();}

        AnchorPane.setTopAnchor(scrollPane, 240.0);
        AnchorPane.setRightAnchor(scrollPane, 100.0);
        scrollPane.setPrefHeight(700);
        scrollPane.setPrefWidth(1020);

        ScrollPane activityPane = activityList(a,primaryStage);
        try {
            activityPane.getStylesheets().add(cssFile_Pane.toURI().toURL().toExternalForm());
        }catch (Exception e1) {e1.printStackTrace();}

        AnchorPane.setTopAnchor(activityPane, 380.0);
        AnchorPane.setBottomAnchor(activityPane, 100.0);
        AnchorPane.setRightAnchor(activityPane, 60.0);
        activityPane.setPrefHeight(600);
        activityPane.setPrefWidth(1020);

        ScrollPane profesorPane = profesorList(a);

        String cssPath_Paneprofesor="src/PlatformaStudiu/invisible.css";
        java.io.File cssFile_Paneprofesor = new java.io.File(cssPath_Paneprofesor);
        try {
            profesorPane.getStylesheets().add(cssFile_Paneprofesor.toURI().toURL().toExternalForm());
        }catch (Exception e1) {e1.printStackTrace();}

        AnchorPane.setTopAnchor(profesorPane, 280.0);
        AnchorPane.setBottomAnchor(profesorPane, 420.0);
        AnchorPane.setRightAnchor(profesorPane, 60.0);
        profesorPane.setPrefHeight(600);
        profesorPane.setPrefWidth(1020);

        ScrollPane studentPane = studentList(a);

        String cssPath_Panestudent="src/PlatformaStudiu/invisible.css";
        java.io.File cssFile_Panestudent = new java.io.File(cssPath_Panestudent);
        try {
            studentPane.getStylesheets().add(cssFile_Panestudent.toURI().toURL().toExternalForm());
        }catch (Exception e1) {e1.printStackTrace();}

        AnchorPane.setTopAnchor(studentPane, 580.0);
        AnchorPane.setBottomAnchor(studentPane, 80.0);
        AnchorPane.setRightAnchor(studentPane, 60.0);
        studentPane.setPrefHeight(600);
        studentPane.setPrefWidth(1020);

        if(idCurs == 0)
        {
            root.getChildren().add(scrollPane);
            searchBar(root,primaryStage);
        }
        else
        {
            createPanel(root,primaryStage);
            if(currentShow == 1)
            {
                activitySearch(a,root,primaryStage);
                root.getChildren().add(activityPane);
            }
            else
            {
                root.getChildren().addAll(profesorPane,studentPane);
            }
        }

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

    private ScrollPane profesorList(AdminBackend s) {
        VBox buttonList = new VBox(10);
        buttonList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        Table t = s.getAllProfesors(idCurs);

        for(int i=0; i<t.getColCount(); i++)
        {
            Button customButton = createElement(s,t.getData(i,1)+" "+t.getData(i,2));
            buttonList.getChildren().add(customButton);
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private ScrollPane studentList(AdminBackend s) {
        VBox buttonList = new VBox(10);
        buttonList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        Table t = s.getAllStudents(idCurs);

        for(int i=0; i<t.getColCount(); i++)
        {
            Button customButton = createElement(s,t.getData(i,1)+" "+t.getData(i,2));
            buttonList.getChildren().add(customButton);
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private void createPanel(AnchorPane root, Stage primaryStage) {

        Image Download = new Image(path + "NewBack.png");
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

        Image Toate = new Image(path + "NewActivitati.png");
        ImageView astaziButton = new ImageView(Toate);
        setupButton(astaziButton, 100.0, 710.0);
        astaziButton.setFitWidth(370.5);
        astaziButton.setFitHeight(130.5);

        Image Astazi = new Image(path + "NewParticipanti.png");
        ImageView toateButton = new ImageView(Astazi);
        setupButton(toateButton, 100.0, 280.0);
        toateButton.setFitWidth(370.5);
        toateButton.setFitHeight(130.5);

        astaziButton.setOnMouseClicked(e -> {
            currentShow = 1;
            AdminCursuri professorPage = new AdminCursuri();
            professorPage.start(primaryStage);
        });

        toateButton.setOnMouseClicked(e -> {
            currentShow = 0;
            AdminCursuri professorPage = new AdminCursuri();
            professorPage.start(primaryStage);
        });

        downloadButton.setOnMouseClicked(e -> {
            idCurs = 0;
            AdminCursuri professorPage = new AdminCursuri();
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

        root.getChildren().addAll(astaziButton, toateButton,downloadButton);
    }

    private void activitySearch(AdminBackend a,AnchorPane root, Stage primaryStage){
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 38);

        Image SearchBar = new Image(path + "AdminSearchBar.png");
        ImageView searchBar = new ImageView(SearchBar);
        searchBar.setFitWidth(720.0);
        searchBar.setFitHeight(60.0);
        AnchorPane.setTopAnchor(searchBar, 280.0);
        AnchorPane.setLeftAnchor(searchBar, 460.0);

        Image Adauga = new Image(path + "GrupNou.png");
        ImageView adaugaButton = new ImageView(Adauga);
        setupButton(adaugaButton, 280.0, 280.0);
        adaugaButton.setFitWidth(60.0);
        adaugaButton.setFitHeight(60.0);

        TextField mesajField = new TextField();
        mesajField.setPromptText("Activitate noua");
        mesajField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        mesajField.setPrefWidth(700);
        mesajField.setFont(SatoshiLight);
        AnchorPane.setTopAnchor(mesajField, 280.0);
        AnchorPane.setRightAnchor(mesajField, 360.0);

        adaugaButton.setOnMouseClicked(e -> {
            a.createActivitateNoua(idCurs,mesajField.getText());
            AdminCursuri professorPage = new AdminCursuri();
            professorPage.start(primaryStage);
        });

        root.getChildren().addAll(searchBar, adaugaButton,mesajField);
    }

    private Button createElement(AdminBackend s, String label1Text) {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Image Activitate = new Image(path + "Activitate.png");
        ImageView imageView = new ImageView(Activitate);
        imageView.setFitWidth(800);
        imageView.setFitHeight(70);

        Label label1 = new Label(label1Text);
        label1.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");

        StackPane labelStack = new StackPane(label1);
        labelStack.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(imageView, labelStack);
        stackPane.setStyle("-fx-alignment: center;");

        button.setGraphic(stackPane);

        return button;
    }

    private ScrollPane activityList(AdminBackend s, Stage primaryStage) {
        VBox buttonList = new VBox(10);
        buttonList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        Table t = s.getActivitate(idCurs);

        for(int i=0; i<t.getColCount(); i++)
        {
            Button customButton = createActivityButton(s,t.getData(i,1), Integer.parseInt(t.getData(i,0)));
            buttonList.getChildren().add(customButton);
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private Button createActivityButton(AdminBackend s, String label1Text, int idAct) {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 30);
        Image Activitate = new Image(path + "Activitate.png");
        ImageView imageView = new ImageView(Activitate);
        imageView.setFitWidth(990);
        imageView.setFitHeight(90);

        Label label1 = new Label(label1Text);
        label1.setStyle("-fx-text-fill: white; -fx-font-size: 34px; -fx-font-weight: bold;");

        TextField mesajField = new TextField();
        mesajField.setPromptText("Prof. Id");
        mesajField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        mesajField.setPrefWidth(200);
        mesajField.setFont(SatoshiLight);

        mesajField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                s.inscriereProfesor(Integer.parseInt(mesajField.getText()),idAct);
            }
        });

        StackPane labelStack = new StackPane(label1);
        labelStack.setAlignment(Pos.CENTER);

        StackPane fieldStack = new StackPane(mesajField);
        labelStack.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(imageView, labelStack,fieldStack);
        stackPane.setStyle("-fx-alignment: center;");

        button.setGraphic(stackPane);

        return button;
    }

    private void searchBar(AnchorPane root, Stage primaryStage) {
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 38);

        Image Search = new Image(path + "Search.png");
        ImageView searchButton = new ImageView(Search);
        setupButton(searchButton,120.0,1000.0);
        searchButton.setFitWidth(80);
        searchButton.setFitHeight(80);

        Image SearchBar = new Image(path + "SearchBar.png");
        ImageView searchBar = new ImageView(SearchBar);
        searchBar.setFitWidth(480);
        searchBar.setFitHeight(80);
        AnchorPane.setTopAnchor(searchBar, 120.0);
        AnchorPane.setRightAnchor(searchBar, 480.0);

        TextField mesajField = new TextField();
        mesajField.setPromptText("Cauta");
        mesajField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        mesajField.setPrefWidth(600);
        mesajField.setFont(SatoshiLight);
        AnchorPane.setTopAnchor(mesajField, 130.0);
        AnchorPane.setRightAnchor(mesajField, 330.0);

        searchButton.setOnMouseClicked(e -> {
            if(filter == 0)
            {
                filter = 1;
            }
            else
            {
                filter = 0;
            }
            cautaCurs = mesajField.getText();
            AdminCursuri professorPage = new AdminCursuri();
            professorPage.start(primaryStage);
        });

        root.getChildren().addAll(searchButton,searchBar,mesajField);
    }

    private ScrollPane cursList(AdminBackend a, AnchorPane root, Stage primaryStage) {
        VBox buttonList = new VBox(10);
        buttonList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        Table t = new Table();
        if(filter == 0)
        {
            t = a.getAllCursuri();
        }
        else
        {
            t = a.cautaCurs(cautaCurs);
        }

        if(idCurs == 0)
        {
            for(int i=0; i<t.getColCount(); i++)
            {
                Button customButton = createCustomButton(a,t.getData(i,1), Integer.parseInt(t.getData(i,0)), primaryStage, root);
                buttonList.getChildren().add(customButton);
            }
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private Button createCustomButton(AdminBackend s, String label1Text, int idC, Stage primaryStage,AnchorPane root) {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 30);

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
            AdminCursuri professorPage = new AdminCursuri();
            professorPage.start(primaryStage);
        });

        button.setGraphic(stackPane);

        return button;
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
        AnchorPane.setTopAnchor(currentSel, 360.0);
        AnchorPane.setRightAnchor(currentSel, 1220.0);
        root.getChildren().add(currentSel);

        ImageView sel = new ImageView(Selection);
        sel.setFitWidth(280);
        sel.setFitHeight(120);
        sel.setVisible(false);
        root.getChildren().add(sel);

        addHoverEffect(profilButton, sel, 100.0);
        addHoverEffect(cautaButton, sel, 230.0);
        addHoverEffect(adaugaButton, sel, 490.0);
        addHoverEffect(iesireButton, sel, 750.0);

        profilButton.setOnMouseClicked(e -> {
            AdminProfil professorPage = new AdminProfil();
            professorPage.start(primaryStage);
        });

        cautaButton.setOnMouseClicked(e -> {
            AdminCauta professorPage = new AdminCauta();
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

        root.getChildren().addAll(devideLine,profilButton,cursuriButton,cautaButton,adaugaButton,iesireButton);

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
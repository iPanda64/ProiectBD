package PlatformaStudiu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static PlatformaStudiu.LoginPage.globalId;

public class StudentMesaje extends Application {

    private String path;
    private String fontPath;
    private String cursPath;
    private static int currentShow = 1;
    private static int idGrup = 0;
    private static boolean show = false;

    private static int currentCourse;
    private static int currentActivity = 0;

    private YearMonth currentYearMonth;
    private GridPane calendarGrid;
    private Label monthLabel;
    private Button selectedDayButton = null;

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

        f="file:/";
        cursPath=System.getProperty("user.dir");
        cursPath+="/Proiect/Cursuri/";
        cursPath=cursPath.replace("\\", "/");
        f+=cursPath;
        cursPath=f;

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Bold.otf", 38);

        AnchorPane root = new AnchorPane();

        StudentBackend s = new StudentBackend(globalId);

        createMenu(root,primaryStage);

        if(currentActivity == 0)
        {
            createPanel(s,root,primaryStage);
        }
        else
        {
            Image Back = new Image(path + "Back.png");
            ImageView backButton = new ImageView(Back);
            setupButton(backButton, 100.0, 1020.0);
            backButton.setFitWidth(100);
            backButton.setFitHeight(100);
            backButton.setOnMouseClicked(e -> {
                currentActivity = 0;
                StudentMesaje professorPage = new StudentMesaje();
                professorPage.start(primaryStage);
            });
            root.getChildren().add(backButton);
        }

        if(idGrup == 0)
        {
            if(currentActivity == 0)
            {
                if(currentShow == 1)
                {
                    ScrollPane activityPane = grupList(s,primaryStage);
                    String cssPath_activityPane="src/PlatformaStudiu/styles.css";
        java.io.File cssFile_activityPane = new java.io.File(cssPath_activityPane);
        try {
            activityPane.getStylesheets().add(cssFile_activityPane.toURI().toURL().toExternalForm());
        }catch (Exception e) {e.printStackTrace();}
                    AnchorPane.setTopAnchor(activityPane, 260.0);
                    AnchorPane.setRightAnchor(activityPane, 65.0);
                    activityPane.setPrefHeight(600);
                    activityPane.setPrefWidth(1020);
                    root.getChildren().add(activityPane);
                }
                else
                {
                    ScrollPane activityPane = suggestionList(s,primaryStage);
                    String cssPath_activityPane="src/PlatformaStudiu/styles.css";
        java.io.File cssFile_activityPane = new java.io.File(cssPath_activityPane);
        try {
            activityPane.getStylesheets().add(cssFile_activityPane.toURI().toURL().toExternalForm());
        }catch (Exception e) {e.printStackTrace();}
                    AnchorPane.setTopAnchor(activityPane, 260.0);
                    AnchorPane.setRightAnchor(activityPane, 65.0);
                    activityPane.setPrefHeight(600);
                    activityPane.setPrefWidth(1020);
                    root.getChildren().add(activityPane);
                }
            }
        }
        else
        {
            if(currentActivity == 0)
            {
                ScrollPane scrollPane = mesajePanel(s);
                String scrollPane_Path="src/PlatformaStudiu/styles.css";
                java.io.File cssFile_scrollPane = new java.io.File(scrollPane_Path);
                try {
                    scrollPane.getStylesheets().add(cssFile_scrollPane.toURI().toURL().toExternalForm());
                }catch (Exception e) {e.printStackTrace();}
                AnchorPane.setTopAnchor(scrollPane, 320.0);
                AnchorPane.setBottomAnchor(scrollPane, 240.0);
                AnchorPane.setLeftAnchor(scrollPane, 475.0);
                AnchorPane.setRightAnchor(scrollPane, 515.0);
                scrollPane.setPrefHeight(700);
                scrollPane.setPrefWidth(550);

                ScrollPane participantiPane = participantiPanel(s);
                String cssPath_participantiPane="src/PlatformaStudiu/invisible.css";
        java.io.File cssFile_participantiPane = new java.io.File(cssPath_participantiPane);
        try {
            participantiPane.getStylesheets().add(cssFile_participantiPane.toURI().toURL().toExternalForm());
        }catch (Exception e) {e.printStackTrace();}
                AnchorPane.setTopAnchor(participantiPane, 300.0);
                AnchorPane.setBottomAnchor(participantiPane, 500.0);
                AnchorPane.setLeftAnchor(participantiPane, 1075.0);
                AnchorPane.setRightAnchor(participantiPane, 120.0);
                participantiPane.setPrefHeight(700);
                participantiPane.setPrefWidth(550);

                ScrollPane activitatiPane = activitatiPanel(s,primaryStage);
                String cssPath_activitatiPane="src/PlatformaStudiu/invisible.css";
        java.io.File cssFile_activitatiPane = new java.io.File(cssPath_activitatiPane);
        try {
            activitatiPane.getStylesheets().add(cssFile_activitatiPane.toURI().toURL().toExternalForm());
        }catch (Exception e) {e.printStackTrace();}
                AnchorPane.setTopAnchor(activitatiPane, 600.0);
                AnchorPane.setBottomAnchor(activitatiPane, 215.0);
                AnchorPane.setLeftAnchor(activitatiPane, 1075.0);
                AnchorPane.setRightAnchor(activitatiPane, 120.0);
                activitatiPane.setPrefHeight(700);
                activitatiPane.setPrefWidth(550);

                root.getChildren().addAll(scrollPane,participantiPane,activitatiPane);
                sendMesaj(s,root,primaryStage);
                adaugaGrup(s,root,primaryStage);
            }
            else
            {
                currentYearMonth = YearMonth.now();

                createButtons(root, s);

                calendarGrid = new GridPane();
                calendarGrid.setHgap(20);
                calendarGrid.setVgap(10);
                updateCalendar(currentYearMonth, s, root);
                AnchorPane.setTopAnchor(calendarGrid, 320.0);
                AnchorPane.setRightAnchor(calendarGrid, 400.0);
                root.getChildren().add(calendarGrid);
            }
        }

        if(currentActivity != 0)
        {
            AnchorPane sidePanel = new AnchorPane();
            sidePanel = createSidePanel(s);
            AnchorPane.setRightAnchor(sidePanel , 0.0);
            root.getChildren().add(sidePanel);
        }

        if(idGrup != 0 && currentActivity == 0)
        {
            Image SugestiiParticipanti = new Image(path + "SugestiiMesaje.png");
            if(!show)
            {
                SugestiiParticipanti = new Image(path + "SugestiiMesaje.png");
            }
            else
            {
                SugestiiParticipanti = new Image(path + "ParticipantiMesaje.png");
            }
            ImageView sugestiiButton = new ImageView(SugestiiParticipanti);
            setupButton(sugestiiButton, 470.0, 130.0);
            sugestiiButton.setFitWidth(325.0);
            sugestiiButton.setFitHeight(65.0);

            sugestiiButton.setOnMouseClicked(e -> {
                System.out.println("asd");
                show = !show;
                StudentMesaje professorPage = new StudentMesaje();
                professorPage.start(primaryStage);
            });
            root.getChildren().add(sugestiiButton);
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

    private void adaugaGrup(StudentBackend s, AnchorPane root, Stage primaryStage) {
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 38);

        TextField mesajField = new TextField();
        mesajField.setPromptText("Activitate noua");
        mesajField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        mesajField.setPrefWidth(240);
        mesajField.setFont(SatoshiLight);
        AnchorPane.setTopAnchor(mesajField, 790.0);
        AnchorPane.setRightAnchor(mesajField, 220.0);

        Image Send = new Image(path + "GrupNou.png");
        ImageView sendButton = new ImageView(Send);
        setupButton(sendButton , 785.0, 115.0);
        sendButton .setFitWidth(70);
        sendButton .setFitHeight(70);
        sendButton .setOnMouseClicked(e -> {
            s.createActivitateNouaGrup(idGrup,mesajField.getText());
            StudentMesaje professorPage = new StudentMesaje();
            professorPage.start(primaryStage);
        });

        root.getChildren().addAll(mesajField,sendButton);
    }

    private ScrollPane activitatiPanel(StudentBackend s, Stage primaryStage){

        VBox participantiList = new VBox(14);
        participantiList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        Table t = s.getActivitatiGrup(idGrup);

        for(int i=0; i<t.getColCount(); i++)
        {
            Button customButton = createActivitate(s,t.getData(i,1), Integer.parseInt(t.getData(i,0)), primaryStage);
            participantiList.getChildren().add(customButton);
        }

        ScrollPane scrollPane = new ScrollPane(participantiList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private Button createActivitate(StudentBackend s,String nume, int idAct, Stage primaryStage) {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Image Participant = new Image(path + "Participant.png");
        ImageView imageView = new ImageView(Participant);
        imageView.setFitWidth(320);
        imageView.setFitHeight(60);

        Label label1 = new Label(nume);
        label1.setStyle("-fx-text-fill: white; -fx-font-size: 30px;");

        StackPane labelStack = new StackPane(label1);
        labelStack.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(imageView, labelStack);
        stackPane.setStyle("-fx-alignment: center;");

        button.setOnAction(event -> {
            s.inscriereCurs(Integer.parseInt(s.getCursId(idAct)));
            currentActivity = idAct;
            StudentMesaje professorPage = new StudentMesaje();
            professorPage.start(primaryStage);
        });

        button.setGraphic(stackPane);

        return button;
    }

    private ScrollPane participantiPanel(StudentBackend s){

        VBox participantiList = new VBox(14);
        participantiList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        Table t = new Table();
        if(!show)
        {
            t = s.getParticipants(idGrup);
        }
        else
        {
            t = s.getRecomendedParticipants(idGrup);
        }

        for(int i=0; i<t.getColCount(); i++)
        {
            Button customButton = createParticipant(t.getData(i,0),t.getData(i,1));
            participantiList.getChildren().add(customButton);
        }

        ScrollPane scrollPane = new ScrollPane(participantiList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private Button createParticipant(String nume, String prenume) {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Image Participant = new Image(path + "Participant.png");
        ImageView imageView = new ImageView(Participant);
        imageView.setFitWidth(320);
        imageView.setFitHeight(60);

        Label label1 = new Label(nume+" "+prenume);
        label1.setStyle("-fx-text-fill: white; -fx-font-size: 30px;");

        StackPane labelStack = new StackPane(label1);
        labelStack.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(imageView, labelStack);
        stackPane.setStyle("-fx-alignment: center;");

        button.setGraphic(stackPane);

        return button;
    }

    private void sendMesaj(StudentBackend s, AnchorPane root, Stage primaryStage) {
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 38);

        TextField mesajField = new TextField();
        mesajField.setPromptText("Mesaj");
        mesajField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        mesajField.setPrefWidth(400);
        mesajField.setFont(SatoshiLight);
        AnchorPane.setTopAnchor(mesajField, 790.0);
        AnchorPane.setRightAnchor(mesajField, 660.0);

        Image Send = new Image(path + "Send.png");
        ImageView sendButton = new ImageView(Send);
        setupButton(sendButton , 785.0, 500.0);
        sendButton .setFitWidth(70);
        sendButton .setFitHeight(70);
        sendButton .setOnMouseClicked(e -> {
            s.scrieMesaj(idGrup, mesajField.getText());
            StudentMesaje professorPage = new StudentMesaje();
            professorPage.start(primaryStage);
        });

        root.getChildren().addAll(mesajField,sendButton);
    }

    private ScrollPane mesajePanel(StudentBackend s) {
        VBox mesajeRightList = new VBox(10);
        VBox mesajeLeftList = new VBox(10);
        mesajeRightList.setAlignment(Pos.CENTER_RIGHT);
        mesajeLeftList.setPrefWidth(600);
        mesajeRightList.setPrefWidth(600);

        Table t = s.mesaje(idGrup);

        for(int i=0; i<t.getColCount(); i++)
        {
            Label mesaj = createMesaj(s,t.getData(i,0),i);
            if(t.getData(i,1).equals("No"))
            {
                mesajeLeftList.getChildren().add(mesaj);
                Label mesajNull = createMesaj(s,"",i);
                mesajeRightList.getChildren().add(mesajNull);
            }
            else
            {
                mesajeRightList.getChildren().add(mesaj);
                Label mesajNull = createMesaj(s,"",i);
                mesajeLeftList.getChildren().add(mesajNull);
            }
        }

        HBox hbox = new HBox(-660);
        hbox.getChildren().addAll(mesajeLeftList,mesajeRightList);

        ScrollPane scrollPane = new ScrollPane(hbox);
        return scrollPane;
    }

    private Label createMesaj(StudentBackend s ,String data, int idMesaj) {
        Table t = s.mesaje(idGrup);
        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Light.otf", 22);
        Label mesaj = new Label(data);
        if(data.equals(""))
        {
            mesaj.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-text-fill: white;" +
                            "-fx-padding: 10 20;" +
                            "-fx-background-radius: 20;"
            );
        }
        else if(t.getData(idMesaj,1).equals("No"))
        {
            mesaj.setStyle(
                    "-fx-background-color: #3949ab;" +
                            "-fx-text-fill: white;" +
                            "-fx-padding: 10 20;" +
                            "-fx-background-radius: 20;"
            );
        }
        else
        {
            mesaj.setStyle(
                    "-fx-background-color: #b80a69;" +
                            "-fx-text-fill: white;" +
                            "-fx-padding: 10 20;" +
                            "-fx-background-radius: 20;"
            );
        }
        mesaj.setFont(Satoshi);
        return mesaj;
    }

    private ScrollPane grupList(StudentBackend s, Stage primaryStage) {
        VBox buttonList = new VBox(10);
        buttonList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        Table q = s.getMyGroups();

        for(int i=0; i<q.getColCount(); i++)
        {
            Button customButton = createGrupButton(q.getData(i,0), Integer.parseInt(q.getData(i,0).substring(7).trim()), primaryStage);
            buttonList.getChildren().add(customButton);
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private ScrollPane suggestionList(StudentBackend s, Stage primaryStage) {
        VBox buttonList = new VBox(10);
        buttonList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        Table q = s.getRecomendedGroups();

        for(int i=0; i<q.getColCount(); i++)
        {
            Button customButton = createSuggestionButton(s,q.getData(i,0), Integer.parseInt(q.getData(i,0).substring(7).trim()), primaryStage);
            buttonList.getChildren().add(customButton);
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private Button createGrupButton(String label1Text, int idG, Stage primaryStage) {
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
            idGrup = idG;
            StudentMesaje professorPage = new StudentMesaje();
            professorPage.start(primaryStage);
        });

        button.setGraphic(stackPane);

        return button;
    }

    private Button createSuggestionButton(StudentBackend s, String label1Text, int idG, Stage primaryStage) {
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
            s.joinGroup(idG);
            StudentMesaje professorPage = new StudentMesaje();
            professorPage.start(primaryStage);
        });

        button.setGraphic(stackPane);

        return button;
    }

    private void createPanel(StudentBackend p,AnchorPane root, Stage primaryStage) {

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
        if(idGrup == 0)
        {
            if(currentShow == 1)
            {
                AnchorPane.setRightAnchor(currentSel, 710.0);
            }
            else
            {
                AnchorPane.setRightAnchor(currentSel, 280.0);
            }
            root.getChildren().add(currentSel);
        }

        Image Grupuri = null;
        if(idGrup == 0)
        {
            Grupuri = new Image(path + "Grupuri.png");
        }
        else
        {
            //Grupuri = new Image(path + "Adauga.png");
        }
        ImageView grupuriButton = new ImageView(Grupuri);
        setupButton(grupuriButton, 100.0, 710.0);
        grupuriButton.setFitWidth(370.5);
        grupuriButton.setFitHeight(130.5);

        Image Sugestii = null;
        if(idGrup == 0)
        {
            Sugestii = new Image(path + "Sugestii.png");
        }
        else
        {
            Sugestii = new Image(path + "Paraseste.png");
        }
        ImageView toateButton = new ImageView(Sugestii);
        setupButton(toateButton, 100.0, 280.0);
        toateButton.setFitWidth(370.5);
        toateButton.setFitHeight(130.5);

        grupuriButton.setOnMouseClicked(e -> {
            currentShow = 1;
            StudentMesaje professorPage = new StudentMesaje();
            professorPage.start(primaryStage);
        });

        toateButton.setOnMouseClicked(e -> {
            if(idGrup == 0)
            {
                currentShow = 0;
                StudentMesaje professorPage = new StudentMesaje();
                professorPage.start(primaryStage);
            }
            else
            {
                p.leaveGroup(idGrup);
                idGrup = 0;
                currentShow = 1;
                StudentMesaje professorPage = new StudentMesaje();
                professorPage.start(primaryStage);
            }
        });

        downloadButton.setOnMouseClicked(e -> {
            idGrup = 0;
            StudentMesaje professorPage = new StudentMesaje();
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

        if(idGrup == 0)
        {
            if(currentShow == 0)
            {
                hoverEffect(grupuriButton, Sel, 710.0);
            }
            else
            {
                hoverEffect(toateButton, Sel, 280.0);
            }
        }
        else
        {
            //hoverEffect(grupuriButton, Sel, 710.0);
            hoverEffect(toateButton, Sel, 280.0);
        }

        hoverEffect(downloadButton, downloadSel, 110.0);

        root.getChildren().addAll(grupuriButton, toateButton);

        if(idGrup != 0)
        {
            Image Panel = new Image(path + "MesajePanel.png");
            ImageView mesajePanel = new ImageView(Panel);
            AnchorPane.setTopAnchor(mesajePanel, 280.0);
            AnchorPane.setLeftAnchor(mesajePanel, 460.0);
            mesajePanel.setFitWidth(960.0);
            mesajePanel.setFitHeight(570.0);

            root.getChildren().addAll(mesajePanel,downloadButton);
        }
    }

    private void createMenu(AnchorPane root, Stage primaryStage){
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
        AnchorPane.setTopAnchor(currentSel, 620.0);
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
        addHoverEffect(activitatiButton, sel, 490.0);
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

        activitatiButton.setOnMouseClicked(e -> {
            StudentActivitati professorPage = new StudentActivitati();
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

    private void createButtons(AnchorPane root, StudentBackend p) {

        Image Calendar = new Image(cursPath + "Calendar.png");
        ImageView calendarImage = new ImageView(Calendar);
        AnchorPane.setTopAnchor(calendarImage, 240.0);
        AnchorPane.setRightAnchor(calendarImage, 340.0);
        calendarImage.setFitHeight(650);
        calendarImage.setFitWidth(700);

        Image LeftArrow = new Image(path + "LeftArrow.png");
        ImageView leftButton = new ImageView(LeftArrow);
        setupButton(leftButton, 270, 840);
        leftButton.setFitWidth(40.0);
        leftButton.setFitHeight(40.0);
        leftButton.setPickOnBounds(true);

        Image RightArrow = new Image(path + "RightArrow.png");
        ImageView rightButton = new ImageView(RightArrow);
        setupButton(rightButton, 270, 460);
        rightButton.setFitWidth(40.0);
        rightButton.setFitHeight(40.0);
        rightButton.setPickOnBounds(true);

        monthLabel = new Label();
        updateMonthLabel();
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Bold.otf", 32);
        monthLabel.setFont(SatoshiLight);
        monthLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setTopAnchor(monthLabel, 270.0);
        AnchorPane.setLeftAnchor(monthLabel, 740.0);

        leftButton.setOnMouseClicked(event -> {
            currentYearMonth = currentYearMonth.minusMonths(1);
            Platform.runLater(() -> updateCalendar(currentYearMonth, p, root));
            updateMonthLabel();
        });

        rightButton.setOnMouseClicked(event -> {
            currentYearMonth = currentYearMonth.plusMonths(1);
            Platform.runLater(() -> updateCalendar(currentYearMonth, p, root));
            updateMonthLabel();
        });

        root.getChildren().addAll(calendarImage, leftButton, rightButton, monthLabel);
    }

    private void updateMonthLabel() {
        monthLabel.setText(currentYearMonth.getMonth() +" - " + currentYearMonth.getYear());
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Bold.otf", 32);
        monthLabel.setFont(SatoshiLight);
        monthLabel.setStyle("-fx-text-fill: white;");
    }

    private void updateCalendar(YearMonth yearMonth, StudentBackend p, AnchorPane root) {
        calendarGrid.getChildren().clear();
        calendarGrid.getColumnConstraints().clear();
        calendarGrid.getRowConstraints().clear();

        Table q = p.getCalendar(currentActivity);
        String[] dates = new String[q.getColCount()];
        int dateNr = 0;
        for (int i = 0; i < q.getColCount(); i++) {
            String d = "";
            d += (q.getData(i, 4));
            d += ("-0");
            d += (q.getData(i, 3));
            dates[dateNr] = d;
            dateNr++;
        }

        List<Button> highlightedDays = new ArrayList<>();

        String[] dayNames = {"L", "M", "M", "J", "V", "S", "D"};
        for (int i = 0; i < dayNames.length; i++) {
            Label dayLabel = new Label(dayNames[i]);
            dayLabel.setStyle("-fx-padding: 10; -fx-font-size: 24; -fx-font-weight: bold;");
            GridPane.setHgrow(dayLabel, Priority.ALWAYS);
            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setAlignment(Pos.CENTER);
            calendarGrid.add(dayLabel, i, 0);
        }

        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int startDay = (firstDayOfMonth.getDayOfWeek().getValue() - 1) % 7;
        int daysInMonth = yearMonth.lengthOfMonth();

        int day = 1;
        for (int row = 1; day <= daysInMonth; row++) {
            for (int col = 0; col < 7; col++) {
                if (row == 1 && col < startDay) continue;
                if (day > daysInMonth) break;

                boolean canSelect = true;
                boolean isHighlighted = false;

                Button dayButton = new Button(String.valueOf(day));
                dayButton.setStyle("-fx-padding: 18; -fx-background-color: white; -fx-border-color: transparent; "
                        + "-fx-background-radius: 90px; "
                        + "-fx-font-size: 22px;"
                        + "-fx-font-weight: bold; -fx-alignment: center;");
                dayButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                GridPane.setHgrow(dayButton, Priority.ALWAYS);
                GridPane.setVgrow(dayButton, Priority.ALWAYS);

                for (int i = 0; i < dateNr; i++) {
                    if (dates[i].equals(yearMonth.toString()) && day == Integer.parseInt(q.getData(i, 2))) {
                        highlightDay(dayButton);
                        highlightedDays.add(dayButton);
                        isHighlighted = true;
                        canSelect = false;
                        break;
                    }
                }

                int currentDay = day;
                boolean finalCanSelect = canSelect;
                boolean finalIsHighlighted = isHighlighted;

                dayButton.setOnAction(event -> {
                    if (selectedDayButton != null && !highlightedDays.contains(selectedDayButton)) {
                        selectedDayButton.setStyle("-fx-padding: 18; -fx-background-color: white; -fx-border-color: transparent; "
                                + "-fx-background-radius: 90px; "
                                + "-fx-font-size: 22px;"
                                + "-fx-font-weight: bold; -fx-alignment: center;");
                    }

                    selectedDayButton = dayButton;

                    if (finalCanSelect) {
                        highlightSelectedDay(dayButton);
                    } else if (finalIsHighlighted) {
                        highlightDay(dayButton);
                    }

                    AnchorPane sidePanel = new AnchorPane();
                    root.getChildren().removeLast();
                    if (finalCanSelect) {
                        sidePanel = createSidePanel(p);
                    } else {
                        sidePanel = showSidePanel(p);
                    }
                    AnchorPane.setRightAnchor(sidePanel, 0.0);
                    root.getChildren().add(sidePanel);
                });

                calendarGrid.add(dayButton, col, row);
                day++;
            }
        }

        for (int i = 0; i < 7; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.ALWAYS);
            colConstraints.setPercentWidth(100.0 / 7);
            calendarGrid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < calendarGrid.getRowCount(); i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            rowConstraints.setPercentHeight(100.0 / calendarGrid.getRowCount());
            calendarGrid.getRowConstraints().add(rowConstraints);
        }
    }

    private void highlightSelectedDay(Button dayButton) {
        selectedDayButton = dayButton;
        dayButton.setStyle("-fx-padding: 18; -fx-background-color: lightblue; -fx-border-color: transparent; "
                + "-fx-background-radius: 90px; "
                + "-fx-font-size: 22px;"
                + "-fx-font-weight: bold; -fx-alignment: center;");
    }

    private void highlightDay(Button dayButton) {
        dayButton.setStyle("-fx-padding: 18; -fx-background-color: #37c8ab; -fx-border-color: transparent; "
                + "-fx-background-radius: 90px; "
                + "-fx-font-size: 22px;"
                + "-fx-font-weight: bold; -fx-alignment: center;");
    }

    private AnchorPane createSidePanel(StudentBackend p) {
        AnchorPane root = new AnchorPane();

        Font SatoshiMedium = Font.loadFont(fontPath + "Satoshi-Medium.otf", 24);
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 24);

        Table q = p.getCalendar(currentActivity);
        String[] dates = new String[q.getColCount()];
        int dateNr = 0;
        for(int i=0;i<q.getColCount();i++)
        {
            String d = new String();
            d+=(q.getData(i,4));
            d+=("-0");
            d+=(q.getData(i,3));
            dates[dateNr]=d;
            dateNr++;
        }

        Image Panel = new Image(cursPath + "Panel.png");
        ImageView panelImage = new ImageView(Panel);
        AnchorPane.setTopAnchor(panelImage, 240.0);
        AnchorPane.setRightAnchor(panelImage, 100.0);
        panelImage.setFitHeight(650);
        panelImage.setFitWidth(200);

        TextField hourField = new TextField();
        hourField.setPromptText("HH");
        hourField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 24px;");
        hourField.setPrefWidth(400);
        AnchorPane.setTopAnchor(hourField, 490.0);
        AnchorPane.setRightAnchor(hourField, -140.0);

        TextField minField = new TextField();
        minField.setPromptText("MM");
        minField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 24px;");
        minField.setPrefWidth(400);
        AnchorPane.setTopAnchor(minField, 490.0);
        AnchorPane.setRightAnchor(minField, -180.0);

        TextField hourFieldS = new TextField();
        hourFieldS.setPromptText("HH");
        hourFieldS.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 24px;");
        hourFieldS.setPrefWidth(400);
        AnchorPane.setTopAnchor(hourFieldS, 590.0);
        AnchorPane.setRightAnchor(hourFieldS, -140.0);

        TextField minFieldS = new TextField();
        minFieldS.setPromptText("MM");
        minFieldS.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 24px;");
        minFieldS.setPrefWidth(400);
        AnchorPane.setTopAnchor(minFieldS, 590.0);
        AnchorPane.setRightAnchor(minFieldS, -180.0);

        Label inceputLabel = new Label("Ora inceput:");
        inceputLabel.setFont(SatoshiMedium);
        inceputLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setTopAnchor(inceputLabel , 450.0);
        AnchorPane.setLeftAnchor(inceputLabel , 30.0);

        Label sfarsitLabel = new Label("Ora sfarsit:");
        sfarsitLabel.setFont(SatoshiMedium);
        sfarsitLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setTopAnchor(sfarsitLabel , 550.0);
        AnchorPane.setLeftAnchor(sfarsitLabel , 40.0);

        Image Plus = new Image(path + "Plus.png");
        ImageView plusButton = new ImageView(Plus);
        setupButton(plusButton, 700, 150);
        plusButton.setFitWidth(100.0);
        plusButton.setFitHeight(100.0);

        if(currentYearMonth!=null)
        {
            String[] parts = currentYearMonth.toString().split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);

            plusButton.setOnMouseClicked(event -> {
                int[] start = new int[6];
                int[] end = new int[6];
                start[0] = 0;
                start[1] = Integer.parseInt(minField.getText());
                start[2] = Integer.parseInt(hourField.getText());
                start[3] = Integer.parseInt(selectedDayButton.getText());
                start[4] = month;
                start[5] = year;

                end[0] = 0;
                end[1] = Integer.parseInt(minFieldS.getText());
                end[2] = Integer.parseInt(hourFieldS.getText());
                end[3] = Integer.parseInt(selectedDayButton.getText());
                end[4] = month;
                end[5] = year;
                System.out.println(currentActivity);
                p.programareActivitateExistenta(currentActivity,start,end);
                updateCalendar(currentYearMonth, p, root);
            });
        }

        root.getChildren().addAll(panelImage,hourField,minField,plusButton,hourFieldS,minFieldS,inceputLabel,sfarsitLabel);
        //root.getChildren().addAll(hourField,minField,plusButton,hourFieldS,minFieldS);

        return root;
    }

    private AnchorPane showSidePanel(StudentBackend p) {
        int year = 2025;
        int month = 1;
        int day = 1;
        if(currentYearMonth!=null)
        {
            String[] parts = currentYearMonth.toString().split("-");
            year = Integer.parseInt(parts[0]);
            month = Integer.parseInt(parts[1]);
            day = Integer.parseInt(selectedDayButton.getText());
        }
        //System.out.println(day+" "+month+" "+year);
        //System.out.println(p.getHours(idActivity,day,month,year)[0]);

        AnchorPane root = new AnchorPane();
        Font SatoshiMedium = Font.loadFont(fontPath + "Satoshi-Medium.otf", 24);
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 24);

        Image Panel = new Image(cursPath + "Panel.png");
        ImageView panelImage = new ImageView(Panel);
        AnchorPane.setTopAnchor(panelImage, 240.0);
        AnchorPane.setRightAnchor(panelImage, 100.0);
        panelImage.setFitHeight(650);
        panelImage.setFitWidth(200);

        Label inceputLabel = new Label("Ora inceput:");
        inceputLabel.setFont(SatoshiMedium);
        inceputLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setTopAnchor(inceputLabel , 450.0);
        AnchorPane.setLeftAnchor(inceputLabel , 30.0);

        Label inceputOraLabel = new Label(p.getHours(currentActivity,day,month,year)[0]);
        inceputOraLabel.setFont(SatoshiLight);
        inceputOraLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setTopAnchor(inceputOraLabel , 500.0);
        AnchorPane.setLeftAnchor(inceputOraLabel , 70.0);

        Label sfarsitLabel = new Label("Ora sfarsit:");
        sfarsitLabel.setFont(SatoshiMedium);
        sfarsitLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setTopAnchor(sfarsitLabel , 550.0);
        AnchorPane.setLeftAnchor(sfarsitLabel , 40.0);

        Label sfarsitOraLabel = new Label(p.getHours(currentActivity,day,month,year)[1]);
        sfarsitOraLabel.setFont(SatoshiLight);
        sfarsitOraLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setTopAnchor(sfarsitOraLabel , 600.0);
        AnchorPane.setLeftAnchor(sfarsitOraLabel , 70.0);

        root.getChildren().addAll(panelImage,inceputLabel,sfarsitLabel,inceputOraLabel,sfarsitOraLabel);

        return root;
    }
}
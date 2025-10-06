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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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

import static PlatformaStudiu.LoginPage.globalId;

import java.util.List;

public class SupAdminCauta extends Application {

    private String path;
    private String fontPath;
    private static int idUtilizator;
    private static int filter = 0;
    private static String cautaNume;

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

        SuperAdminBackend a = new SuperAdminBackend(globalId);

        ScrollPane scrollPane = utilizatorList(a,root,primaryStage);
        String cssPath="src/PlatformaStudiu/invisible.css";
        java.io.File cssFile = new java.io.File(cssPath);
        try {
            scrollPane.getStylesheets().add(cssFile.toURI().toURL().toExternalForm());
        }catch (Exception e) {e.printStackTrace();}
        System.out.println("CSS loaded from file: " + cssFile.getAbsolutePath());
        System.out.println(cssFile.getAbsolutePath());

        AnchorPane.setTopAnchor(scrollPane, 240.0);
        AnchorPane.setBottomAnchor(scrollPane, 100.0);
        AnchorPane.setRightAnchor(scrollPane, 80.0);
        scrollPane.setPrefHeight(700);
        scrollPane.setPrefWidth(1020);

        root.getChildren().add(scrollPane);

        if(idUtilizator == 0)
        {
            searchBar(root,primaryStage);
        }
        else
        {
            VBox panel = createPanel();
            AnchorPane.setTopAnchor(panel, 300.0);
            AnchorPane.setLeftAnchor(panel, 500.0);
            root.getChildren().add(panel);

            Utilizator u = new Utilizator(idUtilizator);
            VBox data = userData(u,a,primaryStage);
            AnchorPane.setTopAnchor(data, 280.0);
            AnchorPane.setLeftAnchor(data, 740.0);
            root.getChildren().add(data);

            createPanel(a,root,primaryStage);
        }

        createMenu(root,primaryStage);

        Scene scene = new Scene(root, screenWidth, screenHeight);

        Rectangle background = new Rectangle(0, 0, screenWidth, screenHeight);
        LinearGradient linearGradient = new LinearGradient(
                0, 0, 1, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#000000")),
                new Stop(1, Color.web("#000000"))
        );
        background.setFill(linearGradient);

        root.getChildren().add(0, background);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Professor Page");
        primaryStage.show();
    }

    private void createPanel(SuperAdminBackend p,AnchorPane root, Stage primaryStage) {

        Image Download = new Image(path + "NewBack.png");
        ImageView downloadButton = new ImageView(Download);
        setupButton(downloadButton, 100.0, 940.0);
        downloadButton.setFitWidth(130.0);
        downloadButton.setFitHeight(130.0);

        Image Selection = new Image(path + "StergeSel.png");
        ImageView currentSel = new ImageView(Selection);
        currentSel.setFitWidth(610);
        currentSel.setFitHeight(130);
        AnchorPane.setTopAnchor(currentSel, 100.0);
        AnchorPane.setRightAnchor(currentSel, 300.0);
        currentSel.setVisible(false);

        root.getChildren().add(currentSel);

        Image Sterge = new Image(path + "Sterge.png");
        ImageView stergeButton = new ImageView(Sterge);
        setupButton(stergeButton, 100.0, 300.0);
        stergeButton.setFitWidth(610.5);
        stergeButton.setFitHeight(130.5);

        stergeButton.setOnMouseClicked(e -> {
            p.deleteUtilizator(idUtilizator);
            idUtilizator = 0;
            SupAdminCauta professorPage = new SupAdminCauta();
            professorPage.start(primaryStage);
        });

        downloadButton.setOnMouseClicked(e -> {
            idUtilizator = 0;
            SupAdminCauta professorPage = new SupAdminCauta();
            professorPage.start(primaryStage);
        });

        Image SelDownload = new Image(path + "SelDownload.png");
        ImageView downloadSel = new ImageView(SelDownload);
        downloadSel.setFitWidth(130);
        downloadSel.setFitHeight(130);
        AnchorPane.setTopAnchor(downloadSel, 100.0);
        downloadSel.setVisible(false);
        root.getChildren().add(downloadSel);

        hoverEffect(stergeButton, currentSel, 300.0);

        hoverEffect(downloadButton, downloadSel, 940.0);

        root.getChildren().addAll(stergeButton, downloadButton);
    }

    private VBox userData(Utilizator p, SuperAdminBackend a, Stage primaryStage) {
        VBox vbox = new VBox(1);
        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Light.otf", 32);

        Table t = p.viewProfile();

        TextField idField = new TextField(t.getData(0, 0));
        idField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        idField.setFont(Satoshi);

        TextField tipField = new TextField(t.getData(0, 1));
        tipField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        tipField.setFont(Satoshi);

        TextField numeField = new TextField(t.getData(0, 3));
        numeField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        numeField.setFont(Satoshi);
        numeField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                a.changeNume(idUtilizator, numeField.getText());
                SupAdminCauta professorPage = new SupAdminCauta();
                professorPage.start(primaryStage);
            }
        });

        TextField prenumeField = new TextField(t.getData(0, 4));
        prenumeField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        prenumeField.setFont(Satoshi);
        prenumeField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                a.changePrenume(idUtilizator, prenumeField.getText());
                SupAdminCauta professorPage = new SupAdminCauta();
                professorPage.start(primaryStage);
            }
        });

        TextField cnpField = new TextField(t.getData(0, 2));
        cnpField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        cnpField.setFont(Satoshi);
        cnpField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                a.changeCNP(idUtilizator, cnpField.getText());
                SupAdminCauta professorPage = new SupAdminCauta();
                professorPage.start(primaryStage);
            }
        });

        TextField emailField = new TextField(t.getData(0, 6));
        emailField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        emailField.setFont(Satoshi);
        emailField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                a.changeEmail(idUtilizator, emailField.getText());
                SupAdminCauta professorPage = new SupAdminCauta();
                professorPage.start(primaryStage);
            }
        });

        TextField telField = new TextField(t.getData(0, 5));
        telField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        telField.setFont(Satoshi);
        telField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                a.changeNumarTelefon(idUtilizator, telField.getText());
                SupAdminCauta professorPage = new SupAdminCauta();
                professorPage.start(primaryStage);
            }
        });

        TextField ibanField = new TextField(t.getData(0, 7));
        ibanField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        ibanField.setFont(Satoshi);
        ibanField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                a.changeContIBAN(idUtilizator, ibanField.getText());
                SupAdminCauta professorPage = new SupAdminCauta();
                professorPage.start(primaryStage);
            }
        });

        TextField contractField = new TextField(t.getData(0, 8));
        contractField.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: white;");
        contractField.setFont(Satoshi);
        contractField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                a.changeNumarContact(idUtilizator, Integer.parseInt(contractField.getText()));
                SupAdminCauta professorPage = new SupAdminCauta();
                professorPage.start(primaryStage);
            }
        });

        vbox.getChildren().addAll(
                idField, tipField, numeField, prenumeField, cnpField, emailField, telField, ibanField, contractField
        );

        return vbox;
    }

    private VBox createPanel()
    {
        VBox vbox = new VBox(25);
        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Medium.otf", 32);

        Label idLabel = new Label("Id:");
        idLabel.setStyle("-fx-text-fill: white;");
        idLabel.setFont(Satoshi);

        Label tipLabel = new Label("Tip:");
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

    private ScrollPane utilizatorList(SuperAdminBackend s, AnchorPane root, Stage primaryStage) {
        VBox buttonList = new VBox(10);
        buttonList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        Table t = new Table();
        if(filter == 0)
        {
            t = s.getAllUtilizatori();
        }
        else if(filter == 1)
        {
            t = s.getAllStudents();
        }
        else if(filter == 2)
        {
            t = s.getAllProfesors();
        }
        else if(filter == 3)
        {
            t = s.getAllAdmin();
        }
        else
        {
            String[] parts = cautaNume.split(" ");
            String prenume = parts[0];
            String nume = parts[1];

            System.out.println(nume);

            t = s.cautaUtilizator(prenume, nume);
        }
        System.out.println(t);
        if(idUtilizator == 0)
        {
            for(int i=0; i<t.getColCount(); i++)
            {
                Button customButton = createCustomButton(s,t.getData(i,0), t.getData(i,1),t.getData(i,2), primaryStage);
                buttonList.getChildren().add(customButton);
            }
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private Button createCustomButton(SuperAdminBackend p, String labelId, String nume, String prenume, Stage primaryStage) {
        AnchorPane root = new AnchorPane();

        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        Image Activitate = new Image(path + "Activitate.png");
        ImageView imageView = new ImageView(Activitate);
        imageView.setFitWidth(880);
        imageView.setFitHeight(80);

        Label label1 = new Label(labelId);
        AnchorPane.setTopAnchor(label1, 20.0);
        AnchorPane.setLeftAnchor(label1, 60.0);

        Label label2 = new Label(nume);
        AnchorPane.setTopAnchor(label2, 20.0);
        AnchorPane.setLeftAnchor(label2, 320.0);

        Label label3 = new Label(prenume);
        AnchorPane.setTopAnchor(label3, 20.0);
        AnchorPane.setLeftAnchor(label3, 680.0);

        label1.setStyle("-fx-background-color: transparent;-fx-font-family: 'Arial Black'; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        label2.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        label3.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");

        root.getChildren().addAll(imageView, label1, label2, label3);

        button.setOnAction(event -> {
            idUtilizator = Integer.parseInt(labelId);
            SupAdminCauta professorPage = new SupAdminCauta();
            professorPage.start(primaryStage);
        });

        button.setGraphic(root);

        return button;
    }

    private void searchBar(AnchorPane root, Stage primaryStage) {
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 38);

        Image Search = new Image(path + "Search.png");
        ImageView searchButton = new ImageView(Search);
        setupButton(searchButton,120.0,1000.0);
        searchButton.setFitWidth(80);
        searchButton.setFitHeight(80);

        Image Student = new Image(path + "Student.png");
        ImageView studentButton = new ImageView(Student);
        setupButton(studentButton,120.0,360.0);
        studentButton.setFitWidth(80);
        studentButton.setFitHeight(80);

        Image Profesor = new Image(path + "Profesor.png");
        ImageView profesorButton = new ImageView(Profesor);
        setupButton(profesorButton,120.0,240.0);
        profesorButton.setFitWidth(80);
        profesorButton.setFitHeight(80);

        Image Admin = new Image(path + "Admin.png");
        ImageView adminButton = new ImageView(Admin);
        setupButton(adminButton,120.0,120.0);
        adminButton.setFitWidth(80);
        adminButton.setFitHeight(80);

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

        studentButton.setOnMouseClicked(e -> {
            if(filter == 1)
            {
                filter = 0;
            }
            else
            {
                filter = 1;
            }
            SupAdminCauta professorPage = new SupAdminCauta();
            professorPage.start(primaryStage);
        });

        profesorButton.setOnMouseClicked(e -> {
            if(filter == 2)
            {
                filter = 0;
            }
            else
            {
                filter = 2;
            }
            SupAdminCauta professorPage = new SupAdminCauta();
            professorPage.start(primaryStage);
        });

        adminButton.setOnMouseClicked(e -> {
            if(filter == 3)
            {
                filter = 0;
            }
            else
            {
                filter = 3;
            }
            SupAdminCauta professorPage = new SupAdminCauta();
            professorPage.start(primaryStage);
        });

        searchButton.setOnMouseClicked(e -> {
            if(filter == 4)
            {
                filter = 0;
            }
            else
            {
                filter = 4;
            }
            cautaNume = mesajField.getText();
            SupAdminCauta professorPage = new SupAdminCauta();
            professorPage.start(primaryStage);
        });

        root.getChildren().addAll(searchButton,searchBar,mesajField,studentButton,profesorButton,adminButton);
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
        AnchorPane.setTopAnchor(currentSel, 230.0);
        AnchorPane.setRightAnchor(currentSel, 1220.0);
        root.getChildren().add(currentSel);

        ImageView sel = new ImageView(Selection);
        sel.setFitWidth(280);
        sel.setFitHeight(120);
        sel.setVisible(false);
        root.getChildren().add(sel);

        addHoverEffect(profilButton, sel, 100.0);
        addHoverEffect(cursuriButton, sel, 360.0);
        addHoverEffect(adaugaButton, sel, 490.0);
        addHoverEffect(iesireButton, sel, 750.0);

        profilButton.setOnMouseClicked(e -> {
            SupAdminProfil professorPage = new SupAdminProfil();
            professorPage.start(primaryStage);
        });

        cursuriButton.setOnMouseClicked(e -> {
            SupAdminCursuri professorPage = new SupAdminCursuri();
            professorPage.start(primaryStage);
        });

        adaugaButton.setOnMouseClicked(e -> {
            SupAdminAdauga professorPage = new SupAdminAdauga();
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
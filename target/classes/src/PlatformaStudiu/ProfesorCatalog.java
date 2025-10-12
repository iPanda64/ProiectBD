package PlatformaStudiu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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

import java.util.List;
import java.util.Objects;

import static PlatformaStudiu.LoginPage.globalId;

public class ProfesorCatalog extends Application {

    private String path;
    private String fontPath;
    private static int currentCourse = 1;
    private static boolean show = false;

    public void PrintStudents(List<String[]> students, int idC, ProfesorBackend p)
    {
        Table t = p.getCatalog();
        int k = 0;
        for(int i=0;i<t.getColCount();i++)
        {
            String[] s = new String[20];
            if(Integer.parseInt(t.getData(i,0)) == currentCourse)
            {
                k=0;
                for(int j=2; j<5;j++)
                {
                    s[k]=t.getData(i,j);
                    k++;
                }
                students.add(s);
            }
        }
    }

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

        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Light.otf", 38);

        AnchorPane root = new AnchorPane();

        show = false;

        ProfesorBackend p = new ProfesorBackend(globalId);
        Table t = p.getCatalog();
        //currentCourse = Integer.parseInt(t.getData(0,0));

        Image Bar = new Image(path + "Activitate.png");
        ImageView searchBar = new ImageView(Bar);
        searchBar.setFitWidth(770);
        searchBar.setFitHeight(70);
        AnchorPane.setTopAnchor(searchBar, 110.0);
        AnchorPane.setRightAnchor(searchBar, 300.0);

        Table q = p.getOnlyCursuri();
        String s = new String();
        for(int i=0;i<q.getColCount();i++)
        {
            if(Integer.parseInt(q.getData(i,0))==currentCourse)
            {
                s = q.getData(i,1);
            }
        }
        Label welcomeLabel = new Label(s);
        welcomeLabel.setStyle("-fx-text-fill: white;");
        welcomeLabel.setFont(Satoshi);
        AnchorPane.setTopAnchor(welcomeLabel, 120.0);
        AnchorPane.setLeftAnchor(welcomeLabel, 500.0);

        Image Arrow = new Image(path + "DownArrow.png");
        ImageView downButton = new ImageView(Arrow);
        setupButton(downButton, 110.0, 200.0);
        downButton.setFitWidth(70);
        downButton.setFitHeight(70);

        root.getChildren().addAll(searchBar, welcomeLabel, downButton);

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

        Image Download = new Image(path + "Download.png");
        ImageView downloadButton = new ImageView(Download);
        setupButton(downloadButton, 100.0, 60.0);
        downloadButton.setFitWidth(100.0);
        downloadButton.setFitHeight(100.0);

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
        addHoverEffect(iesireButton, sel, 750.0);

        profilButton.setOnMouseClicked(e -> {
            ProfesorProfil professorPage = new ProfesorProfil();
            professorPage.start(primaryStage);
        });

        cursuriButton.setOnMouseClicked(e -> {
            ProfesorCursuri professorPage = new ProfesorCursuri();
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

        downloadButton.setOnMouseClicked(e -> {
            p.downloadCatalog();
        });

        /*

        MenuButton menuButton = new MenuButton(c[0]);
        menuButton.setText(c[currentCourse]);
        for (int i = 0; i < cursId; i++) {
            String itemValue = c[i];
            int id = i;
            MenuItem button = new MenuItem(itemValue);

            button.setOnAction(e -> {
                currentCourse = id;
                ProfesorCatalog professorPage = new ProfesorCatalog();
                professorPage.start(primaryStage);
            });

            menuButton.getItems().add(button);
        }

         */

        root.getChildren().addAll(devideLine,profilButton,cursuriButton,catalogButton,activitatiButton,iesireButton,downloadButton);

        ScrollPane scrollPane = new ScrollPane();
        GridPane rightList = new GridPane();
        rightList.setVgap(10);
        rightList.setHgap(10);
        rightList.setPadding(new Insets(10));
        rightList.setAlignment(Pos.TOP_LEFT);

        List<String[]> students = new java.util.ArrayList<>(List.of());
        PrintStudents(students, Integer.parseInt(t.getData(0,0)),p);

        String listStyle = " -fx-font-size: 32px; -fx-font-family: 'SatoshiLight';fx-border-color: white; -fx-border-width: 0 0 0 0;";

        int rowIndex = 0;
        for (String[] student : students) {
            Label nameLabel = new Label("     " + student[0]);
            nameLabel.setStyle(listStyle);

            Label emailLabel = new Label(student[1]);
            emailLabel.setStyle(listStyle);

            Label numberLabel = new Label(student[2]);
            numberLabel.setStyle(listStyle);

            Label delimiter1 = new Label("                ");
            delimiter1.setStyle("-fx-font-family: 'Satoshi'; -fx-font-size: 28px; -fx-text-fill: white; -fx-padding: 0 20px;");

            Label delimiter2 = new Label("                ");
            delimiter2.setStyle("-fx-font-family: 'Satoshi'; -fx-font-size: 28px; -fx-text-fill: white; -fx-padding: 0 20px;");

            rightList.add(nameLabel, 0, rowIndex);
            rightList.add(delimiter1, 1, rowIndex);
            rightList.add(emailLabel, 2, rowIndex);
            rightList.add(delimiter2, 3, rowIndex);
            rightList.add(numberLabel, 4, rowIndex);

            Line line = new Line();
            line.setStartX(0);
            line.setStartY(0);
            line.setEndX(850);
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

        scrollPane.setContent(rightList);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background: transparent;");

        AnchorPane.setTopAnchor(scrollPane, 250.0);
        AnchorPane.setRightAnchor(scrollPane, 50.0);
        AnchorPane.setBottomAnchor(scrollPane, 100.0);
        AnchorPane.setLeftAnchor(scrollPane, screenWidth / 2 - 300);

        String cssPath_cursPane="src/PlatformaStudiu/scrollpane-style.css";
        java.io.File cssFile_Pane1 = new java.io.File(cssPath_cursPane);
        try {
            scrollPane.getStylesheets().add(cssFile_Pane1.toURI().toURL().toExternalForm());
        }catch (Exception e1) {e1.printStackTrace();}

        root.getChildren().add(scrollPane);

        scrollPane.setStyle("-fx-background: transparent;");

        downButton.setOnMouseClicked(e -> {
            show = !show;

            if(show)
            {

                ScrollPane cursPane = cursList(p,root,primaryStage);

                String cssPath_Pane="src/PlatformaStudiu/styles.css";
                java.io.File cssFile_Pane = new java.io.File(cssPath_Pane);
                try {
                    cursPane.getStylesheets().add(cssFile_Pane.toURI().toURL().toExternalForm());
                }catch (Exception e1) {e1.printStackTrace();}

                AnchorPane.setTopAnchor(cursPane, 190.0);
                AnchorPane.setRightAnchor(cursPane, 100.0);
                AnchorPane.setLeftAnchor(cursPane, 460.0);
                cursPane.setPrefHeight(700);
                cursPane.setPrefWidth(1020);
                root.getChildren().add(cursPane);
            }
            else
            {
                root.getChildren().removeLast();
            }
        });

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

    private ScrollPane cursList(ProfesorBackend a, AnchorPane root, Stage primaryStage) {
        VBox buttonList = new VBox(10);
        buttonList.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        Table t = a.getOnlyCursuri();

        for(int i=0; i<t.getColCount(); i++)
        {
            Button customButton = createCustomButton(a,t.getData(i,1), Integer.parseInt(t.getData(i,0)), primaryStage, root);
            buttonList.getChildren().add(customButton);
        }

        ScrollPane scrollPane = new ScrollPane(buttonList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return scrollPane;
    }

    private Button createCustomButton(ProfesorBackend s, String label1Text, int idC, Stage primaryStage,AnchorPane root) {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 30);

        Image Activitate = new Image(path + "List.png");
        ImageView imageView = new ImageView(Activitate);
        imageView.setFitWidth(770);
        imageView.setFitHeight(70);

        Label label1 = new Label(label1Text);
        label1.setStyle("-fx-text-fill: black; -fx-font-size: 34px;");
        label1.setFont(SatoshiLight);

        StackPane labelStack = new StackPane(label1);
        labelStack.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(imageView, labelStack);
        stackPane.setStyle("-fx-alignment: center;");

        button.setOnAction(event -> {
            currentCourse = idC;
            System.out.println(currentCourse);
            ProfesorCatalog professorPage = new ProfesorCatalog();
            professorPage.start(primaryStage);
        });

        button.setGraphic(stackPane);

        return button;
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
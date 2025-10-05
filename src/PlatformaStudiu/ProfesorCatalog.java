package PlatformaStudiu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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

public class ProfesorCatalog extends Application {

    private String path = "file:/C:/Users/mihai/Desktop/School/An 2 Sem 1/BD/Proiect/ProiectPng/Meniu/";
    private String fontPath = "file:/C:/Users/mihai/Desktop/School/An 2 Sem 1/BD/Proiect/ProiectPng/Fonts/";
    /**
     *
     * */
    public void setCatalog(List<String> student){

    }

    public void start(Stage primaryStage) {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 38);

        AnchorPane root = new AnchorPane();

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

        root.getChildren().addAll(devideLine,profilButton,cursuriButton,catalogButton,activitatiButton,iesireButton);


        ScrollPane scrollPane = new ScrollPane();
        GridPane rightList = new GridPane(); // Change to GridPane for better control
        rightList.setVgap(10); // Vertical gap between rows
        rightList.setHgap(10); // Horizontal gap between columns
        rightList.setPadding(new Insets(10));
        rightList.setAlignment(Pos.TOP_LEFT);

        // Example data
        List<String[]> students = List.of(
                new String[]{"Buleu Mihai-Andrei", "mihai@gmail.com", "2"},
                new String[]{"Buleu Mihai-Andrei", "mihai@gmail.com", "2"},
                new String[]{"Buleu Mihai-Andrei", "mihai@gmail.com", "2"},
                new String[]{"Buleu Mihai-Andrei", "mihai@gmail.com", "2"},
                new String[]{"Buleu Mihai-Andrei", "mihai@gmail.com", "2"},
                new String[]{"Souca Vlad-Cristian", "souca@gmail.com", "10"},
                new String[]{"Souca Vlad-Cristian", "souca@gmail.com", "10"},
                new String[]{"Souca Vlad-Cristian", "souca@gmail.com", "10"},
                new String[]{"Souca Vlad-Cristian", "souca@gmail.com", "10"},
                new String[]{"Souca Vlad-Cristian", "souca@gmail.com", "10"},
                new String[]{"John Doe", "john@example.com", "8"},
                new String[]{"John Doe", "john@example.com", "8"},
                new String[]{"John Doe", "john@example.com", "8"},
                new String[]{"John Doe", "john@example.com", "8"},
                new String[]{"Jane Doe", "jane@example.com", "9"}
        );
        ProfesorBackend p=new ProfesorBackend(20);
        Table t=p.getCatalog();
        System.out.println(t);
        for(int i=0;i<t.getColCount();++i)
        {for(int j=0;j<t.getRowCount();++j)
                System.out.println(t.getData(i,j));
        System.out.println();}

        System.out.println(p.getActivitatiToateZilele());

        String listStyle = " -fx-font-size: 32px; -fx-font-family: 'SatoshiLight';fx-border-color: white; -fx-border-width: 0 0 0 0;";

        int rowIndex = 0;
        for (String[] student : students) {
            Label nameLabel = new Label("     " + student[0]);
            nameLabel.setStyle(listStyle);

            Label emailLabel = new Label(student[1]);
            emailLabel.setStyle(listStyle);

            Label numberLabel = new Label(student[2]);
            numberLabel.setStyle(listStyle);

            Label delimiter1 = new Label("     ");
            delimiter1.setStyle("-fx-font-family: 'Satoshi'; -fx-font-size: 28px; -fx-text-fill: white; -fx-padding: 0 20px;");

            Label delimiter2 = new Label(" ");
            delimiter2.setStyle("-fx-font-family: 'Satoshi'; -fx-font-size: 28px; -fx-text-fill: white; -fx-padding: 0 20px;");

            // Adding columns for each part of the row
            rightList.add(nameLabel, 0, rowIndex);   // First column: Name
            rightList.add(delimiter1, 1, rowIndex);   // Second column: First delimiter
            rightList.add(emailLabel, 2, rowIndex);  // Third column: Email
            rightList.add(delimiter2, 3, rowIndex);   // Fourth column: Second delimiter
            rightList.add(numberLabel, 4, rowIndex); // Fifth column: Number

            // Add a line below the row
            Line line = new Line();
            line.setStartX(0);
            line.setStartY(0);
            line.setEndX(850);  // Width of the line (adjust as needed)
            line.setEndY(0);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(4);
            line.setStrokeType(StrokeType.CENTERED);

            // Add the line below the current row (on the next row)
            if(rowIndex < students.size() - 1)
            {
                rightList.add(line, 0, rowIndex , 5, 2); // Span the whole width (5 columns)
            }

            rowIndex++;// Move to the next row
        }

        // Wrap GridPane in ScrollPane
        scrollPane.setContent(rightList);
        scrollPane.setFitToWidth(true); // Ensure the content width matches the ScrollPane width
        scrollPane.setPannable(true);   // Allow panning by dragging
        scrollPane.setStyle("-fx-background: transparent;");

        // Position the ScrollPane
        AnchorPane.setTopAnchor(scrollPane, 250.0);
        AnchorPane.setRightAnchor(scrollPane, 50.0);
        AnchorPane.setBottomAnchor(scrollPane, 100.0);
        AnchorPane.setLeftAnchor(scrollPane, screenWidth / 2 - 300);

        scrollPane.getStylesheets().add(getClass().getResource("scrollpane-style.css").toExternalForm());

        root.getChildren().add(scrollPane);

        scrollPane.setStyle("-fx-background: transparent;");  // Transparent background

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
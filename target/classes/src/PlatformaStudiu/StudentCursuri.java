package PlatformaStudiu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import java.util.Objects;

import static PlatformaStudiu.LoginPage.globalId;

public class StudentCursuri extends Application {

    private String path;
    private String cursPath;
    private String fontPath;
    private static int currentCourse;
    private static int currentActivity;
    private static int idActivity;

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
        Font SatoshiMedium = Font.loadFont(fontPath + "Satoshi-Medium.otf", 38);

        AnchorPane root = new AnchorPane();

        StudentBackend s = new StudentBackend(globalId);
        Table t = s.getCursuri();
        System.out.println(t);
        String[] c = new String[20];
        String[] a = {"Curs", "Seminar", "Laborator", "Colocviu", "Examen"};
        c[0] = t.getData(0,0);
        int cursId = 1;
        for(int i=1;i<t.getColCount();i++)
        {
            boolean exists = false;
            for(int j=0;j<cursId;j++)
            {
                if(Objects.equals(c[j], t.getData(i, 0)))
                {
                    exists = true;
                }
            }

            if(!exists)
            {
                c[cursId] = t.getData(i,0);
                cursId++;
            }
        }

        VBox vbox = createRows(s,primaryStage,c,cursId);
        vbox.setLayoutX(0);
        vbox.setLayoutY(150);

        if(currentActivity == 0)
        {
            root.getChildren().addAll(vbox);
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

        if(currentActivity != 0)
        {
            Image Back = new Image(path + "Back.png");
            ImageView backButton = new ImageView(Back);
            setupButton(backButton, 100.0, 1020.0);
            backButton.setFitWidth(100);
            backButton.setFitHeight(100);
            backButton.setOnMouseClicked(e -> {
                currentActivity = 0;
                idActivity = 0;
                StudentCursuri professorPage = new StudentCursuri();
                professorPage.start(primaryStage);
            });

            Label titleLabel = new Label(c[currentCourse]+" - "+a[currentActivity-1]);
            titleLabel.setFont(SatoshiMedium);
            titleLabel.setStyle("-fx-text-fill: white;");
            AnchorPane.setTopAnchor(titleLabel , 125.0);
            root.getChildren().add(titleLabel);
            Platform.runLater(() -> {
                double labelWidth = titleLabel.getWidth();
                AnchorPane.setLeftAnchor(titleLabel, screenWidth / 2 - labelWidth / 2 + 200);
            });

            root.getChildren().add(backButton);
        }

        createMenu(root,primaryStage);

        if(idActivity != 0)
        {
            AnchorPane sidePanel = new AnchorPane();
            sidePanel = createSidePanel(s);
            AnchorPane.setRightAnchor(sidePanel , 0.0);
            root.getChildren().add(sidePanel);
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
        AnchorPane.setTopAnchor(currentSel, 230.0);
        AnchorPane.setRightAnchor(currentSel, 1220.0);
        root.getChildren().add(currentSel);

        ImageView sel = new ImageView(Selection);
        sel.setFitWidth(280);
        sel.setFitHeight(120);
        sel.setVisible(false);
        root.getChildren().add(sel);

        addHoverEffect(profilButton, sel, 100.0);
        addHoverEffect(cautaButton, sel, 360.0);
        addHoverEffect(activitatiButton, sel, 490.0);
        addHoverEffect(mesajeButton, sel, 620.0);
        addHoverEffect(iesireButton, sel, 750.0);

        profilButton.setOnMouseClicked(e -> {
            StudentProfil professorPage = new StudentProfil();
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

    private AnchorPane createRow(StudentBackend p, String title, String[] c, int cursId, Stage primaryStage) {

        AnchorPane row = new AnchorPane();
        Font Satoshi = Font.loadFont(fontPath + "Satoshi-Light.otf", 38);

        Double X = 400.0;
        Double Y = 0.0;

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-text-fill: white;");
        titleLabel.setFont(Satoshi);
        AnchorPane.setTopAnchor(titleLabel, Y+40);
        AnchorPane.setLeftAnchor(titleLabel, X+30);

        Image Bara = new Image(cursPath + "Bara.png");
        ImageView bara = new ImageView(Bara);
        bara.setFitWidth(1100);
        bara.setFitHeight(130);
        AnchorPane.setTopAnchor(bara, Y);
        AnchorPane.setLeftAnchor(bara, X);

        Image Curs = new Image(cursPath + "Curs.png");
        ImageView cursButton = new ImageView(Curs);
        setupButton(cursButton, Y+10, X-390);
        cursButton.setFitWidth(110.0);
        cursButton.setFitHeight(110.0);

        Image Seminar = new Image(cursPath + "Seminar.png");
        ImageView seminarButton = new ImageView(Seminar);
        setupButton(seminarButton, Y+10, X-270);
        seminarButton.setFitWidth(110.0);
        seminarButton.setFitHeight(110.0);

        Image Laborator = new Image(cursPath + "Laborator.png");
        ImageView labButton = new ImageView(Laborator);
        setupButton(labButton, Y+10, X-150);
        labButton.setFitWidth(110.0);
        labButton.setFitHeight(110.0);

        Image Colocviu = new Image(cursPath + "Colocviu.png");
        ImageView colocviuButton = new ImageView(Colocviu);
        setupButton(colocviuButton, Y+10, X-30);
        colocviuButton.setFitWidth(110.0);
        colocviuButton.setFitHeight(110.0);

        Image Examen = new Image(cursPath + "Examen.png");
        ImageView examenButton = new ImageView(Examen);
        setupButton(examenButton, Y+10, X+90);
        examenButton.setFitWidth(110.0);
        examenButton.setFitHeight(110.0);

        Table t = p.getCursuri();
        int[][] buttons = new int[10][5];
        for(int i=0;i<t.getColCount();i++)
        {
            if(title.equals(t.getData(i,0)))
            {
                if(t.getData(i,1).equals("Curs"))
                {
                    buttons[cursId][0] = Integer.parseInt(t.getData(i,2));
                }
                if(t.getData(i,1).equals("Seminar"))
                {
                    buttons[cursId][1] = Integer.parseInt(t.getData(i,2));
                }
                if(t.getData(i,1).equals("Laborator"))
                {
                    buttons[cursId][2] = Integer.parseInt(t.getData(i,2));
                }
                if(t.getData(i,1).equals("Colocviu"))
                {
                    buttons[cursId][3] = Integer.parseInt(t.getData(i,2));
                }
                if(t.getData(i,1).equals("Examen"))
                {
                    buttons[cursId][4] = Integer.parseInt(t.getData(i,2));
                }
            }
        }

        if(buttons[cursId][0] != 0)
        {
            cursButton.setOnMouseClicked(e -> {
                idActivity = buttons[cursId][0];
                currentCourse = cursId;
                currentActivity = 1;
                StudentCursuri professorPage = new StudentCursuri();
                professorPage.start(primaryStage);
            });
        }
        else
        {
            cursButton.setOpacity(0.3);
        }

        if(buttons[cursId][1] != 0)
        {
            seminarButton.setOnMouseClicked(e -> {
                idActivity = buttons[cursId][1];
                currentCourse = cursId;
                currentActivity = 2;
                StudentCursuri professorPage = new StudentCursuri();
                professorPage.start(primaryStage);
            });
        }
        else
        {
            seminarButton.setOpacity(0.3);
        }

        if(buttons[cursId][2] != 0)
        {
            labButton.setOnMouseClicked(e -> {
                idActivity = buttons[cursId][2];
                currentCourse = cursId;
                currentActivity = 3;
                StudentCursuri professorPage = new StudentCursuri();
                professorPage.start(primaryStage);
            });
        }
        else
        {
            labButton.setOpacity(0.3);
        }

        if(buttons[cursId][3] != 0)
        {
            colocviuButton.setOnMouseClicked(e -> {
                idActivity = buttons[cursId][3];
                currentCourse = cursId;
                currentActivity = 4;
                StudentCursuri professorPage = new StudentCursuri();
                professorPage.start(primaryStage);
            });
        }
        else
        {
            colocviuButton.setOpacity(0.3);
        }

        if(buttons[cursId][4] != 0)
        {
            examenButton.setOnMouseClicked(e -> {
                idActivity = buttons[cursId][4];
                currentCourse = cursId;
                currentActivity = 5;
                StudentCursuri professorPage = new StudentCursuri();
                professorPage.start(primaryStage);
            });
        }
        else
        {
            examenButton.setOpacity(0.3);
        }

        row.getChildren().addAll(bara,titleLabel,cursButton,seminarButton,labButton,colocviuButton,examenButton);

        return row;
    }

    private VBox createRows(StudentBackend s, Stage primaryStage, String[] c, int cursId) {
        VBox vbox = new VBox(20);

        for(int i=0;i<cursId;i++)
        {
            vbox.getChildren().add(createRow(s,c[i],c,i, primaryStage));
        }

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

        Table q = p.getCalendar(idActivity);
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

        // Set column constraints
        for (int i = 0; i < 7; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.ALWAYS);
            colConstraints.setPercentWidth(100.0 / 7);
            calendarGrid.getColumnConstraints().add(colConstraints);
        }

        // Set row constraints
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

    private AnchorPane createSidePanel(StudentBackend p)
    {
        AnchorPane root = new AnchorPane();

        Image Panel = new Image(cursPath + "Panel.png");
        ImageView panelImage = new ImageView(Panel);
        AnchorPane.setTopAnchor(panelImage, 240.0);
        AnchorPane.setRightAnchor(panelImage, 100.0);
        panelImage.setFitHeight(650);
        panelImage.setFitWidth(200);

        root.getChildren().addAll(panelImage);

        return root;
    }

    private AnchorPane showSidePanel(StudentBackend p)
    {
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

        Label inceputOraLabel = new Label(p.getHours(idActivity,day,month,year)[0]);
        inceputOraLabel.setFont(SatoshiLight);
        inceputOraLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setTopAnchor(inceputOraLabel , 500.0);
        AnchorPane.setLeftAnchor(inceputOraLabel , 70.0);

        Label sfarsitLabel = new Label("Ora sfarsit:");
        sfarsitLabel.setFont(SatoshiMedium);
        sfarsitLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setTopAnchor(sfarsitLabel , 550.0);
        AnchorPane.setLeftAnchor(sfarsitLabel , 40.0);

        Label sfarsitOraLabel = new Label(p.getHours(idActivity,day,month,year)[1]);
        sfarsitOraLabel.setFont(SatoshiLight);
        sfarsitOraLabel.setStyle("-fx-text-fill: white;");
        AnchorPane.setTopAnchor(sfarsitOraLabel , 600.0);
        AnchorPane.setLeftAnchor(sfarsitOraLabel , 70.0);

        root.getChildren().addAll(panelImage,inceputLabel,sfarsitLabel,inceputOraLabel,sfarsitOraLabel);

        return root;
    }
}
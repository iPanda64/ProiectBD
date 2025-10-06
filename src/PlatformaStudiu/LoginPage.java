package PlatformaStudiu;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
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

public class LoginPage extends Application {

    private String path;
    private String fontPath;
    public static int globalId = 0;
    @Override

    public void start(Stage primaryStage) {
        String f="file:/";
        String path = System.getProperty("user.dir");
        path += "/Proiect/Login/";
        path = path.replace("\\", "/");
                f+=path;
        path=f;

        f="file:/";
        String fontPath=System.getProperty("user.dir");
        fontPath+="/Proiect/Fonts/";
        fontPath=fontPath.replace("\\", "/");
                f+=fontPath;
        fontPath=f;

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        Font SatoshiLight = Font.loadFont(fontPath + "Satoshi-Light.otf", 28);

        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root,screenWidth,screenHeight);

        Image Icon = new Image(path + "Icon.png");
        ImageView profileIcon = new ImageView(Icon);
        profileIcon.setTranslateX(Icon.getWidth() / 2);
        profileIcon.setTranslateY(Icon.getHeight() / 2);
        profileIcon.setFitWidth(300);
        profileIcon.setFitHeight(300);
        AnchorPane.setTopAnchor(profileIcon, -100.0);
        AnchorPane.setRightAnchor(profileIcon, 800.0);

        Image Profile = new Image(path + "Profile.png");
        ImageView userIcon = new ImageView(Profile);
        userIcon.setTranslateX(Profile.getWidth() / 2);
        userIcon.setTranslateY(Profile.getHeight() / 2);
        userIcon.setFitWidth(600);
        userIcon.setFitHeight(600);
        AnchorPane.setTopAnchor(userIcon, -200.0);
        AnchorPane.setRightAnchor(userIcon, 850.0);

        TextField usernameField = new TextField();
        //usernameField.setFont(SatoshiLight);
        usernameField.setPromptText("username");
        usernameField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        usernameField.setPrefWidth(400);
        AnchorPane.setTopAnchor(usernameField, 440.0);
        AnchorPane.setRightAnchor(usernameField, 580.0);

        Image Lock = new Image(path + "Lock.png");
        ImageView lockIcon = new ImageView(Lock);
        lockIcon.setTranslateX(Lock.getWidth() / 2);
        lockIcon.setTranslateY(Lock.getHeight() / 2);
        lockIcon.setFitWidth(600);
        lockIcon.setFitHeight(600);
        AnchorPane.setTopAnchor(lockIcon, -100.0);
        AnchorPane.setRightAnchor(lockIcon, 850.0);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        passwordField.setPrefWidth(400);
        AnchorPane.setTopAnchor(passwordField, 540.0);
        AnchorPane.setRightAnchor(passwordField, 580.0);

        TextField textField = new TextField();
        textField.setPromptText("password");
        textField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #CCCCCC; -fx-border-width: 0 0 0 0; -fx-border-color: white; -fx-font-size: 28px;");
        textField.setPrefWidth(400);
        textField.setVisible(false);
        AnchorPane.setTopAnchor(textField, 540.0);
        AnchorPane.setRightAnchor(textField, 580.0);

        Image Eye = new Image(path + "Eye.png");
        ImageView eyeIcon = new ImageView(Eye);
        eyeIcon.setFitWidth(70);
        eyeIcon.setFitHeight(70);
        AnchorPane.setTopAnchor(eyeIcon, 530.0);
        AnchorPane.setRightAnchor(eyeIcon, 500.0);
        eyeIcon.setPickOnBounds(true);

        eyeIcon.setOnMouseClicked(e -> {
            if (passwordField.isVisible()) {
                textField.setText(passwordField.getText());
                passwordField.setVisible(false);
                textField.setVisible(true);
            } else {
                passwordField.setText(textField.getText());
                textField.setVisible(false);
                passwordField.setVisible(true);
            }
        });

        Image Login = new Image(path + "Login.png");
        ImageView loginButton = new ImageView(Login);
        loginButton.setFitWidth(400);
        loginButton.setFitHeight(100);
        AnchorPane.setTopAnchor(loginButton, 700.0);
        AnchorPane.setRightAnchor(loginButton, 570.0);
        loginButton.setPickOnBounds(true);

        loginButton.setOnMouseClicked(e -> {
            LogareBackend l=new LogareBackend(usernameField.getText(), passwordField.getText());
            System.out.println(l.getId());
            System.out.println(l.getTip());
            if(l.valid()) {
                globalId = l.getId();
                //id este l.getId() -- foloseste pentru a accesa in utilizator
                switch (l.getTip()) {
                    case "Student":
                        StudentProfil studentPage = new StudentProfil();
                        studentPage.start(primaryStage);
                        break;
                    case "Profesor":
                        ProfesorProfil professorPage = new ProfesorProfil();
                        professorPage.start(primaryStage);
                        break;
                    case "Administrator":
                        AdminProfil adminPage = new AdminProfil();
                        adminPage.start(primaryStage);
                        break;
                    case "Super-Administrator":
                        SupAdminProfil supAdminProfil = new SupAdminProfil();
                        supAdminProfil.start(primaryStage);
                        break;
                    default:
                        System.out.println("Nu exista acest tip");
                        break;
                }

            }
        });

        root.getChildren().addAll(profileIcon,userIcon,usernameField,lockIcon,passwordField,eyeIcon,textField,loginButton);

        Rectangle background = new Rectangle(0, 0, screenWidth, screenHeight);
        LinearGradient linearGradient = new LinearGradient(
                0, 0, 1, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#6A11CB")),
                new Stop(1, Color.web("#2575FC"))
        );
        background.setFill(linearGradient);

        root.getChildren().add(0, background);

        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
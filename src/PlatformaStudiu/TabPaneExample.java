package PlatformaStudiu;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Side;

public class TabPaneExample extends Application {
    private String path = "file:/C:/Users/mihai/Desktop/School/An 2 Sem 1/BD/Proiect/ProiectPng/Iesire.png";

    @Override
    public void start(Stage primaryStage) {
        // Create the TabPane
        TabPane tabPane = new TabPane();

        // Set the tabs to appear on the left side
        tabPane.setSide(Side.LEFT);

        // Set the preferred width of the TabPane
        tabPane.setPrefWidth(200);  // Ensure the TabPane is wide enough to fit the images

        // Create first tab with an image
        Tab tab1 = new Tab();
        tab1.setText("");  // Remove the text from the tab
        Image image1 = new Image(path);  // Load image
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(180);  // Set the image width
        imageView1.setFitHeight(60);  // Set the image height
        imageView1.setPreserveRatio(true);
        tab1.setGraphic(imageView1);  // Set the image as the tab graphic
        Label label1 = new Label("Content for Tab 1");
        tab1.setContent(label1);  // Set the content of the tab
        tab1.setClosable(false);  // Make this tab unclosable

        // Create second tab with an image
        Tab tab2 = new Tab();
        tab2.setText("");  // Remove the text from the tab
        Image image2 = new Image(path);  // Load image
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(180);  // Set the image width
        imageView2.setFitHeight(60);  // Set the image height
        imageView2.setPreserveRatio(true);
        tab2.setGraphic(imageView2);  // Set the image as the tab graphic
        Label label2 = new Label("Content for Tab 2");
        tab2.setContent(label2);  // Set the content of the tab
        tab2.setClosable(false);  // Make this tab unclosable

        // Create third tab with an image
        Tab tab3 = new Tab();
        tab3.setText("");  // Remove the text from the tab
        Image image3 = new Image(path);  // Load image
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(180);  // Set the image width
        imageView3.setFitHeight(60);  // Set the image height
        imageView3.setPreserveRatio(true);
        tab3.setGraphic(imageView3);  // Set the image as the tab graphic
        Label label3 = new Label("Content for Tab 3");
        tab3.setContent(label3);  // Set the content of the tab
        tab3.setClosable(false);  // Make this tab unclosable

        // Add tabs to the TabPane
        tabPane.getTabs().addAll(tab1, tab2, tab3);

        // Set the first tab as the default selected tab
        tabPane.getSelectionModel().select(0);

        // Set up the layout
        VBox layout = new VBox();
        layout.getChildren().add(tabPane);

        // Create the scene and set it on the stage
        Scene scene = new Scene(layout, 500, 300);  // Adjust the scene size if needed
        primaryStage.setTitle("TabPane with Image Tabs and Unclosable Tabs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
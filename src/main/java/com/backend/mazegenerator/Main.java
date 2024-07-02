package com.backend.mazegenerator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main class is the entry point for the JavaFX application.
 * It sets up the primary stage and user interface components.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the primary stage (window)
        primaryStage.setTitle("Maze Generator");

        // Create an instance of MazePane with 35 rows and 35 columns
        MazePane mazePane = new MazePane(35, 35);

        // Create a button to generate a new maze
        Button generateButton = new Button("Generate New Maze");
        // Set the action to be performed when the button is clicked
        generateButton.setOnAction(e -> mazePane.generateNewMaze());

        // Create a label with the title "Maze Generator"
        Label titleLabel = new Label("Maze Generator");

        // Create a toolbar and add the title label and generate button
        ToolBar toolBar = new ToolBar(titleLabel, generateButton);

        // Create a BorderPane layout to arrange UI components
        BorderPane root = new BorderPane();
        // Set the toolbar at the top of the BorderPane
        root.setTop(toolBar);
        // Set the MazePane at the center of the BorderPane
        root.setCenter(mazePane);

        // Create a new scene with the BorderPane as the root node
        Scene scene = new Scene(root);

        // Set the scene for the primary stage
        primaryStage.setScene(scene);
        // Enable full-screen mode for the primary stage
        primaryStage.setFullScreen(true);

        // Show the primary stage
        primaryStage.show();
    }

    /**
     * The main method is the entry point of the application.
     * It launches the JavaFX application.
     * args Command line arguments
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}

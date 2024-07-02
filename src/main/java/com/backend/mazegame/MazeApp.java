package com.backend.mazegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MazeApp extends Application {

    private MazeGenerator mazeGenerator;

    @Override
    public void start(Stage primaryStage) {
        mazeGenerator = new MazeGenerator(20, 20); // 20x20 maze

        BorderPane root = new BorderPane();
        Button generateButton = new Button("Generate New Maze");
        generateButton.setOnAction(e -> mazeGenerator.generateMaze());
        root.setBottom(generateButton);
        root.setCenter(mazeGenerator.getMazeCanvas());

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Maze Generator");
        primaryStage.show();
    }
}

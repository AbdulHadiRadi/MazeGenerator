package com.backend.mazegenerator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * MazePane is a custom JavaFX Pane that generates and displays a maze.
 */
public class MazePane extends Pane {
    // Constants
    private static final int CELL_SIZE = 20; // Size of each cell in the maze

    // Instance variables
    private final int rows; // Number of rows in the maze
    private final int cols; // Number of columns in the maze
    private int[][] maze; // 2D array representing the maze structure
    private MazeGenerator generator; // Maze generator instance
    private Canvas canvas; // Canvas for drawing the maze

    /**
     * Constructor for MazePane.
     * Initializes the maze generator and canvas, and sets up listeners for resizing.
     * rows Number of rows in the maze
     * cols Number of columns in the maze
     */
    public MazePane(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.generator = new MazeGenerator(rows, cols); // Initialize the maze generator
        this.canvas = new Canvas(); // Create a new canvas
        this.getChildren().add(canvas); // Add the canvas to the pane

        // Bind canvas size to the pane size
        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());

        // Regenerate and redraw the maze whenever the pane size changes
        this.widthProperty().addListener((obs, oldVal, newVal) -> drawMaze());
        this.heightProperty().addListener((obs, oldVal, newVal) -> drawMaze());

        // Generate the initial maze
        generateNewMaze();
    }

    /**
     * Generates a new maze and redraws it.
     */
    public void generateNewMaze() {
        maze = generator.generateMaze(); // Generate a new maze
        drawMaze(); // Draw the generated maze
    }

    /**
     * Draws the maze on the canvas.
     */
    private void drawMaze() {
        GraphicsContext gc = canvas.getGraphicsContext2D(); // Get the graphics context for drawing
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas

        double cellWidth = canvas.getWidth() / cols; // Calculate the width of each cell
        double cellHeight = canvas.getHeight() / rows; // Calculate the height of each cell

        // Iterate over each cell in the maze
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (maze[y][x] == 1) { // If the cell is part of the maze path
                    gc.setFill(Color.BLACK); // Set the fill color to black
                    gc.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight); // Draw the cell
                }
            }
        }
    }
}

package com.backend.mazegame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
    private final int rows;
    private final int cols;
    private final int cellSize = 30;
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final Cell[][] cells;

    public MazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.canvas = new Canvas(cols * cellSize, rows * cellSize);
        this.gc = canvas.getGraphicsContext2D();
        this.cells = new Cell[rows][cols];

        initializeCells();
        generateMaze();
    }

    private void initializeCells() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public void generateMaze() {
        // Implement a maze generation algorithm (e.g., Depth-First Search, Prim's)
        Random rand = new Random();
        List<Cell> stack = new ArrayList<>();
        Cell current = cells[0][0];
        current.visited = true;

        do {
            Cell next = getRandomUnvisitedNeighbor(current, rand);
            if (next != null) {
                next.visited = true;
                stack.add(current);
                removeWalls(current, next);
                current = next;
            } else if (!stack.isEmpty()) {
                current = stack.remove(stack.size() - 1);
            }
        } while (!stack.isEmpty());

        renderMaze();
    }

    private Cell getRandomUnvisitedNeighbor(Cell cell, Random rand) {
        List<Cell> neighbors = new ArrayList<>();

        int row = cell.row;
        int col = cell.col;

        if (row > 0 && !cells[row - 1][col].visited) neighbors.add(cells[row - 1][col]);
        if (row < rows - 1 && !cells[row + 1][col].visited) neighbors.add(cells[row + 1][col]);
        if (col > 0 && !cells[row][col - 1].visited) neighbors.add(cells[row][col - 1]);
        if (col < cols - 1 && !cells[row][col + 1].visited) neighbors.add(cells[row][col + 1]);

        if (neighbors.isEmpty()) return null;
        Collections.shuffle(neighbors, rand);
        return neighbors.get(0);
    }

    private void removeWalls(Cell current, Cell next) {
        int dx = current.col - next.col;
        int dy = current.row - next.row;

        if (dx == 1) { // next is to the left of current
            current.walls[3] = false;
            next.walls[1] = false;
        } else if (dx == -1) { // next is to the right of current
            current.walls[1] = false;
            next.walls[3] = false;
        }

        if (dy == 1) { // next is above current
            current.walls[0] = false;
            next.walls[2] = false;
        } else if (dy == -1) { // next is below current
            current.walls[2] = false;
            next.walls[0] = false;
        }
    }

    private void renderMaze() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = cells[row][col];
                int x = col * cellSize;
                int y = row * cellSize;

                if (cell.walls[0]) gc.strokeLine(x, y, x + cellSize, y); // top wall
                if (cell.walls[1]) gc.strokeLine(x + cellSize, y, x + cellSize, y + cellSize); // right wall
                if (cell.walls[2]) gc.strokeLine(x + cellSize, y + cellSize, x, y + cellSize); // bottom wall
                if (cell.walls[3]) gc.strokeLine(x, y + cellSize, x, y); // left wall
            }
        }
    }

    public Canvas getMazeCanvas() {
        return canvas;
    }

    private static class Cell {
        int row, col;
        boolean[] walls = {true, true, true, true}; // top, right, bottom, left
        boolean visited = false;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}

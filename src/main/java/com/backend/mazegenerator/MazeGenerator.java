package com.backend.mazegenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * MazeGenerator class is responsible for generating a random maze using
 * Depth-First Search (DFS) algorithm with backtracking.
 */
public class MazeGenerator {
    // Maze dimensions
    private int rows;
    private int cols;

    // Maze representation: 0 for path, 1 for wall
    private int[][] maze;

    // Visited cells tracker
    private boolean[][] visited;

    // Constants for path and wall
    private static final int PATH = 0;
    private static final int WALL = 1;

    /**
     * Constructor for MazeGenerator.
     * rows Number of rows in the maze
     * cols Number of columns in the maze
     */
    public MazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Generates the maze using DFS algorithm.
     * 2D array representing the generated maze
     */
    public int[][] generateMaze() {
        // Initialize maze and visited arrays
        maze = new int[rows][cols];
        visited = new boolean[rows][cols];

        // Set all cells as walls initially
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                maze[y][x] = WALL;
            }
        }

        // Stack for DFS
        Stack<Cell> stack = new Stack<>();

        // Start position (1, 1) and mark as path
        stack.push(new Cell(1, 1));
        maze[1][1] = PATH;
        visited[1][1] = true;

        // DFS to carve out the maze paths
        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            List<Cell> neighbors = getUnvisitedNeighbors(current);

            if (neighbors.isEmpty()) {
                // Backtrack if no unvisited neighbors
                stack.pop();
            } else {
                // Randomly pick a neighbor, remove wall, and mark as visited
                Collections.shuffle(neighbors);
                Cell next = neighbors.get(0);
                removeWall(current, next);
                visited[next.y][next.x] = true;
                maze[next.y][next.x] = PATH;
                stack.push(next);
            }
        }

        return maze;
    }

    /**
     * Gets the list of unvisited neighbors for a given cell.
     * cell The current cell
     * List of unvisited neighbor cells
     */
    private List<Cell> getUnvisitedNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();

        // Check each direction for unvisited cells
        if (cell.x > 1 && !visited[cell.y][cell.x - 2]) {
            neighbors.add(new Cell(cell.x - 2, cell.y));
        }
        if (cell.x < cols - 2 && !visited[cell.y][cell.x + 2]) {
            neighbors.add(new Cell(cell.x + 2, cell.y));
        }
        if (cell.y > 1 && !visited[cell.y - 2][cell.x]) {
            neighbors.add(new Cell(cell.x, cell.y - 2));
        }
        if (cell.y < rows - 2 && !visited[cell.y + 2][cell.x]) {
            neighbors.add(new Cell(cell.x, cell.y + 2));
        }

        return neighbors;
    }

    /**
     * Removes the wall between two cells.
     * current The current cell
     * next The next cell
     */
    private void removeWall(Cell current, Cell next) {
        // Calculate the position of the wall to remove
        int x = (current.x + next.x) / 2;
        int y = (current.y + next.y) / 2;

        // Set the wall position to path
        maze[y][x] = PATH;
    }

    /**
     * Inner class representing a cell in the maze.
     */
    private static class Cell {
        int x, y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

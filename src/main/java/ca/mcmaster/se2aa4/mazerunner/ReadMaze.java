package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadMaze {

    private char[][] maze;
    private int width;
    private int height;

    public void loadMaze(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowCount = 0;

            // determine height and width of the maze by counting the rows and the length of each line in the text file
            while ((line = reader.readLine()) != null) {
                if (rowCount == 0) {
                    width = line.length();
                }
                rowCount++;
            }

            height = rowCount;
            maze = new char[height][width];

            
            try (BufferedReader secondReader = new BufferedReader(new FileReader(filePath))) {
                int row = 0;
                while ((line = secondReader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        
                        line = " ".repeat(width);
                    }
                    maze[row] = line.toCharArray();
                    
                    
                    row++;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading maze file: " + filePath, e);
        }
    }

    public char getTile(int x, int y) {
        return maze[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isWall(int x, int y) {
        return getTile(x, y) == '#';
    }

    public boolean isPassage(int x, int y) {
        return getTile(x, y) != '#';
    }

    public void printMaze() {
        System.out.println(height);
        System.out.println(width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isWall(x, y)) {
                    System.out.print("WALL ");
                } else {
                    System.out.print("PASS ");
                }
            }
            System.out.println();
        }
    }
}

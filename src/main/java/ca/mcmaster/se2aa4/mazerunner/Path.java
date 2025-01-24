
package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private ReadMaze maze;
    private int[] position;

    public Path(ReadMaze maze, int[] position) {
        this.maze = maze;
        this.position = position;
    }

    public boolean moveForward(String direction) {
        int x = position[0];
        int y = position[1];
        //logic for moving left right up down etc
        return true;
    }

    

    
}

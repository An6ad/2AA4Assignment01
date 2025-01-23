
package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private ReadMaze maze;
    private Position position;

    public Path(ReadMaze maze, Position position) {
        this.maze = maze;
        this.position = position;
    }

    public boolean moveForward(String direction) {
        int x = position.getX();
        int y = position.getY();
        //logic for moving left right up down etc
        return true;
    }

    
}

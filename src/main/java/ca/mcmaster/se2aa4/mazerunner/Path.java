
package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;
import java.util.List;

public class Path {
    private ReadMaze maze;
    private Position position;
    private List<String> canonicalPath;

    public Path(ReadMaze maze) {
        this.maze = maze;
        // Assign to the class-level 'position' field, not a local variable
        this.position = maze.findEntry();
        
        this.canonicalPath = new ArrayList<>();
    }

    public boolean move(String direction) {
        int x = position.getX();
        int y = position.getY();

        if (direction.equalsIgnoreCase("up")) {
            y -= 1; 
        } else if (direction.equalsIgnoreCase("down")) {
            y += 1; 
        } else if (direction.equalsIgnoreCase("left")) {
            x -= 1; 
        } else if (direction.equalsIgnoreCase("right")) {
            x += 1; 
            canonicalPath.add("F"); 
        } else {
            return false; // Invalid direction
        }

        if (x < 0 || x >= maze.getWidth() || y < 0 || y >= maze.getHeight()) {
            return false; // Out of bounds
        }
        
        
        if (!maze.isPassage(x, y)) {
            return false; // Next tile is not passable
        }

        
        position.setX(x);
        position.setY(y);

       
        

        return true; // Move successful
    }

    public String factorizedPath() {
        return canonicalPath.size() + "F";
    }

    public List<String> canonicalPathList() {
        return canonicalPath;
    }

    public int getX() {
        return position.getX();
    }
    
    public int getY() {
        return position.getY();
    }

   

}

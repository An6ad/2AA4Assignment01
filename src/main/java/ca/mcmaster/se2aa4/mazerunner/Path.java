
package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private ReadMaze maze;
    private Position position;

    public Path(ReadMaze maze) {
        this.maze = maze;
        // Assign to the class-level 'position' field, not a local variable
        this.position = maze.findEntry();
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
        } else {
            return false; // Invalid direction
        }

        if (x < 0 || x >= maze.getWidth() || y < 0 || y >= maze.getHeight()) {
            return false; // Out of bounds
        }
        
        
        if (!maze.isPassage(x, y)) {
            return false; // Next tile is not passable
        }

        // If valid, update position
        position.setX(x);
        position.setY(y);

       
        // Update the position with the new coordinates
        position.setX(x);
        position.setY(y);

        return true; // Move successful
    }

    

    public int getX() {
        return position.getX();
    }
    
    public int getY() {
        return position.getY();
    }

   

}

package ca.mcmaster.se2aa4.mazerunner;

public class RightHandAlgorithm implements PathfindingImplementation {

    @Override
    public String computePath(ReadMaze maze, Position start) {
        // find the exit position in the maze
        Position exitPosition = maze.findExit();
        
        // start at the given position
        int currentX = start.getX();
        int currentY = start.getY();
        
        // start facing right follows the right-hand rule as we start moving along the right wall
        int headingX = 1;  
        int headingY = 0;  

        // this will store all the moves we make
        String canonicalPath = "";

        // keep moving until we reach the exit
        while (!(currentX == exitPosition.getX() && currentY == exitPosition.getY())) {
            
            
            // figure out where right is helps us stick to the right wall
            int rightX = -headingY;
            int rightY = headingX;

            // figure out where left is
            int leftX = headingY;
            int leftY = -headingX;

            // if the right side is open turn right and move forward follow the right wall
            if (canMove(maze, currentX + rightX, currentY + rightY)) {
                canonicalPath = canonicalPath + "R ";  // Turn right
                headingX = rightX;
                headingY = rightY;
                
                // move forward after turning right
                if (canMove(maze, currentX + headingX, currentY + headingY)) {
                    currentX = currentX + headingX;
                    currentY = currentY + headingY;
                    canonicalPath = canonicalPath + "F ";
                } 
            }
            // if front is open move forward
            else if (canMove(maze, currentX + headingX, currentY + headingY)) {
                currentX = currentX + headingX;
                currentY = currentY + headingY;
                canonicalPath = canonicalPath + "F ";
            }
            // if the left is open turn left and move forward
            else if (canMove(maze, currentX + leftX, currentY + leftY)) {
                canonicalPath = canonicalPath + "L ";  // Turn left
                headingX = leftX;
                headingY = leftY;
                
                // move forward after turning left
                if (canMove(maze, currentX + headingX, currentY + headingY)) {
                    currentX = currentX + headingX;
                    currentY = currentY + headingY;
                    canonicalPath = canonicalPath + "F ";
                }
            }
            // if there is no way to go right, forward, or left, do a U-turn
            else {
                canonicalPath = canonicalPath + "R R ";  // turn right twice (180-degree turn)
                
                // first right turn
                int tempHeadingX = -headingY;
                int tempHeadingY = headingX;
                
                // second right turn (completing the U-turn)
                headingX = -tempHeadingY;
                headingY = tempHeadingX;
                
                // move forward after turning around.
                if (canMove(maze, currentX + headingX, currentY + headingY)) {
                    currentX = currentX + headingX;
                    currentY = currentY + headingY;
                    canonicalPath = canonicalPath + "F ";
                } 
            }
        }

        // factorized path
        String factorizedPath = factorizeForwardMoves(canonicalPath);

        return "Canonical: " + canonicalPath + "\nFactorized: " + factorizedPath;
    }

    
    private boolean canMove(ReadMaze maze, int newX, int newY) {
        // Make sure the new position is inside the maze and is not a wall.
        return newX >= 0 && newX < maze.getWidth() &&
               newY >= 0 && newY < maze.getHeight() &&
               maze.isPassage(newX, newY);
    }

    
    private String factorizeForwardMoves(String canonicalPath) {
        String[] tokens = canonicalPath.split("\\s+");  
        String factorizedPath = "";
        int i = 0;
        
        while (i < tokens.length) {
            if (tokens[i].equals("F")) {
                int count = 0;
            
                while (i < tokens.length && tokens[i].equals("F")) {
                    count++;
                    i++;
                }
                
               
                if (count == 1) {
                    factorizedPath = factorizedPath + "F ";
                } else {
                    factorizedPath = factorizedPath + count + "F ";  
                }
            } else {
                factorizedPath = factorizedPath + tokens[i] + " ";  
                i++;
            }
        }
        return factorizedPath.trim();
    }
}

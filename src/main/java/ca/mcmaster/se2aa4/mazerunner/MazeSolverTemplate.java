package ca.mcmaster.se2aa4.mazerunner;

public abstract class MazeSolverTemplate implements PathfindingImplementation {
    protected String canonicalPath = "";
    protected int currentX;
    protected int currentY;
    protected int headingX;
    protected int headingY;
    
    @Override
    public final String[] computePath(ReadMaze maze, Position start) {
        // find the exit position in the maze
        Position exitPosition = maze.findExit();
        
        // start at the given position
        currentX = start.getX();
        currentY = start.getY();
        
        // initialize heading (concrete implementation in subclass)
        initHeading();
        
        // keep moving until we reach the exit
        while (!(currentX == exitPosition.getX() && currentY == exitPosition.getY())) {
            processNextStep(maze);
        }
        
        // factorized path
        String factorizedPath = factorizeForwardMoves(canonicalPath);
        
        String[] paths = {canonicalPath.trim(), factorizedPath};
        return paths;
    }
    
    // Abstract hook for initializing the heading/direction.
    protected abstract void initHeading();
    
    // Abstract hook for processing each step in the maze.
    protected abstract void processNextStep(ReadMaze maze);
    
    protected boolean canMove(ReadMaze maze, int newX, int newY) {
        // Make sure the new position is inside the maze and is not a wall.
        return newX >= 0 && newX < maze.getWidth() &&
               newY >= 0 && newY < maze.getHeight() &&
               maze.isPassage(newX, newY);
    }
    
    protected String factorizeForwardMoves(String canonicalPath) {
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

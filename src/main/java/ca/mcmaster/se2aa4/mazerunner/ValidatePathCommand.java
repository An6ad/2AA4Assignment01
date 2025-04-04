package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidatePathCommand implements MazeCommand {
    private static final Logger logger = LogManager.getLogger(ValidatePathCommand.class);
    private String mazeFilePath;
    private String inputPath;
    
    public ValidatePathCommand(String mazeFilePath, String inputPath) {
        this.mazeFilePath = mazeFilePath;
        this.inputPath = inputPath;
    }
    
    @Override
    public void execute() {
        ReadMaze maze = new ReadMaze();
        maze.loadMaze(mazeFilePath);
        Position start = maze.findEntry();
        RightHandAlgorithm solver = new RightHandAlgorithm();
        String[] paths = solver.computePath(maze, start);
        // Remove spaces for proper comparison.
        String canonicalPath = paths[0].replaceAll("\\s", "");
        String factorizedPath = paths[1].replaceAll("\\s", "");
        String provided = inputPath.replaceAll("\\s", "");
        
        if (canonicalPath.equals(provided) || factorizedPath.equals(provided)) {
            logger.info("Valid Path");
        } else {
            logger.info("Invalid Path");
        }
    }
}

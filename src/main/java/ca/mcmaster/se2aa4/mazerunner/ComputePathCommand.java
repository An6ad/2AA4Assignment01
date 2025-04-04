package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//Created ComputePathCommand
public class ComputePathCommand implements MazeCommand {
    private static final Logger logger = LogManager.getLogger(ComputePathCommand.class);
    private String mazeFilePath;
    
    public ComputePathCommand(String mazeFilePath) {
        this.mazeFilePath = mazeFilePath;
    }

    @Override
    public void execute() {
        ReadMaze maze = new ReadMaze();
        maze.loadMaze(mazeFilePath);
        Position start = maze.findEntry();
        // Use our Template–based solver
        RightHandAlgorithm solver = new RightHandAlgorithm();
        String[] paths = solver.computePath(maze, start);
        logger.info("Computed Path:\nCanonical: " + paths[0] + "\nFactorized: " + paths[1]);

    }

}
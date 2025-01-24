package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", "input", true, "maze input");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            // Create a ReadMaze object to handle reading and parsing the maze
            ReadMaze readMaze = new ReadMaze();
            

            String inputFilePath = cmd.getOptionValue("i");
            
            readMaze.loadMaze(inputFilePath);

            
            
            //initialize path object that handles the position in the maze
            Path path = new Path(readMaze);
            logger.info("Reading maze from " + inputFilePath);
            //simply moving from the start position, to the right through the simplest maze (straight open path)
            while (path.move("right")) {
                System.out.println("Moved to: (" + path.getX() + ", " + path.getY() + ")");
            }


            
            logger.info("Compute logic");
        } catch (ParseException e) {
            System.exit(1);
        }
        logger.info("End");
    }
}

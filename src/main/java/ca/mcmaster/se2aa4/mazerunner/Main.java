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

            String inputFilePath = cmd.getOptionValue("i");
            logger.info("Reading maze from " + inputFilePath);

            // Create a ReadMaze object to handle reading and parsing the maze
            ReadMaze readMaze = new ReadMaze();
            readMaze.loadMaze(inputFilePath);
            
            //initialize position object that handles the position in the maze
            Position position = new Position(readMaze.findEntry()[0], readMaze.findEntry()[1]); 

            System.out.print(position.getCurrentPosition()[0]);
            System.out.print(",");
            System.out.print(position.getCurrentPosition()[1]);
            System.out.println();


            // Displaying the maze
            readMaze.printMaze();
            logger.info("Compute logic");
        } catch (ParseException e) {
            System.exit(1);
        }
        logger.info("End");
    }
}

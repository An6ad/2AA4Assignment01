package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
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
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            

            String inputFilePath = cmd.getOptionValue("i");
            logger.info("reading maze " + inputFilePath);

           
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            
                            System.out.print("WALL ");
                        } else {
                            System.out.print("PASS ");
                        }
                    }
                    System.out.println("");
                }
            } catch (Exception e) {
                logger.error("An error occurred while reading the maze file: ", e);
                System.exit(1);
            }

            logger.info("Computing path (feature not implemented yet)");
            logger.info("PATH NOT COMPUTED");

        } catch (ParseException e) {
            logger.error("Error parsing command-line arguments: ", e);
            formatter.printHelp("MazeRunner", options);
            System.exit(1);
        }

        logger.info("End of Maze Runner");
    }
}



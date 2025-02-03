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
       

        Options options = new Options();
        options.addOption("i", "input", true, "maze input");
        options.addOption("p", "path", true, "path validation");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        ReadMaze readMaze = new ReadMaze();

        if (cmd.hasOption("p")) {
            String pathInput = cmd.getOptionValue("p").replaceAll("\\s", ""); // Ignore spaces

        if (cmd.hasOption("i")) {
            String inputFilePath = cmd.getOptionValue("i");
            readMaze.loadMaze(inputFilePath);
        }

        
        RightHandAlgorithm implementation = new RightHandAlgorithm();
        Path path = new Path(readMaze, implementation);

        
        String canonicalPath = path.computePath()[0].replaceAll("\\s", "");
        String factorizedPath = path.computePath()[1].replaceAll("\\s", "");

        if (canonicalPath.equals(pathInput) || factorizedPath.equals(pathInput)) {
            logger.info("Valid Path");
        } else {
            logger.info("Invalid Path");
        }
    } else if (cmd.hasOption("i")) {
        String inputFilePath = cmd.getOptionValue("i");
        readMaze.loadMaze(inputFilePath);


        RightHandAlgorithm implementation = new RightHandAlgorithm();
        Path path = new Path(readMaze, implementation);

        logger.info("Computed Path: \n" + "Canonical: " + path.computePath()[0] + "\nFactorized: " + path.computePath()[1]);
    }

            
    
        } catch (ParseException e) {
            System.exit(1);
        }
        
    }
}

package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", "input", true, "maze input file");
        options.addOption("p", "path", true, "path validation input");
        
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            MazeCommand command;
            if (cmd.hasOption("p")) {
                String mazeFilePath = cmd.getOptionValue("i");
                String providedPath = cmd.getOptionValue("p");
                command = new ValidatePathCommand(mazeFilePath, providedPath);
            } else if (cmd.hasOption("i")) {
                String mazeFilePath = cmd.getOptionValue("i");
                command = new ComputePathCommand(mazeFilePath);
            } else {
                System.out.println("Usage: -i <maze file> [-p <path>]");
                return;
            }
            command.execute();
        } catch (ParseException e) {
            System.exit(1);
        }
    }
}

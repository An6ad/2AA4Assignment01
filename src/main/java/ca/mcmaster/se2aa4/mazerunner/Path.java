package ca.mcmaster.se2aa4.mazerunner;

//heavily refactored Path Class to adhere to the single responsibility principle and to allow for algorithmic polymorhpism
public class Path {
    private PathfindingImplementation implementation;
    private ReadMaze maze;
    private Position position;

    public Path(ReadMaze maze, PathfindingImplementation implementation) {
        this.maze = maze;
        this.implementation = implementation;
        this.position = maze.findEntry();
    }

    public String computePath() {
        return implementation.computePath(maze, position);
    }
}

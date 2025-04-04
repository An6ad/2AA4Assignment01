package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class MazeRunnerTests {

    @Test
    public void testPositionGetCurrentPosition() {
        Position pos = new Position(1, 2);
        int[] current = pos.getCurrentPosition();
        assertEquals(1, current[0]);
        assertEquals(2, current[1]);
    }

    @Test
    public void testPositionSettersGetters() {
        Position pos = new Position(0, 0);
        pos.setX(3);
        pos.setY(4);
        assertEquals(3, pos.getX());
        assertEquals(4, pos.getY());
    }

    @Test
    public void testLoadMaze(@TempDir Path tempDir) throws IOException {
        String mazeContent = "###\n# #\n###";
        Path mazeFile = tempDir.resolve("maze.txt");
        Files.write(mazeFile, mazeContent.getBytes());

        ReadMaze maze = new ReadMaze();
        maze.loadMaze(mazeFile.toString());
        assertEquals(3, maze.getWidth());
        assertEquals(3, maze.getHeight());
    }

    @Test
    public void testFindEntry(@TempDir Path tempDir) throws IOException {
        String mazeContent = "###\n # \n###";
        Path mazeFile = tempDir.resolve("mazeEntry.txt");
        Files.write(mazeFile, mazeContent.getBytes());

        ReadMaze maze = new ReadMaze();
        maze.loadMaze(mazeFile.toString());
        Position entry = maze.findEntry();
        assertNotNull(entry);
        assertEquals(0, entry.getX());
        assertEquals(1, entry.getY());
    }

    @Test
    public void testFindExit(@TempDir Path tempDir) throws IOException {
        String mazeContent = "###\n## \n###";
        Path mazeFile = tempDir.resolve("mazeExit.txt");
        Files.write(mazeFile, mazeContent.getBytes());

        ReadMaze maze = new ReadMaze();
        maze.loadMaze(mazeFile.toString());
        Position exit = maze.findExit();
        assertNotNull(exit);
        assertEquals(maze.getWidth() - 1, exit.getX());
        assertEquals(1, exit.getY());
    }

    @Test
    public void testIsWallAndPassage(@TempDir Path tempDir) throws IOException {
        String mazeContent = "# #\n   \n###";
        Path mazeFile = tempDir.resolve("mazeWallPass.txt");
        Files.write(mazeFile, mazeContent.getBytes());

        ReadMaze maze = new ReadMaze();
        maze.loadMaze(mazeFile.toString());
        assertTrue(maze.isWall(0, 0));
        assertTrue(maze.isPassage(1, 0));
    }

    @Test
    public void testRightHandAlgorithmSimpleMaze(@TempDir Path tempDir) throws IOException {
        String mazeContent = "   ";
        Path mazeFile = tempDir.resolve("simpleMaze.txt");
        Files.write(mazeFile, mazeContent.getBytes());

        ReadMaze maze = new ReadMaze();
        maze.loadMaze(mazeFile.toString());
        
        // Use fully qualified name to avoid conflict with java.nio.file.Path which prevented Path class from being recognized
        ca.mcmaster.se2aa4.mazerunner.Path path = new ca.mcmaster.se2aa4.mazerunner.Path(maze, new RightHandAlgorithm());
        String[] computedPaths = path.computePath();
        String canonicalPath = computedPaths[0].replaceAll("\\s", "");
        String factorizedPath = computedPaths[1].replaceAll("\\s", "");
        assertEquals("FF", canonicalPath);
        assertEquals("2F", factorizedPath);
    }

    @Test
    public void testFactorizePathManual() throws Exception {
        RightHandAlgorithm algo = new RightHandAlgorithm();
        Method method = RightHandAlgorithm.class.getDeclaredMethod("factorizeForwardMoves", String.class);
        method.setAccessible(true);
        String result = (String) method.invoke(algo, "F F L F F F R F");
        assertEquals("2F L 3F R F", result);
    }

    @Test
    public void testMainValidPath(@TempDir Path tempDir) throws IOException {
        String mazeContent = "   ";
        Path mazeFile = tempDir.resolve("mainMazeValid.txt");
        Files.write(mazeFile, mazeContent.getBytes());
        String[] args = {"-i", mazeFile.toString(), "-p", "FF"};
        Main.main(args);
    }

    @Test
    public void testMainWithoutPathOption(@TempDir Path tempDir) throws IOException {
        String mazeContent = "   ";
        Path mazeFile = tempDir.resolve("mainMazeNoPath.txt");
        Files.write(mazeFile, mazeContent.getBytes());
        String[] args = {"-i", mazeFile.toString()};
        Main.main(args);
    }
}

package ca.mcmaster.se2aa4.mazerunner;

public class RightHandAlgorithm extends MazeSolverTemplate {

    @Override
    protected void initHeading() {
        // start facing right follows the right-hand rule as we start moving along the right wall
        headingX = 1;  
        headingY = 0;
    }

    @Override
    protected void processNextStep(ReadMaze maze) {
        // figure out where right is helps us stick to the right wall
        int rightX = -headingY;
        int rightY = headingX;

        // figure out where left is
        int leftX = headingY;
        int leftY = -headingX;

        // if the right side is open turn right and move forward follow the right wall
        if (canMove(maze, currentX + rightX, currentY + rightY)) {
            canonicalPath = canonicalPath + "R ";  // Turn right
            headingX = rightX;
            headingY = rightY;
            
            // move forward after turning right
            if (canMove(maze, currentX + headingX, currentY + headingY)) {
                currentX = currentX + headingX;
                currentY = currentY + headingY;
                canonicalPath = canonicalPath + "F ";
            } 
        }
        // if front is open move forward
        else if (canMove(maze, currentX + headingX, currentY + headingY)) {
            currentX = currentX + headingX;
            currentY = currentY + headingY;
            canonicalPath = canonicalPath + "F ";
        }
        // if the left is open turn left and move forward
        else if (canMove(maze, currentX + leftX, currentY + leftY)) {
            canonicalPath = canonicalPath + "L ";  // Turn left
            headingX = leftX;
            headingY = leftY;
            
            // move forward after turning left
            if (canMove(maze, currentX + headingX, currentY + headingY)) {
                currentX = currentX + headingX;
                currentY = currentY + headingY;
                canonicalPath = canonicalPath + "F ";
            }
        }
        // if there is no way to go right, forward, or left, do a U-turn
        else {
            canonicalPath = canonicalPath + "R R ";  // turn right twice (180-degree turn)
            
            // first right turn
            int tempHeadingX = -headingY;
            int tempHeadingY = headingX;
            
            // second right turn (completing the U-turn)
            headingX = -tempHeadingY;
            headingY = tempHeadingX;
            
            // move forward after turning around.
            if (canMove(maze, currentX + headingX, currentY + headingY)) {
                currentX = currentX + headingX;
                currentY = currentY + headingY;
                canonicalPath = canonicalPath + "F ";
            } 
        }
    }
}

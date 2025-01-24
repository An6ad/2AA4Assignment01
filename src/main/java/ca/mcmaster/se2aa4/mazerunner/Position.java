package ca.mcmaster.se2aa4.mazerunner;

public class Position {
    private int x;
    private int y;

    public Position(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveUp() {
        
    }

    public void moveDown() {
        
    }

    public void moveLeft() {
        
    }

    public void moveRight() {
        
    }

    public int[] getCurrentPosition() {
        return new int[] { x, y }; // Return position as {x, y}
    }
    
}

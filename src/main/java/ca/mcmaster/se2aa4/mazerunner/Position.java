package ca.mcmaster.se2aa4.mazerunner;

public class Position {
    private int x;
    private int y;

    public Position(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    

    public int[] getCurrentPosition() {
        return new int[] { x, y }; // Return position as {x, y}
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

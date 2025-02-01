package ca.mcmaster.se2aa4.mazerunner;

//creation of direction class to allow for future implementation of the Liskov Substitution Principle
public abstract class Direction {
    public abstract Position move(Position position);
    public abstract Direction turnRight();
    public abstract Direction turnLeft();
}

package agh.ics.oop.model;

import java.util.Map;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    public Animal(){};
    public Animal(Vector2d position){
        this.position = position;
    }

    @Override
    public String toString() {
        return position.toString() + ", " + orientation.toString();
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction){

        Vector2d newPosition = this.position;

        switch (direction){
            case FORWARD -> newPosition = this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> newPosition = this.position.subtract(this.orientation.toUnitVector());
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
        }

        if(newPosition.follows(new Vector2d(0, 0)) && newPosition.precedes(new Vector2d(4, 4))){
            this.position = newPosition;
        }

    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }
}

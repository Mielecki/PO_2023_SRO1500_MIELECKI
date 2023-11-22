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
        return switch (getOrientation()){
            case NORTH -> "N";
            case SOUTH -> "S";
            case WEST -> "W";
            case EAST -> "E";
        };
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction, MoveValidator moveValidator){

        Vector2d newPosition;

        switch (direction){
            case FORWARD ->{
                newPosition = this.position.add(this.orientation.toUnitVector());
                if (moveValidator.canMoveTo(newPosition)){
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                newPosition = this.position.subtract(this.orientation.toUnitVector());
                if (moveValidator.canMoveTo(newPosition)){
                    this.position = newPosition;
                }
            }
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
        }
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }
}

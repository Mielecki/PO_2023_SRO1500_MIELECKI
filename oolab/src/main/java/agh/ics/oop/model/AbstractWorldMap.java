package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap{
    protected final Map<Vector2d, Animal> animals = new HashMap<>();

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (this.animals.containsKey(position)) {
            return this.animals.get(position);
        }
        return null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if(canMoveTo(position)){
            animals.put(position, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition());
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);
    }
}

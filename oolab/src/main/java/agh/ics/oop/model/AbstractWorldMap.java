package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap{
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected Vector2d upperRight;
    protected Vector2d lowerLeft;
    protected List<MapChangeListener> observers = new ArrayList<>();

    public void addObserver(MapChangeListener observer){
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer){
        observers.remove(observer);
    }

    public void notify(String message){
        for (MapChangeListener observer : observers){
            observer.mapChanged(this, message);
        }
    }


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
    public boolean place(Animal animal) throws PositionAlreadyOccupiedException{
        Vector2d position = animal.getPosition();
        if(canMoveTo(position)){
            animals.put(position, animal);
            notify("Umieszczono zwierzaka");
            return true;
        }
        throw new PositionAlreadyOccupiedException(position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition());
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);
        notify("Zwierzak poruszyl sie");
    }

    @Override
    public Map<Vector2d, WorldElement> getElements(){
        Map<Vector2d, WorldElement> elements = new HashMap<>();

        for (Vector2d animal : animals.keySet()){
            elements.put(animal, animals.get(animal));
        }
        return elements;
    }

    public Boundary getCurrentBounds(){
        return new Boundary(lowerLeft, upperRight);
    }

    @Override
    public String toString() {
        Boundary currentBounds = getCurrentBounds();
        return new MapVisualizer(this).draw(currentBounds.lowerLeft(), currentBounds.upperRight());
    }
}

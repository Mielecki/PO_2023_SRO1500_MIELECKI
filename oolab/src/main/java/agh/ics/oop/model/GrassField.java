package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount){
        this.placeGrass(grassCount);
        this.id = UUID.randomUUID();
    }

    public void placeGrass(int grassCount){
        int size = (int) Math.sqrt(grassCount*10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(size, size, grassCount);

        for(Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement animal = super.objectAt(position);
        if (animal != null) {
            return animal;
        }
        return grasses.get(position);
    }

    private Vector2d setLowerLeft(){
        Vector2d lowerLeft = new Vector2d(0, 0);
        for(Vector2d position : this.animals.keySet()){
            lowerLeft = lowerLeft.lowerLeft(position);
        }
        for(Vector2d position : this.grasses.keySet()){
            lowerLeft = lowerLeft.lowerLeft(position);
        }
        return lowerLeft;
    }

    private Vector2d setUpperRight(){
        Vector2d upperRight = new Vector2d(0, 0);
        for(Vector2d position : this.animals.keySet()){
            upperRight = upperRight.upperRight(position);
        }
        for(Vector2d position : this.grasses.keySet()){
            upperRight = upperRight.upperRight(position);
        }
        return upperRight;
    }

    @Override
    public Map<Vector2d, WorldElement> getElements(){
        Map<Vector2d, WorldElement> elements = super.getElements();
        for (Vector2d grass : grasses.keySet()){
            elements.put(grass, grasses.get(grass));
        }
        return elements;
    }

    @Override
    public Boundary getCurrentBounds(){
        lowerLeft = setLowerLeft();
        upperRight = setUpperRight();
        return super.getCurrentBounds();
    }
}

package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount){
        this.placeGrass(grassCount);
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

    @Override
    public String toString(){
        Vector2d vectorBottom = null;
        Vector2d vectorTop = null;

        for(Vector2d position : this.animals.keySet()){
            if (vectorBottom == null){
                vectorBottom = position;
                vectorTop = position;
            }
            else {
                vectorBottom = vectorBottom.lowerLeft(position);
                vectorTop = vectorTop.upperRight(position);
            }
        }
        for(Vector2d position : this.grasses.keySet()){
            if (vectorBottom == null){
                vectorBottom = position;
                vectorTop = position;
            }
            else {
                vectorBottom = vectorBottom.lowerLeft(position);
                vectorTop = vectorTop.upperRight(position);
            }
        }

        return new MapVisualizer(this).draw(vectorBottom, vectorTop);
    }

    @Override
    public Map<Vector2d, WorldElement> getElements(){
        Map<Vector2d, WorldElement> elements = super.getElements();
        for (Vector2d grass : grasses.keySet()){
            elements.put(grass, grasses.get(grass));
        }
        return elements;
    }
}

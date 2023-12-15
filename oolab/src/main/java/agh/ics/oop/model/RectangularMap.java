package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.id = UUID.randomUUID();
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(this.width, this.height));
    }

    @Override
    public Boundary getCurrentBounds() {
        lowerLeft = new Vector2d(0 ,0);
        upperRight = new Vector2d(width, height);
        return super.getCurrentBounds();
    }
}

package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class RandomPositionGenerator implements Iterable<Vector2d>{
    int maxWidth;
    int maxHeight;
    int grassCount;

    ArrayList<Vector2d> positions = new ArrayList<Vector2d>();
    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount){
        generateRandomPositions(maxWidth, maxHeight, grassCount);
    }

    private void generateRandomPositions(int maxWidth, int maxHeight, int grassCount){
        ArrayList<Vector2d> positionsList = new ArrayList<Vector2d>();

        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < maxWidth; j++) {
                positionsList.add(new Vector2d(i, j));
            }
        }

        Collections.shuffle(positionsList);

        for (int i = 0; i < grassCount; i++) {
            this.positions.add(positionsList.get(i));
        }
    }
    @Override
    public Iterator<Vector2d> iterator() {
        return new Iterator<Vector2d>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < positions.size();
            }

            @Override
            public Vector2d next() {
                return positions.get(currentIndex++);
            }
        };
    }
}

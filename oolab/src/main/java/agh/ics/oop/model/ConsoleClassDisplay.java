package agh.ics.oop.model;

public class ConsoleClassDisplay implements MapChangeListener{
    private int counter = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        counter++;
        System.out.println(message);
        System.out.println(worldMap.toString());
        System.out.println(counter);
    }
}

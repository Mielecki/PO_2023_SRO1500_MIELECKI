package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{
    private int counter = 0;

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        counter++;
        System.out.println("ID: " + worldMap.getID());
        System.out.println(message);
        System.out.println(worldMap.toString());
        System.out.println("Zmiana stanu nr: " + counter);
    }
}

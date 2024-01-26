package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    private final WorldMap worldMap;
    public Simulation(List<Vector2d> positions, List<MoveDirection> moves, WorldMap worldMap){
        this.moves = moves;
        this.worldMap = worldMap;
        this.animals = positionsToAnimals(positions);
    }

    private List<Animal> positionsToAnimals(List<Vector2d> positions){
        List<Animal> animalsTemp = new ArrayList<>();
        for (Vector2d position : positions){
            try{
                Animal animal = new Animal(position);
                worldMap.place(animal);
                animalsTemp.add(animal);
            } catch (PositionAlreadyOccupiedException ex){
                System.out.println(ex.toString());
            }
        }
        return animalsTemp;
    }

    public void run(){
        int n = animals.size();
        int animalIndex = 0;

        for(MoveDirection move: moves){
            try {
                Thread.sleep(500);
                Animal currAnimal = animals.get(animalIndex);
                worldMap.move(currAnimal, move);

                animalIndex = (animalIndex + 1) % n;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Animal> getAnimals(){
        return this.animals;
    }
}

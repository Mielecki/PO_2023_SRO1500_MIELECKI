package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

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
            Animal animal = new Animal(position);
            animalsTemp.add(animal);
            worldMap.place(animal);
        }
        return animalsTemp;
    }

    public void run(){
        int n = animals.size();
        int animalIndex = 0;

        for(MoveDirection move: moves){
            Animal currAnimal = animals.get(animalIndex);
            worldMap.move(currAnimal, move);
            System.out.println(currAnimal.getPosition());
            System.out.println(worldMap);

            animalIndex = (animalIndex + 1) % n;
        }
    }

    public List<Animal> getAnimals(){
        return this.animals;
    }
}

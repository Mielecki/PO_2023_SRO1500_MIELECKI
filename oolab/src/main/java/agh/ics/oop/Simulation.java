package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves){
        this.moves = moves;
        this.animals = positionsToAnimals(positions);
    }

    private List<Animal> positionsToAnimals(List<Vector2d> positions){
        List<Animal> animalsTemp = new ArrayList<>();
        for (Vector2d position : positions){
            animalsTemp.add(new Animal(position));
        }
        return animalsTemp;
    }

    public void run(){
        int n = animals.size();
        int animalIndex = 0;

        for(MoveDirection move: moves){
            Animal currAnimal = animals.get(animalIndex);
            currAnimal.move(move);

            System.out.println("Zwierzę " + animalIndex + ": " + currAnimal);

            animalIndex = (animalIndex + 1) % n;
        }
    }

    public List<Animal> getAnimals(){
        return this.animals;
    }
}

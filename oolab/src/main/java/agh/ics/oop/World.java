package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void run(MoveDirection[] args){
        for(MoveDirection arg : args){
            String toSay = switch (arg){
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case LEFT -> "Zwierzak skręca w lewo";
                case RIGHT -> "Zwierzak skręca w prawo";
            };
            System.out.println(toSay);
        }
    }
    public static void main(String [] args){
        try{
            int simulationQuantity = 1;
            List<Simulation> simulations = new ArrayList<>();
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
            ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
            for (int i = 0; i < simulationQuantity; i++) {
                GrassField map = new GrassField(10);
                map.addObserver(consoleMapDisplay);
                Simulation simulation = new Simulation(positions, directions, map);
                simulations.add(simulation);
            }
            SimulationEngine engine = new SimulationEngine(simulations);
            engine.runAsyncInThreadPool();
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("System zakończył działanie");
    }
    }
